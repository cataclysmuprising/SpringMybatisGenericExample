/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.base;

import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;

public interface RemoveableDao<T extends BaseBean> {
	public long delete(long primaryKey, long recordUpdId) throws ConsistencyViolationException, DAOException;

	public long delete(Map<String, Object> criteria,
			long recordUpdId) throws ConsistencyViolationException, DAOException;
}
