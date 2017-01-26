package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.XGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.UserRoleMapper;

@Repository
public class UserRoleDao implements XGenericDao<UserRoleBean> {

	@Autowired
	private UserRoleMapper userRoleMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(UserRoleBean userRole, int recordRegId) throws DuplicatedEntryException, MyBatisException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			userRole.setRecordRegDate(now);
			userRole.setRecordUpdDate(now);
			userRole.setRecordRegId(recordRegId);
			userRole.setRecordUpdId(recordRegId);
			userRoleMapper.insert(userRole);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'UserRole' data ==> " + userRole;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return userRole.getId();
	}

	@Override
	public void insert(List<UserRoleBean> userRoles,
			int recordRegId) throws DuplicatedEntryException, MyBatisException {
		for (UserRoleBean userRole : userRoles) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				userRole.setRecordRegDate(now);
				userRole.setRecordUpdDate(now);
				userRole.setRecordRegId(recordRegId);
				userRole.setRecordUpdId(recordRegId);
				userRoleMapper.insert(userRole);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'UserRole' data ==> " + userRole;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}

	}

	@Override
	public int insert(int userId, int roleId, int recordRegId) throws DuplicatedEntryException, MyBatisException {
		try {
			return userRoleMapper.insert(userId, roleId, recordRegId);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'UserRole' data ==> userId = " + userId + " , roleId = " + roleId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(int userId, int roleId,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException {
		try {
			return userRoleMapper.delete(userId, roleId);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'UserRole' data with ==> userId = " + userId + " , roleId = " + roleId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException {
		try {
			return userRoleMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'User' data with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public void merge(int userId, List<Integer> roleIds,
			int recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, MyBatisException {
		List<Integer> insertIds = new ArrayList<>();
		List<Integer> removeIds = new ArrayList<>();
		List<Integer> oldRelatedActions = select(userId);
		if (oldRelatedActions != null && oldRelatedActions.size() > 0) {
			for (Integer roleId : roleIds) {
				if (!oldRelatedActions.contains(roleId)) {
					insertIds.add(roleId);
				}
			}

			for (Integer roleId : oldRelatedActions) {
				if (!roleIds.contains(roleId)) {
					removeIds.add(roleId);
				}
			}
		}
		if (removeIds.size() > 0) {
			for (Integer roleId : removeIds) {
				userRoleMapper.delete(userId, roleId);
			}
		}

		if (insertIds.size() > 0) {
			for (Integer roleId : insertIds) {
				userRoleMapper.insert(userId, roleId, recordUpdId);
			}
		}

	}

	@Override
	public List<Integer> select(int userId) throws MyBatisException {
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("userId", userId);
			return userRoleMapper.select(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting related 'Action' keys with userId ==> " + userId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public UserRoleBean select(int userId, int roleId) throws MyBatisException {
		try {
			return userRoleMapper.select(userId, roleId);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'UserRole' information with ==> userId = " + userId + " , roleId = " + roleId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<UserRoleBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userRoleMapper.selectList(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'UserRole' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userRoleMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'UserRole' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
