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
package com.enation.javashop.core.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.DoubleMapper;
import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.mapper.CartItemMapper;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IPromotionManager;

/**
 * 购物车业务实现
 * @author kingapex
 *2010-3-23下午03:30:50
 */

public class CartManager extends BaseSupport implements ICartManager {
	
	private IPromotionManager promotionManager;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Cart cart) {
		String sql ="select count(0) from cart where  product_id=? and session_id=? and itemtype=? ";
		int count = this.baseDaoSupport.queryForInt(sql, cart.getProduct_id(),cart.getSession_id(),cart.getItemtype());
		if(count>0){
			this.baseDaoSupport.execute("update cart set num=num+? where  product_id=? and session_id=? and itemtype=? ", cart.getNum(),cart.getProduct_id(),cart.getSession_id(),cart.getItemtype());
		}else{
			this.baseDaoSupport.insert("cart", cart);
		}
	}

	
	public Integer countItemNum(String sessionid) {
		String sql ="select count(0) from cart where session_id =?";
		return this.baseDaoSupport.queryForInt(sql, sessionid);
	}
	
	
	public List listGoods(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select g.goods_id,g.image_default, c.name ,  p.specs  ,g.mktprice,g.point,p.product_id,c.price,c.cart_id as cart_id,c.num as num,c.itemtype,c.addon  from "+ this.getTableName("cart") +" c,"+ this.getTableName("product") +" p,"+ this.getTableName("goods")+" g ");
		sql.append("where c.itemtype=0 and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=?");
		List<CartItem>  list  =this.daoSupport.queryForList(sql.toString(), new CartItemMapper(), sessionid);
		
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member!=null){
			this.promotionManager.applyGoodsPmt(list, member.getLv_id()); 
		}
		
		return list;
	}

	

	
	public List listPgk(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select g.sn,g.goods_id,g.image_default,c.name,g.mktprice,g.point,p.product_id,c.price,c.cart_id as cart_id,c.num as num,c.itemtype  from "+ this.getTableName("cart") +" c,"+ this.getTableName("product") +" p,"+ this.getTableName("goods")+" g ");
		sql.append("where c.itemtype=1 and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=? ");
		List<CartItem>  list  =this.daoSupport.queryForList(sql.toString(), new CartItemMapper(), sessionid);
		return list;
	}
	
	
	
	public List listGift(String sessionid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select f.fo_id as goods_id,f.list_thumb image_default,f.fo_name as name, f.price as mktprice, f.score as point,f.fo_id as product_id,f.score as price,f.limit_purchases as limitnum,c.cart_id as cart_id,c.num as num,c.itemtype  from "+ this.getTableName("cart") +" c,"+ this.getTableName("freeoffer") +" f");
		sql.append(" where c.itemtype=2 and c.product_id=f.fo_id  and c.session_id=?");
		List<CartItem>  list  =this.daoSupport.queryForList(sql.toString(), new CartItemMapper(), sessionid);
				
		return list;
	}
	
	
	
	
	public void  clean(String sessionid){
		String sql ="delete from cart where session_id=?";
		this.baseDaoSupport.execute(sql, sessionid);
	}
	

	
	public void clean(String sessionid, Integer userid, Integer siteid) {
		String sql ="delete from cart  where session_id=?";
		this.baseDaoSupport.execute(sql, sessionid);
	}
	
	
	
	public void delete(String sessionid,Integer cartid){
		String sql = "delete from cart where session_id=? and cart_id=?";
		this.baseDaoSupport.execute(sql, sessionid,cartid);
	}
	
	
	public void updateNum(String sessionid,Integer cartid,Integer num){
		 String sql ="update cart set num=? where session_id =? and cart_id=?";
		 this.baseDaoSupport.execute(sql, num,sessionid,cartid);
	}
	
	
	public Double countGoodsTotal(String sessionid){
		StringBuffer sql = new StringBuffer();
		sql.append("select sum( c.price * c.num ) as num from cart c " );
		sql.append("where  c.session_id=? and c.itemtype=0 ");
		Double price  =(Double)this.baseDaoSupport.queryForObject(sql.toString(),new DoubleMapper(), sessionid);
		return price;
	}
	
	
	
	
	public Double countPgkTotal(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum( c.price * c.num ) as num from cart c  " );
		sql.append("where c.itemtype=1  and c.session_id=?");
		Double price  =(Double)this.baseDaoSupport.queryForObject(sql.toString(),new DoubleMapper(), sessionid);
		
		return price;
	}
	
	
	public Double  countGoodsDiscountTotal(String sessionid){
		
		List<CartItem> itemList = this.listGoods(sessionid);
		
		double price =0; //计算商品促销规则优惠后的总价
		for(CartItem item: itemList){
			price+=item.getSubtotal();
		}
	  
		return  price;
	}

	
	
	public Integer countPoint(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select  sum(g.point * c.num) from "+ this.getTableName("cart") +" c,"+ this.getTableName("product") +" p,"+ this.getTableName("goods")+" g ");
		sql.append("where (c.itemtype=0  or c.itemtype=1)  and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=?");
		
		return this.daoSupport.queryForInt(sql.toString(), sessionid);
	}
	

	
	
	public Double countGoodsWeight(String sessionid) {
		StringBuffer sql = new StringBuffer("select sum( c.weight * c.num )  from cart c where c.session_id=?");
		Double weight=(Double)this.baseDaoSupport.queryForObject(sql.toString(), new DoubleMapper(),sessionid);
		return weight;
	}

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}


}
