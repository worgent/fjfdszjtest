package com.qzgf.application.selfConfig.twitterType.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.Pages;

/**
 * 日志分类持久层实现类
 * @author lsr
 *
 */
public class TwitterTypeFacadeImpl implements TwitterTypeFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TwitterTypeFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTwitterType(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("TwitterType.insertTwitterType", map);
		return st;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTwitterTypeById(HashMap map) {
		int num = baseSqlMapDAO.update("TwitterType.deleteTwitterTypeById", map);
		//return num;
		if(num==1){
			//把原来该分类下面的所有日志都归为默认分类
			 int num1=baseSqlMapDAO.update("TwitterType.updateTwitterNewType", map);
			 return num1;
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int updateTwitterTypeById(HashMap map) {
		int num = baseSqlMapDAO.update("TwitterType.updateTwitterTypeById", map);
		if (num == 1) {
			// 修改成功
			return 1;
		}
		// 修改失败
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List findTwitterType(HashMap map,Pages pages) {
		//列表内容
		List twitterTypeList = baseSqlMapDAO.queryForList("TwitterType.findTwitterType", map);
		
		return twitterTypeList;
	}
	

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
