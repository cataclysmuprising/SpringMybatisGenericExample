package com.mycom.products.mywebsite.core.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.ValidationTestBase;
import com.mycom.products.mywebsite.core.bean.config.UserBean;

public class UserValidationTest extends ValidationTestBase {
    @Test
    public void testValidation() {
	UserBean user = new UserBean();
	user.setAge(10);
	user.setEmail("abc");
	user.setLoginId("a");
	user.setPassword("11111111");
	user.setConfirmPassword("22222222");
	user.setPhone("111111111122222222223333333333444444444455555555556666666666");
	BindingResult errors = new BeanPropertyBindingResult(user, "user");
	validator.validate(user, errors);
	showValidationErrors(errors);
	Assert.assertEquals(0, errors.getErrorCount());
    }

}
