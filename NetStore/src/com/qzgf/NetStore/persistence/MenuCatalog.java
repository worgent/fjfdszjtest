package com.qzgf.NetStore.persistence;


public class MenuCatalog {


    // Fields    

     private String catalogId1;
     private String catalogName;
     private String catalogPic;
     private String catalogIntroduce;
     private String catalogGrade;
     private String priorCatalogId;
     private Integer n_sx;

     
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogPic() {
		return catalogPic;
	}
	public void setCatalogPic(String catalogPic) {
		this.catalogPic = catalogPic;
	}
	public String getCatalogIntroduce() {
		return catalogIntroduce;
	}
	public void setCatalogIntroduce(String catalogIntroduce) {
		this.catalogIntroduce = catalogIntroduce;
	}
	public String getCatalogGrade() {
		return catalogGrade;
	}
	public void setCatalogGrade(String catalogGrade) {
		this.catalogGrade = catalogGrade;
	}
	public String getPriorCatalogId() {
		return priorCatalogId;
	}
	public void setPriorCatalogId(String priorCatalogId) {
		this.priorCatalogId = priorCatalogId;
	}
	public Integer getN_sx() {
		
		return n_sx;
	}
	public void setN_sx(Integer n_sx) {
		this.n_sx = n_sx;
	}
	public String getCatalogId1() {
		return catalogId1;
	}
	public void setCatalogId1(String catalogId1) {
		this.catalogId1 = catalogId1;
	}
     
	

}