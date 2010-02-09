package net.trust.application.systemManage.sms.action;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.trust.PaginactionAction;
import net.trust.application.systemManage.manager.dto.UserInfo;
import net.trust.application.systemManage.sms.domain.SmsManageFacade;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

public class SmsManageAction extends PaginactionAction{
	private Log log = LogFactory.getLog(SmsManageAction.class);
	private SmsManageFacade smsManageFacade;
	
	private HashMap search = new HashMap();
	private List smsList;
	
	private String actionType;		
	private String action;
	private String xml;
	/**
	 * 处理接收到的短信
	 * @return
	 * @throws Exception
	 */
	public String inceptSms() throws Exception {
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(smsManageFacade.findInceptSmsCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "InceptSmsExport";
			search = (HashMap)super.getParameter();
			smsList = smsManageFacade.findInceptSms(search);
			return "search";
		}else if ("edit".equals(actionType)){
			search = (HashMap)smsManageFacade.findInceptSms(search).get(0);
			action = "update";
			return "edit";
		}else if ("update".equals(actionType)){
			UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");	
			search.put("staffId", userInfo.getStaffId());//编辑人
			search.put("smsState", "2");
			int i = smsManageFacade.updateInceptSms(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</result>");
			xml = sb.toString();
			return "xml";
		}else if ("delete".equals(actionType)){		
			UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");	
			search.put("staffId", userInfo.getStaffId());//编辑人
			search.put("smsState", "0");
			int i = smsManageFacade.updateInceptSms(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</result>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}
	
	/**
	 * 短信发送
	 * @return
	 * @throws Exception
	 */
	public String sendSms() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(smsManageFacade.findSendSmsRecordCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "SendSmsRecordExport";
			search = (HashMap)super.getParameter();
			smsList = smsManageFacade.findSendSmsRecord(search);
			return "search";
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";
		}else if ("insert".equals(actionType)){
			int i = smsManageFacade.insertSendSmsRecord(search);
			if (i == 0){				
				super.addActionMessage("短信发送成功");
				addActionScript("parent.document.ifrm_SendSmsManage.window.location.reload();");					
				return SUCCESS;
			}else if (i == -200){				
				super.addActionMessage("要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送");
				addActionScript("parent.document.ifrm_SendSmsManage.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改派车信息,操作失败");
				return ERROR;
			}
			
		}else if ("resend".equals(actionType)){
			int i = smsManageFacade.updateResendSms(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</result>");
			xml = sb.toString();
			return "xml";
			
		}else if ("cancel".equals(actionType)){
			int i = smsManageFacade.updateSendSmsCancel(search);
			
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</result>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}
	
	
	/**
	 * 网关短信发送(2008-12-22)
	 * @return
	 * @throws Exception
	 */
	public String smproxysend() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		search.put("cityId", userInfo.getCityId());
		 if ("".equals(actionType)|| null == actionType){
			action = "insert";
			return "search";
		}
		//群发短信
		else if ("insert".equals(actionType)){
		    //除去提交的回车符
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(search.get("smsContext").toString());
			search.put("smsContext",m.replaceAll(""));
			
			int i = smsManageFacade.smporxySend(search);
			if (i == 1){				
				super.addActionMessage("网关短信发送成功！");		
				return SUCCESS;
				
			}else {
				super.addActionMessage("网关短信发送失败！");
				return ERROR;
			}
		}
		return ERROR;
	}
	
	/**
	 * 短信设置(2009-01-17)
	 * @return
	 * @throws Exception
	 */
	public String smsconfig() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("editer", userInfo.getStaffId());
		 if ("".equals(actionType)|| null == actionType){
			search = (HashMap)smsManageFacade.findSystemConfig(search).get(0);
			action = "update";
			return "search";
		}
		 //短信参数设置
		else if ("update".equals(actionType)){
			int i = smsManageFacade.updateSystemConfig(search);
			if (i == 1){				
				super.addActionMessage("系统短信设置成功！");		
				return SUCCESS;
			}else {
				super.addActionMessage("系统短信设置失败！");
				return ERROR;
			}
		}
		return ERROR;
	}
	
	
	public String execute() throws Exception {
		return ERROR;
	}
	
	public SmsManageFacade getSmsManageFacade() {
		return smsManageFacade;
	}

	public void setSmsManageFacade(SmsManageFacade smsManageFacade) {
		this.smsManageFacade = smsManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getSmsList() {
		return smsList;
	}

	public void setSmsList(List smsList) {
		this.smsList = smsList;
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
	
	
}
