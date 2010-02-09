package com.qzgf.NetStore.persistence;

public class Administrator  implements java.io.Serializable {


    // Fields    

     private String adminId;
     private String password;
     private String sex;
     private String realName;
     private String email;
     private String duty;
     private String localGrade;
     private Integer roleId;
     private Byte ifUse;
     private String old_password;

    // Constructors

    public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	/** default constructor */
    public Administrator() {
    }

	/** minimal constructor */
    public Administrator(String password, String duty, String localGrade, Integer roleId, Byte ifUse) {
        this.password = password;
        this.duty = duty;
        this.localGrade = localGrade;
        this.roleId = roleId;
        this.ifUse = ifUse;
    }
    
    /** full constructor */
    public Administrator(String password, String sex, String realName, String email, String duty, String localGrade, Integer roleId, Byte ifUse) {
        this.password = password;
        this.sex = sex;
        this.realName = realName;
        this.email = email;
        this.duty = duty;
        this.localGrade = localGrade;
        this.roleId = roleId;
        this.ifUse = ifUse;
    }

   
    // Property accessors

    public String getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDuty() {
        return this.duty;
    }
    
    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getLocalGrade() {
        return this.localGrade;
    }
    
    public void setLocalGrade(String localGrade) {
        this.localGrade = localGrade;
    }


    /**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Byte getIfUse() {
        return this.ifUse;
    }
    
    public void setIfUse(Byte ifUse) {
        this.ifUse = ifUse;
    }
   








}