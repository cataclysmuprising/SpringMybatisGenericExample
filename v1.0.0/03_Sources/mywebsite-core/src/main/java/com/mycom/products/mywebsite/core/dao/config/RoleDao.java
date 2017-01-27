/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.SaveHistoryFailedException;
import com.mycom.products.mywebsite.core.mapper.config.RoleMapper;

@Repository
public class RoleDao implements CommonGenericDao<RoleBean> {

	@Autowired
	private RoleMapper roleMapper;

	private Logger daoLogger = Logger.getLogger(this.getClass());

	@Override
	public int insert(RoleBean role, int recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'Role' informations ---");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			role.setRecordRegDate(now);
			role.setRecordUpdDate(now);
			role.setRecordRegId(recordRegId);
			role.setRecordUpdId(recordRegId);
			role.setTransactionType(TransactionType.INSERT);
			roleMapper.insert(role);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history after successfully inserted in major table ---");
			roleMapper.saveHistory(role);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'Role' data ==> " + role + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'Role' informations with new Id # " + role.getId() + " ---");
		return role.getId();
	}

	@Override
	public void insert(List<RoleBean> roles, int recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'Role' informations ---");
		for (RoleBean role : roles) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				role.setRecordRegDate(now);
				role.setRecordUpdDate(now);
				role.setRecordRegId(recordRegId);
				role.setRecordUpdId(recordRegId);
				role.setTransactionType(TransactionType.INSERT);
				roleMapper.insert(role);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history after successfully inserted in major table ---");
				roleMapper.saveHistory(role);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while inserting 'Role' data ==> " + role + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'Role' informations ---");
	}

	@Override
	public int update(RoleBean role, int recordUpdId) throws DAOException, DuplicatedEntryException {
		int totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Updating single 'Role' informations ---");
		try {
			role.setRecordUpdId(recordUpdId);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history before update on major table ---");
			RoleBean oldData = roleMapper.selectByPrimaryKey(role.getId());
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			roleMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
			totalEffectedRows = roleMapper.update(role);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while updating 'Role' data ==> " + role + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Updating single 'Role' informations with Id # " + role.getId() + " ---");
		return totalEffectedRows;
	}

	@Override
	public void update(List<RoleBean> roles, int recordUpdId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Updating multi 'Role' informations ---");
		for (RoleBean role : roles) {
			try {
				role.setRecordUpdId(recordUpdId);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history before update on major table ---");
				RoleBean oldData = roleMapper.selectByPrimaryKey(role.getId());
				if (oldData == null) {
					throw new SaveHistoryFailedException();
				}
				oldData.setTransactionType(TransactionType.UPDATE);
				oldData.setRecordUpdId(recordUpdId);
				roleMapper.saveHistory(oldData);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
				roleMapper.update(role);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while updating 'Role' data ==> " + role + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Updating multi 'Role' informations ---");
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws DAOException, ConsistencyViolationException {
		daoLogger.debug("[START] : >>> --- Deleting single 'Role' informations with primaryKey # " + primaryKey + " ---");
		int totalEffectedRows = 0;
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history before update on major table ---");
			RoleBean oldData = roleMapper.selectByPrimaryKey(primaryKey);
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			roleMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
			totalEffectedRows = roleMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'Role' data with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'Role' informations with primaryKey # " + primaryKey + " ---");
		return totalEffectedRows;
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws DAOException, ConsistencyViolationException {
		int totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Deleting 'Role' informations with criteria  ---");
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Role' informations in history before delete on major table ---");
			List<RoleBean> roles = roleMapper.selectMultiRecords(criteria);
			if (roles == null) {
				throw new SaveHistoryFailedException();
			}
			if (roles != null && roles.size() > 0) {
				for (RoleBean role : roles) {
					role.setTransactionType(TransactionType.DELETE);
					role.setRecordUpdId(recordUpdId);
					roleMapper.saveHistory(role);
				}
			}
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Role' informations in history ---");
			totalEffectedRows = roleMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Role' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'Role' data with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'Role' informations with criteria  ---");
		return totalEffectedRows;
	}

	@Override
	public RoleBean select(int primaryKey) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Role' informations with primaryKey # " + primaryKey + " ---");
		RoleBean role = new RoleBean();
		try {
			role = roleMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Role' informations with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Role' informations with primaryKey # " + primaryKey + " ---");
		return role;
	}

	@Override
	public RoleBean select(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'Role' informations with criteria ---");
		RoleBean role = new RoleBean();
		try {
			role = roleMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Role' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'Role' informations with criteria ---");
		return role;
	}

	@Override
	public List<RoleBean> selectList(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'Role' informations with criteria ---");
		List<RoleBean> roles = null;
		try {
			roles = roleMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'Role' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'Role' informations with criteria ---");
		return roles;
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Role' counts with criteria ---");
		int count = 0;
		try {
			count = roleMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'Role' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Role' counts with criteria ---");
		return count;
	}
}
