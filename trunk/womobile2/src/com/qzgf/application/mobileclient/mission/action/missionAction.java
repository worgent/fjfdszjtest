package com.qzgf.application.mobileclient.mission.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.mobileclient.mission.domain.MissionFacade;
/**
 * 任务管理
 * dpl
 */
@SuppressWarnings("serial")
public class missionAction extends BaseAction {
	Log log = LogFactory.getLog(missionAction.class);
	// 实现类方法
	private MissionFacade missionFacade;
    private String jsonstr;
	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			return "index";
		}
	}	
	
	public String index() throws Exception {
	  	HttpServletRequest request=ServletActionContext.getRequest(); 
    	String executorID="";
    	String missionType="";
    	executorID=request.getParameter("executorID");
    	missionType=request.getParameter("missionType");
    	List lt;
    	if ("dailywork".equals(missionType.toLowerCase())){
      	   lt=missionFacade.findDailywork(executorID);
    	}
    	else if ("declare".equals(missionType.toLowerCase())){
     	   lt=missionFacade.findDeclare(executorID);
    	}else if ("tomonitor".equals(missionType.toLowerCase()))
    	{
     		lt=missionFacade.findTomonitor(executorID);  		
    	}
    	else{
    		lt=null;
    	}
    	if (lt.isEmpty()||lt==null){
    		jsonstr="";
    	}
    	else{
    	 JSONArray jo= JSONArray.fromObject(lt);
    	 jsonstr=jo.toString();
    	}  	
    	System.out.println(jsonstr);
		return "json";
	}
	
	/**
	 * @return the frontloginFacade
	 */
	public MissionFacade getmissionFacade() {
		return missionFacade;
	}

	/**
	 * @param frontloginFacade the frontloginFacade to set
	 */
	public void setMissionFacade(MissionFacade missionFacade) {
		this.missionFacade = missionFacade;
	}

}
