/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.sysmanage.login.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class WoUserDao extends BaseIbatis3Dao<WoUser,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "WoUser";
	}
	
	public void saveOrUpdate(WoUser entity) {
		if(entity.getUserId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(WoUserQuery query) {
		return pageQuery("WoUser.findPage",query);
	}
	

}
