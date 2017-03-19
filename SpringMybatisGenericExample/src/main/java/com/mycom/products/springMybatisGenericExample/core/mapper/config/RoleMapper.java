/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.config;

import com.mycom.products.springMybatisGenericExample.core.bean.config.RoleBean;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.CommonGenericMapper;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.JoinedSelectableMapper;

public interface RoleMapper extends CommonGenericMapper<RoleBean>, JoinedSelectableMapper<RoleBean> {
}
