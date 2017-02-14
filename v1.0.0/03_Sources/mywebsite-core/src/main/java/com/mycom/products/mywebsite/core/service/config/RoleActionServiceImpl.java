/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.service.base.XGenericServiceImpl;
import com.mycom.products.mywebsite.core.service.config.api.RoleActionService;

@Service
public class RoleActionServiceImpl extends XGenericServiceImpl<RoleActionBean>
		implements RoleActionService {

	@Autowired
	public RoleActionServiceImpl(RoleActionDao roleActionDao) {
		super(roleActionDao);
	}

}
