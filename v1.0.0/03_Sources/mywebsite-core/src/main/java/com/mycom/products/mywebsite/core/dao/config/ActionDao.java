package com.mycom.products.mywebsite.core.dao.config;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.config.ActionMapper;

@Repository
public class ActionDao implements CommonExecutable<ActionBean> {

	@Autowired
	private ActionMapper actionMapper;

	@Override
	public int insert(ActionBean action, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			action.setRecordRegDate(now);
			action.setRecordUpdDate(now);
			action.setRecordRegId(loginUserId);
			action.setRecordUpdId(loginUserId);
			action.setTransactionType(TransactionType.INSERT);
			actionMapper.insert(action);
			actionMapper.insertHistory(action);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting Action data ==> " + action, e);
		}
		return action.getId();
	}

	@Override
	public int update(ActionBean action, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			action.setRecordUpdId(loginUserId);
			ActionBean oldData = actionMapper.selectById(action.getId());
			if (oldData == null) {
				throw new SavingHistoryRecordFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(loginUserId);
			actionMapper.insertHistory(oldData);
			return actionMapper.update(action);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating Action data ==> " + action, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<ActionBean> actions = actionMapper.selectByCriteria(criteria);
			if (actions == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (actions != null && actions.size() > 0) {
				for (ActionBean action : actions) {
					action.setTransactionType(TransactionType.DELETE);
					action.setRecordUpdId(loginUserId);
					actionMapper.insertHistory(action);
				}
			}
			return actionMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while deleting Action data ==> " + criteria, e);
		}
	}

	@Override
	public ActionBean selectById(int id) throws MyBatisException {
		try {
			return actionMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting Action by id= " + id, e);
		}
	}

	@Override
	public List<ActionBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return actionMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting actions by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return actionMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all action counts by criteria " + criteria, e);
		}
	}

	public List<String> selectAllPageNames() throws MyBatisException {
		try {
			return actionMapper.selectAllPageNames();
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting all Page Names.", e);
		}
	}

}
