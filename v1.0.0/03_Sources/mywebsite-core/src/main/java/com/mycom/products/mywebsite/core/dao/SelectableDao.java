/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;

public interface SelectableDao<T extends BaseBean> {
	public enum FetchMode {
		LAZY, EAGER;
	}

	public T select(int primaryKey, FetchMode fetchMode) throws DAOException;

	public T select(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;

	public List<T> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;

	public int selectCounts(Map<String, Object> criteria, FetchMode fetchMode) throws DAOException;
}
