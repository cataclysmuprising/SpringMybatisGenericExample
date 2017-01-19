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
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.config.UserRoleDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int add(UserRoleBean userRole, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int lastInsertedId = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for insert new 'UserRole' information with the values" + userRole);
		try {
			lastInsertedId = userRoleDao.insert(userRole, loginUserId);
			coreLogger.debug("Insertion for new 'UserRole' information process has finished.");
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'UserRole' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'UserRole' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int modify(UserRoleBean userRole, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'UserRole' information with the values" + userRole);
		try {
			effectedRows = userRoleDao.update(userRole, loginUserId);
			coreLogger.debug("Updating for 'UserRole' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'UserRole' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'UserRole' with Id = " + userRole.getId());
		coreLogger.info(BaseBean.LOGBREAKER);
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int remove(Map<String, Object> criteria,
			int loginUserId) throws BusinessException, ConsistencyViolationException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for removing existing 'UserRole' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = userRoleDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'UserRole' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'UserRole' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.UserRole", key = "#id")
	public UserRoleBean getById(int id) throws BusinessException {
		UserRoleBean result = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'UserRole' information by Id = " + id);
		try {
			result = userRoleDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'UserRole' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'UserRole' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.UserRole", key = "#criteria.toString()")
	public List<UserRoleBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		List<UserRoleBean> results = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'UserRole' informations with criteria = " + criteria);
		try {
			results = userRoleDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'UserRole' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'UserRole' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.UserRole", key = "#criteria.toString().concat('_count')")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		int count = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'UserRole' counts with criteria = " + criteria);
		try {
			count = userRoleDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'UserRole' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'UserRole' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}
}
