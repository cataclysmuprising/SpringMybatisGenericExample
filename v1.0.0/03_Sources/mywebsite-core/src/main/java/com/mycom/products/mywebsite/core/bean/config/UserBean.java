/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycom.products.mywebsite.core.annotation.FieldMatch;
import com.mycom.products.mywebsite.core.annotation.UniqueEmail;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;
import com.mycom.products.mywebsite.core.util.LocalDateTimeSerializer;
import com.mycom.products.mywebsite.core.validator.base.ValidationGroup;
import com.mycom.products.mywebsite.core.validator.base.ValidationOrder;

// http://stackoverflow.com/questions/1972933/cross-field-validation-with-hibernate-validator-jsr-303
//http://stackoverflow.com/questions/7239897/spring-3-annotation-based-validation-password-and-confirm-password
//http://stackoverflow.com/questions/4613055/hibernate-unique-key-validation
@FieldMatch.List({
	@FieldMatch(first = "password", second = "confirmPassword", message = "{Validation.UserBean.Field.MismatchPasswords}", groups = ValidationGroup.Create.class) })
public class UserBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = -6106913047232092123L;

    public enum Gender {
	MALE, FEMALE, OTHER;
    }

    @NotNull(message = "Age {Validation.common.Field.Required}")
    @Min(value = 18, message = "Age {Validation.common.Field.Min.Number}")
    private Integer age;

    @NotBlank(message = "Login Id {Validation.common.Field.Required}", groups = { ValidationGroup.Create.class })
    @Range(min = 5, max = 50, message = "Login Id {Validation.common.Field.InvalidRange.String}", groups = {
	    ValidationGroup.Create.class })
    private String loginId;

    @NotBlank(message = "Name {Validation.common.Field.Required}")
    @Range(min = 5, max = 50, message = "Name {Validation.common.Field.InvalidRange.String}")
    private String name;

    @NotBlank(message = "Email address {Validation.common.Field.Required}")
    @Email(message = "{Validation.common.Field.InvalidEmail}", groups = { ValidationOrder.Second.class })
    @UniqueEmail(message = "{Validation.UserBean.Field.UniqueEmail}", columnName = "email", groups = {
	    ValidationOrder.Third.class })
    private String email;

    @JsonIgnore
    @NotBlank(message = "Password {Validation.common.Field.Required}", groups = { ValidationGroup.Create.class })
    @Min(value = 8, message = "Password {Validation.common.Field.Min.String}", groups = {
	    ValidationGroup.Create.class })
    private String password;

    @JsonIgnore
    @NotBlank(message = "Confirm password {Validation.common.Field.Required}", groups = {
	    ValidationGroup.Create.class })
    @Min(value = 8, message = "Confirm password {Validation.common.Field.Min.String}", groups = {
	    ValidationGroup.Create.class })
    private String confirmPassword;

    @NotBlank(message = "Phone number {Validation.common.Field.Required}")
    @Max(value = 50, message = "Phone number {Validation.common.Field.Max.String}")
    private String phone;

    @NotBlank(message = "NRC number {Validation.common.Field.Required}")
    @Max(value = 50, message = "NRC number {Validation.common.Field.Max.String}")
    private String nrc;

    @NotEmpty(message = "{Validation.common.Field.ChooseOne} role.", groups = { ValidationGroup.Create.class })
    private List<Integer> roleIds;

    @NotNull(message = "{Validation.common.Field.Specify} gender.")
    private Gender gender;

    private LocalDate dob;
    private String address;
    private List<RoleBean> roles;
    private String dobAsString;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLoginDate;
    private int contentId;
    private StaticContentBean content;

    public UserBean() {
	gender = Gender.MALE;
	content = new StaticContentBean();
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getNrc() {
	return nrc;
    }

    public void setNrc(String nrc) {
	this.nrc = nrc;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public LocalDate getDob() {
	return dob;
    }

    public void setDob(LocalDate dob) {
	this.dob = dob;
	this.dobAsString = convertDateAsString(dob);
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public List<RoleBean> getRoles() {
	return roles;
    }

    public void setRoles(List<RoleBean> roles) {
	this.roles = roles;
    }

    public String getDobAsString() {
	return dobAsString;
    }

    public void setDobAsString(String dobAsString) {
	this.dobAsString = dobAsString;
	this.dob = convertStringAsDate(dobAsString);
    }

    public List<Integer> getRoleIds() {
	return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
	this.roleIds = roleIds;
    }

    public LocalDateTime getLastLoginDate() {
	return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
    }

    public String getLoginId() {
	return loginId;
    }

    public void setLoginId(String loginId) {
	this.loginId = loginId;
    }

    public int getContentId() {
	return contentId;
    }

    public void setContentId(int contentId) {
	this.contentId = contentId;
    }

    public StaticContentBean getContent() {
	return content;
    }

    public void setContent(StaticContentBean content) {
	this.content = content;
    }

    @Override
    public String toString() {
	return String.format(
		"UserBean {age=%s, loginId=%s, name=%s, email=%s, password=%s, confirmPassword=%s, phone=%s, nrc=%s, roleIds=%s, gender=%s, dob=%s, address=%s, roles=%s, dobAsString=%s, lastLoginDate=%s, contentId=%s, content=%s, ID=%s}",
		age, loginId, name, email, password, confirmPassword, phone, nrc, roleIds, gender, dob, address, roles,
		dobAsString, lastLoginDate, contentId, content, getId());
    }

}
