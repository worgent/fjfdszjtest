package com.qzgf.NetStore.persistence;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class UserOrder implements Serializable{
	private int id;
	private int status;
	private double cost;
	private User user;
	@SuppressWarnings("unchecked") 
	private Set items = new HashSet();
	
	public UserOrder() {
		super();
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@SuppressWarnings("unchecked")
	public Set getItems() {
		return items;
	}
	@SuppressWarnings("unchecked")
	public void setItems(Set items) {
		this.items = items;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
