package com.qzgf.application.systemManage.dictionary.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 数据字典
 * @author lsr
 * 
 */
public class DictionaryFacadeImpl implements DictionaryFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory
			.getLog(DictionaryFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	@SuppressWarnings("unchecked")
	public List findDictionaryList(){
		List dictionaryList = baseSqlMapDAO.queryForList("Dictionary.findDictionary", null);
		return dictionaryList;
	}

	/**
	 * 新增数据字典
	 */
	@SuppressWarnings("unchecked") 
	public String insertDictionary(HashMap map)throws Exception{ 
		baseSqlMapDAO.update("Dictionary.insertDictionary", map);
		return "1";
	}
	
	/**
	 * 根据字典编号查询该字典名称
	 * @param id
	 * @return
	 */
	public String findDictNameByDictId(String id){
		return (String)baseSqlMapDAO.queryForObject("Dictionary.findDictNameByDictId",id);
	}
	
	/**
	 * 更新用户数据字典
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateDictNameById(HashMap map) {
		baseSqlMapDAO.update("Dictionary.updateDictById", map);
		// 修改成功
		return true;
		
	}
	
	/**
	 * 根据某一ID数据字典
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteDictById(HashMap map) {
		//删除父表
		int num=0;
		try {
			num = baseSqlMapDAO.update("Dictionary.deleteDictById", map);
			if(num==1){
				//删除子表
				baseSqlMapDAO.update("Dictionary.deleteDictionarydByPid", map);
				num=1;
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			num=0;
		}
		return num;
	}
	
	/**
	 * 查询数据字典子表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findDictionarydListByDictId(HashMap map){
		List dictionarydList = baseSqlMapDAO.queryForList("Dictionary.findDictionarydByDictId", map);
		return dictionarydList;
	}
	
	/**
	 * 更新用户数据字典子表
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateDictdById(HashMap map) {
		baseSqlMapDAO.update("Dictionary.updateDictdById", map);
		// 修改成功
		return true;
		
	}
	
	/**
	 * 新增数据字典子表
	 */
	@SuppressWarnings("unchecked") 
	public String insertDictionaryd(HashMap map)throws Exception{ 
		baseSqlMapDAO.update("Dictionary.insertDictionaryd", map);
		return "1";
	}
	
	/**
	 * 根据某一ID数据字典删除子表数据
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteDictionarydById(HashMap map) {
		//删除父表
		int num=0;
		try {
			num = baseSqlMapDAO.update("Dictionary.deleteDictionarydById", map);
			if(num==1){
				num=1;
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			num=0;
		}
		return num;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
