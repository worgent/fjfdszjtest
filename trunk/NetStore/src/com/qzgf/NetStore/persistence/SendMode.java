package com.qzgf.NetStore.persistence;
// default package



/**
 * SendMode entity. @author MyEclipse Persistence Tools
 */

public class SendMode  implements java.io.Serializable {


    // Fields    

     private Integer sendModeId;
     private String sendModeName;
     private Float sendFee;
     private String sendTime;


    // Constructors

    /** default constructor */
    public SendMode() {
    }

	/** minimal constructor */
    public SendMode(Integer sendModeId, String sendModeName, Float sendFee) {
        this.sendModeId = sendModeId;
        this.sendModeName = sendModeName;
        this.sendFee = sendFee;
    }
    
    /** full constructor */
    public SendMode(Integer sendModeId, String sendModeName, Float sendFee, String sendTime) {
        this.sendModeId = sendModeId;
        this.sendModeName = sendModeName;
        this.sendFee = sendFee;
        this.sendTime = sendTime;
    }

   
    // Property accessors

    public Integer getSendModeId() {
        return this.sendModeId;
    }
    
    public void setSendModeId(Integer sendModeId) {
        this.sendModeId = sendModeId;
    }

    public String getSendModeName() {
        return this.sendModeName;
    }
    
    public void setSendModeName(String sendModeName) {
        this.sendModeName = sendModeName;
    }

    public Float getSendFee() {
        return this.sendFee;
    }
    
    public void setSendFee(Float sendFee) {
        this.sendFee = sendFee;
    }

    public String getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
   








}