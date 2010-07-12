/**
 * ServerServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

import com.apricot.webservice.bean.Request;
import com.apricot.webservice.bean.Response;
import com.apricot.webservice.bean.ServerBo;

public class ServerServiceSoapBindingImpl implements
		com.apricot.webservice.service.Server {
	public java.lang.String execute(java.lang.String in0)
			throws java.rmi.RemoteException {
		ServerBo cb = new ServerBo();
		Request req = new Request(in0);

		if ("SYNCHRONIZATION_SETS".equalsIgnoreCase(req.getAction())) {
			return cb.syncSets(req).toXML();
		}

		if ("SYNCHRONIZATION_DISCOUNT".equalsIgnoreCase(req.getAction())) {
			return cb.syncDiscounts(req).toXML();
		}

		if ("SYNCHRONIZATION_FOOD".equalsIgnoreCase(req.getAction())) {
			return cb.syncFoods(req).toXML();
		}

		if ("SYNCHRONIZATION_ORDER".equalsIgnoreCase(req.getAction())) {
			return cb.syncOrders(req).toXML();
		}
		
		if ("SYNCHRONIZATION_ORDER_ADD".equalsIgnoreCase(req.getAction())) {
			return cb.syncOrdersAdd(req).toXML();
		}
		
		if ("SYNCHRONIZATION_ORDER_TEMP".equalsIgnoreCase(req.getAction())) {
			return cb.syncOrdersTemp(req).toXML();
		}

		return new Response("98", "ACTION±àÂëÃ»ÓÐ×¢²á£¡").toXML();
	}

}
