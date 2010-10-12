/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.perambulate.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class PerambulateDao extends BaseIbatis3Dao<UserInfo,java.lang.Long>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "UserInfo";
	}
	
	public void saveOrUpdate(UserInfo entity) {
		if(entity.getUserId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserInfoQuery query) {
		return pageQuery("UserInfo.findPage",query);
	}
	

}
