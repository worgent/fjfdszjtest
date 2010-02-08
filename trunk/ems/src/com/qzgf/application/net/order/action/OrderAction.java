package com.qzgf.application.net.order.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.net.communicate.domain.CommunicateFacade;
import com.qzgf.application.net.order.domain.OrderFacade;
import com.qzgf.application.net.proLog.domain.ProLogFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class OrderAction extends BaseAction {
	Log log = LogFactory.getLog(OrderAction.class);
    
	private OrderFacade orderFacade;
	private CommunicateFacade communicateFacade; //发送信息到服务器
    private ProLogFacade proLogFacade;          //接收日志的记录
	private HashMap order = new HashMap(); // 商品信息
	private List orderList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回

	// 入口函数
	public String execute() {
		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("ID", "0");
			userInfo = tmp;
		}
		//初始化基本信息
		search.put("pcreate_code", userInfo.get("ID").toString());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			return "list";
		} 
		if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("order.do?action=listdetail");
			setPageList(orderFacade.findOrderPage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
		} else if ("insert".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = orderFacade.insertOrder(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		} else if ("update".equals(super.getAction())) {
			//编辑人信息
			search.put("peditor_code", userInfo.get("ID").toString());//编辑人
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = orderFacade.updateOrderById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			//修改时的处理
			HashMap map=communicateFacade.ProRequest("clientmodify",search);
			String state=map.get("STATE").toString();		
			return "list";
			//寄件
		} else if ("clientadd".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("clientadd",search);
			if(map!=null)
			{
				String state=map.get("STATE").toString();
				String remark=map.get("REMARK").toString();
				//客户要求
				map.put("pclientremark", search.get("pclientremark").toString());
				//修改单据状态
				if(state.equals("2")){
					map.put("orderingstate", "等待收件");//自动下段(已经自动安排揽收人员)
				}else if(state.equals("1")){
					map.put("orderingstate", "等待处理");//手动下段
				}else if(state.equals("0")){
					map.put("orderingstate", "寄件失败");//手动下段
				}
				if(!state.equals("0"))//失败时不更新单据状态,但更新日志
					communicateFacade.modifyBillState(map);
				//写日志
				HashMap hs=new HashMap();
				hs.put("porderid", map.get("ORDERID").toString());
				hs.put("pproname", userInfo.get("NAME").toString());//用户编号
				hs.put("pprocontent",map.get("orderingstate").toString());
				proLogFacade.insertProLog(hs);
				//与删除操作相似,返馈用户
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<result>");
				sb.append("<value>").append(state).append("</value>");
				sb.append("<remark>").append(remark).append("</remark>");
				sb.append("</result>");
				xml = sb.toString();
			}
			return "xml";
			//取消
		}else if ("clientcancel".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("clientcancel",search);
			if(map!=null)
			{
				String state=map.get("STATE").toString();
				String remark=map.get("REMARK").toString();
				//客户要求
				map.put("pclientremark", search.get("pclientremark").toString());
				//修改单据状态
				if(state.equals("1")){
					map.put("orderingstate", "客户撤单");
				}else if(state.equals("0")){
					map.put("orderingstate", "客户撤单失败");
				}
				if(!state.equals("0"))//失败时不更新单据状态,但更新日志
					communicateFacade.modifyBillState(map);
				//写日志
				HashMap hs=new HashMap();
				hs.put("porderid", map.get("ORDERID").toString());
				hs.put("pproname", userInfo.get("NAME").toString());//用户编号
				hs.put("pprocontent",map.get("orderingstate").toString());
				proLogFacade.insertProLog(hs);
				//与删除操作相似,返馈用户
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<result>");
				sb.append("<value>").append(state).append("</value>");
				sb.append("<remark>").append(remark).append("</remark>");
				sb.append("</result>");
				xml = sb.toString();
			}
			return "xml";
			//催揽
		}else if ("clienthurry".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("clienthurry",search);
			if(map!=null)
			{
				String state=map.get("STATE").toString();
				String remark=map.get("REMARK").toString();
				//客户要求
				map.put("pclientremark", search.get("pclientremark").toString());
				//修改单据状态
				if(state.equals("1")){
					map.put("orderingstate", "客户催揽");
				}else if(state.equals("0")){
					map.put("orderingstate", "客户催揽失败");
				}
				if(!state.equals("0"))//失败时不更新单据状态,但更新日志
					communicateFacade.modifyBillState(map);
				//写日志
				HashMap hs=new HashMap();
				hs.put("porderid", map.get("ORDERID").toString());//订单号
				hs.put("pproname", userInfo.get("NAME").toString());//用户编号
				hs.put("pprocontent",map.get("orderingstate").toString());//单据状态
				proLogFacade.insertProLog(hs);
				//与删除操作相似,返馈用户
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<result>");
				sb.append("<value>").append(state).append("</value>");
				sb.append("<remark>").append(remark).append("</remark>");
				sb.append("</result>");
				xml = sb.toString();
			}
			return "xml";
			//客户修改
		}else if ("clientmodify".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("clientmodify",search);
			if(map!=null)
			{
				String state=map.get("STATE").toString();
				String remark=map.get("REMARK").toString();
				//客户要求
				map.put("pclientremark", search.get("pclientremark").toString());
				//修改单据状态
				if(state.equals("1")){
					map.put("orderingstate", "修改订单");
				}else if(state.equals("0")){
					map.put("orderingstate", "修改订单失败");
				}
				if(!state.equals("0"))//失败时不更新单据状态,但更新日志
					communicateFacade.modifyBillState(map);
				//写日志
				HashMap hs=new HashMap();
				hs.put("porderid", map.get("ORDERID").toString());//订单号
				hs.put("pproname", userInfo.get("NAME").toString());//用户编号
				hs.put("pprocontent",map.get("orderingstate").toString());//单据状态
				proLogFacade.insertProLog(hs);
				//与删除操作相似,返馈用户
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<result>");
				sb.append("<value>").append(state).append("</value>");
				sb.append("<remark>").append(remark).append("</remark>");
				sb.append("</result>");
				xml = sb.toString();
			}
			return "xml";
			//保存并寄件
		}else if ("savesend".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("clientadd",search);
			String state=map.get("STATE").toString();
			return "list";
			//测试
		}else if ("test".equals(super.getAction())) {
			HashMap map=communicateFacade.ProRequest("test",search);
			String state=map.get("STATE").toString();
			return "list";
			//查看
		}else if ("view".equals(super.getAction())) {
			order=((HashMap) orderFacade.findOrder(search).get(0)); 
			String orderid=search.get("pid").toString();
			//查看操作日志
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("order.do?action=view&search.pid="+orderid);
			search.clear();
			search.put("porderid", orderid);
			setPageList(proLogFacade.findProLogPage(search, pages));
			return "view";
		} else if ("delete".equals(super.getAction())) {
			// 主表
			int i = orderFacade.deleteOrderById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
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
	 * @return the orderFacade
	 */
	public OrderFacade getOrderFacade() {
		return orderFacade;
	}



	/**
	 * @param orderFacade the orderFacade to set
	 */
	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}



	/**
	 * @return the order
	 */
	public HashMap getOrder() {
		return order;
	}



	/**
	 * @param order the order to set
	 */
	public void setOrder(HashMap order) {
		this.order = order;
	}



	/**
	 * @return the orderList
	 */
	public List getOrderList() {
		return orderList;
	}



	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List orderList) {
		this.orderList = orderList;
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
}
