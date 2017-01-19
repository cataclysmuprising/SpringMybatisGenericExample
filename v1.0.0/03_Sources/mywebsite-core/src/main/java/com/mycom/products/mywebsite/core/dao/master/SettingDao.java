package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.master.SettingMapper;

@Repository
public class SettingDao implements CommonExecutable<SettingBean> {

	@Autowired
	private SettingMapper settingMapper;

	@Override
	public int insert(SettingBean setting, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			setting.setRecordRegDate(now);
			setting.setRecordUpdDate(now);
			setting.setRecordRegId(loginUserId);
			setting.setRecordUpdId(loginUserId);
			setting.setTransactionType(TransactionType.INSERT);
			settingMapper.insert(setting);
			settingMapper.insertHistory(setting);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting Action data ==> " + setting, e);
		}
		return setting.getId();
	}

	@Override
	public int update(SettingBean setting, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			setting.setRecordUpdId(loginUserId);
			SettingBean oldData = settingMapper.selectById(setting.getId());
			if (oldData == null) {
				throw new SavingHistoryRecordFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(loginUserId);
			settingMapper.insertHistory(oldData);
			return settingMapper.update(setting);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating CategoryBean data ==>" + setting, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<SettingBean> settings = settingMapper.selectByCriteria(criteria);
			if (settings == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (settings != null && settings.size() > 0) {
				for (SettingBean setting : settings) {
					setting.setTransactionType(TransactionType.DELETE);
					setting.setRecordUpdId(loginUserId);
					settingMapper.insertHistory(setting);
				}
			}
			return settingMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting Approve data ==> " + criteria, e);
		}
	}

	@Override
	public SettingBean selectById(int id) throws MyBatisException {
		try {
			return settingMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting Action by id= " + id, e);
		}
	}

	@Override
	public List<SettingBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return settingMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting actions by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return settingMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all user counts by criteria " + criteria, e);
		}
	}
}
