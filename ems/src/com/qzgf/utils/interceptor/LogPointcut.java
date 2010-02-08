package com.qzgf.utils.interceptor;

import org.aspectj.lang.annotation.Pointcut;

//定义日志切入点
public class LogPointcut {
	
	@Pointcut("execution(* *.update*(..))")
	public void update(){
	}
	
	@Pointcut("execution(* *.delete*(..))")
	public void delete(){
	}
	
	@Pointcut("execution(* *.insert*(..))")
	public void insert(){
	}
	
	@Pointcut("update()||delete()||insert()")
	public void loggingOperation(){}
	
	
}
