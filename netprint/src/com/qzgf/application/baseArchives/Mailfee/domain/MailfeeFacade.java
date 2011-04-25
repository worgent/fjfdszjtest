package com.qzgf.application.baseArchives.Mailfee.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.utils.export.ImportIface;

public interface MailfeeFacade {
	

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTdMailfeearea(HashMap map);
	

	public abstract int countTdMailfeearea(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTdMailfeeareaById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int allproTdMailfeeareaById(HashMap map);
	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTdMailfeeareaById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTdMailfeearea(HashMap map);
	
	
	public int insertExcelTdMailfeeare(ImportIface exportExc);
	

}
