package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.config.RoleMapper;

@Repository
public class RoleDao implements CommonExecutable<RoleBean> {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public int insert(RoleBean role, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			role.setRecordRegDate(now);
			role.setRecordUpdDate(now);
			role.setRecordRegId(loginUserId);
			role.setRecordUpdId(loginUserId);
			role.setTransactionType(TransactionType.INSERT);
			roleMapper.insert(role);
			roleMapper.insertHistory(role);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting RoleBean data ==> " + role, e);
		}
		return role.getId();
	}

	@Override
	public int update(RoleBean role, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			role.setRecordUpdId(loginUserId);
			RoleBean oldData = roleMapper.selectById(role.getId());
			if (oldData == null) {
				throw new SavingHistoryRecordFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(loginUserId);
			roleMapper.insertHistory(oldData);
			return roleMapper.update(role);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating RoleBean data ==> " + role, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<RoleBean> roles = roleMapper.selectByCriteria(criteria);
			if (roles == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (roles != null && roles.size() > 0) {
				for (RoleBean role : roles) {
					role.setTransactionType(TransactionType.DELETE);
					role.setRecordUpdId(loginUserId);
					roleMapper.insertHistory(role);
				}
			}
			return roleMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting RoleBean data ==> " + criteria, e);
		}
	}

	@Override
	public RoleBean selectById(int id) throws MyBatisException {
		try {
			return roleMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting RoleBean by id= " + id, e);
		}
	}

	@Override
	public List<RoleBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting users by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all user counts by criteria " + criteria, e);
		}
	}
}
