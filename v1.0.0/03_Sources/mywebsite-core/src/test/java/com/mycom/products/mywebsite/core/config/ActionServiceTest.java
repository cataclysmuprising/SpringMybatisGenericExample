package com.mycom.products.mywebsite.core.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.BaseTestService;
import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.dao.config.ActionDao;

public class ActionServiceTest extends BaseTestService {
	@Autowired
	private ActionDao actionService;

	@Test(groups = { "fetch" })
	public void testSelectAll() throws Exception {
		List<ActionBean> results = actionService.selectMultiRecords(null);
		showEntriesOfCollection(results);
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
	}

}