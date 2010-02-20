package YzSystem.J_System;

import java.io.Serializable;

/**
 * <p>Title:登陆信息Bean </p>
 *
 * <p>Description: 根据T_Login表产生</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-07  生成代码
 */
public class BeanLogin {

    private String loginSeqn = "";     // 登陆序号
    private String loginName = "";     // 登陆名称
    private String passwd = "";        // 登陆密码
    private String employeCode="";
    private String employeName = "";   // 员工姓名 
    private String groupName = "";     // 角色名称
    private String groupPowerCode = "";     // 角色名称
    private String deptName = "";      // 部门
    private String loginTime = "";     // 登陆时间
    private String logoutTime = "";    // 登出时间

    public BeanLogin() {
    }

    public void setLoginSeqn(String loginSeqn) {
        this.loginSeqn = loginSeqn;
    }


    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLoginSeqn() {
        return loginSeqn;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

	/**
	 * @param loginName the loginName to set
	 */
    public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the loginName
	 */
    public String getLoginName() {
		return loginName;
	}

	/**
	 * @return the passwd
	 */
    public String getPasswd() {
		return passwd;
	}

	/**
	 * @return the employeName
	 */
    public String getEmployeName() {
		return employeName;
	}

	/**
	 * @return the groupName
	 */
    public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the deptName
	 */
    public String getDeptName() {
		return deptName;
	}

	/**
	 * @param groupPowerCode the groupPowerCode to set
	 */
    public void setGroupPowerCode(String groupPowerCode) {
		this.groupPowerCode = groupPowerCode;
	}

	/**
	 * @return the groupPowerCode
	 */
    public String getGroupPowerCode() {
		return groupPowerCode;
	}

	/**
	 * @param employeCode the employeCode to set
	 */
	public void setEmployeCode(String employeCode) {
		this.employeCode = employeCode;
	}

	/**
	 * @return the employeCode
	 */
	public String getEmployeCode() {
		return employeCode;
	}

}
