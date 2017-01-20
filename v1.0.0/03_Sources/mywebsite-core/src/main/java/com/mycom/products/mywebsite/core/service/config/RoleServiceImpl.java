package com.mycom.products.mywebsite.core.service.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.dao.config.RoleDao;
import com.mycom.products.mywebsite.core.dao.config.UserRoleDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.security.AuthorityChangeEvent;

@Service
public class RoleServiceImpl implements RoleService, ApplicationEventPublisherAware {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	private ApplicationEventPublisher publisher;
	@Autowired
	private RoleActionDao roleActionDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int add(RoleBean role, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int lastInsertedId = 0;
		try {
			lastInsertedId = roleDao.insert(role, loginUserId);
			coreLogger.debug("Insertion for 'Role' information process has finished.");
			if (lastInsertedId > 0 && role.getActionIds() != null && role.getActionIds().size() > 0) {
				coreLogger.debug("Transaction start for insertion related users of 'Role' Id = " + lastInsertedId);
				for (int actionId : role.getActionIds()) {
					ActionBean action = new ActionBean();
					action.setId(actionId);
					RoleActionBean roleAction = new RoleActionBean(role, action);
					roleActionDao.insert(roleAction, loginUserId);
				}
			}
			if (lastInsertedId > 0 && role.getUserIds() != null && role.getUserIds().size() > 0) {
				for (int userId : role.getUserIds()) {
					UserBean user = new UserBean();
					user.setId(userId);
					UserRoleBean userRole = new UserRoleBean(user, role);
					userRoleDao.insert(userRole, loginUserId);
				}
			}
			coreLogger.debug("Transaction finished for insertion related users of 'Role' Id = " + lastInsertedId);
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'Role' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'Role' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "INSERT"));
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int modify(RoleBean role,
			int loginUserId) throws BusinessException, ConsistencyViolationException, DuplicatedEntryException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'Role' information with the values" + role);
		try {
			effectedRows = roleDao.update(role, loginUserId);
			coreLogger.debug("Updating for 'Role' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
			coreLogger.debug("Transaction start for changing related Actions of 'Role' Id = " + role.getId());
			// delete old actions first
			Map<String, Object> criteria = new HashMap<>();
			// delete by roleId
			criteria.put("roleId", role.getId());
			criteria.put("recordUpdId", loginUserId);
			roleActionDao.delete(criteria, loginUserId);
			coreLogger.debug("Deleting old actions for 'Role' Id = " + role.getId() + " has been finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
			// re-add all action
			for (int actionId : role.getActionIds()) {
				ActionBean action = new ActionBean();
				action.setId(actionId);
				RoleActionBean roleAction = new RoleActionBean(role, action);
				roleActionDao.insert(roleAction, loginUserId);
			}
			coreLogger.debug("Re-insert new actions for 'Role' Id = " + role.getId() + " has been finished.");
			coreLogger.debug("Transaction start for changing related Users of 'Role' Id = " + role.getId());
			// delete old users first
			criteria.clear();
			// delete by roleId
			criteria.put("roleId", role.getId());
			criteria.put("recordUpdId", loginUserId);
			userRoleDao.delete(criteria, loginUserId);
			coreLogger.debug("Deleting old Users for 'Role' Id = " + role.getId() + " has been finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
			// re-add all users
			for (int userId : role.getUserIds()) {
				UserBean user = new UserBean();
				user.setId(userId);
				UserRoleBean userRole = new UserRoleBean(user, role);
				userRoleDao.insert(userRole, loginUserId);
			}
			coreLogger.debug("Re-insert new Users for 'Role' Id = " + role.getId() + " has been finished.");

		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'Role' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'Role' with Id = " + role.getId());
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "MODIFY"));
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
		coreLogger.info("Transaction start for removing existing 'Role' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = roleDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'Role' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'Role' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "REMOVE"));
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Role", key = "#id")
	public RoleBean getById(int id) throws BusinessException {
		RoleBean result = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'Role' information by Id = " + id);
		try {
			result = roleDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Role' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Role' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Role", key = "#criteria.toString()", condition = "#criteria != null")
	public List<RoleBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		List<RoleBean> results = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'Role' informations with criteria = " + criteria);
		try {
			results = roleDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Role' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Role' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Role", key = "#criteria.toString().concat('_count')", condition = "#criteria != null")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		int count = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'Role' counts with criteria = " + criteria);
		try {
			count = roleDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Role' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Role' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

}
