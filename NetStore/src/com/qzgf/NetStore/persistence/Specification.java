package com.qzgf.NetStore.persistence;
// default package



/**
 * Specification entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Specification  implements java.io.Serializable {


    // Fields    

     private Integer specificationId;
     private String specificationName;


    // Constructors
     
     
     //产品取值
    // id,
	
		
   //产品取值
     
     

    /** default constructor */
    public Specification() {
    }

	/** minimal constructor */
    public Specification(Integer specificationId) {
        this.specificationId = specificationId;
    }
    
    /** full constructor */
    public Specification(Integer specificationId, String specificationName) {
        this.specificationId = specificationId;
        this.specificationName = specificationName;
    }

   
    // Property accessors

    public Integer getSpecificationId() {
        return this.specificationId;
    }
    
    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public String getSpecificationName() {
        return this.specificationName;
    }
    
    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
    
    








}