package net.trust.application.carManage.attempermanage.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.sms.SMProxySendFacade;
import net.trust.utils.sms.SMProxySendFacadeImplTimeTask;
import net.trust.utils.sms.SmsBean;
/**
 * 调度管理
 *
 */
public class AttemperManageFacadeImpl implements AttemperManageFacade {
	public BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO sqlServerBaseSqlMapDAO;
	
	private SMProxySendFacade smproxySendFacade;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	public BaseSqlMapDAO getSqlServerBaseSqlMapDAO() {
		return sqlServerBaseSqlMapDAO;
	}
	public void setSqlServerBaseSqlMapDAO(BaseSqlMapDAO sqlServerBaseSqlMapDAO) {
		this.sqlServerBaseSqlMapDAO = sqlServerBaseSqlMapDAO;
	}

	/**
	 * 调度管理记录数
	 * @param map
	 * @return
	 */
	public int findAttemperCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("AttemperManage.findAttemperCount", map));
	}
	/**
	 * 调度管理记录
	 * @param map
	 * @return
	 */
	public List findAttemper(HashMap map){
		return baseSqlMapDAO.queryForList("AttemperManage.findAttemper", map);
	}
	/**
	 * 创建调度记录
	 * @param map
	 * @return	1:成功
	 * 			0：失败
	 * 			-100：车辆编号为空
	 * 			-101：本系统中无该车辆信息
	 * 			-200：司机号为空
	 * 			-201：本系统中无司机信息
	 */
	public HashMap insertAttemper(HashMap map){
		if (null == map.get("carNoCode") || "".equals(map.get("carNoCode"))){
			map.clear();
			map.put("st", -100);//车辆编号为空
			map.put("attemperManageId", "");
			return map;	
		}
//		if (null == map.get("motorman") || "".equals(map.get("motorman"))){
//			map.clear();
//			map.put("st", -200);//司机号为空
//			map.put("attemperManageId", "");
//			return map;
//		}
		
		//通过要调度的GPS车牌号取得系统中车辆ID
		
		HashMap Ctmp=new HashMap();
		Ctmp.put("carNoCode", map.get("carNoCode").toString());
		List list = baseSqlMapDAO.queryForList("CarManage.findCarInfo",Ctmp);
		if (null == list || list.size() == 0){
			map.clear();
			map.put("st", -101);//本系统中无该车辆信息
			map.put("attemperManageId", "");
			return map;
		}
		
		HashMap tmp = (HashMap)list.get(0);
		if (null == tmp.get("car_no_id") || "".equals(tmp.get("car_no_id"))){
			map.clear();
			map.put("st", -101);//本系统中无该车辆信息
			map.put("attemperManageId", "");
			return map;
		}
		
		map.put("carNoId", tmp.get("car_no_id"));
		map.put("carPhone", tmp.get("car_phone"));
		
		//通过要调度GPS车辆的司机号取得系统中员工ID
//		map.put("staffCode", map.get("motorman"));
//		list = baseSqlMapDAO.queryForList("EmployeeInfoManage.findEmployeeInfo", map);
//		if (null == list || list.size() == 0){
//			map.clear();
//			map.put("st", -201);//本系统中无司机信息
//			map.put("attemperManageId", "");
//			return map;
//		}
//		
//		tmp = (HashMap)list.get(0);
//		if (null == tmp.get("staff_info_id") || "".equals(tmp.get("staff_info_id"))){
//			map.clear();
//			map.put("st", -201);//本系统中无司机信息
//			map.put("attemperManageId", "");
//			return map;
//		}
		
		map.put("motorId", tmp.get("staff_info_id"));
		
		//创建调度信息记录
		String attemperManageId = baseSqlMapDAO.sequences("attemper_manage_id");
		map.put("attemperManageId", attemperManageId);
		int st = baseSqlMapDAO.update("AttemperManage.insertAttemper", map);
		//调度成功后进行短信发送
		if (st > 0){
			if (null != map.get("carPhone") && !"".equals(map.get("carPhone"))){
				String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
				HashMap param = new HashMap();
				param.put("sendSmsId", sendSmsId);		//流水号
				param.put("callStaffId", "");	//接收者对应员工表ID
				param.put("callManName", "");	//接收者姓名
				param.put("callPhone", map.get("carPhone"));	//接收者手机号码
				param.put("sendContent", map.get("smsContext"));//短信内容
				param.put("staffId", map.get("createMan"));			//短信内容
				param.put("sourceOrderType", "2");		//原单类型
				param.put("sourceOrderCode", attemperManageId);//原单类型
				param.put("cityId", map.get("cityId"));//原单类型
				
				baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param);
				
				
				ArrayList mobileList=new ArrayList();
				mobileList.add((String)map.get("carPhone"));
				SmsBean smsBean = new SmsBean();
				smsBean.setMobileNoList(mobileList);
				smsBean.setSmsContent((String)map.get("smsContext"));
				smsBean.setSn("");
				int res=0;
				try{
				res = smproxySendFacade.SendMessage(smsBean);
				}catch(Exception e){
					System.out.println(e.toString());
				}
				if (res == 1){
					param.clear();
					param.put("sendSmsId", sendSmsId);		//流水号
					baseSqlMapDAO.update("SmsManage.updateSendSmsState", param);
				}
			}
		}
		
		map.clear();
		map.put("st", st);
		map.put("attemperManageId", attemperManageId);
		
		return map;
	}
	/**
	 * 修改调度记录
	 * @param map
	 * @return
	 */
	public int updateAttemper(HashMap map){
		return baseSqlMapDAO.update("AttemperManage.updateAttemper", map);
	}
	/**
	 * 删除调度记录
	 * @param map
	 * @return
	 */
	public int deleteAttemper(HashMap map){
		return baseSqlMapDAO.update("AttemperManage.deleteAttemper", map);
	}
	
	/**
	 * 查询GPS系统中与传入位置匹配的车辆数据
	 * @param map
	 * @return
	 */
	public List getGPSCarLocus(HashMap map){
		return sqlServerBaseSqlMapDAO.queryForList("AttemperManage.findGPSCarLocus", map);
	}
	/**
	 * @return the smproxySendFacade
	 */
	public SMProxySendFacade getSmproxySendFacade() {
		return smproxySendFacade;
	}
	/**
	 * @param smproxySendFacade the smproxySendFacade to set
	 */
	public void setSmproxySendFacade(SMProxySendFacade smproxySendFacade) {
		this.smproxySendFacade = smproxySendFacade;
	}

}
