package com.mycom.products.mywebsite.backend.controller;

import java.util.HashMap;
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

import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.config.api.RoleService;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Controller
@RequestMapping("/roles")
public class RoleController extends BaseController {

    private Logger appLogger = Logger.getLogger("AppLogger");
    private Logger errorLogger = Logger.getLogger("ErrorLogger");

    @Autowired
    private RoleService roleService;

    public RoleController() {
    }

    @InitBinder("role")
    public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Override
    public void subInit(Model model) throws BusinessException {
	setAuthorities(model, "role");
    }

    @RequestMapping(value = "/api/search")
    public @ResponseBody Map<String, Object> search(@RequestParam int start, @RequestParam int length,
	    @RequestParam String orderBy, @RequestParam String orderAs, @RequestParam String word,
	    @RequestParam(required = false) String includeIds, @RequestParam(required = false) String fetchMode)
	    throws BusinessException {
	FetchMode mode = FetchMode.LAZY;
	if (fetchMode != null && fetchMode.equals("EAGER")) {
	    mode = FetchMode.valueOf("EAGER");
	}
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

	results.put("iTotalDisplayRecords", roleService.selectCounts(criteria, mode));
	results.put("aaData", roleService.selectList(criteria, mode));

	return results;
    }

    @RequestMapping(value = "api/searchAll", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> searchAll() throws BusinessException {

	Map<String, Object> results = new HashMap<>();

	results.put("iTotalDisplayRecords", roleService.selectCounts(null, FetchMode.LAZY));
	results.put("aaData", roleService.selectList(null, FetchMode.LAZY));

	appLogger.info("Loading ..... finished 'All Roles' by AJAX request.");

	return results;
    }

}
