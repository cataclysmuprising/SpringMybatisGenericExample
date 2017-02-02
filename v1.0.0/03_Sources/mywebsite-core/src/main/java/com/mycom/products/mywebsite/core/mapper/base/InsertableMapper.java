/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface InsertableMapper<T extends BaseBean> {
	public long insert(T record);

	public long insertList(@Param("records") List<T> records);
}
