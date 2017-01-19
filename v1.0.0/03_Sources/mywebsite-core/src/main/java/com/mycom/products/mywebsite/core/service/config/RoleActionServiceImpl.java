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
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.security.AuthorityChangeEvent;

@Service
public class RoleActionServiceImpl implements RoleActionService, ApplicationEventPublisherAware {

	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	private ApplicationEventPublisher publisher;
	@Autowired
	private RoleActionDao roleActionDao;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int add(RoleActionBean roleAction, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int lastInsertedId = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for insert new 'RoleAction' information with the values" + roleAction);
		try {
			lastInsertedId = roleActionDao.insert(roleAction, loginUserId);
			coreLogger.debug("Insertion for 'RoleAction' information process has finished.");
		} catch (MyBatisException e) {
			errorLogger.error("Insertion process for new 'RoleAction' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for insert new 'RoleAction' with Id = " + lastInsertedId);
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "ADD"));
		return lastInsertedId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.User", "ApplicationCache.Role", "ApplicationCache.Action", "ApplicationCache.UserRole", "ApplicationCache.RoleAction" }, allEntries = true)
	public int modify(RoleActionBean roleAction, int loginUserId) throws BusinessException, DuplicatedEntryException {
		int effectedRows = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update existing 'RoleAction' information with the values" + roleAction);
		try {
			effectedRows = roleActionDao.update(roleAction, loginUserId);
			coreLogger.debug("Updating for 'RoleAction' information process has finished.");
			coreLogger.debug("Total effected rows = " + effectedRows);
		} catch (MyBatisException e) {
			errorLogger.error("Updating for existing 'RoleAction' was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for update 'RoleAction' with Id = " + roleAction.getId());
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
		coreLogger.info("Transaction start for removing existing 'RoleAction' information with criteria = " + criteria);
		try {
			criteria.put("recordUpdId", loginUserId);
			effectedRows = roleActionDao.delete(criteria, loginUserId);
		} catch (MyBatisException e) {
			errorLogger.error("Removing for 'RoleAction' process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for removing existing 'RoleAction' information.");
		coreLogger.debug("Total effected rows = " + effectedRows);
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "REMOVE"));
		return effectedRows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.RoleAction", key = "#id")
	public RoleActionBean getById(int id) throws BusinessException {
		RoleActionBean result = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching  'RoleAction' information by Id = " + id);
		try {
			result = roleActionDao.selectById(id);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'RoleAction' information by Id = " + id + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'RoleAction' information by Id = " + id);
		coreLogger.info(BaseBean.LOGBREAKER);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.RoleAction", key = "#criteria.toString()")
	public List<RoleActionBean> getByCriteria(Map<String, Object> criteria) throws BusinessException {
		List<RoleActionBean> results = null;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'RoleAction' informations with criteria = " + criteria);
		try {
			results = roleActionDao.selectByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'RoleAction' information by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'RoleAction' informations by criteria.");
		coreLogger.info(BaseBean.LOGBREAKER);
		return results;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Cacheable(value = "ApplicationCache.RoleAction", key = "#criteria.toString().concat('_count')")
	public int getCountByCriteria(Map<String, Object> criteria) throws BusinessException {
		int count = 0;
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("Transaction start for fetching 'RoleAction' counts with criteria = " + criteria);
		try {
			count = roleActionDao.selectCountByCriteria(criteria);
		} catch (MyBatisException e) {
			errorLogger.error("Fetching 'RoleAction' counts by criteria = " + criteria + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for fetching 'RoleAction' counts by criteria.");
		coreLogger.info("Total records count = " + count);
		coreLogger.info(BaseBean.LOGBREAKER);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@CacheEvict(value = { "ApplicationCache.RoleAction", "ApplicationCache.Role", "ApplicationCache.Action" }, allEntries = true)
	public void changeRoleAcess(int roleId, String accessQuery,
			int loginUserId) throws BusinessException, DuplicatedEntryException, ConsistencyViolationException {
		coreLogger.info(BaseBean.LOGBREAKER);
		coreLogger.info("*** This transaction was initiated by User Id = " + loginUserId + " ***");
		coreLogger.info("Transaction start for update 'Actions' to 'Role' with the accessments ==> " + accessQuery);
		try {
			String[] actions = accessQuery.split(",");
			for (String actionStr : actions) {
				String actionId = actionStr.split("=")[0];
				boolean isGranted = Boolean.parseBoolean(actionStr.split("=")[1]);
				Map<String, Object> criteria = new HashMap<>();
				criteria.put("roleId", roleId);
				criteria.put("actionId", Integer.parseInt(actionId));
				List<RoleActionBean> roleActions = roleActionDao.selectByCriteria(criteria);
				if (isGranted) {
					coreLogger.debug("'Role' Id = " + roleId + " has granted for 'Action' Id = " + actionId);
					if (roleActions == null || roleActions.size() == 0) {
						RoleActionBean roleAction = new RoleActionBean();
						ActionBean action = new ActionBean();
						action.setId(Integer.parseInt(actionId));
						RoleBean role = new RoleBean();
						role.setId(roleId);
						roleAction.setAction(action);
						roleAction.setRole(role);
						roleActionDao.insert(roleAction, loginUserId);
						coreLogger.info("Inserting new authority for 'Role' Id = " + roleId + " with new 'Action' Id = " + actionId + " has successfully finished.");
					}
				} else if (roleActions != null && roleActions.size() > 0) {
					coreLogger.debug("'Role' Id = " + roleId + " has removed from owning 'Action' Id = " + actionId);
					criteria.clear();
					criteria.put("id", roleActions.get(0).getId());
					criteria.put("recordUpdId", loginUserId);
					roleActionDao.delete(criteria, loginUserId);
					coreLogger.info("Removing an authority for 'Role' Id = " + roleId + " with old 'Action' Id = " + actionId + " has successfully finished.");
				}
			}

		} catch (MyBatisException e) {
			errorLogger.error("Changing 'RoleAccessments' for 'Role' Id = " + roleId + "with accessments " + accessQuery + "process was failed ! See the error logs for more detail.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
			throw new BusinessException(e.getMessage(), e);
		}
		coreLogger.info("Transaction finished successfully for updating 'Actions' to 'Role' process.");
		coreLogger.info(BaseBean.LOGBREAKER);
		publisher.publishEvent(new AuthorityChangeEvent(this, "MODIFY"));
	}
}
