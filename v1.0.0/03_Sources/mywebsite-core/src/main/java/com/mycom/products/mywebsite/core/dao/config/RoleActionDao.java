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
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.config.RoleActionMapper;

@Repository
public class RoleActionDao implements CommonExecutable<RoleActionBean> {

	@Autowired
	private RoleActionMapper roleActionMapper;

	@Override
	public int insert(RoleActionBean roleAction, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			roleAction.setRecordRegDate(now);
			roleAction.setRecordUpdDate(now);
			roleAction.setRecordRegId(loginUserId);
			roleAction.setRecordUpdId(loginUserId);
			roleAction.setTransactionType(TransactionType.INSERT);
			roleActionMapper.insert(roleAction);
			roleActionMapper.insertHistory(roleAction);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting RoleActionBean data ==> " + roleAction, e);
		}
		return roleAction.getId();
	}

	@Override
	public int update(RoleActionBean roleAction, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			roleAction.setRecordUpdId(loginUserId);
			HashMap<String, Object> criteria = new HashMap<>();
			if (roleAction.getRoleId() > 0) {
				criteria.put("roleId", roleAction.getRoleId());
			}
			if (roleAction.getRole() != null && roleAction.getRole().getId() > 0) {
				criteria.put("roleId", roleAction.getRole().getId());
			}
			if (roleAction.getActionId() > 0) {
				criteria.put("actionId", roleAction.getActionId());
			}
			if (roleAction.getAction() != null && roleAction.getAction().getId() > 0) {
				criteria.put("actionId", roleAction.getAction().getId());
			}
			List<RoleActionBean> oldDatas = roleActionMapper.selectByCriteria(criteria);
			if (oldDatas == null) {
				throw new SavingHistoryRecordFailedException();
			}
			for (RoleActionBean data : oldDatas) {
				data.setTransactionType(TransactionType.UPDATE);
				data.setRecordUpdId(loginUserId);
				roleActionMapper.insertHistory(data);
			}
			return roleActionMapper.update(roleAction);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating RoleActionBean data ==> " + roleAction, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<RoleActionBean> roleActions = roleActionMapper.selectByCriteria(criteria);
			if (roleActions == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (roleActions != null && roleActions.size() > 0) {
				for (RoleActionBean roleAction : roleActions) {
					roleAction.setTransactionType(TransactionType.DELETE);
					roleAction.setRecordUpdId(loginUserId);
					roleActionMapper.insertHistory(roleAction);
				}
			}
			return roleActionMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting RoleActionBean data ==> " + criteria, e);
		}
	}

	@Override
	public RoleActionBean selectById(int id) throws MyBatisException {
		return null;
	}

	@Override
	public List<RoleActionBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleActionMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting users by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return roleActionMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all RoleAction counts by criteria " + criteria, e);
		}
	}
}
