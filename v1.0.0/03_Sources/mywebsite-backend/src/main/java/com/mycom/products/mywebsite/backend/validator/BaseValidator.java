/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.backend.validator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class BaseValidator implements Validator, MessageSourceAware {
    protected static MessageSource messageSource;

    public static class UnSupportedValidationCheckException extends RuntimeException {
	private static final long serialVersionUID = 8506363193459502634L;

	public UnSupportedValidationCheckException() {
	    super();
	}

	public UnSupportedValidationCheckException(final String message) {
	    super(message);
	}

	public UnSupportedValidationCheckException(final String message, final Throwable cause) {
	    super(message, cause);
	}

	public UnSupportedValidationCheckException(final Throwable cause) {
	    super(cause);
	}

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
	BaseValidator.messageSource = messageSource;
    }

    public static void validateIsEmpty(FieldValidator fieldValidator) {
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();
	if (target == null) {
	    errors.rejectValue(fieldName, "",
		    getLocalizedMessage("Validation.common.Field.Required", new Object[] { displayName }));
	} else if (target instanceof String) {
	    if (((String) target).isEmpty()) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.Required", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsEqual(String fieldName, FieldValidator fieldValidator1, FieldValidator fieldValidator2,
	    Errors errors) {
	validateIsEmpty(fieldValidator1);
	validateIsEmpty(fieldValidator2);

	if (!fieldValidator1.getTarget().equals(fieldValidator2.getTarget())) {
	    errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.DoNotMatch",
		    new Object[] { fieldValidator1.getDisplayName(), fieldValidator2.getDisplayName() }));
	}
    }

    public static void validateIsValidMinValue(FieldValidator fieldValidator, Number number) {
	validateIsEmpty(fieldValidator);

	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();

	if (target instanceof String) {
	    if (target.toString().length() < number.intValue()) {
		errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.Min.String",
			new Object[] { displayName, number }));
	    }
	} else if (target instanceof Number) {
	    Number inputNumber = (Number) target;
	    if (inputNumber.doubleValue() < number.doubleValue()) {
		errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.Min.Number",
			new Object[] { displayName, number }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidMaxValue(FieldValidator fieldValidator, Number number) {
	validateIsEmpty(fieldValidator);

	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Object target = fieldValidator.getTarget();
	Errors errors = fieldValidator.getErrors();

	if (target instanceof String) {
	    if (target.toString().length() > number.intValue()) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("dation.common.Field.Max.String", new Object[] { displayName, number }));
	    }
	} else if (target instanceof Number) {
	    Number inputNumber = (Number) target;
	    if (inputNumber.doubleValue() > number.doubleValue()) {
		errors.rejectValue(fieldName, "", getLocalizedMessage("Validation.common.Field.Max.Number",
			new Object[] { displayName, number }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidRangeValue(FieldValidator fieldValidator, Number min, Number max) {
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
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidDigits(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("[\\-\\+]?\\d+", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // http://stackoverflow.com/questions/15111420/how-to-check-if-a-string-contains-only-digits-in-java#15111450
    public static void validateIsValidUnSignDigits(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("\\d+", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidAlphaBatics(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[a-zA-Z_ ]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // include space and underscore
    public static void validateIsValidAlphaNumerics(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[a-zA-Z_0-9 ]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // to validate for sql queries
    public static void validateIsValidQueryString(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[a-zA-Z_0-9 \\/\\-\\.\\@]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidCapitalLetters(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[A-Z]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static void validateIsValidSmallLetters(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[a-z]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // http://stackoverflow.com/questions/15805555/java-regex-to-validate-full-name-allow-only-spaces-and-letters#15806080
    public static void validateIsValidUnicodeCharacters(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[\\p{L} .'-]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // http://stackoverflow.com/questions/8204680/java-regex-email/13013056#13013056
    public static void validateIsValidEmail(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	    Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	    if (!pattern.matcher(target).find()) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // http://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java#163410
    public static void validateIsValidURL(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    try {
		new URL(target);
	    } catch (MalformedURLException e) {
		Errors errors = fieldValidator.getErrors();
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    // http://stackoverflow.com/questions/308122/simple-regular-expression-for-a-decimal-with-a-precision-of-2#308216
    public static void validateIsValidFloatingPointNumbers(FieldValidator fieldValidator, int precision) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	Errors errors = fieldValidator.getErrors();
	if (fieldValidator.getTarget() instanceof Double) {
	    Double target = (Double) fieldValidator.getTarget();
	    if (!Pattern.matches("[\\-\\+]?[0-9]+(\\.[0-9]{1," + precision + "})?$", target.toString())) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else if (fieldValidator.getTarget() instanceof Float) {
	    if (fieldValidator.getTarget() instanceof Double) {
		Float target = (Float) fieldValidator.getTarget();
		if (!Pattern.matches("[\\-\\+]?[0-9]+(\\.[0-9]{1," + precision + "})?$", target.toString())) {
		    errors.rejectValue(fieldName, "",
			    getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
		}
	    }
	} else {
	    throw new UnSupportedValidationCheckException();
	}
    }

    public static String getLocalizedMessage(String code, Object... object) {
	return messageSource.getMessage(code, object, Locale.ENGLISH);
    }
}
