package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.config.UserRoleMapper;

@Repository
public class UserRoleDao implements CommonExecutable<UserRoleBean> {
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public int insert(UserRoleBean userRole, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			userRole.setRecordRegDate(now);
			userRole.setRecordUpdDate(now);
			userRole.setRecordRegId(loginUserId);
			userRole.setRecordUpdId(loginUserId);
			userRole.setTransactionType(TransactionType.INSERT);
			userRoleMapper.insert(userRole);
			userRoleMapper.insertHistory(userRole);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting UserRoleBean data ==> " + userRole, e);
		}
		return userRole.getId();
	}

	@Override
	public int update(UserRoleBean userRole, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			userRole.setRecordUpdId(loginUserId);
			HashMap<String, Object> criteria = new HashMap<>();
			if (userRole.getUserId() > 0) {
				criteria.put("userId", userRole.getUserId());
			}
			if (userRole.getUser() != null && userRole.getUser().getId() > 0) {
				criteria.put("userId", userRole.getUser().getId());
			}
			if (userRole.getRoleId() > 0) {
				criteria.put("roleId", userRole.getRoleId());
			}
			if (userRole.getRole() != null && userRole.getRole().getId() > 0) {
				criteria.put("roleId", userRole.getRole().getId());
			}
			List<UserRoleBean> oldDatas = userRoleMapper.selectByCriteria(criteria);
			if (oldDatas == null) {
				throw new SavingHistoryRecordFailedException();
			}
			for (UserRoleBean data : oldDatas) {
				data.setTransactionType(TransactionType.UPDATE);
				data.setRecordUpdId(loginUserId);
				userRoleMapper.insertHistory(data);
			}
			return userRoleMapper.update(userRole);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating UserRoleBean data ==> " + userRole, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<UserRoleBean> userRoles = userRoleMapper.selectByCriteria(criteria);
			if (userRoles == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (userRoles != null && userRoles.size() > 0) {
				for (UserRoleBean userRole : userRoles) {
					userRole.setTransactionType(TransactionType.DELETE);
					userRole.setRecordUpdId(loginUserId);
					userRoleMapper.insertHistory(userRole);
				}
			}
			return userRoleMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting UserRoleBean data ==> " + criteria, e);
		}
	}

	@Override
	public UserRoleBean selectById(int id) throws MyBatisException {
		return null;
	}

	@Override
	public List<UserRoleBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userRoleMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting userRoles by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return userRoleMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all userRole counts by criteria " + criteria, e);
		}
	}
}
