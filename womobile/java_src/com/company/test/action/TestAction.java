package com.company.test.action;

import javacommon.base.BaseStruts2Action;

import org.jbpm.api.ProcessEngine;


/**
 *  lsr
 * 自动注入流程引擎
 */
@SuppressWarnings("serial")
public class TestAction extends BaseStruts2Action
{
	protected static final String QUERY_JSP = "/test/joinJbpm/testJbpm.jsp";
    private ProcessEngine processEngine;

    public ProcessEngine getProcessEngine()
    {
        return processEngine;
    }

    /**
     * 测试是否整合成功 输出部署编号
     */
    public String testJbpm()
    {
        System.out.println("流程引擎: "+processEngine);
        return QUERY_JSP;
    }
    
    public void setProcessEngine(ProcessEngine processEngine)
    {
        this.processEngine = processEngine;
    }
}

