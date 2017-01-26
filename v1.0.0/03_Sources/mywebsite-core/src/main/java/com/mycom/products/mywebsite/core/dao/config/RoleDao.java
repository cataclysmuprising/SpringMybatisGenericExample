package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.RoleMapper;

@Repository
public class RoleDao implements CommonGenericDao<RoleBean> {

	@Autowired
	private RoleMapper roleMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(RoleBean role, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			role.setRecordRegDate(now);
			role.setRecordUpdDate(now);
			role.setRecordRegId(recordRegId);
			role.setRecordUpdId(recordRegId);
			roleMapper.insert(role);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'Role' data ==> " + role;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return role.getId();
	}

	@Override
	public void insert(List<RoleBean> roles, int recordRegId) throws MyBatisException, DuplicatedEntryException {
		for (RoleBean role : roles) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				role.setRecordRegDate(now);
				role.setRecordUpdDate(now);
				role.setRecordRegId(recordRegId);
				role.setRecordUpdId(recordRegId);
				roleMapper.insert(role);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'Role' data ==> " + role;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int update(RoleBean role, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		try {
			role.setRecordUpdId(recordUpdId);
			return roleMapper.update(role);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Updating process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while updating 'Role' data ==> " + role;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public void update(List<RoleBean> roles, int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		for (RoleBean role : roles) {
			try {
				role.setRecordUpdId(recordUpdId);
				roleMapper.update(role);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Updating process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while updating 'Role' data ==> " + role;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return roleMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'Role' data with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return roleMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'Role' data with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public RoleBean select(int primaryKey) throws MyBatisException {
		try {
			return roleMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Role' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public RoleBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Role' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<RoleBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'Role' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'Role' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
