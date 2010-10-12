/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.sysmanage.login.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class WoUserManager extends BaseManager<WoUser,java.lang.Integer>{

	private WoUserDao woUserDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setWoUserDao(WoUserDao dao) {
		this.woUserDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.woUserDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(WoUserQuery query) {
		return woUserDao.findPage(query);
	}
	
}
