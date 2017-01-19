package com.mycom.products.mywebsite.core.service.config;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.BaseService;

public interface UserService extends BaseService<UserBean> {
	public UserBean getByLoginId(String loginId) throws BusinessException;
}