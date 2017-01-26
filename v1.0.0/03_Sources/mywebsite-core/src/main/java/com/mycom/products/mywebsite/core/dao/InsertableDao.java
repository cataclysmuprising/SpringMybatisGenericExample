package com.mycom.products.mywebsite.core.dao;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface InsertableDao<T extends BaseBean> {
	public int insert(T record, int recordRegId) throws DuplicatedEntryException, MyBatisException;

	public void insert(List<T> records, int recordRegId) throws DuplicatedEntryException, MyBatisException;
}
