/**
 * XMLBuilder.java
 * 
 * @author Devei Xu 2007-10-26
 * @history 2007-10-26 created by Devei Xu
 */
package com.apricot.eating.xml;

/**
 * XMLBuilder.java
 * 
 * @author Devei Xu 2007-9-28
 * @history 2007-9-28 created by Devei Xu
 */
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import com.apricot.eating.engine.DataSet;
/**
 * <p>
 * </p>
 * <p>
 * </p>
 */
public class XMLBuilder {
	private OutputStream out;
	private String encoding;

	/**
	 * 
	 */
	public XMLBuilder(OutputStream out, String encoding) {
		super();
		this.out = out;
		this.encoding = (encoding == null || encoding.trim().length() == 0) ? "ISO-8859-1" : encoding;
	}

	private void build(String str) throws Exception {
		// System.out.print(str);
		try {
			this.out.write(str.getBytes(encoding));
			// this.out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	public void close() {
		try {
			this.out.close();
		} catch (Exception e) {
		}
	}

	public void buildXML(String rootNodeTagName, Object obj) throws Exception {
		build("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		build(rootNodeTagName, obj);
	}

	private void build(String name, Object obj) throws Exception {
		if (name == null || name.trim().length() == 0)
			return;
		build("<" + name + ">");
		if (obj instanceof String) {
			this.build("<![CDATA[");
			this.build((String) obj);
			this.build("]]>");
		}
		if (obj instanceof HashMap) {
			this.build((HashMap) obj);
		}
		if (obj instanceof List) {
			this.build((List) obj);
		}
		if (obj instanceof DataSet) {
		}
		if (obj instanceof LazyDynaBean) {
			this.build((LazyDynaBean) obj);
		}
		build("</" + name + ">");
	}

	private void build(HashMap map) throws Exception {
		for (Iterator keys = map.keySet().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			if (key == null || key.trim().length() == 0)
				continue;
			this.build(key, map.get(key));
		}
	}

	private void build(List list) throws Exception {
		for (Iterator objs = list.iterator(); objs.hasNext();) {
			Object obj = objs.next();
			if (obj == null)
				continue;
			this.build("row", obj);
		}
	}

	private void build(LazyDynaBean bean) throws Exception {
		DynaProperty[] props = bean.getDynaClass().getDynaProperties();
		for (int i = 0; i < props.length; i++) {
			try {
				build(props[i].getName(), BeanUtils.getProperty(bean, props[i].getName()));
			} catch (Exception e) {
			}
		}
	}
}
