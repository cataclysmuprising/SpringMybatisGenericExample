/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.config;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.SelectableDao;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.mapper.config.ActionMapper;

@Repository
public class ActionDao implements SelectableDao<ActionBean> {

	@Autowired
	private ActionMapper actionMapper;
	private Logger daoLogger = Logger.getLogger(this.getClass());

	@Override
	public ActionBean select(int primaryKey) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Action' informations with primaryKey # " + primaryKey + " ---");
		ActionBean action = new ActionBean();
		try {
			action = actionMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Action' informations with primaryKey ==> " + primaryKey + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Action' informations with primaryKey # " + primaryKey + " ---");
		return action;
	}

	@Override
	public ActionBean select(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching single 'Action' informations with criteria ---");
		ActionBean action = new ActionBean();
		try {
			action = actionMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching single 'Action' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching single 'Action' informations with criteria ---");
		return action;
	}

	@Override
	public List<ActionBean> selectList(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching multi 'Action' informations with criteria ---");
		List<ActionBean> actions = null;
		try {
			actions = actionMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while fetching multiple 'Action' informations with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching multi 'Action' informations with criteria ---");
		return actions;
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws DAOException {
		daoLogger.debug("[START] : >>> --- Fetching 'Action' counts with criteria ---");
		int count = 0;
		try {
			count = actionMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "xxx Error occured while counting 'Action' records with criteria ==> " + criteria + " xxx";
			daoLogger.error(errorMsg, e);
			throw new DAOException(errorMsg, e);
		}
		daoLogger.debug("[FINISH] : <<< --- Fetching 'Action' counts with criteria ---");
		return count;
	}
}
