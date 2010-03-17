package com.qzgf.utils.interceptor;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.log.dto.OperateLog;
import com.qzgf.comm.Constant;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.webutils.interceptor.SessionAware;

//切面
@Aspect
public class LoggingAspect implements SessionAware{
	
	private Log log=LogFactory.getLog(this.getClass());
	
	private OperateLog operateLog=new OperateLog();
	private BaseSqlMapDAO baseSqlMapDAO = null;
	private UserSession userSession;

	@AfterReturning(pointcut="LogPointcut.loggingOperation()",returning="result")
	public void logAfterReturning(JoinPoint joinPoint,Object result){
		
		UserSession us=(UserSession)ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		operateLog.setStaffId(us.getUserId());	//操作人员ID
		operateLog.setOptIp("127.0.0.1111");			//登陆终端号`
		operateLog.setOptResult(result==null?"":String.valueOf(result));		//结果
		operateLog.setInterfaceName(joinPoint.getSignature().getDeclaringTypeName());	//操作的业务名称
		operateLog.setImplName(joinPoint.getTarget().getClass().getName());
		operateLog.setOptMethod(joinPoint.getSignature().getName());
		operateLog.setOptArgs(Arrays.toString(joinPoint.getArgs()));
		baseSqlMapDAO.insert("OperateLog.insertOpersteLog", operateLog);

	}
	
	/**
	 * 也需要记录日志,等待完善代码
	 * @param joinPoint
	 * @param e
	 */
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

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	
	
}
