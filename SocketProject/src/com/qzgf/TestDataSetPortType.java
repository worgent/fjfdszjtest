
package com.qzgf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "TestDataSetPortType", targetNamespace = "http://qzgf.com")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface TestDataSetPortType {

	@WebMethod(operationName = "example", action = "")
	@WebResult(name = "out", targetNamespace = "http://qzgf.com")
	public String example(
			@WebParam(name = "in0", targetNamespace = "http://qzgf.com")
			String in0);

}
