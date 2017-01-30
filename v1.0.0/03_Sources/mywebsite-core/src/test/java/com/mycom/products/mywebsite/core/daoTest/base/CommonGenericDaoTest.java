/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.daoTest.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface CommonGenericDaoTest<T extends BaseBean>
		extends InsertableDaoTest<T>, UpdateableDaoTest<T>, RemoveableDaoTest<T>, SelectableDaoTest<T> {

}
