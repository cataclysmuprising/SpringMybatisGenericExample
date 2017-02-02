/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.base;

import com.mycom.products.mywebsite.core.exception.DAOException;

public interface XSelectableUnitTest {

	public void testSelectAllWithLazyMode() throws DAOException;

	public void testSelectAllWithEagerMode() throws DAOException;

	public void testSelectAllCount() throws DAOException;

	public void testSelectRelatedKeysByKey1() throws DAOException;

	public void testSelectRelatedKeysByKey2() throws DAOException;

	public void testSelectByKeysWithLazyMode() throws DAOException;

	public void testSelectByKeysWithEagerMode() throws DAOException;

	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException;

	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException;

}
