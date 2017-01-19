package com.mycom.products.mywebsite.core.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.BaseTestService;
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.master.SettingDao;
import com.mycom.products.mywebsite.core.service.master.SettingService;

public class SettingServiceTest extends BaseTestService {
	@Autowired
	private SettingService settingService;

	@Autowired
	private SettingDao settingDao;

	@Test
	public void testInsert() throws Exception {
		SettingBean setting = new SettingBean();
		setting.setName("dummyName");
		setting.setValue("dummyValue");
		setting.setType("dummyType");
		setting.setGroup("dummyGroup");
		setting.setSubGroup("dummySubGroup");
		int lastInsertedId = settingDao.insert(setting, 111);
		logger.info("Testing insert process has Successful ! Last inserted Id = " + lastInsertedId);
	}

	@Test
	public void testUpdate() throws Exception {
		SettingBean setting = new SettingBean();
		setting.setId(299999);
		setting.setName("updateName");
		setting.setValue("updateValue");
		setting.setType("updateType");
		setting.setGroup("updateGroup");
		setting.setSubGroup("updateSubGroup");
		int effectedRows = settingService.modify(setting, 222);
		logger.info("Testing update process has Successful ! Total effected rows = " + effectedRows);
	}

	@Test
	public void testDelete() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("id", 1);
		int effectedRows = settingService.remove(criteria, 333);
		logger.info("Total effected rows after delete " + effectedRows);
	}

	@Test(groups = { "fetch" })
	public void testSelectById() throws Exception {
		SettingBean setting = settingService.getById(2);
		logger.info("Setting = " + setting);
	}

	@Test(groups = { "fetch" })
	public void testSelectByCriteria() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		List<SettingBean> settings = settingService.getByCriteria(criteria);
		for (SettingBean setting : settings) {
			logger.info("Setting = " + setting);
		}
	}

	@Test(groups = { "fetch" })
	public void testSelectCountByCriteria() throws Exception {
		Map<String, Object> criteria = new HashMap<>();
		int count = settingService.getCountByCriteria(criteria);
		logger.info("Total record counts = " + count);
	}

}