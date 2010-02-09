package com.qzgf.NetStore.persistence;
// default package



/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu  implements java.io.Serializable {


    // Fields    

     private String theCode;
     private String theName;
     private String theParentCode;
     private String theUrl;
     private String theRemark;
     private Integer theOrderId;


    // Constructors

    /** default constructor */
    public Menu() {
    }

	/** minimal constructor */
    public Menu(String theCode) {
        this.theCode = theCode;
    }
    
    /** full constructor */
    public Menu(String theCode, String theName, String theParentCode, String theUrl, String theRemark, Integer theOrderId) {
        this.theCode = theCode;
        this.theName = theName;
        this.theParentCode = theParentCode;
        this.theUrl = theUrl;
        this.theRemark = theRemark;
        this.theOrderId = theOrderId;
    }

   
    // Property accessors

    public String getTheCode() {
        return this.theCode;
    }
    
    public void setTheCode(String theCode) {
        this.theCode = theCode;
    }

    public String getTheName() {
        return this.theName;
    }
    
    public void setTheName(String theName) {
        this.theName = theName;
    }

    public String getTheParentCode() {
        return this.theParentCode;
    }
    
    public void setTheParentCode(String theParentCode) {
        this.theParentCode = theParentCode;
    }

    public String getTheUrl() {
        return this.theUrl;
    }
    
    public void setTheUrl(String theUrl) {
        this.theUrl = theUrl;
    }

    public String getTheRemark() {
        return this.theRemark;
    }
    
    public void setTheRemark(String theRemark) {
        this.theRemark = theRemark;
    }

    public Integer getTheOrderId() {
        return this.theOrderId;
    }
    
    public void setTheOrderId(Integer theOrderId) {
        this.theOrderId = theOrderId;
    }
   








}