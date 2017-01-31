/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;
import com.mycom.products.mywebsite.core.mapper.base.JoinedSelectableMapper;

public interface ActionMapper extends JoinedSelectableMapper<ActionBean> {
	public List<String> selectAllPageNames();
}
