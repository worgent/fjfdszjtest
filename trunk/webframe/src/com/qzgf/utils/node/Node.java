package com.qzgf.utils.node;

/**
 * @author chenf
 * 2006年4月15日
 * 一个节点类
 * **/
public class Node {
	private String id ;//节点的唯一标
	private String title;//节点名称
	private String superId;//父亲节点
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the superId
	 */
	public String getSuperId() {
		return superId;
	}
	/**
	 * @param superId the superId to set
	 */
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	
}
