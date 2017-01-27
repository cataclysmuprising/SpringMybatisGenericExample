/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface CommonGenericMapper<T extends BaseBean>
		extends InsertableMapper<T>, UpdateableMapper<T>, RemoveableMapper<T>, SelectableMapper<T>,
		HistoryTrackableMapper<T> {
}
