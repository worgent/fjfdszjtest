package com.qzgf.application.mobileclient.attendance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
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
import com.qzgf.application.mobileclient.attendance.domain.AttendanceFacade;
import com.qzgf.application.mobileclient.attendance.dto.FieldList;
/**
 * 任务管理
 * dpl
 */
@SuppressWarnings("serial")
public class attendanceAction extends BaseAction {
	Log log = LogFactory.getLog(attendanceAction.class);
	// 实现类方法
	private AttendanceFacade attendanceFacade;
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
    	String data="";//反馈表数据
    	data=request.getParameter("dataStr");
    	HashMap map=new HashMap();
    	List<FieldList> lt=json2List(data,FieldList.class);
    	 for(FieldList ac:lt){
    	        map.put("UserId", ac.getUserId());
    	        map.put("Position",ac.getLatitude()+','+ac.getLongitude());
    	        map.put("Latitude", ac.getLatitude());
    	        map.put("Longitude", ac.getLongitude());    		 
    	 }
    	if (lt.size()>0)
    	{
          int i=attendanceFacade.insertAttendance(map);
    	  jsonstr="ok";
    	}else
    	{
    		jsonstr="FAIL";
    	}
		return "json";
	}
    public static List json2List(String s,Class clazz){
        JSONArray jarr=JSONArray.fromObject(s);
        return (List)jarr.toList(jarr,clazz);
    }


	
	/**
	 * @return 
	 */
	public AttendanceFacade getattendanceFacade() {
		return attendanceFacade;
	}

	/**
	 * @param 
	 */
	public void setAttendanceFacade(AttendanceFacade attendanceFacade) {
		this.attendanceFacade = attendanceFacade;
	}

}
