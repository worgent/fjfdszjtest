/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.company.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.company.test.model.*;
import com.company.test.dao.*;
import com.company.test.service.*;
import com.company.test.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UserInfoManager extends BaseManager<UserInfo,java.lang.Long>{

	private UserInfoDao userInfoDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUserInfoDao(UserInfoDao dao) {
		this.userInfoDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.userInfoDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(UserInfoQuery query) {
		return userInfoDao.findPage(query);
	}
	
}
