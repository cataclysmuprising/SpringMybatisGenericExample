/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.springMybatisGenericExample.core.bean.config.UserRoleBean;
import com.mycom.products.springMybatisGenericExample.core.dao.config.UserRoleDao;
import com.mycom.products.springMybatisGenericExample.core.service.base.XGenericServiceImpl;
import com.mycom.products.springMybatisGenericExample.core.service.config.api.UserRoleService;

@Service
public class UserRoleServiceImpl extends XGenericServiceImpl<UserRoleBean> implements UserRoleService {

	@Autowired
	public UserRoleServiceImpl(UserRoleDao userRoleDao) {
		super(userRoleDao);
	}

}
