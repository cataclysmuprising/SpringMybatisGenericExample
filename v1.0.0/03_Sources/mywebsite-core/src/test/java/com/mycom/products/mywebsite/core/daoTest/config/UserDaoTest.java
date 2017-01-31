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

import com.mycom.products.mywebsite.core.bean.config.UserBean;
import com.mycom.products.mywebsite.core.dao.config.UserDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.JoinedSelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class UserDaoTest extends BaseDaoTest implements JoinedSelectableDaoTest {
	@Autowired
	private UserDao userDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<UserBean> results = userDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllWithEagerMode() throws DAOException {
		List<UserBean> results = userDao.selectList(null, FetchMode.EAGER);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws DAOException {
		long count = userDao.selectCounts(null, null);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithLazyCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("loginId", "super-user");
		// criteria.put("name", "Super User");
		// criteria.put("email", "superuser@gmail.com");
		// criteria.put("nrc", "12/KMY(N)123455");
		// criteria.put("phone", "09779988530");
		// criteria.put("gender", Gender.MALE);
		// criteria.put("word", "user");
		long count = userDao.selectCounts(criteria, FetchMode.LAZY);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectCountWithEagerCriteria() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		// criteria.put("roleName", "SUPER_USER");
		// criteria.put("contentId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("loginId", "super-user");
		// criteria.put("name", "Super User");
		// criteria.put("email", "superuser@gmail.com");
		// criteria.put("nrc", "12/KMY(N)123455");
		// criteria.put("phone", "09779988530");
		// criteria.put("gender", Gender.MALE);
		// criteria.put("word", "user");
		long count = userDao.selectCounts(criteria, FetchMode.EAGER);
		testLogger.info("Total counts ==> " + count);
		Assert.assertEquals(true, count > 0);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("loginId", "super-user");
		// criteria.put("name", "Super User");
		// criteria.put("email", "superuser@gmail.com");
		// criteria.put("nrc", "12/KMY(N)123455");
		// criteria.put("phone", "09-000000001");
		// criteria.put("gender", Gender.MALE);
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "loginDate");
		criteria.put("orderAs", "desc");
		List<UserBean> results = userDao.selectList(criteria, FetchMode.LAZY);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("roleId", 1);
		// criteria.put("roleName", "SUPER_USER");
		// criteria.put("contentId", 1);
		// criteria.put("id", 1);
		// criteria.put("ids", Arrays.asList(new Integer[] { 1, 2, 3 }));
		// criteria.put("loginId", "super-user");
		// criteria.put("name", "Super User");
		// criteria.put("email", "superuser@gmail.com");
		// criteria.put("nrc", "12/KMY(N)123455");
		// criteria.put("phone", "09-000000001");
		// criteria.put("gender", Gender.MALE);
		// criteria.put("word", "user");
		criteria.put("offset", 0);
		criteria.put("limit", 5);
		criteria.put("orderBy", "loginDate");
		criteria.put("orderAs", "desc");
		List<UserBean> results = userDao.selectList(criteria, FetchMode.EAGER);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		showEntriesOfCollection(results);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException {
		UserBean user = userDao.select(1, FetchMode.LAZY);
		Assert.assertNotNull(user);
		testLogger.info("User ==> " + user);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException {
		UserBean user = userDao.select(1, FetchMode.EAGER);
		Assert.assertNotNull(user);
		testLogger.info("User ==> " + user);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		UserBean user = userDao.select(criteria, FetchMode.LAZY);
		Assert.assertNotNull(user);
		testLogger.info("User ==> " + user);
	}

	@Override
	@Test(groups = { "fetch" })
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException {
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		UserBean user = userDao.select(criteria, FetchMode.EAGER);
		Assert.assertNotNull(user);
		testLogger.info("User ==> " + user);
	}

}
