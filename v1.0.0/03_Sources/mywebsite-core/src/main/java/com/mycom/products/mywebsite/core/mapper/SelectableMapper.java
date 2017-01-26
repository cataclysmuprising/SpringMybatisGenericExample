package com.mycom.products.mywebsite.core.mapper;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface SelectableMapper<T extends BaseBean> {

	public T selectByPrimaryKey(int primaryKey);

	public T selectSingleRecord(Map<String, Object> criteria);

	public List<T> selectMultiRecords(Map<String, Object> criteria);

	public int selectCounts(Map<String, Object> criteria);

}
