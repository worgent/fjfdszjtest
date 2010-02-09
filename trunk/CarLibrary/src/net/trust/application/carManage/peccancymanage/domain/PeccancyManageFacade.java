package net.trust.application.carManage.peccancymanage.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 违章管理
 *
 */
public interface PeccancyManageFacade {
	/**
	 * 查询违章信息总记录数
	 * @param map
	 * @return
	 */
    public int findPeccancyCount(HashMap map);
    /**
	 * 查询违章信息
	 * @param map
	 * @return
	 */
    public List findPeccancy(HashMap map);
    /**
	 * 添加违章信息
	 * @param map
	 * @return
	 */
    public int insertPeccancy(HashMap map);
    /**
	 * 修改违章信息
	 * @param map
	 * @return
	 */
	public int updatePeccancy(HashMap map);
	
	/**
	 * 删除违章信息
	 * @param map
	 * @return
	 */
	public int deletePeccancy(HashMap map);
}
