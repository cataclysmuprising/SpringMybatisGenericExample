package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.UserBean;

public interface UserMapper {

	public int insert(UserBean action);

	public int insertHistory(UserBean action);

	public int update(UserBean action);

	public int delete(Map<String, Object> criteria);

	public UserBean selectById(int id);

	public List<UserBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);

	public UserBean selectByLoginId(String loginId);

}
