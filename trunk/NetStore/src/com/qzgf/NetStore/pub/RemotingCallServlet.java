package com.qzgf.NetStore.pub;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.cownew.ctk.common.StringUtils;
import com.qzgf.NetStore.pub.lifeMgr.ServiceLifeMonitor;
import com.qzgf.NetStore.pub.lifeMgr.SessionServiceLifeListener;
import com.qzgf.NetStore.pub.sessionMgr.SessionManager;

/**
 * 远程映射接口的实现类
 * @author lsr
 */
@SuppressWarnings("serial")
public class RemotingCallServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(RemotingCallServlet.class);

	protected void doPost(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws ServletException,
			IOException {
		try {
			invokeService(httpResponse, httpRequest);
		} catch (Exception rpcEx) {
			// 像session非法之类的异常不是在bean运行过程中抛出的,因此客户端得不到异常,
			// 这类异常会在此处抛出,因此我们通过http错误代码的方式发送给客户端
			// 这样也保证了SessionServiceLifeListener不用关心用的是什么remoting技术
			// 只要抛出异常就可以了
			//HttpServletResponse.SC_BAD_REQUEST:400错误,即请求语法上面有错
			String msg = rpcEx.getMessage();
			httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);

			logger.error(msg, rpcEx);
		} catch (Throwable e) {
			// 注意,bean运行过程中的异常并不是通过此处抛出的,而是通过remoting机制传递到客户端再抛出的
			// 此处抛出的是非bean的异常
			logger.error(e.getMessage(), e);

			throw new ServletException(e);
		}
	}

	private void invokeService(HttpServletResponse response,
			HttpServletRequest request) throws ServletException {
		String reqPath = request.getPathInfo();
		String serviceId = getServiceId(reqPath);
		String sessionId = request.getParameter(SysConstants.SESSIONID);
		ServiceLifeMonitor lifeMonitor = new ServiceLifeMonitor(sessionId);
		lifeMonitor.addServiceLifeListener(new SessionServiceLifeListener());

		try {
			if(serviceId.equals("com.qzgf.NetStore.dao.ICommonDAO")){
				// 对于登录服务等系统服务来说是无需而且也无法得到sessionId的,因为当时还没有一个sessionId
				// 所以要进行非空判断
				if (!StringUtils.isEmpty(sessionId)) {
					SessionManager sessionMgr = SessionManager.getInstance();
					boolean valid = sessionMgr.isValid(sessionId);
					// 判断sessionId是否合法 
					if (!valid) {
						System.out.println("sessionId不合法");
						//
					}

					ThreadVariableManager.getInstance().setCurrentSessionId(sessionId);
				}
			}
			else{
			    lifeMonitor.beforeInvoke();
			}
			invokeBean(request, response, serviceId);
		} finally {
			lifeMonitor.afterInvoke();
		}
	}

	private static String getServiceId(String reqPath) {
		Pattern pattern = Pattern.compile("/(.+)");
		Matcher match = pattern.matcher(reqPath);
		match.matches();
		match.group();

		String serviceId = match.group(1);
		return serviceId;
	}
	
	@SuppressWarnings("unchecked")
	private void invokeBean(HttpServletRequest request,
			HttpServletResponse response, String serviceId)
			throws ServletException {
		// 服务都是无状态的,所以无需同步服务
		try {
			Class serviceIntfClass = Class.forName(serviceId);
			Object _service = LocalServiceLocator.getInstance().getService(
					serviceIntfClass);
			HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
			exporter.setService(_service);
			exporter.setServiceInterface(serviceIntfClass);
			exporter.afterPropertiesSet();
			exporter.handleRequest(request, response);
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	
}
