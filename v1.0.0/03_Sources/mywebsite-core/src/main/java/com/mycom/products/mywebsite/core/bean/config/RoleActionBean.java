/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class RoleActionBean extends BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer roleId;
	private Integer actionId;

	private RoleBean role;
	private ActionBean action;

	public RoleActionBean() {
	}

	public RoleActionBean(RoleBean role, ActionBean action) {
		this.role = role;
		this.action = action;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public ActionBean getAction() {
		return action;
	}

	public void setAction(ActionBean action) {
		this.action = action;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return String.format("RoleActionBean [roleId=%s, actionId=%s, role=%s, action=%s, MetaDatas=%s]", roleId, actionId, role, action, super.toString());
	}

}
