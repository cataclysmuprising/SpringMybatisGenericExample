/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean.master;

import java.io.Serializable;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class SettingBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6073139095165627054L;
	private String name;
	private String value;
	private String type;
	private String group;
	private String subGroup;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	@Override
	public String toString() {
		return String.format("SettingBean {name=%s, value=%s, type=%s, group=%s, subGroup=%s, ID=%s}", name, value, type, group, subGroup, getId());
	}

}
