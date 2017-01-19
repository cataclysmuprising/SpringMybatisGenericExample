package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.LoginHistoryMapper;

@Repository
public class LoginHistoryDao implements CommonExecutable<LoginHistoryBean> {
	@Autowired
	private LoginHistoryMapper loginHistoryMapper;

	@Override
	public int insert(LoginHistoryBean loginHistory,
			int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			loginHistory.setRecordRegDate(now);
			loginHistory.setRecordUpdDate(now);
			loginHistory.setRecordRegId(loginUserId);
			loginHistory.setRecordUpdId(loginUserId);
			loginHistory.setTransactionType(TransactionType.INSERT);
			loginHistoryMapper.insert(loginHistory);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting LoginHistoryBean data ==> " + loginHistory, e);
		}
		return loginHistory.getId();
	}

	@Override
	public int update(LoginHistoryBean loginHistory,
			int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			loginHistory.setRecordUpdId(loginUserId);
			return loginHistoryMapper.update(loginHistory);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while update LoginHistoryBean data" + loginHistory, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			return loginHistoryMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while delete LoginHistoryBean data" + criteria, e);
		}
	}

	@Override
	public LoginHistoryBean selectById(int id) throws MyBatisException {
		try {
			return loginHistoryMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting LoginHistoryBean by id= " + id, e);
		}
	}

	@Override
	public List<LoginHistoryBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return loginHistoryMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting LoginHistoryBean by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return loginHistoryMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all LoginHistoryBean counts by criteria " + criteria, e);
		}
	}

}
