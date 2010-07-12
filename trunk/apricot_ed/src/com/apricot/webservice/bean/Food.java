package com.apricot.webservice.bean;

/**
 * Food entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Food implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rid;
	private String name;
	private String price;
	private String type;
	private String unit;
	private String state;
	private String isOrder;
	private String image;

	// Constructors

	/** default constructor */
	public Food() {
	}

	/** full constructor */
	public Food(String rid, String name, String price, String type,
			String unit, String state, String isOrder, String image) {
		this.rid = rid;
		this.name = name;
		this.price = price;
		this.type = type;
		this.unit = unit;
		this.state = state;
		this.isOrder = isOrder;
		this.image = image;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsOrder() {
		return this.isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}