/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.config;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.SelectableDao.FetchMode;
import com.mycom.products.mywebsite.core.dao.config.ActionDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;

public class ActionDaoTest extends BaseDaoTest {
	@Autowired
	private ActionDao actionDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws Exception {
		List<ActionBean> results = actionDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws Exception {
		List<ActionBean> results = actionDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws Exception {
		int count = actionDao.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws Exception {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("page", "User");
		// criteria.put("actionName", "userAdd");
		// criteria.put("actionType", ActionType.SUB);
		// criteria.put("word", "user");
		int count = actionDao.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws Exception {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		int count = actionDao.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

}