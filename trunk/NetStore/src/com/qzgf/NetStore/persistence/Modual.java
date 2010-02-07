package com.qzgf.NetStore.persistence;
// default package



/**
 * Modual entity. @author MyEclipse Persistence Tools
 */

public class Modual  implements java.io.Serializable {


    // Fields    

     private String modualId;
     private String modualName;
     private String priorId;


    // Constructors

    /** default constructor */
    public Modual() {
    }

	/** minimal constructor */
    public Modual(String modualId) {
        this.modualId = modualId;
    }
    
    /** full constructor */
    public Modual(String modualId, String modualName, String priorId) {
        this.modualId = modualId;
        this.modualName = modualName;
        this.priorId = priorId;
    }

   
    // Property accessors

    public String getModualId() {
        return this.modualId;
    }
    
    public void setModualId(String modualId) {
        this.modualId = modualId;
    }

    public String getModualName() {
        return this.modualName;
    }
    
    public void setModualName(String modualName) {
        this.modualName = modualName;
    }

    public String getPriorId() {
        return this.priorId;
    }
    
    public void setPriorId(String priorId) {
        this.priorId = priorId;
    }
   








}