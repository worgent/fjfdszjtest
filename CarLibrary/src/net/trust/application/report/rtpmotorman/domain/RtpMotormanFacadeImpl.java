package net.trust.application.report.rtpmotorman.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.PubFunction;
/**
 * 综合报表 -》 报警查询
 * @author chenqf
 *
 */
public class RtpMotormanFacadeImpl implements RtpMotormanFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 查询保养管理记录信息
	 * @param map
	 * @return
	 */
	public int findRtpMotormanCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("RtpMotorman.findRtpMotormanCount",map)).intValue();
    }
    /**
	 * 查询保养管理信息
	 * @param map
	 * @return
	 */
    public List findRtpMotorman(HashMap map){
    	return baseSqlMapDAO.queryForList("RtpMotorman.findRtpMotorman",map);
    }
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
}
