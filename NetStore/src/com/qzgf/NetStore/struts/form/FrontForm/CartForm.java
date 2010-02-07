package com.qzgf.NetStore.struts.form.FrontForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("serial")
public class CartForm extends ActionForm {
    private String receiveUserName;
    private String receivePostCode;
    private String receivePhone;
    private String receiveCellPhone;
    private String receiveAddress;
    private String ifSame;
    private String sendUser;
    private String sendPostCode;
    private String sendPhone;
    private String sendCellPhone;
    private String sendAddress;
    private String sendModeId;
    private String payTypeId;
    private String cost;
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}
	public String getSendModeId() {
		return sendModeId;
	}
	public void setSendModeId(String sendModeId) {
		this.sendModeId = sendModeId;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}
	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}
	public String getReceivePostCode() {
		return receivePostCode;
	}
	public void setReceivePostCode(String receivePostCode) {
		this.receivePostCode = receivePostCode;
	}
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	public String getReceiveCellPhone() {
		return receiveCellPhone;
	}
	public void setReceiveCellPhone(String receiveCellPhone) {
		this.receiveCellPhone = receiveCellPhone;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	

	public String getIfSame() {
		return ifSame;
	}
	public void setIfSame(String ifSame) {
		this.ifSame = ifSame;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getSendPostCode() {
		return sendPostCode;
	}
	public void setSendPostCode(String sendPostCode) {
		this.sendPostCode = sendPostCode;
	}
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	public String getSendCellPhone() {
		return sendCellPhone;
	}
	public void setSendCellPhone(String sendCellPhone) {
		this.sendCellPhone = sendCellPhone;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
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
