package com.mycom.products.mywebsite.core.dao;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;
import com.mycom.products.mywebsite.core.exception.MyBatisException;

public interface UpdateableDao<T extends BaseBean> {
	public int update(T record, int recordUpdId) throws DuplicatedEntryException, MyBatisException;

	public void update(List<T> records, int recordUpdId) throws DuplicatedEntryException, MyBatisException;
}
