package com.qzgf.NetStore.persistence;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	private double cost;
	@SuppressWarnings("unchecked")
	private Map items = new HashMap();
	public Cart() {
		super();
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@SuppressWarnings("unchecked")
	public Map getItems() {
		return items;
	}
	@SuppressWarnings("unchecked")
	public void setItems(Map items) {
		this.items = items;
	}
	@SuppressWarnings("unchecked")
	public void addItem(Product product,int number){
		Item item0 = findItemByProductId(product.getProductId());
		if(item0!=null){
			number = number + this.getItemNumber(item0);
			modifyNumberByProductId(product.getProductId(),number);
		}else{
			Item item = new Item(product,number);
			items.put(product.getProductId(),item);
		}
		
	}
	public void modifyNumberByProductId(String productId,int number){
		Item item = findItemByProductId(productId);
		if(item!=null){
			item.setNumber(number);
		}else
		((Item)items.get(productId)).setNumber(number);
	}
	public void deleteItemByProductId(String productId){
		items.remove(productId);
	}
	//public void deleteItemsByProductId(Integer[] productId){}
	@SuppressWarnings("unchecked")
	public Item findItemByProductId(String  productId){
		Map map = getItems();
		if(map.containsKey(productId)){
			return (Item)map.get(productId);
		}else 
		return null;
	}
	
	
	public void clear(){
		items.clear();
	}
	@SuppressWarnings("unchecked")
	public HashMap getCartItem(){
		return null;
	}
	public int getItemNumber(Item item){
		return item.getNumber();
	}
	public boolean isEmpty(){
		return items.isEmpty();
	}
}
