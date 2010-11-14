<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public interface ${className}Facade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insert${className}(HashMap map);
	

	public abstract int count${className}(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int delete${className}ById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int allpro${className}ById(HashMap map);
	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int update${className}ById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List find${className}(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList find${className}Page(HashMap map,Pages pages);
	
}