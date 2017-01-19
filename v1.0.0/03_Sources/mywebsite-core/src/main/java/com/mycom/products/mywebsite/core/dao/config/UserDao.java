package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.config.UserMapper;

@Repository
public class UserDao implements CommonExecutable<UserBean> {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int insert(UserBean user, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setRecordRegDate(now);
			user.setRecordUpdDate(now);
			user.setRecordRegId(loginUserId);
			user.setRecordUpdId(loginUserId);
			user.setTransactionType(TransactionType.INSERT);
			userMapper.insert(user);
			userMapper.insertHistory(user);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting UserBean data ==> " + user, e);
		}
		return user.getId();
	}

	@Override
	public int update(UserBean user, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			user.setRecordUpdId(loginUserId);
			UserBean oldData = userMapper.selectById(user.getId());
			if (oldData == null) {
				throw new SavingHistoryRecordFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(loginUserId);
			userMapper.insertHistory(oldData);
			return userMapper.update(user);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating UserBean data ==> " + user, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<UserBean> users = userMapper.selectByCriteria(criteria);
			if (users == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (users != null && users.size() > 0) {
				for (UserBean user : users) {
					user.setTransactionType(TransactionType.DELETE);
					user.setRecordUpdId(loginUserId);
					userMapper.insertHistory(user);
				}
			}
			return userMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting UserBean data ==> " + criteria, e);
		}
	}

	@Override
	public UserBean selectById(int id) throws MyBatisException {
		try {
			return userMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting UserBean by id= " + id, e);
		}
	}

	@Override
	public List<UserBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting users by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all user counts by criteria " + criteria, e);
		}
	}

	public UserBean selectByLoginId(String loginId) throws MyBatisException {
		UserBean user = null;
		try {
			user = userMapper.selectByLoginId(loginId);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting with email = " + loginId, e);
		}
		return user;
	}
}
