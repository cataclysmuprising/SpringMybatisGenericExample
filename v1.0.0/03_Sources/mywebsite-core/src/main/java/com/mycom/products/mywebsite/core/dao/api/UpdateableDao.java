/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.api;

import java.util.HashMap;
import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface UpdateableDao<T extends BaseBean> {
	public long update(T record, long recordUpdId) throws DuplicatedEntryException, DAOException;

	public void update(List<T> records, long recordUpdId) throws DuplicatedEntryException, DAOException;

	public long update(HashMap<String, Object> criteria, HashMap<String, Object> updateItems,
			long recordUpdId) throws DAOException, DuplicatedEntryException;
}
