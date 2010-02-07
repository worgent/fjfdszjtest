package com.qzgf.NetStore.persistence;
// default package



/**
 * Manufacturer entity. @author MyEclipse Persistence Tools
 */

public class Manufacturer  implements java.io.Serializable {


    // Fields    

     private Integer manufacturerId;
     private String manufacturerInfo;
     private String manufacturerName;
     private String producerHomePage;
     private String phone;
     private String cellPhone;
     private String contactMan;
     private String contactAddress;
     private String postCode;


    // Constructors

    /** default constructor */
    public Manufacturer() {
    }

	/** minimal constructor */
    public Manufacturer(String manufacturerName, String contactMan) {
        this.manufacturerName = manufacturerName;
        this.contactMan = contactMan;
    }
    
    /** full constructor */
    public Manufacturer(String manufacturerInfo, String manufacturerName, String producerHomePage, String phone, String cellPhone, String contactMan, String contactAddress, String postCode) {
        this.manufacturerInfo = manufacturerInfo;
        this.manufacturerName = manufacturerName;
        this.producerHomePage = producerHomePage;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.contactMan = contactMan;
        this.contactAddress = contactAddress;
        this.postCode = postCode;
    }

   
    // Property accessors

    public Integer getManufacturerId() {
        return this.manufacturerId;
    }
    
    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerInfo() {
        return this.manufacturerInfo;
    }
    
    public void setManufacturerInfo(String manufacturerInfo) {
        this.manufacturerInfo = manufacturerInfo;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }
    
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getProducerHomePage() {
        return this.producerHomePage;
    }
    
    public void setProducerHomePage(String producerHomePage) {
        this.producerHomePage = producerHomePage;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }
    
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getContactMan() {
        return this.contactMan;
    }
    
    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactAddress() {
        return this.contactAddress;
    }
    
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
   








}