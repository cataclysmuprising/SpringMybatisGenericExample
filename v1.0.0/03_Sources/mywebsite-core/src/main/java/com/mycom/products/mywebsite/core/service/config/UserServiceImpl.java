/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.dao.config.UserDao;
import com.mycom.products.mywebsite.core.service.base.JoinedServiceImpl;
import com.mycom.products.mywebsite.core.service.config.api.UserService;

@Service
public class UserServiceImpl extends JoinedServiceImpl<UserBean> implements UserService {
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super(userDao, userDao);
	}

}
