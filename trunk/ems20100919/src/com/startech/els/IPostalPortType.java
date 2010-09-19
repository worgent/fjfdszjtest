/**
 * IPostalPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.startech.els;

public interface IPostalPortType extends java.rmi.Remote {
    public java.lang.String postalBack(com.startech.els.Back[] in0) throws java.rmi.RemoteException;
    public com.startech.els.Postal[] getList(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
}
