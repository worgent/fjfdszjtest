/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.workduty.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class WorkdutyDao extends BaseIbatis3Dao<Workduty,java.lang.Long>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "Workduty";
	}
	
	public void saveOrUpdate(Workduty entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(WorkdutyQuery query) {
		return pageQuery("Workduty.findPage",query);
	}
	

}
