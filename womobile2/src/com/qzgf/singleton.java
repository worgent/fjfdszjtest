/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :womobile2
* Package        :com.qzgf
* File	         :singleton.java
* Written by     :fjfdszj
* Created Date   :Dec 6, 2010
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :Dec 6, 2010
* Purpose        :描述

*/
package com.qzgf;

/**
 * Purpose : 说明
 * 
 * @author fjfdszj
 * @see singleton.java
 * 
 */
public class singleton {

	public static class eagerSingletonmm {
		/**
		 * Purpose      : 说明
		 */
		private static  eagerSingletonmm m_instance =null;

		/** 
		 * 私有的默认构造子 
		 */
		private eagerSingletonmm() {
		}

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
	
	
	
	
	public static class eagerSingleton {
		/**
		 * Purpose      : 说明
		 */
		private static final eagerSingleton m_instance = new eagerSingleton();

		/** 
		 * 私有的默认构造子 
		 */
		private eagerSingleton() {
		}

		/** 
		 * 静态工厂方法 
		 */
		public static eagerSingleton getInstance() {

			return m_instance;
		}
	}
}
