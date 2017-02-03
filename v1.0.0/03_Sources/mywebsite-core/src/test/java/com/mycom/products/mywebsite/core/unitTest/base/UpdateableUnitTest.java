/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.unitTest.base;

import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.exception.DuplicatedEntryException;

public interface UpdateableUnitTest {

	public void testSingleRecordUpdate() throws DuplicatedEntryException, DAOException;

	public void testMultiRecordsUpdate() throws DuplicatedEntryException, DAOException;

	public void testUpdateByCriteria() throws DuplicatedEntryException, DAOException;
}
