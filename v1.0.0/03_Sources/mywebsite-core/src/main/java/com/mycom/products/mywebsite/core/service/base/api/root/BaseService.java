/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base.api.root;

import java.util.List;

import org.apache.log4j.Logger;

public interface BaseService<T> {
	public static Logger serviceLogger = Logger.getLogger("ServiceLogger");

	public default String getObjectName(T record) {
		return " '" + record.getClass().getSimpleName().replace("Bean", "") + "' ";
	}

	public default String getObjectName(List<T> records) {
		if (records == null || records.isEmpty()) {
			return "";
		}
		return " '" + records.get(0).getClass().getSimpleName().replace("Bean", "") + "' ";
	}
}
