/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.master;

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
import com.mycom.products.mywebsite.core.dao.master.StaticContentDao;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.unitTest.base.InsertableUnitTest;
import com.mycom.products.mywebsite.core.unitTest.base.StandAloneSelectableUnitTest;

public class StaticContentUnitTest extends TestBase implements StandAloneSelectableUnitTest, InsertableUnitTest {
	@Autowired
	private StaticContentDao staticContentDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	// --------------------------------- for fetching
	@Override
	@Test(groups = { "fetch" })
	public void testSelectAll() throws DAOException {
		List<StaticContentBean> results = staticContentDao.selectList(null);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = staticContentDao.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("fileType", FileType.IMAGE);
		// criteria.put("fileName", "super_user.jpg");
		// criteria.put("fileSize", "10KB");
		// criteria.put("word", "avatar");
		long count = staticContentDao.selectCounts(criteria);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteria() throws DAOException {
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
		List<StaticContentBean> results = staticContentDao.selectList(criteria);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKey() throws DAOException {
		StaticContentBean staticContent = staticContentDao.select(1);
		Assert.assertNotNull(staticContent);
		testLogger.info("StaticContent ==> " + staticContent);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		StaticContentBean staticContent = staticContentDao.select(criteria);
		Assert.assertNotNull(staticContent);
		testLogger.info("StaticContent ==> " + staticContent);
	}

	// --------------------------------- for insertion
	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsert() throws DAOException, DuplicatedEntryException {
		StaticContentBean content = new StaticContentBean();
		content.setFileType(FileType.IMAGE);
		content.setFileName("favicon.ico");
		content.setFileSize("120KB");
		content.setFilePath("D:/web-resources/mywebsite/uploadFiles/avatar/favicon_123.jpg");
		long lastInsertedId = staticContentDao.insert(content, TEST_CREATE_USER_ID);
		Assert.assertEquals(true, lastInsertedId > 0);
		testLogger.info("Last inserted ID = " + lastInsertedId);
	}

	@Override
	@Test(groups = { "insert" })
	@Transactional
	@Rollback(true)
	public void testInsertList() throws DAOException, DuplicatedEntryException {
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
		staticContentDao.insert(records, TEST_CREATE_USER_ID);
	}

}
