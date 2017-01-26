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
import com.mycom.products.mywebsite.core.dao.XGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.RoleActionMapper;

@Repository
public class RoleActionDao implements XGenericDao<RoleActionBean> {

	@Autowired
	private RoleActionMapper roleActionMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(RoleActionBean roleAction, int recordRegId) throws DuplicatedEntryException, MyBatisException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			roleAction.setRecordRegDate(now);
			roleAction.setRecordUpdDate(now);
			roleAction.setRecordRegId(recordRegId);
			roleAction.setRecordUpdId(recordRegId);
			roleActionMapper.insert(roleAction);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'RoleAction' data ==> " + roleAction;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return roleAction.getId();
	}

	@Override
	public void insert(List<RoleActionBean> roleActions,
			int recordRegId) throws DuplicatedEntryException, MyBatisException {
		for (RoleActionBean roleAction : roleActions) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				roleAction.setRecordRegDate(now);
				roleAction.setRecordUpdDate(now);
				roleAction.setRecordRegId(recordRegId);
				roleAction.setRecordUpdId(recordRegId);
				roleActionMapper.insert(roleAction);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'RoleAction' data ==> " + roleAction;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}

	}

	@Override
	public int insert(int roleId, int actionId, int recordRegId) throws DuplicatedEntryException, MyBatisException {
		try {
			return roleActionMapper.insert(roleId, actionId, recordRegId);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'RoleAction' data ==> roleId = " + roleId + " , actionId = " + actionId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(int roleId, int actionId,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException {
		try {
			return roleActionMapper.delete(roleId, actionId);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'RoleAction' data with ==> roleId = " + roleId + " , actionId = " + actionId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, MyBatisException {
		try {
			return roleActionMapper.delete(criteria);
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
	public void merge(int roleId, List<Integer> actionIds,
			int recordUpdId) throws DuplicatedEntryException, ConsistencyViolationException, MyBatisException {
		List<Integer> insertIds = new ArrayList<>();
		List<Integer> removeIds = new ArrayList<>();
		List<Integer> oldRelatedActions = select(roleId);
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
		if (removeIds.size() > 0) {
			for (Integer actionId : removeIds) {
				roleActionMapper.delete(roleId, actionId);
			}
		}

		if (insertIds.size() > 0) {
			for (Integer actionId : insertIds) {
				roleActionMapper.insert(roleId, actionId, recordUpdId);
			}
		}

	}

	@Override
	public List<Integer> select(int roleId) throws MyBatisException {
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("roleId", roleId);
			return roleActionMapper.select(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting related 'Action' keys with roleId ==> " + roleId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public RoleActionBean select(int roleId, int actionId) throws MyBatisException {
		try {
			return roleActionMapper.select(roleId, actionId);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'RoleAction' information with ==> roleId = " + roleId + " , actionId = " + actionId;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<RoleActionBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleActionMapper.selectList(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'RoleAction' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleActionMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'RoleAction' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
