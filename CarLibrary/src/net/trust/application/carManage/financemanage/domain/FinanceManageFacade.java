package net.trust.application.carManage.financemanage.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 财务管理
 * @author chenqf
 *
 */
public interface FinanceManageFacade {
	/**
	 * 查询财务单据总数
	 * @param map
	 * @return
	 */
	public int findFinanceCount(HashMap map);
	/**
	 * 查询财务单据
	 * @param map
	 * @return
	 */
	public List findFinance(HashMap map);
	/**
	 * 创建财物管理交单据 
	 * @param map
	 * @return
	 */
	public int insertFinance(HashMap map);
	/**
	 * 修改财务单据
	 * @param map
	 * @return
	 */
	public int updateFinance(HashMap map);
	/**
	 * 删除财务单据
	 * @param map
	 * @return
	 */
	public int deleteFinance(HashMap map);
}
