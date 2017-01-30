/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.master;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycom.products.mywebsite.core.bean.master.SettingBean;
import com.mycom.products.mywebsite.core.dao.master.SettingDao;
import com.mycom.products.mywebsite.core.daoTest.BaseDaoTest;
import com.mycom.products.mywebsite.core.daoTest.base.SelectableDaoTest;
import com.mycom.products.mywebsite.core.exception.DAOException;

public class SettingDaoTest extends BaseDaoTest implements SelectableDaoTest<SettingBean> {
	@Autowired
	private SettingDao setttingDao;
	private Logger testLogger = Logger.getLogger(this.getClass());

	@Override
	public void testSelectAllWithLazyMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectAllWithEagerMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectAllCount() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectCountWithLazyCriteria() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectCountWithEagerCriteria() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectMultiRecordByCriteriaWithLazyMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectMultiRecordByCriteriaWithEagerMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectByPrimaryKeyWithLazyMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectByPrimaryKeyWithEagerMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectSingleRecordByCriteriaWithLazyMode() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSelectSingleRecordByCriteriaWithEagerMode() throws DAOException {
		// TODO Auto-generated method stub

	}

}
