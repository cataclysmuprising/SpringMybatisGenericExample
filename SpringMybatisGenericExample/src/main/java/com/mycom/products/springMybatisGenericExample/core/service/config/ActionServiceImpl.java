/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.bean.config.ActionBean;
import com.mycom.products.springMybatisGenericExample.core.dao.config.ActionDao;
import com.mycom.products.springMybatisGenericExample.core.exception.BusinessException;
import com.mycom.products.springMybatisGenericExample.core.exception.DAOException;
import com.mycom.products.springMybatisGenericExample.core.service.base.JoinedSelectableServiceImpl;
import com.mycom.products.springMybatisGenericExample.core.service.config.api.ActionService;

@Service
public class ActionServiceImpl extends JoinedSelectableServiceImpl<ActionBean> implements ActionService {
	private Logger serviceLogger = Logger.getLogger("ServiceLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	private ActionDao actionDao;

	@Autowired
	public ActionServiceImpl(ActionDao actionDao) {
		super(actionDao);
		this.actionDao = actionDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> selectPageNamesByModule(String module) throws BusinessException {
		serviceLogger.info(BaseBean.LOGBREAKER);
		List<String> results = null;
		serviceLogger.info("Transaction start for fetching all 'pageNames' process.");
		try {
			results = actionDao.selectPageNamesByModule(module);
		} catch (DAOException e) {
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		serviceLogger.info("Transaction finished successfully for fetching 'pageNames' process.");
		serviceLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

}
