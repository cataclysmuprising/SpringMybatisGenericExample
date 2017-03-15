package com.mycom.products.mywebsite.backend.validator.site;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.mycom.products.mywebsite.backend.validator.BaseValidator;
import com.mycom.products.mywebsite.backend.validator.FieldValidator;
import com.mycom.products.mywebsite.core.bean.config.UserBean;

@Component
public class UserValidator extends BaseValidator {
    @Override
    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
	return UserBean.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
	UserBean user = (UserBean) obj;
	// validateMinValue(new FieldValidator("age", "Age", user.getAge(),
	// errors), 18);
	validateRangeValue(new FieldValidator("age", "Age", user.getAge(), errors), 10, 100);
	// try {
	// errors.pushNestedPath("address");
	// ValidationUtils.invokeValidator(this.contactValidator,
	// person.getAddress(), errors);
	// } finally {
	// errors.popNestedPath();
	// }
    }

}