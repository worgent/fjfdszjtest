/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.workduty.model;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.qzgf.application.workduty.model.*;
import com.qzgf.application.workduty.dao.*;
import com.qzgf.application.workduty.service.*;
import com.qzgf.application.workduty.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class Workduty extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Workduty";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_TITLE = "主题";
	public static final String ALIAS_DETAIL = "描述";
	public static final String ALIAS_ADJUNCT = "附件";
	public static final String ALIAS_TARGET = "指标";
	public static final String ALIAS_FORMAT = "选择反馈单格式";
	public static final String ALIAS_PROID = "指定反馈对象";
	public static final String ALIAS_ATTITUDE = "意见";
	public static final String ALIAS_GRADE = "评分";
	public static final String ALIAS_ISEND = "结束";
	public static final String ALIAS_DEPLOYMENTID = "流程id";
	public static final String ALIAS_LEVELID = "级别id";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Long id;
	@NotBlank @Length(max=100)
	private java.lang.String title;
	@Length(max=200)
	private java.lang.String detail;
	@Length(max=100)
	private java.lang.String adjunct;
	@Length(max=10)
	private java.lang.String target;
	@Length(max=4)
	private java.lang.String format;
	@Length(max=100)
	private java.lang.String proid;
	@Length(max=300)
	private java.lang.String attitude;
	@Length(max=10)
	private java.lang.String grade;
	@Length(max=2)
	private java.lang.String isend;
	@Length(max=30)
	private java.lang.String deploymentid;
	@Length(max=10)
	private java.lang.String levelid;
	//columns END

	public Workduty(){
	}

	public Workduty(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setDetail(java.lang.String value) {
		this.detail = value;
	}
	
	public java.lang.String getDetail() {
		return this.detail;
	}
	public void setAdjunct(java.lang.String value) {
		this.adjunct = value;
	}
	
	public java.lang.String getAdjunct() {
		return this.adjunct;
	}
	public void setTarget(java.lang.String value) {
		this.target = value;
	}
	
	public java.lang.String getTarget() {
		return this.target;
	}
	public void setFormat(java.lang.String value) {
		this.format = value;
	}
	
	public java.lang.String getFormat() {
		return this.format;
	}
	public void setProid(java.lang.String value) {
		this.proid = value;
	}
	
	public java.lang.String getProid() {
		return this.proid;
	}
	public void setAttitude(java.lang.String value) {
		this.attitude = value;
	}
	
	public java.lang.String getAttitude() {
		return this.attitude;
	}
	public void setGrade(java.lang.String value) {
		this.grade = value;
	}
	
	public java.lang.String getGrade() {
		return this.grade;
	}
	public void setIsend(java.lang.String value) {
		this.isend = value;
	}
	
	public java.lang.String getIsend() {
		return this.isend;
	}
	public void setDeploymentid(java.lang.String value) {
		this.deploymentid = value;
	}
	
	public java.lang.String getDeploymentid() {
		return this.deploymentid;
	}
	public void setLevelid(java.lang.String value) {
		this.levelid = value;
	}
	
	public java.lang.String getLevelid() {
		return this.levelid;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Detail",getDetail())
			.append("Adjunct",getAdjunct())
			.append("Target",getTarget())
			.append("Format",getFormat())
			.append("Proid",getProid())
			.append("Attitude",getAttitude())
			.append("Grade",getGrade())
			.append("Isend",getIsend())
			.append("Deploymentid",getDeploymentid())
			.append("Levelid",getLevelid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Workduty == false) return false;
		if(this == obj) return true;
		Workduty other = (Workduty)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

