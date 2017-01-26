package com.mycom.products.mywebsite.core.mapper;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface XGenericMapper<T extends BaseBean> extends InsertableMapper<T> {
	public int insert(int key1, int key2, int recordRegId);

	public int delete(int key1, int key2);

	public int delete(Map<String, Object> criteria);

	public List<Integer> select(Map<String, Object> criteria);

	public T select(int key1, int key2);

	public List<T> selectList(Map<String, Object> criteria);

	public int selectCounts(Map<String, Object> criteria);

}
