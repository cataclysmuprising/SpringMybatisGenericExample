package com.mycom.products.mywebsite.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@ContextConfiguration(locations = { "classpath:spring-test-context.xml" })
public class ValidationTestBase extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    protected Validator validator;

    private Logger logger = Logger.getLogger(this.getClass());

    protected void showValidationErrors(BindingResult errors) {
	if (errors.hasErrors()) {
	    errors.getAllErrors().forEach(item -> {
		logger.info(item.getDefaultMessage());
	    });
	}
    }
}
