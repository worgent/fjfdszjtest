/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :womobile2
 * Package        :com.qzgf
 * File	         :innertest.java
 * Written by     :fjfdszj
 * Created Date   :Dec 8, 2010
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Dec 8, 2010
 * Purpose        :描述

 */
package com.qzgf;


/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     innertest.java
 *
 */
public  class   innertest {
	float f=(float)3.4;
	

	public int j=0;
	public static int m=0;
	public  class   InterClass {
		//int j=innertest.m;
		public  InterClass() {
			System.out.println("InterClass Create");
		}

	}

	public innertest() {
		//f=3.4; double
		//InterClass ic = new InterClass();
		System.out.println("OuterClass Create");
	}
	
	public static int  hell(){
		innertest mm=new innertest();
		//mm.j=100;
		int j=mm.j;
		int m=innertest.m;
		
		return j;
		
	}

	public static void main(String[] args) {
		innertest oc = new innertest();
		System.out.println(innertest.hell());
		int i=innertest.m;
	}

}
