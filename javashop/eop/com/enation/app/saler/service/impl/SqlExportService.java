/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.app.saler.service.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.SQLNestedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;

public class SqlExportService {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	/**
	 * 备份文件，并且返回SQL语句
	 * @return
	 * @throws SQLNestedException 
	 */
	public String dumpSql() {
		List tables = getAllTableNames();
		return this.dumpSql();
		 
	}
	
	
	public String dumpSql(List<String> tables){
		String sql = "";
		if(tables == null)
			throw new RuntimeException("不存在数据库或者是没有表");//不存在数据库或者是没有表
		for(int i = 0; i < tables.size(); i++){
			String tabname= tables.get(i).toString();
		//	if(!tabname.equals("es_print_tmpl"))continue;
			if(tabname.startsWith("eop_"))continue;
			if(tabname.endsWith("widgetbundle"))continue;
			
			if(tabname.endsWith("border"))continue;
			if(tabname.endsWith("menu"))continue;
			if(tabname.endsWith("themeuri"))continue;
			if(tabname.endsWith("theme"))continue;
			if(tabname.endsWith("admintheme"))continue;
			String querySql  ;
			
			if("2".equals(EopSetting.RUNMODE)){
				EopSite site  = EopContext.getContext().getCurrentSite();
				querySql="show tables like 'es_"+ tabname +"_"+site.getUserid()+"_"+site.getId()+"'";
			}else{
				querySql="show tables like 'es_"+ tabname+"'";
			}
			
			List tblist  =this.simpleJdbcTemplate.queryForList(querySql);
			if(tblist==null || tblist.isEmpty()){
				continue;
			}			
			sql += dumpTableSql("es_"+tabname);
		}
		
		return sql ;
	}
	
	
	/**
	 * 获取所有表
	 * @return
	 */
	public List getAllTableNames() {
		List tableList = new ArrayList();
		List list = simpleJdbcTemplate.queryForList("show tables");
		for(int i = 0; i < list.size(); i++){
			Map map = (Map)list.get(i);
			Object[] keyArray = map.keySet().toArray();
			for(int j = 0; j < keyArray.length; j++){
				if(map.get(keyArray[j].toString()) != null)
					tableList.add(map.get(keyArray[j].toString()).toString());
			}
		}
		return tableList;
	}

	/**
	 * 生成一个表的sql文件
	 * @param table
	 * @return
	 */
	public String dumpTableSql(String table) throws BadSqlGrammarException,EmptyResultDataAccessException{
	//	System.out.println("备份：" + table);

		
		
		String sql ="truncate table "+ table+"_<userid>_<siteid>;\n";//"DROP TABLE IF EXISTS " + table + ";\n";
//		sql+="truncate table "+ table+"_<userid>_<siteid>;\n";
		//sql += getCreateTableSql(table);
		//sql += getTableStatus(table);
		sql += getInsertSql(table);
		return sql;
	}
	
	
 
	
	/**
	 * 获取一个表的建表语句
	 * @param table
	 * @return
	 */
	public String getCreateTableSql(String table)throws BadSqlGrammarException{
		Map map = simpleJdbcTemplate.queryForMap("SHOW CREATE TABLE "+table+";");
		byte[] bytes = (byte[])map.get("Create Table");
		String sql = new String(bytes);
		return sql;
	}
	
	/**
	 * 获取表的状态SQL,如自增值等
	 * @param table
	 * @return
	 */
	public String getTableStatus(String table)throws EmptyResultDataAccessException{
		String sql = "";
		Map map = simpleJdbcTemplate.queryForMap("SHOW TABLE STATUS LIKE '"+table+"'");
		if(map != null){
			if(map.get("Auto_increment") != null && !map.get("Auto_increment").equals("")){
				sql = "  AUTO_INCREMENT=" + map.get("Auto_increment").toString();
			}
		}
		sql += ";\n\n";
		return sql;
	}
	
	/**
	 * 获取插入数据的SQL
	 */
	public String getInsertSql(final String table)throws BadSqlGrammarException{
		 EopSite site  = EopContext.getContext().getCurrentSite();

		final Integer userid  = site.getUserid();
		final Integer siteid = site.getId();
		String rname = table;
		if("2".equals(EopSetting.RUNMODE)){
			rname  =table+"_"+userid+"_"+siteid;
		}
		StringBuffer sql = new StringBuffer();
		
		int total = simpleJdbcTemplate.queryForInt("SELECT COUNT(0) FROM " + rname);
		int pageSize = 200;
		int pageTotal = (int) Math.ceil((double) total / pageSize);
		
		ParameterizedRowMapper mapper = new ParameterizedRowMapper() {
			
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			 
		 
				StringBuffer sb = new StringBuffer();
				ResultSetMetaData rsmd = rs.getMetaData();
				sb.append("INSERT INTO "+table+"_<userid>_<siteid> VALUES (");
				int count = rsmd.getColumnCount();
				String comma = "";
				for(int i = 1; i <= count; i++){
//					System.out.println(rsmd.getColumnName(i));
					String value = rs.getString(rsmd.getColumnName(i));
					if(value != null){
						sb.append(comma + "\'" + mysql_escape_string(value) + "\'");
					}else{
						sb.append(comma + "null");
					}
					comma = ",";
//					System.out.println(rsmd.getColumnName(i) + ":" + rs.getString(rsmd.getColumnName(i)));
				}
				sb.append(");\n");
				return sb.toString();
			}
		};
		
		String  querysql;
		List list = null;
		
		for(int i = 1; i <= pageTotal; i++){
			querysql  =  "SELECT * FROM "+rname+" LIMIT "+((i-1)*pageSize)+", " + pageSize;
			list = simpleJdbcTemplate.query(querysql,mapper);
			for(int j = 0; j < list.size(); j++){
				sql.append(list.get(j).toString());
			}
		}
		
		return sql.toString();
	}
	
	/**
	 * 获取mysql的版本信息
	 * @return
	 */
	public String getMySqlVersion(){
		String version = "";
		Map map = simpleJdbcTemplate.queryForMap("SELECT version() as version");
		version = map.get("version").toString();		
		return version;
	}
	
	/**
	 * 转义SQL语句中的字符
	 * @param str
	 * @return
	 */
	private String mysql_escape_string(String str){
	       if( str == null || str.length() == 0 )
	           return str;
	       str = str.replaceAll("'", "\\\\\'");
	       str = str.replaceAll("\"", "\\\"");     
	       str = str.replaceAll("\r", "");
	       str = str.replaceAll("\n", "");
	       return str;
	}

	
	public static void main(String args[]){
		System.out.println("<p'dd'a>".replaceAll("'", "\\\\'"));
	}
	
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
}
