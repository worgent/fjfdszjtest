package com.qzgf.application.biz.custom.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.utils.PinyinConv;

/**
 * 自定义实现类
 *
 */
public class CustomFacadeImpl implements CustomFacade { 
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(CustomFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	/**
	 * 保存自定义主对象
	 * @param custom
	 * @param num
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean saveFieldCustom(Map custom,int num){
			//1.插入主表的数据
		Map custommap=new HashMap();
		String pb_seq=(String)baseSqlMapDAO.queryForObject("Custom.createId", null);
		custommap.put("id", pb_seq);
		custommap.put("patternType", (String)custom.get("patternType"));
		custommap.put("patternName", (String)custom.get("patternName"));
		custommap.put("remark", (String)custom.get("remark"));
		custommap.put("maker", custom.get("maker").toString());
		custommap.put("state", (String)custom.get("state"));
		custommap.put("events", (String)custom.get("events"));
		//获得模板的表名
		String tableName=(String)baseSqlMapDAO.queryForObject("Custom.createTableId", null);
		custommap.put("tableName", tableName);
		int flag=baseSqlMapDAO.update("Custom.insertPattern", custommap);
		//int customid=(Integer)custommap.get("customid");
		if(flag>0){	
			try {
				//获得模板的表名
				//String table_seq=(String)baseSqlMapDAO.queryForObject("Custom.createTableId", null);
				//custommap.put("table_seq", table_seq);
				baseSqlMapDAO.update("Custom.createTable", custommap);
				//插入子表的数据
				this.addFieldInfo(custom,num,pb_seq,tableName);
				//创建数据库
				
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * 保存自定义主对象
	 * @param custom
	 * @param num
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateFieldCustom(Map custom,int num){
		
		//根据ID删除自定义表
		baseSqlMapDAO.delete("Custom.deletePatterndTable", custom);
		
		//1.更新主表的数据
		Map custommap=new HashMap();
		String id=(String)custom.get("id");
		custommap.put("id", id);
		custommap.put("patternType", (String)custom.get("patternType"));
		custommap.put("patternName", (String)custom.get("patternName"));
		custommap.put("remark", (String)custom.get("remark").toString().trim());
		custommap.put("maker", custom.get("maker").toString());
		custommap.put("state", (String)custom.get("state"));
		custommap.put("events", (String)custom.get("events"));
		
		//获得模板的表名
		String tableName=(String)baseSqlMapDAO.queryForObject("Custom.createTableId", null);
		custommap.put("tableName",tableName);
		int flag=baseSqlMapDAO.update("Custom.updatePattern", custommap);
		baseSqlMapDAO.update("Custom.deletePatterndByFid", custommap);
		//int customid=(Integer)custommap.get("customid");
		if(flag>0){	
			try {
				//获得模板的表名
				//String table_seq=(String)baseSqlMapDAO.queryForObject("Custom.createTableId", null);
				//custommap.put("table_seq", table_seq);
				baseSqlMapDAO.update("Custom.createTable", custommap);
				//插入子表的数据
				this.addFieldInfo(custom,num,id,tableName);
				//创建数据库
				
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		System.out.println("返回的结果："+flag);
		return true;
	}
	
	
	/**
	 * 保存自定义子对象
	 * @param custom
	 * @param num
	 * @param customId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void addFieldInfo(Map custom, int num, String customId,String tableName)
			throws Exception {
		int st = 0;
		HashMap map=new HashMap();
		
		if (num == 1) {	
			map.put("id", customId);
			String fielddesc=(String)custom.get("fielddesc");
			String fieldname=new String(PinyinConv.cn2py(fielddesc));
			map.put("fieldseqn", (String)custom.get("fieldseqn"));
			map.put("fielddesc", (String)custom.get("fielddesc"));
			map.put("fieldname", fieldname);
			map.put("fieldtype", (String)custom.get("fieldtype"));
			map.put("fieldlength", (String)custom.get("fieldlength"));
			map.put("fieldremark", (String)custom.get("fieldremark"));
			map.put("fieldstate", (String)custom.get("fieldstate"));
			
			map.put("tableName", tableName);
			
			
			
			st=baseSqlMapDAO.update("Custom.insertPatternd", map);
			baseSqlMapDAO.update("Custom.addField", map);
			if (st < 1) {
				throw new Exception("插入数据有误.");
			}
		}else if (num > 1) {
			
			String[] fieldseqn=(String[])custom.get("fieldseqn");
			String[] fielddesc=(String[])custom.get("fielddesc");
			String[] fieldtype=(String[])custom.get("fieldtype");
			String[] fieldlength=(String[])custom.get("fieldlength");
			String[] fieldremark=(String[])custom.get("fieldremark");
			String[] fieldstate=(String[])custom.get("fieldstate");
			String[] fieldenum=(String[])custom.get("fieldenum");
			map.put("tableName", tableName);
			
			
			for (int i = 0; i < num; i++) {
				map = new HashMap();
				map.put("id", customId);
				String fielddesc1=""+fielddesc[i];
				String fieldname1=new String(PinyinConv.cn2py(fielddesc1)+i);
				map.put("fieldseqn", fieldseqn[i]);
				map.put("fielddesc", fielddesc[i]);
				map.put("fieldname", fieldname1);
				map.put("fieldtype", fieldtype[i]);
				map.put("fieldlength", fieldlength[i]);
				map.put("fieldremark", fieldremark[i]);
				map.put("fieldstate", fieldstate[i]);
				map.put("fieldenum", fieldenum[i]);
				map.put("tableName", tableName);
				
				st=baseSqlMapDAO.update("Custom.insertPatternd", map);
				baseSqlMapDAO.update("Custom.addField", map);
				if (st < 1) {
					throw new Exception("添加自定义字段失败!");
				}
			}
			
		}
	}
	
	/**
	 * 查询所有的模板列表根据模板类别
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findPatternList(Map map){ 
		return baseSqlMapDAO.queryForList("Custom.findPatternList", map);
	}
	
	/**
	 * 根据模板编号查询该模板的信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map findCustomById(Map map){
		List customList=baseSqlMapDAO.queryForList("Custom.findCustomById", map);
		if(!customList.isEmpty() && customList!=null){
			return (HashMap)(customList.get(0)); 
		}
		else return null;
	
	}
	
	/**
	 * 删除自定义信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	public void delCustomById(Map map){
		String info=null;
		//查询该模板关联的表
		map.put("info","");
		baseSqlMapDAO.update("Custom.delCustomById",map);
		info=(String)map.get("info"); 
		System.out.println("返回的信息："+info);
	}
	
	/**
	 * 查询系统定义且正启用的事件
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEvents(){ 
		return baseSqlMapDAO.queryForList("Custom.findEvents", null);
	}
	
	/**
	 * 查询所有的枚举来源
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEnums(){ 
		return baseSqlMapDAO.queryForList("Custom.findEnums", null);
	}
	
	/**
	 * 查询自定义项
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCustomItems(Map map){   
		return baseSqlMapDAO.queryForList("Custom.findCustomItems", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
