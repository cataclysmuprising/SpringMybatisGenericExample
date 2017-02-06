/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.TestBase;
import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;
import com.mycom.products.mywebsite.core.dao.config.UserRoleDao;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
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
		List<Long> results = userRoleDao.selectByKey1(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey2() throws DAOException {
		List<Long> results = userRoleDao.selectByKey2(1);
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
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws DAOException, DuplicatedEntryException {
		UserRoleBean userRole = new UserRoleBean();
		userRole.setUserId(1001);
		userRole.setRoleId(2002);
		userRoleDao.insert(userRole, TEST_CREATE_USER_ID);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws DAOException, DuplicatedEntryException {
		List<UserRoleBean> records = new ArrayList<>();
		UserRoleBean record1 = new UserRoleBean();
		record1.setUserId(1001);
		record1.setRoleId(2002);
		records.add(record1);

		UserRoleBean record2 = new UserRoleBean();
		record2.setUserId(3003);
		record2.setRoleId(4004);
		records.add(record2);
		userRoleDao.insert(records, TEST_CREATE_USER_ID);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertWithKeys() throws DAOException, DuplicatedEntryException {
		userRoleDao.insert(5005, 6006, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update
	@Override
	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMerge() throws DAOException, DuplicatedEntryException, ConsistencyViolationException {
		userRoleDao.merge(1, Arrays.asList(new Long[] { 2l, 3l, 4l }), TEST_UPDATE_USER_ID);

	}

	// --------------------------------- for delete
	@Override
	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByKeys() throws DAOException, DuplicatedEntryException, ConsistencyViolationException {
		long totalEffectedRows = userRoleDao.delete(1, 1, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);

	}

	@Override
	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByCriteria() throws DAOException, DuplicatedEntryException, ConsistencyViolationException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		long totalEffectedRows = userRoleDao.delete(criteria, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);

	}
}
