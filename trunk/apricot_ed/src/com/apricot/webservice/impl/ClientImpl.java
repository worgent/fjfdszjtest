/**
 * 
 */
package com.apricot.webservice.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.apricot.webservice.bean.Request;
import com.apricot.webservice.bean.Response;

/**
 * @author Administrator
 * 
 */
public class ClientImpl {

	/**
	 * 
	 */
	private String ip = "127.0.0.1";//117.21.178.39
	public ClientImpl(String ip) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
	}
	
	/**
	 * 同步座位信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncSets(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_SETS");
		req.addRows(list);

		return send(req);
	}

	/**
	 * 同步折扣
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncDiscounts(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_DISCOUNT");
		req.addRows(list);

		return send(req);
	}

	/**
	 * 同步菜品信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncFoods(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_FOOD");

		req.addRows(list);

		return send(req);
	}

	/**
	 * 同步订单信息
	 * 
	 * @param shopId
	 * @param action
	 * @param list
	 * @return
	 */
	public Response syncOrders(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_ORDER");
		req.addRows(list);
		System.out.println("return:"+req.toXML());
		return send(req);
	}
	
	public Response syncOrdersAdd(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_ORDER_ADD");
		req.addRows(list);
		return send(req);
	}
	
	public Response syncOrdersTemp(String shopId, String action, List list) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("SYNCHRONIZATION_ORDER_TEMP");
		req.addRows(list);
		System.out.println(req.toXML());
		return send(req);
	}

	private Response send(Request req) {
		try {
			com.apricot.webservice.service.ServerServiceSoapBindingStub binding;
			try {
				binding = (com.apricot.webservice.service.ServerServiceSoapBindingStub) new com.apricot.webservice.service.ServerService_ServiceLocator(ip)
						.getServerService();
			} catch (javax.xml.rpc.ServiceException jre) {
				if (jre.getLinkedCause() != null)
					jre.getLinkedCause().printStackTrace();
//				throw new junit.framework.AssertionFailedError(
//						"JAX-RPC ServiceException caught: " + jre);
				jre.printStackTrace();
				return new Response("98", jre.getMessage());
			}

			// Time out after a minute
			binding.setTimeout(6000);

			// Test operation

			return new Response(binding.execute(req.toXML()));
		} catch (Exception e) {
			e.printStackTrace();
			return new Response("99", e.getMessage());
		}
	}

}
