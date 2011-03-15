package com.qzgf.application.biz.custom.domain;


import java.util.List;
import java.util.Map;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 自定义接口
 *
 */
public interface CustomFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 保存自定义主对象
	 * @param custom
	 * @param num
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean saveFieldCustom(Map custom,int num);
	
	/**
	 * 查询所有的模板列表根据模板类别
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findPatternList(Map map);
	
	/**
	 * 根据模板编号查询该模板的信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map findCustomById(Map map);
	
	/**
	 * 删除自定义信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	public void delCustomById(Map map);
	
	/**
	 * 查询系统定义且正启用的事件
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEvents();
	
	/**
	 * 查询所有的枚举来源
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEnums();
	
	/**
	 * 查询自定义项
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCustomItems(Map map);
	
	/**
	 * 保存自定义主对象
	 * @param custom
	 * @param num
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateFieldCustom(Map custom,int num);
}