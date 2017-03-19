/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.dao.api.CommonGenericDao;
import com.mycom.products.springMybatisGenericExample.core.dao.api.JoinedSelectableDao;
import com.mycom.products.springMybatisGenericExample.core.exception.BusinessException;
import com.mycom.products.springMybatisGenericExample.core.exception.DAOException;
import com.mycom.products.springMybatisGenericExample.core.service.base.api.JoinedService;
import com.mycom.products.springMybatisGenericExample.core.util.FetchMode;

public class JoinedServiceImpl<T extends BaseBean> extends CommonGenericServiceImpl<T> implements JoinedService<T> {
	private JoinedSelectableDao<T> dao;

	public JoinedServiceImpl(CommonGenericDao<T> commonGenericDao, JoinedSelectableDao<T> joinedSelectableDao) {
		super(commonGenericDao);
		this.dao = joinedSelectableDao;
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
	@Transactional(readOnly = true)
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
