/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.context.webcontext.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enation.framework.context.webcontext.WebSessionContext;

/**
 * @author kingapex
 */
public class WebSessionContextImpl implements WebSessionContext, Externalizable {

	private HttpSession session;
	
	private final Log logger=LogFactory.getLog(getClass());

	// private SaveSessionAttributeCallBack callBackHandle;

	private Hashtable attributes;

	// private FrameworkSessionContextImpl sessionAttributeHolder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#getSession()
	 */
	public HttpSession getSession() {
		return session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	public void setSession(HttpSession session) {
		
		if(logger.isDebugEnabled()){
			logger.debug("set session "+ session); 
		}
		
		this.session = session;
		this.attributes = (Hashtable) this.session
				.getAttribute(sessionAttributeKey);
		if (attributes == null) {
			attributes = new Hashtable();
			this.onSaveSessionAttribute();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#invalidateSession()
	 */
	public void invalidateSession() {
		// this.session.removeAttribute(sessionAttributeKey);
		this.session.invalidate();
		// this.session = null;
		// this.sessionAttributeHolder = null;
	}

	// public FrameworkSessionContextImpl getSessionAttributeHolder() {
	// return sessionAttributeHolder;
	// }

	// public void setSessionAttributeHolder(FrameworkSessionContextImpl
	// sessionAttributeHolder) {
	// // this.sessionAttributeHolder = sessionAttributeHolder;
	// // this.sessionAttributeHolder.setCallBackHandle(this);
	// this.session.setAttribute(sessionAttributeKey,
	// sessionAttributeHolder);
	// }

	private void onSaveSessionAttribute() {
		this.session.setAttribute(sessionAttributeKey, attributes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String name, Object value) {
		
		if(attributes!=null){
		if(logger.isDebugEnabled()){
			logger.debug("set attribute :");
			logger.debug("attributes=>"+attributes);
			logger.debug("name=>"+name);
			logger.debug("value=>"+value);
		}
		
		attributes.put(name, value);
		onSaveSessionAttribute();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		if(attributes!=null)
		return attributes.get(name);
		else return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#getAttributeNames()
	 */
	public Set getAttributeNames() {
		return attributes.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lenovo.labs.framework.util.sessioncontext.impl.FrameworkSessionContext#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		attributes.remove(name);
		onSaveSessionAttribute();
	}

	// public SaveSessionAttributeCallBack getCallBackHandle() {
	// return callBackHandle;
	// }
	//
	// public void setCallBackHandle(SaveSessionAttributeCallBack
	// callBackHandle) {
	// this.callBackHandle = callBackHandle;
	// }

	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		attributes = (Hashtable) input.readObject();
	}

	public void writeExternal(ObjectOutput output) throws IOException {
		output.writeObject(attributes);
	}

	public void destory() {
		this.attributes = null;
		this.session = null;
	}
}
