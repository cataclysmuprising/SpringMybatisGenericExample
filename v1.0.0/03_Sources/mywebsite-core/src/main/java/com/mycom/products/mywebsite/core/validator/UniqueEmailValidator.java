package com.mycom.products.mywebsite.core.validator;

import java.util.HashMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycom.products.mywebsite.core.annotation.UniqueEmail;
import com.mycom.products.mywebsite.core.service.config.api.UserService;
import com.mycom.products.mywebsite.core.util.FetchMode;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private String columnName;
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
	this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	try {
	    HashMap<String, Object> criteria = new HashMap<>();
	    criteria.put(columnName, value);
	    return userService.selectCounts(criteria, FetchMode.LAZY) == 0;
	} catch (Exception e) {
	    // ignore
	}
	return false;
    }
}
