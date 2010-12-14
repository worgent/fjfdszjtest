/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.enation.framework.database.NotDbField;

public class ReflectionUtil {
		
	public static Object invokeMethod(String className, String methodName,
			Object[] args) {

		try {

			Class serviceClass = Class.forName(className);
			Object service = serviceClass.newInstance();

			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Method method = serviceClass.getMethod(methodName, argsClass);
			return method.invoke(service, args);

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static Object newInstance(String className,Object... _args ){

		try {
			  Class[] argsClass = new Class[_args.length];                                  
			                                                                                 
			   for (int i = 0, j = _args.length; i < j; i++) {   
					   
				   if(_args[i]==null){
					   argsClass[i]=null;
				   }
				   else{
					    
					   argsClass[i] = _args[i].getClass();
				   }
			    }      
			   
			   
			 Class newoneClass  = Class.forName(className);
			 Constructor cons = newoneClass.getConstructor(argsClass);                    
             
			 Object obj= cons.newInstance(_args);
			 return obj;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		 
		 return null;
	 
	}

	/**
	 * 将po对象中有属性和值转换成map
	 * 
	 * @param po
	 * @return
	 */
	public static Map po2Map(Object po) {
		Map poMap = new HashMap();
		Map map = new HashMap();
		try {
			map = BeanUtils.describe(po);
		} catch (Exception ex) {
		}
		Object[] keyArray = map.keySet().toArray();
		for (int i = 0; i < keyArray.length; i++) {
			String str = keyArray[i].toString();
			if (str != null && !str.equals("class")) {
				if (map.get(str) != null) {
					poMap.put(str, map.get(str));
				}
			}
		}

		Method[] ms =po.getClass().getMethods();
		for(Method m:ms){
			String name = m.getName();
			
			if(name.startsWith("get")){
				if(m.getAnnotation(NotDbField.class)!=null){
					poMap.remove(getFieldName(name)); 
				} 
			}

		}
		return poMap;
	}

	private static String getFieldName(String methodName){
		 
		methodName = methodName.replaceAll("get", "");
		methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
		return methodName;
	}
	
}
