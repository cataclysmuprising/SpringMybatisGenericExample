package com.mycom.products.mywebsite.core.security;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mycom.products.mywebsite.core.bean.config.LoggedUserBean;
import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.service.config.LoginHistoryService;
import com.mycom.products.mywebsite.core.service.config.UserRoleService;
import com.mycom.products.mywebsite.core.service.config.UserService;

@Component
public class AuthenticationUserService implements UserDetailsService {
	private Logger appLogger = Logger.getLogger("AppLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private LoginHistoryService loginHistoryService;

	@Override
	public final UserDetails loadUserByUsername(final String loginId) throws UsernameNotFoundException {
		UserBean user = null;
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			user = userService.getByLoginId(loginId);
			if (user == null) {
				throw new UsernameNotFoundException("User doesn`t exist");
			}
			LoginHistoryBean loginHistory = new LoginHistoryBean();
			loginHistory.setClientIp(getClientIp(request));
			loginHistory.setOs(getOperatingSystem(request));
			loginHistory.setBrowser(getBrowser(request));
			loginHistory.setLoginDate(new Timestamp(System.currentTimeMillis()));
			loginHistory.setUserId(user.getId());
			loginHistoryService.add(loginHistory, user.getId());
			Map<String, Object> criterias = new HashMap<>();
			criterias.put("userId", user.getId());
			List<UserRoleBean> userRoles = userRoleService.getByCriteria(criterias);
			List<String> dbRoles = new ArrayList<String>();
			for (UserRoleBean userRole : userRoles) {
				dbRoles.add(userRole.getRole().getName());
			}
			appLogger.info("User '" + user.getName() + "' has successfully signed in.");
			appLogger.info("Roles of :" + user.getName() + " are " + dbRoles);
			// pass user object and roles to LoggedUser
			LoggedUserBean loggedUser = new LoggedUserBean(user, dbRoles);
			return loggedUser;
		} catch (Exception e) {
			e.printStackTrace();
			errorLogger.error("Signin user is not valid or found", e);
			return null;
		}
	}

	private String getClientIp(HttpServletRequest request) {
		return request.getRemoteAddr();

	}

	private String getOperatingSystem(HttpServletRequest request) {
		String os = "";
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		return os;
	}

	private String getBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		String user = userAgent.toLowerCase();

		String browser = "";
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

}
