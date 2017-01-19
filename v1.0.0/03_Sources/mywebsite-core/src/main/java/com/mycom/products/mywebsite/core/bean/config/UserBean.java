package com.mycom.products.mywebsite.core.bean.config;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;

public class UserBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6106913047232092123L;

	public enum Gender {
		MALE, FEMALE;
	}

	private Integer age;
	private String loginId;
	private String name;
	private String email;

	@JsonIgnore
	private String password;
	@JsonIgnore
	private String confirmPassword;
	private String phone;
	private String nrc;
	private List<Integer> roleIds;
	private Gender gender;
	private Date dob;
	private String address;
	private List<RoleBean> roles;
	private String dobAsString;
	private Timestamp lastLoginDate;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
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
		return "UserBean [age=" + age + ", loginId=" + loginId + ", name=" + name + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", phone=" + phone + ", nrc=" + nrc + ", roleIds=" + roleIds + ", gender=" + gender + ", dob=" + dob + ", address=" + address + ", roles=" + roles + ", dobAsString=" + dobAsString + ", lastLoginDate=" + lastLoginDate + ", contentId=" + contentId + ", content=" + content + "]";
	}

}
