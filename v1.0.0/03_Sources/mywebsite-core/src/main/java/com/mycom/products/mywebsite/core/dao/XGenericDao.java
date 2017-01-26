package com.mycom.products.mywebsite.core.dao;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface XGenericDao<T extends BaseBean> extends InsertableDao<T> {

	public int insert(int key1, int key2, int recordRegId) throws DuplicatedEntryException, MyBatisException;

	public int delete(int key1, int key2, int recordUpdId) throws ConsistencyViolationException, MyBatisException;

	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException;

	public void merge(int mainKey, List<Integer> relatedKeys,
			int recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, MyBatisException;

	public List<Integer> select(int key) throws MyBatisException;

	public T select(int key1, int key2) throws MyBatisException;

	public List<T> selectList(Map<String, Object> criteria) throws MyBatisException;

	public int selectCounts(Map<String, Object> criteria) throws MyBatisException;

}
