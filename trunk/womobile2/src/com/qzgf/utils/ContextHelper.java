package com.qzgf.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Spring Bean容器环境获取工具类
 * @author lsr
 * @date 20081102
 */
public final class ContextHelper {
	private static ClassPathXmlApplicationContext _ctx;

	static {
        _ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    private ContextHelper() {
    }

    public static ClassPathXmlApplicationContext getContext() {
        return _ctx;
    }
}
