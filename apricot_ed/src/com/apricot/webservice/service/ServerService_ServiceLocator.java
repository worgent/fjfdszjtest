/**
 * ServerService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

public class ServerService_ServiceLocator extends org.apache.axis.client.Service implements com.apricot.webservice.service.ServerService_Service {

	private String ip="127.0.0.1:80";//117.21.178.39:80
	
    public ServerService_ServiceLocator(String ip) {
    	this.ip=ip;
    	this.ServerService_address="http://"+ip+"/services/ServerService";
    	System.out.println(ServerService_address);
    }
	
//	public ServerService_ServiceLocator() {
//    }


    public ServerService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServerService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServerService
//    private java.lang.String ServerService_address = "http://117.21.178.39/services/ServerService";
    private java.lang.String ServerService_address = "";

    public java.lang.String getServerServiceAddress() {
        return ServerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServerServiceWSDDServiceName = "ServerService";

    public java.lang.String getServerServiceWSDDServiceName() {
        return ServerServiceWSDDServiceName;
    }

    public void setServerServiceWSDDServiceName(java.lang.String name) {
        ServerServiceWSDDServiceName = name;
    }

    public com.apricot.webservice.service.Server getServerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServerService(endpoint);
    }

    public com.apricot.webservice.service.Server getServerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.apricot.webservice.service.ServerServiceSoapBindingStub _stub = new com.apricot.webservice.service.ServerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getServerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServerServiceEndpointAddress(java.lang.String address) {
        ServerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.apricot.webservice.service.Server.class.isAssignableFrom(serviceEndpointInterface)) {
                com.apricot.webservice.service.ServerServiceSoapBindingStub _stub = new com.apricot.webservice.service.ServerServiceSoapBindingStub(new java.net.URL(ServerService_address), this);
                _stub.setPortName(getServerServiceWSDDServiceName());
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
        if ("ServerService".equals(inputPortName)) {
            return getServerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("ServerService", "ServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ServerService", "ServerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServerService".equals(portName)) {
            setServerServiceEndpointAddress(address);
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
