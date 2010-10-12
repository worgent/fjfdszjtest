/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.task.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class WoTaskfieldDao extends BaseIbatis3Dao<WoTaskfield,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "WoTaskfield";
	}
	
	public void saveOrUpdate(WoTaskfield entity) {
		if(entity.getFieldId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(WoTaskfieldQuery query) {
		return pageQuery("WoTaskfield.findPage",query);
	}
	

}
