/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.HashMap;
import java.util.List;

import com.mycom.products.mywebsite.core.annotation.TXManageable;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.UpdateableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.base.api.root.UpdateableService;

@TXManageable
public class UpdateableServiceImpl<T extends BaseBean> implements UpdateableService<T> {
	private UpdateableDao<T> dao;

	public UpdateableServiceImpl(UpdateableDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public long update(T record, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for updating" + getObjectName(record) + "informations.");
		long totalEffectedRows = 0;
		try {
			totalEffectedRows = dao.update(record, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for updating" + getObjectName(record) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return totalEffectedRows;
	}

	@Override
	public void update(List<T> records, long recordUpdId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for updating multi" + getObjectName(records) + "informations.");
		try {
			dao.update(records, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for updating multi" + getObjectName(records) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);
	}

	@Override
	public long update(HashMap<String, Object> criteria, HashMap<String, Object> updateItems,
			long recordUpdId) throws BusinessException, DuplicatedEntryException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordUpdId + " ***");
		serviceLogger.info("Transaction start for updating" + updateItems + " with criteria ==> " + criteria);
		long totalEffectedRows = 0;
		try {
			totalEffectedRows = dao.update(criteria, updateItems, recordUpdId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return totalEffectedRows;
	}

}
