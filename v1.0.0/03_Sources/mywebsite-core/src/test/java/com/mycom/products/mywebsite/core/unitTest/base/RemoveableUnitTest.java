/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.base;

import com.mycom.products.mywebsite.core.exception.ConsistencyViolationException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface RemoveableUnitTest {
	public void testDeleteByPrimaryKey() throws DAOException, DuplicatedEntryException, ConsistencyViolationException;

	public void testDeleteByCriteria() throws DAOException, DuplicatedEntryException, ConsistencyViolationException;
}
