package com.qzgf.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class BaseMainAction extends BaseAction  {

	private static final Log logger = LogFactory.getLog(BaseMainAction.class);
	
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			this.addActionError(this.getText("error.msg"));
			return ERROR;
		}
	}
}
