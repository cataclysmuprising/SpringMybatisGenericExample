package com.mycom.products.mywebsite.backend;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.service.master.api.SettingService;

@Component
public class EntryPoint implements InitializingBean {
    @Autowired
    private SettingService settingService;
    public static final String MODULE_NAME = "back-end";
    private static String projectVersion;
    private static String uploadPath;
    private Logger appLogger = Logger.getLogger("AppLogger");
    private Logger errorLogger = Logger.getLogger("ErrorLogger");

    @Override
    public void afterPropertiesSet() throws Exception {
	initUploadPath();
	projectVersion = getVersion();
	if (uploadPath != null) {
	    File file = new File(uploadPath);
	    if (!file.exists()) {
		file.mkdirs();
	    }
	}
    }

    public static String getUploadPath() {
	return uploadPath;
    }

    public static String getProjectVersion() {
	return projectVersion;
    }

    private String getVersion() {
	String path = "/project.properties";
	InputStream stream = getClass().getResourceAsStream(path);
	if (stream == null)
	    return "UNKNOWN";
	Properties props = new Properties();
	try {
	    props.load(stream);
	    stream.close();
	    return (String) props.get("version");
	} catch (IOException e) {
	    return "UNKNOWN";
	}
    }

    private void initUploadPath() {
	appLogger.info("*** Transaction start for fetching 'File Upload Directory' information.");
	HashMap<String, Object> criteria = new HashMap<>();
	criteria.put("name", "UploadPath");
	try {
	    SettingBean setting = settingService.select(criteria);
	    if (setting != null) {
		uploadPath = setting.getValue();
	    }
	} catch (BusinessException e) {
	    errorLogger.error("Retrieving 'File Upload Directory' process has failed.");
	    errorLogger.error(e.getMessage(), e);
	    errorLogger.info(BaseBean.LOGBREAKER);
	}
    }
}
