package net.trust.application.baseArchives.carInfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.carInfo.domain.CarFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * 车辆信息
 * @author zhengmh
 *
 */
public class CarInfoAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(CarInfoAction.class);
	private CarFacade carFacade;	
	
	private List carInfoList;
	private List carFixingList;
	private HashMap search=new HashMap();
	
	private String actionType;		
	private String action;
	private String xml;
	private String json;
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(carFacade.findCarCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			carInfoList = carFacade.findCarInfo(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("renew".equals(actionType)){
			carFixingList = carFacade.findCarFixing(search);
			search = (HashMap)(carFacade.findCarInfo(search).get(0));			
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			int i = carFacade.insertCar(search);
			if (i>0){
				
				super.addActionMessage("添加车辆信息,操作成功");
				addActionScript("parent.document.ifrm_CarInfo.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("添加车辆信息,操作失败");				
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			carFixingList = carFacade.findCarFixing(search);
			search = (HashMap)(carFacade.findCarInfo(search).get(0));
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){	
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = carFacade.updateCarInfo(search);
			if (i>=0){				
				super.addActionMessage("修改车辆信息,操作成功");
				addActionScript("parent.document.ifrm_CarInfo.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("修改车辆信息,操作失败");				
				return ERROR;
			}
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = carFacade.deleteCarInfo(search);							
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";	
				
		}else if ("view".equals(actionType)){
			carFixingList = carFacade.findCarFixing(search);
			search = (HashMap)(carFacade.findCarInfo(search).get(0));
			return "view";

		}else if ("isExist".equals(actionType)){
			int st = carFacade.findCarCount(search);
			
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<isExist>");
			sb.append("<value>").append(""+st).append("</value>");
			sb.append("</isExist>");
			xml = sb.toString();
			return "xml";	
			
		}
		return ERROR;
	}
	
	/**
	 * 取得车辆对应的设备信息
	 * @return
	 */
	public String ajaxXML(){
		carFixingList = carFacade.findCarFixing(search);
		Iterator it = carFixingList.iterator();
		
		java.lang.StringBuffer sb = new StringBuffer();
		HashMap carFixingMap = null;
		sb.append("<carFixings>");
		while (it.hasNext()){
			carFixingMap = (HashMap)it.next();
			sb.append("<carFixing>");
			sb.append("	<fixingId>").append(carFixingMap.get("fixing_id")).append("</fixingId>");
			sb.append("	<fixingCode>").append(carFixingMap.get("fixing_code")).append("</fixingCode>");
			sb.append("	<fixingName>").append(carFixingMap.get("fixing_name")).append("</fixingName>");
			sb.append("	<memo>").append(carFixingMap.get("memo")).append("</memo>");
			sb.append("	<checked>").append(carFixingMap.get("checked")).append("</checked>");
			sb.append("</carFixing>");
		}
		sb.append("</carFixings>");
		xml = sb.toString();
		return "xml";	
	}
	
	public String ajaxGetGPSCarMileage(){
		String mileage = carFacade.findGPSCarCurrMileage(search);
		if (null == mileage || "".equals(mileage))
		{
			mileage = "0";
		}
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<GPSCarMileage>");
		sb.append("	<value>").append(mileage).append("</value>");
		sb.append("	<carmark>").append(search.get("carNoCode").toString()).append("</carmark>");
		sb.append("</GPSCarMileage>");
		
		xml = sb.toString();
		return "xml";
	}
	
	/**
	 * 20081128增加油箱总容量
	 * @return
	 */
	public String ajaxPutOilTotal(){
		String oilTotal = carFacade.findOilTotal(search);
		if (null == oilTotal || "".equals(oilTotal))
			oilTotal = "0";
		
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<CarOilTatol>");
		sb.append("	<value>").append(oilTotal).append("</value>");
		sb.append("</CarOilTatol>");
		
		xml = sb.toString();
		return "xml";
	}
	/**
	 * 2009-12-19:车辆信息,通过部门过滤
	 * @return
	 */
	public String ajaxJsonCar(){
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());

		search.put("belongDept", search.get("belongDept").toString());
		carInfoList = carFacade.ajaxJsonCar(search);
		Iterator it = carInfoList.iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("text")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("id")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
	
	public CarFacade getCarFacade() {
		return carFacade;
	}
	public void setCarFacade(CarFacade carFacade) {
		this.carFacade = carFacade;
	}
	public List getCarInfoList() {
		return carInfoList;
	}
	public void setCarInfoList(List carInfoList) {
		this.carInfoList = carInfoList;
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
	public List getCarFixingList() {
		return carFixingList;
	}
	public void setCarFixingList(List carFixingList) {
		this.carFixingList = carFixingList;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
	
}
