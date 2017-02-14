/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private Logger serviceLogger = Logger.getLogger("ServiceLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	private LoginHistoryDao loginHistoryDao;

	@Autowired
	public LoginHistoryServiceImpl(LoginHistoryDao dao) {
		super(dao);
		this.loginHistoryDao = dao;
	}

	@Override
	public long insert(LoginHistoryBean loginHistory,
			long recordRegId) throws DuplicatedEntryException, BusinessException {
		try {
			return loginHistoryDao.insert(loginHistory, recordRegId);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void insert(List<LoginHistoryBean> loginHistories,
			long recordRegId) throws DuplicatedEntryException, BusinessException {
		try {
			loginHistoryDao.insert(loginHistories, recordRegId);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
