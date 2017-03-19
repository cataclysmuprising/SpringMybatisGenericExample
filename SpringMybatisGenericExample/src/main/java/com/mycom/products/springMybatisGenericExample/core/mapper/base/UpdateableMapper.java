/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.base;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface UpdateableMapper<T extends BaseBean> {
	public long update(T record);

	public long updateWithCriteria(@Param("criteria") HashMap<String, Object> criteria,
			@Param("updateItems") HashMap<String, Object> updateItems);
}
