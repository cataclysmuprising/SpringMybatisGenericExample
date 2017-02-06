/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.BusinessException;

public interface StandAloneSelectableService<T extends BaseBean> {
	public T select(long primaryKey) throws BusinessException;

	public T select(Map<String, Object> criteria) throws BusinessException;

	public List<T> selectList(Map<String, Object> criteria) throws BusinessException;

	public long selectCounts(Map<String, Object> criteria) throws BusinessException;
}
