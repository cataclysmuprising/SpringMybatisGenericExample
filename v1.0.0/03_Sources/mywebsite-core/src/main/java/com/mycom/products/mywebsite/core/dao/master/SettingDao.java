/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.SaveHistoryFailedException;
import com.mycom.products.mywebsite.core.mapper.master.SettingMapper;

@Repository
public class SettingDao implements CommonGenericDao<SettingBean> {

	@Autowired
	private SettingMapper settingMapper;

	private Logger daoLogger = Logger.getLogger(this.getClass());

	@Override
	public int insert(SettingBean setting, int recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'Setting' informations ---");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			setting.setRecordRegDate(now);
			setting.setRecordUpdDate(now);
			setting.setRecordRegId(recordRegId);
			setting.setRecordUpdId(recordRegId);
			setting.setTransactionType(TransactionType.INSERT);
			settingMapper.insert(setting);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history after successfully inserted in major table ---");
			settingMapper.saveHistory(setting);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'Setting' data ==> " + setting + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'Setting' informations with new Id # " + setting.getId() + " ---");
		return setting.getId();
	}

	@Override
	public void insert(List<SettingBean> settings, int recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'Setting' informations ---");
		for (SettingBean setting : settings) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				setting.setRecordRegDate(now);
				setting.setRecordUpdDate(now);
				setting.setRecordRegId(recordRegId);
				setting.setRecordUpdId(recordRegId);
				setting.setTransactionType(TransactionType.INSERT);
				settingMapper.insert(setting);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history after successfully inserted in major table ---");
				settingMapper.saveHistory(setting);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while inserting 'Setting' data ==> " + setting + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'Setting' informations ---");
	}

	@Override
	public int update(SettingBean setting, int recordUpdId) throws DAOException, DuplicatedEntryException {
		int totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Updating single 'Setting' informations ---");
		try {
			setting.setRecordUpdId(recordUpdId);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history before update on major table ---");
			SettingBean oldData = settingMapper.selectByPrimaryKey(setting.getId());
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			settingMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
			totalEffectedRows = settingMapper.update(setting);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while updating 'Setting' data ==> " + setting + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Updating single 'Setting' informations with Id # " + setting.getId() + " ---");
		return totalEffectedRows;
	}

	@Override
	public void update(List<SettingBean> settings, int recordUpdId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Updating multi 'Setting' informations ---");
		for (SettingBean setting : settings) {
			try {
				setting.setRecordUpdId(recordUpdId);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history before update on major table ---");
				SettingBean oldData = settingMapper.selectByPrimaryKey(setting.getId());
				if (oldData == null) {
					throw new SaveHistoryFailedException();
				}
				oldData.setTransactionType(TransactionType.UPDATE);
				oldData.setRecordUpdId(recordUpdId);
				settingMapper.saveHistory(oldData);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
				settingMapper.update(setting);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while updating 'Setting' data ==> " + setting + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Updating multi 'Setting' informations ---");
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws DAOException, ConsistencyViolationException {
		daoLogger.debug("[START] : >>> --- Deleting single 'Setting' informations with primaryKey # " + primaryKey + " ---");
		int totalEffectedRows = 0;
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history before update on major table ---");
			SettingBean oldData = settingMapper.selectByPrimaryKey(primaryKey);
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			settingMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
			totalEffectedRows = settingMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'Setting' data with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'Setting' informations with primaryKey # " + primaryKey + " ---");
		return totalEffectedRows;
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws DAOException, ConsistencyViolationException {
		int totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Deleting 'Setting' informations with criteria  ---");
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'Setting' informations in history before delete on major table ---");
			List<SettingBean> settings = settingMapper.selectMultiRecords(criteria);
			if (settings == null) {
				throw new SaveHistoryFailedException();
			}
			if (settings != null && settings.size() > 0) {
				for (SettingBean setting : settings) {
					setting.setTransactionType(TransactionType.DELETE);
					setting.setRecordUpdId(recordUpdId);
					settingMapper.saveHistory(setting);
				}
			}
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'Setting' informations in history ---");
			totalEffectedRows = settingMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'Setting' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'Setting' data with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'Setting' informations with criteria  ---");
		return totalEffectedRows;
	}

	@Override
	public SettingBean select(int primaryKey) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Setting' informations with primaryKey # " + primaryKey + " ---");
		SettingBean setting = new SettingBean();
		try {
			setting = settingMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Setting' informations with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Setting' informations with primaryKey # " + primaryKey + " ---");
		return setting;
	}

	@Override
	public SettingBean select(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'Setting' informations with criteria ---");
		SettingBean setting = new SettingBean();
		try {
			setting = settingMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Setting' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'Setting' informations with criteria ---");
		return setting;
	}

	@Override
	public List<SettingBean> selectList(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'Setting' informations with criteria ---");
		List<SettingBean> settings = null;
		try {
			settings = settingMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'Setting' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'Setting' informations with criteria ---");
		return settings;
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Setting' counts with criteria ---");
		int count = 0;
		try {
			count = settingMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'Setting' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Setting' counts with criteria ---");
		return count;
	}
}
