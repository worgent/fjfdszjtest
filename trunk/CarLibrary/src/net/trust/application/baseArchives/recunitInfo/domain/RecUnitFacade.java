package net.trust.application.baseArchives.recunitInfo.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 来往单位信息管理
 * @author zhengmh
 *
 */
public interface RecUnitFacade {
	/**
	 * 查询往来单位信息总记录数
	 * @param map
	 * @return
	 */
    public int findRecUnitInfoCount(HashMap map);
    /**
	 * 查询往来单位信息
	 * @param map
	 * @return
	 */
    public List findRecUnitInfo(HashMap map);
    /**
	 * 添加往来单位信息
	 * @param map
	 * @return
	 */
    public int insertRecUnitInfo(HashMap map);
    /**
	 * 修改往来单位信息
	 * @param map
	 * @return
	 */
	public int updateRecUnitInfo(HashMap map);
	
	/**
	 * 删除往来单位信息
	 * @param map
	 * @return
	 */
	
	public int deleteRecUnitInfo(HashMap map);
}
