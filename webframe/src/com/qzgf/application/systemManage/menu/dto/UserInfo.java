package com.qzgf.application.systemManage.menu.dto;

import java.util.List;

public class UserInfo {
	String staffNo;
	String staffId;
	String deptId;
	String staffState;
	String password;
	String staffName;
	String isOnline;
	String registerTime;
	String phone;
	Integer cityId;
	String cityName;
	private String ieversion;//�����
	private String agent;//����ϵͳ��Ϣ
	private String powers ;
	private String ip;

	
	String deptName;//�������
	String isUse;   //�Ƿ�����
	String isUseName; //�Ƿ��������
	
	@SuppressWarnings("unchecked")
	private List roles ;	//��ɫ�б�
	
	private String temp;
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getStaffState() {
		return staffState;
	}
	public void setStaffState(String staffState) {
		this.staffState = staffState;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIeversion() {
		return ieversion;
	}
	public void setIeversion(String ieversion) {
		this.ieversion = ieversion;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getPowers() {
		return powers;
	}
	public void setPowers(String powers) {
		this.powers = powers;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@SuppressWarnings("unchecked")
	public List getRoles() {
		return roles;
	}
	@SuppressWarnings("unchecked")
	public void setRoles(List roles) {
		this.roles = roles;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the isUseName
	 */
	public String getIsUseName() {
		return isUseName;
	}
	/**
	 * @param isUseName the isUseName to set
	 */
	public void setIsUseName(String isUseName) {
		this.isUseName = isUseName;
	}
	/**
	 * @return the isUse
	 */
	public String getIsUse() {
		return isUse;
	}
	/**
	 * @param isUse the isUse to set
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
