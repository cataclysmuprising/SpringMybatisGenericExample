/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base.api.root;

import java.util.HashMap;
import java.util.List;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface UpdateableService<T extends BaseBean> extends BaseService<T> {
	public long update(T record, long recordUpdId) throws DuplicatedEntryException, BusinessException;

	public void update(List<T> records, long recordUpdId) throws DuplicatedEntryException, BusinessException;

	public long update(HashMap<String, Object> criteria, HashMap<String, Object> updateItems,
			long recordUpdId) throws BusinessException, DuplicatedEntryException;
}
