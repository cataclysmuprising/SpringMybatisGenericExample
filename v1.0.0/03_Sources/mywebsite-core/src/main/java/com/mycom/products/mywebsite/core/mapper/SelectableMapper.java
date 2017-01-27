/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.SelectableDao.FetchMode;

public interface SelectableMapper<T extends BaseBean> {

	public T selectByPrimaryKey(@Param("primaryKey") int primaryKey, @Param("fetchMode") FetchMode fetchMode);

	public T selectSingleRecord(@Param("criteria") Map<String, Object> criteria,
			@Param("fetchMode") FetchMode fetchMode);

	public List<T> selectMultiRecords(@Param("criteria") Map<String, Object> criteria,
			@Param("fetchMode") FetchMode fetchMode);

	public int selectCounts(@Param("criteria") Map<String, Object> criteria, @Param("fetchMode") FetchMode fetchMode);

}
