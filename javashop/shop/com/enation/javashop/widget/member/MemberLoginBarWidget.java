/*
============================================================================
��Ȩ���� (C) 2008-2010 �����ǻ㣨�������Ƽ����޹�˾������������Ȩ����
��վ��ַ��http://www.javamall.com.cn

����������ȫ���ء������û���ȨЭ�顷�Ļ����ϣ���������Ӧ�����κ���;��������
ҵ��;����������֧��������Ȩ��Ȩ���á��������û���ȨЭ�顷���Դ����ǵ���վ���أ�
������ķ��ɷ��գ���Ҳ������ϵ���ǻ������汾����ȨЭ�顣��δ����Ȩ����²�
�����Գ���������κ���ʽ�κ�Ŀ�ĵ��޸Ļ��ٷ�����
============================================================================
*/
package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;

public class MemberLoginBarWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if(member==null){
			this.putData("isLogin", false);
		}else{
			this.putData("isLogin", true);
			this.putData("member", member);
		}

	}

}