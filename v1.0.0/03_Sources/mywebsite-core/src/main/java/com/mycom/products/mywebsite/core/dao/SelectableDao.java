package com.mycom.products.mywebsite.core.dao;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface SelectableDao<T extends BaseBean> {
	public T select(int primaryKey) throws MyBatisException;

	public T select(Map<String, Object> criteria) throws MyBatisException;

	public List<T> selectList(Map<String, Object> criteria) throws MyBatisException;

	public int selectCounts(Map<String, Object> criteria) throws MyBatisException;
}
