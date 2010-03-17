package com.qzgf.application.systemManage.taglib.dto;

import java.io.Serializable;

/**
 * 获得系统的相关配置
 *
 */
@SuppressWarnings("serial")
public class Config implements Serializable {

	private String id;

	private String confContext;

	public Config() {
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setConfContext(String confContext) {
		this.confContext = confContext;
	}

	public String getConfContext() {
		return confContext;
	}
}
