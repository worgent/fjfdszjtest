/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.sysmanage.login.vo.query;

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

import com.womobile.sysmanage.login.model.*;
import com.womobile.sysmanage.login.dao.*;
import com.womobile.sysmanage.login.service.*;
import com.womobile.sysmanage.login.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoUserQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 用户编号 */
	private java.lang.Integer userId;
	/** 用户组编号 */
	private java.lang.Integer groupId;
	/** 用户名 */
	private java.lang.String username;
	/** 密码明文 */
	private java.lang.String passwd;
	/** 密码密文 */
	private java.lang.String repasswd;

	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.Integer value) {
		this.groupId = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getPasswd() {
		return this.passwd;
	}
	
	public void setPasswd(java.lang.String value) {
		this.passwd = value;
	}
	
	public java.lang.String getRepasswd() {
		return this.repasswd;
	}
	
	public void setRepasswd(java.lang.String value) {
		this.repasswd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

