package com.qzgf.application.baseArchives.UploadExcel.domain;

import java.util.HashMap;
import java.util.List;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;


public class UploadExcelFacadeImpl implements UploadExcelFacade{
	BaseSqlMapDAO baseSqlMapDAO;

    @SuppressWarnings("unchecked")
	public int insertUploadExcel(HashMap map){
    	int st=0;    	
    	st=baseSqlMapDAO.update("UploadExcel.insertUploadExcel",map);
    	return st;
    }
    
    
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	@SuppressWarnings("unchecked")
	public int isExistNumber(HashMap map) {
		return ((Integer)baseSqlMapDAO.queryForObject("UploadExcel.isExistNumber",map)).intValue();
		
	}
	
	public List findUploadExcelNumber(HashMap map){
		 return baseSqlMapDAO.queryForList("UploadExcel.findUploadExcelNumber",map);
	}

	@SuppressWarnings("unchecked")
	public int updateUploadExcel(HashMap map) {
		int st=0;    	
    	st=baseSqlMapDAO.update("UploadExcel.updateUploadExcel",map);
    	return st;
	}



	public List findUploadExcelOnlyList(HashMap map) {
		 return baseSqlMapDAO.queryForList("UploadExcel.findUploadExcelOnlyList",map);
	}





	@SuppressWarnings("unchecked")
	public int updateSaveUploadExcel(HashMap map) {
		return baseSqlMapDAO.update("UploadExcel.updateSaveUploadExcel",map);
	}


	@Override
	public List UploadExcelQueryList(HashMap map) {
		return baseSqlMapDAO.queryForList("UploadExcel.UploadExcelQueryList", map);	
	}


	@Override
	public int findUploadExcelQueryCount(HashMap map) {
		return ((Integer)baseSqlMapDAO.queryForObject("UploadExcel.findUploadExcelQueryCount",map)).intValue();
	}

	
	public int deleteUploadExcel(String employId) {
		int num = baseSqlMapDAO.update("UploadExcel.deleteUploadExcel", employId);
		return num;
	}
	
	
	
}
