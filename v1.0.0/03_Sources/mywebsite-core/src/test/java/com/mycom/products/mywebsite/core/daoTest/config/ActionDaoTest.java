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

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.config.ActionDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.JoinedSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class ActionDaoTest extends BaseDaoTest implements JoinedSelectableDaoTest {
	@Autowired
	private ActionDao actionDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<ActionBean> results = actionDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<ActionBean> results = actionDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = actionDao.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("page", "User");
		// criteria.put("actionName", "userAdd");
		// criteria.put("actionType", ActionType.SUB);
		// criteria.put("word", "user");
		long count = actionDao.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("page", "User");
		// criteria.put("actionName", "userAdd");
		// criteria.put("actionType", ActionType.SUB);
		// criteria.put("word", "user");
		long count = actionDao.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("page", "User");
		// criteria.put("actionName", "userAdd");
		// criteria.put("actionType", ActionType.SUB);
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "page");
		criteria.put("orderAs", "desc");
		List<ActionBean> results = actionDao.selectList(criteria, FetchMode.LAZY);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("page", "User");
		// criteria.put("actionName", "userAdd");
		// criteria.put("actionType", ActionType.SUB);
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "page");
		criteria.put("orderAs", "desc");
		List<ActionBean> results = actionDao.selectList(criteria, FetchMode.EAGER);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException {
		ActionBean action = actionDao.select(1, FetchMode.LAZY);
		Assert.assertNotNull(action);
		testLogger.info("Action ==> " + action);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException {
		ActionBean action = actionDao.select(1, FetchMode.EAGER);
		Assert.assertNotNull(action);
		testLogger.info("Action ==> " + action);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		ActionBean action = actionDao.select(criteria, FetchMode.LAZY);
		Assert.assertNotNull(action);
		testLogger.info("Action ==> " + action);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		ActionBean action = actionDao.select(criteria, FetchMode.EAGER);
		Assert.assertNotNull(action);
		testLogger.info("Action ==> " + action);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllPageNames() throws Exception {
		List<String> pageNames = actionDao.selectAllPageNames();
		Assert.assertNotNull(pageNames);
		Assert.assertEquals(true, pageNames.size() > 0);
		showEntriesOfCollection(pageNames);
	}

}