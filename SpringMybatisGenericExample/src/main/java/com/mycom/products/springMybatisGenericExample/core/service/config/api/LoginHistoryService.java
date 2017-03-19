/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.config.api;

import com.mycom.products.springMybatisGenericExample.core.bean.config.LoginHistoryBean;
import com.mycom.products.springMybatisGenericExample.core.service.base.api.root.InsertableService;
import com.mycom.products.springMybatisGenericExample.core.service.base.api.root.JoinedSelectableService;

public interface LoginHistoryService
		extends JoinedSelectableService<LoginHistoryBean>, InsertableService<LoginHistoryBean> {

}
