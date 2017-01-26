package com.mycom.products.mywebsite.core.dao;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface CommonGenericDao<T extends BaseBean>
		extends InsertableDao<T>, UpdateableDao<T>, RemoveableDao<T>, SelectableDao<T> {

}
