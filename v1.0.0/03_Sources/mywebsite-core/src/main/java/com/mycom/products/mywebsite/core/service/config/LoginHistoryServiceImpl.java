package com.mycom.products.mywebsite.core.service.config;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.dao.config.LoginHistoryDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Autowired
	private LoginHistoryDao loginHistoryDao;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = "ApplicationCache.LoginHistory", allEntries = true)
	public int add(LoginHistoryBean loginHistory, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int lastInsertedId = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for insert new 'LoginHistory' information with the values" + loginHistory);
		try {
			lastInsertedId = loginHistoryDao.insert(loginHistory, loginUserId);
			coreLogger.debug("Insertion for 'LoginHistory' information process has finished.");
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'LoginHistory' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'LoginHistory' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = "ApplicationCache.LoginHistory", allEntries = true)
	public int modify(LoginHistoryBean loginHistory,
			int loginUserId) throws BusinessException, DuplicatedEntryException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'LoginHistory' information with the values" + loginHistory);
		try {
			effectedRows = loginHistoryDao.update(loginHistory, loginUserId);
			coreLogger.debug("Updating for 'LoginHistory' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'LoginHistory' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'LoginHistory' with Id = " + loginHistory.getId());
		coreLogger.info(BaseBean.LOGBREAKER);
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = "ApplicationCache.LoginHistory", allEntries = true)
	public int remove(Map<String, Object> criteria,
			int loginUserId) throws BusinessException, ConsistencyViolationException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for removing existing 'LoginHistory' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = loginHistoryDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'LoginHistory' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'LoginHistory' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.LoginHistory", key = "#id")
	public LoginHistoryBean getById(int id) throws BusinessException {
		LoginHistoryBean result = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'LoginHistory' information by Id = " + id);
		try {
			result = loginHistoryDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'LoginHistory' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'LoginHistory' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.LoginHistory", key = "#criteria.toString()")
	public List<LoginHistoryBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		List<LoginHistoryBean> results = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'LoginHistory' informations with criteria = " + criteria);
		try {
			results = loginHistoryDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'LoginHistory' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'LoginHistory' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.LoginHistory", key = "#criteria.toString().concat('_count')")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		int count = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'LoginHistory' counts with criteria = " + criteria);
		try {
			count = loginHistoryDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'LoginHistory' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'LoginHistory' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}
}
