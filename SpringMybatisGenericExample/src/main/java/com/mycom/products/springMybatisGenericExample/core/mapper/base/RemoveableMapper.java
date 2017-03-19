/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.base;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface RemoveableMapper<T extends BaseBean> {

	public long deleteByPrimaryKey(@Param("primaryKey") long primaryKey);

	public long deleteByCriteria(@Param("criteria") Map<String, Object> criteria);
}
