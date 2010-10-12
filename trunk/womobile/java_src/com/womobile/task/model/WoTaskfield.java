/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.task.model;

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

import com.womobile.task.model.*;
import com.womobile.task.dao.*;
import com.womobile.task.service.*;
import com.womobile.task.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoTaskfield extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WoTaskfield";
	public static final String ALIAS_FIELD_ID = "工作单自定义表编号";
	public static final String ALIAS_FIELD1 = "备用字段_1";
	public static final String ALIAS_FIELD2 = "备用字段_2";
	public static final String ALIAS_FIELD3 = "备用字段_3";
	public static final String ALIAS_FIELD4 = "备用字段_4";
	public static final String ALIAS_FIELD5 = "备用字段_5";
	public static final String ALIAS_FIELD6 = "备用字段_6";
	public static final String ALIAS_FIELD7 = "备用字段_7";
	public static final String ALIAS_FIELD8 = "备用字段_8";
	public static final String ALIAS_FIELD9 = "备用字段_9";
	public static final String ALIAS_FIELD10 = "备用字段_10";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer fieldId;
	@Length(max=30)
	private java.lang.String field1;
	@Length(max=30)
	private java.lang.String field2;
	@Length(max=30)
	private java.lang.String field3;
	@Length(max=30)
	private java.lang.String field4;
	@Length(max=30)
	private java.lang.String field5;
	@Length(max=30)
	private java.lang.String field6;
	@Length(max=30)
	private java.lang.String field7;
	@Length(max=30)
	private java.lang.String field8;
	@Length(max=30)
	private java.lang.String field9;
	@Length(max=30)
	private java.lang.String field10;
	//columns END

	public WoTaskfield(){
	}

	public WoTaskfield(
		java.lang.Integer fieldId
	){
		this.fieldId = fieldId;
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
			.append(getFieldId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WoTaskfield == false) return false;
		if(this == obj) return true;
		WoTaskfield other = (WoTaskfield)obj;
		return new EqualsBuilder()
			.append(getFieldId(),other.getFieldId())
			.isEquals();
	}
}

