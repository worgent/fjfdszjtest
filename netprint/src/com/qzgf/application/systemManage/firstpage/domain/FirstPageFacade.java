package com.qzgf.application.systemManage.firstpage.domain;
import java.util.HashMap;
import java.util.List;

/**
 * 人员档案接口
 * @author Administrator
 *
 */
public interface FirstPageFacade {

	
     @SuppressWarnings("unchecked")
     public int findFirstPageCount(HashMap map);
	
   
	 @SuppressWarnings("unchecked")
	 public List findFirstPageList(HashMap map);
	
	 @SuppressWarnings("unchecked")
	public List gobackFirstList(HashMap map);
	 
	@SuppressWarnings("unchecked")

	public HashMap insertFirstPage(HashMap map, HashMap roldInfo)
	throws Exception;
	 
	 


	
}
