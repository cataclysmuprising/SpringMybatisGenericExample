package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.master.SettingMapper;

@Repository
public class SettingDao implements CommonGenericDao<SettingBean> {

	@Autowired
	private SettingMapper settingMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(SettingBean setting, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			setting.setRecordRegDate(now);
			setting.setRecordUpdDate(now);
			setting.setRecordRegId(recordRegId);
			setting.setRecordUpdId(recordRegId);
			settingMapper.insert(setting);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'Setting' data ==> " + setting;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return setting.getId();
	}

	@Override
	public void insert(List<SettingBean> settings, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		for (SettingBean setting : settings) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				setting.setRecordRegDate(now);
				setting.setRecordUpdDate(now);
				setting.setRecordRegId(recordRegId);
				setting.setRecordUpdId(recordRegId);
				settingMapper.insert(setting);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'Setting' data ==> " + setting;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int update(SettingBean setting, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		try {
			setting.setRecordUpdId(recordUpdId);
			return settingMapper.update(setting);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Updating process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while updating 'Setting' data ==> " + setting;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public void update(List<SettingBean> settings, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		for (SettingBean setting : settings) {
			try {
				setting.setRecordUpdId(recordUpdId);
				settingMapper.update(setting);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Updating process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while updating 'Setting' data ==> " + setting;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return settingMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'Setting' data with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return settingMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'Setting' data with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public SettingBean select(int primaryKey) throws MyBatisException {
		try {
			return settingMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Setting' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public SettingBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return settingMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Setting' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<SettingBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return settingMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'Setting' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return settingMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'Setting' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
