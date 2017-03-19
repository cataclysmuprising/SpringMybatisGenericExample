/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.products.springMybatisGenericExample.core.bean.master.SettingBean;
import com.mycom.products.springMybatisGenericExample.core.dao.master.SettingDao;
import com.mycom.products.springMybatisGenericExample.core.service.base.StandAloneServiceImpl;
import com.mycom.products.springMybatisGenericExample.core.service.master.api.SettingService;

@Service
public class SettingServiceImpl extends StandAloneServiceImpl<SettingBean> implements SettingService {

	@Autowired
	public SettingServiceImpl(SettingDao settingDao) {
		super(settingDao, settingDao);
	}

}
