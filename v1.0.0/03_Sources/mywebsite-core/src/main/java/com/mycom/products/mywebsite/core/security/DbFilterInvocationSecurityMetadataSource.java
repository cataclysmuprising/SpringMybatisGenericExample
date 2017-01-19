package com.mycom.products.mywebsite.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.config.RoleActionService;

@Component
public class DbFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource,
		InitializingBean, DisposableBean, ApplicationListener<AuthorityChangeEvent> {
	private Logger appLogger = Logger.getLogger("AppLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");
	@Autowired
	private RoleActionService roleActionService;

	@Autowired
	private EhCacheCacheManager cacheManager;

	private HashMap<String, List<String>> urlRolesMapper;
	private HashMap<String, String> urlActionMapper;

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

	@Override
	public void afterPropertiesSet() throws BusinessException {
		appLogger.info("*** Initializing roles for specified URLs and Actions ***");
		initializeRolesAndActions();
	}

	public List<String> getAssociatedRolesByUrl(String url) throws BusinessException {
		for (String key : urlRolesMapper.keySet()) {
			if (url.matches(key)) {
				return urlRolesMapper.get(key);
			}
		}
		return null;
	}

	public List<String> getAssociatedRolesByAction(String actionName) throws BusinessException {
		for (String key : urlRolesMapper.keySet()) {
			if (urlActionMapper.get(actionName) != null && urlActionMapper.get(actionName).equals(key)) {
				return urlRolesMapper.get(key);
			}
		}
		return null;
	}

	@Override
	public void onApplicationEvent(AuthorityChangeEvent event) {
		appLogger.info("*** Authorities were changed by Application ***");
		try {
			initializeRolesAndActions();
		} catch (BusinessException e) {
			errorLogger.error("xxxxxx Reloading Roles and related Actions Failed ! xxxxxx");
			errorLogger.error(e.getMessage(), e);
		}
		appLogger.info("----- Reloading Roles and related Actions Finished ! -----");
	}

	private void initializeRolesAndActions() throws BusinessException {
		List<RoleActionBean> roleActions = roleActionService.getByCriteria(new HashMap<>());
		urlRolesMapper = new HashMap<>();
		urlActionMapper = new HashMap<>();
		for (RoleActionBean roleAction : roleActions) {
			String requestUrl = roleAction.getAction().getUrl();
			urlActionMapper.put(roleAction.getAction().getActionName(), roleAction.getAction().getUrl());
			if (this.urlRolesMapper.containsKey(requestUrl)) {
				List<String> roles = this.urlRolesMapper.get(requestUrl);
				roles.add(roleAction.getRole().getName());

			} else {
				List<String> roles = new ArrayList<String>();
				roles.add(roleAction.getRole().getName());
				this.urlRolesMapper.put(requestUrl, roles);
			}
		}
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
