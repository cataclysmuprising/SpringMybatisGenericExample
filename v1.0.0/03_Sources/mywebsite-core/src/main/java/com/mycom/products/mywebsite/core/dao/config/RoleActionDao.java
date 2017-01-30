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

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.base.XGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.mapper.config.RoleActionMapper;

@Repository
public class RoleActionDao implements XGenericDao<RoleActionBean> {

	@Autowired
	private RoleActionMapper roleActionMapper;
	private Logger daoLogger = Logger.getLogger(this.getClass());

	@Override
	public int insert(RoleActionBean roleAction, int recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'RoleAction' informations ---");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			roleAction.setRecordRegDate(now);
			roleAction.setRecordUpdDate(now);
			roleAction.setRecordRegId(recordRegId);
			roleAction.setRecordUpdId(recordRegId);
			roleActionMapper.insert(roleAction);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'RoleAction' data ==> " + roleAction + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'RoleAction' informations with new Id # " + roleAction.getId() + " ---");
		return roleAction.getId();
	}

	@Override
	public void insert(List<RoleActionBean> roleActions,
			int recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'RoleAction' informations ---");
		for (RoleActionBean roleAction : roleActions) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				roleAction.setRecordRegDate(now);
				roleAction.setRecordUpdDate(now);
				roleAction.setRecordRegId(recordRegId);
				roleAction.setRecordUpdId(recordRegId);
				roleActionMapper.insert(roleAction);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while inserting 'RoleAction' data ==> " + roleAction + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'RoleAction' informations ---");
	}

	@Override
	public void insert(int roleId, int actionId, int recordRegId) throws DuplicatedEntryException, DAOException {
		daoLogger.debug("[START] : >>> --- Inserting single 'RoleAction' informations ---");
		try {
			roleActionMapper.insert(roleId, actionId, recordRegId);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'RoleAction' data ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'RoleAction' informations ---");
	}

	@Override
	public int delete(int roleId, int actionId,
			int recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting single 'RoleAction' informations with ==> roleId " + roleId + " , actionId = " + actionId + " ---");
		int effectedRows = 0;
		try {
			effectedRows = roleActionMapper.deleteByKeys(roleId, actionId);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'RoleAction' data with ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'RoleAction' informations with ==> roleId " + roleId + " , actionId = " + actionId + " ---");
		return effectedRows;
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Deleting 'RoleAction' informations with criteria  ---");
		int effectedRows = 0;
		try {
			effectedRows = roleActionMapper.deleteByCriteria(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'RoleAction' data with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'RoleAction' informations with criteria  ---");
		return effectedRows;
	}

	@Override
	public void merge(int roleId, List<Integer> actionIds,
			int recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, DAOException {
		daoLogger.debug("[START] : >>> --- Merging  'RoleAction' informations for roleId # =" + roleId + " with related actionIds =" + actionIds + " ---");
		List<Integer> insertIds = new ArrayList<>();
		List<Integer> removeIds = new ArrayList<>();
		daoLogger.debug("[START] : $1 --- Fetching old related actionIds for roleId # " + roleId + " ---");
		List<Integer> oldRelatedActions = selectByKey1(roleId);
		daoLogger.debug("[FINISH] : $1 --- Fetching old related actionIds for roleId # " + roleId + " ==> " + oldRelatedActions + " ---");
		if (oldRelatedActions != null && oldRelatedActions.size() > 0) {
			for (Integer actionId : actionIds) {
				if (!oldRelatedActions.contains(actionId)) {
					insertIds.add(actionId);
				}
			}

			for (Integer actionId : oldRelatedActions) {
				if (!actionIds.contains(actionId)) {
					removeIds.add(actionId);
				}
			}
		}
		daoLogger.debug("[FINISH] : $2 --- Removing  related actionIds[ " + removeIds + " ] for roleId # " + roleId + " these have been no longer used  ---");
		if (removeIds.size() > 0) {
			HashMap<String, Object> criteria = new HashMap<>();
			criteria.put("actionIds", removeIds);
			roleActionMapper.deleteByCriteria(criteria);
		}
		daoLogger.debug("[FINISH] : $2 --- Removing  related actionIds[ " + removeIds + " ] for roleId # " + roleId + " these have been no longer used  ---");

		daoLogger.debug("[START] : $3 --- Inserting newly selected actionIds[ " + insertIds + " ] for roleId # " + roleId + " ---");
		if (insertIds.size() > 0) {
			for (Integer actionId : insertIds) {
				roleActionMapper.insert(roleId, actionId, recordUpdId);
			}
		}
		daoLogger.debug("[FINISH] : $3 --- Inserting newly selected actionIds[ " + insertIds + " ] for roleId # " + roleId + " ---");

		daoLogger.debug("[FINISH] : <<< --- Merging 'RoleAction' informations for roleId # =" + roleId + " with related actionIds =" + actionIds.toArray() + " ---");

	}

	@Override
	public List<Integer> selectByKey1(int key1) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related actionIds with roleId # " + key1 + " ---");
		List<Integer> actionIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("roleId", key1);
			actionIds = roleActionMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Action' keys with roleId ==> " + key1 + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related actionIds with roleId # " + key1 + " ---");
		return actionIds;
	}

	@Override
	public List<Integer> selectByKey2(int key2) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching related roleIds with actionId # " + key2 + " ---");
		List<Integer> roleIds = null;
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("actionId", key2);
			roleIds = roleActionMapper.selectRelatedKeys(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching related 'Role' keys with actionId ==> " + key2 + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching related roleIds with actionId # " + key2 + " ---");
		return roleIds;
	}

	@Override
	public RoleActionBean select(int roleId, int actionId) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " ---");
		RoleActionBean roleAction = null;
		try {
			roleAction = roleActionMapper.selectByKeys(roleId, actionId);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'RoleAction' informations with ==> roleId = " + roleId + " , actionId = " + actionId + " ---");
		return roleAction;
	}

	@Override
	public List<RoleActionBean> selectList(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'RoleAction' informations with criteria ---");
		List<RoleActionBean> results = null;
		try {
			results = roleActionMapper.selectList(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'RoleAction' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'RoleAction' informations with criteria ---");
		return results;
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'RoleAction' counts with criteria ---");
		int count = 0;
		try {
			count = roleActionMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'RoleAction' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'RoleAction' counts with criteria ---");
		return count;
	}
}
