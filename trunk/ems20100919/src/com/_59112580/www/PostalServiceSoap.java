/**
 * PostalServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com._59112580.www;

public interface PostalServiceSoap extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public com._59112580.www.Postal[] getList(java.lang.String username, java.lang.String password, java.lang.String parameter) throws java.rmi.RemoteException;
    public java.lang.String postalRecBack(com._59112580.www.RecBack[] backList) throws java.rmi.RemoteException;
    public java.lang.String postalDistBack(com._59112580.www.DistBack[] backList) throws java.rmi.RemoteException;
    public java.lang.String postalGetDataBack(java.lang.String orders) throws java.rmi.RemoteException;
}
