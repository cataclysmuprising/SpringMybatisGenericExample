/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.config;

import com.mycom.products.springMybatisGenericExample.core.bean.config.LoginHistoryBean;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.InsertableMapper;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.JoinedSelectableMapper;

public interface LoginHistoryMapper
		extends InsertableMapper<LoginHistoryBean>, JoinedSelectableMapper<LoginHistoryBean> {

}
