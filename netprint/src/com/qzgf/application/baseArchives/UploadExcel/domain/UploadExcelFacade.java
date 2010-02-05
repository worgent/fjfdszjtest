package com.qzgf.application.baseArchives.UploadExcel.domain;

import java.util.HashMap;
import java.util.List;

public interface UploadExcelFacade {
	
    @SuppressWarnings("unchecked")
	public int insertUploadExcel(HashMap map);
    
    
    @SuppressWarnings("unchecked")
	public int isExistNumber(HashMap map);
    
	public List findUploadExcelNumber(HashMap map);
    
    @SuppressWarnings("unchecked")
	public int updateUploadExcel(HashMap map);
    
    

    
    @SuppressWarnings("unchecked")
	public List findUploadExcelOnlyList(HashMap map);
    
    

    
	//更新保存
	public int updateSaveUploadExcel(HashMap map);
	
    
	@SuppressWarnings("unchecked")
	public int findUploadExcelQueryCount(HashMap map);
	@SuppressWarnings("unchecked")
	public List UploadExcelQueryList(HashMap map);
	
    
	public int deleteUploadExcel(String employId);
	
	
    /**
	 * 查询机构部门信息
	 * @param map
	 * @return
	 */
   
}
