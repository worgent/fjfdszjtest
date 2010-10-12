/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class WoTaskfieldManager extends BaseManager<WoTaskfield,java.lang.Integer>{

	private WoTaskfieldDao woTaskfieldDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setWoTaskfieldDao(WoTaskfieldDao dao) {
		this.woTaskfieldDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.woTaskfieldDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(WoTaskfieldQuery query) {
		return woTaskfieldDao.findPage(query);
	}
	
}
