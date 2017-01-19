package com.mycom.products.mywebsite.core.service.config;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.BaseService;

public interface ActionService extends BaseService<ActionBean> {

	public List<String> getAllPageNames() throws BusinessException;
}