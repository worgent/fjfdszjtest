/**
 * 
 */
package com.apricot.eating.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import com.apricot.app.eating.Resource;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.StaticDataSet;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class StreamWriter {
    private String encoding;
    private OutputStream out;

    /**
	 * 
	 */
    public StreamWriter(OutputStream out, String encoding) {
	// TODO Auto-generated constructor stub
	this.out = out;
	this.encoding = (DataUtil.isNull(encoding)) ? "gb2312" : encoding;
    }

    private String format(LazyDynaBean obj) {
	DynaProperty[] props = obj.getDynaClass().getDynaProperties();
	StringBuffer str = new StringBuffer();
	for (int i = 0; i < props.length; i++) {
	    try {
		str.append(format(props[i].getName(), BeanUtils.getProperty(obj, props[i].getName())));
	    } catch (Exception e) {
	    }
	    if (i < props.length - 1)
		str.append(",");
	}
	return str.toString();
    }

    public void close() throws IOException {
	this.encoding = null;
	if (this.out != null)
	    this.out.close();
	this.out = null;
    }

    private String format(String name, String value) {
	StringBuffer str = new StringBuffer("\"");
	str.append(name).append("\":\"").append(value).append("\"");
	return str.toString();
    }

    private void write(DataSet set) {
	write("{");
	write(format("totalCount", set.getTotalCount()));
	write(",");
	write("rowSet", set.getRowSet());
	write("}");
    }

    private void write(Resource r) {
	write("{");
	write("id:");
	write(DataUtil.getSqlString("menu_" + r.getId()));
	write(",nofream:" + r.getNofream());
	write(",text:");
	write(DataUtil.getSqlString(r.getText()));

	if ("false".equalsIgnoreCase(r.getLeaf())) {
	    write(",children:");
	    write(r.getChilds());

	} else {
	    write(",url:");
	    write(DataUtil.getSqlString(r.getUrl()));
	    write(",leaf:");
	    write(r.getLeaf());
	}
	write("}");
    }

    private void write(List l) {

	if (l == null)
	    l = new ArrayList();
	this.write("[");
	for (Iterator objs = l.iterator(); objs.hasNext();) {
	    this.write(null, objs.next());
	    if (objs.hasNext())
		this.write(",");
	}

	this.write("]");
    }

    private void write(StaticDataSet set) {
	this.write("[");
	for (Iterator objs = set.iterator(); objs.hasNext();) {
	    Object o = objs.next();
	    this.write("[");
	    this.write("\"");
	    this.write(DataUtil.getString(o, "attr_value"));
	    this.write("\",\"");
	    this.write(DataUtil.getString(o, "attr_desc"));
	    this.write("\"");
	    this.write("]");
	    if (objs.hasNext())
		this.write(",");
	}
	this.write("]");
    }

    private void write(HashMap map) {
	if(map==null) map=new HashMap();
	    this.write("{");
	for (Iterator keys = map.keySet().iterator(); keys.hasNext();) {
	    String k = (String) keys.next();
	    this.write(k, map.get(k));
	    if (keys.hasNext())
		this.write(",");
	}

	    this.write("}");
    }

    private void write(String name, Object o) {
	if (name != null)
	    this.write(new StringBuffer("\"").append(name).append("\":").toString());
	if (o == null)
	    o = "";
	if (o instanceof DataSet) {
	    this.write((DataSet) o);
	    return;
	}
	if (o instanceof StaticDataSet) {
	    this.write((StaticDataSet) o);
	    return;
	}
	if (o instanceof HashMap) {
	    this.write((HashMap) o);
	    return;
	}
	if (o instanceof List) {
	    this.write((List) o);
	    return;
	}
	if (o instanceof LazyDynaBean) {
	    this.write("{");
	    this.write(format((LazyDynaBean) o));
	    this.write("}");
	    return;
	}
	if (o instanceof Resource) {
	    this.write((Resource) o);
	    return;
	}
	if (o instanceof String) {
	    this.write(new StringBuffer("\"").append((String) o).append("\"").toString());
	    return;
	}
	this.write(new StringBuffer().append(o.toString()).toString());
    }

    public void write(Object o) {
	this.write(null, o);
    }

    private void write(String str) {
	try {
	    this.out.write(str.getBytes(this.encoding));
	} catch (Exception e) {
	}
    }
}
