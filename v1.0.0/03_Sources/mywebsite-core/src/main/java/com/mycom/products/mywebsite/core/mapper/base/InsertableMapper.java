/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.base;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface InsertableMapper<T extends BaseBean> {
	public long insert(T record);
}
