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
	public int delete(int primaryKey, int recordUpdId) throws ConsistencyViolationException, DAOException;

	public int delete(Map<String, Object> criteria,
			int recordUpdId) throws ConsistencyViolationException, DAOException;
}
