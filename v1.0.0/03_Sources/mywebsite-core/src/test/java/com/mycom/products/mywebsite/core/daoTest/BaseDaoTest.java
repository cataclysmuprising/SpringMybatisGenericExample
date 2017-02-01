/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations = { "classpath:spring-test-context.xml" })
public class BaseDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	protected Logger daoLogger = Logger.getLogger("daoLogger");
	protected static final int TEST_CREATE_USER_ID = 10009;
	protected static final int TEST_UPDATE_USER_ID = 90001;

	@BeforeMethod
	public void beforeMethod(Method method) {
		daoLogger.info("***** DAO-TEST : Testing method '" + method.getName() + "' has started. *****");
	}

	@AfterMethod
	public void afterMethod(Method method) {
		daoLogger.info("----- DAO-TEST : Testing method '" + method.getName() + "' has finished. -----");
	}

	protected <T> void showEntriesOfCollection(Collection<T> collection) {
		if (collection != null) {
			Iterator<?> iterator = collection.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				daoLogger.info(" >>> " + obj.toString());
			}
		}
	}

	// @Test
	// private void testMockObject() {
	// try {
	// UserBean action = new UserBean();
	// MockDataGenerator.mock(action, GenerateMode.SINGLE);
	// daoLogger.info("After making mock object >>> " + action);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}