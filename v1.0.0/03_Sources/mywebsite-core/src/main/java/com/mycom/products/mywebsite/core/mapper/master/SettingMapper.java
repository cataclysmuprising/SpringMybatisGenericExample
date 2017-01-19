package com.mycom.products.mywebsite.core.mapper.master;

import java.util.List;
import java.util.Map;

import com.mycom.products.mywebsite.core.bean.master.SettingBean;

public interface SettingMapper {

	public int insert(SettingBean setting);

	public int insertHistory(SettingBean setting);

	public int update(SettingBean setting);

	public int delete(Map<String, Object> criteria);

	public SettingBean selectById(int id);

	public List<SettingBean> selectByCriteria(Map<String, Object> criteria);

	public int selectCountByCriteria(Map<String, Object> criteria);
}
