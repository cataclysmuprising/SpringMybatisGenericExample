/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base.api.root;

import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;

public interface RemoveableService<T extends BaseBean> extends BaseService<T> {
	public long delete(long id, long recordUpdId) throws ConsistencyViolationException, BusinessException;

	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, BusinessException;
}
