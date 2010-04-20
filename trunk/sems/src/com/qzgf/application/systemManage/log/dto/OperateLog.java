package com.qzgf.application.systemManage.log.dto;
 
public class OperateLog {
	private String optSeq; // 操作日志序
	private String staffId; // 人员ID
	private String interfaceName; // 业务操作接口
	private String implName;//业务实现类
	private String optTime; // 操作时间
	private String optResult; // 操作结果 操作完返回的成功信息或失败信息
	private String optIp; // 登录终端号 IP ,POS机号
	private String optMethod;
	private String optArgs;

	public String getOptSeq() {
		return optSeq;
	}

	public void setOptSeq(String optSeq) {
		this.optSeq = optSeq;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getOptResult() {
		return optResult;
	}

	public void setOptResult(String optResult) {
		this.optResult = optResult;
	}

	public String getOptIp() {
		return optIp;
	}

	public void setOptIp(String optIp) {
		this.optIp = optIp;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getImplName() {
		return implName;
	}

	public void setImplName(String implName) {
		this.implName = implName;
	}

	public String getOptMethod() {
		return optMethod;
	}

	public void setOptMethod(String optMethod) {
		this.optMethod = optMethod;
	}

	public String getOptArgs() {
		return optArgs;
	}

	public void setOptArgs(String optArgs) {
		this.optArgs = optArgs;
	}

	
	
}
