package com.qzgf.application.mobileclient.attendance.domain;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;


/**
 * 任务
 * @author dpl
 * 
 */
public  class AttendanceFacadeImpl implements AttendanceFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(AttendanceFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	

	   /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public int insertAttendance(HashMap map){
		int i=0;
		try{
		i=baseSqlMapDAO.update("attendance.insertattendance", map);
		} catch(RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

}
