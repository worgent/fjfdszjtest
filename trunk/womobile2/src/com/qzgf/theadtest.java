/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :womobile2
 * Package        :com.qzgf
 * File	         :theadtest.java
 * Written by     :fjfdszj
 * Created Date   :Dec 5, 2010
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Dec 5, 2010
 * Purpose        :描述

 */
package com.qzgf;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     theadtest.java
 *
 */
public class theadtest {
	protected int m = 0;
	private int j = 0;//两个线程同时处理该值

	private synchronized void dec() {
		j--;
		System.out.println(Thread.currentThread().getName() + "--dec:" + j);
	}

	private void sayhell() {
		System.out.println("dec say hello");
	}

	
	
	private synchronized void inc() {
		j++;
		System.out.println(Thread.currentThread().getName() + "-inc:" + j);
	}

	class Dec implements Runnable {

		/**
		 * Purpose      : 说明 
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 100; i++)
				dec();
		}

		public void say() {
			sayhell();
		}
	}

	class Inc implements Runnable {
		public void run() {
			for (int i = 0; i < 100; i++)
				inc();
		}

		public void sayno() {
			System.out.println("inc say no");
		}
	}

	/**
	 * Purpose      : 说明
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new eagerSingletonmmab().say();
		//
		eagerSingleton tm=	eagerSingleton.getInstance();
		//类中类
		singleton.eagerSingleton tx=singleton.eagerSingleton.getInstance();
		
		//eagerSingletonmm.
		
		
		theadtest tt = new theadtest();
		Inc inc = tt.new Inc();
		Dec dec = tt.new Dec();
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(inc);
			/*
			try {
				t.notifyAll();
				System.out.println("1:");
				t.wait(1000);
				
				System.out.println("2:");
				//t.sleep(1000);
				System.out.println("3:");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			System.out.println("线程名1:"+t.currentThread().getName());
			System.out.println("main thread:"+Thread.currentThread().getName());
			t.start();
			inc.sayno();
			t=new Thread(dec);
			System.out.println("线程名2:"+t.currentThread().getName());
			t.start();
			dec.say();
		}
	}

	/*
	
	public void  xx(){}
	
	
	public abstract class  abs{
		
		public  abstract   void  yy();
		
		public native void nn();
		
		public synchronized void ss(){};
		
		public   abs(){};
		
		public synchronized  abs(int i){};
		
	}
	
	
	public interface  absi{
		
		public abstract void  yy();
		
		
	}
	 */

	//public abstract void  yy();
	/**
	 * Purpose      : 说明
	 */
}
