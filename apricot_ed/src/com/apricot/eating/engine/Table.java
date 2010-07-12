/**
 * 
 */
package com.apricot.eating.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.NestedException;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class Table {
	public static final int DELETE = 3;
	public static final int INSERT = 1;
	public static final int INSERT_OR_UPDATE = 4;
	public static final int SELECT = 5;
	private static final String TIMESTAMP_FORMAT = "%Y-%m-%d %H:%i:%S";
	public static final int UPDATE = 2;
	private HashMap columns;
	private boolean isOr = false;
	private DyncParameterMap map;
	private String name;
	private int sqlType;
	public static final String TABLE_KEY = "com.apricot.eating.engine.Table.KEY";

	public HashMap getStaticDataKeyMap() {
		HashMap map = new HashMap();
		for (Iterator cols = columns.values().iterator(); cols.hasNext();) {
			Column c = (Column) cols.next();
			map.put(c.getName(), name);
		}
		return map;
	}

	Table() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void release() {
		this.columns.clear();
		this.map.clear();
	}

	public String getWhere(String aliasTabname) throws NestedException {
		StringBuffer where = new StringBuffer();
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			String v = map.getString(c.getName(), 0);
			if (DataUtil.isNull(v))
				continue; // No data
			if (!DataUtil.isNull(aliasTabname))
				where.append(aliasTabname).append(".");
			if (c.getType().startsWith("varchar")) {
				where.append(c.getName()).append(" like '%");
				where.append(v).append("%'");
			} else {
				where.append(c.getName()).append("=");
				where.append(format(v, c));
			}
			where.append((!isAnd())?" or  ":" and ");
			
		}
		if (where.length() > 0)
			return where.substring(0, where.length() - 5);
		return "";
	}

	public Iterator getColumns() {
		return columns.values().iterator();
	}

	private String format(String v, Column c) throws NestedException {
		if (c.getDefaultValueType() == Column.DEFAULT_VALUE) {
			if (DataUtil.isNull(v))
				v = c.getDefaultValue();
		}
		// 数据不能为空
		if (c.isNotNull() && DataUtil.isNull(v)) {
			throw new NestedException("SQL-001", new String[] { name, c.getName() });
		}
		// 数字
		if (c.getType().startsWith("decimal")) {
			if (DataUtil.isNull(v))
				return "0";
			return v;
		}
		// 日期时间
		if (c.getType().startsWith("datetime")) {
			if (DataUtil.isNull(v)) v="null";
			else v="'"+v+"'";
			return new StringBuffer("STR_TO_DATE(").append(v).append(",'").append(Table.TIMESTAMP_FORMAT).append("')").toString();
		}
		//日期
//		if (c.getType().startsWith("date")) {
//			if (DataUtil.isNull(v)) v="null";
//			else v="'"+v+"'";
//			return new StringBuffer("STR_TO_DATE(").append(v).append(",'").append("%Y-%m-%d").append("')").toString();
//		}
		return (v == null) ? "" : new StringBuffer("'").append(v).append("'").toString();
	}

	public List getAllSQL() throws NestedException {
		List l = new ArrayList();
		int cs = map.getMaxSize(null);
		for (int i = 0; i < cs; i++) {
			StringBuffer sql = null;
			switch (sqlType) {
			case Table.INSERT:
				sql = getInsertSQL(i);
				break;
			case Table.UPDATE:
				sql = getUpdateSQL(i);
				break;
			case Table.DELETE:
				sql = getDeleteSQL(i);
				break;
			}
			if (sql != null)
				l.add(sql);
		}
		return l;
	}
	
	private boolean and=false;

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

	private StringBuffer getSelectSQL(int index) throws NestedException {
		StringBuffer sql = new StringBuffer("select * from ").append(name);
		StringBuffer where = new StringBuffer();
		boolean sf = false;
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			String v = map.getString(c.getName(), index);
			if ("sf_id".equalsIgnoreCase(c.getName()))
				sf = true; // Exist shop id column
			if (DataUtil.isNull(v))
				continue; // No data
			if (c.getType().startsWith("varchar")) {
				where.append(c.getName()).append(" like '%");
				where.append(v).append("%'");
			} else {
				v = format(v, c);
				where.append(c.getName()).append("=");
				where.append(v).append("");
			}
			where.append((!isAnd())?" or  ":" and ");
		}
		if (where.length() > 0) {
			sql.append(" where (").append(where.substring(0, where.length() - 5));
			sql.append(")");
			if (sf) {
				sql.append(" and sf_id=");
				sql.append(map.getString("sf_id", "-1"));
			}
		}
		return sql;
	}

	private StringBuffer getDeleteSQL(int index) throws NestedException {
		StringBuffer sql = new StringBuffer("delete from ").append(name);
		StringBuffer where = new StringBuffer();
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			String v = map.getString(c.getName(), index);
			if (c.isWhereColumn()) {
				where.append(c.getName()).append("=").append(format(v, c));
				where.append((isOr) ? " or  " : " and ");
			}
		}
		if (where.length() > 0)
			sql.append(" where ").append(where.substring(0, where.length() - 5));
		else
			throw new NestedException("SQL-002", new String[] { name });
		return sql;
	}

	private StringBuffer getInsertSQL(int index) throws NestedException {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(name).append("(");
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			// 如果是自动增长的列,则不需要处理
			if (c.isAutoIncrement())
				continue;
			sql.append(c.getName());
			if (objs.hasNext())
				sql.append(",");
		}
		sql.append(") values(");
		// 填充值
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			// 如果是自动增长的列,则不需要处理
			if (c.isAutoIncrement()) {
				// 处理自动数据
				continue;
			}
			String v = map.getString(c.getName(), index);
			// 处理自动参数部分
			sql.append(format(v, c));
			if (objs.hasNext())
				sql.append(",");
		}
		sql.append(")");
		return sql;
	}

	public StringBuffer getSQL() throws NestedException {
		switch (sqlType) {
		case Table.INSERT:
			return getInsertSQL(0);
		case Table.UPDATE:
			return getUpdateSQL(0);
		case Table.DELETE:
			return getDeleteSQL(0);
		case Table.SELECT:
			return getSelectSQL(0);
		}
		return null;
	}

	private StringBuffer getUpdateSQL(int index) throws NestedException {
		StringBuffer set = new StringBuffer("update ").append(name);
		set.append(" set ");
		StringBuffer where = new StringBuffer();
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			String v = map.getString(c.getName(), index);
			if (!map.containKey(c.getName()))// No data update
				continue;
			if (c.isWhereColumn()) {
				where.append(c.getName()).append("=").append(format(v, c));
				where.append((isOr) ? " or  " : " and ");
				continue;
			}
			set.append(c.getName()).append("=").append(format(v, c));
			set.append(",");
		}
		StringBuffer sqls = new StringBuffer();
		if (set.toString().endsWith(","))
			sqls.append(set.substring(0, set.length() - 1));
		else
			sqls.append(set.toString());
		if (where.length() > 0)
			sqls.append(" where ").append(where.substring(0, where.length() - 5));
		return sqls;
	}

	public void setParameterMap(DyncParameterMap map) {
		this.map = map;
	}

	void setColumns(List columns) {
		this.columns = new HashMap();
		// System.out.println(name);
		for (Iterator objs = columns.iterator(); objs.hasNext();) {
			Object o = objs.next();
			Column c = new Column();
			// System.out.println(DataUtil.getString(o, "column_name"));
			c.setName(DataUtil.getString(o, "field").toLowerCase());
			c.setNotNull("NO".equals(DataUtil.getString(o, "null")));
			c.setType(DataUtil.getString(o, "type"));
			c.setPrimaryKey("PRI".equals(DataUtil.getString(o, "key")));
			c.setDefaultValue(DataUtil.getString(o, "default"));
			c.setDefaultValueType(Column.DEFAULT_VALUE);
			c.setTableName(name);
			this.columns.put(c.getName(), c);
		}
		columns.clear();
	}

	public void setOrOperator(boolean or) {
		this.isOr = or;
	}

	/**
	 * 设置sequence序列的表列
	 * 
	 * @param cols
	 */
	public void setColumnDefaultValue(Column[] cols) {
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			c.setDefaultValue("");
			c.setDefaultValueType(0);
		}
		for (int i = 0; cols != null && i < cols.length; i++) {
			Column c = (Column) columns.get(cols[i].getName());
			c.setDefaultValue(cols[i].getDefaultValue());
			c.setDefaultValueType(cols[i].getDefaultValueType());
		}
	}

	/**
	 * 设置where条件列
	 * 
	 * @param cols
	 */
	public void setWhereColumn(String[] ks) {
		for (Iterator objs = columns.values().iterator(); objs.hasNext();) {
			Column c = (Column) objs.next();
			c.setWhereColumn(false);
		}
		if (ks != null && ks.length > 0) {
			for (int i = 0; i < ks.length; i++) {
				Column c = (Column) columns.get(ks[i]);
				c.setWhereColumn(true);
			}
		} else {
			for (int i = 0; i < ks.length; i++) {
				Column c = (Column) columns.get(ks[i]);
				if (c.isPrimaryKey())
					c.setWhereColumn(true);
			}
		}
	}

	public void setName(String tabname) {
		this.name = tabname;
	}

	public String getName() {
		return name;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
}
