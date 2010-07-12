package com.apricot.webservice.bean;

/**
 * Orders entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderNo;
	private String orderId;
	private String cancelTime;
	private String orderTime;
	private String handleTime;
	private String arrivingTime;
	private String manCount;
	private String rid;
	private String type;
	private String state;
	private String seatNo;
	private int sqlType;
	private String total;

	// Constructors

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(String orderNo, String orderId, String cancelTime,
			String orderTime, String handleTime, String arrivingTime,
			String rid, String type, String state, String seatNo) {
		this.orderNo = orderNo;
		this.orderId = orderId;
		this.cancelTime = cancelTime;
		this.orderTime = orderTime;
		this.handleTime = handleTime;
		this.arrivingTime = arrivingTime;
		this.rid = rid;
		this.type = type;
		this.state = state;
		this.seatNo = seatNo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getArrivingTime() {
		return this.arrivingTime;
	}

	public void setArrivingTime(String arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSeatNo() {
		return this.seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getManCount() {
		return manCount;
	}

	public void setManCount(String manCount) {
		this.manCount = manCount;
	}

}