/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.base;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface XGenericDaoTest<T extends BaseBean> extends InsertableDaoTest<T> {

	public void insert(int key1, int key2, int recordRegId) throws DuplicatedEntryException, DAOException;

	public int delete(int key1, int key2, int recordUpdId) throws ConsistencyViolationException, DAOException;

	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, DAOException;

	public void merge(int mainKey, List<Integer> relatedKeys,
			int recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException;

	public List<Integer> selectByKey1(int key1) throws DAOException;

	public List<Integer> selectByKey2(int key2) throws DAOException;

	public T select(int key1, int key2) throws DAOException;

	public List<T> selectList(Map<String, Object> criteria) throws DAOException;

	public int selectCounts(Map<String, Object> criteria) throws DAOException;

}
