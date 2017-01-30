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

public interface XGenericMapper<T extends BaseBean> extends InsertableMapper<T> {
	public void insert(int key1, int key2, int recordRegId);

	public int deleteByKeys(@Param("key1") int key1, @Param("key2") int key2);

	public int deleteByCriteria(@Param("criteria") Map<String, Object> criteria);

	public List<Integer> selectRelatedKeys(@Param("criteria") Map<String, Object> criteria);

	public T selectByKeys(@Param("key1") int key1, @Param("key2") int key2);

	public List<T> selectList(@Param("criteria") Map<String, Object> criteria);

	public int selectCounts(@Param("criteria") Map<String, Object> criteria);

}
