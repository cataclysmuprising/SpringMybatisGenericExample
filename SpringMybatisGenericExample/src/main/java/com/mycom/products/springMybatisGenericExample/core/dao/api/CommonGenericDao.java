/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.dao.api;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface CommonGenericDao<T extends BaseBean>
		extends InsertableDao<T>, UpdateableDao<T>, RemoveableDao<T> {

}
