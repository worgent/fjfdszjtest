/**
 * IPostalLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.startech.els;

public class IPostalLocator extends org.apache.axis.client.Service implements com.startech.els.IPostal {

    public IPostalLocator() {
    }


    public IPostalLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IPostalLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IPostalHttpPort
    private java.lang.String IPostalHttpPort_address = "http://218.85.72.214:9080/PostalService/postalService.ws";

    public java.lang.String getIPostalHttpPortAddress() {
        return IPostalHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IPostalHttpPortWSDDServiceName = "IPostalHttpPort";

    public java.lang.String getIPostalHttpPortWSDDServiceName() {
        return IPostalHttpPortWSDDServiceName;
    }

    public void setIPostalHttpPortWSDDServiceName(java.lang.String name) {
        IPostalHttpPortWSDDServiceName = name;
    }

    public com.startech.els.IPostalPortType getIPostalHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IPostalHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIPostalHttpPort(endpoint);
    }

    public com.startech.els.IPostalPortType getIPostalHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.startech.els.IPostalHttpBindingStub _stub = new com.startech.els.IPostalHttpBindingStub(portAddress, this);
            _stub.setPortName(getIPostalHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIPostalHttpPortEndpointAddress(java.lang.String address) {
        IPostalHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.startech.els.IPostalPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.startech.els.IPostalHttpBindingStub _stub = new com.startech.els.IPostalHttpBindingStub(new java.net.URL(IPostalHttpPort_address), this);
                _stub.setPortName(getIPostalHttpPortWSDDServiceName());
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
        if ("IPostalHttpPort".equals(inputPortName)) {
            return getIPostalHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://els.startech.com", "IPostal");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://els.startech.com", "IPostalHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IPostalHttpPort".equals(portName)) {
            setIPostalHttpPortEndpointAddress(address);
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
