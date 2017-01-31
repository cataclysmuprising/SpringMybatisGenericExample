/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import java.io.Serializable;
import java.sql.Timestamp;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class LoginHistoryBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 3454068909862152769L;
	private String ipAddress;
	private String os;
	private String userAgent;
	private Timestamp loginDate;
	private long userId;
	private UserBean user;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return String.format("LoginHistoryBean [ipAddress=%s, os=%s, userAgent=%s, loginDate=%s, userId=%s, user=%s, toString()=%s]", ipAddress, os, userAgent, loginDate, userId, user, super.toString());
	}

}
