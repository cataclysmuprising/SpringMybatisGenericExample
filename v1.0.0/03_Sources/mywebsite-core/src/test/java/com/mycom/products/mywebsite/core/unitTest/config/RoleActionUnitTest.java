/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.TestBase;
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.unitTest.base.XGenericUnitTest;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class RoleActionUnitTest extends TestBase implements XGenericUnitTest {
	@Autowired
	private RoleActionDao roleActionDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	// --------------------------------- for fetching
	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<RoleActionBean> results = roleActionDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<RoleActionBean> results = roleActionDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = roleActionDao.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey1() throws DAOException {
		List<Integer> results = roleActionDao.selectByKey1(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey2() throws DAOException {
		List<Integer> results = roleActionDao.selectByKey2(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByKeysWithLazyMode() throws DAOException {
		RoleActionBean result = roleActionDao.select(1, 1, FetchMode.LAZY);
		testLogger.info("RoleAction ==> " + result);
		Assert.assertNotNull(result);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByKeysWithEagerMode() throws DAOException {
		RoleActionBean result = roleActionDao.select(1, 1, FetchMode.EAGER);
		testLogger.info("RoleAction ==> " + result);
		Assert.assertNotNull(result);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		criteria.put("actionId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<RoleActionBean> results = roleActionDao.selectList(criteria, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		criteria.put("actionId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<RoleActionBean> results = roleActionDao.selectList(criteria, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	// --------------------------------- for insertion
	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsert() throws DAOException, DuplicatedEntryException {
		RoleActionBean roleAction = new RoleActionBean();
		roleAction.setRoleId(1001);
		roleAction.setActionId(2002);
		roleActionDao.insert(roleAction, TEST_CREATE_USER_ID);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertList() throws DAOException, DuplicatedEntryException {
		List<RoleActionBean> records = new ArrayList<>();
		RoleActionBean record1 = new RoleActionBean();
		record1.setRoleId(1001);
		record1.setActionId(2002);
		records.add(record1);

		RoleActionBean record2 = new RoleActionBean();
		record2.setRoleId(3003);
		record2.setActionId(4004);
		records.add(record2);
		roleActionDao.insert(records, TEST_CREATE_USER_ID);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertWithRelatedKeys() throws DAOException, DuplicatedEntryException {
		roleActionDao.insert(5005, 6006, TEST_CREATE_USER_ID);
	}
}