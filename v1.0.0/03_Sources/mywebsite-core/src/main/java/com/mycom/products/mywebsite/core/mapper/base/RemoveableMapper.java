/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.base;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface RemoveableMapper<T extends BaseBean> {

	public int deleteByPrimaryKey(@Param("primaryKey") int primaryKey);

	public int deleteByCriteria(@Param("criteria") Map<String, Object> criteria);
}
