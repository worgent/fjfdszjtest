package com.qzgf.application.systemManage.frontlogin.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.frontlogin.domain.FrontLoginFacade;
/**
 * 用户登录
 * 
 */
@SuppressWarnings("serial")
public class FrontLoginAction extends BaseAction {
	Log log = LogFactory.getLog(FrontLoginAction.class);
	// 实现类方法
	private FrontLoginFacade frontloginFacade;
	// 用于返回xml文件档
	private String xml;

	// 关键是这个有bug.
	/*
	 * private String pcode,现在封装formValidator的js中变成 var parm =
	 * "clientid="+id+"&search."+id+"="+encodeURIComponent(encodeURIComponent(srcjo.val()));
	 * 2、ajaxvalidator是大家问的最多的问题，修正一个bug（感谢网友“じ龍峸√”），并把大家最关心的问题，再做一次阐述。
	 * bug现象：无论校验有没有校验通过，当控件再次得到焦点而再次失去焦点的时候tip里的提示就会停滞在得到焦点的提示 历次升级的相关记录：
	 * a、为AjaxValidator添加一个addidvalue属性(是否自动添加id和值到url参数后面)
	 * 为了修复AjaxValidator在配置信息的时候,取不到运行时候值的bug，插件将自动在url后面自动添加,形式为"id=value"的网页参数。
	 * 在服务器端,你可以通过Request.querystring["id"]来取值。 具体演示请看demo1里的用户名输入和Default.aspx
	 */
	public String execute() {
		// 用户登录
		if ("".equals(super.getAction()) || null == super.getAction()|| "index".equals(super.getAction())) {
			// 保存图片
			int i = 0;// 0:验证码有误,1:注册成功
			try {
				String sessioncode = (String) ActionContext.getContext()
						.getSession().get(Def.VERIFY_SESSION_NAME);
				if (sessioncode.equalsIgnoreCase(search.get("pverifycode")
						.toString())) {

					// 验证用户信息
					HashMap userInfo = frontloginFacade.checkLogin(search);
					if (userInfo != null) {
						// 将用户信息存放到session中.
						ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME, userInfo);
						// 测试一些数据
//						String tmp = ServletActionContext.getRequest()
//								.getHeader("User-Agent");
//						tmp = ServletActionContext.getRequest().getRemoteAddr();
						return SUCCESS;
					}
				}
			} catch (Exception e) {
				log.error(e.toString());
				i = 2;// 2:注册失败
			}
			if (i == 0) {
				this.addActionMessage(this.getText("验证码有误!"));
			} else {
				this.addActionMessage(this.getText("登录出现异常,请重试!"));
			}
			return "login";// 转到登录页面
			// 用户注册
		} else if ("regedit".equals(super.getAction())) {
			// 保存图片
			int i = 0;// 0:验证码有误,1:注册成功
			try {
				String sessioncode = (String) ActionContext.getContext()
						.getSession().get(Def.VERIFY_SESSION_NAME);
				if (sessioncode.equalsIgnoreCase(search.get("pverifycode")
						.toString())) {
					// 验证码的处理
					frontloginFacade.regedit(search);
					i = 1;// 1:注册成功
					return "login";// 转到登录页面
				}
			} catch (Exception e) {
				log.error(e.toString());
				i = 2;// 2:注册失败
			}
			if (i == 0) {
				this.addActionMessage(this.getText("验证码有误!"));
			} else {
				this.addActionMessage(this.getText("注册出现异常,请重试!"));
			}
			return "regedit";// 仍在注册页面
		} else if ("isExist".equals(super.getAction())) {
			String pcode = search.get("pcode").toString();
			try {
				/*
				 * Pattern p = Pattern.compile("\t|\r|\n"); Matcher m =
				 * p.matcher(pcode.toString());
				 * search.put("useExcuse",m.replaceAll(""));
				 */
				pcode = java.net.URLDecoder.decode(pcode, "UTF-8");
				// encodeURIComponent(encodeURIComponent(srcjo.val())); //ajax校验
				// formValidator的ajaxValid 两次编码,一次解码
				// pcode=pcode.replaceAll(" ", "");
				// pcode=new String(pcode.getBytes("gbk"),"utf-8");//编码转化
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			search.put("pcode", pcode);
			int i = frontloginFacade.isExist(search);
			// System.out.print(search.get("pcode").toString());
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}else if ("adminbottom".equals(super.getAction())) {
			search=(HashMap)ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME);
			return "adminbottom";// 仍在注册页面
		}
		return ERROR;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml
	 *            the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * @return the frontloginFacade
	 */
	public FrontLoginFacade getFrontloginFacade() {
		return frontloginFacade;
	}

	/**
	 * @param frontloginFacade the frontloginFacade to set
	 */
	public void setFrontloginFacade(FrontLoginFacade frontloginFacade) {
		this.frontloginFacade = frontloginFacade;
	}

}
