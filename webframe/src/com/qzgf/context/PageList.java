package com.qzgf.context;

import java.util.*;

public class PageList {

	@SuppressWarnings("unchecked")
	private List objectList;
	private Pages pages;

	public PageList() {
	}

	@SuppressWarnings("unchecked")
	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@SuppressWarnings("unchecked")
	public List getObjectList() {
		return objectList;
	}

	public Pages getPages() {
		return pages;
	}
}
