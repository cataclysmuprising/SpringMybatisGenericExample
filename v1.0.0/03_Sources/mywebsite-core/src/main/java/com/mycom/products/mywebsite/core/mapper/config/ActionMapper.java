package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.ActionBean;

public interface ActionMapper {

	public int insert(ActionBean action);

	public int insertHistory(ActionBean action);

	public int update(ActionBean action);

	public int delete(Map<String, Object> criteria);

	public ActionBean selectById(int id);

	public List<ActionBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);

	public List<String> selectAllPageNames();

}
