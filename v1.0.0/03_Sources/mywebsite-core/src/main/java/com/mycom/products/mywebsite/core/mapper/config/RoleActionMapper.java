package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.RoleActionBean;

public interface RoleActionMapper {

	public int insert(RoleActionBean roleAction);

	public int insertHistory(RoleActionBean roleAction);

	public int update(RoleActionBean roleAction);

	public int delete(Map<String, Object> criteria);

	public List<RoleActionBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);
}
