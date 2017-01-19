package com.mycom.products.mywebsite.core.service;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface BaseService<T extends BaseBean> {
	public int add(T bean,
			int loginUserId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException;

	public int modify(T bean,
			int loginUserId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException;

	public int remove(Map<String, Object> criteria,
			int loginUserId) throws ConsistencyViolationException, BusinessException;

	public T getById(int id) throws BusinessException;

	public List<T> getByCriteria(Map<String, Object> criteria) throws BusinessException;

	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException;
}
