/**
 * 
 */
package com.apricot.eating.engine;

import java.io.Serializable;
/**
 * @author Administrator
 */
public class DataSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4218511549086695751L;
	private Object rowSet;
	private String totalCount;

	/**
	 * 
	 */
	public DataSet() {
		// TODO Auto-generated constructor stub
	}

	public DataSet(String totalCount, Object rowSet) {
		super();
		this.totalCount = totalCount;
		this.rowSet = rowSet;
	}

	public Object getRowSet() {
		return rowSet;
	}

	public void setRowSet(Object rowSet) {
		this.rowSet = rowSet;
	}

	public String getTotalCount() {
		return totalCount;
	}

	void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
}
