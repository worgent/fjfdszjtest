package com.qzgf.application.biz.ConsumeAuth.action;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.ConsumeAuth.domain.ConsumeAuthFacade;

/**
 * 累积卡模块(重构)
 * auth.do->
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class ConsumeAuthAction extends BaseAction {
	Log log = LogFactory.getLog(ConsumeAuthAction.class);
	private char revertType; //调用接口的方式 1:web 2.短信 3.wap
	private String tel;//手机号
	private String cumulateCard;//累积卡号
	private double money;
	private ConsumeAuthFacade consumeAuthFacade;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	
	/**
	 * http://localhost:8088/consumeAuth.do?action=systemJudge&revertType=1&tel=1&cumulateCard=1&money=200
	 * 1.商家发送某一卡号和手机号至系统要求判断其有效性
	 * 商家根据卡号，手机号判断累积卡的有效性(卡号正确，共享用户，时间可用)
	 * @return 正确时，返回一条短信至用户的手机要求其确认
	 * 	       错误时，返回一条信息给商家，通知其错误原因：可能是卡号过期，没有此用户等
	 */
	@SuppressWarnings("unchecked")
	public String systemJudge(){
		Map map=judgeAvailible(cumulateCard,tel,money);
		String flag=(String)map.get("flag");//是否正确 0:不正确，1：正确
		if(flag.equals("0")){
			//卡不可用
			@SuppressWarnings("unused")
			String reason=(String)map.get("reason");//原因
			
			//失败时，发送信息给商家，通知商家该累积卡不能用的原因。
			return returnValue(revertType);
		}else if(flag.equals("1")){
			//卡可以用
			
			//成功时，要求用户确认，调用手机短信发送接口，发送内容时：“尊敬的***用户您好，我们接到您的累积卡购物请求，欲购买***产品，价格**元，请确认
			//请您点击http://www.183.com.cn/aaa/verity.jsp进行确认，或编辑短信”确认使用累积卡”发送到1590509****.”
			return note();
		}
		
		return returnValue(revertType);
	}
	
	/**
	 * 用户手机确认
	 * 如：我的手机号是多少，我确认要购买,购买号是多少。。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String userConfirm(){
		
		
		
		
		Map map=judgeAvailible(cumulateCard,tel,money);
		String flag=(String)map.get("flag");//是否正确 0:不正确，1：正确
		if(flag.equals("0")){
			//卡不可用
			@SuppressWarnings("unused")
			String reason=(String)map.get("reason");//原因
			
			//失败时，发送信息给商家，通知商家该累积卡不能用的原因。
			return returnValue(revertType);
		}else if(flag.equals("1")){
			//卡可以用
			
			//成功时，要求用户确认，调用手机短信发送接口，发送内容时：“尊敬的***用户您好，我们接到您的累积卡购物请求，
			//请您点击http://www.183.com.cn/aaa/verity.jsp进行确认，或编辑短信”确认使用累积卡”发送到1590509****.”
			return note();
		}
		
		return returnValue(revertType);
	}
	
	
	/*
	 *短信接口,包括发送的内容，有确认的短信信息或WAP信息
	 */
	public String note(){
		/*
		 * 
		 * 
		 * 
		 *短信接口 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		return "note";
	}
	
	/**
	 * 
	 * @param cc 累积卡号
	 * @param phone 手机号
	 * @return map:记录成功或失败的原因，是一键值对
	 */
	@SuppressWarnings("unchecked")
	public Map judgeAvailible(String cc,String phone,double money){
		//根据条件判断卡号及共享的用户是否可用
		
		
		search.put("cumulateCard", cumulateCard);//卡号
		search.put("tel", tel);//手机号
		search.put("money", money);//手机号
		String st=this.consumeAuthFacade.findAvailable(search);
		search.clear();
		if(st.equals("100")){
			search.put("flag", "0");
			search.put("reason","该卡无效!");
		}else if(st.equals("200")){
			search.put("flag", "0");
			search.put("reason","不是共享用户!");
		}else if(st.equals("300")){
			search.put("flag", "0");
			search.put("reason","该卡还没开始使用!");
		}else if(st.equals("400")){
			search.put("flag", "1");
			search.put("reason","该卡未过期!");
		}else if(st.equals("500")){
			search.put("flag", "0");
			search.put("reason","该卡已过期!");
		}
		return search;
	}
	
	/**
	 * 返回的值信息
	 * @param rt:调用接口的方式
	 * @return
	 */
	public String returnValue(char rt){
		switch(rt){
		case '1':return "web"; //web方式
		case '2':return "wap"; //wap方式
		case '3':return "note"; //短信方式
		default:return INPUT;
		}
	}
	
	public char getRevertType() {
		return revertType;
	}

	public void setRevertType(char revertType) {
		this.revertType = revertType;
	}

	public String getCumulateCard() {
		return cumulateCard;
	}

	public void setCumulateCard(String cumulateCard) {
		this.cumulateCard = cumulateCard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ConsumeAuthFacade getConsumeAuthFacade() {
		return consumeAuthFacade;
	}

	public void setConsumeAuthFacade(ConsumeAuthFacade consumeAuthFacade) {
		this.consumeAuthFacade = consumeAuthFacade;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}


	
}
