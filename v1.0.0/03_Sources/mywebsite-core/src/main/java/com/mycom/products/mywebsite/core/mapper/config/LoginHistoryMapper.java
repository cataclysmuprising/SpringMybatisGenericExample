package com.mycom.products.mywebsite.core.mapper.config;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.config.LoginHistoryBean;

public interface LoginHistoryMapper {

	public int insert(LoginHistoryBean action);

	public int update(LoginHistoryBean action);

	public int delete(Map<String, Object> criteria);

	public LoginHistoryBean selectById(int id);

	public List<LoginHistoryBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);

}
