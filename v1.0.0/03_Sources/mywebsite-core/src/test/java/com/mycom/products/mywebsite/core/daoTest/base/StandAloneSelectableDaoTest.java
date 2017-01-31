/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.base;

import com.mycom.products.mywebsite.core.exception.DAOException;

public interface StandAloneSelectableDaoTest {

	public void testSelectAll() throws DAOException;

	public void testSelectAllCount() throws DAOException;

	public void testSelectCountByCriteria() throws DAOException;

	public void testSelectMultiRecordByCriteria() throws DAOException;

	public void testSelectByPrimaryKey() throws DAOException;

	public void testSelectSingleRecordByCriteria() throws DAOException;

}
