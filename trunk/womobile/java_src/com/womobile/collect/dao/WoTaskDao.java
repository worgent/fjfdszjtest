/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.collect.dao;

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
import com.womobile.task.model.WoTaskfield;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


import org.springframework.stereotype.Repository;


@Repository
public class WoTaskDao extends BaseIbatis3Dao<WoTask,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "WoTask";
	}
	
	public void saveOrUpdate(WoTask entity) {
		if(entity.getTaskId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(WoTaskQuery query) {
		return pageQuery("WoTask.findPage",query);
	}
	
	@SuppressWarnings("unchecked")
	public WoTaskfield findTaskField() {
		List fieldList=getSqlSessionTemplate().selectList("WoTask.findTaskField", null, 0, 1);
		WoTaskfield woTaskfield=(WoTaskfield)fieldList.get(0);
    	return woTaskfield;
	}
	

}
