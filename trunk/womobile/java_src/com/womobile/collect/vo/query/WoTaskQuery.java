/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.collect.vo.query;

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

import com.womobile.collect.model.*;
import com.womobile.collect.dao.*;
import com.womobile.collect.service.*;
import com.womobile.collect.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoTaskQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 任务单编号 */
	private java.lang.Integer taskId;
	/** 主题 */
	private java.lang.String topic;
	/** 指标 */
	private java.lang.Integer guideline;
	/** 描述 */
	private java.lang.String description;
	/** 反馈单格式编号 */
	private java.lang.Integer feedbacktypeId;
	/** 考核周期编号 */
	private java.lang.Integer examinecycleId;
	/** 状态 */
	private Integer statusId;
	/** 备用字段编号 */
	private java.lang.Integer fieldId;
	/** 备用字段_1 */
	private java.lang.String field1;
	/** 备用字段_2 */
	private java.lang.String field2;
	/** 备用字段_3 */
	private java.lang.String field3;
	/** 备用字段_4 */
	private java.lang.String field4;
	/** 备用字段_5 */
	private java.lang.String field5;
	/** 备用字段_6 */
	private java.lang.String field6;
	/** 备用字段_7 */
	private java.lang.String field7;
	/** 备用字段_8 */
	private java.lang.String field8;
	/** 备用字段_9 */
	private java.lang.String field9;
	/** 备用字段_10 */
	private java.lang.String field10;

	public java.lang.Integer getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(java.lang.Integer value) {
		this.taskId = value;
	}
	
	public java.lang.String getTopic() {
		return this.topic;
	}
	
	public void setTopic(java.lang.String value) {
		this.topic = value;
	}
	
	public java.lang.Integer getGuideline() {
		return this.guideline;
	}
	
	public void setGuideline(java.lang.Integer value) {
		this.guideline = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.Integer getFeedbacktypeId() {
		return this.feedbacktypeId;
	}
	
	public void setFeedbacktypeId(java.lang.Integer value) {
		this.feedbacktypeId = value;
	}
	
	public java.lang.Integer getExaminecycleId() {
		return this.examinecycleId;
	}
	
	public void setExaminecycleId(java.lang.Integer value) {
		this.examinecycleId = value;
	}
	
	public Integer getStatusId() {
		return this.statusId;
	}
	
	public void setStatusId(Integer value) {
		this.statusId = value;
	}
	
	public java.lang.Integer getFieldId() {
		return this.fieldId;
	}
	
	public void setFieldId(java.lang.Integer value) {
		this.fieldId = value;
	}
	
	public java.lang.String getField1() {
		return this.field1;
	}
	
	public void setField1(java.lang.String value) {
		this.field1 = value;
	}
	
	public java.lang.String getField2() {
		return this.field2;
	}
	
	public void setField2(java.lang.String value) {
		this.field2 = value;
	}
	
	public java.lang.String getField3() {
		return this.field3;
	}
	
	public void setField3(java.lang.String value) {
		this.field3 = value;
	}
	
	public java.lang.String getField4() {
		return this.field4;
	}
	
	public void setField4(java.lang.String value) {
		this.field4 = value;
	}
	
	public java.lang.String getField5() {
		return this.field5;
	}
	
	public void setField5(java.lang.String value) {
		this.field5 = value;
	}
	
	public java.lang.String getField6() {
		return this.field6;
	}
	
	public void setField6(java.lang.String value) {
		this.field6 = value;
	}
	
	public java.lang.String getField7() {
		return this.field7;
	}
	
	public void setField7(java.lang.String value) {
		this.field7 = value;
	}
	
	public java.lang.String getField8() {
		return this.field8;
	}
	
	public void setField8(java.lang.String value) {
		this.field8 = value;
	}
	
	public java.lang.String getField9() {
		return this.field9;
	}
	
	public void setField9(java.lang.String value) {
		this.field9 = value;
	}
	
	public java.lang.String getField10() {
		return this.field10;
	}
	
	public void setField10(java.lang.String value) {
		this.field10 = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

