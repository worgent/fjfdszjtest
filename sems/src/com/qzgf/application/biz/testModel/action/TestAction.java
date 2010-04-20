package com.qzgf.application.biz.testModel.action;

import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.testModel.domain.TestFacade;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.webutils.interceptor.SessionAware;

/** 
 * 测试
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class TestAction extends BaseAction implements SessionAware {
	UserSession userSession;
	private TestFacade testFacade;
	private String name;
	
	public String execute() {
		try {
			System.out.println("action:"+this.getAction());
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			//log.error(e.printStackTrace());
			e.printStackTrace();
			return "index";
		}
	}
	
	public String index() throws Exception {
		this.setAction("add");
		try {
			System.out.println("插入");
			System.out.println("name:"+this.getName());
			System.out.println("action:"+this.getAction());
			//this.testFacade.insertTest(search);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return INPUT;
	}
	
	
	
	public TestFacade getTestFacade() {
		return testFacade;
	}
	public void setTestFacade(TestFacade testFacade) {
		this.testFacade = testFacade;
	}
	public UserSession getUserSession() {
		return userSession;
	}
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
}
