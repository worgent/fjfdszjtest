/**
 * PostalServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com._59112580.www;

public class PostalServiceLocator extends org.apache.axis.client.Service implements com._59112580.www.PostalService {

    public PostalServiceLocator() {
    }


    public PostalServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PostalServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PostalServiceSoap
    private java.lang.String PostalServiceSoap_address = "http://www.59112580.com/PostalService.asmx";

    public java.lang.String getPostalServiceSoapAddress() {
        return PostalServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PostalServiceSoapWSDDServiceName = "PostalServiceSoap";

    public java.lang.String getPostalServiceSoapWSDDServiceName() {
        return PostalServiceSoapWSDDServiceName;
    }

    public void setPostalServiceSoapWSDDServiceName(java.lang.String name) {
        PostalServiceSoapWSDDServiceName = name;
    }

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PostalServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPostalServiceSoap(endpoint);
    }

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com._59112580.www.PostalServiceSoapStub _stub = new com._59112580.www.PostalServiceSoapStub(portAddress, this);
            _stub.setPortName(getPostalServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPostalServiceSoapEndpointAddress(java.lang.String address) {
        PostalServiceSoap_address = address;
    }


    // Use to get a proxy class for PostalServiceSoap12
    private java.lang.String PostalServiceSoap12_address = "http://www.59112580.com/PostalService.asmx";

    public java.lang.String getPostalServiceSoap12Address() {
        return PostalServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PostalServiceSoap12WSDDServiceName = "PostalServiceSoap12";

    public java.lang.String getPostalServiceSoap12WSDDServiceName() {
        return PostalServiceSoap12WSDDServiceName;
    }

    public void setPostalServiceSoap12WSDDServiceName(java.lang.String name) {
        PostalServiceSoap12WSDDServiceName = name;
    }

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PostalServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPostalServiceSoap12(endpoint);
    }

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com._59112580.www.PostalServiceSoap12Stub _stub = new com._59112580.www.PostalServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getPostalServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPostalServiceSoap12EndpointAddress(java.lang.String address) {
        PostalServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com._59112580.www.PostalServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com._59112580.www.PostalServiceSoapStub _stub = new com._59112580.www.PostalServiceSoapStub(new java.net.URL(PostalServiceSoap_address), this);
                _stub.setPortName(getPostalServiceSoapWSDDServiceName());
                return _stub;
            }
            if (com._59112580.www.PostalServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com._59112580.www.PostalServiceSoap12Stub _stub = new com._59112580.www.PostalServiceSoap12Stub(new java.net.URL(PostalServiceSoap12_address), this);
                _stub.setPortName(getPostalServiceSoap12WSDDServiceName());
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
        if ("PostalServiceSoap".equals(inputPortName)) {
            return getPostalServiceSoap();
        }
        else if ("PostalServiceSoap12".equals(inputPortName)) {
            return getPostalServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.59112580.com/", "PostalService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.59112580.com/", "PostalServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://www.59112580.com/", "PostalServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PostalServiceSoap".equals(portName)) {
            setPostalServiceSoapEndpointAddress(address);
        }
        else 
if ("PostalServiceSoap12".equals(portName)) {
            setPostalServiceSoap12EndpointAddress(address);
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
