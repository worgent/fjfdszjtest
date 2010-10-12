/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.workduty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class WorkdutyManager extends BaseManager<Workduty,java.lang.Long>{

	private WorkdutyDao workdutyDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setWorkdutyDao(WorkdutyDao dao) {
		this.workdutyDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.workdutyDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(WorkdutyQuery query) {
		return workdutyDao.findPage(query);
	}
	
}
