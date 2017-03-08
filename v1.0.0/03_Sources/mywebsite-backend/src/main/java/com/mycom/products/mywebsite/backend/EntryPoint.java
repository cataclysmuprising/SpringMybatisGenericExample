package com.mycom.products.mywebsite.backend;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint implements InitializingBean {
    private static String projectVersion;

    public static final String MODULE_NAME = "back-end";

    @Override
    public void afterPropertiesSet() throws Exception {
	projectVersion = getVersion();
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
}
