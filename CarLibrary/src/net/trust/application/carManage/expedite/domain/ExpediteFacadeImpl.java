package net.trust.application.carManage.expedite.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.sms.SMProxySendFacade;
import net.trust.utils.sms.SmsBean;

/**
 * 派车登记
 * 
 */
public class ExpediteFacadeImpl implements ExpediteFacade {
	private BaseSqlMapDAO baseSqlMapDAO;

	private SMProxySendFacade smproxySendFacade;

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	/**
	 * 派车信息
	 * 
	 * @param map
	 * @return
	 */
	public int findExpediteCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findExpediteCount", map)).intValue();
	}

	public List findExpedite(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.findExpedite", map);
	}

	/**
	 * 短信发送
	 * 
	 * @param smsManId
	 *            接收者对应员工表ID
	 * @param callManName
	 *            接收者姓名
	 * @param smsMobileNo
	 *            接收者手机号码
	 * @param smsContext
	 *            短信内容
	 * @param staffId
	 *            发送者ID
	 * @param sourceOrderCode
	 *            原单号，即为派车单的单据号
	 */
	private void sendSms(String[] smsManId, String[] callManName,
			String[] smsMobileNo, String[] smsContext, String staffId,
			String sourceOrderCode, String cityId, String sourceOrderType,String[] msistype,String per) {
		int st = 0;
		List list = null;
		SmsBean smsBean = null;
		HashMap param = null;
		String sendSmsId = "";// 发送短信主键
		String msisdnId = "";// 已经发送短信主键(-源终端MSISDN号码)

		for (int i = 0; i < smsManId.length; i++) {
			if (null != smsMobileNo[i] && !"".equals(smsMobileNo[i])) {
				sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
				if(msistype!=null)//非空
				{
					if(msistype[i].equals("1"))//需要回复的处理
					{
						msisdnId =per+ baseSqlMapDAO.sequencesmsisdn("msisdn_id");
					}
				}

				param = new HashMap();
				param.put("sendSmsId", sendSmsId); // 流水号
				param.put("callStaffId", smsManId[i]); // 接收者对应员工表ID
				param.put("callManName", callManName[i]); // 接收者姓名
				param.put("callPhone", smsMobileNo[i]); // 接收者手机号码
				param.put("sendContent", smsContext[i]);// 短信内容
				param.put("staffId", staffId); // 短信内容
				param.put("sourceOrderType", sourceOrderType); // 原单类型
				param.put("sourceOrderCode", sourceOrderCode);// 原单号
				param.put("msisdnId", msisdnId);// 短信长号
				param.put("cityId", cityId);// 原单类型

				baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param);
				smsBean = new SmsBean();
				ArrayList mobileList = new ArrayList();
				mobileList.add(smsMobileNo[i]);
				smsBean.setMobileNoList(mobileList);
				smsBean.setSmsContent(smsContext[i]);
				smsBean.setSn(msisdnId);
				try {
					st = smproxySendFacade.SendMessage(smsBean);
				} catch (Exception e) {
					System.out.println(e.toString());
				}

				if (st == 1) {
					param.clear();
					param.put("sendSmsId", sendSmsId); // 流水号
					baseSqlMapDAO.update("SmsManage.updateSendSmsState", param);
				}
			}
		}
	}

	/**
	 * 创建派车记录
	 */
	public HashMap insertExpedite(HashMap map, HashMap roldInfo)
			throws Exception {

		String expediteCarId = baseSqlMapDAO.sequences("expedite_car_id");
		map.put("expediteCarId", expediteCarId);
		// 插入派车信息表
		int st = baseSqlMapDAO.update("Expedite.insertExpedite", map);
		if (st == 0) {
			throw new Exception("添加派车信息失败.");
		}
		// 插入中转站信息表，多条纪录
		if (null != roldInfo && roldInfo.size() > 0) {
			this.addRoldInfo(roldInfo, Integer.valueOf(
					roldInfo.get("num").toString()).intValue(), expediteCarId,
					"1");
		}

		// 发送短信
		sendSms((String[]) map.get("smsManId"), (String[]) map
				.get("smsManName"), (String[]) map.get("smsMobileNo"),
				(String[]) map.get("smsContext"), map.get("createMan")
						.toString(), expediteCarId, map.get("cityId")
						.toString(), "1",null,"");
		//2009.6.10 对于(0:代表派车单模块)需要修改车辆状态和申请单状态
		if (map.get("billType").toString().equals("0")) {
//			HashMap carInfoMap = new HashMap();
//			// 设置车辆状态为 2：出车
//			carInfoMap.put("carState", "2");
//			carInfoMap.put("carNoId", map.get("carNoId"));
//			st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
//			if (st == 0) {
//				throw new Exception("修改车辆状态时出错");
//			}

			// 再新增完单据后，修改原派车申请单的标识位，使其为这个流程完成
			HashMap hm = new HashMap();
			hm.put("expediteapplyId", map.get("expediteapplyId").toString());
			hm.put("useState", 1);
			st = baseSqlMapDAO.update("Expedite.updateApplyer", hm);
			if (st == 0) {
				throw new Exception("修改派车申请单状态出错");
			}
		}
		map.clear();
		map.put("st", st);
		map.put("expediteCarId", expediteCarId);
		return map;
	}

	/**
	 * 修改出车信息
	 */
	public HashMap updateExpedite(HashMap map, HashMap roldInfo)
			throws Exception {
		int st = 0;
		String expediteCarId = map.get("expediteCarId").toString();

//		HashMap expediteMap = new HashMap();
//		expediteMap.put("carNoId", map.get("carNoId"));
//		expediteMap.put("expediteCarId", expediteCarId);
		
		//2009-06-10对于派车单模块,修改车辆状态变化的处理
//		if (map.get("billType").toString().equals("0")) {
//			// 判断当前修改的出车信息，是否有改变出车的车辆编号
//			int ifAlteration = ((Integer) baseSqlMapDAO.queryForObject(
//					"Expedite.findExpediteCount", expediteMap));
//
//			if (ifAlteration == 0) {
//				expediteMap.remove("carNoId");
//				expediteMap = (HashMap) baseSqlMapDAO.queryForObject(
//						"Expedite.findExpedite", expediteMap);
//
//				HashMap carInfoMap = new HashMap();
//				// 将原来的车辆状态改回空闲
//				carInfoMap.put("carNoId", expediteMap.get("car_no_id")); // 取得原来的车辆ID
//				carInfoMap.put("carState", "1"); // 设定状态为空闲
//				st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
//				if (st == 0) {
//					throw new Exception("修改原车辆状态时出错");
//				}
//
//				// 将新的车辆状态置为出车
//				carInfoMap.put("carNoId", map.get("carNoId")); // 取得新的车辆ID
//				carInfoMap.put("carState", "2"); // 设定状态为出车
//				st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
//				if (st == 0) {
//					throw new Exception("修改原车辆状态时出错");
//				}
//			}
//		}
		
		st = baseSqlMapDAO.update("Expedite.updateExpedite", map);
		if (st == 0) {
			throw new Exception("修改出车信息时出错");
		}
		// 更新中转站信息，多条纪录
		map.put("roadType", "1");
		this.deleteRoldInfo(map);// 将中转站信息全设为归档，后面会有重新创建新的中转站信息
		if (st == 0) {
			throw new Exception("清除中转站信息出错");
		}

		// 发送短信
		sendSms((String[]) map.get("smsManId"), (String[]) map
				.get("smsManName"), (String[]) map.get("smsMobileNo"),
				(String[]) map.get("smsContext"), map.get("editorMan")
						.toString(), expediteCarId, map.get("cityId")
						.toString(), "1",null,"");

		// 插入中转站信息表，多条纪录
		if (null != roldInfo && roldInfo.size() > 0) {
			this.addRoldInfo(roldInfo, Integer.valueOf(
					roldInfo.get("num").toString()).intValue(), expediteCarId,
					"1");
		}

		map.clear();
		map.put("st", st);
		map.put("expediteCarId", expediteCarId);

		return map;
	}

	/**
	 * 删除出车信息
	 */
	public int deleteExpedite(HashMap map) throws Exception {
		int st = 0;
		
		// 2009-06-10对于派车单模块,修改车辆状态变化的处理
//		if (map.get("billType").toString().equals("0")) {
//			// 设置车辆状态为 1：空闲
//			HashMap expediteMap = (HashMap) baseSqlMapDAO.queryForObject(
//					"Expedite.findExpedite", map);
//			HashMap carInfoMap = new HashMap();
//			carInfoMap.put("carNoId", expediteMap.get("car_no_id")); // 取得车辆ID
//			carInfoMap.put("carState", "1"); // 设定状态为空闲
//			st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
//			if (st == 0) {
//				throw new Exception("修改车辆状态时出错");
//			}
//		}
		
		st = baseSqlMapDAO.update("Expedite.deleteExpedite", map);
		if (st == 0) {
			throw new Exception("删除出车信息时出错");
		}

		return st;

	}

	/**
	 * 查询派车与回车关系
	 * 
	 * @param map
	 * @return
	 */
	public int findExpediteAndGobackCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findExpediteAndGobackCount", map)).intValue();
	}

	/**
	 * 查询派车与回车关系总数
	 * 
	 * @param map
	 * @return
	 */
	public List findExpediteAndGoback(HashMap map) {
		return baseSqlMapDAO
				.queryForList("Expedite.findExpediteAndGoback", map);
	}

	/**
	 * 回车信息
	 * 
	 * @param map
	 * @return
	 */
	public int findGobackCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findGobackCount", map)).intValue();
	}

	public List findGoback(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.findGoback", map);
	}

	public int insertGoBack(HashMap map, HashMap roldInfo) throws Exception {
		String expediteCarId = map.get("expediteCarId").toString();
		// 插入回车信息表
		int st = baseSqlMapDAO.update("Expedite.insertGoBack", map);
		if (st == 0) {
			throw new Exception("添加回车信息失败.");
		}

		HashMap param = new HashMap();
		param.put("expediteState", "2");
		param.put("expediteCarId", map.get("expediteCarId"));
		st = baseSqlMapDAO.update("Expedite.updateExpedite", param);
		if (st == 0) {
			throw new Exception("修改派车状态出错.");
		}

		//2009-06-10对于派车单模块,修改车辆状态变化的处理
//		if (map.get("billType").toString().equals("0")) {
//			param = new HashMap();
//			param.put("carNoId", map.get("carNoId")); // 取得车辆ID
//			param.put("carState", "1"); // 设定状态为空闲
//			st = baseSqlMapDAO.update("CarManage.updateCar", param);
//			if (st == 0) {
//				throw new Exception("修改车辆状态时出错");
//			}
//		}

		if (null != roldInfo && roldInfo.size() > 0){
			this.addRoldInfo(roldInfo, Integer.valueOf(roldInfo.get("num").toString()).intValue(), expediteCarId,"2");
		}
		return st;
	}

	public int updateGoBack(HashMap map, HashMap roldInfo) throws Exception {

		String expediteCarId = map.get("expediteCarId").toString();
		// 插入回车信息表
		int st = baseSqlMapDAO.update("Expedite.updateGoBack", map);
		if (st == 0) {
			throw new Exception("更新回车信息失败.");
		}

		// 更新中转站信息，多条纪录
		map.put("roadType", "2");
		this.deleteRoldInfo(map);// 将中转站信息全设为归档，后面会有重新创建新的中转站信息
		// 插入中转站信息表，多条纪录
		if (null != roldInfo && roldInfo.size() > 0) {
			this.addRoldInfo(roldInfo, Integer.valueOf(
					roldInfo.get("num").toString()).intValue(), expediteCarId,
					"2");
		}
		return st;

	}

	public int deleteGoback(HashMap map) {
		return baseSqlMapDAO.update("Expedite.deleteGoback", map);

	}

	/**
	 * 中转信息
	 * 
	 * @param map
	 * @return
	 */
	public List findRoldInfo(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.findRoldInfo", map);
	}

	public int insertRoldInfo(HashMap map) {
		return baseSqlMapDAO.update("Expedite.insertRoldInfo", map);
	}

	public int updateRoldInfo(HashMap map) {
		return baseSqlMapDAO.update("Expedite.updateRoldInfo", map);
	}

	public int deleteRoldInfo(HashMap map) {
		return baseSqlMapDAO.update("Expedite.deleteRoldInfo", map);

	}

	public void addRoldInfo(HashMap roldInfo, int num, String expediteCarId,
			String roadType) throws Exception {
		int st = 0;
		HashMap map;
		if (num == 1) {
			roldInfo.put("expediteCarId", expediteCarId);
			roldInfo.put("roadType", roadType);
			st = baseSqlMapDAO.update("Expedite.insertRoldInfo", roldInfo);// 添加中转站点情况
			if (st < 1) {
				throw new Exception("添加中转站点情况.");
			}
		} else if (num > 1) {
			String[] firstLocus = (String[]) roldInfo.get("firstLocus");
			String[] firstDate = (String[]) roldInfo.get("firstDate");
			String[] transferLocur = (String[]) roldInfo.get("transferLocur");
			String[] transferDate = (String[]) roldInfo.get("transferDate");

			String[] runMileage = {};
			String[] useOilNum = {};
			String[] useOilCharge = {};
			String[] firstMileage = {};
			String[] transferMileage = {};
			String[] roadCharge = {};
			String[] runTime = {};
			//String[] beiZhu = {};

			if (roldInfo.get("runMileage") != null) {
				runMileage = (String[]) roldInfo.get("runMileage");
				useOilNum = (String[]) roldInfo.get("useOilNum");
				useOilCharge = (String[]) roldInfo.get("useOilCharge");
				firstMileage = (String[]) roldInfo.get("firstMileage");
				transferMileage = (String[]) roldInfo.get("transferMileage");
				roadCharge = (String[]) roldInfo.get("roadCharge");
				runTime = (String[]) roldInfo.get("runTime");
				//beiZhu = (String[]) roldInfo.get("beiZhu");
			}

			for (int i = 0; i < num; i++) {
				map = new HashMap();
				map.put("firstLocus", firstLocus[i]);
				map.put("firstDate", firstDate[i]);
				map.put("transferLocur", transferLocur[i]);
				map.put("transferDate", transferDate[i]);

				if (roldInfo.get("runMileage") != null) {
					map.put("runMileage", runMileage[i]);
					map.put("useOilNum", useOilNum[i]);
					map.put("useOilCharge", useOilCharge[i]);
					map.put("firstMileage", firstMileage[i]);
					map.put("transferMileage", transferMileage[i]);
					map.put("roadCharge", roadCharge[i]);
					map.put("runTime", runTime[i]);
					//map.put("beiZhu", beiZhu[i]);
				}
				// map.put("useOilCharge", useOilCharge[i]);
				// map.put("firstManager", firstManager[i]);
				// map.put("transferManager", transferManager[i]);
				map.put("expediteCarId", expediteCarId);
				map.put("roadType", roadType); // 路途类型1：出车 2：回车
				st = baseSqlMapDAO.update("Expedite.insertRoldInfo", map);// 添加中转站点情况
				if (st < 1) {
					throw new Exception("添加中转站点情况失败.");
				}
			}
		}
	}

	/**
	 * 2009-02-25 派车申请
	 * 
	 * @param map
	 * @return
	 */
	public int findApplyerCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findApplyerCount", map)).intValue();
	}

	public List findApplyer(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.findApplyer", map);
	}

	public int insertApplyer(HashMap map) throws Exception {
		return baseSqlMapDAO.update("Expedite.insertApplyer", map);
	}

	public int updateApplyer(HashMap map) throws Exception {
		return baseSqlMapDAO.update("Expedite.updateApplyer", map);
	}

	public int deleteApplyer(HashMap map) throws Exception {
		return baseSqlMapDAO.update("Expedite.deleteApplyer", map);
	}

	/**
	 * 2009-02-26 派车申请日志
	 * 
	 * @param map
	 * @return
	 */
	public int findFlowerTaskCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findFlowerTaskCount", map)).intValue();
	}

	public List findFlowerTask(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.findFlowerTask", map);
	}

	public int updateFlowerTask(HashMap map) throws Exception {
		return baseSqlMapDAO.update("Expedite.updateFlowerTask", map);
	}

	/**
	 * 2009-02-28 审批日志的自动生成
	 * 
	 * @param map
	 * @return
	 */
	public void execProFlowerTask(HashMap map) {
		baseSqlMapDAO.update("Expedite.proFlowerTask", map);
	}

	/**
	 * 2009-03-01 取得需要审批的数据
	 * 
	 * @param map
	 * @return
	 */
	public List getProFlowerTask(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.getFlowerTask", map);
	}

	public List getAttempermsg(HashMap map) {
		return baseSqlMapDAO.queryForList("Expedite.getAttempermsg", map);
	}
	/**
	 * 保存申请单，分别为"保存"与"保存提交"的区别。
	 * 2009-03-21 修改为部门级别
	 * @param map
	 */
	public int saveApplyer(HashMap map) throws Exception {
		int i = 0;// 默认更新数据
		if (map.get("expediteapplyId").toString().equals("")) {
			// 判断为增加数据,因为新增加时不会有主键，而审批处理过程必须有主键。
			map.put("expediteapplyId", baseSqlMapDAO.sequences("expediteapply_id"));
			i = 1;
		}
		// 当为保存并提交时多做
		if (map.get("cmdFlag").toString().trim().equals("1")) {
			// 生成审批日志
			map.put("flowerdefineCode", "Exp_apply");
			execProFlowerTask(map);
			// 修改标识位
			map.put("promptcontent", map.get("useMan").toString()+";"
					+ map.get("useMobile").toString()+";"
					+ map.get("useExcuse").toString());
			List ls = getProFlowerTask(map);
			int count = ls.size();
			if (count > 0) {
				// 发送下一次要审批人员sendsmsAuditing(List ls,String staffId,String
				// cityId,String srcNo)
				sendsmsAuditing(ls, map.get("editorMan").toString(), map.get(
						"cityId").toString(), map.get("expediteapplyId")
						.toString());
				map.put("useState", 4);
			} else {
				// 发送短信给调度人员
				HashMap atths = new HashMap();
				atths.put("extdeptId", map.get("extdeptId").toString());
				atths.put("srcnoContent", map.get("expediteapplyId").toString()+ "(" 
					+ map.get("useMan").toString()+";"
					+ map.get("useMobile").toString()+";"
					+ map.get("useExcuse").toString() + ")");
				List attemperls = getAttempermsg(atths);
				sendsmsAuditing(attemperls, map.get("editorMan").toString(),map.get("cityId").toString(), map.get("expediteapplyId").toString());
				map.put("useState", 2);
			}
		}
		// 保存操作
		else {
			map.put("useState", 3);
		}
		// 先增加，后修改
		if (i == 1)
			i = insertApplyer(map);
		else
			i = updateApplyer(map);
		return i;
	}

	/**
	 * 处理审批，为同意/不同意
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int proAgent(HashMap map) throws Exception {
		
		String expediteapplyId = map.get("expediteapplyId").toString();
		// 得到单据状态
		HashMap tmpid = new HashMap();
		tmpid.put("expediteapplyId", expediteapplyId);
		HashMap applyDate = (HashMap) (findApplyer(tmpid).get(0));
		String cityId = applyDate.get("city_id").toString();
		if (!applyDate.get("use_state").toString().equalsIgnoreCase(
				map.get("useState").toString())) {
			return -1;
		}

		int i = 0;

		// 查询需要发送的人员
		HashMap param = new HashMap();
		param.put("staffId", map.get("editorMan").toString());
		param.put("extdeptId", map.get("extdeptId").toString());
		param.put("content", expediteapplyId + "|"
				+ map.get("useState").toString() + "||"
				+ map.get("agentFlag").toString());

		List proAudls = baseSqlMapDAO.queryForList("Expedite.proAuditing",param);
		if (proAudls==null ||(proAudls.size()==1 && ((HashMap)(proAudls.get(0))).get("msistype").toString().equals("0"))) {
			i=0;
		}else
		{
			// 发送短信通知申请人，通过审核
			sendsmsAuditing(proAudls, map.get("approveCode").toString(),cityId, expediteapplyId);
			i = 1;//成功
		}
		return i;
	}


	/**
	 * 2008-03-03 说明:发送信息给审批用户
	 * 
	 * @param ls
	 *            数据
	 * @param staffId
	 *            员工号
	 * @param cityId
	 *            城市ID
	 * @param srcNo
	 *            源单号
	 */
	private void sendsmsAuditing(List ls, String staffId, String cityId,
			String srcNo) {
		int size = ls.size();
		ArrayList moblies = new ArrayList();
		HashMap flowertask = new HashMap();
		String[] mobile = new String[size];
		String[] content = new String[size];
		String[] smsstaffid = new String[size];
		String[] smsstaffname = new String[size];
		String[] msistype=new String[size];
		for (int i = 0; i < size; i++) {
			flowertask = (HashMap) ls.get(i);
			mobile[i] = flowertask.get("mobile").toString();
			content[i] = flowertask.get("content").toString();
			smsstaffid[i] = "";
			smsstaffname[i] = flowertask.get("smsstaffname").toString();
			msistype[i]=flowertask.get("msistype").toString();
		}
		// 发送短信
		sendSms(smsstaffid, smsstaffname, mobile, content, staffId, srcNo,
				cityId, "8",msistype,"1");
		//原来是00,改成1审批短信
	}
	/**
	 * 与上面的方法类似，仅重载了该方法。
	 * 功能:处理单条记录,发送短信,同时写入数据库,客户反馈
	 * @param flowertask
	 * @param staffId
	 * @param cityId
	 * @param srcNo
	 */
	private void sendsmsAuditing(HashMap flowertask, String staffId,
			String cityId, String srcNo) {
        String[] msistype={flowertask.get("msistype").toString()};
		String[] mobile = { flowertask.get("mobile").toString() };
		String[] content = { flowertask.get("content").toString() };
		String[] smsstaffid = { flowertask.get("smsstaffid").toString() };
		String[] smsstaffname = { flowertask.get("smsstaffname").toString() };
		// 发送短信
		sendSms(smsstaffid, smsstaffname, mobile, content, staffId, srcNo,
				cityId, "9",msistype,"2");
		//原来是01,改成2客户反馈
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
	/**
	 * 功能说明:更新回车信息主表的,feedback_state状态.
	 */
	public int updateGoBackMain(HashMap map)  {
		return baseSqlMapDAO.update("Expedite.updateGoBack", map);
	}
	/**
	 * 客户反馈
	 * @param map
	 * @return
	 */
	public int sendFeedback(HashMap map){
		int result=0;
        HashMap hsfeedback=new HashMap();
        //1.更新回车信息的标识位,从短信未发送转为已发送
        hsfeedback.put("feedbackState", 1);
        hsfeedback.put("gobackCarId",map.get("gobackCarId").toString());
        result=updateGoBackMain(hsfeedback);
        //2.判断类原派车单的类型,0:派车单,1:临时派车
		//3.设置发送短信
        hsfeedback.clear();
        hsfeedback.put("msistype","1");//短信类型
        hsfeedback.put("mobile", map.get("useCarManmobile"));//用车人名称
        hsfeedback.put("content", "尊敬的"+map.get("useCarManName").toString()+"先生(小姐),您好!欢迎您对此次本司"
        		+map.get("drivername").toString()+"驾驶员的服务进行评价,请您直接回复：A非常满意;B满意;C不满意,感谢您对国通广拓汽车商务有限公司的支持!(注:仅需回复A,B或C)");
        hsfeedback.put("smsstaffid", "");//接收短信人员工号	
        hsfeedback.put("smsstaffname", map.get("useCarManName"));//接收短信人员名称
        //3.调用公用发送短信方法
	    sendsmsAuditing(hsfeedback,map.get("editorMan").toString(),map.get("cityid").toString(), map.get("gobackCarId").toString());
	    return result;
	}
	
	
	/**
	 * 2009-04-14
	 * 司机行车登记
	 * 2009-06-22
	 * 修改说明:因为客户需求增加,对金额的统计处理,
	 * 结合原财务模块的处理方式,所以修改如下:
	 * @param map
	 * @return
	 */
    public int findDriversmsCount(HashMap map){
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findDriversmsCount", map)).intValue();
    }
    
    public List findDriversms(HashMap map){
    	return baseSqlMapDAO.queryForList("Expedite.findDriversms", map);
    }
    
    public int insertDriversms(HashMap map) throws Exception{
    	String pb_seq = baseSqlMapDAO.sequences("driversms_id");
    	map.put("driversmsId",pb_seq);
    	int st=baseSqlMapDAO.update("Expedite.insertDriversms", map);
    	if (st == 0){
    		throw new Exception("创建司机行车登记时出错");
    	}
    	//创建财务单据
    	map.put("sourceOrderType", "1");    	
		map.put("sourceOrderCode",pb_seq);
		map.put("charge",pb_seq);
		map.put("memo","");
		map.put("carNoId","");
		map.put("createMan","");
		map.put("cityId","");
		st=baseSqlMapDAO.update("FinanceManage.insertFinance", map);
		if (st == 0){
    		throw new Exception("创建财务单据时出错");
    	}
    	return st;
    }
    
	public int updateDriversms(HashMap map)throws Exception{
		int st= baseSqlMapDAO.update("Expedite.updateDriversms", map);
		if (st == 0){
    		throw new Exception("修改司机行车登记时出错");
    	}
		//修改财务单据
		map.put("sourceOrderCode", map.get("driversmsId"));
		map.put("sourceOrderType", "1");
		st = baseSqlMapDAO.update("FinanceManage.updateFinance", map);
		if (st == 0){
    		throw new Exception("修改财务单据时出错");
    	}
		return st;
	}
	
	public int deleteDriversms(HashMap map) throws Exception{
		int st=baseSqlMapDAO.update("Expedite.deleteDriversms", map);
		if (st == 0){
    		throw new Exception("删除司机行车登记时出错");
    	}
		//删除财务单据
		map.put("sourceOrderCode", map.get("driversmsId"));
		map.put("sourceOrderType", "1");
		st = baseSqlMapDAO.update("FinanceManage.deleteFinance",map);
		if (st == 0){
    		throw new Exception("删除财务单据时出错");
    	}
		return st;
	}

	/**
	 * 导出回车信息的excel
	 */
	public int findRoldInfoExcelCount(HashMap map){
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Expedite.findRoldInfoExcelCount", map)).intValue();		
	}
    
    public List findRoldInfoExcel(HashMap map){
    	return baseSqlMapDAO.queryForList("Expedite.findRoldInfoExcel", map);
    }
}
