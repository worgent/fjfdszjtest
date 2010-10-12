/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.collect.service;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.rapid_framework.page.Page;

import com.womobile.collect.dao.WoTaskDao;
import com.womobile.collect.model.WoTask;
import com.womobile.collect.vo.query.WoTaskQuery;
import com.womobile.task.model.WoTaskfield;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class WoTaskManager extends BaseManager<WoTask,java.lang.Integer>{

	private WoTaskDao woTaskDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setWoTaskDao(WoTaskDao dao) {
		this.woTaskDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.woTaskDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(WoTaskQuery query) {
		return woTaskDao.findPage(query);
	}
	
	@SuppressWarnings("unchecked")
	public WoTaskfield findTaskField() {
		return woTaskDao.findTaskField();
	}
	
}
