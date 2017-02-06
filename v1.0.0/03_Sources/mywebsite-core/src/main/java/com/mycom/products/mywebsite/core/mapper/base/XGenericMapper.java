/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.util.FetchMode;

public interface XGenericMapper<T extends BaseBean> extends InsertableMapper<T> {
	public void insertWithRelatedKeys(@Param("key1") long key1, @Param("key2") long key2,
			@Param("recordRegId") long recordRegId);

	public long deleteByKeys(@Param("key1") long key1, @Param("key2") long key2);

	public long deleteByCriteria(@Param("criteria") Map<String, Object> criteria);

	public List<Long> selectRelatedKeys(@Param("criteria") Map<String, Object> criteria);

	public T selectByKeys(@Param("key1") long key1, @Param("key2") long key2, @Param("fetchMode") FetchMode fetchMode);

	public List<T> selectMultiRecords(@Param("criteria") Map<String, Object> criteria,
			@Param("fetchMode") FetchMode fetchMode);

	public long selectCounts(@Param("criteria") Map<String, Object> criteria, @Param("fetchMode") FetchMode fetchMode);

}
