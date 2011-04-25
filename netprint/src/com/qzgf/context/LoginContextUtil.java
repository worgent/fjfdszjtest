package com.qzgf.context;

import com.qzgf.application.systemManage.manager.dto.UserInfo;



public class LoginContextUtil {
	public static UserInfo getLoginContext(){
        if ((ContextHolder.getContext() == null)
                || !(ContextHolder.getContext() instanceof Context)) {
                throw new IllegalStateException("ContextHolder invalid: '"
                    + ContextHolder.getContext());
            }
        return ((ContextImpl)ContextHolder.getContext()).getUserInfo();
	}
}
