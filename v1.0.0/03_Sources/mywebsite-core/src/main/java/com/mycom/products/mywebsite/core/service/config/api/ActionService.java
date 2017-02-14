/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config.api;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.base.api.root.JoinedSelectableService;

public interface ActionService extends JoinedSelectableService<ActionBean> {

	List<String> selectPageNamesByModule(String module) throws BusinessException;
}