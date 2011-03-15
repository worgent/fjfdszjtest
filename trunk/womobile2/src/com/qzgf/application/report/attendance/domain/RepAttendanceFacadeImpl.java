package com.qzgf.application.report.attendance.domain;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


/**
 * 任务
 * @author dpl
 * 
 */
public  class RepAttendanceFacadeImpl implements RepAttendanceFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(RepAttendanceFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	@SuppressWarnings("unchecked")
	public PageList findUser(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("attendancerep.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		
		List testList = baseSqlMapDAO.queryForList("attendancerep.findUserPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}	

	   /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public List AttendanceRep(HashMap map){
		List tmp=null;
		try{
		 tmp=baseSqlMapDAO.queryForList("attendancerep.attendancerep", map);
		} catch(RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

}
