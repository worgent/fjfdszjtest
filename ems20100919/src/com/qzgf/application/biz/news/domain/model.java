package com.qzgf.application.biz.news.domain;

public class model {
    private String NEWSID;
    private String ARTICLESTYPE;
    private String TITLE;
    private String RELEASETIME;
    private String ISAUDIT;
    private String SOURCE;
    private String AUTHOR; 
    private String CONTENT;
	public String getNEWSID() {
		return NEWSID;
	}
	public void setNEWSID(String newsid) {
		NEWSID = newsid;
	}
	public String getARTICLESTYPE() {
		return ARTICLESTYPE;
	}
	public void setARTICLESTYPE(String articlestype) {
		ARTICLESTYPE = articlestype;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String title) {
		TITLE = title;
	}
	public String getRELEASETIME() {
		return RELEASETIME;
	}
	public void setRELEASETIME(String releasetime) {
		RELEASETIME = releasetime;
	}
	public String getISAUDIT() {
		return ISAUDIT;
	}
	public void setISAUDIT(String isaudit) {
		ISAUDIT = isaudit;
	}
	public String getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(String source) {
		SOURCE = source;
	}
	public String getAUTHOR() {
		return AUTHOR;
	}
	public void setAUTHOR(String author) {
		AUTHOR = author;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String content) {
		CONTENT = content;
	}
}
