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
public abstract class eagerSingletonmmab {
	/**
	 * Purpose      : 说明
	 */
	private static  eagerSingletonmmab m_instance =null;
	
	public eagerSingletonmmab(){};
	
	public void say(){
		System.out.println("");
	};
	
	public abstract void sayx();
	
	//从synchronized的功能也可以看出，用synchronized的前提是该方法可以被直接调用，显然和abstract连用

}

