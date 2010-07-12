/**
 * 
 */
package com.apricot.eating.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.StaticDataSet;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class CopyOfStreamWriter {
	private String encoding;
	private OutputStream out;

	/**
	 * 
	 */
	public CopyOfStreamWriter(OutputStream out, String encoding) {
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
		write("\"rowSet\":[");
		if (set.getRowSet() != null) {
			for (Iterator objs = ((List) set.getRowSet()).iterator(); objs.hasNext();) {
				write("{");
				write(this.format((LazyDynaBean) objs.next()));
				write("}");
				if (objs.hasNext())
					write(",");
			}
		}
		write("]");
		write("}");
	}

	private void write(List l) {
		if (l.size() > 0)
			this.write("[");
		for (Iterator objs = l.iterator(); objs.hasNext();) {
			write("{");
			write(this.format((LazyDynaBean) objs.next()));
			write("}");
			if (objs.hasNext())
				this.write(",");
		}
		if (l.size() > 0)
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
		if (map.size() > 0)
			this.write("{");
		for (Iterator keys = map.keySet().iterator(); keys.hasNext();) {
			String k = (String) keys.next();
			this.write(k, map.get(k));
			if (keys.hasNext())
				this.write(",");
		}
		if (map.size() > 0)
			this.write("}");
	}

	private void write(String name, Object o) {
		if (name != null)
			this.write(new StringBuffer("\"").append(name).append("\":").toString());
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
		}
		if (o instanceof String) {
			this.write(new StringBuffer("\"").append((String) o).append("\"").toString());
			return;
		}
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
