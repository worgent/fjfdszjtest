/**
 * PostalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com._59112580.www;

public interface PostalService extends javax.xml.rpc.Service {
    public java.lang.String getPostalServiceSoapAddress();

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap() throws javax.xml.rpc.ServiceException;

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getPostalServiceSoap12Address();

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com._59112580.www.PostalServiceSoap getPostalServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
