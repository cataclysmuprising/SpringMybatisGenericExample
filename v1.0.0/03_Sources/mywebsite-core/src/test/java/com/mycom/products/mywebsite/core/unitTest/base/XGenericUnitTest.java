/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.base;

import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface XGenericUnitTest extends InsertableUnitTest, XSelectableUnitTest {

	public void testInsertWithKeys() throws DAOException, DuplicatedEntryException;

	public void testDeleteByKeys() throws DAOException, DuplicatedEntryException, ConsistencyViolationException;

	public void testDeleteByCriteria() throws DAOException, DuplicatedEntryException, ConsistencyViolationException;

	public void testMerge() throws DAOException, DuplicatedEntryException, ConsistencyViolationException;

}
