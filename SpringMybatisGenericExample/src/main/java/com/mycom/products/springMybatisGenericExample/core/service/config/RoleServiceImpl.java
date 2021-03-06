/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.springMybatisGenericExample.core.bean.config.RoleBean;
import com.mycom.products.springMybatisGenericExample.core.dao.config.RoleDao;
import com.mycom.products.springMybatisGenericExample.core.service.base.JoinedServiceImpl;
import com.mycom.products.springMybatisGenericExample.core.service.config.api.RoleService;

@Service
public class RoleServiceImpl extends JoinedServiceImpl<RoleBean> implements RoleService {

	@Autowired
	public RoleServiceImpl(RoleDao dao) {
		super(dao, dao);
	}

}
