package com.mycom.products.mywebsite.core.mapper;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public interface CommonGenericMapper<T extends BaseBean>
		extends InsertableMapper<T>, UpdateableMapper<T>, RemoveableMapper<T>, SelectableMapper<T> {
}
