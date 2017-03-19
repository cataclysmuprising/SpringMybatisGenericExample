/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.base;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public interface CommonGenericMapper<T extends BaseBean>
		extends InsertableMapper<T>, UpdateableMapper<T>, RemoveableMapper<T>, HistoryTrackableMapper<T> {
}
