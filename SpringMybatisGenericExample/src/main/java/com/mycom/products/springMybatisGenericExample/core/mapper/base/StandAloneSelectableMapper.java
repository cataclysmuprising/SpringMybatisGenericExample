/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface StandAloneSelectableMapper<T extends BaseBean> {

	public T selectByPrimaryKey(@Param("primaryKey") long primaryKey);

	public T selectSingleRecord(@Param("criteria") Map<String, Object> criteria);

	public List<T> selectMultiRecords(@Param("criteria") Map<String, Object> criteria);

	public long selectCounts(@Param("criteria") Map<String, Object> criteria);

}
