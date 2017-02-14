/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.api;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public interface JoinedSelectableDao<T extends BaseBean> {
	public T select(long primaryKey, FetchMode fetchMode) throws DAOException;

	public T select(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;

	public List<T> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;

	public long selectCounts(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;
}
