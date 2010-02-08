package com.qzgf.application.net.communicate.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.qzgf.application.BaseAction;
import com.qzgf.application.net.communicate.domain.CommunicateFacade;
import com.qzgf.application.net.proLog.domain.ProLogFacade;
import com.qzgf.comm.PageList;

/**
 * 网上下单(与派揽系统之间的通信)
 * 
 * IoC方式

　　要使用IoC方式，我们首先要告诉IoC容器（Container）想取得某个对象的意愿，通过实现相应的接口做到这点

非IoC方式

　　要获得上述对象，关键Struts 2.0中com.opensymphony.xwork2.ActionContext类。我们可以通过它的静态方法getContext()获取当前Action的上下文对象。 另外，org.apache.struts2.ServletActionContext作为辅助类（Helper Class），可以帮助您快捷地获得这几个对象。
 * 
 */
@SuppressWarnings("serial")
public class CommunicateAction extends BaseAction {
	Log log = LogFactory.getLog(CommunicateAction.class);

	//采用非IOC方式,实现request与response的方法     

	//采用IOC方式:public class IoCServlet extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware 

    private CommunicateFacade communicateFacade;//接收信息的处理 
    private ProLogFacade proLogFacade;          //接收日志的记录
	private HashMap communicate = new HashMap();// 商品信息
	private List communicateList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回

	// 入口函数
	public String execute() {
		//其他
		HttpServletRequest request=ServletActionContext.getRequest(); 

		//HttpServletResponse response = ServletActionContext.getResponse();     
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			xml="123";
			return "xml";
		} 
		//揽收成功
		if ("success".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "揽收成功");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//客户取消	
		} else if ("cancel".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "客户取消");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//客户要求延时	
		} else if ("delay".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "客户延时");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//无法联系客户	
		} else if ("uncontact".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "无法联系客户");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//超范围
		} else if ("range".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		 //等待收件
		} else if ("waitreceive".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "等待收件");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//测试
		}else if ("clientadd".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				//request.setCharacterEncoding("utf-8");
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if ("clientmodify".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				//request.setCharacterEncoding("utf-8");
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if ("clientcancel".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				//request.setCharacterEncoding("utf-8");
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if ("clienthurry".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				//request.setCharacterEncoding("utf-8");
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ERROR;
	}



	/**
	 * @return the pageList
	 */
	public PageList getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * @return the communicateFacade
	 */
	public CommunicateFacade getCommunicateFacade() {
		return communicateFacade;
	}



	/**
	 * @param communicateFacade the communicateFacade to set
	 */
	public void setCommunicateFacade(CommunicateFacade communicateFacade) {
		this.communicateFacade = communicateFacade;
	}



	/**
	 * @return the proLogFacade
	 */
	public ProLogFacade getProLogFacade() {
		return proLogFacade;
	}



	/**
	 * @param proLogFacade the proLogFacade to set
	 */
	public void setProLogFacade(ProLogFacade proLogFacade) {
		this.proLogFacade = proLogFacade;
	}



	/**
	 * @return the communicate
	 */
	public HashMap getCommunicate() {
		return communicate;
	}



	/**
	 * @param communicate the communicate to set
	 */
	public void setCommunicate(HashMap communicate) {
		this.communicate = communicate;
	}



	/**
	 * @return the communicateList
	 */
	public List getCommunicateList() {
		return communicateList;
	}



	/**
	 * @param communicateList the communicateList to set
	 */
	public void setCommunicateList(List communicateList) {
		this.communicateList = communicateList;
	}
}
