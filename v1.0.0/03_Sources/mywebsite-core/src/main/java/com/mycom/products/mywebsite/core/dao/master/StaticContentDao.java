package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.dao.CommonGenericDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.master.StaticContentMapper;

@Repository
public class StaticContentDao implements CommonGenericDao<StaticContentBean> {

	@Autowired
	private StaticContentMapper staticContentMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int insert(StaticContentBean staticContent,
			int recordRegId) throws MyBatisException, DuplicatedEntryException {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			staticContent.setRecordRegDate(now);
			staticContent.setRecordUpdDate(now);
			staticContent.setRecordRegId(recordRegId);
			staticContent.setRecordUpdId(recordRegId);
			staticContentMapper.insert(staticContent);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Insertion process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while inserting 'StaticContent' data ==> " + staticContent;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
		return staticContent.getId();
	}

	@Override
	public void insert(List<StaticContentBean> staticContents,
			int recordRegId) throws MyBatisException, DuplicatedEntryException {
		for (StaticContentBean staticContent : staticContents) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				staticContent.setRecordRegDate(now);
				staticContent.setRecordUpdDate(now);
				staticContent.setRecordRegId(recordRegId);
				staticContent.setRecordUpdId(recordRegId);
				staticContentMapper.insert(staticContent);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Insertion process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while inserting 'StaticContent' data ==> " + staticContent;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int update(StaticContentBean staticContent,
			int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		try {
			staticContent.setRecordUpdId(recordUpdId);
			return staticContentMapper.update(staticContent);
		} catch (DuplicateKeyException e) {
			String errorMsg = "Updating process was failed due to Unique Key constraint.";
			logger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while updating 'StaticContent' data ==> " + staticContent;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public void update(List<StaticContentBean> staticContents,
			int recordUpdId) throws MyBatisException, DuplicatedEntryException {
		for (StaticContentBean staticContent : staticContents) {
			try {
				staticContent.setRecordUpdId(recordUpdId);
				staticContentMapper.update(staticContent);
			} catch (DuplicateKeyException e) {
				String errorMsg = "Updating process was failed due to Unique Key constraint.";
				logger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (Exception e) {
				String errorMsg = "Error occured while updating 'StaticContent' data ==> " + staticContent;
				logger.error(errorMsg, e);
				throw new MyBatisException(errorMsg, e);
			}
		}
	}

	@Override
	public int delete(int primaryKey,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return staticContentMapper.delete(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'StaticContent' data with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws MyBatisException, ConsistencyViolationException {
		try {
			return staticContentMapper.delete(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency.";
			logger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (Exception e) {
			String errorMsg = "Error occured while deleting 'StaticContent' data with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public StaticContentBean select(int primaryKey) throws MyBatisException {
		try {
			return staticContentMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'StaticContent' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public StaticContentBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return staticContentMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'StaticContent' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<StaticContentBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return staticContentMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'StaticContent' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return staticContentMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'StaticContent' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
