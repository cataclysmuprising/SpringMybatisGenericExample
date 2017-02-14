/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;

import com.mycom.products.mywebsite.core.annotation.TXManageable;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.api.InsertableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.base.api.root.InsertableService;

@TXManageable
public class InsertableServiceImpl<T extends BaseBean> implements InsertableService<T> {
	private InsertableDao<T> dao;

	public InsertableServiceImpl(InsertableDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public long insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting" + getObjectName(record) + "informations.");
		long lastInsertedId = 0;
		try {
			lastInsertedId = dao.insert(record, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for inserting" + getObjectName(record) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting multi" + getObjectName(records) + "informations.");
		try {
			dao.insert(records, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for inserting multi" + getObjectName(records) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);

	}

}
