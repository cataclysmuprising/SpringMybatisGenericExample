/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.master;

import com.mycom.products.springMybatisGenericExample.core.bean.master.StaticContentBean;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.CommonGenericMapper;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.StandAloneSelectableMapper;

public interface StaticContentMapper
		extends CommonGenericMapper<StaticContentBean>, StandAloneSelectableMapper<StaticContentBean> {
}
