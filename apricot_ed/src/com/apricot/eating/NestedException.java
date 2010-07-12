/**
 * 
 */
package com.apricot.eating;

import java.text.MessageFormat;
import java.util.Properties;
/**
 * @author Administrator
 */
public class NestedException extends Exception {
	public static final Properties messageMap = new Properties();
	static {
		try {
			messageMap.load(NestedException.class.getResourceAsStream("exception.properties"));
		} catch (Exception e) {
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2836380725125432281L;
	private String[] args;
	private String code;
	private String message;

	public NestedException(String code) {
		super();
		this.code = code;
		this.args = new String[0];
	}

	public NestedException(String code, String e) {
		super(e);
		this.code = code;
		this.message = e;
	}

	public NestedException(String code, String e, String[] args) {
		super(e);
		this.code = code;
		this.args = args;
		this.message = e;
	}

	public NestedException(String code, String[] args) {
		super(code);
		this.code = code;
		this.args = args;
	}

	public NestedException(String code, Throwable e) {
		super(e);
		this.code = code;
	}

	public String[] getArgs() {
		return args;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		if (message == null) {
			message = NestedException.messageMap.getProperty(code);
		}
		return MessageFormat.format(message, args);
	}
}
