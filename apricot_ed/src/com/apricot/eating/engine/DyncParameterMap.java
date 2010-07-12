/**
 * 
 */
package com.apricot.eating.engine;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.apricot.eating.NestedException;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class DyncParameterMap {
	private HashMap getterMap;
	private HashMap globalStaticDataMap;
	private HashMap map;

	public DyncParameterMap() {
		// TODO Auto-generated constructor stub
		this(null);
	}

	private List menus;

	public List getMenus() {
		return menus;
	}
	
	public void put(String name,Object obj){
	    this.map.put(name, obj);
	}
	
	

	public Object get(String name) {
		return map.get(name);
	}

	public void setMenus(List m) {
		if (m != null)
			this.menus.addAll(m);
	}

	public String getStrings(String name, char splitChar) {
		String[] args = (String[]) map.get(name);
		StringBuffer s = new StringBuffer();
		if (args == null || args.length == 0)
			return "";
		for (int i = 0; i < args.length; i++) {
			if (DataUtil.isNull(args[i]))
				continue;
			s.append(args[i]).append(splitChar);
		}
		if (s.toString().endsWith(String.valueOf(splitChar))) {
			return s.toString().substring(0, s.length() - 1);
		} else
			return s.toString();
	}
	
	public void remove(String name) {
		this.map.remove(name);
	}

	/**
	 * 
	 */
	public DyncParameterMap(Map m) {
		// TODO Auto-generated constructor stub
		this.map = new HashMap();
		this.getterMap = new HashMap();
		if (m != null)
			this.map.putAll(m);
		this.menus = new ArrayList();
	}

	/**
	 * 添加参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void add(String name, String value) {
		String[] args = (String[]) map.get(name);
		if (args == null) {
			map.put(name, new String[] { value });
			return;
		}
		String[] tmp = new String[args.length + 1];
		System.arraycopy(args, 0, tmp, 0, args.length);
		tmp[tmp.length - 1] = value;
		map.remove(name);
		map.put(name, tmp);
		args = null;
		tmp = null;
	}

	public void clear() {
		this.map.clear();
		this.globalStaticDataMap.clear();
		this.getterMap.clear();
		this.menus.clear();
	}

	public boolean containKey(String key) {
		return this.map.containsKey(key);
	}

	public Date getDate(String name) throws NestedException {
		return getDate(name, 0);
	}

	public Date getDate(String name, int i) throws NestedException {
		String v = getString(name, i);
		if (DataUtil.isNull(v))
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return new Date(df.parse(v).getTime());
		} catch (Exception e) {
			throw new NestedException("SYS-0023", new String[] { name, v });
		}
	}

	public double getDouble(String name) {
		return getDouble(name, 0);
	}

	public double getDouble(String name, int i) {
		String v = getString(name, i);
		if (DataUtil.isNull(v))
			return 0;
		return Double.parseDouble(v);
	}

	public HashMap getGlobalStaticDataMap() {
		return globalStaticDataMap;
	}

	public int getInt(String name) {
		return this.getInt(name, 0);
	}

	public int getInt(String name, int i) {
		String v = getString(name, i);
		if (DataUtil.isNull(v))
			return i;
		return Integer.parseInt(v);
	}

	public long getLong(String name) {
		return getLong(name, 0);
	}

	public long getLong(String name, int i) {
		String v = getString(name, i);
		if (DataUtil.isNull(v))
			return 0;
		return Long.parseLong(v);
	}

	/**
	 * 获取多个参数中，最多个数的参数
	 * 
	 * @param properties
	 * @return
	 */
	public int getMaxSize(String[] properties) {
		int l = 0;
		if (properties == null) {
			properties = (String[]) map.keySet().toArray(
					new String[map.keySet().size()]);
		}
		for (int i = 0; i < properties.length; i++) {
			String[] args = (String[]) this.map.get(properties[i]);
			if (args == null)
				continue;
			if (args.length == 0)
				continue;
			if (l < args.length)
				l = args.length;
		}
		return l;
	}

	public int getPageSize() {
		return getInt("limit", 20);
	}

	public final String getSqlString(String name) {
		String v = getString(name);
		if (DataUtil.isNull(v))
			return "''";
		return new StringBuffer("'").append(v).append("'").toString();
	}

	public final String getSqlString(String name, String df) {
		String v = getString(name, df);
		if (DataUtil.isNull(v))
			return "''";
		return new StringBuffer("'").append(v).append("'").toString();
	}

	public int getStartRows() {
		return getInt("start", 0);
	}

	public String getString(String name) {
		return getString(name, 0);
	}
	public String getString(String name,String df,int i){
		return getString(name).split(df)[i];
	}

	public String getString(String name, int i) {
		DyncParameterGetter getter = (DyncParameterGetter) this.getterMap
				.get(name);
		if (getter != null) {
			return getter.getString(this, name, i);
		}
		Object obj = this.map.get(name);
		if (obj == null)
			return "";
		if (obj instanceof String) {
			return (String) obj;
		}
		if (!(obj instanceof String[])) {
			return obj.toString();
		}
		String[] args = (String[]) this.map.get(name);
		if (args == null)
			return "";
		if (args.length == 0)
			return "";
		String v = null;
		if (i < 0 || i >= args.length)
			v = args[0];
		else
			v = args[i];
		return (v == null) ? "" : v;
	}

	public String getString(String name, String dfValue) {
		String v = getString(name);
		if (v == null || v.length() == 0)
			return dfValue;
		return v;
	}
	
	public String getJoinString(String name,String join,String sp){
	    Object o=this.map.get(name);
	    if(o==null) return null;
	    if(sp==null) sp="";
	    
	    if(o instanceof String[]){
		String[] args=(String[])o;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<args.length;i++){
		    sb.append(sp).append(args[i]).append(sp);
		    if(i<(args.length-1)) sb.append("join");
		}
		return sb.toString();
	    }else{
		return new StringBuffer(sp).append(o).append(sp).toString();
	    }
	}

	public Timestamp getTimestamp(String name) throws NestedException {
		return getTimestamp(name, 0);
	}

	public Timestamp getTimestamp(String name, int i) throws NestedException {
		String v = getString(name, i);
		if (DataUtil.isNull(v))
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return new Timestamp(df.parse(v).getTime());
		} catch (Exception e) {
			throw new NestedException("SYS-0024", new String[] { name, v });
		}
	}

	public boolean isNull(String key) {
		String v = getString(key);
		return v == null || v.trim().length() == 0;
	}

	public void putAll(Map m) {
		if (m != null) {
			this.map.putAll(m);
		}

	}

	/**
	 * 设置参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, int value) {
		set(name, String.valueOf(value));
	}
	

	
	/**
	 * 设置参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, java.util.Date value) {
		set(name, value, null);
	}

	/**
	 * 设置参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, java.util.Date value, String p) {
		SimpleDateFormat df = new SimpleDateFormat(
				(p == null) ? "yyyy-MM-dd HH:mm:ss" : p);
		set(name, df.format(value));
	}

	/**
	 * 设置参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, long value) {
		set(name, String.valueOf(value));
	}

	/**
	 * 设置参数咯
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, String value) {
		map.remove(name);
		add(name, value);
	}

	public void setGetter(String name, DyncParameterGetter getter) {
		this.getterMap.put(name, getter);
	}

	public void setGlobalStaticDataMap(HashMap st) {
		this.globalStaticDataMap = new HashMap();
		this.globalStaticDataMap.putAll(st);
	}
}
