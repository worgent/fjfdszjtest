/**
 * ClientService_ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

public class ClientService_ServiceTestCase extends junit.framework.TestCase {
    public ClientService_ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testClientServiceWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.apricot.webservice.service.ClientService_ServiceLocator().getClientServiceAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.apricot.webservice.service.ClientService_ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1ClientServiceExecute() throws Exception {
        com.apricot.webservice.service.ClientServiceSoapBindingStub binding;
        try {
            binding = (com.apricot.webservice.service.ClientServiceSoapBindingStub)
                          new com.apricot.webservice.service.ClientService_ServiceLocator().getClientService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.execute(new java.lang.String());
        // TBD - validate results
    }

}
