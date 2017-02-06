/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.dao.base.JoinedSelectableDao;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class JoinedSelectableServiceImpl<T extends BaseBean> implements JoinedSelectableService<T> {

	private JoinedSelectableDao<T> dao;

	public JoinedSelectableServiceImpl(JoinedSelectableDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public T select(long primaryKey, FetchMode fetchMode) throws BusinessException {
		try {
			return dao.select(primaryKey, fetchMode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T select(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		try {
			return dao.select(criteria, fetchMode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "ApplicationCache")
	public List<T> selectList(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		try {
			return dao.selectList(criteria, fetchMode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long selectCounts(Map<String, Object> criteria, FetchMode fetchMode) throws BusinessException {
		try {
			return dao.selectCounts(criteria, fetchMode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0l;
	}

}
