/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.dao.config.LoginHistoryDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.base.JoinedSelectableServiceImpl;
import com.mycom.products.mywebsite.core.service.config.api.LoginHistoryService;

@Service
public class LoginHistoryServiceImpl extends JoinedSelectableServiceImpl<LoginHistoryBean>
		implements LoginHistoryService {
	private LoginHistoryDao loginHistoryDao;

	@Autowired
	public LoginHistoryServiceImpl(LoginHistoryDao dao) {
		super(dao);
		this.loginHistoryDao = dao;
	}

	@Override
	public long insert(LoginHistoryBean record, long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting" + getObjectName(record) + "informations.");
		long lastInsertedId = 0;
		try {
			lastInsertedId = loginHistoryDao.insert(record, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for inserting" + getObjectName(record) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	public void insert(List<LoginHistoryBean> records,
			long recordRegId) throws DuplicatedEntryException, BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("*** This transaction was initiated by User ID # " + recordRegId + " ***");
		serviceLogger.info("Transaction start for inserting multi" + getObjectName(records) + "informations.");
		try {
			loginHistoryDao.insert(records, recordRegId);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for inserting multi" + getObjectName(records) + "informations.");
		serviceLogger.info(BaseBean.LOGBREAKER);

	}
}
