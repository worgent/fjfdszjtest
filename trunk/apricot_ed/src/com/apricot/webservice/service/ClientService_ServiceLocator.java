/**
 * ClientService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

public class ClientService_ServiceLocator extends org.apache.axis.client.Service implements com.apricot.webservice.service.ClientService_Service {

    public ClientService_ServiceLocator() {
    }


    public ClientService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ClientService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ClientService
    private java.lang.String ClientService_address = "http://localhost:8888/apricot_ed/services/ClientService";

    public java.lang.String getClientServiceAddress() {
        return ClientService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ClientServiceWSDDServiceName = "ClientService";

    public java.lang.String getClientServiceWSDDServiceName() {
        return ClientServiceWSDDServiceName;
    }

    public void setClientServiceWSDDServiceName(java.lang.String name) {
        ClientServiceWSDDServiceName = name;
    }

    public com.apricot.webservice.service.Client getClientService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ClientService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getClientService(endpoint);
    }

    public com.apricot.webservice.service.Client getClientService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.apricot.webservice.service.ClientServiceSoapBindingStub _stub = new com.apricot.webservice.service.ClientServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getClientServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setClientServiceEndpointAddress(java.lang.String address) {
        ClientService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.apricot.webservice.service.Client.class.isAssignableFrom(serviceEndpointInterface)) {
                com.apricot.webservice.service.ClientServiceSoapBindingStub _stub = new com.apricot.webservice.service.ClientServiceSoapBindingStub(new java.net.URL(ClientService_address), this);
                _stub.setPortName(getClientServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ClientService".equals(inputPortName)) {
            return getClientService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("ClientService", "ClientService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ClientService", "ClientService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ClientService".equals(portName)) {
            setClientServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
