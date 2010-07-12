/**
 * ClientServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

import com.apricot.webservice.bean.ClientBo;
import com.apricot.webservice.bean.Request;
import com.apricot.webservice.bean.Response;

public class ClientServiceSoapBindingImpl implements
		com.apricot.webservice.service.Client {
	public java.lang.String execute(java.lang.String in0)
			throws java.rmi.RemoteException {
		ClientBo cb = new ClientBo();
		Request req = new Request(in0);
		if ("PRE_ORDER".equalsIgnoreCase(req.getAction())) {
			return cb.preOrder(req).toXML();
		}

		return new Response("98", "ACTION±àÂëÃ»ÓÐ×¢²á£¡").toXML();
	}

}
