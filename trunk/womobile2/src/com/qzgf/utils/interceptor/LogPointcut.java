package com.qzgf.utils.interceptor;

import org.aspectj.lang.annotation.Pointcut;

//定义日志切入点
public class LogPointcut {
	
	@Pointcut("execution(* com.qzgf.application..*.update*(..))")
	public void update(){
	}
	
	@Pointcut("execution(* com.qzgf.application..*.delete*(..))")
	public void delete(){
	}
	
	@Pointcut("execution(* com.qzgf.application..*.insert*(..))")
	public void insert(){
	}
	
	@Pointcut("update()||delete()||insert()")
	public void loggingOperation(){}
	
	
}
