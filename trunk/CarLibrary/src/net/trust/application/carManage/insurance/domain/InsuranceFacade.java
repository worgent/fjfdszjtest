package net.trust.application.carManage.insurance.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 保险管理
 *
 */
public interface InsuranceFacade {
	/**
	 * 查询保险信息总记录数
	 * @param map
	 * @return
	 */
    public int findInsuranceCount(HashMap map);
    /**
	 * 查询保险信息
	 * @param map
	 * @return
	 */
    public List findInsurance(HashMap map);
    /**
	 * 添加保险信息
	 * @param map
	 * @return
	 */
    public int insertInsurance(HashMap map);
    /**
	 * 修改保险信息
	 * @param map
	 * @return
	 */
	public int updateInsurance(HashMap map);
	
	/**
	 * 删除保险信息
	 * @param map
	 * @return
	 */
	public int deleteInsurance(HashMap map);
}
