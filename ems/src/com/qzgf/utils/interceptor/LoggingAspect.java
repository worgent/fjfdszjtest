package com.qzgf.utils.interceptor;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.log.dto.OperateLog;

//切面
@Aspect
public class LoggingAspect {
	
	private Log log=LogFactory.getLog(this.getClass());
	
	private OperateLog operateLog=new OperateLog();
	private BaseSqlMapDAO baseSqlMapDAO = null;

	@AfterReturning(pointcut="LogPointcut.loggingOperation()",returning="result")
	public void logAfterReturning(JoinPoint joinPoint,Object result){
		log.info("Signature declaring type :"+
				joinPoint.getSignature().getDeclaringTypeName());//接口
		log.info("Signature name : "+joinPoint.getSignature().getName());//方法
		log.info("Arguments :"+Arrays.toString(joinPoint.getArgs()));//参数
		log.info("Target class :"+joinPoint.getTarget().getClass().getName());//接口实现类
		log.info("The method "+joinPoint.getSignature().getName()+"() ends with "+result);//返回值
		
		operateLog.setStaffId("whitewolf");	//操作人员ID
		operateLog.setOptIp("127.0.0.1111");			//登陆终端号`
		operateLog.setOptResult(result==null?"":String.valueOf(result));		//结果
		operateLog.setInterfaceName(joinPoint.getSignature().getDeclaringTypeName());	//操作的业务名称
		operateLog.setImplName(joinPoint.getTarget().getClass().getName());
		operateLog.setOptMethod(joinPoint.getSignature().getName());
		operateLog.setOptArgs(Arrays.toString(joinPoint.getArgs()));
		baseSqlMapDAO.input("OperateLog.insertOpersteLog", operateLog);
		
	}
	
	@AfterThrowing(pointcut="LogPointcut.loggingOperation()",throwing="e")
	public void logAfterThrowing(JoinPoint joinPoint,Throwable e){
		log.error("An exception "+ e +" has been throws in "
				+joinPoint.getSignature().getName()+"()");
		System.out.println(e.getMessage());
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
	
}
