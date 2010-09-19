package com.startech.els;

public class IPostalPortTypeProxy implements com.startech.els.IPostalPortType {
  private String _endpoint = null;
  private com.startech.els.IPostalPortType iPostalPortType = null;
  
  public IPostalPortTypeProxy() {
    _initIPostalPortTypeProxy();
  }
  
  public IPostalPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initIPostalPortTypeProxy();
  }
  
  private void _initIPostalPortTypeProxy() {
    try {
      iPostalPortType = (new com.startech.els.IPostalLocator()).getIPostalHttpPort();
      if (iPostalPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iPostalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iPostalPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iPostalPortType != null)
      ((javax.xml.rpc.Stub)iPostalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.startech.els.IPostalPortType getIPostalPortType() {
    if (iPostalPortType == null)
      _initIPostalPortTypeProxy();
    return iPostalPortType;
  }
  
  public java.lang.String postalBack(com.startech.els.Back[] in0) throws java.rmi.RemoteException{
    if (iPostalPortType == null)
      _initIPostalPortTypeProxy();
    return iPostalPortType.postalBack(in0);
  }
  
  public com.startech.els.Postal[] getList(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException{
    if (iPostalPortType == null)
      _initIPostalPortTypeProxy();
    return iPostalPortType.getList(in0, in1, in2);
  }
  
  
}