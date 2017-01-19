package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.RoleBean;

public interface RoleMapper {

	public int insert(RoleBean action);

	public int insertHistory(RoleBean action);

	public int update(RoleBean action);

	public int delete(Map<String, Object> criteria);

	public RoleBean selectById(int id);

	public List<RoleBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);
}
