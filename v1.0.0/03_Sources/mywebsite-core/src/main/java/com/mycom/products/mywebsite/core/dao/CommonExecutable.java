package com.mycom.products.mywebsite.core.dao;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface CommonExecutable<T extends BaseBean> {
	public int insert(T bean, int loginUserId) throws DuplicatedEntryException, MyBatisException;

	public int update(T bean, int loginUserId) throws DuplicatedEntryException, MyBatisException;

	public int delete(Map<String, Object> criteria,
			int loginUserId) throws ConsistencyViolationException, MyBatisException;

	public T selectById(int id) throws MyBatisException;

	public List<T> selectByCriteria(Map<String, Object> criteria) throws MyBatisException;

	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException;

}
