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
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.dao.config.RoleDao;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.unitTest.base.InsertableUnitTest;
import com.mycom.products.mywebsite.core.unitTest.base.JoinedSelectableUnitTest;
import com.mycom.products.mywebsite.core.unitTest.base.UpdateableUnitTest;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class RoleUnitTest extends TestBase implements JoinedSelectableUnitTest, InsertableUnitTest, UpdateableUnitTest {
	@Autowired
	private RoleDao roleDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	// --------------------------------- for fetching
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

	// --------------------------------- for insertion
	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws DAOException, DuplicatedEntryException {
		RoleBean role = new RoleBean();
		role.setName("FINANCE");
		role.setDescription("This role can manage the finicial processes.");
		long lastInsertedId = roleDao.insert(role, TEST_CREATE_USER_ID);
		Assert.assertEquals(true, lastInsertedId > 0);
		testLogger.info("Last inserted ID = " + lastInsertedId);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws DAOException, DuplicatedEntryException {
		List<RoleBean> records = new ArrayList<>();
		RoleBean record1 = new RoleBean();
		record1.setName("STAFF");
		record1.setDescription("This role aim to own for all staffs.");
		records.add(record1);

		RoleBean record2 = new RoleBean();
		record2.setName("FINANCE");
		record2.setDescription("This role can manage the finicial processes.");
		records.add(record2);
		roleDao.insert(records, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update
	@Override
	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testSingleRecordUpdate() throws DAOException, DuplicatedEntryException {
		RoleBean role = new RoleBean();
		role.setId(1);
		role.setName("FINANCE-ADMIN");
		role.setDescription("This role can manage the finicial processes and can do as well as system administrator.");
		long totalEffectedRows = roleDao.update(role, TEST_UPDATE_USER_ID);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Override
	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMultiRecordsUpdate() throws DAOException, DuplicatedEntryException {
		List<RoleBean> records = new ArrayList<>();
		RoleBean record = new RoleBean();
		record.setId(1);
		record.setName("FINANCE-ADMIN");
		record.setDescription("This role can manage the finicial processes and can do as well as system administrator.");
		records.add(record);

		RoleBean record2 = new RoleBean();
		record2.setId(2);
		record2.setName("USER");
		record2.setDescription("Basic role for every users.");
		records.add(record2);
		roleDao.update(records, TEST_UPDATE_USER_ID);
	}

	@Override
	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testUpdateByCriteria() throws DAOException, DuplicatedEntryException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		criteria.put("ids", new Integer[] { 1, 2, 3 });
		criteria.put("name", "SUPER_USER");

		HashMap<String, Object> updateItems = new HashMap<>();
		updateItems.put("name", "USER");
		updateItems.put("description", "Basic role for every users.");

		roleDao.update(criteria, updateItems, TEST_UPDATE_USER_ID);
	}
}
