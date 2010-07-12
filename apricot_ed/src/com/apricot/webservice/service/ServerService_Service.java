/**
 * ServerService_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.apricot.webservice.service;

public interface ServerService_Service extends javax.xml.rpc.Service {
    public java.lang.String getServerServiceAddress();

    public com.apricot.webservice.service.Server getServerService() throws javax.xml.rpc.ServiceException;

    public com.apricot.webservice.service.Server getServerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
