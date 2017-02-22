package com.mycom.products.mywebsite.backend.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController extends BaseController {

	private Logger appLogger = Logger.getLogger("AppLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Override
	public void subInit(Model model) {

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("messageStyle", "alert-danger");
		model.addAttribute("pageMessage", "Incorrect Login ID or Password.");
		return "login";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		if (loginUser != null) {
			errorLogger.error(LOGBREAKER);
			errorLogger.error("User '" + loginUser.getRealName() + "' has requested for unauthorized contents.");
			errorLogger.error(LOGBREAKER);
		}
		return "error/403";
	}

	@RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
	public String sessionTimeOut(Model model) {
		if (loginUser != null) {
			errorLogger.error(LOGBREAKER);
			errorLogger.error("Session of User '" + loginUser.getRealName() + "' was Time-out.");
			errorLogger.error(LOGBREAKER);
		}
		return "error/401";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes attributes) {

		attributes.addFlashAttribute("messageStyle", "alert-success");
		attributes.addFlashAttribute("pageMessage", "You have been logged out successfully.");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			appLogger.info("User '" + loginUser.getRealName() + "' has been signed out.");
		}

		// delete remember me cookie after logout
		Cookie cookie = new Cookie("mywebsite_rbm", null);
		cookie.setMaxAge(0);
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");

		response.addCookie(cookie);

		return "redirect:/login?logout";
	}
}