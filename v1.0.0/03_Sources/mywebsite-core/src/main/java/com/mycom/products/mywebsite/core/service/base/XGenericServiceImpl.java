/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.annotation.TXManageable;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.XGenericDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.base.api.XGenericService;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class XGenericServiceImpl<T extends BaseBean> implements XGenericService<T> {
	private XGenericDao<T> dao;

	public XGenericServiceImpl(XGenericDao<T> dao) {
		this.dao = dao;
	}

	@Override
	@TXManageable
	public void insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting" + getObjectName(record) + "informations.");
		try {
			dao.insert(record, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
	}

	@Override
	@TXManageable
	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting" + getObjectName(records) + "informations.");
		try {
			dao.insert(records, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
	}

	@Override
	@TXManageable
	public void insert(long key1, long key2, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting with keys {key1=" + key1 + ",key2=" + key2 + "}");
		try {
			dao.insert(key1, key2, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
	}

	@Override
	@TXManageable
	public void merge(long mainKey, List<Long> relatedKeys,
			long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for merging (Main Key = " + mainKey + ") with related keys ==> " + relatedKeys);
		try {
			dao.merge(mainKey, relatedKeys, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
	}

	@Override
	@TXManageable
	public long delete(long key1, long key2, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for delete by Keys ==> {key1=" + key1 + ",key2=" + key2 + "}");
		long totalEffectedRows = 0;
		try {
			totalEffectedRows = dao.delete(key1, key2, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return totalEffectedRows;
	}

	@Override
	@TXManageable
	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for delete by criteria ==> " + criteria);
		long totalEffectedRows = 0;
		try {
			totalEffectedRows = dao.delete(criteria, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return totalEffectedRows;
	}

	@Override
	public List<Long> selectByKey1(long key1) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching related keys by key1# " + key1);
		List<Long> relatedKeys = null;
		try {
			relatedKeys = dao.selectByKey1(key1);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return relatedKeys;
	}

	@Override
	public List<Long> selectByKey2(long key2) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching related keys by key2# " + key2);
		List<Long> relatedKeys = null;
		try {
			relatedKeys = dao.selectByKey2(key2);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return relatedKeys;
	}

	@Override
	public T select(long key1, long key2, FetchMode fetchMode) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching single record by Keys ==> {key1=" + key1 + ",key2=" + key2 + "}");
		T record = null;
		try {
			record = dao.select(key1, key2, fetchMode);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return record;
	}

	@Override
	public List<T> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching multi records by criteria ==> " + criteria);
		List<T> records = null;
		try {
			records = dao.selectList(criteria, fetchMode);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return records;
	}

	@Override
	public long selectCounts(Map<String, Object> criteria) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching record counts by criteria ==> " + criteria);
		long count = 0;
		try {
			count = dao.selectCounts(criteria);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

}
