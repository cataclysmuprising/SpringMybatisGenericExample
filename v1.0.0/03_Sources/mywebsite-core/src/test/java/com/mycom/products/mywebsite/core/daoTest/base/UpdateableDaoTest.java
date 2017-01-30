/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.base;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface UpdateableDaoTest<T extends BaseBean> {
	public int update(T record, int recordUpdId) throws DuplicatedEntryException, DAOException;

	public void update(List<T> records, int recordUpdId) throws DuplicatedEntryException, DAOException;
}
