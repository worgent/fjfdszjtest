package com.qzgf.application.mobileclient.pattern.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.mobileclient.pattern.dto.PatternInfo;


/**
 * 提取模板
 * @author dpl
 * 
 */
public class PatternFacadeImpl implements PatternFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(PatternFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	

	@SuppressWarnings("unchecked")
	public List<PatternInfo> findPattern(HashMap map) {
		List<PatternInfo> lt=null;
		try
		{
		 lt= (List<PatternInfo>)baseSqlMapDAO.queryForList("pattern.findPattern", map);
		}
		catch(Exception e) {
				e.printStackTrace();
		}
		return lt;
	}
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
