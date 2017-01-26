package com.mycom.products.mywebsite.core.mapper;

import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface RemoveableMapper<T extends BaseBean> {

	public int delete(int primaryKey);

	public int delete(Map<String, Object> criteria);
}
