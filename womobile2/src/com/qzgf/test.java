/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :womobile2
 * Package        :com.qzgf
 * File	         :test.java
 * Written by     :fjfdszj
 * Created Date   :Dec 3, 2010
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Dec 3, 2010
 * Purpose        :描述

 */
package com.qzgf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     test.java
 *
 */
public class test {
	/**
	 * Purpose      : 说明
	 */
	public class TestClass3 {//Error 
		int m=10;
		public TestClass3(){
			System.out.println("内部");
		}
		private void hell() {
			System.out.println("123");
			
		}
		
		public void change(TestClass3 mm){
			mm.m=1;
		}

		public boolean equals(Object anObject) {
			if (this == anObject) {
				return true;
			}
			if (anObject instanceof TestClass3) {
				return true;
			}
			return false;
		}

		public int hashCode() {
			return 11;
		}


	}
	
	public test(){
		System.out.println("外部");
	}

	
	public static void main(String[] args) {
		
        StringTokenizer exprsTok = new StringTokenizer("m m m", " \t",
                false);
        while (exprsTok.hasMoreTokens() ) {
        	 String expr = exprsTok.nextToken().trim();
     		System.out.println(expr);
        	
        }
		//test txx=new test();
		/*
		 TestClass3 ts =new test().new TestClass3();
		 System.out.println(ts.m);
		 ts.change(ts);
		 System.out.println(ts.m);
		 
		 
		/*		   TestClass3 ts =new test().new TestClass3();
		 TestClass3 nn =new test().new TestClass3();
		 ts.hell();
		 if(ts==nn){
		
		 }
		
		 String a=new String("123");
		 String b=new String("123");

		 //a="555";

		
		 System.out.println(Integer.toHexString(a.hashCode()));
		 System.out.println(Integer.toHexString(b.hashCode()));
		
		 System.out.println(ts.equals(nn));
		 System.out.println(Integer.toHexString(ts.hashCode()));
		 System.out.println(Integer.toHexString(nn.hashCode()));
		
		
		 System.out.println(a+"   "+b);
		
		
		
		
		 int y = 3;
		 int x = 4;
		 int c = 3;
		 System.out.println(x == y);//结果是false
		 System.out.println(y == c);//结果是true
		 //System.out.println(y.equals(c));//错误，编译不能通过，equals方法
		 System.out.println(ts.equals(nn));//错误，编译不能通过，equals方法
		 //不能运用与基本类型的比较


		
		 */
		
		
		/*
		StringBuffer ms=new StringBuffer();
		
		short st=12;
		//复合赋值运算符会自动地将运算结果转型为其左操作数的类型！
		//st=st+1;
		st+=1;
		//Collection t=new Collection();
		
		int j = new theadtest().m;

		System.out.println("jjj:" + j);
		Set<String> set = new HashSet<String>();//语句(1)   
		System.out.println(set.add("abc"));//语句(2)   
		System.out.println(set.add("xyz"));//语句(3)   
		System.out.println(set.add("abc"));//语句(4)   

		Set<String> set1 = new HashSet<String>();//语句(1)   
		System.out.println(set1.add("abc"));//语句(2)   
		System.out.println(set1.add("xyz"));//语句(3)   
		System.out.println(set1.add("abc"));//语句(4)   

		if (set1.equals(set)) {
			System.out.println("set good");
		}
		if (set.contains("abc")) {
			System.out.println("good");
		}
		;
		set.iterator();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {//语句(5)   
			System.out.println(it.next());
		}

		String[] ss = new String[2];
		int i = ss.length;

		String mx = "";
		mx.length();

		System.out.println(Math.round(11.6) + "  " + Math.round(-11.6) + "  "
				+ Math.floor(11.5) + "  " + Math.floor(-11.5));

		final int b;
		b = 5;

		final Integer x; // 本例 

		x = new Integer(2);

		final StringBuffer s1;

		final StringBuffer s2 = new StringBuffer("cd");

		s1 = s2; //编译错误
		System.gc();

		s1.append(s2); //编译通过

		System.out.println(s1.toString());
		System.out.println(s2.toString());

		// System.out.println(new test().say());
		 * 
		 * 
		 */

	}

	private int say() {
		try {
			int i = 10 / 0;
		} catch (Exception tt) {
			System.out.println("my");
			return 1;
		} finally {
			System.out.println("min");
			//return 0;
		}
		return 2;
	}

}
