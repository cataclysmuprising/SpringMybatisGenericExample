package com.mycom.products.mywebsite.core.mapper;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface InsertableMapper<T extends BaseBean> {

	public int insert(T record);

	public int insert(List<T> records);
}
