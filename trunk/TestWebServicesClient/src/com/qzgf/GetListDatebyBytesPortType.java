
package com.qzgf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "GetListDatebyBytesPortType", targetNamespace = "http://service.gpsdata.application.qzgf.com")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface GetListDatebyBytesPortType {

	@WebMethod(operationName = "GetListToByte", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public byte[] getListToByte(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

	@WebMethod(operationName = "DynamicSqlListXmlToByte", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public byte[] dynamicSqlListXmlToByte(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

	@WebMethod(operationName = "GetCompress", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public byte[] getCompress(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

	@WebMethod(operationName = "GetListToXmlToByte", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public byte[] getListToXmlToByte(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

	@WebMethod(operationName = "GetListToXml", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public String getListToXml(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

	@WebMethod(operationName = "DynamicSqlListXml", action = "")
	@WebResult(name = "out", targetNamespace = "http://service.gpsdata.application.qzgf.com")
	public String dynamicSqlListXml(
			@WebParam(name = "in0", targetNamespace = "http://service.gpsdata.application.qzgf.com")
			String in0);

}
