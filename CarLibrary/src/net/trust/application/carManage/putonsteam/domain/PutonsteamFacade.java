package net.trust.application.carManage.putonsteam.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 加油管理
 *
 */
public interface PutonsteamFacade {
	/**
	 * 查询加油信息总记录数
	 * @param map
	 * @return
	 */
    public int findPutonsteamCount(HashMap map);
    /**
	 * 查询加油信息
	 * @param map
	 * @return
	 */
    public List findPutonsteam(HashMap map);
    /**
	 * 添加加油信息
	 * @param map
	 * @return
	 */
    public int insertPutonsteam(HashMap map) throws Exception;
    /**
	 * 修改加油信息
	 * @param map
	 * @return
	 */
	public int updatePutonsteam(HashMap map) throws Exception;
	
	/**
	 * 删除加油信息
	 * @param map
	 * @return
	 */
	public int deletePutonsteam(HashMap map) throws Exception;
}
