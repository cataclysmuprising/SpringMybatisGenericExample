/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface JoinedService<T extends BaseBean>
		extends CommonGenericService<T>, JoinedSelectableService<T> {
}
