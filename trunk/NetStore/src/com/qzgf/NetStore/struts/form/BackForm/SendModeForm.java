package com.qzgf.NetStore.struts.form.BackForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



@SuppressWarnings("serial")
public class SendModeForm extends ActionForm {
	
	private String sendModeId;
	private String sendModeName;
	private String sendFee;
	private String sendTime;
	
	public String getSendModeId() {
		return sendModeId;
	}

	public void setSendModeId(String sendModeId) {
		this.sendModeId = sendModeId;
	}

	public String getSendModeName() {
		return sendModeName;
	}

	public void setSendModeName(String sendModeName) {
		this.sendModeName = sendModeName;
	}

	public String getSendFee() {
		return sendFee;
	}

	public void setSendFee(String sendFee) {
		this.sendFee = sendFee;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}
 
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

}
