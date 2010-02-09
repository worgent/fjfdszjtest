package net.trust.application.baseArchives.institutionManage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.application.systemManage.manager.dto.UserInfo;
/**
 * 管理机购
 * @author zhengmh
 *
 */
public interface InstitutionFacade {
	/**
	 * 查询机构总记录信息
	 * @param map
	 * @return
	 */
    public int findInstitutionCount(HashMap map);
    /**
	 * 查询机构信息
	 * @param map
	 * @return
	 */
    public List findInstitutionInfo(HashMap map);
    /**
	 * 添加机构信息
	 * @param map
	 * @return
	 */
    public int insertInstitution(HashMap map);
    /**
	 * 修改机构信息
	 * @param map
	 * @return
	 */
	public int updateInstitutionInfo(HashMap map);
	
	/**
	 * 删除机构信息
	 * @param map
	 * @return
	 */
	public int deleteInstitutionInfo(HashMap map);
	
}
