package com.qzgf.NetStore.persistence;
// default package



/**
 * RoleValue entity. @author MyEclipse Persistence Tools
 */

public class RoleValue  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer roleId;
     private String menuCode;
     private Byte funisShow;
     private String powerValue;
     private String remark;
    // Constructors

    /** default constructor */
    public RoleValue() {
    }

	/** minimal constructor */
    public RoleValue(Integer id, Integer roleId, String menuCode, Byte funisShow) {
        this.id = id;
        this.roleId = roleId;
        this.menuCode = menuCode;
        this.funisShow = funisShow;
    }
    
    /** full constructor */
    public RoleValue(Integer id, Integer roleId, String menuCode, Byte funisShow, String powerValue, String remark) {
        this.id = id;
        this.roleId = roleId;
        this.menuCode = menuCode;
        this.funisShow = funisShow;
        this.powerValue = powerValue;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

	public String getMenuCode() {
        return this.menuCode;
    }
    
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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

	public Byte getFunisShow() {
        return this.funisShow;
    }
    
    public void setFunisShow(Byte funisShow) {
        this.funisShow = funisShow;
    }

    public String getPowerValue() {
        return this.powerValue;
    }
    
    public void setPowerValue(String powerValue) {
        this.powerValue = powerValue;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}