/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.config;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;
import com.mycom.products.mywebsite.core.dao.config.LoginHistoryDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.JoinedSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class LoginHistoryDaoTest extends BaseDaoTest implements JoinedSelectableDaoTest {
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<LoginHistoryBean> results = loginHistoryDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<LoginHistoryBean> results = loginHistoryDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = loginHistoryDao.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("userId", 1);
		long count = loginHistoryDao.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("userId", 1);
		long count = loginHistoryDao.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "userId");
		criteria.put("orderAs", "desc");
		List<LoginHistoryBean> results = loginHistoryDao.selectList(criteria, FetchMode.LAZY);
		Assert.assertNotNull(results);
		showEntriesOfCollection(results);

	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "userId");
		criteria.put("orderAs", "desc");
		List<LoginHistoryBean> results = loginHistoryDao.selectList(criteria, FetchMode.EAGER);
		Assert.assertNotNull(results);
		showEntriesOfCollection(results);

	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException {
		LoginHistoryBean loginHistory = loginHistoryDao.select(1, FetchMode.LAZY);
		Assert.assertNotNull(loginHistory);
		testLogger.info("LoginHistory ==> " + loginHistory);

	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException {
		LoginHistoryBean loginHistory = loginHistoryDao.select(1, FetchMode.EAGER);
		Assert.assertNotNull(loginHistory);
		testLogger.info("LoginHistory ==> " + loginHistory);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		LoginHistoryBean loginHistory = loginHistoryDao.select(criteria, FetchMode.LAZY);
		Assert.assertNotNull(loginHistory);
		testLogger.info("LoginHistory ==> " + loginHistory);

	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		LoginHistoryBean loginHistory = loginHistoryDao.select(criteria, FetchMode.EAGER);
		Assert.assertNotNull(loginHistory);
		testLogger.info("LoginHistory ==> " + loginHistory);
	}

}
