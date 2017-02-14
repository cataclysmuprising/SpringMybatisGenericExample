/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.Map;

import com.mycom.products.mywebsite.core.annotation.TXManageable;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.RemoveableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.service.base.api.root.RemoveableService;

@TXManageable
public class RemoveableServiceImpl<T extends BaseBean> implements RemoveableService<T> {
	private RemoveableDao<T> dao;

	public RemoveableServiceImpl(RemoveableDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public long delete(long id, long recordUpdId) throws ConsistencyViolationException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for delete by ID ==> " + id);
		long totalEffectedRows = 0;
		try {
			totalEffectedRows = dao.delete(id, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return totalEffectedRows;
	}

	@Override
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

}
