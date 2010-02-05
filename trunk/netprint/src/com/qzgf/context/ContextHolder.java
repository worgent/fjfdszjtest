package com.qzgf.context;
/**
 * 把Context对象的实例保存为当前线程的变量，在当前线程里的其他功能模块可以获取到Context变量
 * */
public class ContextHolder {
	//声明一个线程级变量
    @SuppressWarnings("unchecked")
	private static ThreadLocal contextHolder = new ThreadLocal();

    @SuppressWarnings("unchecked")
	public static void setContext(Context context) {
        contextHolder.set(context);
    }

    public static Context getContext() {
        return (Context) contextHolder.get();
    }
}
