/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.config.ActionDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.service.base.JoinedSelectableServiceImpl;

@Service
public class ActionServiceImpl extends JoinedSelectableServiceImpl<ActionBean> implements ActionService {

	private ActionDao actionDao;

	@Autowired
	public ActionServiceImpl(ActionDao dao) {
		super(dao);
		this.actionDao = dao;
	}

	private Logger serviceLogger = Logger.getLogger("ServiceLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "ApplicationCache")
	public List<String> getAllPageNamesByModule(String module) throws BusinessException {
		List<String> results = null;
		serviceLogger.info(BaseBean.LOGBREAKER);
		serviceLogger.info("Transaction start for fetching all 'pageNames' process.");
		try {
			results = actionDao.selectPageNamesByModule(module);
		} catch (DAOException e) {
			errorLogger.info("Fetching all 'pageNames' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for fetching 'pageNames' process.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

}
