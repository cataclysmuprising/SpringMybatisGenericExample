package com.mycom.products.mywebsite.backend.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mycom.products.mywebsite.backend.EntryPoint;
import com.mycom.products.mywebsite.backend.security.DbFilterInvocationSecurityMetadataSource;
import com.mycom.products.mywebsite.core.bean.config.LoggedUserBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;

@Controller
public abstract class BaseController {

    protected final String LOGBREAKER = "---------------------------------------";

    private Logger appLogger = Logger.getLogger("AppLogger");
    private Logger errorLogger = Logger.getLogger("ErrorLogger");

    @Autowired
    private DbFilterInvocationSecurityMetadataSource securityMetaData;

    protected LoggedUserBean loginUser;

    @ModelAttribute
    public void init(Model model, HttpServletRequest request) throws BusinessException {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof LoggedUserBean) {
	    loginUser = (LoggedUserBean) principal;
	} else {
	    loginUser = null;
	}
	if (loginUser != null) {
	    model.addAttribute("loginUserName", loginUser.getRealName());
	    model.addAttribute("loginUserId", loginUser.getId());
	    model.addAttribute("contentId", loginUser.getContentId());
	}
	model.addAttribute("projectVersion", EntryPoint.getProjectVersion());
	// Add the mendatory actions for the application
	securityMetaData.getMendatoryActionsForApplcation().forEach(action -> {
	    String actionName = action.getActionName();
	    model.addAttribute(actionName, getAccessRolesForAction(actionName));
	});
	subInit(model);
    }

    protected void setAuthorities(Model model, String page) throws BusinessException {
	if (loginUser == null) {
	    return;
	}
	Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
	Iterator<? extends GrantedAuthority> itr = authorities.iterator();
	List<String> loginUserRoles = new ArrayList<>();
	while (itr.hasNext()) {
	    loginUserRoles.add(itr.next().getAuthority());
	}
	appLogger.info("User '" + loginUser.getRealName() + "' own " + loginUserRoles + " Roles.");
	model.addAttribute("page", page.toLowerCase());
	appLogger.info(
		"User '" + loginUser.getRealName() + "' request '" + page.toLowerCase() + "' related informations.");

	HashMap<String, Boolean> accessments = new HashMap<>();
	securityMetaData.getActionsOfPage(page).forEach(action -> {
	    String actionName = action.getActionName();
	    String accessRoles = getAccessRolesForAction(action.getActionName());
	    for (String role : loginUserRoles) {
		if (accessRoles.contains("'" + role + "'")) {
		    accessments.put(actionName, true);
		    break;
		} else {
		    accessments.put(actionName, false);
		}
	    }
	    model.addAttribute(actionName, accessRoles);
	});
	model.addAttribute("accessments", accessments);
	appLogger.info("User '" + loginUser.getRealName() + "' has authorities for " + accessments);
	appLogger.info(LOGBREAKER);
    }

    private String getAccessRolesForAction(String actionName) {
	List<String> roles = securityMetaData.getAssociatedRolesByAction(actionName);
	String accessRoles = "";
	if (roles != null) {
	    for (int i = 0; i < roles.size(); i++) {
		if (roles.get(i) != null) {
		    accessRoles += "'" + roles.get(i) + "'";
		    if (i + 1 != roles.size()) {
			accessRoles += ",";
		    }
		}
	    }
	    appLogger.info("Access Roles for action [" + actionName + "] === > " + accessRoles);
	}
	return accessRoles;
    }

    protected String getPureString(String input) {
	return input.replaceAll("[^a-zA-Z0-9> \\/\\-\\.]+", "").trim();
    }

    public abstract void subInit(Model model) throws BusinessException;
}
