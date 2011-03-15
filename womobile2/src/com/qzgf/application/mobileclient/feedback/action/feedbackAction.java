package com.qzgf.application.mobileclient.feedback.action;

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

import java.io.File;
import java.io.FileOutputStream;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.mobileclient.feedback.domain.FeedbackFacade;
import com.qzgf.application.mobileclient.feedback.dto.*;
/**
 * 任务管理
 * dpl
 */
@SuppressWarnings("serial")
public class feedbackAction extends BaseAction {
	Log log = LogFactory.getLog(feedbackAction.class);
	// 实现类方法
	private FeedbackFacade feedbackFacade;
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
	  	String parentId="";
    	String patternId="";
    	String data="";//反馈表数据
    	String TableName="";
    	String fieldstr="";
    	String valuestr="";
    	String insertstr="";
    	parentId=request.getParameter("missionId");
    	patternId=request.getParameter("patternId");
    	 System.out.println(patternId);
    	data=request.getParameter("dataStr");
    	TableName=feedbackFacade.selectTableName(patternId);
    	List<FieldList> lt=json2List(data,FieldList.class);
        for(FieldList ac:lt){
        	
        	if ("image".equals(ac.get_FieldType().toString()))
        	{
              //base64转为jpg
        	  if ("".equals(ac.get_FieldValue())==false)
        	  {
                 String distFileName = "F" + System.currentTimeMillis() + ".jpg";
                 String filename = ServletActionContext.getServletContext().getRealPath("/FeedbackPhoto/"+distFileName);
                 byte[] bt = new sun.misc.BASE64Decoder().decodeBuffer(ac.get_FieldValue().replace(" ", "+"));
                 File bfile=new File(filename);           
                 FileOutputStream fos = new FileOutputStream(bfile);   
                 fos.write(bt); 
                 fos.flush();
                 fos.close(); 
        	     fieldstr+=ac.get_FieldName()+",";
          	     valuestr+="'"+distFileName+"',";
        	  }
        	  else{
        		  fieldstr+=ac.get_FieldName()+",";
           	      valuestr+="'',";  
        	  }
        	}
        	else
        	{
        	  fieldstr+=ac.get_FieldName()+",";
        	  valuestr+="'"+ac.get_FieldValue()+"',";
        	}
        }
        if (fieldstr.length()>0){
          fieldstr="insert into "+TableName+"("+fieldstr.substring(0, fieldstr.length()-1)+")"; 
        }
        if (fieldstr.length()>0)
        {
          valuestr=" values("+valuestr.substring(0, valuestr.length()-1)+")";
        }
        insertstr=fieldstr+valuestr;
        System.out.println(insertstr);
        int i=feedbackFacade.insertFeedback(insertstr);
    	jsonstr="ok";
		return "json";
	}
    public static List json2List(String s,Class clazz){
        JSONArray jarr=JSONArray.fromObject(s);
        return (List)jarr.toList(jarr,clazz);
    }


	
	/**
	 * @return 
	 */
	public FeedbackFacade getfeedbackFacade() {
		return feedbackFacade;
	}

	/**
	 * @param 
	 */
	public void setFeedbackFacade(FeedbackFacade feedbackFacade) {
		this.feedbackFacade = feedbackFacade;
	}

}
