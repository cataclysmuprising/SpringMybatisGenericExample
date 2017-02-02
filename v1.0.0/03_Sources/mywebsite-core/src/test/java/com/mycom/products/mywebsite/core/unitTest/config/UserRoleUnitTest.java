/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.config;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.TestBase;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.config.UserRoleDao;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.unitTest.base.XGenericUnitTest;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class UserRoleUnitTest extends TestBase implements XGenericUnitTest {
	// --------------------------------- for fetching
	@Autowired
	private UserRoleDao userRoleDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<UserRoleBean> results = userRoleDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<UserRoleBean> results = userRoleDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = userRoleDao.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey1() throws DAOException {
		List<Integer> results = userRoleDao.selectByKey1(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey2() throws DAOException {
		List<Integer> results = userRoleDao.selectByKey2(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByKeysWithLazyMode() throws DAOException {
		UserRoleBean result = userRoleDao.select(1, 1, FetchMode.LAZY);
		testLogger.info("UserRole ==> " + result);
		Assert.assertNotNull(result);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByKeysWithEagerMode() throws DAOException {
		UserRoleBean result = userRoleDao.select(1, 1, FetchMode.EAGER);
		testLogger.info("UserRole ==> " + result);
		Assert.assertNotNull(result);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<UserRoleBean> results = userRoleDao.selectList(criteria, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<UserRoleBean> results = userRoleDao.selectList(criteria, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	// --------------------------------- for insertion
	@Override
	public void testInsert() throws DAOException, DuplicatedEntryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testInsertList() throws DAOException, DuplicatedEntryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testInsertWithRelatedKeys() throws DAOException, DuplicatedEntryException {
		// TODO Auto-generated method stub

	}
}
