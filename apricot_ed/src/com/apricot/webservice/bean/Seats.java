package com.apricot.webservice.bean;

/**
 * Seats entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Seats implements java.io.Serializable {

	// Fields

	private Integer setNo;
	private Integer id;
	private String setName;
	private String setLayer;
	private Integer rid;
	private String isPreOrder;
	private String minCastType;
	private String minCastCash;
	private String lodge;
	private String state;
	private String location;

	// Constructors

	/** default constructor */
	public Seats() {
	}

	/** full constructor */
	public Seats(Integer id, String setName, String setLayer, Integer rid,
			String isPreOrder, String minCastType, String minCastCash,
			String lodge, String state, String location) {
		this.id = id;
		this.setName = setName;
		this.setLayer = setLayer;
		this.rid = rid;
		this.isPreOrder = isPreOrder;
		this.minCastType = minCastType;
		this.minCastCash = minCastCash;
		this.lodge = lodge;
		this.state = state;
		this.location = location;
	}

	// Property accessors

	public Integer getSetNo() {
		return this.setNo;
	}

	public void setSetNo(Integer setNo) {
		this.setNo = setNo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSetName() {
		return this.setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSetLayer() {
		return this.setLayer;
	}

	public void setSetLayer(String setLayer) {
		this.setLayer = setLayer;
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getIsPreOrder() {
		return this.isPreOrder;
	}

	public void setIsPreOrder(String isPreOrder) {
		this.isPreOrder = isPreOrder;
	}

	public String getMinCastType() {
		return this.minCastType;
	}

	public void setMinCastType(String minCastType) {
		this.minCastType = minCastType;
	}

	public String getMinCastCash() {
		return this.minCastCash;
	}

	public void setMinCastCash(String minCastCash) {
		this.minCastCash = minCastCash;
	}

	public String getLodge() {
		return this.lodge;
	}

	public void setLodge(String lodge) {
		this.lodge = lodge;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}