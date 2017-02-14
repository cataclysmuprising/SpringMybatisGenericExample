/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.functionalTest.config;

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
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.config.api.RoleService;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class RoleFunctionalTest extends TestBase {
	@Autowired
	private RoleService roleService;
	private Logger testLogger = Logger.getLogger(this.getClass());

	// --------------------------------- for fetching
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws BusinessException {
		List<RoleBean> results = roleService.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws BusinessException {
		List<RoleBean> results = roleService.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws BusinessException {
		long count = roleService.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		long count = roleService.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		// criteria.put("actionId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		long count = roleService.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "SUPER_USER");
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "name");
		criteria.put("orderAs", "desc");
		List<RoleBean> results = roleService.selectList(criteria, FetchMode.LAZY);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws BusinessException {
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
		List<RoleBean> results = roleService.selectList(criteria, FetchMode.EAGER);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithLazyMode() throws BusinessException {
		RoleBean role = roleService.select(1, FetchMode.LAZY);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithEagerMode() throws BusinessException {
		RoleBean role = roleService.select(1, FetchMode.EAGER);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		RoleBean role = roleService.select(criteria, FetchMode.LAZY);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		RoleBean role = roleService.select(criteria, FetchMode.EAGER);
		Assert.assertNotNull(role);
		testLogger.info("Role ==> " + role);
	}

	// --------------------------------- for insertion

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws BusinessException, DuplicatedEntryException {
		RoleBean role = new RoleBean();
		role.setName("FINANCE");
		role.setDescription("This role can manage the finicial processes.");
		long lastInsertedId = roleService.insert(role, TEST_CREATE_USER_ID);
		Assert.assertEquals(true, lastInsertedId > 0);
		testLogger.info("Last inserted ID = " + lastInsertedId);
	}

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws BusinessException, DuplicatedEntryException {
		List<RoleBean> records = new ArrayList<>();
		RoleBean record1 = new RoleBean();
		record1.setName("STAFF");
		record1.setDescription("This role aim to own for all staffs.");
		records.add(record1);

		RoleBean record2 = new RoleBean();
		record2.setName("FINANCE");
		record2.setDescription("This role can manage the finicial processes.");
		records.add(record2);
		roleService.insert(records, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testSingleRecordUpdate() throws BusinessException, DuplicatedEntryException {
		RoleBean role = new RoleBean();
		role.setId(1);
		role.setName("FINANCE-ADMIN");
		role.setDescription("This role can manage the finicial processes and can do as well as system administrator.");
		long totalEffectedRows = roleService.update(role, TEST_UPDATE_USER_ID);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMultiRecordsUpdate() throws BusinessException, DuplicatedEntryException {
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
		roleService.update(records, TEST_UPDATE_USER_ID);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testUpdateByCriteria() throws BusinessException, DuplicatedEntryException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		criteria.put("ids", new Integer[] { 1, 2, 3 });
		criteria.put("name", "SUPER_USER");

		HashMap<String, Object> updateItems = new HashMap<>();
		updateItems.put("name", "USER");
		updateItems.put("description", "Basic role for every users.");

		roleService.update(criteria, updateItems, TEST_UPDATE_USER_ID);
	}

	// --------------------------------- for delete

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByPrimaryKey() throws BusinessException, ConsistencyViolationException {
		long totalEffectedRows = roleService.delete(1, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByCriteria() throws BusinessException, ConsistencyViolationException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		criteria.put("ids", new Integer[] { 1, 2, 3 });
		criteria.put("name", "SUPER_USER");

		long totalEffectedRows = roleService.delete(criteria, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}
}