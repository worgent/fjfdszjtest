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
package com.enation.javashop.widget.cart;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.enation.eop.context.EopContext;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.AdjunctItem;
import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.model.FreeOffer;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.model.support.AdjunctGroup;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.IGoodsAdjunctManager;
import com.enation.javashop.core.service.IProductManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.widget.cart.checkout.CheckOutWidget;
import com.enation.javashop.widget.member.AbstractMemberWidget;

/**
 * 购物车挂件
 * @author kingapex
 *2010-3-23下午03:18:58
 */
public class CartWidget extends AbstractMemberWidget {
	private  HttpServletRequest  request;
	private ICartManager cartManager;
	private CheckOutWidget checkOutWidget;
	private IPromotionManager promotionManager ;
	private IFreeOfferManager freeOfferManager;
	private IProductManager productManager ;
	private IGoodsAdjunctManager goodsAdjunctManager;
	
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		request  = ThreadContextHolder.getHttpRequest();
		String step = request.getParameter("step");
		if(step==null|| "".equals(step)){
			this.execute();
		}else if("checkout".equals(step)){
			checkOutWidget.process(params);
		}
		 
	}
	
	
	private void execute() {
		
		
		 String action = request.getParameter("action");
		  
		// this.setPageFolder("/widgets/cart");
		 
		 if("add".equals(action)){
			 this.add();
		 } 
		 if("delete".equals(action)){
			 this.delete();
		 }
		 if("clean".equals(action)){
			 this.clean();
		 } 		 
		 if("update".equals(action)){
			 this.updateNum();
		 } 		 
		 if("getTotal".equals(action)){
			 this.getTotal();
		 }
		 
		 if(action==null || action.equals("")){
			 this.list();
		 }

			
		//使用优惠卷
		if("useCoupon".equals(action)){
				Member member  = UserServiceFactory.getUserService().getCurrentMember();
				this.setPageFolder("/shop/common/");
				this.putData("jumpurl", "cart.html");
				this.setPageName("success");
				if(member== null){
					this.showError("您尚未登录不能使用优惠卷");
				}else{
					String code  = request.getParameter("coupCode");
					try{
						this.promotionManager.useCoupon(code, member.getMember_id());
						Coupons coupons=   (Coupons )ThreadContextHolder.getSessionContext().getAttribute("coupon");
						this.showSuccess("优惠卷["+coupons.getCpns_name()+"]已经成功应用于购物车中}", "购物车", "cart.html");
						
					}catch(RuntimeException e){
						e.printStackTrace();
						this.showError(e.getMessage());
					}
				}
		}
		
		if("cancelCoupon".equals(action)){
			ThreadContextHolder.getSessionContext().removeAttribute("coupon");
			this.showSuccess("优惠卷已经取消","购物车","cart.html");
	 			
		}
		
			
 
		 
		 
	}	
	
	/**
	 * 向购物车中添加货品
	 */
	private void add(){
		this.setPageName("addsuccess");
		String productid= request.getParameter("productid");
		String itemtype= request.getParameter("itemtype");
		String sessionid = request.getSession().getId();
		itemtype =itemtype == null?"0":itemtype;
		
		String num = request.getParameter("num");
		num=num==null|| num.equals("")?"1":num;
		if(productid==null || "".equals(productid)) throw new RuntimeException("productid is null");
		
		//添加赠品
		if(itemtype.equals("2")) {
			boolean r =this.addGiftToCart(Integer.valueOf( productid ), Integer.valueOf(num), sessionid);
			if(!r)return;
			
		}else{//添加商品或捆绑促销
			this.addGoodsToCart(Integer.valueOf( productid ), Integer.valueOf(num), sessionid, Integer.valueOf(itemtype));
		}
				
		 //在session中记录site信息
		 ThreadContextHolder.getSessionContext().setAttribute("site_key", EopContext.getContext().getCurrentSite());
		// this.list();
	}
	
	
	/**
	 * 将商品或捆绑商品添加到购物
	 * @param productid
	 * @param num
	 * @param sessionid
	 */
	private void addGoodsToCart(Integer productid,Integer num,String sessionid,Integer itemtype){
		
		Product product =null;
		if( itemtype.intValue()==0){
			product  =this.productManager.get(productid);
		}
		
		if(itemtype.intValue()==1){
			product = productManager.getByGoodsId(productid);
		}
		
		if(product!=null){
			Cart cart = new Cart();
			cart.setProduct_id(product.getProduct_id());
			cart.setSession_id(sessionid);
			cart.setNum(num);
			cart.setItemtype(itemtype);
			cart.setWeight(product.getWeight());
			cart.setPrice( product.getPrice() );
			cart.setName(product.getName());
			this.processAdj(cart);
			this.cartManager.add(cart);
		}
	}

	
	/**
	 * 处理挂件
	 */
	private void processAdj(Cart cart){
		String[] has_adj = this.request.getParameterValues("has_adj");

		//选择了配件
		if(has_adj!=null && has_adj.length>0){
			String name="";
			Double price=0D;
			Integer goodsid  = Integer.valueOf( request.getParameter("goodsid") );
			List<Map> adjGroupList  = goodsAdjunctManager.list(goodsid);
			List<AdjunctItem> adjList = new ArrayList<AdjunctItem>();
			for(Map adjGroup : adjGroupList) {

				Integer adjid  =(Integer)adjGroup.get("adjunct_id");
				String[] proidArray =request.getParameterValues("adj_pro_id_"+ adjid);
			
				String[] adj_nums= request.getParameterValues("adj_num_"+adjid);
				
				if(proidArray==null || proidArray.length==0) continue;
				
				for(int i=0;i<proidArray.length;i++){
					Product product =productManager.get(Integer.valueOf(proidArray[i]));
					String adjname = product.getName();
					Double adjprice =product.getPrice();
					
					String type = (String)adjGroup.get("set_price");  //折扣方式
					Double discount =((BigDecimal)adjGroup.get("price")).doubleValue();      //折扣价格
					if("discount".equals(type)){ //打折方式
						adjprice=adjprice*discount;
					}
					if("minus".equals(type)){ //直接减价
						adjprice=adjprice-discount;
					}
					adjprice=Integer.valueOf( adj_nums[i] ) *adjprice;
					
					price+=adjprice;
					name+="+"+adjname+"("+ adj_nums[i] +")";
					
					AdjunctItem adjunctItem = new AdjunctItem();
					adjunctItem.setName(name);
					adjunctItem.setNum( Integer.valueOf(adj_nums[i] ));
					adjunctItem.setGoodsid(product.getGoods_id());
					adjunctItem.setProductid(product.getProduct_id());
					adjunctItem.setPrice(product.getPrice());
					adjunctItem.setCoupPrice(adjprice);
					
					adjList.add(adjunctItem);
				}

			}
			
			cart.setName( cart.getName()+ name);
			cart.setPrice(cart.getPrice()+price);
			cart.setAddon(JSONArray.fromObject(adjList).toString());
		}		
	}
	
	/**
	 * 添加赠品至购物车
	 * @param giftid
	 * @param num
	 * @param sessionid
	 */
	private boolean addGiftToCart(Integer giftid,Integer num,String sessionid ){
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		
		 if(member==null){
				try {
					this.showError("您尚未登录不能兑换赠品","点击此处到登录页面", "member_login.html?forward="+URLEncoder.encode("cart.html?action=add&productid="+giftid+"&itemtype=2","UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return false;
			 }else{
				 FreeOffer freeOffer = this.freeOfferManager.get( Integer.valueOf(giftid ));
				if(  freeOffer.getLv_ids()==null || member.getLv_id()==null){
					 
					 this.showError("您的会员等级不能兑换此赠品","赠品列表","giftlist.html");
					 return false;
				}else{
					 String[] lvAr = freeOffer.getLv_ids().split(",");
					 if(!StringUtil.isInArray(member.getLv_id(), lvAr)){
						 this.showError("您的会员等级不能兑换此赠品","赠品列表","giftlist.html");
						 return false ;
					 }
				}
				if(member.getPoint().intValue()< freeOffer.getScore().intValue() ){
					 this.showError("您的积分不足，不能兑换此赠品","赠品列表","giftlist.html");
					 return false;
				 }
				Cart cart = new Cart();
				cart.setProduct_id(giftid);
				cart.setSession_id(sessionid);
				cart.setNum(num);
				cart.setItemtype(2);
				cart.setWeight(freeOffer.getWeight());
				cart.setName( freeOffer.getFo_name() );
				cart.setPrice(Double.valueOf( freeOffer.getScore()));
				this.cartManager.add(cart);
			 }
		 
		 	return true;

	 
	}
	
	
	/**
	 * 读取购物车中货品列表
	 */
	private void list(){
		String sessionid = request.getSession().getId();
		List goodsList = cartManager.listGoods( sessionid ); //商品列表
		List giftItemList = cartManager.listGift(sessionid); //兑换赠品列表
		List pgkItemList  = cartManager.listPgk(sessionid); //捆绑商品列表
		
		
		this.setPageName("cart");
		this.putData("goodsItemList", goodsList);
		this.putData("giftItemList", giftItemList);
		this.putData("pgkItemList", pgkItemList);
		
		if(goodsList==null|| goodsList.isEmpty()){
			this.putData("hasGoods", false);
		}else{
			this.putData("hasGoods", true);
		}
		
		if(giftItemList==null|| giftItemList.isEmpty()){
			this.putData("hasGift", false);
		}else{
			this.putData("hasGift", true);
		}
		
		if(pgkItemList==null|| pgkItemList.isEmpty()){
			this.putData("hasPgk", false);
		}else{
			this.putData("hasPgk", true);
		}		
		
		this.putData("GoodsPic",new  GoodsPicDirectiveModel());
		
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if(member==null){
			this.putData("isLogin", false);
		}else{
			this.putData("isLogin", true);
		}
		
		Coupons coupons=   (Coupons )ThreadContextHolder.getSessionContext().getAttribute("coupon");
		this.putData("coupon", coupons);
	}
	
	/**
	 * 删除购物车中一项货品
	 */
	public void delete(){
		String json;
		try{
			String cartid= request.getParameter("cartid");
			cartManager.delete(request.getSession().getId(), Integer.valueOf(cartid));
			json="{result:0}";
		}catch(RuntimeException e){
			e.printStackTrace();
			json="{result:1}";
		}
		this.putData("json", json);
		this.setPageName("cartJson");
	}
	
	public void clean(){
		String json;
		try{
			cartManager.clean(request.getSession().getId());
			json="{result:0}";
		}catch(RuntimeException e){
			e.printStackTrace();
			json="{result:1}";
		}
		this.putData("json", json);
		this.setPageName("cartJson");		
	}
	
	/**
	 * 更新某个货品的数量
	 */
	public void updateNum(){
		String json;
		try{
			String cartid= request.getParameter("cartid");
			String num= request.getParameter("num");
			cartManager.updateNum(request.getSession().getId(),  Integer.valueOf(cartid),  Integer.valueOf(num));
			json="{result:0}";
		}catch(RuntimeException e){
			e.printStackTrace();
			json="{result:1}";
		}
		this.putData("json", json);
		this.setPageName("cartJson");
	}
	
	/**
	 * 购物车的价格、优惠总计信息
	 */
	public void getTotal(){
		String sessionid  = request.getSession().getId();
		Integer point  = this.cartManager.countPoint(sessionid);	
		//商品原始总价
		Double originalTotal  =cartManager.countGoodsTotal(request.getSession().getId());
		//计算捆绑商品的总价，并加入订单总价中
		Double pgkTotal = this.cartManager.countPgkTotal(sessionid);
		originalTotal = originalTotal+pgkTotal;	
		
		//应用了商品优惠的优惠价格
		Double actualTotal = cartManager.countGoodsDiscountTotal(sessionid);
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		if(member!=null){
			List pmtList =promotionManager.list(actualTotal, member.getLv_id());
			this.putData("pmtList", pmtList);
			//应用的订单优惠的优惠价
			actualTotal= this.promotionManager.applyOrderPmt(actualTotal, 0D, point,member.getLv_id()).getOrderPrice();
		}
		
		
		this.putData("originalTotal", originalTotal);
		this.putData("actualTotal", actualTotal);
		this.setPageName("cartTotal");
	}
	
	
	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

	public CheckOutWidget getCheckOutWidget() {
		return checkOutWidget;
	}

	public void setCheckOutWidget(CheckOutWidget checkOutWidget) {
		this.checkOutWidget = checkOutWidget;
	}
	
 
	
	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}

	
	
	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}

	public static void main(String args[]){

		  NumberFormat format = new DecimalFormat("#0.00"); 
		  double a = 1.09; double b = 0.03; 
		  double d = Double.valueOf(format.format(a + b));

		  System.out.println(d);

	    }

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	public IGoodsAdjunctManager getGoodsAdjunctManager() {
		return goodsAdjunctManager;
	}

	public void setGoodsAdjunctManager(IGoodsAdjunctManager goodsAdjunctManager) {
		this.goodsAdjunctManager = goodsAdjunctManager;
	} 
	
	

}
