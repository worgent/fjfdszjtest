package net.trust.utils.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.application.systemManage.log.dto.OperateLog;
import net.trust.application.systemManage.manager.dto.UserInfo;
import net.trust.context.LoginContextUtil;

import java.lang.reflect.Method;

public class LoggingInterceptor implements AfterReturningAdvice{
	private Log log = LogFactory.getLog(LoggingInterceptor.class);
	private OperateLog operateLog=new OperateLog();
	private BaseSqlMapDAO baseSqlMapDAO = null;
	private String optTerm;

	public void afterReturning(Object returnValue, Method method, Object[] arg2, Object arg3) throws Throwable {
	    //方法拦截
	    //可以用于写日志或进行权限判断
		if(log.isDebugEnabled()){
			log.debug("success-------------logging after! "+method.getDeclaringClass().getName()+"."+method.getName());
		}
		
		UserInfo userInfo = LoginContextUtil.getLoginContext();
		
		operateLog.setStaffId(userInfo.getStaffId());	//操作人员ID
		operateLog.setOptTerm(userInfo.getIp());			//登陆终端号`
		operateLog.setOptResult(this.getResult(returnValue));		//结果
//		operateLog.setTermType(userInfo.getAgent());		//终端类型
//		operateLog.setOpertype(getOperType(method));
		operateLog.setServiceName(getMethod(method));		//操作的业务名称
		operateLog.setOptMemo(getOpercontext(arg2)); 		//操作说明
		operateLog.setCityId(userInfo.getCityId()); 		//操作说明
		baseSqlMapDAO.insert("OperateLog.insertOpersteLog", operateLog);
 	}

	/**
	 * 取得操作类型
	 * */
	private String getOperType(Method method){
		String methodName=method.getName().toLowerCase();
		if(methodName.indexOf("find")>-1){
			return "S";
		}else if(methodName.indexOf("insert")>-1){
			return "I";
		}else if(methodName.indexOf("update")>-1){
			return "U";
		}else if(methodName.indexOf("delete")>-1){
			return "D";
		}else{
			return "O";
		}
	}
	/**
	 * 取得操作方法名
	 * */
	private String getMethod(Method method){
		String methodName=method.getDeclaringClass().getName()+"."+method.getName();
		return methodName;
	}
	/**
	 * 取得操作上下文
	 * */
	private String getOpercontext(Object[] arg2){
		if(arg2==null||arg2.length==0){
			return "";
		}
		String context;
		if(arg2[0]==null)
			context="NULL";
		else
			context=arg2[0].toString();
		for(int i=1;i<arg2.length;i++){
			if(arg2[i]==null)
				context+=","+"NULL";
			else
				context+=","+arg2[i].hashCode();
		}
		return context;
	}
	
	private String getResult(Object returnValue){
		if (returnValue == null){
			return "";
		}
		
		String result = String.valueOf(returnValue);
		//result = arg3.getClass().get
		
		return result;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}
