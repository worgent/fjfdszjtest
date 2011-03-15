/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :womobile2
* Package        :com.qzgf
* File	         :tt.java
* Written by     :fjfdszj
* Created Date   :Dec 7, 2010
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :Dec 7, 2010
* Purpose        :描述

*/
package com.qzgf;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     tt.java
 *
 */
public class eagerSingletonmm {
	/**
	 * Purpose      : 说明
	 */
	private static  eagerSingletonmm m_instance =null;
	/** 
	 * 静态工厂方法 
	 */
	public static synchronized eagerSingletonmm getInstance() {

		if(m_instance==null){
			m_instance=new eagerSingletonmm();
		}
		return m_instance;
	}
}



/**

2、编程题目：写一个单例模式出来

 

【答案】


Singleton模式主要作用是保证在Java应用程序中，一个类Class只有一个实例存在。 
一般Singleton模式通常有几种种形式: 
第一种形式: 定义一个类，它的构造函数为private的，它有一个static的private的该类变量，在类初始化时实例化，通过一个public的getInstance方法获取对它的引用,继而调用其中的方法。 


 



 

 

Java代码 
public class Singleton {    
　　private Singleton(){}    
　　//在自己内部定义自己一个实例，是不是很奇怪？    
　　//注意这是private 只供内部调用    
　　private static Singleton instance = new Singleton();    
　　//这里提供了一个供外部访问本class的静态方法，可以直接访问　　    
　　public static Singleton getInstance() {    
　　　　return instance; 　　    
　　 }    
}  

public class Singleton { 
　　private Singleton(){} 
　　//在自己内部定义自己一个实例，是不是很奇怪？ 
　　//注意这是private 只供内部调用 
　　private static Singleton instance = new Singleton(); 
　　//这里提供了一个供外部访问本class的静态方法，可以直接访问　　 
　　public static Singleton getInstance() { 
　　　　return instance; 　　 
　　 } 
}
 

 

第二种形式: 


Java代码 
public class Singleton {    
　　private static Singleton instance = null;    
　　public static synchronized Singleton getInstance() {    
　　//这个方法比上面有所改进，不用每次都进行生成对象，只是第一次　　   
　　//使用时生成实例，提高了效率！    
　　if (instance==null)    
　　　　instance＝new Singleton();    
return instance; 　　}    
}   

public class Singleton { 
　　private static Singleton instance = null; 
　　public static synchronized Singleton getInstance() { 
　　//这个方法比上面有所改进，不用每次都进行生成对象，只是第一次　　
　　//使用时生成实例，提高了效率！ 
　　if (instance==null) 
　　　　instance＝new Singleton(); 
return instance; 　　} 
} 
 


 

 

其他形式: 
定义一个类，它的构造函数为private的，所有方法为static的。 
一般认为第一种形式要更加安全些


*/