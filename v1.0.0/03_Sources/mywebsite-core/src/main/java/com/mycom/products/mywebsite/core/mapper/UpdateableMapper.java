package com.mycom.products.mywebsite.core.mapper;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface UpdateableMapper<T extends BaseBean> {

	public int update(T record);

	public int update(List<T> records);
}
