/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.dao.config.RoleActionDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class RoleActionDaoTest extends BaseDaoTest {
	@Autowired
	private RoleActionDao roleActionDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Test(groups = { "fetch" })
	public void testSelectAllWithLazyMode() throws DAOException {
		List<RoleActionBean> results = roleActionDao.selectList(null, FetchMode.LAZY);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}
}
