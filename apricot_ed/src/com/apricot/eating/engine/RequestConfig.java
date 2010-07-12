/**
 * 
 */
package com.apricot.eating.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**
 * @author Administrator
 */
public class RequestConfig {
	private String className;
	private String dataSourceJndi;
	private String enableTransaction = "false";
	private String failureForward;
	private String methodName;
	private String path;
	private String plugIn;
	private List plugIns;
	private String successForward;
	private String target;
	private String userTransactionJndi;
	private HashMap globalStaticData;


	/**
	 * @return the globalStaticData
	 */
	public HashMap getGlobalStaticData() {
	    return globalStaticData;
	}
	/**
	 * @param globalStaticData the globalStaticData to set
	 */
	public void setGlobalStaticData(HashMap globalStaticData) {
	    this.globalStaticData = globalStaticData;
	}
	public void addAll(HashMap map){
		for(Iterator objs=map.values().iterator();objs.hasNext();){
			PlugInConfig p=(PlugInConfig) objs.next();
			if(p.isGlobal()){
				add(p);
			}
		}
		

	}
	/**
	 * 
	 */
	public RequestConfig() {
		// TODO Auto-generated constructor stub
		this.plugIns = new ArrayList();
	}
	
	public void addAll(List a){
		if(a!=null) this.plugIns.addAll(a);
	}

	public void add(PlugInConfig cfg) {
		this.plugIns.add(cfg);
	}

	public boolean remove(PlugInConfig cfg) {
		return this.plugIns.remove(cfg);
	}

	public Iterator plugins() {
		return this.plugIns.iterator();
	}

	public boolean enableTransaction() {
		return "true".equalsIgnoreCase(enableTransaction);
	}

	public String getClassName() {
		return className;
	}

	public String getDataSourceJndi() {
		return dataSourceJndi;
	}

	public String getEnableTransaction() {
		return enableTransaction;
	}

	public String getFailureForward() {
		return failureForward;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getPath() {
		return path;
	}

	public String getPlugIn() {
		return plugIn;
	}

	public String getSuccessForward() {
		return successForward;
	}

	public String getTarget() {
		return target;
	}

	public String getUserTransactionJndi() {
		return userTransactionJndi;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDataSourceJndi(String dataSourceJndi) {
		this.dataSourceJndi = dataSourceJndi;
	}

	public void setEnableTransaction(String et) {
		if (et != null && et.trim().length() > 0) {
			this.enableTransaction = et;
		}
	}

	public void setFailureForward(String failureForward) {
		this.failureForward = failureForward;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPlugIn(String plugIn) {
		this.plugIn = plugIn;
	}

	public void setSuccessForward(String successForward) {
		this.successForward = successForward;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setUserTransactionJndi(String userTransactionJndi) {
		this.userTransactionJndi = userTransactionJndi;
	}

	public List getPlugIns() {
		return plugIns;
	}
}
