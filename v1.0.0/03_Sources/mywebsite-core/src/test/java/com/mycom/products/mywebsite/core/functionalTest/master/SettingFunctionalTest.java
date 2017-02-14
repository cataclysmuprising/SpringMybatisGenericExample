/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.functionalTest.master;

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
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.master.api.SettingService;

public class SettingFunctionalTest extends TestBase {
	@Autowired
	private SettingService settingService;
	private Logger testLogger = Logger.getLogger(this.getClass());
	// --------------------------------- for fetching

	@Test(groups = { "fetch" })
	public void testSelectAll() throws BusinessException {
		List<SettingBean> results = settingService.selectList(null);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws BusinessException {
		long count = settingService.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "UploadPath");
		// criteria.put("type", "String");
		// criteria.put("group", "Application");
		// criteria.put("subGroup", "File Directory");
		// criteria.put("value", "MMK");
		// criteria.put("word", "currency");
		long count = settingService.selectCounts(criteria);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "UploadPath");
		// criteria.put("type", "String");
		// criteria.put("group", "Application");
		// criteria.put("subGroup", "File Directory");
		// criteria.put("value", "MMK");
		// criteria.put("word", "currency");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "group");
		criteria.put("orderAs", "desc");
		List<SettingBean> results = settingService.selectList(criteria);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKey() throws BusinessException {
		SettingBean setting = settingService.select(1);
		Assert.assertNotNull(setting);
		testLogger.info("Setting ==> " + setting);
	}

	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		SettingBean setting = settingService.select(criteria);
		Assert.assertNotNull(setting);
		testLogger.info("Setting ==> " + setting);
	}

	// --------------------------------- for insertion

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws DuplicatedEntryException, BusinessException {
		SettingBean setting = new SettingBean();
		setting.setName("test_name");
		setting.setValue("test_value");
		setting.setType("test_type");
		setting.setGroup("test_group");
		setting.setSubGroup("test_subgroup");
		long lastInsertedId = settingService.insert(setting, TEST_CREATE_USER_ID);
		Assert.assertEquals(true, lastInsertedId > 0);
		testLogger.info("Last inserted ID = " + lastInsertedId);
	}

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws DuplicatedEntryException, BusinessException {
		List<SettingBean> records = new ArrayList<>();
		SettingBean record1 = new SettingBean();
		record1.setName("test_name");
		record1.setValue("test_value");
		record1.setType("test_type");
		record1.setGroup("test_group");
		record1.setSubGroup("test_subgroup");
		records.add(record1);

		SettingBean record2 = new SettingBean();
		record2.setName("test_name2");
		record2.setValue("test_value2");
		record2.setType("test_type2");
		record2.setGroup("test_group2");
		record2.setSubGroup("test_subgroup2");
		records.add(record2);
		settingService.insert(records, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testSingleRecordUpdate() throws DuplicatedEntryException, BusinessException {
		SettingBean setting = new SettingBean();
		setting.setId(1);
		setting.setName("test_name");
		setting.setValue("test_value");
		setting.setType("test_type");
		setting.setGroup("test_group");
		setting.setSubGroup("test_subgroup");
		long totalEffectedRows = settingService.update(setting, TEST_UPDATE_USER_ID);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMultiRecordsUpdate() throws DuplicatedEntryException, BusinessException {
		List<SettingBean> records = new ArrayList<>();
		SettingBean record1 = new SettingBean();
		record1.setId(1);
		record1.setName("test_name");
		record1.setValue("test_value");
		record1.setType("test_type");
		record1.setGroup("test_group");
		record1.setSubGroup("test_subgroup");
		records.add(record1);

		SettingBean record2 = new SettingBean();
		record2.setId(2);
		record2.setName("test_name2");
		record2.setValue("test_value2");
		record2.setType("test_type2");
		record2.setGroup("test_group2");
		record2.setSubGroup("test_subgroup2");
		records.add(record2);
		settingService.update(records, TEST_UPDATE_USER_ID);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testUpdateByCriteria() throws DuplicatedEntryException, BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 280);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "UploadPath");
		// criteria.put("type", "String");
		// criteria.put("group", "Application");
		// criteria.put("subGroup", "File Directory");
		// criteria.put("value", "MMK");

		HashMap<String, Object> updateItems = new HashMap<>();
		updateItems.put("name", "test_name");
		updateItems.put("value", "test_value");
		updateItems.put("type", "test_type");
		updateItems.put("group", "test_group");
		updateItems.put("subGroup", "test_subgroup");

		settingService.update(criteria, updateItems, TEST_UPDATE_USER_ID);
	}

	// --------------------------------- for delete

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByPrimaryKey() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		long totalEffectedRows = settingService.delete(1, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByCriteria() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 280);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "UploadPath");
		// criteria.put("type", "String");
		// criteria.put("group", "Application");
		// criteria.put("subGroup", "File Directory");
		// criteria.put("value", "MMK");

		long totalEffectedRows = settingService.delete(criteria, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}
}
