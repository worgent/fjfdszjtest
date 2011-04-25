package com.qzgf.application.baseArchives.Mailfee.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.utils.export.ImportIface;


public class MailfeeFacadeImpl implements MailfeeFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	@SuppressWarnings("unchecked")
	public int insertTdMailfeearea(HashMap map){
		return baseSqlMapDAO.update("TdMailfeearea.insertTdMailfeearea", map);
	}
	
	/**
	 * 
	 * Purpose      : 记录数返回
	 * @param map
	 * @return
	 */
	public int countTdMailfeearea(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("TdMailfeearea.findTdMailfeeareaCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTdMailfeeareaById(HashMap map) {
		return baseSqlMapDAO.update("TdMailfeearea.deleteTdMailfeeareaById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int allproTdMailfeeareaById(HashMap map) {
		return baseSqlMapDAO.update("TdMailfeearea.allproTdMailfeeareaById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTdMailfeeareaById(HashMap map) {
		return baseSqlMapDAO.update("TdMailfeearea.updateTdMailfeeareaById", map);
	}
	@SuppressWarnings("unchecked")
	public List findTdMailfeearea(HashMap map) {
		return baseSqlMapDAO.queryForList("TdMailfeearea.findTdMailfeeareaPage", map);
	}
	
	/**
	 * 
	 * Purpose      : 导入excel数据(针对封发局处理时,需要独立先删除全部数据)
	 * @param exportExc:excel数据导入方法.
	 * 
	 * @return
	 */
	public int insertExcelTdMailfeeare(ImportIface exportExc){
		//1.册除全部数据信息
		baseSqlMapDAO.update("TdMailfeearea.delallproTdMailfeeareaById", new HashMap());
		//2.导入数据信息
		int i=0;
		try{
		HashMap line = exportExc.readLine();
		while (line != null) {
			HashMap map=new HashMap();
			map=line;
			//对数据进行处理,然后导入
			String comaddress="";
			if(map.containsKey("pprovince_name")){
				comaddress+=map.get("pprovince_name").toString();
			}
			if(map.containsKey("pcity_name")){
				comaddress+=map.get("pcity_name").toString();	
			}
			if(map.containsKey("pcounty_name")){
				comaddress+=map.get("pcounty_name").toString();
			}
			//组合地址信息
			map.put("paddressname", comaddress);
			//增加数据
			i= baseSqlMapDAO.update("TdMailfeearea.insertTdMailfeearea", map);
			//循环处理信息
			line = exportExc.readLine();
		}
		//关闭连接
		exportExc.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return i;
	}


	/**
	 * Purpose      : 说明
	 * @return the baseSqlMapDAO
	 */
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	/**
	 * Purpose      : 说明
	 * @param baseSqlMapDAO the baseSqlMapDAO to set
	 */
	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
