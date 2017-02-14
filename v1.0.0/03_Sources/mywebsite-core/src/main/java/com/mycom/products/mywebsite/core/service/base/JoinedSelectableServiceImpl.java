/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.JoinedSelectableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.service.base.api.root.JoinedSelectableService;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class JoinedSelectableServiceImpl<T extends BaseBean> implements JoinedSelectableService<T> {
	private JoinedSelectableDao<T> dao;

	public JoinedSelectableServiceImpl(JoinedSelectableDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public T select(long primaryKey, FetchMode fetchMode) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetch by primary key # " + primaryKey + " with fetchMode ==> " + fetchMode);
		T record = null;
		try {
			record = dao.select(primaryKey, fetchMode);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return record;
	}

	@Override
	public T select(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching single record by criteria ==> " + criteria + " with fetchMode ==> " + fetchMode);
		T record = null;
		try {
			record = dao.select(criteria, fetchMode);
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
		serviceLogger.info("Transaction start for fetching multi records by criteria ==> " + criteria + " with fetchMode ==> " + fetchMode);
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
	public long selectCounts(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching record counts by criteria ==> " + criteria + " with fetchMode ==> " + fetchMode);
		long count = 0;
		try {
			count = dao.selectCounts(criteria, fetchMode);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

}
