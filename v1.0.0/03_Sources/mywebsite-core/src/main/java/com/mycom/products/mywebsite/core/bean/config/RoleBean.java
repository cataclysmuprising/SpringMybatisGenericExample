/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.config;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class RoleBean extends BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = -5005684482585299863L;
	private String name;
	private String description;
	private List<Integer> actionIds;
	private List<Integer> userIds;
	private List<ActionBean> actions;
	private List<UserBean> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Integer> getActionIds() {
		return actionIds;
	}

	public void setActionIds(List<Integer> actionIds) {
		this.actionIds = actionIds;
	}

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}

	public List<ActionBean> getActions() {
		return actions;
	}

	public void setActions(List<ActionBean> actions) {
		this.actions = actions;
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return String.format("RoleBean {name=%s, description=%s, actionIds=%s, userIds=%s, actions=%s, users=%s, ID=%s}", name, description, actionIds, userIds, actions, users, getId());
	}

}
