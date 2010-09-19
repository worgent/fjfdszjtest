package com._59112580.www;

public class PostalServiceSoapProxy implements com._59112580.www.PostalServiceSoap {
  private String _endpoint = null;
  private com._59112580.www.PostalServiceSoap postalServiceSoap = null;
  
  public PostalServiceSoapProxy() {
    _initPostalServiceSoapProxy();
  }
  
  public PostalServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initPostalServiceSoapProxy();
  }
  
  private void _initPostalServiceSoapProxy() {
    try {
      postalServiceSoap = (new com._59112580.www.PostalServiceLocator()).getPostalServiceSoap();
      if (postalServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)postalServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)postalServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (postalServiceSoap != null)
      ((javax.xml.rpc.Stub)postalServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com._59112580.www.PostalServiceSoap getPostalServiceSoap() {
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap;
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap.helloWorld();
  }
  
  public com._59112580.www.Postal[] getList(java.lang.String username, java.lang.String password, java.lang.String parameter) throws java.rmi.RemoteException{
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap.getList(username, password, parameter);
  }
  
  public java.lang.String postalRecBack(com._59112580.www.RecBack[] backList) throws java.rmi.RemoteException{
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap.postalRecBack(backList);
  }
  
  public java.lang.String postalDistBack(com._59112580.www.DistBack[] backList) throws java.rmi.RemoteException{
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap.postalDistBack(backList);
  }
  
  public java.lang.String postalGetDataBack(java.lang.String orders) throws java.rmi.RemoteException{
    if (postalServiceSoap == null)
      _initPostalServiceSoapProxy();
    return postalServiceSoap.postalGetDataBack(orders);
  }
  
  
}