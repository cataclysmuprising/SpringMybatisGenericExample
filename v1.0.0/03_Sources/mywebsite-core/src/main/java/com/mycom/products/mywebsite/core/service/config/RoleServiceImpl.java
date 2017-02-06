/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.config.RoleDao;
import com.mycom.products.mywebsite.core.service.base.JoinedSelectableServiceImpl;

@Service
public class RoleServiceImpl extends JoinedSelectableServiceImpl<RoleBean> implements RoleService {

	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao dao) {
		super(dao);
		this.roleDao = dao;
	}

	private Logger serviceLogger = Logger.getLogger("ServiceLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

}
