package net.trust.utils.sms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 短信传输对象
 * @author chenqf
 * 
 * 修改：2008-12-13,fjfdszj
 * 原因：短信发送方式，改为网关方式处理，手机号不需要加86，同时改为支持群发的方式
 */
public class SmsBean implements Serializable {
	private ArrayList mobileNoList;	//手机号码,群发手机数据组
	private String smsContent;			//短信内容
	private String sn;					//序列号，数据库中存储主键
	/**
	 * 获取序列号
	 * @return
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置序列号
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取短信内容
	 * @return
	 */
	public String getSmsContent() {
		return smsContent;
	}
	/**
	 * 设置发送信息内容 (应该在70个字符以内,包含标点符号,1个汉字算1个字符，超过长度部分的内容将会丢失)
	 * @param smsContent
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	/**
	 * @return the mobileNoList
	 */
	public ArrayList getMobileNoList() {
		return mobileNoList;
	}
	/**
	 * @param mobileNoList the mobileNoList to set
	 */
	public void setMobileNoList(ArrayList mobileNoList) {
		this.mobileNoList = mobileNoList;
	}
}
