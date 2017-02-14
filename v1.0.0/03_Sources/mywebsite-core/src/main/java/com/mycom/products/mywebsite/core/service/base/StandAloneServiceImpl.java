/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.CommonGenericDao;
import com.mycom.products.mywebsite.core.dao.api.StandAloneSelectableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.service.base.api.StandAloneService;

public class StandAloneServiceImpl<T extends BaseBean> extends CommonGenericServiceImpl<T>
		implements StandAloneService<T> {
	private Logger serviceLogger = Logger.getLogger("ServiceLogger");

	private StandAloneSelectableDao<T> dao;

	public StandAloneServiceImpl(CommonGenericDao<T> commonGenericDao, StandAloneSelectableDao<T> standAloneSelectableDao) {
		super(commonGenericDao);
		this.dao = standAloneSelectableDao;
	}

	@Override
	public T select(long primaryKey) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetch by primary key # " + primaryKey);
		T record = null;
		try {
			record = dao.select(primaryKey);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return record;
	}

	@Override
	public T select(Map<String, Object> criteria) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching single record by criteria ==> " + criteria);
		T record = null;
		try {
			record = dao.select(criteria);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return record;
	}

	@Override
	public List<T> selectList(Map<String, Object> criteria) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching multi records by criteria ==> " + criteria);
		List<T> records = null;
		try {
			records = dao.selectList(criteria);
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
