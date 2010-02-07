package com.qzgf.NetStore.pub;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalServiceLocator {

	private static LocalServiceLocator instance;

	private LocalServiceLocator() {
		super();
	};

	private static BeanFactory appContext = null;

	public static LocalServiceLocator getInstance() {
		if (instance == null) {
			instance = new LocalServiceLocator();

		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	public Object getService(Class serviceIntfClass) {
		System.out.println("读取配置文件结束后,进入getService---------------");
		String serviceId = serviceIntfClass.getName();
		System.out.println("=======serviceId:" + serviceId);
		Object bean = appContext.getBean(serviceId);
        System.out.println("ServiceId:["+serviceId+"].bean:"+bean);
	
        System.out.println("退出:"+bean+"的日志,权限,事务操作");
		return bean;
	}

	static {
		try {
			System.out.println("开始读取配置信息");
			appContext = new ClassPathXmlApplicationContext(ServerConfig
					.getInstance().getBeanFiles());
			System.out.println("读取配置信息完成");
		} catch (Exception e) {
			System.out.println("ClassPathXmlApplicationContext错误的信息是"
					+ e.getMessage());
		}
	}

}
