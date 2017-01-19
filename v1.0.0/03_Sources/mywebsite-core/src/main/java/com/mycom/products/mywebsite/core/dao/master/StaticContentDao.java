package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.dao.CommonExecutable;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.exception.SavingHistoryRecordFailedException;
import com.mycom.products.mywebsite.core.mapper.master.StaticContentMapper;

@Repository
public class StaticContentDao implements CommonExecutable<StaticContentBean> {

	@Autowired
	private StaticContentMapper staticContentMapper;

	@Override
	public int insert(StaticContentBean content, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			content.setRecordRegDate(now);
			content.setRecordUpdDate(now);
			content.setRecordRegId(loginUserId);
			content.setRecordUpdId(loginUserId);
			content.setTransactionType(TransactionType.INSERT);
			staticContentMapper.insert(content);
			staticContentMapper.insertHistory(content);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Insertion process was failed due to Unique Key constraint.", e);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while inserting StaticContentBean data ==> " + content, e);
		}
		return content.getId();
	}

	@Override
	public int update(StaticContentBean content, int loginUserId) throws MyBatisException, DuplicatedEntryException {
		try {
			content.setRecordUpdId(loginUserId);
			StaticContentBean oldData = staticContentMapper.selectById(content.getId());
			if (oldData == null) {
				throw new SavingHistoryRecordFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(loginUserId);
			staticContentMapper.insertHistory(oldData);
			return staticContentMapper.update(content);
		} catch (DuplicateKeyException e) {
			throw new DuplicatedEntryException("Updating process was failed due to Unique Key constraint.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while updating StaticContentBean data ==> " + content, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int loginUserId) throws MyBatisException, ConsistencyViolationException {
		try {
			List<StaticContentBean> contents = staticContentMapper.selectByCriteria(criteria);
			if (contents == null) {
				throw new SavingHistoryRecordFailedException();
			}
			if (contents != null && contents.size() > 0) {
				for (StaticContentBean content : contents) {
					content.setTransactionType(TransactionType.DELETE);
					content.setRecordUpdId(loginUserId);
					staticContentMapper.insertHistory(content);
				}
			}
			return staticContentMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			throw new ConsistencyViolationException("Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.", e);
		} catch (SavingHistoryRecordFailedException e) {
			// nothing to do yet
			return 0;
		} catch (Exception e) {
			throw new MyBatisException("Error occured while delete content data" + criteria, e);
		}
	}

	@Override
	public StaticContentBean selectById(int id) throws MyBatisException {
		try {
			return staticContentMapper.selectById(id);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting StaticContentBean by id= " + id, e);
		}
	}

	@Override
	public List<StaticContentBean> selectByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return staticContentMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while selecting static contents by criteria." + criteria, e);
		}
	}

	@Override
	public int selectCountByCriteria(Map<String, Object> criteria) throws MyBatisException {
		try {
			return staticContentMapper.selectCountByCriteria(criteria);
		} catch (Exception e) {
			throw new MyBatisException("Error occured while select all user counts by criteria " + criteria, e);
		}
	}

}
