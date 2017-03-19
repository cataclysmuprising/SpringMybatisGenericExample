/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.dao.api;

import java.util.List;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.exception.DAOException;
import com.mycom.products.springMybatisGenericExample.core.exception.DuplicatedEntryException;

public interface InsertableDao<T extends BaseBean> {
	public long insert(T record, long recordRegId) throws DuplicatedEntryException, DAOException;

	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, DAOException;
}
