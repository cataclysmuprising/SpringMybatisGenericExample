package com.mycom.products.mywebsite.core.service.config;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.master.SettingService;

@Service
public class GeneralService implements ApplicationListener<ContextRefreshedEvent> {
	private Logger coreLogger = Logger.getLogger("CoreLogger");
	private Logger errorLogger = Logger.getLogger("ErrorLogger");
	@Autowired
	private SettingService settingService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		String uploadPath = getUploadPath();
		if (uploadPath != null) {
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	public String getUploadPath() {
		coreLogger.info("*** Transaction start for fetching 'File Upload Directory' information.");
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("name", "UploadPath");
		try {
			List<SettingBean> settings = settingService.getByCriteria(criteria);
			if (settings != null && settings.size() > 0) {
				return settings.get(0).getValue();
			}
		} catch (BusinessException e) {
			errorLogger.error("Retrieving 'File Upload Directory' process has failed.");
			errorLogger.error(e.getMessage(), e);
			errorLogger.info(BaseBean.LOGBREAKER);
		}
		return null;
	}
}
