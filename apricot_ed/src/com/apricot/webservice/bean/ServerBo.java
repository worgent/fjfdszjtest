/**
 * 
 */
package com.apricot.webservice.bean;


/**
 * @author Administrator
 *
 */
public class ServerBo {

	/**
	 * 
	 */
	public ServerBo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 同步座位信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncSets(Request req) {
		System.out.println("I am client!!");
	    return null;
	}

	/**
	 * 同步折扣
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncDiscounts(Request req) {
	   return null;
	}

	/**
	 * 同步菜品信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncFoods(Request req) {
		 return null;
	}

	/**
	 * 同步订单信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncOrders(Request req) {
		  return null;
	}
	
	public Response syncOrdersAdd(Request req) {
		  return null;
	}
	
	public Response syncOrdersTemp(Request req) {
		  return null;
	}
}
