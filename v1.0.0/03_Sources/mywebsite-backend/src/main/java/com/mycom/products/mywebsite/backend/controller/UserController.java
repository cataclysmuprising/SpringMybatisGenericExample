package com.mycom.products.mywebsite.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.config.api.UserService;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private Logger appLogger = Logger.getLogger("AppLogger");
    private Logger errorLogger = Logger.getLogger("ErrorLogger");

    @Autowired
    private UserService userService;

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Override
    public void subInit(Model model) throws BusinessException {
	setAuthorities(model, "user");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) throws BusinessException {
	appLogger.info("User '" + loginUser.getRealName() + "' request for 'User Home' page.");
	return "user_home";
    }

    @RequestMapping(value = "api/search", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> search(@RequestParam(required = false) Integer start,
	    @RequestParam int length, @RequestParam String orderBy, @RequestParam String orderAs,
	    @RequestParam String word, @RequestParam String loginId, @RequestParam(required = false) String roleId,
	    @RequestParam(required = false) String includeIds) throws BusinessException {

	Map<String, Object> results = new HashMap<>();
	Map<String, Object> criteria = new HashMap<>();

	criteria.put("offset", start);
	criteria.put("limit", length);
	criteria.put("orderBy", orderBy);
	criteria.put("orderAs", orderAs);
	if (includeIds != null && includeIds.length() > 0) {
	    criteria.put("includeIds", includeIds.split(","));
	}
	if (word != null && word.length() > 0) {
	    criteria.put("word", getPureString(word));
	}
	if (loginId != null && loginId.length() > 0) {
	    criteria.put("loginId", loginId);
	}
	if (roleId != null && roleId.length() > 0) {
	    try {
		criteria.put("roleId", Integer.parseInt(roleId));
	    } catch (NumberFormatException e) {
		// ignore this error , just just parse exception
	    }
	}

	List<UserBean> users = userService.selectList(criteria, FetchMode.LAZY);
	results.put("iTotalDisplayRecords", userService.selectCounts(criteria, FetchMode.LAZY));
	results.put("aaData", users);

	appLogger.info("Loading ..... finished 'User' informations by AJAX request with criteria ==> " + criteria);

	return results;
    }
}
