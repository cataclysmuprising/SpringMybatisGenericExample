package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.UserRoleBean;

public interface UserRoleMapper {
	public int insert(UserRoleBean action);

	public int insertHistory(UserRoleBean action);

	public int update(UserRoleBean action);

	public int delete(Map<String, Object> criteria);

	public List<UserRoleBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);
}
