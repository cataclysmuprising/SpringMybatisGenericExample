/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.master;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.dao.master.StaticContentDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.StandAloneSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;

public class StaticContentDaoTest extends BaseDaoTest implements StandAloneSelectableDaoTest {
	@Autowired
	private StaticContentDao staticContentDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

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

}
