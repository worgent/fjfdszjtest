package com.qzgf.NetStore.persistence;
// default package



/**
 * PayType entity. @author MyEclipse Persistence Tools
 */

public class PayType  implements java.io.Serializable {


    // Fields    

     private Integer payTypeId;
     private String payTypeName;
     private String companyName;
     private String openAccountName;
     private String bankAccount;
     private String remark;


    // Constructors

    /** default constructor */
    public PayType() {
    }

	/** minimal constructor */
    public PayType(String payTypeName) {
        this.payTypeName = payTypeName;
    }
    
    /** full constructor */
    public PayType(String payTypeName, String companyName, String openAccountName, String bankAccount, String remark) {
        this.payTypeName = payTypeName;
        this.companyName = companyName;
        this.openAccountName = openAccountName;
        this.bankAccount = bankAccount;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getPayTypeId() {
        return this.payTypeId;
    }
    
    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getPayTypeName() {
        return this.payTypeName;
    }
    
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOpenAccountName() {
        return this.openAccountName;
    }
    
    public void setOpenAccountName(String openAccountName) {
        this.openAccountName = openAccountName;
    }

    public String getBankAccount() {
        return this.bankAccount;
    }
    
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}