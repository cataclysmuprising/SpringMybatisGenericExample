/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * @See CommonGenericDao
 */
package com.mycom.products.springMybatisGenericExample.core.dao.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean.TransactionType;
import com.mycom.products.springMybatisGenericExample.core.bean.config.UserBean;
import com.mycom.products.springMybatisGenericExample.core.dao.api.CommonGenericDao;
import com.mycom.products.springMybatisGenericExample.core.dao.api.JoinedSelectableDao;
import com.mycom.products.springMybatisGenericExample.core.exception.ConsistencyViolationException;
import com.mycom.products.springMybatisGenericExample.core.exception.DAOException;
import com.mycom.products.springMybatisGenericExample.core.exception.DuplicatedEntryException;
import com.mycom.products.springMybatisGenericExample.core.exception.SaveHistoryFailedException;
import com.mycom.products.springMybatisGenericExample.core.mapper.config.UserMapper;
import com.mycom.products.springMybatisGenericExample.core.util.FetchMode;

@Repository
public class UserDao implements CommonGenericDao<UserBean>, JoinedSelectableDao<UserBean> {

	@Autowired
	private UserMapper userMapper;

	private Logger daoLogger = Logger.getLogger("daoLogger");

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public long insert(UserBean user, long recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'User' informations ---");
			LocalDateTime now = LocalDateTime.now();
			user.setRecordRegDate(now);
			user.setRecordUpdDate(now);
			user.setRecordRegId(recordRegId);
			user.setRecordUpdId(recordRegId);
			user.setTransactionType(TransactionType.INSERT);
			userMapper.insert(user);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history after successfully inserted in major table ---");
			userMapper.insertSingleHistoryRecord(user);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'User' data ==> " + user + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'User' informations with new Id # " + user.getId() + " ---");
		return user.getId();
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public void insert(List<UserBean> users, long recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'User' informations ---");
		LocalDateTime now = LocalDateTime.now();
		for (UserBean user : users) {
			user.setRecordRegDate(now);
			user.setRecordUpdDate(now);
			user.setRecordRegId(recordRegId);
			user.setRecordUpdId(recordRegId);
			user.setTransactionType(TransactionType.INSERT);
		}
		try {
			userMapper.insertList(users);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history after successfully inserted in major table ---");
			userMapper.insertMultiHistoryRecords(users);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting multi 'User' datas ==> " + users + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'User' informations ---");
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public long update(UserBean user, long recordUpdId) throws DAOException, DuplicatedEntryException {
		long totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Updating single 'User' informations ---");
		try {
			user.setRecordUpdId(recordUpdId);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history before update on major table ---");
			UserBean oldData = userMapper.selectByPrimaryKey(user.getId(), FetchMode.LAZY);
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			userMapper.insertSingleHistoryRecord(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
			totalEffectedRows = userMapper.update(user);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while updating 'User' data ==> " + user + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Updating single 'User' informations with Id # " + user.getId() + " ---");
		return totalEffectedRows;
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public void update(List<UserBean> users, long recordUpdId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Updating multi 'User' informations ---");
		for (UserBean user : users) {
			try {
				user.setRecordUpdId(recordUpdId);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history before update on major table ---");
				UserBean oldData = userMapper.selectByPrimaryKey(user.getId(), FetchMode.LAZY);
				if (oldData == null) {
					throw new SaveHistoryFailedException();
				}
				oldData.setTransactionType(TransactionType.UPDATE);
				oldData.setRecordUpdId(recordUpdId);
				userMapper.insertSingleHistoryRecord(oldData);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
				userMapper.update(user);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while updating 'User' data ==> " + user + " xxx";
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Updating multi 'User' informations ---");
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public long update(HashMap<String, Object> criteria, HashMap<String, Object> updateItems,
			long recordUpdId) throws DAOException, DuplicatedEntryException {
		long totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Updating multi 'User' informations with criteria ---");
		try {
			criteria.put("recordUpdId", recordUpdId);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history before update on major table ---");
			List<UserBean> users = userMapper.selectMultiRecords(criteria, FetchMode.LAZY);
			if (users == null) {
				throw new SaveHistoryFailedException();
			} else if (users != null && users.size() > 0) {
				for (UserBean user : users) {
					user.setTransactionType(TransactionType.UPDATE);
					user.setRecordUpdId(recordUpdId);
					userMapper.insertSingleHistoryRecord(user);
				}
			}
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
			totalEffectedRows = userMapper.updateWithCriteria(criteria, updateItems);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while updating multiple 'User' informations [Values] ==> " + updateItems + " with [Criteria] ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Updating multi 'User' informations with criteria ---");
		return totalEffectedRows;
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public long delete(long primaryKey,
			long recordUpdId) throws DAOException, ConsistencyViolationException {
		daoLogger.debug("[START] : >>> --- Deleting single 'User' informations with primaryKey # " + primaryKey + " ---");
		long totalEffectedRows = 0;
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history before update on major table ---");
			UserBean oldData = userMapper.selectByPrimaryKey(primaryKey, FetchMode.LAZY);
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			userMapper.insertSingleHistoryRecord(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
			totalEffectedRows = userMapper.deleteByPrimaryKey(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'User' data with primaryKey ==> " + primaryKey + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'User' informations with primaryKey # " + primaryKey + " ---");
		return totalEffectedRows;
	}

	@Override
	@CacheEvict(value = "ConfigurationCache.User", allEntries = true)
	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws DAOException, ConsistencyViolationException {
		long totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Deleting 'User' informations with criteria  ---");
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'User' informations in history before delete on major table ---");
			List<UserBean> users = userMapper.selectMultiRecords(criteria, FetchMode.LAZY);
			if (users == null) {
				throw new SaveHistoryFailedException();
			} else if (users != null && users.size() > 0) {
				for (UserBean user : users) {
					user.setTransactionType(TransactionType.DELETE);
					user.setRecordUpdId(recordUpdId);
				}
				userMapper.insertMultiHistoryRecords(users);
			}
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'User' informations in history ---");
			totalEffectedRows = userMapper.deleteByCriteria(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'User' informations in history for later tracking xxx";
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'User' data with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'User' informations with criteria  ---");
		return totalEffectedRows;
	}

	@Override
	@Cacheable(value = "ConfigurationCache.User")
	public UserBean select(long primaryKey, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'User' informations with primaryKey # " + primaryKey + " ---");
		UserBean user = new UserBean();
		try {
			user = userMapper.selectByPrimaryKey(primaryKey, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'User' informations with primaryKey ==> " + primaryKey + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'User' informations with primaryKey # " + primaryKey + " ---");
		return user;
	}

	@Override
	@Cacheable(value = "ConfigurationCache.User")
	public UserBean select(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'User' informations with criteria ---");
		UserBean user = new UserBean();
		try {
			user = userMapper.selectSingleRecord(criteria, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'User' informations with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'User' informations with criteria ---");
		return user;
	}

	@Override
	@Cacheable(value = "ConfigurationCache.User")
	public List<UserBean> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'User' informations with criteria ---");
		List<UserBean> users = null;
		try {
			users = userMapper.selectMultiRecords(criteria, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'User' informations with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'User' informations with criteria ---");
		return users;
	}

	@Override
	@Cacheable(value = "ConfigurationCache.User")
	public long selectCounts(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'User' counts with criteria ---");
		long count = 0;
		try {
			count = userMapper.selectCounts(criteria, fetchMode);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'User' records with criteria ==> " + criteria + " xxx";
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'User' counts with criteria ---");
		return count;
	}

}
