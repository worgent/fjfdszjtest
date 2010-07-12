/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.MessageResource;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class Rule {
	private List conditions;
	private String desc;
	private String id;
	private String name;
	private List scopes;
	private List values;
	private String type;
	
	private boolean vip;
	
	/**
	 * @return Returns the vip.
	 */
	public boolean isVip() {
		return vip;
	}

	/**
	 * @param vip The vip to set.
	 */
	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public void clearScope(){
		this.scopes.clear();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	/**
	 * 
	 */
	public Rule(Object obj) {
		// TODO Auto-generated constructor stub
		this.scopes = new ArrayList();
		this.values = new ArrayList();
		this.conditions = new ArrayList();
		if(obj instanceof String){
              this.id=(String) obj;
             
		}else{
			this.id = DataUtil.getString(obj, "price_id");
			this.name = DataUtil.getString(obj, "price_name");
			this.type = DataUtil.getString(obj, "price_type");
			
		}
	}

	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer();
		s.append("[");
		s.append("\"").append(id).append("\",");
		s.append("\"").append(name).append("\",");
		s.append("\"");
		if (this.scopes.size() > 0) {
			for (Iterator objs = this.scopes(); objs.hasNext();) {
				s.append(objs.next().toString());
				if (objs.hasNext()) {
					s.append("<br>");
				}
			}
		} else {
			try {
				s.append(MessageResource.getInstance().getMessage("price_scope_all", null));
			} catch (Exception e) {
			}
		}
		s.append("\",");
		s.append("\"");
		for (Iterator objs = this.values(); objs.hasNext();) {
			s.append(objs.next().toString());
			if (objs.hasNext()) {
				s.append("<br>");
			}
		}
		s.append("\"]");
		return s.toString();
	}

	/**
	 * <p>
	 *
	 * </p>
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(!(obj instanceof Rule)) return false;
		
		return this.id.equals(((Rule) obj).id);
	}

	/**
	 * 删除规则下面的不满足条件的详细定义
	 */
	public void delete() {
		// 删除当前不适应的范围
		for (int i = this.scopes.size() - 1; i >= 0; i--) {
			RuleScope o = (RuleScope) scopes.get(i);
			if (o.isDelete())
				scopes.remove(i);
		}
	}
	
	public boolean existScope(){
		return this.scopes.size()>0;
	}

	/**
	 * 获取规则类型是菜品类型的值
	 * 
	 * @return
	 */
	public String getScopeFoodType() {
		StringBuffer s = new StringBuffer();
		for (Iterator objs = scopes.iterator(); objs.hasNext();) {
			RuleScope r = (RuleScope) objs.next();
			if ("1".equals(r.getScopeType())) {
				s.append(r.getScopeValue()).append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 获取规则类型是菜品ID的值
	 * 
	 * @return
	 */
	public String getScopeFoodID() {
		StringBuffer s = new StringBuffer();
		for (Iterator objs = scopes.iterator(); objs.hasNext();) {
			RuleScope r = (RuleScope) objs.next();
			if ("2".equals(r.getScopeType())) {
				s.append(r.getScopeValue()).append(",");
			}
		}
		return s.toString();
	}
	
	/**
	 * 获取规则类型是菜品类型的值
	 * 
	 * @return
	 */
	public String getBelongType() {
		StringBuffer s = new StringBuffer();
		for (Iterator objs = scopes.iterator(); objs.hasNext();) {
			RuleScope r = (RuleScope) objs.next();
			if ("03".equals(r.getScopeType())) {
				s.append(r.getScopeValue()).append(",");
			}
		}
		return s.toString();
	}

	public void add(RuleCondition rs) {
		this.conditions.add(rs);
	}

	public void add(RuleScope rs) {
		this.scopes.add(rs);
	}

	public void add(RuleValue rs) {
		this.values.add(rs);
	}

	public Iterator conditions() {
		return this.conditions.iterator();
	}

	public String getDesc() {
		return desc;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Iterator scopes() {
		return this.scopes.iterator();
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Iterator values() {
		return this.values.iterator();
	}
}
