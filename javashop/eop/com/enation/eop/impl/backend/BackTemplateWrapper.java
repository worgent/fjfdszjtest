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
package com.enation.eop.impl.backend;

import com.enation.eop.core.Request;
import com.enation.eop.core.Response;
import com.enation.eop.core.facade.AbstractFacadePagetParser;
import com.enation.eop.model.FacadePage;
import com.enation.eop.utils.JspUtil;

public class BackTemplateWrapper extends AbstractFacadePagetParser {
	
	public BackTemplateWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	
	protected Response parse(Response response) {
		
		httpRequest.setAttribute("content", response.getContent());
		
		String content  = JspUtil.getJspOutput("/admin/main_frame.jsp", httpRequest, httpResponse);
		response.setContent(content);
		return response;
	}

}