package com.qzgf.NetStore.util.context;

import com.qzgf.NetStore.persistence.Administrator;

public class LoginContextUtil {
	public static Administrator getLoginContext(){
        if ((ContextHolder.getContext() == null)
                || !(ContextHolder.getContext() instanceof Context)) {
                throw new IllegalStateException("ContextHolder invalid: '"
                    + ContextHolder.getContext());
            }
        return ((ContextImpl)ContextHolder.getContext()).getUserInfo();
	}
	
	public static void setLoginContext(Administrator as){
		ContextImpl ct=new ContextImpl();
		ContextHolder ch=new ContextHolder();
		ct.setUserInfo(as);
		ch.setContext(ct);
	}
}
