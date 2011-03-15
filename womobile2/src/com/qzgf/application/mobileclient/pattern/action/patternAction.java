package com.qzgf.application.mobileclient.pattern.action;

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
import com.qzgf.application.mobileclient.pattern.domain.PatternFacade;
import com.qzgf.application.mobileclient.pattern.dto.PatternInfo;
/**
 * 用户登录
 * 
 */
@SuppressWarnings("serial")
public class patternAction extends BaseAction {
	Log log = LogFactory.getLog(patternAction.class);
	// 实现类方法
	private PatternFacade patternFacade;
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
    	String patternId="";
    	String patternType="";
    	patternId=request.getParameter("patternId");
    	patternType=request.getParameter("patternType");
    	if ("dailywork".equals(patternType)){
    	   patternType="1";
    	}
    	if ("tomonitor".equals(patternType)){
    	   patternType="2";
    	}
    	if ("declare".equals(patternType)){
    	   patternType="3";
    	}
    	HashMap map=new HashMap();
    	map.put("id", patternId);
    	map.put("patternType", patternType);
    	List<PatternInfo> lt;
      	lt=patternFacade.findPattern(map);
    	
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

	public PatternFacade getPatternFacade() {
		return patternFacade;
	}

	public void setPatternFacade(PatternFacade patternFacade) {
		this.patternFacade = patternFacade;
	}
	


}
