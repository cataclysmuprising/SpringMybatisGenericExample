/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.dao.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface CommonGenericDao<T extends BaseBean>
		extends InsertableDao<T>, UpdateableDao<T>, RemoveableDao<T>, SelectableDao<T> {

}
