package com.mycom.products.mywebsite.backend.validator;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class BaseValidator implements Validator, MessageSourceAware {
    protected MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
	this.messageSource = messageSource;
    }

    public void validateIsEmpty(FieldValidator fieldValidator) {
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();
	if (target == null) {
	    errors.rejectValue(fieldName, "",
		    getLocalizedMessage("Validation.common.Field.Required", new Object[] { displayName }));
	    return;
	} else if (target instanceof String) {
	    if (((String) target).isEmpty()) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.Required", new Object[] { displayName }));
	    }
	}
    }

    public void validateMinValue(FieldValidator fieldValidator, int value) {
	validateIsEmpty(fieldValidator);

	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();

	if (target instanceof String) {
	    if (target.toString().length() < value) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.Min.String", new Object[] { displayName, value }));
	    }
	} else if (target instanceof Integer) {
	    Integer number = (Integer) target;
	    if (number < value) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.Min.Number", new Object[] { displayName, value }));
	    }
	}
    }

    public void validateMaxValue(FieldValidator fieldValidator, int value) {
	validateIsEmpty(fieldValidator);

	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();

	if (target instanceof String) {
	    if (target.toString().length() > value) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("dation.common.Field.Max.String", new Object[] { displayName, value }));
	    }
	} else if (target instanceof Integer) {
	    Integer number = (Integer) target;
	    if (number > value) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.Max.Number", new Object[] { displayName, value }));
	    }
	}
    }

    public void validateRangeValue(FieldValidator fieldValidator, Number min, Number max) {
	validateIsEmpty(fieldValidator);

	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();
	if (target instanceof String) {
	    if (target.toString().length() < min.intValue() || target.toString().length() > max.intValue()) {
		errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.Range.String",
			new Object[] { displayName, min, max }));
	    }
	} else if (target instanceof Number) {
	    Number number = (Number) target;
	    if (number.doubleValue() < min.doubleValue() || number.doubleValue() > max.doubleValue()) {
		errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.Range.Number",
			new Object[] { displayName, min, max }));
	    }
	}
    }

    protected String getLocalizedMessage(String code, Object... object) {
	return messageSource.getMessage(code, object, Locale.ENGLISH);
    }
}
