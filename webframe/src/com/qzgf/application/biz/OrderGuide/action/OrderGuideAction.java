package com.qzgf.application.biz.OrderGuide.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.OrderGuide.domain.OrderGuideFacade;

/**
 * 订阅向导(重构)
 * orderGuide.do->
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class OrderGuideAction extends BaseAction {
	Log log = LogFactory.getLog(OrderGuideAction.class);
	private String orderType;
	private String classify;
	private String productname;
	
	
	private OrderGuideFacade orderGuideFacade;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String find(){
		
		if(orderType.equals("1")){
			//直接发起订阅,即用户直接对某一个向导进行订阅
			search.put("userid", "1"); //添加用户
			search.put("guideid", "1");//添加向导
			this.orderGuideFacade.insertGuide(search);
		}else if(orderType.equals("2")){
			//模糊匹配,需先经过匹配找到相应的人才能发起订阅，当然这些全部在存储过程来定
			search.put("userid", "1");
			this.orderGuideFacade.insertAvailable(search);
		}
		
		//等待对方的确认
		
		
		return "";
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public OrderGuideFacade getOrderGuideFacade() {
		return orderGuideFacade;
	}

	public void setOrderGuideFacade(OrderGuideFacade orderGuideFacade) {
		this.orderGuideFacade = orderGuideFacade;
	}





	
}
