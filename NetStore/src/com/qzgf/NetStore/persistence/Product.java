package com.qzgf.NetStore.persistence;
// default package

import java.util.Date;


/**
 * Product entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Product  implements java.io.Serializable {


    // Fields    

     private String productId;
     private String productname;
     private String catalogId;
     private Date displayDate;
     private Date productDate;
     private String manufacturerId;
     private Byte ifCommend;
     private Float marketPrice;
     private Float memberPrice;
     private String brand;
     private String productIntro;
     private String smallPath;
     private String bigPath;
     private String specification;
     private Integer unitId;
     private Byte isRelease;
     private Byte isNewGoods;
     private Byte isSpecialPrice;
     private Double stock;
     private String unitName;


    // Constructors

    /** default constructor */
    public Product() {
    }

	/** minimal constructor */
    public Product(String productId, String productname, String catalogId, Date displayDate, Date productDate, String manufacturerId, Byte ifCommend, Float marketPrice, Float memberPrice, String smallPath, String bigPath, Double stock) {
        this.productId = productId;
        this.productname = productname;
        this.catalogId = catalogId;
        this.displayDate = displayDate;
        this.productDate = productDate;
        this.manufacturerId = manufacturerId;
        this.ifCommend = ifCommend;
        this.marketPrice = marketPrice;
        this.memberPrice = memberPrice;
        this.smallPath = smallPath;
        this.bigPath = bigPath;
        this.stock = stock;
    }
    
    /** full constructor */
    public Product(String productId, String productname, String catalogId, Date displayDate, Date productDate, String manufacturerId, Byte ifCommend, Float marketPrice, Float memberPrice, String brand, String productIntro, String smallPath, String bigPath, String specification, Integer unitId, Byte isRelease, Byte isNewGoods, Byte isSpecialPrice, Double stock) {
        this.productId = productId;
        this.productname = productname;
        this.catalogId = catalogId;
        this.displayDate = displayDate;
        this.productDate = productDate;
        this.manufacturerId = manufacturerId;
        this.ifCommend = ifCommend;
        this.marketPrice = marketPrice;
        this.memberPrice = memberPrice;
        this.brand = brand;
        this.productIntro = productIntro;
        this.smallPath = smallPath;
        this.bigPath = bigPath;
        this.specification = specification;
        this.unitId = unitId;
        this.isRelease = isRelease;
        this.isNewGoods = isNewGoods;
        this.isSpecialPrice = isSpecialPrice;
        this.stock = stock;
    }

   
    // Property accessors

    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductname() {
        return this.productname;
    }
    
    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCatalogId() {
        return this.catalogId;
    }
    
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public Date getDisplayDate() {
        return this.displayDate;
    }
    
    public void setDisplayDate(Date displayDate) {
        this.displayDate = displayDate;
    }

    public Date getProductDate() {
        return this.productDate;
    }
    
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getManufacturerId() {
        return this.manufacturerId;
    }
    
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Byte getIfCommend() {
        return this.ifCommend;
    }
    
    public void setIfCommend(Byte ifCommend) {
        this.ifCommend = ifCommend;
    }

    public Float getMarketPrice() {
        return this.marketPrice;
    }
    
    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getMemberPrice() {
        return this.memberPrice;
    }
    
    public void setMemberPrice(Float memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductIntro() {
        return this.productIntro;
    }
    
    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public String getSmallPath() {
        return this.smallPath;
    }
    
    public void setSmallPath(String smallPath) {
        this.smallPath = smallPath;
    }

    public String getBigPath() {
        return this.bigPath;
    }
    
    public void setBigPath(String bigPath) {
        this.bigPath = bigPath;
    }

    public String getSpecification() {
        return this.specification;
    }
    
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getUnitId() {
        return this.unitId;
    }
    
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Byte getIsRelease() {
        return this.isRelease;
    }
    
    public void setIsRelease(Byte isRelease) {
        this.isRelease = isRelease;
    }

    public Byte getIsNewGoods() {
        return this.isNewGoods;
    }
    
    public void setIsNewGoods(Byte isNewGoods) {
        this.isNewGoods = isNewGoods;
    }

    public Byte getIsSpecialPrice() {
        return this.isSpecialPrice;
    }
    
    public void setIsSpecialPrice(Byte isSpecialPrice) {
        this.isSpecialPrice = isSpecialPrice;
    }

    public Double getStock() {
        return this.stock;
    }
    
    public void setStock(Double stock) {
        this.stock = stock;
    }

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
   








}