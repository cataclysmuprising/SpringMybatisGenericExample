/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config.api;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.service.base.api.JoinedService;

public interface UserService extends JoinedService<UserBean> {

	UserBean selectAuthenticatedUser(String loginId, String password);

}
