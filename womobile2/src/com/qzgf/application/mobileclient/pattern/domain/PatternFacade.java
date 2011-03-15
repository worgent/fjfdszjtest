package com.qzgf.application.mobileclient.pattern.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.application.mobileclient.pattern.dto.PatternInfo;

/**
 * 提取模板
 * @author dpl
 *
 */
public interface PatternFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	

	
    /*提取模板*/
	@SuppressWarnings("unchecked")
	public List<PatternInfo> findPattern(HashMap map);

}