package com.mycom.products.mywebsite.backend.validator;

import org.springframework.validation.Errors;

public class FieldValidator {
    private String fieldName;
    private String displayName;
    private Object target;
    private Errors errors;

    public FieldValidator(String fieldName, String displayName, Object target, Errors errors) {
	this.fieldName = fieldName;
	this.displayName = displayName;
	this.target = target;
	this.errors = errors;
    }

    public Object getTarget() {
	return target;
    }

    public void setTarget(Object target) {
	this.target = target;
    }

    public Errors getErrors() {
	return errors;
    }

    public void setErrors(Errors errors) {
	this.errors = errors;
    }

    public String getFieldName() {
	return fieldName;
    }

    public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
    }

    public String getDisplayName() {
	return displayName;
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

}
