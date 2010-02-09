package com.qzgf.NetStore.persistence;
// default package



/**
 * ProductCatalog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ProductCatalog  implements java.io.Serializable {


    // Fields    

     private String id;
     private String catalogName;
     private String parentId;
     private String remark;


    // Constructors

    /** default constructor */
    public ProductCatalog() {
    }

	/** minimal constructor */
    public ProductCatalog(String id, String catalogName, String parentId) {
        this.id = id;
        this.catalogName = catalogName;
        this.parentId = parentId;
    }
    
    /** full constructor */
    public ProductCatalog(String id, String catalogName, String parentId, String remark) {
        this.id = id;
        this.catalogName = catalogName;
        this.parentId = parentId;
        this.remark = remark;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCatalogName() {
        return this.catalogName;
    }
    
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getParentId() {
        return this.parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}