/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.config;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.mapper.base.CommonGenericMapper;
import com.mycom.products.mywebsite.core.mapper.base.JoinedSelectableMapper;

public interface UserMapper extends CommonGenericMapper<UserBean>, JoinedSelectableMapper<UserBean> {

}
