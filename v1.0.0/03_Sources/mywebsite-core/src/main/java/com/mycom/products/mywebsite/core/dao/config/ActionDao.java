package com.mycom.products.mywebsite.core.dao.config;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.SelectableDao;
import com.mycom.products.mywebsite.core.exception.MyBatisException;
import com.mycom.products.mywebsite.core.mapper.config.ActionMapper;

@Repository
public class ActionDao implements SelectableDao<ActionBean> {

	@Autowired
	private ActionMapper actionMapper;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ActionBean select(int primaryKey) throws MyBatisException {
		try {
			return actionMapper.selectByPrimaryKey(primaryKey);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Action' information with primaryKey ==> " + primaryKey;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public ActionBean select(Map<String, Object> criteria) throws MyBatisException {
		try {
			return actionMapper.selectSingleRecord(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting single 'Action' information with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public List<ActionBean> selectList(Map<String, Object> criteria) throws MyBatisException {
		try {
			return actionMapper.selectMultiRecords(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while selecting multiple 'Action' informations with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}

	@Override
	public int selectCounts(Map<String, Object> criteria) throws MyBatisException {
		try {
			return actionMapper.selectCounts(criteria);
		} catch (Exception e) {
			String errorMsg = "Error occured while counting 'Action' records with criteria ==> " + criteria;
			logger.error(errorMsg, e);
			throw new MyBatisException(errorMsg, e);
		}
	}
}
