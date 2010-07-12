/**
 * 
 */
package com.apricot.eating.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import com.apricot.eating.NestedException;
import com.apricot.eating.log.Log;
/**
 * @author Administrator
 */
public class Controller {
	private RequestConfig request;

	/**
	 * 
	 */
	public Controller(RequestConfig request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	public Object service(DyncParameterMap map) throws NestedException {
		BO bo = null;
		try {
			bo = (BO) Class.forName(request.getClassName()).newInstance();
		} catch (ClassNotFoundException e) {
			Log.debug("Controller.service", e);
			throw new NestedException("SYS-0017", new String[] { request.getClassName() });
		} catch (InstantiationException e) {
			Log.debug("Controller.service", e);
			// TODO Auto-generated catch block
			throw new NestedException("SYS-0018", new String[] { request.getClassName() });
		} catch (IllegalAccessException e) {
			Log.debug("Controller.service", e);
			// TODO Auto-generated catch block
			throw new NestedException("SYS-0019", new String[] { request.getClassName() });
		}
		try {
			bo.setConnection(getConnection());
			bo.init();
			if (request.enableTransaction()) {
				bo.setTransaction(new DyncUserTransaction(bo.getConnection()));
				bo.begin();
			}
		} catch (NestedException e) {
			throw e;
		}
		try {
			Object r = null;
			if (map != null)
				bo.setGlobalStaticDataMap(map.getGlobalStaticDataMap());
	
			Method method = bo.getClass().getMethod(request.getMethodName(), new Class[] { DyncParameterMap.class });
			// System.out.println(method.getReturnType());
			if (method.getReturnType() == null || method.getReturnType().equals(void.class)) {
				/***************************************************************
				 * throw new NestedException("SYS-0020", new String[] {
				 * request.getClassName(), request.getMethodName() });
				 **************************************************************/
				method.invoke(bo, new Object[] { map });
			} else
				r = method.invoke(bo, new Object[] { map });
			if (request.enableTransaction())
				bo.commit();
			return r;
		} catch (Exception e) {
		    System.out.println("错误URL:class="+request.getClassName()+"  method="+request.getMethodName());
			e.printStackTrace();
			try {
				if (request.enableTransaction()) {
					bo.rollback();
				}
			} catch (Exception e1) {
				Log.debug("DataBase.rollback", e);
			}
			if (e instanceof InvocationTargetException) {
				if (e.getCause() != null) {
					if (e.getCause() instanceof NestedException)
						throw (NestedException) e.getCause();
					else
						throw new NestedException("SYS-0001", e.getCause().getMessage());
				} else
					throw new NestedException("SYS-0001", e);
			}
			if (e instanceof NoSuchMethodException) {
				throw new NestedException("SYS-0021", new String[] { request.getClassName(), request.getMethodName() });
			}
			Log.debug("Controller.service", e);
			throw new NestedException("SYS-0022", new String[] { request.getClassName(), request.getMethodName() });
		} finally {
			bo.closeConnection();
		}
	}

	/**
	 * 获取事务
	 * 
	 * @deprecated
	 * @return
	 * @throws NestedException
	 */
	UserTransaction getUserTransaction() throws NestedException {
		try {
			InitialContext initCtx = new InitialContext();
			return (UserTransaction) initCtx.lookup("java:comp/env/" + request.getUserTransactionJndi());
		} catch (Exception e) {
			Log.error("Controller.getUserTransaction", e, this);
			throw new NestedException("SYS-0015", new String[] { request.getUserTransactionJndi() });
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws NestedException
	 */
	private Connection getConnection() throws NestedException {
		try {
			InitialContext initCtx = new InitialContext();
			DataSource o = (DataSource) initCtx.lookup("java:comp/env/" + request.getDataSourceJndi());
			return o.getConnection();
		} catch (Exception e) {
			Log.error("Controller.getConnection", e, this);
			throw new NestedException("SYS-0016", new String[] { request.getDataSourceJndi() });
		}
	}
}
