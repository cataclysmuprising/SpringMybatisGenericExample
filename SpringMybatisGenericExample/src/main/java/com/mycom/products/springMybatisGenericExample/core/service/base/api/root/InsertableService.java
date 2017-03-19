/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.base.api.root;

import java.util.List;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.exception.BusinessException;
import com.mycom.products.springMybatisGenericExample.core.exception.DuplicatedEntryException;

public interface InsertableService<T extends BaseBean> extends BaseService<T> {
	public long insert(T record, long recordRegId) throws DuplicatedEntryException, BusinessException;

	public void insert(List<T> records, long recordRegId) throws DuplicatedEntryException, BusinessException;
}
