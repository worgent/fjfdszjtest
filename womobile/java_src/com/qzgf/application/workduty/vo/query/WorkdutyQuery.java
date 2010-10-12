/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.workduty.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

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


public class WorkdutyQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 主键 */
	private java.lang.Long id;
	/** 主题 */
	private java.lang.String title;
	/** 描述 */
	private java.lang.String detail;
	/** 附件 */
	private java.lang.String adjunct;
	/** 指标 */
	private java.lang.String target;
	/** 选择反馈单格式 */
	private java.lang.String format;
	/** 指定反馈对象 */
	private java.lang.String proid;
	/** 意见 */
	private java.lang.String attitude;
	/** 评分 */
	private java.lang.String grade;
	/** 结束 */
	private java.lang.String isend;
	/** 流程id */
	private java.lang.String deploymentid;
	/** 级别id */
	private java.lang.String levelid;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getDetail() {
		return this.detail;
	}
	
	public void setDetail(java.lang.String value) {
		this.detail = value;
	}
	
	public java.lang.String getAdjunct() {
		return this.adjunct;
	}
	
	public void setAdjunct(java.lang.String value) {
		this.adjunct = value;
	}
	
	public java.lang.String getTarget() {
		return this.target;
	}
	
	public void setTarget(java.lang.String value) {
		this.target = value;
	}
	
	public java.lang.String getFormat() {
		return this.format;
	}
	
	public void setFormat(java.lang.String value) {
		this.format = value;
	}
	
	public java.lang.String getProid() {
		return this.proid;
	}
	
	public void setProid(java.lang.String value) {
		this.proid = value;
	}
	
	public java.lang.String getAttitude() {
		return this.attitude;
	}
	
	public void setAttitude(java.lang.String value) {
		this.attitude = value;
	}
	
	public java.lang.String getGrade() {
		return this.grade;
	}
	
	public void setGrade(java.lang.String value) {
		this.grade = value;
	}
	
	public java.lang.String getIsend() {
		return this.isend;
	}
	
	public void setIsend(java.lang.String value) {
		this.isend = value;
	}
	
	public java.lang.String getDeploymentid() {
		return this.deploymentid;
	}
	
	public void setDeploymentid(java.lang.String value) {
		this.deploymentid = value;
	}
	
	public java.lang.String getLevelid() {
		return this.levelid;
	}
	
	public void setLevelid(java.lang.String value) {
		this.levelid = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

