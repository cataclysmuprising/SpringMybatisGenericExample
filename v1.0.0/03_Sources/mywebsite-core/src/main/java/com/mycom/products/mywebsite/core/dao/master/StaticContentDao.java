/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.master;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.BaseBean.TransactionType;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.dao.base.CommonGenericDao;
import com.mycom.products.mywebsite.core.dao.base.StandAloneSelectableDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.SaveHistoryFailedException;
import com.mycom.products.mywebsite.core.mapper.master.StaticContentMapper;

@Repository
public class StaticContentDao
		implements CommonGenericDao<StaticContentBean>, StandAloneSelectableDao<StaticContentBean> {

	@Autowired
	private StaticContentMapper staticContentMapper;

	private Logger daoLogger = Logger.getLogger(this.getClass());

	@Override
	public long insert(StaticContentBean staticContent,
			long recordRegId) throws DAOException, DuplicatedEntryException {
		try {
			daoLogger.debug("[START] : >>> --- Inserting single 'StaticContent' informations ---");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			staticContent.setRecordRegDate(now);
			staticContent.setRecordUpdDate(now);
			staticContent.setRecordRegId(recordRegId);
			staticContent.setRecordUpdId(recordRegId);
			staticContent.setTransactionType(TransactionType.INSERT);
			staticContentMapper.insert(staticContent);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history after successfully inserted in major table ---");
			staticContentMapper.saveHistory(staticContent);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Insertion process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while inserting 'StaticContent' data ==> " + staticContent + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting single 'StaticContent' informations with new Id # " + staticContent.getId() + " ---");
		return staticContent.getId();
	}

	@Override
	public void insert(List<StaticContentBean> staticContents,
			long recordRegId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Inserting multi 'StaticContent' informations ---");
		for (StaticContentBean staticContent : staticContents) {
			try {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				staticContent.setRecordRegDate(now);
				staticContent.setRecordUpdDate(now);
				staticContent.setRecordRegId(recordRegId);
				staticContent.setRecordUpdId(recordRegId);
				staticContent.setTransactionType(TransactionType.INSERT);
				staticContentMapper.insert(staticContent);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history after successfully inserted in major table ---");
				staticContentMapper.saveHistory(staticContent);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Insertion process was failed due to Unique Key constraint. xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while inserting 'StaticContent' data ==> " + staticContent + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Inserting multi 'StaticContent' informations ---");
	}

	@Override
	public long update(StaticContentBean staticContent,
			long recordUpdId) throws DAOException, DuplicatedEntryException {
		long totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Updating single 'StaticContent' informations ---");
		try {
			staticContent.setRecordUpdId(recordUpdId);
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history before update on major table ---");
			StaticContentBean oldData = staticContentMapper.selectByPrimaryKey(staticContent.getId());
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			staticContentMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
			totalEffectedRows = staticContentMapper.update(staticContent);
		} catch (DuplicateKeyException e) {
			String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
			daoLogger.error(errorMsg, e);
			throw new DuplicatedEntryException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while updating 'StaticContent' data ==> " + staticContent + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Updating single 'StaticContent' informations with Id # " + staticContent.getId() + " ---");
		return totalEffectedRows;
	}

	@Override
	public void update(List<StaticContentBean> staticContents,
			long recordUpdId) throws DAOException, DuplicatedEntryException {
		daoLogger.debug("[START] : >>> --- Updating multi 'StaticContent' informations ---");
		for (StaticContentBean staticContent : staticContents) {
			try {
				staticContent.setRecordUpdId(recordUpdId);
				daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history before update on major table ---");
				StaticContentBean oldData = staticContentMapper.selectByPrimaryKey(staticContent.getId());
				if (oldData == null) {
					throw new SaveHistoryFailedException();
				}
				oldData.setTransactionType(TransactionType.UPDATE);
				oldData.setRecordUpdId(recordUpdId);
				staticContentMapper.saveHistory(oldData);
				daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
				staticContentMapper.update(staticContent);
			} catch (DuplicateKeyException e) {
				String errorMsg = "xxx Updating process was failed due to Unique Key constraint xxx";
				daoLogger.error(errorMsg, e);
				throw new DuplicatedEntryException(errorMsg, e);
			} catch (SaveHistoryFailedException e) {
				String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
				daoLogger.error(errorMsg, e);
				throw new SaveHistoryFailedException(errorMsg, e.getCause());
			} catch (Exception e) {
				String errorMsg = "xxx Error occured while updating 'StaticContent' data ==> " + staticContent + " xxx";
				daoLogger.error(errorMsg, e);
				throw new DAOException(errorMsg, e);
			}
		}
		daoLogger.debug("[FINISH] : <<< --- Updating multi 'StaticContent' informations ---");
	}

	@Override
	public long delete(long primaryKey,
			long recordUpdId) throws DAOException, ConsistencyViolationException {
		daoLogger.debug("[START] : >>> --- Deleting single 'StaticContent' informations with primaryKey # " + primaryKey + " ---");
		long totalEffectedRows = 0;
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history before update on major table ---");
			StaticContentBean oldData = staticContentMapper.selectByPrimaryKey(primaryKey);
			if (oldData == null) {
				throw new SaveHistoryFailedException();
			}
			oldData.setTransactionType(TransactionType.UPDATE);
			oldData.setRecordUpdId(recordUpdId);
			staticContentMapper.saveHistory(oldData);
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
			totalEffectedRows = staticContentMapper.deleteByPrimaryKey(primaryKey);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'StaticContent' data with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting single 'StaticContent' informations with primaryKey # " + primaryKey + " ---");
		return totalEffectedRows;
	}

	@Override
	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws DAOException, ConsistencyViolationException {
		long totalEffectedRows = 0;
		daoLogger.debug("[START] : >>> --- Deleting 'StaticContent' informations with criteria  ---");
		try {
			daoLogger.debug("[HISTORY][START] : $1 --- Save 'StaticContent' informations in history before delete on major table ---");
			List<StaticContentBean> staticContents = staticContentMapper.selectMultiRecords(criteria);
			if (staticContents == null) {
				throw new SaveHistoryFailedException();
			}
			if (staticContents != null && staticContents.size() > 0) {
				for (StaticContentBean staticContent : staticContents) {
					staticContent.setTransactionType(TransactionType.DELETE);
					staticContent.setRecordUpdId(recordUpdId);
					staticContentMapper.saveHistory(staticContent);
				}
			}
			daoLogger.debug("[HISTORY][FINISH] : $1 --- Save 'StaticContent' informations in history ---");
			totalEffectedRows = staticContentMapper.deleteByCriteria(criteria);
		} catch (DataIntegrityViolationException e) {
			String errorMsg = "xxx Rejected : Deleting process was failed because this entity was connected with other resources.If you try to forcely remove it, entire database will loose consistency xxx";
			daoLogger.error(errorMsg, e);
			throw new ConsistencyViolationException(errorMsg, e);
		} catch (SaveHistoryFailedException e) {
			String errorMsg = "xxx Error occured while saving 'StaticContent' informations in history for later tracking xxx";
			daoLogger.error(errorMsg, e);
			throw new SaveHistoryFailedException(errorMsg, e.getCause());
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while deleting 'StaticContent' data with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Deleting 'StaticContent' informations with criteria  ---");
		return totalEffectedRows;
	}

	@Override
	public StaticContentBean select(long primaryKey) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'StaticContent' informations with primaryKey # " + primaryKey + " ---");
		StaticContentBean staticContent = new StaticContentBean();
		try {
			staticContent = staticContentMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'StaticContent' informations with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'StaticContent' informations with primaryKey # " + primaryKey + " ---");
		return staticContent;
	}

	@Override
	public StaticContentBean select(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'StaticContent' informations with criteria ---");
		StaticContentBean staticContent = new StaticContentBean();
		try {
			staticContent = staticContentMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'StaticContent' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'StaticContent' informations with criteria ---");
		return staticContent;
	}

	@Override
	public List<StaticContentBean> selectList(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'StaticContent' informations with criteria ---");
		List<StaticContentBean> staticContents = null;
		try {
			staticContents = staticContentMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'StaticContent' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'StaticContent' informations with criteria ---");
		return staticContents;
	}

	@Override
	public long selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'StaticContent' counts with criteria ---");
		long count = 0;
		try {
			count = staticContentMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'StaticContent' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'StaticContent' counts with criteria ---");
		return count;
	}
}
