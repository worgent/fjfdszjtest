package com.qzgf.application.biz.report.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 自定义报表实现类
 * 
 * @author lsr
 * 
 */
public class CustomReportFacadeImpl extends JdbcDaoSupport implements CustomReportFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory
			.getLog(CustomReportFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	@SuppressWarnings("unchecked")
	public List findCustomReport() {
		List crList = baseSqlMapDAO.queryForList(
				"CustomReport.findCustomReport", null);
		return crList;
	}

	/**
	 * 查询自定义报表的查询条件字段
	 */
	@SuppressWarnings("unchecked")
	public List findQueryField(HashMap map) {
		List crList = baseSqlMapDAO.queryForList("CustomReport.findQueryField",
				map);
		return crList;
	}
	
	/**
	 * 查询自定义报表显示的字段
	 */
	@SuppressWarnings("unchecked")
	public List findReportResultFieldList(String id) {
		List fieldList = baseSqlMapDAO.queryForList("CustomReport.findReportResultFieldList",
				id);
		return fieldList;
	}

	/**
	 * 查询枚举值列表
	 */
	@SuppressWarnings("unchecked")
	public List findEnumList(String id) {
		List crList = baseSqlMapDAO.queryForList("CustomReport.findEnumList",
				id);
		return crList;
	}
	
	/**
	 * 用于导出Excel
	 * @param query
	 * @param dayMap1
	 * @param dayMap2
	 * @param parentType
	 * @param reportId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execExport(HashMap query,HashMap dayMap1,HashMap dayMap2, String parentType,String reportId){
		//列出所有的输出报表字段,用于拼装Excel表头
		List resultFieldList=baseSqlMapDAO.queryForList("CustomReport.findReportResultFieldList",reportId);
		
		//表体内容
		Map fieldmap=new HashMap();
		List patternFieldList = baseSqlMapDAO.queryForList("CustomReport.findPatternField",reportId);
		if(!patternFieldList.isEmpty() && patternFieldList!=null){
    		fieldmap=(HashMap)(patternFieldList.get(0));
    	}
		String childTable=(String)fieldmap.get("tablename");
		String masterTable="";
		if(parentType.equals("1")){
			masterTable="t_dailywork";//日常工作
		}else if(parentType.equals("2")){
			masterTable="T_ToMonitor";//督办工作
		}else if(parentType.equals("3")){
			masterTable="T_Declare";//事项申报
		}
		
		String sql_enum1="";//枚举值列表
		String sql_enum2="";//枚举值条件
		String sql_z="";//工作单主表查询条件
		String sql_f="";//模板子表查询条件
		
		//列出是枚举型的字段
		List isEnumFieldList=this.baseSqlMapDAO.queryForList("CustomReport.findIsEnumFieldByReportId", reportId);
		int num=1;
		// 有主表的字段
		Map enumItem = new HashMap();
		java.util.Iterator enumit = isEnumFieldList.iterator();
		while (enumit.hasNext()) {
			enumItem = (HashMap) enumit.next();   
			sql_enum1=sql_enum1+",m"+num+".dictvalue "+enumItem.get("fieldname")+"val" ;
			sql_enum2=sql_enum2+" left join t_dictionaryd m"+num+" on u."+enumItem.get("fieldname")+"="+"m"+num+".id" ;
			num++;
		}
		
		List masterFields = new ArrayList();
		List masterFieldsList=new ArrayList();
		if (parentType != null && parentType.equals("1")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "1");
		} else if (parentType != null && parentType.equals("2")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "2");
		} else if (parentType != null && parentType.equals("3")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "3");
		}

		if (masterFields == null) {
			// 没有主表的字段
		} else {
			// 有主表的字段
			Map item = new HashMap();
			java.util.Iterator it = masterFields.iterator();
			while (it.hasNext()) {
				item = (HashMap) it.next();
				masterFieldsList.add(item.get("fieldname"));
			}

			// 判断普通的查询字段是否是属于主表的字段
			Set<String> keySet = query.keySet();
			for (String key : keySet) {
                if(masterFieldsList.contains(key)){
                	//工单主表的字段
                	if(query.get(key)!=null&&!query.get(key).equals("")){
                		sql_z=sql_z+" and m."+key+"='"+query.get(key)+"'";
                	}
                }else{
                	//反馈单子表的字段
                	if(query.get(key)!=null&&!query.get(key).equals("")){
                		sql_f=sql_f+" and u."+key+"='"+query.get(key)+"'";
                	}
                }
			}
			
			Set<String> daySet = dayMap1.keySet();
			for (String key : daySet) {
                if(masterFieldsList.contains(key)){
                	//主表日期
                	if(dayMap2.get(key+"start")!=null&&!"".equals(dayMap2.get(key+"start"))){
                		sql_z=sql_f+" and m."+key+">=cast('"+dayMap2.get(key+"start")+"' as date)";
                	}
                	
                	//反馈单子表的字段
                	if(dayMap2.get(key+"end")!=null&&!"".equals(dayMap2.get(key+"end"))){
                		sql_z=sql_f+" and m."+key+"<=cast('"+dayMap2.get(key+"end")+"' as date)";
                	}
                }else{
                	//反馈单子表的字段
                	if(dayMap2.get(key+"start")!=null&&!"".equals(dayMap2.get(key+"start"))){
                		sql_f=sql_f+" and u."+key+">=cast('"+dayMap2.get(key+"start")+"' as date)";
                	}
                	
                	//反馈单子表的字段
                	if(dayMap2.get(key+"end")!=null&&!"".equals(dayMap2.get(key+"end"))){
                		sql_f=sql_f+" and u."+key+"<=cast('"+dayMap2.get(key+"end")+"' as date)";
                	}
                }
			}
			
			String sql="select u.*,m.* "+sql_enum1+" from "+masterTable+" m,"+childTable+" u "+sql_enum2+" where u.parentId=m.id";
			
			
			sql=sql+sql_z+sql_f;
			
			List list=this.getJdbcTemplate().queryForList(sql);
			String reportName=(String)((Map)baseSqlMapDAO.queryForList("CustomReport.findReportName", reportId).get(0)).get("reportName");
			//拼装表头
			StringBuffer buffer=new StringBuffer("<table border='1'>");
			buffer.append("<tr height='26'>"+
			        		"<td colspan='"+resultFieldList.size()+"' height='39' align='center'><font size='5'><strong>"+reportName+"</strong></font></td>"+
			       		  "</tr>");
			//添加字段名
			Map fieldMap = new HashMap();
			if(resultFieldList.size()>0){
				buffer.append("<tr style='background-color: #B1C6CD'>");
				java.util.Iterator fieldIt = resultFieldList.iterator();
				while (fieldIt.hasNext()) {
					fieldMap=(HashMap)fieldIt.next();
					buffer.append("	<td align='center' width='150'>"+fieldMap.get("fielddesc")+"</td>");
				}
				buffer.append("</tr>");
			}
			
			//添加字段名
			Map valMap = new HashMap();
			if(list.size()>0){
				java.util.Iterator valIt = list.iterator();
				int i=0;//用于区别单双行
				while (valIt.hasNext()) {
					i++;
					if(i%2==0){
						buffer.append("<tr style='background-color:#FEFEFC'>");
					}else{
						buffer.append("<tr style='background-color:#E7EDEF'>");
					}
					
					valMap=(HashMap)valIt.next();
					//添加字段名
					Map fieldMap1 = new HashMap();
					if(resultFieldList.size()>0){
						
						java.util.Iterator fieldIt = resultFieldList.iterator();
						while (fieldIt.hasNext()) {
							fieldMap1=(HashMap)fieldIt.next();
							if(fieldMap1.get("fieldtype").equals("enum")){//枚举型
								buffer.append("	<td align='center' width='150'>"+(valMap.get(fieldMap1.get("fieldname")+"val")==null?"":valMap.get(fieldMap1.get("fieldname")+"val"))+"</td>");
							}else if(fieldMap1.get("fieldtype").equals("date")){//日期型
								buffer.append("	<td align='center' width='150'>"+(valMap.get(fieldMap1.get("fieldname"))==null?"":valMap.get(fieldMap1.get("fieldname")))+"&nbsp;"+"</td>");
							}else{
								buffer.append("	<td align='center' width='150'>"+(valMap.get(fieldMap1.get("fieldname"))!=null?valMap.get(fieldMap1.get("fieldname")):"")+"</td>");
							}
						}
						
					}
					
				}
				buffer.append("</tr>");
			}
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			//表尾
			buffer.append("<tr height='24'>"+
					"<td colspan='"+resultFieldList.size()+"' valign='middle'><font size='4'><strong>制表日期：</strong></font>"+f.format(new Date())+
        			"&nbsp;&nbsp;&nbsp;&nbsp;<font size='4'><strong>制表人：</strong></font>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
        			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<font size='4'><strong>审核人：</strong></font>"+
        			"</td>"+
	       		  "</tr>");
			
			buffer.append("</table>");
			return buffer.toString(); 
			
		}
		
		return "";
	}

	/**
	 * 分页查询自定义报表数据
	 */
	@SuppressWarnings("unchecked")
	public PageList queryReportList(HashMap query,HashMap dayMap1,HashMap dayMap2, String parentType,String reportId,Pages pages) {
		
		Map fieldmap=new HashMap();
		List patternFieldList = baseSqlMapDAO.queryForList("CustomReport.findPatternField",reportId);
		if(!patternFieldList.isEmpty() && patternFieldList!=null){
    		fieldmap=(HashMap)(patternFieldList.get(0));
    	}
		String childTable=(String)fieldmap.get("tablename");
		String masterTable="";
		if(parentType.equals("1")){
			masterTable="t_dailywork";//日常工作
		}else if(parentType.equals("2")){
			masterTable="T_ToMonitor";//督办工作
		}else if(parentType.equals("3")){
			masterTable="T_Declare";//事项申报
		}
		
		String sql_enum1="";//枚举值列表
		String sql_enum2="";//枚举值条件
		String sql_z="";//工作单主表查询条件
		String sql_f="";//模板子表查询条件
		
		//列出是枚举型的字段
		List isEnumFieldList=this.baseSqlMapDAO.queryForList("CustomReport.findIsEnumFieldByReportId", reportId);
		int num=1;
		// 有主表的字段
		Map enumItem = new HashMap();
		java.util.Iterator enumit = isEnumFieldList.iterator();
		while (enumit.hasNext()) {
			enumItem = (HashMap) enumit.next();   
			sql_enum1=sql_enum1+",m"+num+".dictvalue "+enumItem.get("fieldname")+"val" ;
			sql_enum2=sql_enum2+" left join t_dictionaryd m"+num+" on u."+enumItem.get("fieldname")+"="+"m"+num+".id" ;
			num++;
		}
		
		List masterFields = new ArrayList();
		List masterFieldsList=new ArrayList();
		if (parentType != null && parentType.equals("1")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "1");
		} else if (parentType != null && parentType.equals("2")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "2");
		} else if (parentType != null && parentType.equals("3")) {
			masterFields = baseSqlMapDAO.queryForList(
					"CustomReport.findMasterFields", "3");
		}

		if (masterFields == null) {
			// 没有主表的字段
		} else {
			// 有主表的字段
			Map item = new HashMap();
			java.util.Iterator it = masterFields.iterator();
			while (it.hasNext()) {
				item = (HashMap) it.next();
				masterFieldsList.add(item.get("fieldname"));
			}

			// 判断普通的查询字段是否是属于主表的字段
			Set<String> keySet = query.keySet();
			for (String key : keySet) {
                if(masterFieldsList.contains(key)){
                	//工单主表的字段
                	if(query.get(key)!=null&&!query.get(key).equals("")){
                		sql_z=sql_z+" and m."+key+"='"+query.get(key)+"'";
                	}
                }else{
                	//反馈单子表的字段
                	if(query.get(key)!=null&&!query.get(key).equals("")){
                		sql_f=sql_f+" and u."+key+"='"+query.get(key)+"'";
                	}
                }
			}
			
			Set<String> daySet = dayMap1.keySet();
			for (String key : daySet) {
                if(masterFieldsList.contains(key)){
                	//主表日期
                	if(dayMap2.get(key+"start")!=null&&!"".equals(dayMap2.get(key+"start"))){
                		sql_z=sql_f+" and m."+key+">=cast('"+dayMap2.get(key+"start")+"' as date)";
                	}
                	
                	//反馈单子表的字段
                	if(dayMap2.get(key+"end")!=null&&!"".equals(dayMap2.get(key+"end"))){
                		sql_z=sql_f+" and m."+key+"<=cast('"+dayMap2.get(key+"end")+"' as date)";
                	}
                }else{
                	//反馈单子表的字段
                	if(dayMap2.get(key+"start")!=null&&!"".equals(dayMap2.get(key+"start"))){
                		sql_f=sql_f+" and u."+key+">=cast('"+dayMap2.get(key+"start")+"' as date)";
                	}
                	
                	//反馈单子表的字段
                	if(dayMap2.get(key+"end")!=null&&!"".equals(dayMap2.get(key+"end"))){
                		sql_f=sql_f+" and u."+key+"<=cast('"+dayMap2.get(key+"end")+"' as date)";
                	}
                }
			}
			
			String sql="select u.*,m.* "+sql_enum1+" from "+masterTable+" m,"+childTable+" u "+sql_enum2+" where u.parentId=m.id";
			//页数判断
			//加分布控制
			PageList pl = new PageList();
			//获得总数
			if (pages.getTotalNum() == -1) {
				String totalSql="select count(*) "+" from "+masterTable+" m,"+childTable+" u "+sql_enum2+" where u.parentId=m.id"+sql_z+sql_f;
				int total=this.getJdbcTemplate().queryForInt(totalSql);
				pages.setTotalNum(total);
			}
			//计算总页数
			pages.executeCount();
			//开始记录
			int start= pages.getSpage();
			//最多显示记录数
			int end= pages.getEpage();
			sql=sql+sql_z+sql_f+" limit "+start+","+end;
			System.out.println(sql);
			List list=this.getJdbcTemplate().queryForList(sql);
			pl.setObjectList(list);
			//分页信息
			pl.setPages(pages);
			return pl;
		}

		return null;
	}
	
	/**
	 * 获得报表名称
	 * @param reportId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getReportName(String reportId){
		String reportName=(String)((Map)baseSqlMapDAO.queryForList("CustomReport.findReportName", reportId).get(0)).get("reportName");
		return reportName;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
