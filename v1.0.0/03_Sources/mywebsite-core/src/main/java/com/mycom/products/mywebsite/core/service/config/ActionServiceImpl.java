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
import com.mycom.products.mywebsite.core.dao.config.ActionDao;
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.security.AuthorityChangeEvent;

@Service
public class ActionServiceImpl implements ActionService, ApplicationEventPublisherAware {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	private ApplicationEventPublisher publisher;

	@Autowired
	private ActionDao actionDao;

	@Autowired
	private RoleActionDao roleActionDao;

	public enum SelectType {
		ALL("all"), UNIQUE("unique");
		private String value;

		SelectType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int add(ActionBean action, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int lastInsertedId = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for insert new 'Action' information with the values" + action);
		try {
			lastInsertedId = actionDao.insert(action, loginUserId);
			coreLogger.debug("Insertion for 'Action' information process has finished.");
			if (lastInsertedId > 0 && action.getRoleIds() != null && action.getRoleIds().size() > 0) {
				coreLogger.debug("Transaction start for insertion related roles of 'Action' Id = " + lastInsertedId);
				for (int roleId : action.getRoleIds()) {
					RoleBean role = new RoleBean();
					role.setId(roleId);
					RoleActionBean roleAction = new RoleActionBean(role, action);
					roleActionDao.insert(roleAction, loginUserId);
				}
				coreLogger.debug("Transaction finished for insertion related roles of 'Action' Id = " + lastInsertedId);
				coreLogger.info(BaseBean.LOGBREAKER);
				publisher.publishEvent(new AuthorityChangeEvent(this, "ADD"));
			}
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'Action' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'Action' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int modify(ActionBean action,
			int loginUserId) throws BusinessException, DuplicatedEntryException, ConsistencyViolationException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'Action' information with the values" + action);
		try {
			effectedRows = actionDao.update(action, loginUserId);
			coreLogger.debug("Updating for 'Action' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
			if (effectedRows > 0 && action.getRoleIds() != null && action.getRoleIds().size() > 0) {
				coreLogger.debug("Transaction start for changing related roles of 'Action' Id = " + action.getId());
				// delete old roles first
				Map<String, Object> criteria = new HashMap<>();
				// delete by actionId
				criteria.put("actionId", action.getId());
				criteria.put("recordUpdId", loginUserId);
				effectedRows = roleActionDao.delete(criteria, loginUserId);
				coreLogger.debug("Deleting old roles for 'Action' Id = " + action.getId() + " has been finished.");
				coreLogger.debug("Total effected rows = " + effectedRows);
				for (int roleId : action.getRoleIds()) {
					RoleBean role = new RoleBean();
					role.setId(roleId);
					RoleActionBean roleAction = new RoleActionBean(role, action);
					roleActionDao.insert(roleAction, loginUserId);
				}
				coreLogger.debug("Re-insert new roles for 'Action' Id = " + action.getId() + " has been finished.");
				publisher.publishEvent(new AuthorityChangeEvent(this, "MODIFY"));
			}
		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'Action' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'Action' with Id = " + action.getId());
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
		coreLogger.info("Transaction start for removing existing 'Action' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = actionDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'Action' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'Action' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "REMOVE"));
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Action", key = "#id")
	public ActionBean getById(int id) throws BusinessException {
		ActionBean result = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'Action' information by Id = " + id);
		try {
			result = actionDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Action' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Action' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Cacheable(value = "ApplicationCache.Action", key = "#criteria.toString()")
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<ActionBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		List<ActionBean> actions = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'Action' informations with criteria = " + criteria);
		try {
			actions = actionDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Action' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Action' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return actions;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Action", key = "#criteria.toString().concat('_count')")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'Action' counts with criteria = " + criteria);
		int count = 0;
		try {
			count = actionDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'Action' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'Action' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.Action", key = "#root.method.name")
	public List<String> getAllPageNames() throws BusinessException {
		List<String> results = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching all 'pageNames' process.");
		try {
			results = actionDao.selectAllPageNames();
		} catch (MyBatisException e) {
			errorLogger.error("Fetching all 'pageNames' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'pageNames' process.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return results;
	}
}
