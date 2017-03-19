/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.dao.api;

import java.util.Map;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;
import com.mycom.products.springMybatisGenericExample.core.exception.ConsistencyViolationException;
import com.mycom.products.springMybatisGenericExample.core.exception.DAOException;

public interface RemoveableDao<T extends BaseBean> {
	public long delete(long primaryKey, long recordUpdId) throws ConsistencyViolationException, DAOException;

	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, DAOException;
}
