/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface InsertableDao<T extends BaseBean> {
	public int insert(T record, int recordRegId) throws DuplicatedEntryException, DAOException;

	public void insert(List<T> records, int recordRegId) throws DuplicatedEntryException, DAOException;
}
