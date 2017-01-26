package com.mycom.products.mywebsite.core.dao;

import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface RemoveableDao<T extends BaseBean> {
	public int delete(int primaryKey, int recordUpdId) throws ConsistencyViolationException, MyBatisException;

	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException;
}
