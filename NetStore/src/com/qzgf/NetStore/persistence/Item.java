package com.qzgf.NetStore.persistence;

import java.io.Serializable;

public class Item implements Serializable {
	private Integer id;
	private Product product;
	private int number;
	private double cost;
	private UserOrder order;

	public Item(Product product, int number) {
		this.product= product;
		this.number = number;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public UserOrder getOrder() {
		return order;
	}
	public void setOrder(UserOrder order) { 
		this.order = order;
	}
	
}
