/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
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
import com.mycom.products.mywebsite.core.dao.base.XGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.mapper.config.UserRoleMapper;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Repository
public class UserRoleDao implements XGenericDao<UserRoleBean> {

	@Autowired
	private UserRoleMapper userRoleMapper;
	private Logger daoLogger = Logger.getLogger("daoLogger");

	@Override
	public void insert(UserRoleBean userRole, long recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'UserRole' informations ---");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			userRole.setRecordRegDate(now);
			userRole.setRecordUpdDate(now);
			userRole.setRecordRegId(recordRegId);
			userRole.setRecordUpdId(recordRegId);
			userRoleMapper.insert(userRole);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'UserRole' data ==> " + userRole + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'UserRole' informations with new Id # " + userRole.getId() + " ---");
	}

	@Override
	public void insert(List<UserRoleBean> userRoles,
			long recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'UserRole' informations ---");
		for (UserRoleBean userRole : userRoles) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				userRole.setRecordRegDate(now);
				userRole.setRecordUpdDate(now);
				userRole.setRecordRegId(recordRegId);
				userRole.setRecordUpdId(recordRegId);
				userRoleMapper.insert(userRole);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while inserting 'UserRole' data ==> " + userRole + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'UserRole' informations ---");
	}

	@Override
	public void insert(long userId, long roleId, long recordRegId) throws DuplicatedEntryException, DAOException {
		daoLogger.debug("[START] : >>> --- Inserting single 'UserRole' informations ---");
		try {
			userRoleMapper.insert(userId, roleId, recordRegId);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'UserRole' data ==> userId = " + userId + " , roleId = " + roleId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'UserRole' informations ---");
	}

	@Override
	public long delete(long userId, long roleId,
			long recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting single 'UserRole' informations with ==> userId " + userId + " , roleId = " + roleId + " ---");
		long effectedRows = 0;
		try {
			effectedRows = userRoleMapper.deleteByKeys(userId, roleId);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'UserRole' data with ==> userId = " + userId + " , roleId = " + roleId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'UserRole' informations with ==> userId " + userId + " , roleId = " + roleId + " ---");
		return effectedRows;
	}

	@Override
	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting 'UserRole' informations with criteria  ---");
		long effectedRows = 0;
		try {
			effectedRows = userRoleMapper.deleteByCriteria(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'UserRole' data with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'UserRole' informations with criteria  ---");
		return effectedRows;
	}

	@Override
	public void merge(long userId, List<Integer> roleIds,
			long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Merging  'UserRole' informations for userId # =" + userId + " with related roleIds =" + roleIds + " ---");
		List<Integer> insertIds = new ArrayList<>();
		List<Integer> removeIds = new ArrayList<>();
		daoLogger.debug("[START] : $1 --- Fetching old related roleIds for userId # " + userId + " ---");
		List<Integer> oldRelatedActions = selectByKey1(userId);
		daoLogger.debug("[FINISH] : $1 --- Fetching old related roleIds for userId # " + userId + " ==> " + oldRelatedActions + " ---");
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
		daoLogger.debug("[FINISH] : $2 --- Removing  related roleIds[ " + removeIds + " ] for userId # " + userId + " these have been no longer used  ---");
		if (removeIds.size() > 0) {
			HashMap<String, Object> criteria = new HashMap<>();
			criteria.put("roleIds", removeIds);
			userRoleMapper.deleteByCriteria(criteria);
		}
		daoLogger.debug("[FINISH] : $2 --- Removing  related roleIds[ " + removeIds + " ] for userId # " + userId + " these have been no longer used  ---");

		daoLogger.debug("[START] : $3 --- Inserting newly selected roleIds[ " + insertIds + " ] for userId # " + userId + " ---");
		if (insertIds.size() > 0) {
			for (Integer roleId : insertIds) {
				userRoleMapper.insert(userId, roleId, recordUpdId);
			}
		}
		daoLogger.debug("[FINISH] : $3 --- Inserting newly selected roleIds[ " + insertIds + " ] for userId # " + userId + " ---");

		daoLogger.debug("[FINISH] : <<< --- Merging 'UserRole' informations for userId # =" + userId + " with related roleIds =" + roleIds.toArray() + " ---");

	}

	@Override
	public List<Integer> selectByKey1(long key1) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related roleIds with userId # " + key1 + " ---");
		List<Integer> roleIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("userId", key1);
			roleIds = userRoleMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Action' keys with userId ==> " + key1 + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related roleIds with userId # " + key1 + " ---");
		return roleIds;
	}

	@Override
	public List<Integer> selectByKey2(long key2) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related userIds with roleId # " + key2 + " ---");
		List<Integer> userIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("roleId", key2);
			userIds = userRoleMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Role' keys with roleId ==> " + key2 + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related userIds with roleId # " + key2 + " ---");
		return userIds;
	}

	@Override
	public UserRoleBean select(long userId, long roleId, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'UserRole' informations with ==> userId = " + userId + " , roleId = " + roleId + " ---");
		UserRoleBean userRole = null;
		try {
			// Noticed : we don't allow for filtering from joined
			// tables.FetchMode is just only for eager or lazy loading
			userRole = userRoleMapper.selectByKeys(userId, roleId, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'UserRole' informations with ==> userId = " + userId + " , roleId = " + roleId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'UserRole' informations with ==> userId = " + userId + " , roleId = " + roleId + " ---");
		return userRole;
	}

	@Override
	public List<UserRoleBean> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'UserRole' informations with criteria ---");
		List<UserRoleBean> results = null;
		try {
			// Noticed : we don't allow for filtering from joined
			// tables.FetchMode is just only for eager or lazy loading
			results = userRoleMapper.selectMultiRecords(criteria, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'UserRole' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'UserRole' informations with criteria ---");
		return results;
	}

	@Override
	public long selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'UserRole' counts with criteria ---");
		long count = 0;
		try {
			// we don't allow for filtering from joined tables
			count = userRoleMapper.selectCounts(criteria, null);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'UserRole' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'UserRole' counts with criteria ---");
		return count;
	}
}
