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
	public String index() throws Exception {
		this.setAction("add");
		try {
			this.testFacade.insertTest(search);
		} catch (RuntimeException e) {
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
	
	
}
