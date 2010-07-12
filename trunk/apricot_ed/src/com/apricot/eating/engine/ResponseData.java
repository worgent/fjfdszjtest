/**
 * 
 */
package com.apricot.eating.engine;

import com.apricot.eating.MessageResource;
/**
 * @author Administrator
 */
public class ResponseData {
	private String errorMessage;
	private Object data;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 */
	private ResponseData(String code, String message, Object o) {
		// TODO Auto-generated constructor stub
		try {
			this.errorMessage = MessageResource.getInstance().getMessage(message, null);
		} catch (Exception e) {
		}
		this.data = o;
		this.errorCode = code;
	}

	public Object getData() {
		return data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public static final ResponseData createFailure(String message, Object o) {
		return new ResponseData("01", message, o);
	}

	public static final ResponseData createSuccess(String message, Object o) {
		return new ResponseData("00", message, o);
	}
}
