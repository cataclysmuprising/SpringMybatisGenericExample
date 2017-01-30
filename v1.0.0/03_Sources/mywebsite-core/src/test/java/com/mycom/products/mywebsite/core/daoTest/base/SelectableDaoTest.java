/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.exception.DAOException;

public interface SelectableDaoTest<T extends BaseBean> {

	public void testSelectAllWithLazyMode() throws DAOException;

	public void testSelectAllWithEagerMode() throws DAOException;

	public void testSelectAllCount() throws DAOException;

	public void testSelectCountWithLazyCriteria() throws DAOException;

	public void testSelectCountWithEagerCriteria() throws DAOException;

	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException;

	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException;

	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException;

	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException;

	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException;

	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException;

}
