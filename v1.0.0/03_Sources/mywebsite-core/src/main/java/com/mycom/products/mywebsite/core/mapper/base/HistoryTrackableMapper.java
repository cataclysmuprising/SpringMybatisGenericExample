/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HistoryTrackableMapper<T> {
	public void saveHistory(T record);

	public void saveHistoryList(@Param("records") List<T> records);
}
