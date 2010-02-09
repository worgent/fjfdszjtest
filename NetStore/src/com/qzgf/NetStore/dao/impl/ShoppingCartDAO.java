package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IShoppingCartDAO;
import com.qzgf.NetStore.persistence.Item;
import com.qzgf.NetStore.persistence.Product;
import com.qzgf.NetStore.persistence.User;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

//购物车的实现
public class ShoppingCartDAO implements IShoppingCartDAO {
	//UtilDB du=new UtilDB();

	@SuppressWarnings("unchecked")
	public Map listProducts() {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map map = new HashMap();
		StringBuffer sql = new StringBuffer(
				"select a.productId as productId,a.productName as productName,");
		sql
				.append("a.marketPrice as marketPrice,a.memberPrice as memberPrice,a.unitId as unitId,");
		sql
				.append("b.unitName as unitName from t_product a left join t_unit b on(a.unitId=b.unitId);");
		ResultSet rs;
		try {
			rs = utilDB.executeQuery(sql.toString());
			int i = 0;
			while (rs.next()) {
				Product product = new Product();
				try {
					product.setProductId(rs.getString("productId"));
					product.setProductname(rs.getString("productName"));
					product.setMarketPrice(Float.parseFloat(rs
							.getString("marketPrice")));
					product.setMemberPrice(Float.parseFloat(rs
							.getString("memberPrice")));
					product.setUnitId(Integer.parseInt(rs.getString("unitId")));
					product.setUnitName(rs.getString("unitName"));
					map.put(i, product);
					i++;
					//
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 用于修改收件人及寄件人信息的页面时取得信息
	 */
	@SuppressWarnings("unchecked")
	public Map listOrderUserInfo(String userId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map map = new HashMap();
		StringBuffer strsql = new StringBuffer(
				"select realName,postCode,phone,cellPhone,address,receiveUserName,receivepostCode,receivephone,receivecellPhone,receiveUserAddress from t_user where userId='"
						+ userId + "'");
		ResultSet rs;

		try {
			rs = utilDB.executeQuery(strsql.toString());
			while (rs.next()) {
				map.put("userId", userId);
				map.put("realName", rs.getString("realName"));
				map.put("postCode", rs.getString("postCode"));
				map.put("phone", rs.getString("phone"));
				map.put("cellPhone", rs.getString("cellPhone"));
				map.put("address", rs.getString("address"));
				map.put("receiveUserName", rs.getString("receiveUserName"));
				map.put("receivePostCode", rs.getString("receivePostCode"));
				map.put("receivePhone", rs.getString("receivePhone"));
				map.put("receiveCellPhone", rs.getString("receiveCellPhone"));
				map.put("receiveAddress", rs.getString("receiveUserAddress"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 修改用户表里面收件人信息
	 * @param order
	 * @return
	 */
	public boolean updateUserRes(User user) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer(
				"update t_user set receiveUserName='"
						+ user.getReceiveUserName() + "',receiveUserAddress='"
						+ user.getReceiveUserAddress() + "',");
		sql.append("receivePostCode='" + user.getReceivePostCode()
				+ "',receivePhone='" + user.getReceivePhone()
				+ "',receiveCellPhone='" + user.getReceiveCellPhone() + "'");
		sql.append(" where userId='" + user.getUserId() + "'");

		boolean tf = utilDB.executeUpdate(sql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	/**
	 * 往订单表里面插入收件人信息,且修改注册用户信息
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateUserResSed(User user) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer update_user = new StringBuffer(
				"update t_user set realName='" + user.getRealName() + "',");
		update_user.append("receiveUserName='" + user.getReceiveUserName()
				+ "',receiveUserAddress='" + user.getReceiveUserAddress()
				+ "',");
		update_user.append("receivePostCode='" + user.getReceivePostCode()
				+ "',receivePhone='" + user.getReceivePhone()
				+ "',receiveCellPhone='" + user.getReceiveCellPhone() + "'");
		update_user.append(",phone='" + user.getPhone() + "',cellPhone='"
				+ user.getCellPhone() + "',address='");
		update_user.append(user.getAddress() + "',postcode='"
				+ user.getPostCode() + "' where userId='" + user.getUserId()
				+ "'");
		List sqlList = new ArrayList();
		sqlList.add(update_user);
		boolean flag = utilDB.executeUpdate(sqlList);

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 用于订单的配送方式
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List querySendMode(String userId) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer(
				"select a.sendmodeid,a.sendmodename,a.sendFee,a.sendTime ,");
		sql
				.append("case when a.sendmodeid=b.sendmodeid then 'checked' else '' end as ifChecked  from t_sendmode a left join t_user b ");
		sql.append("on(a.sendmodeid=b.sendmodeid) and b.userid='" + userId
				+ "'");

		//UtilDB du=new UtilDB();

		List sendModeList = new ArrayList();
		ResultSet rs = null;
		try {
			rs = utilDB.executeQuery(sql.toString());
			while (rs.next()) {
				Map map = new HashMap();
				map.put("sendModeId", rs.getString("sendModeId"));
				map.put("sendModeName", rs.getString("sendModeName"));
				map.put("sendFee", rs.getString("sendFee"));
				map.put("sendTime", rs.getString("sendTime"));
				map.put("ifChecked", rs.getString("ifChecked"));
				sendModeList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sendModeList;
	}

	/** 
	 * 更新配送与付款方式
	 * @param orderId
	 * @param sendModeId
	 * @param payTypeId
	 * @return
	 */
	public boolean updateSendPayMode(String userId, String sendModeId,
			String payTypeId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String update_sql = "update t_user set sendModeId='" + sendModeId
				+ "',payTypeId='" + payTypeId + "' where userId='" + userId
				+ "'";

		boolean tf = utilDB.executeUpdate(update_sql);

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tf;
	}

	/**
	 * 查询所有的付款方式
	 */
	@SuppressWarnings("unchecked")
	public List queryPayType(String userId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer strsql = new StringBuffer(
				"select a.payTypeId,a.payTypeName,a.companyName,a.openAccountName,a.bankAccount,a.remark,");
		strsql
				.append("case when a.payTypeid=b.payTypeId then 'checked' else '' end as ifChecked  from t_payType a left join t_user b ");
		strsql.append("on(a.payTypeid=b.payTypeid) and b.userid='" + userId
				+ "'");

		//UtilDB du=new UtilDB();

		List payTypeList = new ArrayList();
		ResultSet rs = null;
		try {
			rs = utilDB.executeQuery(strsql.toString());
			while (rs.next()) {
				Map map = new HashMap();
				map.put("payTypeId", rs.getString("payTypeId"));
				map.put("payTypeName", rs.getString("payTypeName"));
				map.put("companyName", rs.getString("companyName"));
				map.put("openAccountName", rs.getString("openAccountName"));
				map.put("bankAccount", rs.getString("bankAccount"));
				map.put("remark", rs.getString("remark"));
				map.put("ifChecked", rs.getString("ifChecked"));
				payTypeList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return payTypeList;
	}

	/**
	 * 列出配送的详细信息,用于订单的再次确认，可做修改
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map listSendInfo(String userId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map map = new HashMap();

		StringBuffer strsql_userInfo = new StringBuffer(
				"select a.sendModeId,d.sendModeName,a.receiveUserName as receiveName,a.receivephone as receivePhone,d.sendFee as sendFee");
		strsql_userInfo
				.append(",a.receivecellphone as receiveCellPhone,a.ReceiveuserAddress as receiveAddress,a.receivepostcode as receivePostCode ");
		strsql_userInfo.append("from t_user a,t_sendmode d where a.userId='"
				+ userId + "' and a.sendmodeid=d.sendmodeid;");
		ResultSet rs;

		try {
			rs = utilDB.executeQuery(strsql_userInfo.toString());
			while (rs.next()) {
				map.put("receiveName", rs.getString("receiveName"));
				map.put("receivePhone", rs.getString("receivePhone"));
				map.put("receiveCellPhone", rs.getString("receiveCellPhone"));
				map.put("receiveAddress", rs.getString("receiveAddress"));
				map.put("sendModeId", rs.getString("sendModeId"));
				map.put("sendModeName", rs.getString("sendModeName"));
				map.put("receivePostCode", rs.getString("receivePostCode"));
				map.put("sendFee", rs.getString("sendFee"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public boolean insertOrderDetails(HashSet<Item> items, String userId,
			float cost, String orderId) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Page pg = new Page();

		String tag1 = "dt";// 标志位 订单 
		int len = 12;// 位数
		String tableStr = "t_lsh";// 表名
		String dateField1 = "orderdetailDate";// 日期字段名
		String initField1 = "orderdetailInitValue";// 初始化值

		List sqlList = new ArrayList();
		StringBuffer insert_orders = new StringBuffer(
				"insert into t_orders(orderId,userId,allSum,orderStatus,payStatus,paytypeId,sendModeId,");
		insert_orders
				.append("receiveUserName,receiveUserAddress,receivePostCode,receivePhone,receiveCellPhone) select '");
		insert_orders.append(orderId + "','" + userId + "'," + cost + ",2,0");
		insert_orders
				.append(",payTypeId,sendModeId,receiveUserName,receiveUserAddress,receivePostCode,receivePhone,receiveCellPhone from t_user where userId='"
						+ userId + "'");
		sqlList.add(insert_orders);
		Iterator ite = items.iterator();
		items.size();
		while (ite.hasNext()) {
			Item item = (Item) ite.next();
			Product product = item.getProduct();
			StringBuffer insert_sql = new StringBuffer(
					"insert into t_orderDetails(orderItemId,orderId,unitid,productId,quantity) values('");
			insert_sql.append(pg.lshNO(tag1, len, tableStr, dateField1,
					initField1)
					+ "','" + orderId + "','" + product.getUnitId() + "','");
			insert_sql.append(product.getProductId() + "','" + item.getNumber()
					+ "')");
			sqlList.add(insert_sql.toString());
		}
		boolean flag = false;
		flag = utilDB.executeUpdate(sqlList);

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 列出付款的信息
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map listPayInfo(String orderId) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map map = new HashMap();

		StringBuffer strsql_userInfo = new StringBuffer(
				"select c.payTypeName,a.payTypeId,c.companyName,c.openAccountName,c.bankAccount,c.remark ");
		strsql_userInfo.append("from t_orders a,t_paytype c where a.orderid='"
				+ orderId + "' and a.payTypeId=c.payTypeId ");
		ResultSet rs;

		try {
			rs = utilDB.executeQuery(strsql_userInfo.toString());
			while (rs.next()) {
				map.put("payTypeName", rs.getString("payTypeName"));
				map.put("payTypeId", rs.getString("payTypeId"));
				map.put("companyName", rs.getString("companyName"));
				map.put("openAccountName", rs.getString("openAccountName"));
				map.put("bankAccount", rs.getString("bankAccount"));
				map.put("remark", rs.getString("remark"));
				map.put("orderId", orderId);
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String queryAllSum(String orderId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select allSum from t_orders where orderId='" + orderId
				+ "'";
		try {
			ResultSet rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				return rs.getString("allSum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得某一个产品的价格
	 */
	public float getProductPrice(String productId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select memberPrice from t_product where productId='"
				+ productId + "'";
		ResultSet rs;
		try {
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				return rs.getFloat("memberPrice");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获得配送费用
	 */
	public float getSendFee(String userId) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select sendFee from t_sendMode where sendModeId=(select sendModeId from t_user where userId='"
				+ userId + "')";
		ResultSet rs;
		try {
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				return rs.getFloat("sendFee");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
}
