/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base.api.root;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface InsertableService<T extends BaseBean> extends BaseService<T> {
	public long insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException;

	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException;
}
