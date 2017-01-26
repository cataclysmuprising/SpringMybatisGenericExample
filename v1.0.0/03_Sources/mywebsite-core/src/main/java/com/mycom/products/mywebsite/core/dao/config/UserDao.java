package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.UserMapper;

@Repository
public class UserDao implements CommonGenericDao<UserBean> {

	@Autowired
	private UserMapper userMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(UserBean user, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setRecordRegDate(now);
			user.setRecordUpdDate(now);
			user.setRecordRegId(recordRegId);
			user.setRecordUpdId(recordRegId);
			userMapper.insert(user);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'User' data ==> " + user;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return user.getId();
	}

	@Override
	public void insert(List<UserBean> users, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		for (UserBean user : users) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				user.setRecordRegDate(now);
				user.setRecordUpdDate(now);
				user.setRecordRegId(recordRegId);
				user.setRecordUpdId(recordRegId);
				userMapper.insert(user);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'User' data ==> " + user;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int update(UserBean user, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		try {
			user.setRecordUpdId(recordUpdId);
			return userMapper.update(user);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Updating process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while updating 'User' data ==> " + user;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public void update(List<UserBean> users, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		for (UserBean user : users) {
			try {
				user.setRecordUpdId(recordUpdId);
				userMapper.update(user);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Updating process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while updating 'User' data ==> " + user;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return userMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'User' data with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return userMapper.delete(criteria);
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
	public UserBean select(int primaryKey) throws MyBatisException {
		try {
			return userMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'User' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public UserBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'User' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<UserBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'User' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'User' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
