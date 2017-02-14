/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.api.XGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.mapper.config.RoleActionMapper;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Repository
public class RoleActionDao implements XGenericDao<RoleActionBean> {

	@Autowired
	private RoleActionMapper roleActionMapper;
	private Logger daoLogger = Logger.getLogger("daoLogger");

	@Override
	public void insert(RoleActionBean roleAction, long recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'RoleAction' informations ---");
			LocalDateTime now = LocalDateTime.now();
			roleAction.setRecordRegDate(now);
			roleAction.setRecordUpdDate(now);
			roleAction.setRecordRegId(recordRegId);
			roleAction.setRecordUpdId(recordRegId);
			roleActionMapper.insert(roleAction);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'RoleAction' data ==> " + roleAction + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'RoleAction' informations ---");
	}

	@Override
	public void insert(List<RoleActionBean> roleActions,
			long recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'RoleAction' informations ---");
		LocalDateTime now = LocalDateTime.now();
		for (RoleActionBean roleAction : roleActions) {
			roleAction.setRecordRegDate(now);
			roleAction.setRecordUpdDate(now);
			roleAction.setRecordRegId(recordRegId);
			roleAction.setRecordUpdId(recordRegId);
		}
		try {
			roleActionMapper.insertList(roleActions);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'RoleAction' datas ==> " + roleActions + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'RoleAction' informations ---");
	}

	@Override
	public void insert(long roleId, long actionId, long recordRegId) throws DuplicatedEntryException, DAOException {
		daoLogger.debug("[START] : >>> --- Inserting single 'RoleAction' informations ---");
		try {
			roleActionMapper.insertWithRelatedKeys(roleId, actionId, recordRegId);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'RoleAction' data ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'RoleAction' informations ---");
	}

	@Override
	public long delete(long roleId, long actionId,
			long recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting single 'RoleAction' informations with ==> roleId " + roleId + " , actionId = " + actionId + " ---");
		long effectedRows = 0;
		try {
			effectedRows = roleActionMapper.deleteByKeys(roleId, actionId);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'RoleAction' data with ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'RoleAction' informations with ==> roleId " + roleId + " , actionId = " + actionId + " ---");
		return effectedRows;
	}

	@Override
	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting 'RoleAction' informations with criteria  ---");
		long effectedRows = 0;
		try {
			effectedRows = roleActionMapper.deleteByCriteria(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'RoleAction' data with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'RoleAction' informations with criteria  ---");
		return effectedRows;
	}

	@Override
	public void merge(long roleId, List<Long> actionIds,
			long recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Merging  'RoleAction' informations for roleId # =" + roleId + " with related actionIds =" + actionIds + " ---");
		List<Long> insertIds = new ArrayList<>();
		List<Long> removeIds = new ArrayList<>();
		daoLogger.debug("[START] : $1 --- Fetching old related actionIds for roleId # " + roleId + " ---");
		List<Long> oldRelatedActions = selectByKey1(roleId);
		daoLogger.debug("[FINISH] : $1 --- Fetching old related actionIds for roleId # " + roleId + " ==> " + oldRelatedActions + " ---");
		if (oldRelatedActions != null && oldRelatedActions.size() > 0) {
			for (long actionId : actionIds) {
				if (!oldRelatedActions.contains(actionId)) {
					insertIds.add(actionId);
				}
			}

			for (long actionId : oldRelatedActions) {
				if (!actionIds.contains(actionId)) {
					removeIds.add(actionId);
				}
			}
		}
		if (removeIds.size() > 0) {
			daoLogger.debug("[START] : $2 --- Removing  related actionIds " + removeIds + " for roleId # " + roleId + " these have been no longer used  ---");
			HashMap<String, Object> criteria = new HashMap<>();
			criteria.put("actionIds", removeIds);
			roleActionMapper.deleteByCriteria(criteria);
			daoLogger.debug("[FINISH] : $2 --- Removing  related actionIds " + removeIds + " for roleId # " + roleId + " these have been no longer used  ---");
		}

		if (insertIds.size() > 0) {
			daoLogger.debug("[START] : $3 --- Inserting newly selected actionIds " + insertIds + " for roleId # " + roleId + " ---");
			List<RoleActionBean> roleActions = new ArrayList<>();
			for (Long actionId : insertIds) {
				RoleActionBean roleAction = new RoleActionBean(roleId, actionId);
				roleActions.add(roleAction);
			}
			LocalDateTime now = LocalDateTime.now();
			for (RoleActionBean roleAction : roleActions) {
				roleAction.setRecordRegDate(now);
				roleAction.setRecordUpdDate(now);
				roleAction.setRecordRegId(recordUpdId);
				roleAction.setRecordUpdId(recordUpdId);
			}
			roleActionMapper.insertList(roleActions);
			daoLogger.debug("[FINISH] : $3 --- Inserting newly selected actionIds " + insertIds + " for roleId # " + roleId + " ---");
		}

		daoLogger.debug("[FINISH] : <<< --- Merging 'RoleAction' informations for roleId # =" + roleId + " with related actionIds =" + actionIds.toArray() + " ---");

	}

	@Override
	public List<Long> selectByKey1(long key1) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related actionIds with roleId # " + key1 + " ---");
		List<Long> actionIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("roleId", key1);
			actionIds = roleActionMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Action' keys with roleId ==> " + key1 + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related actionIds with roleId # " + key1 + " ---");
		return actionIds;
	}

	@Override
	public List<Long> selectByKey2(long key2) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related roleIds with actionId # " + key2 + " ---");
		List<Long> roleIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("actionId", key2);
			roleIds = roleActionMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Role' keys with actionId ==> " + key2 + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related roleIds with actionId # " + key2 + " ---");
		return roleIds;
	}

	@Override
	public RoleActionBean select(long roleId, long actionId, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " ---");
		RoleActionBean roleAction = null;
		try {
			// Noticed : we don't allow for filtering from joined
			// tables.FetchMode is just only for eager or lazy loading
			roleAction = roleActionMapper.selectByKeys(roleId, actionId, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " ---");
		return roleAction;
	}

	@Override
	public List<RoleActionBean> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'RoleAction' informations with criteria ---");
		List<RoleActionBean> results = null;
		try {
			// Noticed : we don't allow for filtering from joined
			// tables.FetchMode is just only for eager or lazy loading
			results = roleActionMapper.selectMultiRecords(criteria, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'RoleAction' informations with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'RoleAction' informations with criteria ---");
		return results;
	}

	@Override
	public long selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'RoleAction' counts with criteria ---");
		long count = 0;
		try {
			// we don't allow for filtering from joined tables
			count = roleActionMapper.selectCounts(criteria, null);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'RoleAction' records with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'RoleAction' counts with criteria ---");
		return count;
	}
}
