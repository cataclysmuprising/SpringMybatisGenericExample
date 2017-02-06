/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.functionalTest.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.mycom.products.mywebsite.core.TestBase;
import com.mycom.products.mywebsite.core.exception.BusinessException;
import com.mycom.products.mywebsite.core.exception.DAOException;
import com.mycom.products.mywebsite.core.service.config.ActionService;
import com.mycom.products.mywebsite.core.util.FetchMode;

public class ActionFuntionalTest extends TestBase {
	@Autowired
	private ActionService actionService;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Test(groups = { "fetch" })
	public void testSelectAll() throws DAOException, BusinessException {
		actionService.selectList(null, FetchMode.LAZY);
		actionService.getAllPageNamesByModule("back-end");
	}
}