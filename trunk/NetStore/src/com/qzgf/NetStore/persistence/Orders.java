package com.qzgf.NetStore.persistence;
// default package

import java.util.Date;


/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders  implements java.io.Serializable {


    // Fields    

     private String orderId;
     private String userId;
     private Float allSum;
     private Integer sendModeId;
     private Integer packModeId;
     private Integer noneDealModeId;
     private Integer orderStatus;
     private Date downOrderTime;
     private String receiveUserName;
     private String receiveUserAddress;
     private String postCode;
     private String email;
     private String phone;
     private String cellPhone;
     private Byte ifInvoice;
     private String invoiceUnit;
     private String remark;
     private Short payStatus;
     private Short payTypeId;
     private Float productSum;


    // Constructors

    /** default constructor */
    public Orders() {
    }

	/** minimal constructor */
    public Orders(String orderId, String userId, Float allSum, Integer sendModeId, Integer packModeId, Integer noneDealModeId, Integer orderStatus, Date downOrderTime, String receiveUserName, String receiveUserAddress, String postCode, Byte ifInvoice, Short payStatus, Float productSum) {
        this.orderId = orderId;
        this.userId = userId;
        this.allSum = allSum;
        this.sendModeId = sendModeId;
        this.packModeId = packModeId;
        this.noneDealModeId = noneDealModeId;
        this.orderStatus = orderStatus;
        this.downOrderTime = downOrderTime;
        this.receiveUserName = receiveUserName;
        this.receiveUserAddress = receiveUserAddress;
        this.postCode = postCode;
        this.ifInvoice = ifInvoice;
        this.payStatus = payStatus;
        this.productSum = productSum;
    }
    
    /** full constructor */
    public Orders(String orderId, String userId, Float allSum, Integer sendModeId, Integer packModeId, Integer noneDealModeId, Integer orderStatus, Date downOrderTime, String receiveUserName, String receiveUserAddress, String postCode, String email, String phone, String cellPhone, Byte ifInvoice, String invoiceUnit, String remark, Short payStatus, Short payTypeId, Float productSum) {
        this.orderId = orderId;
        this.userId = userId;
        this.allSum = allSum;
        this.sendModeId = sendModeId;
        this.packModeId = packModeId;
        this.noneDealModeId = noneDealModeId;
        this.orderStatus = orderStatus;
        this.downOrderTime = downOrderTime;
        this.receiveUserName = receiveUserName;
        this.receiveUserAddress = receiveUserAddress;
        this.postCode = postCode;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.ifInvoice = ifInvoice;
        this.invoiceUnit = invoiceUnit;
        this.remark = remark;
        this.payStatus = payStatus;
        this.payTypeId = payTypeId;
        this.productSum = productSum;
    }

   
    // Property accessors

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getAllSum() {
        return this.allSum;
    }
    
    public void setAllSum(Float allSum) {
        this.allSum = allSum;
    }

    public Integer getSendModeId() {
        return this.sendModeId;
    }
    
    public void setSendModeId(Integer sendModeId) {
        this.sendModeId = sendModeId;
    }

    public Integer getPackModeId() {
        return this.packModeId;
    }
    
    public void setPackModeId(Integer packModeId) {
        this.packModeId = packModeId;
    }

    public Integer getNoneDealModeId() {
        return this.noneDealModeId;
    }
    
    public void setNoneDealModeId(Integer noneDealModeId) {
        this.noneDealModeId = noneDealModeId;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDownOrderTime() {
        return this.downOrderTime;
    }
    
    public void setDownOrderTime(Date downOrderTime) {
        this.downOrderTime = downOrderTime;
    }

    public String getReceiveUserName() {
        return this.receiveUserName;
    }
    
    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveUserAddress() {
        return this.receiveUserAddress;
    }
    
    public void setReceiveUserAddress(String receiveUserAddress) {
        this.receiveUserAddress = receiveUserAddress;
    }

    public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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

    public Byte getIfInvoice() {
        return this.ifInvoice;
    }
    
    public void setIfInvoice(Byte ifInvoice) {
        this.ifInvoice = ifInvoice;
    }

    public String getInvoiceUnit() {
        return this.invoiceUnit;
    }
    
    public void setInvoiceUnit(String invoiceUnit) {
        this.invoiceUnit = invoiceUnit;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getPayStatus() {
        return this.payStatus;
    }
    
    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public Short getPayTypeId() {
        return this.payTypeId;
    }
    
    public void setPayTypeId(Short payTypeId) {
        this.payTypeId = payTypeId;
    }

    public Float getProductSum() {
        return this.productSum;
    }
    
    public void setProductSum(Float productSum) {
        this.productSum = productSum;
    }
   








}