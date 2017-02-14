/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.functionalTest.config;

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
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.config.api.UserRoleService;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class UserRoleFunctionalTest extends TestBase {

	@Autowired
	private UserRoleService userRoleService;
	private Logger testLogger = Logger.getLogger(this.getClass());
	// --------------------------------- for fetching

	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws BusinessException {
		List<UserRoleBean> results = userRoleService.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws BusinessException {
		List<UserRoleBean> results = userRoleService.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws BusinessException {
		long count = userRoleService.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey1() throws BusinessException {
		List<Long> results = userRoleService.selectByKey1(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectRelatedKeysByKey2() throws BusinessException {
		List<Long> results = userRoleService.selectByKey2(1);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectByKeysWithLazyMode() throws BusinessException {
		UserRoleBean result = userRoleService.select(1, 1, FetchMode.LAZY);
		testLogger.info("UserRole ==> " + result);
		Assert.assertNotNull(result);
	}

	@Test(groups = { "fetch" })
	public void testSelectByKeysWithEagerMode() throws BusinessException {
		UserRoleBean result = userRoleService.select(1, 1, FetchMode.EAGER);
		testLogger.info("UserRole ==> " + result);
		Assert.assertNotNull(result);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<UserRoleBean> results = userRoleService.selectList(criteria, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "roleId");
		criteria.put("orderAs", "desc");
		List<UserRoleBean> results = userRoleService.selectList(criteria, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	// --------------------------------- for insertion

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws DuplicatedEntryException, BusinessException {
		UserRoleBean userRole = new UserRoleBean();
		userRole.setUserId(1001);
		userRole.setRoleId(2002);
		userRoleService.insert(userRole, TEST_CREATE_USER_ID);
	}

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws DuplicatedEntryException, BusinessException {
		List<UserRoleBean> records = new ArrayList<>();
		UserRoleBean record1 = new UserRoleBean();
		record1.setUserId(1001);
		record1.setRoleId(2002);
		records.add(record1);

		UserRoleBean record2 = new UserRoleBean();
		record2.setUserId(3003);
		record2.setRoleId(4004);
		records.add(record2);
		userRoleService.insert(records, TEST_CREATE_USER_ID);
	}

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertWithKeys() throws DuplicatedEntryException, BusinessException {
		userRoleService.insert(5005, 6006, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMerge() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		userRoleService.merge(1, Arrays.asList(new Long[] { 2l, 3l, 4l }), TEST_UPDATE_USER_ID);

	}

	// --------------------------------- for delete

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByKeys() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		long totalEffectedRows = userRoleService.delete(1, 1, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);

	}

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByCriteria() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("userId", 1);
		criteria.put("roleId", 1);
		long totalEffectedRows = userRoleService.delete(criteria, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);

	}
}
