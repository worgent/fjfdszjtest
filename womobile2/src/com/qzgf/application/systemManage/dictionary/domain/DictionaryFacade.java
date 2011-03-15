package com.qzgf.application.systemManage.dictionary.domain;

import java.util.HashMap;
import java.util.List;


public interface DictionaryFacade {
	@SuppressWarnings("unchecked")
	public List findDictionaryList();
	
	@SuppressWarnings("unchecked") 
	public String insertDictionary(HashMap map)throws Exception;
	
	/**
	 * 根据字典编号查询该字典名称
	 * @param id
	 * @return
	 */
	public String findDictNameByDictId(String id);
	
	/**
	 * 更新用户数据字典
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateDictNameById(HashMap map);
	
	/**
	 * 根据某一ID数据字典
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteDictById(HashMap map);
	
	/**
	 * 查询数据字典子表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findDictionarydListByDictId(HashMap map);
	
	/**
	 * 更新用户数据字典子表
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateDictdById(HashMap map);
	/**
	 * 新增数据字典子表
	 */
	@SuppressWarnings("unchecked") 
	public String insertDictionaryd(HashMap map)throws Exception;
	
	/**
	 * 根据某一ID数据字典删除子表数据
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteDictionarydById(HashMap map);
}