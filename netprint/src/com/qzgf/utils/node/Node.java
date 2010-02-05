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
	private int checked = 0 ;//是否选中该节点
	private String target ;//用于链接目标窗口名字
	private String href; //节点链接
	private String nodeXMLSrc;//通过URL动态在加载子节点数据,可用程序动态生成xml数据
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getNodeXMLSrc() {
		return nodeXMLSrc;
	}
	public void setNodeXMLSrc(String nodeXMLSrc) {
		this.nodeXMLSrc = nodeXMLSrc;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
