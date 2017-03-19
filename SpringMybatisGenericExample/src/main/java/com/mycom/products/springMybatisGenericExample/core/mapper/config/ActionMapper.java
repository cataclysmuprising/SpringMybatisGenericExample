/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.mapper.config;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycom.products.springMybatisGenericExample.core.bean.config.ActionBean;
import com.mycom.products.springMybatisGenericExample.core.mapper.base.JoinedSelectableMapper;

public interface ActionMapper extends JoinedSelectableMapper<ActionBean> {
	public List<String> selectPageNamesByModule(@Param("module") String module);
}
