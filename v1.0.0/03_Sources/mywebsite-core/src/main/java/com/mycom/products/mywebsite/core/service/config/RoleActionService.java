package com.mycom.products.mywebsite.core.service.config;

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.BaseService;

public interface RoleActionService extends BaseService<RoleActionBean> {

	public void changeRoleAcess(int roleId, String accessQuery,
			int loginUserId) throws BusinessException, DuplicatedEntryException, ConsistencyViolationException;

}