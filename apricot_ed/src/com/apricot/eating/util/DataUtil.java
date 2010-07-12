/**
 * 
 */
package com.apricot.eating.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.beanutils.BeanUtils;
import com.apricot.eating.DefaultContext;

/**
 * @author Administrator
 */
public final class DataUtil {
    public static final void copyProperties(Object desc, Object src, String[] props) {
	if (props == null)
	    return;
	for (int i = 0; i < props.length; i++) {
	    DataUtil.setObject(desc, props[i], DataUtil.getObject(src, props[i]));
	}
    }
    
    public static  final String nvl(String v,String d){
	if(v==null) return d;
	return v;
    }
    
    public static final String formatDateTime(String s,String partten){
	try{
	    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat df2=new SimpleDateFormat(partten);
	    return df2.format(df1.parse(s));
	}catch(Exception e){}
	
	return s;
    }

    public static final Object copyProperties(Class claz, HashMap map) {
	Object o = null;
	try {
	    o = claz.newInstance();
	} catch (Exception e) {
	}
	if (o == null)
	    return o;
	for (Iterator objs = map.keySet().iterator(); objs.hasNext();) {
	    String k = (String) objs.next();
	    DataUtil.setObject(o, k, map.get(k));
	}
	return o;
    }

    public static final String format(float f, String p) {
	try {
	    return new DecimalFormat(DataUtil.isNull(p) ? "#0.00" : p).format(f);
	} catch (Exception e) {
	}
	;
	return String.valueOf(f);
    }

    public static final String format(Date d, String p) {
	try {
	    SimpleDateFormat df = new SimpleDateFormat(p);
	    return df.format(d);
	} catch (Exception e) {
	}
	return "";
    }

    public static final float parseFloat(String p) {
	try {
	    return Float.parseFloat(p);
	} catch (Exception e) {
	}
	return 0F;
    }

    public static final int parseInt(String p) {
	try {
	    return Integer.parseInt(p);
	} catch (Exception e) {
	}
	return 0;
    }

    public static final String getCurrDateTime() {
	return DataUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static final String getCurrDateTime(int hour) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	cal.add(Calendar.HOUR_OF_DAY, hour);
	return DataUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static final String getLastMinutesDateTime(int minu) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	cal.add(Calendar.MINUTE, minu);
	return DataUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static final String getCurrDateTime(String p) {
	return DataUtil.format(new Date(), p);
    }

    public static final double getDouble(Object o, String name, double d) {
	try {
	    return Double.parseDouble(DataUtil.getString(o, name));
	} catch (Exception e) {
	}
	return d;
    }

    public static final float getFloat(Object o, String name, float d) {
	try {
	    return Float.parseFloat(DataUtil.getString(o, name));
	} catch (Exception e) {
	}
	return d;
    }

    public static final int getInt(Object o, String name, int d) {
	try {
	    return Integer.parseInt(DataUtil.getString(o, name));
	} catch (Exception e) {
	}
	return d;
    }

    public static final long getLong(Object o, String name, long d) {
	try {
	    return Long.parseLong(DataUtil.getString(o, name));
	} catch (Exception e) {
	}
	return d;
    }

    public static synchronized String getNo() {
	StringBuffer no = new StringBuffer();
	no.append("E");
	no.append(DataUtil.getCurrDateTime("yyyyMMddHHmmssSSS"));
	no.append(DataUtil.lpad(String.valueOf(new Random().nextInt(100000)), '0', 6));
	return no.toString();
    }

    public static final Object getObject(Object obj, String name) {
	try {
	    return BeanUtils.getProperty(obj, name);
	} catch (Exception e) {
	}
	return null;
    }

    public static final Object getObjectSpecial(Object obj, String name1, String name2) {
	try {
	    return BeanUtils.getMappedProperty(obj, name1, name2);
	} catch (Exception e) {
	}
	return null;
    }

    public static final String getSqlString(String v) {
	if (DataUtil.isNull(v))
	    return "''";
	return new StringBuffer("'").append(v).append("'").toString();
    }

    public static final String getString(Object obj, String name) {
	try {
	    return (String) BeanUtils.getProperty(obj, name);
	} catch (Exception e) {
	}
	return null;
    }

    public static final boolean isNull(String a) {
	return (a == null || "".equals(a));
    }

    public static final String lpad(String v, char p, int l) {
	StringBuffer b = new StringBuffer();
	if (DataUtil.isNull(v))
	    v = "";
	for (int i = 0; i < l - v.length(); i++) {
	    b.append(p);
	}
	b.append(v);
	return b.toString();
    }

    public static final String rbytepad(String v, char p, int l) {
	int s = (v == null) ? 0 : v.getBytes().length;

	int ss = String.valueOf(p).getBytes().length;
	StringBuffer sb = new StringBuffer((s == 0) ? "" : v);
	while (s < l) {
	    sb.append(p);
	    s = s + ss;
	}
	return sb.toString();
    }

    public static final Date parseDate(String v, String p) {
	try {
	    SimpleDateFormat df = new SimpleDateFormat(p);
	    return df.parse(v);
	} catch (Exception e) {
	}
	return null;
    }

    public static final void setObject(Object obj, String name, Object v) {
	try {

	    BeanUtils.setProperty(obj, name, v);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static final void sortAsc(List l, String key) {
	HashMap map = new HashMap();
	int max = 0;
	int min = 0;
	for (Iterator objs = l.iterator(); objs.hasNext();) {
	    Object o = objs.next();
	    int t = DataUtil.getInt(o, key, 0);
	    max = (max > t) ? max : t;
	    min = (min < t) ? min : t;
	    map.put(String.valueOf(t), o);
	}
	l.clear();
	for (int i = min; i <= max; i++) {
	    Object o = map.get(String.valueOf(i));
	    if (o == null)
		continue;
	    l.add(o);
	}
	map.clear();
	map = null;
    }

    public static final String toDataBaseCharset(String c) {
	try {

	    // System.out.println(DefaultContext.getContext().getSystemCharset()+DefaultContext.getContext().getDataBaseCharset());
	    return new String(c.getBytes(DefaultContext.getContext().getSystemCharset()), DefaultContext.getContext()
		    .getDataBaseCharset());
	} catch (Exception e) {
	}
	return c;
    }

    public static final String toGBK(String v) {
	try {
	    return new String(v.getBytes("ISO-8859-1"), "GBK");
	} catch (Exception e) {
	}
	return v;
    }

    public static final Map toMap(List l, String keyColumn) {
	HashMap map = new HashMap();
	for (Iterator objs = l.iterator(); objs.hasNext();) {
	    Object o = objs.next();
	    map.put(DataUtil.getObject(o, keyColumn), o);
	}
	return map;
    }

    public static final Map toMapSpecial(List l, String keyColumn1, String keyColumn2) {
	HashMap map = new HashMap();
	for (Iterator objs = l.iterator(); objs.hasNext();) {
	    Object o = objs.next();
	    map.put(((String) DataUtil.getObject(o, keyColumn1) + (String) DataUtil.getObject(o, keyColumn2)), o);
	}
	return map;
    }

    public static final String toSqlDateTime(String v) {
	return new StringBuffer("STR_TO_DATE('").append(v).append("','").append("%Y-%m-%d %H:%i:%S").append("')")
		.toString();
    }

    public static final String toSystemCharset(String c) {
	try {
	    return new String(c.getBytes(DefaultContext.getContext().getDataBaseCharset()), DefaultContext.getContext()
		    .getSystemCharset());
	} catch (Exception e) {
	}
	return c;
    }

    public static final String toUTF(String v) {
	try {
	    return new String(v.getBytes("GBK"), "ISO-8859-1");
	} catch (Exception e) {
	}
	return v;
    }
}
