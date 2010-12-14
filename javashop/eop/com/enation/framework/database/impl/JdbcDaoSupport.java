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
package com.enation.framework.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

import com.enation.eop.EopSetting;
import com.enation.framework.database.DBRuntimeException;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.framework.util.ReflectionUtil;
import com.enation.framework.util.StringUtil;

/**
 * jdbc数据库操作支撑
 * 
 * @author kingapex 2010-1-6下午01:54:18
 * @param <T>
 */
public class JdbcDaoSupport<T> implements IDaoSupport<T> {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	protected final Logger logger = Logger.getLogger(getClass());
	
	public void execute(String sql, Object... args) {
		try{
			this.simpleJdbcTemplate.update(sql, args);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		} 
	}

	
	public int getLastId(String table) {
 	   
		if(EopSetting.DBTYPE.equals("1")){
		//	System.out.println("dbtype is mysql");
	
			return this.jdbcTemplate
			.queryForInt("SELECT last_insert_id() as id");
		
		}else{
			int result = 0;
	    	result = this.jdbcTemplate
			.queryForInt("SELECT s_" + table + ".currval as id from DUAL" );
	    	return result;
		}
	}
 

	
	public void insert(String table, Map fields) {
		String sql = "";

		try {
		 
			Assert.hasText(table, "表名不能为空");
			Assert.notEmpty(fields, "字段不能为空");
			table = quoteCol(table);

			Object[] cols = fields.keySet().toArray();
			Object[] values = new Object[cols.length];
			for (int i = 0; i < cols.length; i++) {
				if (fields.get(cols[i]) == null) {
					values[i] = null;
				} else {
					values[i] = fields.get(cols[i]).toString();
				}
				cols[i] = quoteCol(cols[i].toString());
			}

			sql = "INSERT INTO " + table + " ("
					+ StringUtil.implode(", ", cols) + ") VALUES ("
					+ StringUtil.implodeValue(", ", values) + ")";
		//	System.out.println(sql);
			jdbcTemplate.update(sql, values);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DBRuntimeException(e, sql);
		}
	}

	
	public void insert(String table, Object po) {
		insert(table, ReflectionUtil.po2Map(po));
	}

	
	public int queryForInt(String sql, Object... args) {
		try{
			return this.simpleJdbcTemplate.queryForInt(sql, args);
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			throw e;
		}
	}


	
	public String queryForString(String sql) {
		String s="";
		try{
			s=(String)this.jdbcTemplate.queryForObject(sql, String.class);
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}
		return s;
	}

	
	@SuppressWarnings("unchecked")
	
	public List queryForList(String sql, Object... args) {
		return this.jdbcTemplate.queryForList(sql, args);
	}

	
	public List<T> queryForList(String sql, RowMapper mapper, Object... args) {
		try {
			return this.jdbcTemplate.query(sql, args, mapper);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	
	public List<T> queryForList(String sql, Class<T> clazz, Object... args) {
		 
		return this.simpleJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);

	}

	
	public List queryForListPage(String sql, int pageNo, int pageSize,
			Object... args) {

		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return queryForList(listSql, args);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}

	}

	
	public List<T> queryForList(String sql, int pageNo, int pageSize,
			RowMapper mapper) {

		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return this.queryForList(listSql, mapper);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}

	}

	
	public long queryForLong(String sql, Object... args) {
		return this.simpleJdbcTemplate.queryForLong(sql, args);
	}

	
	public Map queryForMap(String sql, Object... args) {
//		Map map = this.simpleJdbcTemplate.queryForMap(sql, args);
		Map map = this.jdbcTemplate.queryForMap(sql, args);
		if(EopSetting.DBTYPE.equals("2")){
			Map newMap  = new HashMap();
		Iterator keyItr = map.keySet().iterator();
			while(keyItr.hasNext()){
				String key = (String)keyItr.next();
				Object value = map.get(key);
				newMap.put(key.toLowerCase(), value);
			}
			return newMap;
		}else
		return map;
	}

	
	public T queryForObject(String sql, Class clazz, Object... args) {
		try{
			return (T)simpleJdbcTemplate
			.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
//			return  (T) this.jdbcTemplate.queryForObject(sql, args, clazz);
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new ObjectNotFoundException(ex, sql);
		}
	}  
	
 

	
	public Page queryForPage(String sql, int pageNo, int pageSize,
			Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List list = queryForList(listSql, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	
	public Page queryForPage(String sql, int pageNo, int pageSize,
			RowMapper rowMapper, Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, rowMapper, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	
	public Page queryForPage(String sql, int pageNo, int pageSize,
			Class<T> clazz, Object... args) {
	 
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, clazz, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	
	
	public void update(String table, Map fields, Map where) {
		String whereSql = "";
		 
		if (where != null) {
			Object[] wherecols = where.keySet().toArray();
			for (int i = 0; i < wherecols.length; i++) {
				wherecols[i] = quoteCol(wherecols[i].toString()) + "="
						+ quoteValue(where.get(wherecols[i]).toString());
			}
			whereSql += StringUtil.implode(" AND ", wherecols);
		}
		update(table, fields, whereSql);
	}

	
	public void update(String table, T po, Map where) {
		String whereSql = "";
		// where值
		if (where != null) {
			Object[] wherecols = where.keySet().toArray();
			for (int i = 0; i < wherecols.length; i++) {
				wherecols[i] = quoteCol(wherecols[i].toString()) + "="
						+ quoteValue(where.get(wherecols[i]).toString());
			}
			whereSql +=StringUtil.implode(" AND ", wherecols);
		}
		update(table, ReflectionUtil.po2Map(po), whereSql);

	}

	
	public void update(String table, T po, String where) {
		 this.update(table,ReflectionUtil.po2Map(po), where);

	}
	
	
	public void update(String table, Map fields, String where){
		String sql = "";
		try {
			Assert.hasText(table, "表名不能为空");
			Assert.notEmpty(fields, "字段不能为空");
			Assert.hasText(where, "where条件不能为空");
			table = quoteCol(table);

			// 字段值
			Object[] cols = fields.keySet().toArray();

			Object[] values = new Object[cols.length];
			for (int i = 0; i < cols.length; i++) {
				if (fields.get(cols[i]) == null) {
					values[i] = null;
				} else {
					values[i] = fields.get(cols[i]).toString();
				}
				cols[i] = quoteCol(cols[i].toString()) + "=?";

			}
			sql = "UPDATE " + table + " SET " + StringUtil.implode(", ", cols)
					+ " WHERE " + where;
			 
			simpleJdbcTemplate.update(sql, values);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}		
	}
	

	public String buildPageSql(String _sql, int page, int pageSize) {

		String sql_str = null;


		String db_type = EopSetting.DBTYPE;
		if (db_type.equals("1")) {
			db_type = "mysql";
		} else {
			db_type = "oracle";
		}

		if (db_type.equals("mysql")) {
			sql_str = _sql + " LIMIT " + (page - 1) * pageSize + "," + pageSize;
		} else {
			StringBuffer sql = new StringBuffer(
					"SELECT * FROM (SELECT t1.*,rownum sn1 FROM (");
			sql.append(_sql);
			sql.append(") t1) t2 WHERE t2.sn1 BETWEEN ");
			sql.append((page - 1) * pageSize + 1);
			sql.append(" AND ");
			sql.append(page * pageSize);
			sql_str = sql.toString();
		}

		return sql_str.toString();

	}

	
	/**
	 * 格式化列名 只适用于Mysql
	 * 
	 * @param col
	 * @return
	 */
	private String quoteCol(String col) {
		if (col == null || col.equals("")) {
			return "";
		} else {
			return col;
		}
	}

	/**
	 * 格式化值 只适用于Mysql
	 * 
	 * @param value
	 * @return
	 */
	private String quoteValue(String value) {
		if (value == null || value.equals("")) {
			return "''";
		} else {
			return "'" + value.replaceAll("'", "''") + "'";
		}
	}	
	
	
	/**
	 * 去除sql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}


	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	
	public T queryForObject(String sql, ParameterizedRowMapper mapper,
			Object... args) {
		return (T)this.simpleJdbcTemplate.queryForObject(sql, mapper, args);
	}

 


}
