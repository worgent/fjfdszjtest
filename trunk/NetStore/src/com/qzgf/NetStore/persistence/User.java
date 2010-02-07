package com.qzgf.NetStore.persistence;
// default package

import java.util.Date;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String userId;
     private String userPwd;
     private String sex;
     private String realName;
     private String email;
     private String phone;
     private String cellPhone;
     private String address;
     private Date registerTime;
     private String ifUsable;
     private String postCode;
     private String idCard;
     private String age;
     private String introduce;
     private Integer sendModeId;
     private String receiveUserName;
     private String receiveUserAddress;
     private String receivePostCode;
     private String receivePhone;
     private String receiveCellPhone;
     private Short payTypeId;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String userId, String userPwd, String ifUsable) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.ifUsable = ifUsable;
    }
    
    /** full constructor */
    public User(String userId, String userPwd, String sex, String realName, String email, String phone, String cellPhone, String address, Date registerTime, String ifUsable, String postCode, String idCard, String age, String introduce, Integer sendModeId, String receiveUserName, String receiveUserAddress, String receivePostCode, String receivePhone, String receiveCellPhone, Short payTypeId) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.sex = sex;
        this.realName = realName;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.address = address;
        this.registerTime = registerTime;
        this.ifUsable = ifUsable;
        this.postCode = postCode;
        this.idCard = idCard;
        this.age = age;
        this.introduce = introduce;
        this.sendModeId = sendModeId;
        this.receiveUserName = receiveUserName;
        this.receiveUserAddress = receiveUserAddress;
        this.receivePostCode = receivePostCode;
        this.receivePhone = receivePhone;
        this.receiveCellPhone = receiveCellPhone;
        this.payTypeId = payTypeId;
    }

   
    // Property accessors

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return this.userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
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

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }
    
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

   

    public String getIfUsable() {
		return ifUsable;
	}

	public void setIfUsable(String ifUsable) {
		this.ifUsable = ifUsable;
	}

	public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getIdCard() {
        return this.idCard;
    }
    
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAge() {
        return this.age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }

    public String getIntroduce() {
        return this.introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getSendModeId() {
        return this.sendModeId;
    }
    
    public void setSendModeId(Integer sendModeId) {
        this.sendModeId = sendModeId;
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

    public String getReceivePostCode() {
        return this.receivePostCode;
    }
    
    public void setReceivePostCode(String receivePostCode) {
        this.receivePostCode = receivePostCode;
    }

    public String getReceivePhone() {
        return this.receivePhone;
    }
    
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveCellPhone() {
        return this.receiveCellPhone;
    }
    
    public void setReceiveCellPhone(String receiveCellPhone) {
        this.receiveCellPhone = receiveCellPhone;
    }

    public Short getPayTypeId() {
        return this.payTypeId;
    }
    
    public void setPayTypeId(Short payTypeId) {
        this.payTypeId = payTypeId;
    }
   








}