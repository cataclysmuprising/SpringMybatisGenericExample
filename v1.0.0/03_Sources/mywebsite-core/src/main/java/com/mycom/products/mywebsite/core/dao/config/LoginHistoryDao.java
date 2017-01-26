package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.dao.InsertableDao;
import com.mycom.products.mywebsite.core.dao.SelectableDao;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.LoginHistoryMapper;

@Repository
public class LoginHistoryDao implements InsertableDao<LoginHistoryBean>, SelectableDao<LoginHistoryBean> {

	@Autowired
	private LoginHistoryMapper loginHistoryMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(LoginHistoryBean loginHistory,
			int recordRegId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			loginHistory.setRecordRegDate(now);
			loginHistory.setRecordUpdDate(now);
			loginHistory.setRecordRegId(recordRegId);
			loginHistory.setRecordUpdId(recordRegId);
			loginHistoryMapper.insert(loginHistory);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'LoginHistory' data ==> " + loginHistory;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return loginHistory.getId();
	}

	@Override
	public void insert(List<LoginHistoryBean> loginHistorys,
			int recordRegId) throws MyBatisException, DuplicatedEntryException {
		for (LoginHistoryBean loginHistory : loginHistorys) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				loginHistory.setRecordRegDate(now);
				loginHistory.setRecordUpdDate(now);
				loginHistory.setRecordRegId(recordRegId);
				loginHistory.setRecordUpdId(recordRegId);
				loginHistoryMapper.insert(loginHistory);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'LoginHistory' data ==> " + loginHistory;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public LoginHistoryBean select(int primaryKey) throws MyBatisException {
		try {
			return loginHistoryMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'LoginHistory' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public LoginHistoryBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return loginHistoryMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'LoginHistory' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<LoginHistoryBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return loginHistoryMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'LoginHistory' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return loginHistoryMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'LoginHistory' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
