package com.qzgf.NetStore.util.context;

import com.qzgf.NetStore.persistence.Administrator;

public class TestContext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContextHolder ch=new ContextHolder();
		Administrator as=new Administrator();
		ContextImpl ct=new ContextImpl();
		as.setAdminId("adminid");
		as.setRealName("fjfdszj");
		ct.setUserInfo(as);
		ch.setContext(ct);//放入线程，
		System.out.print(((ContextImpl)ch.getContext()).getUserInfo().getAdminId().toString());
	}
}
