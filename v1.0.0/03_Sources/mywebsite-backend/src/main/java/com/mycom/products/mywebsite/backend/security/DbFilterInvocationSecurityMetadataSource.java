package com.mycom.products.mywebsite.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.mycom.products.mywebsite.backend.EntryPoint;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.bean.config.ActionBean.ActionType;
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.config.api.RoleActionService;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Component
public class DbFilterInvocationSecurityMetadataSource
	implements FilterInvocationSecurityMetadataSource, InitializingBean, DisposableBean {
    private Logger appLogger = Logger.getLogger("AppLogger");
    private Logger errorLogger = Logger.getLogger("ErrorLogger");

    @Autowired
    private RoleActionService roleActionService;

    @Autowired
    private EhCacheCacheManager cacheManager;

    private HashMap<String, List<String>> urlRolesMapper;
    private HashMap<String, String> actionUrlMapper;
    private static List<RoleActionBean> roleActions;
    private static Set<ActionBean> mendatoryActions;

    public DbFilterInvocationSecurityMetadataSource() {
	urlRolesMapper = new HashMap<>();
	actionUrlMapper = new HashMap<>();
    }

    @Override
    public void afterPropertiesSet() throws BusinessException {
	appLogger.info("*** Initializing roles for specified URLs and Actions ***");
	initializeRolesAndActions();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	try {
	    FilterInvocation fi = (FilterInvocation) object;
	    String url = fi.getRequestUrl();
	    appLogger.info("Request Url====> " + url);

	    List<String> roles = getAssociatedRolesByUrl(url);
	    appLogger.info("Url Associated Roles :" + roles);
	    if (roles == null) {
		return null;
	    }
	    appLogger.info(BaseBean.LOGBREAKER);
	    String[] stockArr = new String[roles.size()];
	    stockArr = roles.toArray(stockArr);
	    return SecurityConfig.createList(stockArr);
	} catch (Exception e) {
	    errorLogger.error(e.getMessage(), e);
	}
	return null;
    }

    private void initializeRolesAndActions() throws BusinessException {
	HashMap<String, Object> criteria = new HashMap<>();
	criteria.put("module", EntryPoint.MODULE_NAME);
	roleActions = roleActionService.selectList(criteria, FetchMode.EAGER);
	roleActions.forEach(roleAction -> {
	    String url = roleAction.getAction().getUrl();
	    actionUrlMapper.put(roleAction.getAction().getActionName(), roleAction.getAction().getUrl());
	    if (this.urlRolesMapper.containsKey(url)) {
		List<String> roles = this.urlRolesMapper.get(url);
		roles.add(roleAction.getRole().getName());

	    } else {
		List<String> roles = new ArrayList<>();
		roles.add(roleAction.getRole().getName());
		this.urlRolesMapper.put(url, roles);
	    }
	});
    }

    public List<String> getAssociatedRolesByUrl(String url) {
	return urlRolesMapper.entrySet().stream().filter(entry -> entry.getKey().matches(url))
		.flatMap(entry -> entry.getValue().stream()).collect(Collectors.toList());
    }

    public List<String> getAssociatedRolesByAction(String actionName) {
	return actionUrlMapper.entrySet().stream().filter(entry -> actionName.equals(entry.getKey()))
		.flatMap(entry -> urlRolesMapper.get(entry.getValue()).stream()).collect(Collectors.toList());
    }

    public Set<ActionBean> getMendatoryActionsForApplcation() {
	if (mendatoryActions == null) {
	    mendatoryActions = roleActions.stream()
		    .filter(roleAction -> roleAction.getAction().getActionType() == ActionType.MAIN)
		    .map(roleAction -> roleAction.getAction()).collect(Collectors.toSet());
	}
	return mendatoryActions;
    }

    public Set<ActionBean> getActionsOfPage(String page) {
	Predicate<RoleActionBean> pageActionFilter = new Predicate<RoleActionBean>() {
	    @Override
	    public boolean test(RoleActionBean roleAction) {
		ActionBean action = roleAction.getAction();
		return action.getActionType() == ActionType.SUB && action.getPage().equalsIgnoreCase(page);
	    }
	};
	return roleActions.stream().filter(pageActionFilter).map(roleAction -> roleAction.getAction())
		.collect(Collectors.toSet());
    }

    @Override
    public void destroy() throws Exception {
	appLogger.info("xxxxxxxxxxxxxxxxx clear all caches before application was shutting down xxxxxxxxxxxxxxxxx");
	cacheManager.getCacheManager().clearAll();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
	return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
	return true;
    }

}
