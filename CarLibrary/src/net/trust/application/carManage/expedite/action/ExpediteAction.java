package net.trust.application.carManage.expedite.action;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.carInfo.domain.CarFacade;
import net.trust.application.carManage.expedite.domain.ExpediteFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.opensymphony.xwork.ActionContext;

/**
 * 派车登记
 * 
 */
public class ExpediteAction extends PaginactionAction {

	private static final long serialVersionUID = 1L;
	// 用于调试或打印信息的Log
	//private Log log = LogFactory.getLog(ExpediteAction.class);
	private ExpediteFacade expediteFacade;

	private CarFacade carFacade;	
	

	private List expediteList;
	private List roldInfoList;
	private List gobackRoldList;
	//2009-02-26
	//因为派车申请的需要新增
	private List applyerList;
	private List flowerTaskList;
    //2009-04-14
	//因为行车记录,与短信结处理
	private List driversmsList;
	
	private HashMap search = new HashMap();
	private HashMap roldInfo = new HashMap();
	//2009-02-26
	//因为派车申请的需要新增
	private HashMap flowerTask = new HashMap();

	private String actionType;
	private String action;
	private String xml;

	/**
	 * 派车模块
	 */
	public String execute() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
        //单据类型0临时派车
		search.put("billType", "0");
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findExpediteCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "expedite";
			search = (HashMap) super.getParameter();
			search.put("billType", "0");
			expediteList = expediteFacade.findExpedite(search);
			return "search";
		} else if ("new".equals(actionType)) {
			//得到派车申请信息
			HashMap param = new HashMap();
			param.put("expediteapplyId", search.get("expediteapplyId"));
			param = (HashMap) (expediteFacade.findApplyer(param).get(0));
			
			search.put("applyerList",param);
			
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
	        //单据类型0临时派车
			search.put("billType", "0");
			//保存一个变量为下一次准备
			String expediteapplyId=search.get("expediteapplyId").toString();
			search.put("expediteapplyId", expediteapplyId);// 派车申请单
			search.put("createMan", userInfo.getStaffId());// 创建人
			
			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId);//2009-12-20修改实现方法因为数据通过tree事件获取
			
			search = expediteFacade.insertExpedite(search, roldInfo);
			int i= Integer.valueOf(search.get("st").toString());

			if (i > 0) {
				//同时更新，派车申请单据
				addActionScript("try{");
				addActionScript("	parent.document.ifrm_Applyering.window.location.reload();");
				addActionScript("}catch(e){ }");
				
				super.addActionMessage("添加派车信息,操作成功");
				addActionScript("parent.document.ifrm_ExpediteCarBooking.window.location.reload();");
				addActionScript("try{");
				addActionScript("	parent.document.ifrm_GoBackCarBooking.window.location.reload();");
				addActionScript("}catch(e){ }");

				search.put("sourceOrderType", "1");
				search.put("sourceOrderCode", search.get("expediteCarId"));

				return "ExpSuccess";
			} else {
				super.addActionMessage("添加派车信息,操作失败");
				return ERROR;
			}

		} else if ("edit".equals(actionType)) {
			//得到派车申请信息
			HashMap param = new HashMap();
			
			param.put("expediteapplyId", search.get("expediteapplyId"));
			param = (HashMap) (expediteFacade.findApplyer(param).get(0));

			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search = (HashMap) (expediteFacade.findExpedite(search).get(0));

			search.put("applyerList",param);

			action = "update";
			return "edit";

		} else if ("update".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人

			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId);//2009-12-20修改实现方法因为数据通过tree事件获取
			
			search = expediteFacade.updateExpedite(search, roldInfo);
			int i = Integer.valueOf(search.get("st").toString());
			
			if (i > 0) {
				super.addActionMessage("修改派车信息,操作成功");
				addActionScript("parent.document.ifrm_ExpediteCarBooking.window.location.reload();");
				
				search.put("sourceOrderType", "1");
				search.put("sourceOrderCode", search.get("expediteCarId"));

				return "ExpSuccess";
			} else {
				super.addActionMessage("修改派车信息,操作失败");
				return ERROR;
			}

		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.deleteExpedite(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";

		} else if ("view".equals(actionType)) {
			//得到派车申请信息
			HashMap param = new HashMap();
			param.put("expediteapplyId", search.get("expediteapplyId"));
			param = (HashMap) (expediteFacade.findApplyer(param).get(0));

			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search = (HashMap) (expediteFacade.findExpedite(search).get(0));
			
			search.put("applyerList",param);
			
			return "view";

		}
		return ERROR;
	}
	
    /**
     * 2009-06-18
     * 临时派车模块
     * @return
     * @throws Exception
     */
	public String expediteex() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
        //单据类型1临时派车
		search.put("billType", "1");
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findExpediteCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "expedite";
			search = (HashMap) super.getParameter();
			search.put("billType", "1");
			expediteList = expediteFacade.findExpedite(search);
			return "search";
		} else if ("new".equals(actionType)) {
			//得到派车申请信息
			HashMap param = new HashMap();
			param.put("expediteapplyId","");
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
            //单据类型1临时派车
			search.put("billType", "1");
			//保存一个变量为下一次准备
			search.put("expediteapplyId", "");// 派车申请单
			search.put("createMan", userInfo.getStaffId());// 创建人
			
			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId.substring(0, carNoId.indexOf("|")));
			//search.put("carNoId", carNoId);
			
			search = expediteFacade.insertExpedite(search, roldInfo);
			int i= Integer.valueOf(search.get("st").toString());
				
			if (i > 0) {
				super.addActionMessage("添加临时派车信息,操作成功");
				addActionScript("parent.document.ifrm_ExpediteCarBookingEx.window.location.reload();");
				addActionScript("try{");
				addActionScript("	parent.document.ifrm_GoBackCarBooking.window.location.reload();");
				addActionScript("}catch(e){ }");

				search.put("sourceOrderType", "1");
				search.put("sourceOrderCode", search.get("expediteCarId"));

				return "ExpSuccess";
			} else {
				super.addActionMessage("添加临时派车信息,操作失败");
				return ERROR;
			}
		} else if ("edit".equals(actionType)) {
			//得到派车申请信息
			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search = (HashMap) (expediteFacade.findExpedite(search).get(0));
			action = "update";
			return "edit";

		} else if ("update".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人

			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId.substring(0, carNoId.indexOf("|")));
			//search.put("carNoId", carNoId);
			
			search = expediteFacade.updateExpedite(search, roldInfo);
			int i = Integer.valueOf(search.get("st").toString());
			
			if (i > 0) {
				super.addActionMessage("修改临时派车信息,操作成功");
				addActionScript("parent.document.ifrm_ExpediteCarBookingEx.window.location.reload();");
				
				search.put("sourceOrderType", "1");
				search.put("sourceOrderCode", search.get("expediteCarId"));

				return "ExpSuccess";
			} else {
				super.addActionMessage("修改临时派车信息,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.deleteExpedite(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			//得到派车申请信息
			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search = (HashMap) (expediteFacade.findExpedite(search).get(0));
			return "view";
		}
		return ERROR;
	}
	
	// 回车登记
	public String goback() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findExpediteAndGobackCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "goback";
			search = (HashMap) super.getParameter();
			expediteList = expediteFacade.findExpediteAndGoback(search);
			return "search";
		} else if ("new".equals(actionType)) {
			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search.put("roadType", "2");
			gobackRoldList = expediteFacade.findRoldInfo(search);
			
			search = (HashMap) (expediteFacade.findExpedite(search).get(0));
			
			//处理结束里程
			HashMap carmarkHs=new HashMap();
			carmarkHs.put("carNoCode", search.get("car_no_code").toString());
			
			//String mileage = carFacade.findGPSCarCurrMileage(carmarkHs);
			//暂不对后台的sqlserver端数据进行采集
			String mileage="0";
			if (null == mileage || "".equals(mileage))
			{
				mileage = "0";
			}
			search.put("totalDistance", mileage);

			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.insertGoBack(search, roldInfo);
			if (i > 0) {
				super.addActionMessage("添加回车信息,操作成功");
				addActionScript("parent.document.ifrm_GoBackCarBooking.window.location.reload();");
				addActionScript("try{");
				addActionScript(" parent.document.ifrm_ExpediteCarBookingEx.window.location.reload();");
				addActionScript("}catch(e){ }");

				return SUCCESS;
			} else {
				super.addActionMessage("添加回车信息,操作失败");
				return ERROR;
			}
		} else if ("edit".equals(actionType)) {
			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);//派车的中转站信息
			search.put("roadType", "2");
			gobackRoldList = expediteFacade.findRoldInfo(search);//回车的中转站信息
			
			HashMap param = new HashMap();
			param.put("expediteCarId", search.get("expediteCarId"));

			search = (HashMap) (expediteFacade.findGoback(search).get(0));
			param = (HashMap) (expediteFacade.findExpedite(param).get(0));
			
			search.putAll(param);
			
			action = "update";
			return "edit";
		} else if ("update".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.updateGoBack(search, roldInfo);
			if (i > 0) {
				super.addActionMessage("修改回车信息,操作成功");
				addActionScript("parent.document.ifrm_GoBackCarBooking.window.location.reload();");
				addActionScript("parent.document.ifrm_ExpediteCarBookingEx.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改回车信息,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.deleteGoback(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}  else if ("feedback".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i=expediteFacade.sendFeedback(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			search.put("roadType", "1");
			roldInfoList = expediteFacade.findRoldInfo(search);
			search.put("roadType", "2");
			gobackRoldList = expediteFacade.findRoldInfo(search);
			
			HashMap param = new HashMap();
			param.put("expediteCarId", search.get("expediteCarId"));
			
			expediteList = expediteFacade.findGoback(search);
			if (null != expediteList && expediteList.size() > 0){
				search = (HashMap)expediteList.get(0);
			}
			
			param = (HashMap) (expediteFacade.findExpedite(param).get(0));
			
			search.put("expediteInfo", param);
			return "view";
		}
		return ERROR;
	}

	
	// 派车申请
	public String applyer() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		search.put("extdeptId", userInfo.getBelongTodept());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findApplyerCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "applyer";
			search = (HashMap) super.getParameter();
			applyerList = expediteFacade.findApplyer(search);
			return "search";
		} else if ("new".equals(actionType)) {
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
		    //除去提交的回车符
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(search.get("useExcuse").toString());
			search.put("useExcuse",m.replaceAll(""));
			//保存与保存提交
			search.put("createMan", userInfo.getStaffId());// 创建人
			search.put("editorMan", userInfo.getStaffId());// 创建人
			int i=expediteFacade.saveApplyer(search);
			if (i > 0) {
				super.addActionMessage("添加派车申请,操作成功");
				addActionScript("parent.document.ifrm_Applyering.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("添加派车申请,操作失败");
				return ERROR;
			}
		} else if ("edit".equals(actionType)) {
			search = (HashMap) (expediteFacade.findApplyer(search).get(0));
			action = "update";
			return "edit";
		} else if ("update".equals(actionType)) {
		    //除去提交的回车符
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(search.get("useExcuse").toString());
			search.put("useExcuse",m.replaceAll(""));
			//保存与保存提交
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i=expediteFacade.saveApplyer(search);
			
			if (i > 0) {
				super.addActionMessage("修改派车申请,操作成功");
				addActionScript("parent.document.ifrm_Applyering.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改派车申请,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.deleteApplyer(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("agent".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			search.put("approveCode", userInfo.getStaffId());
			search.put("approveRemark", "");
			int i=expediteFacade.proAgent(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			flowerTaskList = expediteFacade.findFlowerTask(search);
			search = (HashMap) (expediteFacade.findApplyer(search).get(0));
			return "view";
		}
		return ERROR;
	}
	
	//2009-04-14
	// 司机行车登记
	public String driversms() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findDriversmsCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "driversms";
			search = (HashMap) super.getParameter();
			driversmsList = expediteFacade.findDriversms(search);
			return "search";
		} else if ("new".equals(actionType)) {
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			search.put("createMan", userInfo.getStaffId());// 创建人
			int i = expediteFacade.insertDriversms(search);
			if (i > 0) {
				super.addActionMessage("添加行车记录,操作成功");
				addActionScript("parent.document.ifrm_Driversms.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("添加行车记录,操作失败");
				return ERROR;
			}
		} else if ("edit".equals(actionType)) {
			search=(HashMap)(expediteFacade.findDriversms(search).get(0));
			action = "update";
			return "edit";
		} else if ("update".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.updateDriversms(search);
			if (i > 0) {
				super.addActionMessage("修改行车记录,操作成功");
				addActionScript("parent.document.ifrm_Driversms.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改行车记录,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = expediteFacade.deleteDriversms(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}  else if ("view".equals(actionType)) {
			search=(HashMap)(expediteFacade.findDriversms(search).get(0));
			return "view";
		}
		return ERROR;
	}
	
	// 2009-04-24
	// 回车登记导出报表
	public String gobackExcel() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(expediteFacade.findRoldInfoExcelCount(search));// 获取查询结果总记录数
			}
			this.exportFlag = "gobackExcel";
			search = (HashMap) super.getParameter();
			expediteList = expediteFacade.findRoldInfoExcel(search);
			return "search";
		}
		return ERROR;
	}
	
	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public ExpediteFacade getExpediteFacade() {
		return expediteFacade;
	}

	public void setExpediteFacade(ExpediteFacade expediteFacade) {
		this.expediteFacade = expediteFacade;
	}

	public List getExpediteList() {
		return expediteList;
	}

	public void setExpediteList(List expediteList) {
		this.expediteList = expediteList;
	}

	public List getRoldInfoList() {
		return roldInfoList;
	}

	public void setRoldInfoList(List roldInfoList) {
		this.roldInfoList = roldInfoList;
	}

	public HashMap getRoldInfo() {
		return roldInfo;
	}

	public void setRoldInfo(HashMap roldInfo) {
		this.roldInfo = roldInfo;
	}

	public List getGobackRoldList() {
		return gobackRoldList;
	}

	public void setGobackRoldList(List gobackRoldList) {
		this.gobackRoldList = gobackRoldList;
	}

	/**
	 * @return the applyerList
	 */
	public List getApplyerList() {
		return applyerList;
	}

	/**
	 * @param applyerList the applyerList to set
	 */
	public void setApplyerList(List applyerList) {
		this.applyerList = applyerList;
	}

	/**
	 * @return the flowerTaskList
	 */
	public List getFlowerTaskList() {
		return flowerTaskList;
	}

	/**
	 * @param flowerTaskList the flowerTaskList to set
	 */
	public void setFlowerTaskList(List flowerTaskList) {
		this.flowerTaskList = flowerTaskList;
	}

	/**
	 * @return the flowerTask
	 */
	public HashMap getFlowerTask() {
		return flowerTask;
	}

	/**
	 * @param flowerTask the flowerTask to set
	 */
	public void setFlowerTask(HashMap flowerTask) {
		this.flowerTask = flowerTask;
	}

	/**
	 * @return the carFacade
	 */
	public CarFacade getCarFacade() {
		return carFacade;
	}

	/**
	 * @param carFacade the carFacade to set
	 */
	public void setCarFacade(CarFacade carFacade) {
		this.carFacade = carFacade;
	}

	/**
	 * @return the driversmsList
	 */
	public List getDriversmsList() {
		return driversmsList;
	}

	/**
	 * @param driversmsList the driversmsList to set
	 */
	public void setDriversmsList(List driversmsList) {
		this.driversmsList = driversmsList;
	}
}
