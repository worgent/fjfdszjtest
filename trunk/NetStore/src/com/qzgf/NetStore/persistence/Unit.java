package com.qzgf.NetStore.persistence;
// default package



/**
 * Unit entity. @author MyEclipse Persistence Tools
 */

public class Unit  implements java.io.Serializable {


    // Fields    

     private Integer unitId;
     private String unitName;


    // Constructors

    /** default constructor */
    public Unit() {
    }

    
    /** full constructor */
    public Unit(Integer unitId, String unitName) {
        this.unitId = unitId;
        this.unitName = unitName;
    }

   
    // Property accessors

    public Integer getUnitId() {
        return this.unitId;
    }
    
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return this.unitName;
    }
    
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
   








}