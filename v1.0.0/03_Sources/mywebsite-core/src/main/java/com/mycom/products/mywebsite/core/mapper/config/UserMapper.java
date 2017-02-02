/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.config;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.mapper.base.CommonGenericMapper;
import com.mycom.products.mywebsite.core.mapper.base.JoinedSelectableMapper;
import com.mycom.products.mywebsite.core.util.FetchMode;

public interface UserMapper extends CommonGenericMapper<UserBean>, JoinedSelectableMapper<UserBean> {
	public UserBean selectAuthenticatedUser(@Param("loginId") String loginId, @Param("password") String password,
			@Param("fetchMode") FetchMode fetchMode);
}
