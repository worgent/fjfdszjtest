/**
 * 
 */
package com.apricot.webservice.bean;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Administrator
 * 
 */
public class Row {

	/**
	 * 
	 */
	public Row() {
		// TODO Auto-generated constructor stub
		this.values = new HashMap();
	}

	public Row(Object obj, String[] properties, String[] nodeNames) {
		this();
		for (int i = 0; i < nodeNames.length; i++) {
			if (i >= (properties.length))
				break;

			try {
				setValue(nodeNames[i], BeanUtils
						.getProperty(obj, properties[i]));
			} catch (Exception e) {
			}
		}

	}

	private HashMap values;

	public void setValue(String name, String value) {
		this.values.put(name, value);
	}

	public HashMap getValues() {
		return values;
	}

	public Object toObject(Class clazz, String[] properties, String[] nodeNames) {
		Object o = null;
		try {
			o = clazz.newInstance();
		} catch (Exception e) {
			return null;
		}

		for (int i = 0; i < nodeNames.length; i++) {
			if (i >= (properties.length))
				break;

			try {
				BeanUtils.setProperty(o, properties[i], this.values
						.get(nodeNames[i]));
			} catch (Exception e) {
			}
		}

		return o;

	}

}
