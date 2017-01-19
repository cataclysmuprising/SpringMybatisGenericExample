package com.mycom.products.mywebsite.core.service.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.config.UserDao;
import com.mycom.products.mywebsite.core.dao.config.UserRoleDao;
import com.mycom.products.mywebsite.core.dao.master.StaticContentDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

@Service
public class UserServiceImpl implements UserService {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private StaticContentDao staticContentDao;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int add(UserBean user, int loginUserId) throws BusinessException, DuplicatedEntryException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for insert new 'User' information with the values" + user);
		int lastInsertedId = 0;
		try {
			int contentId = staticContentDao.insert(user.getContent(), loginUserId);
			user.setContentId(contentId);
			lastInsertedId = userDao.insert(user, loginUserId);
			coreLogger.debug("Insertion for 'User' information process has finished.");
			if (lastInsertedId > 0 && user.getRoleIds() != null && user.getRoleIds().size() > 0) {
				coreLogger.debug("Transaction start for insertion related roles of 'User' Id = " + lastInsertedId);
				for (int roleId : user.getRoleIds()) {
					RoleBean role = new RoleBean();
					role.setId(roleId);
					UserRoleBean userRole = new UserRoleBean(user, role);
					userRoleDao.insert(userRole, loginUserId);
				}
				coreLogger.debug("Transaction finished for insertion related roles of 'User' Id = " + lastInsertedId);
				coreLogger.info(BaseBean.LOGBREAKER);
			}
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'User' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'User' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction", "ApplicationCache.StaticContent" }, allEntries = true)
	public int modify(UserBean user,
			int loginUserId) throws BusinessException, ConsistencyViolationException, DuplicatedEntryException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'User' information with the values" + user);
		try {
			staticContentDao.update(user.getContent(), loginUserId);
			effectedRows = userDao.update(user, loginUserId);
			coreLogger.debug("Updating for 'User' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
			if (user.getRoleIds() != null && user.getRoleIds().size() > 0) {
				coreLogger.debug("Transaction start for changing related roles of 'User' Id = " + user.getId());
				Map<String, Object> criteria = new HashMap<>();
				criteria.put("userId", user.getId());
				userRoleDao.delete(criteria, loginUserId);
				coreLogger.debug("Deleting old roles for 'User' Id = " + user.getId() + " has been finished.");
				coreLogger.debug("Total effected rows = " + effectedRows);
				for (int roleId : user.getRoleIds()) {
					RoleBean role = new RoleBean();
					role.setId(roleId);
					UserRoleBean userRole = new UserRoleBean(user, role);
					userRoleDao.insert(userRole, loginUserId);
				}
				coreLogger.debug("Re-insert new roles for 'User' Id = " + user.getId() + " has been finished.");
			}
		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'User' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'User' with Id = " + user.getId());
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
		coreLogger.info("Transaction start for removing existing 'User' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = userDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'User' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'User' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.User", key = "#id")
	public UserBean getById(int id) throws BusinessException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'User' information by Id = " + id);
		UserBean result = null;
		try {
			result = userDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'User' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'User' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.User", key = "#criteria.toString()")
	public List<UserBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'User' informations with criteria = " + criteria);
		List<UserBean> users = null;
		try {
			users = userDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'User' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'User' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return users;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.User", key = "#criteria.toString().concat('_count')")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'User' counts with criteria = " + criteria);
		int count = 0;
		try {
			count = userDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'User' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'User' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.User")
	public UserBean getByLoginId(String loginId) throws BusinessException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'User' information by loginId = " + loginId);
		UserBean user = null;
		try {
			user = userDao.selectByLoginId(loginId);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching all 'User' information by loginId = " + loginId + " process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'User' information with loginId process.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return user;
	}
}
