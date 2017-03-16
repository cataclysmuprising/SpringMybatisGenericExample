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
	}
    }

    // http://stackoverflow.com/questions/15111420/how-to-check-if-a-string-contains-only-digits-in-java#15111450
    public static void validateIsValidPositiveNumbers(FieldValidator fieldValidator) {
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
	}
    }

    public static void validateIsValidAlphaBatics(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[a-zA-Z]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	}
    }

    public static void validateIsValidAlphaNumerics(FieldValidator fieldValidator) {
	validateIsEmpty(fieldValidator);
	String fieldName = fieldValidator.getFieldName();
	String displayName = fieldValidator.getDisplayName();
	if (fieldValidator.getTarget() instanceof String) {
	    String target = (String) fieldValidator.getTarget();
	    Errors errors = fieldValidator.getErrors();
	    if (!Pattern.matches("^[\\w]+$", target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
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
	    String emailPattern = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\&lt;(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\&gt;(?:(?:\\r\\n)?[ \\t])*)|(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\&lt;(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\&gt;(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\&lt;(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()&lt;&gt;@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()&lt;&gt;@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\&gt;(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";
	    if (!Pattern.matches(emailPattern, target)) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
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
	    if (!Pattern.matches("[\\-\\+]?^[0-9]+(\\.[0-9]{1," + precision + "})?$", target.toString())) {
		errors.rejectValue(fieldName, "",
			getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
	    }
	} else if (fieldValidator.getTarget() instanceof Float) {
	    if (fieldValidator.getTarget() instanceof Double) {
		Float target = (Float) fieldValidator.getTarget();
		if (!Pattern.matches("[\\-\\+]?^[0-9]+(\\.[0-9]{1," + precision + "})?$", target.toString())) {
		    errors.rejectValue(fieldName, "",
			    getLocalizedMessage("Validation.common.Field.InvalidNumber", new Object[] { displayName }));
		}
	    }
	}
    }

    public static String getLocalizedMessage(String code, Object... object) {
	return messageSource.getMessage(code, object, Locale.ENGLISH);
    }
}
