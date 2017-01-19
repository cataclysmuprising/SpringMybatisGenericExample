package com.mycom.products.mywebsite.core.mapper.master;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.master.StaticContentBean;

public interface StaticContentMapper {

	public int insert(StaticContentBean content);

	public int insertHistory(StaticContentBean content);

	public int update(StaticContentBean content);

	public int delete(Map<String, Object> criteria);

	public StaticContentBean selectById(int id);

	public List<StaticContentBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);

}
