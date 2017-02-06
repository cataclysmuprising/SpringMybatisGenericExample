/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface StandAloneService<T extends BaseBean>
		extends CommonGenericService<T>, StandAloneSelectableService<T> {
}
