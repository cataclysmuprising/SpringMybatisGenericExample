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
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean.FileType;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.service.master.api.StaticContentService;

public class StaticContentFunctionalTest extends TestBase {
	@Autowired
	private StaticContentService staticContentService;
	private Logger testLogger = Logger.getLogger(this.getClass());

	// --------------------------------- for fetching

	@Test(groups = { "fetch" })
	public void testSelectAll() throws BusinessException {
		List<StaticContentBean> results = staticContentService.selectList(null);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws BusinessException {
		long count = staticContentService.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("fileType", FileType.IMAGE);
		// criteria.put("fileName", "super_user.jpg");
		// criteria.put("fileSize", "10KB");
		// criteria.put("word", "avatar");
		long count = staticContentService.selectCounts(criteria);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("fileType", FileType.IMAGE);
		// criteria.put("fileName", "super_user.jpg");
		// criteria.put("fileSize", "10KB");
		// criteria.put("word", "avatar");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "fileName");
		criteria.put("orderAs", "desc");
		List<StaticContentBean> results = staticContentService.selectList(criteria);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKey() throws BusinessException {
		StaticContentBean staticContent = staticContentService.select(1);
		Assert.assertNotNull(staticContent);
		testLogger.info("StaticContent ==> " + staticContent);
	}

	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteria() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		StaticContentBean staticContent = staticContentService.select(criteria);
		Assert.assertNotNull(staticContent);
		testLogger.info("StaticContent ==> " + staticContent);
	}

	// --------------------------------- for insertion

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertSingleRecord() throws DuplicatedEntryException, BusinessException {
		StaticContentBean content = new StaticContentBean();
		content.setFileType(FileType.IMAGE);
		content.setFileName("favicon.ico");
		content.setFileSize("120KB");
		content.setFilePath("D:/web-resources/mywebsite/uploadFiles/avatar/favicon_123.jpg");
		long lastInsertedId = staticContentService.insert(content, TEST_CREATE_USER_ID);
		Assert.assertEquals(true, lastInsertedId > 0);
		testLogger.info("Last inserted ID = " + lastInsertedId);
	}

	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertMultiRecords() throws DuplicatedEntryException, BusinessException {
		List<StaticContentBean> records = new ArrayList<>();
		StaticContentBean record1 = new StaticContentBean();
		record1.setFileType(FileType.IMAGE);
		record1.setFileName("favicon.ico");
		record1.setFileSize("120KB");
		record1.setFilePath("D:/web-resources/mywebsite/uploadFiles/avatar/favicon_123.jpg");
		records.add(record1);

		StaticContentBean record2 = new StaticContentBean();
		record2.setFileType(FileType.PDF);
		record2.setFileName("user_manual.pdf");
		record2.setFileSize("3MB");
		record2.setFilePath("D:/web-resources/mywebsite/uploadFiles/guides/user_manual_321.pdf");
		records.add(record2);
		staticContentService.insert(records, TEST_CREATE_USER_ID);
	}

	// --------------------------------- for update

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testSingleRecordUpdate() throws DuplicatedEntryException, BusinessException {
		StaticContentBean content = new StaticContentBean();
		content.setId(1);
		content.setFileType(FileType.IMAGE);
		content.setFileName("favicon.ico");
		content.setFileSize("120KB");
		content.setFilePath("D:/web-resources/mywebsite/uploadFiles/avatar/favicon_123.jpg");
		long totalEffectedRows = staticContentService.update(content, TEST_UPDATE_USER_ID);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testMultiRecordsUpdate() throws DuplicatedEntryException, BusinessException {
		List<StaticContentBean> records = new ArrayList<>();
		StaticContentBean record1 = new StaticContentBean();
		record1.setId(1);
		record1.setFileType(FileType.IMAGE);
		record1.setFileName("favicon.ico");
		record1.setFileSize("120KB");
		record1.setFilePath("D:/web-resources/mywebsite/uploadFiles/avatar/favicon_123.jpg");
		records.add(record1);

		StaticContentBean record2 = new StaticContentBean();
		record2.setId(1);
		record2.setFileType(FileType.PDF);
		record2.setFileName("user_manual.pdf");
		record2.setFileSize("3MB");
		record2.setFilePath("D:/web-resources/mywebsite/uploadFiles/guides/user_manual_321.pdf");
		records.add(record2);
		staticContentService.update(records, TEST_UPDATE_USER_ID);
	}

	@Test(groups = { "update" })
	@Transactional
	@Rollback(true)
	public void testUpdateByCriteria() throws DuplicatedEntryException, BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("fileType", FileType.IMAGE);
		// criteria.put("fileName", "super_user.jpg");
		// criteria.put("fileSize", "10KB");

		HashMap<String, Object> updateItems = new HashMap<>();
		updateItems.put("fileType", FileType.IMAGE);
		updateItems.put("fileName", "logo.png");
		updateItems.put("fileSize", "50KB");

		staticContentService.update(criteria, updateItems, TEST_UPDATE_USER_ID);
	}

	// --------------------------------- for delete

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByPrimaryKey() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		long totalEffectedRows = staticContentService.delete(1, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

	@Test(groups = { "delete" })
	@Transactional
	@Rollback(true)
	public void testDeleteByCriteria() throws DuplicatedEntryException, ConsistencyViolationException, BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("fileType", FileType.IMAGE);
		// criteria.put("fileName", "super_user.jpg");
		// criteria.put("fileSize", "10KB");

		long totalEffectedRows = staticContentService.delete(criteria, TEST_UPDATE_USER_ID);
		Assert.assertEquals(true, totalEffectedRows > 0);
		testLogger.info("Total effected rows = " + totalEffectedRows);
	}

}
