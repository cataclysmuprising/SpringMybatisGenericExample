/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.bean.config;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.util.LocalDateTimeSerializer;

public class LoginHistoryBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 3454068909862152769L;
    private String ipAddress;
    private String os;
    private String userAgent;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime loginDate;
    private long userId;
    private UserBean user;

    public long getUserId() {
	return userId;
    }

    public void setUserId(long userId) {
	this.userId = userId;
    }

    public LocalDateTime getLoginDate() {
	return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
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
	return String.format(
		"LoginHistoryBean {ipAddress=%s, os=%s, userAgent=%s, loginDate=%s, userId=%s, user=%s, ID=%s}",
		ipAddress, os, userAgent, loginDate, userId, user, getId());
    }

}
