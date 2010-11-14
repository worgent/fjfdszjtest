<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">

package ${basepackage}.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public class ${className}FacadeImpl implements ${className}Facade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(${className}FacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insert${className}(HashMap map){
		return baseSqlMapDAO.update("${className}.insert${className}", map);
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
	public int count${className}(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("${className}.find${className}Count", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int delete${className}ById(HashMap map) {
		return baseSqlMapDAO.update("${className}.delete${className}ById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int allpro${className}ById(HashMap map) {
		return baseSqlMapDAO.update("${className}.allpro${className}ById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int update${className}ById(HashMap map) {
		return baseSqlMapDAO.update("${className}.update${className}ById", map);
	}
	@SuppressWarnings("unchecked")
	public List find${className}(HashMap map) {
		return baseSqlMapDAO.queryForList("${className}.find${className}", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList find${className}Page(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("${className}.find${className}Count", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("${className}.find${className}Page", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}


	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
