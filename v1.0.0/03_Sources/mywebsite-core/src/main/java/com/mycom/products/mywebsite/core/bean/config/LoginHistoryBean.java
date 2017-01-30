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
	private String clientIp;
	private String os;
	private String browser;
	private Timestamp loginDate;
	private int userId;
	private UserBean user;

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("LoginHistoryBean [clientIp=%s, os=%s, browser=%s, loginDate=%s, userId=%s, user=%s, MetaDatas=%s]", clientIp, os, browser, loginDate, userId, user, super.toString());
	}

}
