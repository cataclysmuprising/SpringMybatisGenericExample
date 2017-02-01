/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class UserRoleBean extends BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 4461809498816982732L;
	private int userId;
	private int roleId;
	private UserBean user;
	private RoleBean role;

	public UserRoleBean() {
	}

	public UserRoleBean(UserBean user, RoleBean role) {
		this.user = user;
		this.role = role;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
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
