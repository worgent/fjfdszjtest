/**
 * 
 */
package com.apricot.webservice.impl;

import com.apricot.webservice.bean.Request;
import com.apricot.webservice.bean.Response;
import com.apricot.webservice.bean.Row;

/**
 * @author Administrator
 * 
 */
public class ServerImpl {

	/**
	 * 
	 */
	public ServerImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ÍøÉÏÔ¤¶¨×ùÎ»
	 * 
	 * @param shopId
	 * @param username
	 * @param phone
	 * @param setno
	 * @param arrivetime
	 * @return
	 */
	public Response preOrder(String shopId, String username, String phone,
			String setno, String arrivetime,String net_order_id) {
		Request req = new Request();
		req.setShopId(shopId);
		req.setAction("PRE_ORDER");
		Row row = new Row();
		req.addRow(row);
		row.setValue("username", username);
		row.setValue("phone", phone);
		row.setValue("setno", setno);
		row.setValue("arrivetime", arrivetime);
		row.setValue("net_order_id", net_order_id);

		return send(req);
	}

	private Response send(Request req) {
		try {
			com.apricot.webservice.service.ClientServiceSoapBindingStub binding;
			try {
				binding = (com.apricot.webservice.service.ClientServiceSoapBindingStub) new com.apricot.webservice.service.ClientService_ServiceLocator()
						.getClientService();
			} catch (javax.xml.rpc.ServiceException jre) {
				if (jre.getLinkedCause() != null)
					jre.getLinkedCause().printStackTrace();
				throw new junit.framework.AssertionFailedError(
						"JAX-RPC ServiceException caught: " + jre);
			}
			// assertNotNull("binding is null", binding);

			// Time out after a minute
			binding.setTimeout(60000);

			return new Response(binding.execute(req.toXML()));
		} catch (Exception e) {
			return new Response("99", e.getMessage());
		}
	}

}
