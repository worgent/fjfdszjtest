
package com.qzgf.application.report.reportpattern.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public class TreportpatternFacadeImpl implements TreportpatternFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TreportpatternFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTreportpattern(HashMap map){
		//主表
		String pid=baseSqlMapDAO.sequences("t_reportpattern");
		map.put("pid", pid);
		int i=baseSqlMapDAO.update("Treportpattern.insertTreportpattern", map);
		//取取表头数据
		Object mm=map.get("psearchid");
		String[] searcharr=new String[1];
		String[] headarr=new String[1];
	    if(mm!=null){
	    	if(mm  instanceof   String){
	    		String now=mm.toString();
	    		searcharr[0]=now;
	    	}else
	    	searcharr=(String[])mm;
	    	//查询表头
			for(String val:searcharr){
				map.clear();
				map.put("pReportId", pid);
				map.put("pPatternId", val);
				i+=baseSqlMapDAO.update("Treportpattern.insertTreportpatternc", map);
			}
	    }
	    mm=map.get("pheadid");
	    if(mm!=null){
	    	if(mm  instanceof   String){
	    		String now=mm.toString();
	    		headarr[0]=now;
	    	}else
	    	headarr=(String[])mm;
	    	//报表表头
			for(String val:headarr){
				map.clear();
				map.put("pReportId", pid);
				map.put("pPatternId", val);
				i+=baseSqlMapDAO.update("Treportpattern.insertTreportpatternf", map);
			}
	    }
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public List findUser(HashMap map) {
		return baseSqlMapDAO.queryForList("User.findUser", map);
	}
	
	/**
	 * 
	 * Purpose      : 记录数返回
	 * @param map
	 * @return
	 */
	public int countTreportpattern(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("Treportpattern.findTreportpatternCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTreportpatternById(HashMap map) {
		//清数据
		String pid=map.get("pid").toString();
		//主表
		int i=baseSqlMapDAO.update("Treportpattern.deleteTreportpatternById", map);
		//子表
		map.put("pReportId", pid);
		i+=baseSqlMapDAO.update("Treportpattern.deleteTreportpatternc", map);
		i+=baseSqlMapDAO.update("Treportpattern.deleteTreportpatternf", map);
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public int allproTreportpatternById(HashMap map) {
		return baseSqlMapDAO.update("Treportpattern.allproTreportpatternById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTreportpatternById(HashMap map) {
		//主表
		String pid=map.get("pid").toString();
		int i= baseSqlMapDAO.update("Treportpattern.updateTreportpatternById", map);
		//取取表头数据
		Object mm=map.get("psearchid");
		String[] searcharr=new String[1];
		String[] headarr=new String[1];
		//先清数据
		map.put("pReportId", pid);
		i+=baseSqlMapDAO.update("Treportpattern.deleteTreportpatternc", map);
	    if(mm!=null){
	    	if(mm  instanceof   String){
	    		String now=mm.toString();
	    		searcharr[0]=now;
	    	}else
	    	searcharr=(String[])mm;
	    	//查询表头
			for(String val:searcharr){
				
				map.put("pPatternId", val);
				i+=baseSqlMapDAO.update("Treportpattern.insertTreportpatternc", map);
			}
	    }
	    mm=map.get("pheadid");
	    //先清数据
	    map.put("pReportId", pid);
	    i+=baseSqlMapDAO.update("Treportpattern.deleteTreportpatternf", map);
	    if(mm!=null){
	    	if(mm  instanceof   String){
	    		String now=mm.toString();
	    		headarr[0]=now;
	    	}else
	    	headarr=(String[])mm;
	    	//报表表头
			for(String val:headarr){
				
				map.put("pPatternId", val);
				i+=baseSqlMapDAO.update("Treportpattern.insertTreportpatternf", map);
			}
	    }
		return i;
		
	}
	@SuppressWarnings("unchecked")
	public List findTreportpattern(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findTreportpattern", map);
	}
	
	public List findpattern(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findpattern", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findTreportpatternPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Treportpattern.findTreportpatternCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Treportpattern.findTreportpatternPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}

	//动态列表头,查询头
	public List findTreportpatterncall(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findTreportpatterncall", map);
	}
	
	public List findTreportpatternc(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findTreportpatternc", map);
	}
	public List findTreportpatternfall(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findTreportpatternfall", map);
	}
	public List findTreportpatternf(HashMap map) {
		return baseSqlMapDAO.queryForList("Treportpattern.findTreportpatternf", map);
	}
	

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
