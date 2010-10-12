/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.collect.model;

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

import com.womobile.collect.model.*;
import com.womobile.collect.dao.*;
import com.womobile.collect.service.*;
import com.womobile.collect.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoTask extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WoTask";
	public static final String ALIAS_TASK_ID = "任务单编号";
	public static final String ALIAS_TOPIC = "主题";
	public static final String ALIAS_GUIDELINE = "指标";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_FEEDBACKTYPE_ID = "反馈单格式编号";
	public static final String ALIAS_EXAMINECYCLE_ID = "考核周期编号";
	public static final String ALIAS_STATUS_ID = "状态";
	public static final String ALIAS_FIELD_ID = "备用字段编号";
	public static String ALIAS_FIELD1 = "备用字段_1";
	public static String ALIAS_FIELD2 = "备用字段_2";
	public static String ALIAS_FIELD3 = "备用字段_3";
	public static String ALIAS_FIELD4 = "备用字段_4";
	public static String ALIAS_FIELD5 = "备用字段_5";
	public static String ALIAS_FIELD6 = "备用字段_6";
	public static String ALIAS_FIELD7 = "备用字段_7";
	public static String ALIAS_FIELD8 = "备用字段_8";
	public static String ALIAS_FIELD9 = "备用字段_9";
	public static String ALIAS_FIELD10 = "备用字段_10";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer taskId;
	@NotBlank @Length(max=30)
	private java.lang.String topic;
	
	private java.lang.Integer guideline;
	@Length(max=200)
	private java.lang.String description;
	
	private java.lang.Integer feedbacktypeId;
	
	private java.lang.Integer examinecycleId;
	@Max(32767)
	private Integer statusId;
	
	private java.lang.Integer fieldId;
	@Length(max=100)
	private java.lang.String field1;
	@Length(max=100)
	private java.lang.String field2;
	@Length(max=100)
	private java.lang.String field3;
	@Length(max=100)
	private java.lang.String field4;
	@Length(max=100)
	private java.lang.String field5;
	@Length(max=100)
	private java.lang.String field6;
	@Length(max=100)
	private java.lang.String field7;
	@Length(max=100)
	private java.lang.String field8;
	@Length(max=100)
	private java.lang.String field9;
	@Length(max=100)
	private java.lang.String field10;
	//columns END

	public WoTask(){
	}

	public WoTask(
		java.lang.Integer taskId
	){
		this.taskId = taskId;
	}

	public void setTaskId(java.lang.Integer value) {
		this.taskId = value;
	}
	
	public java.lang.Integer getTaskId() {
		return this.taskId;
	}
	public void setTopic(java.lang.String value) {
		this.topic = value;
	}
	
	public java.lang.String getTopic() {
		return this.topic;
	}
	public void setGuideline(java.lang.Integer value) {
		this.guideline = value;
	}
	
	public java.lang.Integer getGuideline() {
		return this.guideline;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setFeedbacktypeId(java.lang.Integer value) {
		this.feedbacktypeId = value;
	}
	
	public java.lang.Integer getFeedbacktypeId() {
		return this.feedbacktypeId;
	}
	public void setExaminecycleId(java.lang.Integer value) {
		this.examinecycleId = value;
	}
	
	public java.lang.Integer getExaminecycleId() {
		return this.examinecycleId;
	}
	public void setStatusId(Integer value) {
		this.statusId = value;
	}
	
	public Integer getStatusId() {
		return this.statusId;
	}
	public void setFieldId(java.lang.Integer value) {
		this.fieldId = value;
	}
	
	public java.lang.Integer getFieldId() {
		return this.fieldId;
	}
	public void setField1(java.lang.String value) {
		this.field1 = value;
	}
	
	public java.lang.String getField1() {
		return this.field1;
	}
	public void setField2(java.lang.String value) {
		this.field2 = value;
	}
	
	public java.lang.String getField2() {
		return this.field2;
	}
	public void setField3(java.lang.String value) {
		this.field3 = value;
	}
	
	public java.lang.String getField3() {
		return this.field3;
	}
	public void setField4(java.lang.String value) {
		this.field4 = value;
	}
	
	public java.lang.String getField4() {
		return this.field4;
	}
	public void setField5(java.lang.String value) {
		this.field5 = value;
	}
	
	public java.lang.String getField5() {
		return this.field5;
	}
	public void setField6(java.lang.String value) {
		this.field6 = value;
	}
	
	public java.lang.String getField6() {
		return this.field6;
	}
	public void setField7(java.lang.String value) {
		this.field7 = value;
	}
	
	public java.lang.String getField7() {
		return this.field7;
	}
	public void setField8(java.lang.String value) {
		this.field8 = value;
	}
	
	public java.lang.String getField8() {
		return this.field8;
	}
	public void setField9(java.lang.String value) {
		this.field9 = value;
	}
	
	public java.lang.String getField9() {
		return this.field9;
	}
	public void setField10(java.lang.String value) {
		this.field10 = value;
	}
	
	public java.lang.String getField10() {
		return this.field10;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("TaskId",getTaskId())
			.append("Topic",getTopic())
			.append("Guideline",getGuideline())
			.append("Description",getDescription())
			.append("FeedbacktypeId",getFeedbacktypeId())
			.append("ExaminecycleId",getExaminecycleId())
			.append("StatusId",getStatusId())
			.append("FieldId",getFieldId())
			.append("Field1",getField1())
			.append("Field2",getField2())
			.append("Field3",getField3())
			.append("Field4",getField4())
			.append("Field5",getField5())
			.append("Field6",getField6())
			.append("Field7",getField7())
			.append("Field8",getField8())
			.append("Field9",getField9())
			.append("Field10",getField10())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTaskId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WoTask == false) return false;
		if(this == obj) return true;
		WoTask other = (WoTask)obj;
		return new EqualsBuilder()
			.append(getTaskId(),other.getTaskId())
			.isEquals();
	}

	public static String getALIAS_FIELD1() {
		return ALIAS_FIELD1;
	}

	public static void setALIAS_FIELD1(String alias_field1) {
		ALIAS_FIELD1 = alias_field1;
	}

	public static String getALIAS_FIELD2() {
		return ALIAS_FIELD2;
	}

	public static void setALIAS_FIELD2(String alias_field2) {
		ALIAS_FIELD2 = alias_field2;
	}

	public static String getALIAS_FIELD3() {
		return ALIAS_FIELD3;
	}

	public static void setALIAS_FIELD3(String alias_field3) {
		ALIAS_FIELD3 = alias_field3;
	}

	public static String getALIAS_FIELD4() {
		return ALIAS_FIELD4;
	}

	public static void setALIAS_FIELD4(String alias_field4) {
		ALIAS_FIELD4 = alias_field4;
	}

	public static String getALIAS_FIELD5() {
		return ALIAS_FIELD5;
	}

	public static void setALIAS_FIELD5(String alias_field5) {
		ALIAS_FIELD5 = alias_field5;
	}

	public static String getALIAS_FIELD6() {
		return ALIAS_FIELD6;
	}

	public static void setALIAS_FIELD6(String alias_field6) {
		ALIAS_FIELD6 = alias_field6;
	}

	public static String getALIAS_FIELD7() {
		return ALIAS_FIELD7;
	}

	public static void setALIAS_FIELD7(String alias_field7) {
		ALIAS_FIELD7 = alias_field7;
	}

	public static String getALIAS_FIELD8() {
		return ALIAS_FIELD8;
	}

	public static void setALIAS_FIELD8(String alias_field8) {
		ALIAS_FIELD8 = alias_field8;
	}

	public static String getALIAS_FIELD9() {
		return ALIAS_FIELD9;
	}

	public static void setALIAS_FIELD9(String alias_field9) {
		ALIAS_FIELD9 = alias_field9;
	}

	public static String getALIAS_FIELD10() {
		return ALIAS_FIELD10;
	}

	public static void setALIAS_FIELD10(String alias_field10) {
		ALIAS_FIELD10 = alias_field10;
	}
}

