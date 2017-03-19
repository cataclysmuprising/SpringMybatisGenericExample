/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.config;

import com.mycom.products.springMybatisGenericExample.core.bean.config.UserBean;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.CommonGenericMapper;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.JoinedSelectableMapper;

public interface UserMapper extends CommonGenericMapper<UserBean>, JoinedSelectableMapper<UserBean> {
}
