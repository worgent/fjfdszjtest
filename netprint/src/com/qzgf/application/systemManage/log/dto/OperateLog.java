package com.qzgf.application.systemManage.log.dto;

public class OperateLog {
	private String optSeq;		//操作日志序
	private String staffId;		//人员ID
	private String serviceName;	//业务操作名称		如：兑换可用积分
	private String optTime;		//操作时间
	private String optResult;	//操作结果	操作完返回的成功信息或失败信息
	private String optMemo;		//说明
	private String termType = "";	//终端类型	如IE，前置机
	private String optTerm;		//登录终端号	IP ,POS机号
	private String begDate;		//用于查询时使用
	private String endDate;
	private String userno;		//人员工号
	private Integer cityId;
	private Integer areaId;//区域
	//2009-9-18
	private String deptId;//引入部门号与档案关连,实现权限的灵活控制
	
	
	public String getBegDate() {
		return begDate;
	}



	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}



	public Integer getCityId() {
		return cityId;
	}



	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getOptMemo() {
		return optMemo;
	}



	public void setOptMemo(String optMemo) {
		this.optMemo = optMemo;
	}



	public String getOptResult() {
		return optResult;
	}



	public void setOptResult(String optResult) {
		this.optResult = optResult;
	}



	public String getOptSeq() {
		return optSeq;
	}



	public void setOptSeq(String optSeq) {
		this.optSeq = optSeq;
	}



	public String getOptTerm() {
		return optTerm;
	}



	public void setOptTerm(String optTerm) {
		this.optTerm = optTerm;
	}



	public String getOptTime() {
		return optTime;
	}



	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}



	public String getServiceName() {
		return serviceName;
	}



	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



	public String getStaffId() {
		return staffId;
	}



	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}



	public String getTermType() {
		return termType;
	}



	public void setTermType(String termType) {
		this.termType = termType;
	}



	public String getUserno() {
		return userno;
	}



	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Integer getAreaId() {
		return areaId;
	}



	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}



	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}



	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
