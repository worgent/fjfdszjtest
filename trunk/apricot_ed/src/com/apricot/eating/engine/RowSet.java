/**
 * 
 */
package com.apricot.eating.engine;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.LazyDynaBean;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class RowSet {
	private String[] getColumns(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		String[] props = new String[meta.getColumnCount()];
		for (int i = 1; i <= props.length; i++) {
			props[i - 1] = meta.getColumnLabel(i).toLowerCase();
			// System.out.println(meta.getColumnLabel(i));
		}
		return props;
	}

	public void clearRuntimeParameter() {
		if (this.staticData != null)
			this.staticData.clear();
	}
	private HashMap staticData;

	public void setRuntimeStaticData(HashMap m) {
		if (staticData == null)
			staticData = new HashMap();
		staticData.putAll(m);
	}

	public String getProperty(Object obj, String propertyName) {
		try {
			String v = BeanUtils.getProperty(obj, propertyName);
			return (v == null) ? "" : v;
		} catch (Exception e) {
			return "";
		}
	}

	public Object getRow(ResultSet rs, Class clazz) throws SQLException {
		return getRow(rs, clazz, getColumns(rs));
	}

	public Object getRow(ResultSet rs, Class clazz, String[] propertys) throws SQLException {
		Object obj = null;
		if (clazz == null)
			clazz = LazyDynaBean.class;
		if (propertys == null)
			propertys = getColumns(rs);
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
		}
		if (obj == null)
			return null;
		if (rs.next())
			setRowObject(rs, obj, propertys);
		else {
			obj = null;
		}
		if (staticData != null)
			staticData.clear();
		staticData = null;
		return obj;
	}

	public List getRows(ResultSet rs, Class clazz) throws SQLException {
		return getRows(rs, clazz, getColumns(rs));
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	final void setMaxSize(int maxSize) {
	    this.maxSize = maxSize;
	}

	public List getRows(ResultSet rs, Class clazz, String[] propertys) throws SQLException {
		List l = new ArrayList();
		if (clazz == null)
			clazz = LazyDynaBean.class;
		if (propertys == null)
			propertys = getColumns(rs);
		int count=0;
		while (rs.next()) {
			Object obj = null;
			try {
				obj = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (obj == null){
			    maxSize=-1;
				return l;
			}
			if(count>=maxSize && maxSize>0) break;
			setRowObject(rs, obj, propertys);
			l.add(obj);
			count++;
		}
		maxSize=-1;
		if (staticData != null)
			staticData.clear();
		staticData = null;
		
		return l;
	}

	private String setProperty(Object obj, String property, Object value) {
		String v = toString(value);
		try {
			if (obj instanceof Map) {
				((Map) obj).put(property, v);
				return v;
			}
			if (obj instanceof Properties) {
				((Properties) obj).put(property, v);
				return v;
			}
			if (obj instanceof Hashtable) {
				((Hashtable) obj).put(property, v);
				return v;
			}
			BeanUtils.setProperty(obj, property, v);
		} catch (Exception e) {
		}
		return v;
	}

	public void setRow(ResultSet rs, Object obj) throws SQLException {
		setRow(rs, obj, getColumns(rs));
	}

	public void setRow(ResultSet rs, Object obj, String[] propertys) throws SQLException {
		try {
			if (!rs.next()) {
				return;
			}
			setRowObject(rs, obj, propertys);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (staticData != null)
				staticData.clear();
			staticData = null;
		}
	}

	private void setRowObject(ResultSet rs, Object obj, String[] propertys) throws SQLException {
		if (propertys == null)
			propertys = getColumns(rs);
		for (int i = 1; i <= propertys.length; i++) {
			String v = setProperty(obj, propertys[i - 1], rs.getObject(i));
			if (staticData == null || staticData.size() == 0)
				continue;
			if (!staticData.containsKey(propertys[i - 1]))
				continue;
			Object o = staticData.get(propertys[i - 1]);
			try {
				setProperty(obj, propertys[i - 1] + "_text", BeanUtils.getProperty(o, v));
			} catch (Exception e) {
			}
			o = null;
			v = null;
		}
	}

	private String toString(Object obj) {
		if (obj == null)
			return "";
		if (obj instanceof Timestamp) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj);
		}
		if (obj instanceof java.sql.Date) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj);
		}
		if (obj instanceof String) {
			return DataUtil.toSystemCharset((String) obj);
		}
		return obj.toString();
	}

	/**
	 * 
	 */
	public RowSet() {
		// TODO Auto-generated constructor stub
	}
	
	private int maxSize;

	public RowSet(int maxSize) {
	    super();
	    this.maxSize = maxSize;
	}
}
