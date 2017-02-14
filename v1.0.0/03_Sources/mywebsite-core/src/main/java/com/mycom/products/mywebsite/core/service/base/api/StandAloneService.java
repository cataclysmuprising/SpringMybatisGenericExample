/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.service.base.api;

import com.mycom.products.mywebsite.core.bean.BaseBean;
import com.mycom.products.mywebsite.core.service.base.api.root.CommonGenericService;
import com.mycom.products.mywebsite.core.service.base.api.root.StandAloneSelectableService;

public interface StandAloneService<T extends BaseBean>
		extends CommonGenericService<T>, StandAloneSelectableService<T> {
}
