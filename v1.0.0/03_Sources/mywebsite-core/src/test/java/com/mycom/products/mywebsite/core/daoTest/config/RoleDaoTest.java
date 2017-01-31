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

import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.config.RoleDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.JoinedSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class RoleDaoTest extends BaseDaoTest implements JoinedSelectableDaoTest {
	@Autowired
	private RoleDao roleDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<RoleBean> results = roleDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<RoleBean> results = roleDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = roleDao.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		long count = roleDao.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		// criteria.put("actionId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		long count = roleDao.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "name");
		criteria.put("orderAs", "desc");
		List<RoleBean> results = roleDao.selectList(criteria, FetchMode.LAZY);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		// criteria.put("actionId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "name");
		criteria.put("orderAs", "desc");
		List<RoleBean> results = roleDao.selectList(criteria, FetchMode.EAGER);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException {
		RoleBean role = roleDao.select(1, FetchMode.LAZY);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException {
		RoleBean role = roleDao.select(1, FetchMode.EAGER);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		RoleBean role = roleDao.select(criteria, FetchMode.LAZY);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		RoleBean role = roleDao.select(criteria, FetchMode.EAGER);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}
}
