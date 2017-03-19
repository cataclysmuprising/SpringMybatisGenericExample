/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.service.base.api.root;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface CommonGenericService<T extends BaseBean>
		extends InsertableService<T>, UpdateableService<T>, RemoveableService<T> {
}
