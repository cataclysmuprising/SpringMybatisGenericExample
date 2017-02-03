/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class UserRoleBean extends BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 4461809498816982732L;
	private long userId;
	private long roleId;
	private UserBean user;
	private RoleBean role;

	public UserRoleBean() {
	}

	public UserRoleBean(UserBean user, RoleBean role) {
		this.user = user;
		this.role = role;
	}

	public UserRoleBean(long userId, long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserBean getUser() {
		return this.user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public RoleBean getRole() {
		return this.role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return String.format("UserRoleBean {userId=%s, roleId=%s, user=%s, role=%s}", userId, roleId, user, role);
	}

}
