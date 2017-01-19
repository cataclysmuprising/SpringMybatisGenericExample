package com.mycom.products.mywebsite.core.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.BaseTestService;
import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;
import com.mycom.products.mywebsite.core.bean.config.RoleBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.config.RoleActionService;
import com.mycom.products.mywebsite.core.service.config.RoleService;

public class RoleServiceTest extends BaseTestService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleActionService roleActionService;

	@Test(groups = { "fetch" })
	public void testSelectAll() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		List<RoleBean> results = roleService.getByCriteria(criteria);

		showEntriesOfCollection(results);

		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectByRoleName() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("name", "SUPER_USER");
		List<RoleBean> results = roleService.getByCriteria(criteria);

		showEntriesOfCollection(results);

		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectByCriteria() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("word", "user");
		List<RoleBean> results = roleService.getByCriteria(criteria);

		showEntriesOfCollection(results);

		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectById() throws Exception {
		RoleBean result = roleService.getById(1);

		logger.info("Role = " + result);

		Assert.assertNotNull(result);
	}

	@Test(groups = { "fetch" })
	public void testSelectAllCount() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		int count = roleService.getCountByCriteria(criteria);

		logger.info("Total record counts = " + count);

		Assert.assertNotSame(count, 0);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountByName() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("name", "ROLE_ADMIN");
		int count = roleService.getCountByCriteria(criteria);

		logger.info("Total record counts = " + count);

		Assert.assertSame(count, 1);
	}

	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("word", "user");
		int count = roleService.getCountByCriteria(criteria);

		logger.info("Total record counts = " + count);

		Assert.assertNotSame(count, 0);
		Assert.assertEquals(true, count > 0);
	}

	@Test(groups = { "insert" })
	public void testInsert_FullSelfFields() throws Exception {
		RoleBean role = new RoleBean();
		role.setName("ROLE_TEST");
		role.setDescription("This is testing role.");
		int lastInsertedId = roleService.add(role, TEST_CREATE_USER_ID);

		logger.info("Testing insert process has Successful ! Last inserted Id = " + lastInsertedId);

		Assert.assertEquals(true, lastInsertedId > 0);
	}

	@Test
	public void testUpdate() throws Exception {
		RoleBean role = new RoleBean();
		role.setId(1);
		role.setName("ROLE_ADMIN");
		int effectedRows = roleService.modify(role, 222);
		logger.info("Testing update process has Successful ! Total effected rows = " + effectedRows);
	}

	@Test
	public void testDelete() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("id", 4);
		int effectedRows = roleService.remove(criteria, 333);
		logger.info("Total effected rows after delete " + effectedRows);
	}

	@Test
	public void testRoleActionFetch() throws BusinessException {
		HashMap<String, Object> criteria = new HashMap<>();
		// criteria.put("roleId", 1);
		List<RoleActionBean> roleActions = roleActionService.getByCriteria(criteria);
		System.err.println("Total records count " + roleActions.size());
		for (RoleActionBean roleAction : roleActions) {
			if (roleAction == null) {
				System.err.println("Error !");
			} else {
				System.err.println(roleAction);
			}
		}
	}

}