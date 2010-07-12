/**
 * 
 */
package com.apricot.eating.log;

/**
 * @author Administrator
 */
public final class Log {
	/**
	 * 记录错误日志信息
	 * 
	 * @param message
	 * @param obj
	 */
	public static final void error(String message, Throwable e, Object obj) {
		org.apache.log4j.Logger.getLogger(obj.getClass()).error(message, e);
	}

	/**
	 * 记录调试信息
	 * 
	 * @param message
	 * @param obj
	 */
	public static final void debug(String message, Object obj) {
		org.apache.log4j.Logger.getLogger(obj.getClass()).debug(message);
	}
}
