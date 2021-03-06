/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.base.api.root;

import java.util.List;
import java.util.Map;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.exception.BusinessException;

public interface StandAloneSelectableService<T extends BaseBean> extends BaseService<T> {
	public T select(long primaryKey) throws BusinessException;

	public T select(Map<String, Object> criteria) throws BusinessException;

	public List<T> selectList(Map<String, Object> criteria) throws BusinessException;

	public long selectCounts(Map<String, Object> criteria) throws BusinessException;
}
