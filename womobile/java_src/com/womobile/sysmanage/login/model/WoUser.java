/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.sysmanage.login.model;

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

import com.womobile.sysmanage.login.model.*;
import com.womobile.sysmanage.login.dao.*;
import com.womobile.sysmanage.login.service.*;
import com.womobile.sysmanage.login.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoUser extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WoUser";
	public static final String ALIAS_USER_ID = "用户编号";
	public static final String ALIAS_GROUP_ID = "用户组编号";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWD = "密码明文";
	public static final String ALIAS_REPASSWD = "密码密文";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer userId;
	@NotNull 
	private java.lang.Integer groupId;
	@NotBlank @Length(max=50)
	private java.lang.String username;
	@NotBlank @Length(max=40)
	private java.lang.String passwd;
	@NotBlank @Length(max=40)
	private java.lang.String repasswd;
	//columns END

	public WoUser(){
	}

	public WoUser(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setGroupId(java.lang.Integer value) {
		this.groupId = value;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setPasswd(java.lang.String value) {
		this.passwd = value;
	}
	
	public java.lang.String getPasswd() {
		return this.passwd;
	}
	public void setRepasswd(java.lang.String value) {
		this.repasswd = value;
	}
	
	public java.lang.String getRepasswd() {
		return this.repasswd;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("GroupId",getGroupId())
			.append("Username",getUsername())
			.append("Passwd",getPasswd())
			.append("Repasswd",getRepasswd())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WoUser == false) return false;
		if(this == obj) return true;
		WoUser other = (WoUser)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

