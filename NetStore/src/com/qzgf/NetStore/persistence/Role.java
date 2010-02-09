package com.qzgf.NetStore.persistence;
// default package



/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role  implements java.io.Serializable {


    // Fields    

     private Integer roleId;
     private String roleName;


    // Constructors

    /** default constructor */
    public Role() {
    }

    
    /** full constructor */
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

   
    // Property accessors

 
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
   








}