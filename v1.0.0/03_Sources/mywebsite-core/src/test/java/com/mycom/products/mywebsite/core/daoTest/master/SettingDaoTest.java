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

import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.master.SettingDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.StandAloneSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;

public class SettingDaoTest extends BaseDaoTest implements StandAloneSelectableDaoTest {
	@Autowired
	private SettingDao settingDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAll() throws DAOException {
		List<SettingBean> results = settingDao.selectList(null);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = settingDao.selectCounts(null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("name", "UploadPath");
		// criteria.put("type", "String");
		// criteria.put("group", "Application");
		// criteria.put("subGroup", "File Directory");
		// criteria.put("value", "MMK");
		// criteria.put("word", "currency");
		long count = settingDao.selectCounts(criteria);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteria() throws DAOException {
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
		List<SettingBean> results = settingDao.selectList(criteria);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKey() throws DAOException {
		SettingBean setting = settingDao.select(1);
		Assert.assertNotNull(setting);
		testLogger.info("Setting ==> " + setting);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		SettingBean setting = settingDao.select(criteria);
		Assert.assertNotNull(setting);
		testLogger.info("Setting ==> " + setting);
	}
}
