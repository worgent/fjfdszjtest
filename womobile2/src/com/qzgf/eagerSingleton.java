package com.qzgf;

/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :womobile2
 * Package        :
 * File	         :eagerSingleton.java
 * Written by     :fjfdszj
 * Created Date   :Dec 7, 2010
 * Purpose        :功能描述

 ======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Dec 7, 2010
 * Purpose        :描述

 */

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     eagerSingleton.java
 *
 */
public class eagerSingleton {
	/**
	 * Purpose      : 说明
	 */
	private static final eagerSingleton m_instance = new eagerSingleton();

	/** 
	 * 静态工厂方法 
	 */
	public static eagerSingleton getInstance() {

		return m_instance;
	}
}

