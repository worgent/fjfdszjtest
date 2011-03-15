package com.qzgf.application.biz.report.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

public interface CustomReportFacade {
	@SuppressWarnings("unchecked")
	public List findCustomReport();
	@SuppressWarnings("unchecked")
	public List findQueryField(HashMap map);
	@SuppressWarnings("unchecked")
	public List findEnumList(String id);
	@SuppressWarnings("unchecked")
	public List findReportResultFieldList(String id);
	/**
	 * 用于导出Excel
	 * @param query
	 * @param dayMap1
	 * @param dayMap2
	 * @param parentType
	 * @param reportId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execExport(HashMap query,HashMap dayMap1,HashMap dayMap2, String parentType,String reportId);
	@SuppressWarnings("unchecked")
	public PageList queryReportList(HashMap query,HashMap dayMap1,HashMap dayMap2,String parentType,String reportId,Pages pages);
	/**
	 * 获得报表名称
	 * @param reportId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getReportName(String reportId);
}