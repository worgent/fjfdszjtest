package com.qzgf.application.gpsdata.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class GpsDataFacadeImpl implements GpsDataFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 查询车辆记录
	 * @param map
	 * @return
	 */
	public List findGpsRecord(HashMap map){
		List ls=new ArrayList();
		try{
		ls=baseSqlMapDAO.queryForList("GpsRecord.findGpsRecord", map);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return ls;
	}
	/**
	 * 查询车辆信息
	 * @param map
	 * @return
	 */
	public List findGpsCarMsg(HashMap map)
	{
		List ls=new ArrayList();
		try{
		ls=baseSqlMapDAO.queryForList("GpsRecord.findGpsCarMsg", map);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return ls;
	}
	/**
	 * 查询交结信息
	 * @param map
	 * @return
	 */
	public List findGpsWarnMsg(HashMap map)
	{
		List ls=new ArrayList();
		try{
		ls=baseSqlMapDAO.queryForList("GpsRecord.findGpsWarnMsg", map);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return ls;
	}
	/**
	 * 定时器查询当天数据AreaId(103)的所有车辆数据信息
	 * @param map
	 * @return
	 */
	public List findTimeGpsRecord(HashMap map)
	{
		List ls=new ArrayList();
		try{
		ls=baseSqlMapDAO.queryForList("GpsRecord.findTimeGpsRecord", map);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return ls;
	}
	
	/**
	 * 动态sql查询
	 * @param map
	 * @return
	 */
	public List DynamicSql(HashMap map)
	{
		List ls=new ArrayList();
		try{
		ls=baseSqlMapDAO.queryForList("GpsRecord.genSelectsql", map);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return ls;
	}
}
