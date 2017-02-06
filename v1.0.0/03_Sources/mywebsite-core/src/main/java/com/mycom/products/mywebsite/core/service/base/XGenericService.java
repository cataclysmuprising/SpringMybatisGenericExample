/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public interface XGenericService<T extends BaseBean> {

	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException;

	public void insert(long key1, long key2, long recordRegId) throws DuplicatedEntryException, BusinessException;

	public long delete(long key1, long key2, long recordUpdId) throws ConsistencyViolationException, BusinessException;

	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, BusinessException;

	public void merge(long mainKey, List<Long> relatedKeys,
			long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException;

	public List<Long> selectByKey1(long key1) throws BusinessException;

	public List<Long> selectByKey2(long key2) throws BusinessException;

	public T select(long key1, long key2, FetchMode fetchMode) throws BusinessException;

	public List<T> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException;

	public long selectCounts(Map<String, Object> criteria) throws BusinessException;

}
