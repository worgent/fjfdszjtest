/**
 * 
 */
package com.apricot.app.eating;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class Resource {
	private List childs;
	private String id;
	private String leaf;
	private String name;
	private String parentId;
	private String text;
	private String url;
	private int nofream;

	public Resource(Object o) {
		this.id = DataUtil.getString(o, "res_id");
		this.name = DataUtil.getString(o, "res_name");
		this.text = DataUtil.getString(o, "res_lable");
		this.url = DataUtil.getString(o, "res_path");
		this.parentId = DataUtil.getString(o, "parent_res_id");
		
		this.nofream = Integer.parseInt(DataUtil.getString(o, "nofream")==null?"0":DataUtil.getString(o, "nofream"));
		this.childs=new ArrayList();
		this.leaf = "true";
	}



	public void add(Resource r) {
		this.leaf = "false";
		this.childs.add(r);
	}

	public Iterator childs() {
		return this.childs.iterator();
	}

	public String getId() {
		return id;
	}

	public String getLeaf() {
		return leaf;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List getChilds() {
		return childs;
	}



	public int getNofream() {
		return nofream;
	}



	public void setNofream(int nofream) {
		this.nofream = nofream;
	}



	public void setChilds(List childs) {
		this.childs = childs;
	}
}
