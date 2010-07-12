/**
 * 
 */
package com.apricot.eating.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.LazyDynaBean;
/**
 * @author Administrator
 */
public class StaticDataSet {
	public static final String ATTRIBUTE_CODE_KEY = "StaticDataCode";
	private List set;
 
	/**
	 * 
	 */
	public StaticDataSet(List s) {
		// TODO Auto-generated constructor stub
		this.set = new ArrayList(s);
		//LazyDynaBean o=new LazyDynaBean();
		//o.set("attr_value", " ");
		//o.set("attr_desc", "нч");
		//this.set.add(0, o);
		
	}

	public Iterator iterator() {
		return this.set.iterator();
	}
	
	public List getData(){
	    return this.set;
	}
}
