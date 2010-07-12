/**
 * 
 */
package com.apricot.app.eating.business;

import hb.net.sms.client.Client;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import weblogic.utils.NestedError;

import com.apricot.app.eating.LoginData;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.ResponseData;
import com.apricot.eating.engine.Table;
import com.apricot.eating.engine.Types;
import com.apricot.eating.util.DataUtil;
import com.apricot.webservice.bean.Orders;
import com.apricot.webservice.bean.Response;
import com.apricot.webservice.bean.Row;
import com.apricot.webservice.impl.ClientImpl;
import com.chinasms.JavaHttpSample.ChinaSms;
import com.mchange.util.PasswordUtils;

/**
 * @author Administrator
 */
public class OrderBO extends BO {
    /**
	 * 
	 */
    public OrderBO() {
	// TODO Auto-generated constructor stub
    }

    /**
     * 客户开单
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerOpen(DyncParameterMap map) throws NestedException {
	StringBuffer ext = new StringBuffer();

	// System.out.println("--"+map.getString("order_id"));
	ext.append("select count(*) from order_info where order_status='0'");
	ext.append(" and set_no=").append(map.getString("set_no", "-1"));
	System.out.println(ext.toString());
	if (getInt(ext) > 0) {
	    return getMessage("order_open_failed", null);
	}

	ext = new StringBuffer();
	ext.append("update order_info set order_status='0' ,set_no=");
	ext.append(map.getString("set_no", "-1"));
	ext.append(" ,order_time=now()");
	// ext.append(" ,order_time=DATE_FORMAT(");
	// ext.append(map.getString("now()"));
	// ext.append(" ,'%Y-%m-%d %T')");
	ext.append(" ,MAN_COUNT=PREARRANGE_MAN_COUNT");
	// ext.append(map.getString("man_count"));
	ext.append(" where order_id=").append(map.getString("order_id", "-1"));
	System.out.println(ext.toString());
	update(ext);

	ClientImpl cli = new ClientImpl(map.getString("service_ip"));
	List listSend = new ArrayList();
	Orders order = new Orders();
	// order.setArrivingTime(DataUtil.getString(obj,
	// "prearrange_order_time"));
	// order.setCancelTime(DataUtil.getString(obj, "can_order_time"));
	order.setHandleTime(DataUtil.getCurrDateTime());
	order.setOrderId(map.getString("order_id", "-1") + "");
	order.setOrderNo(map.getString("order_no", "-1"));
	order.setOrderTime(DataUtil.getCurrDateTime());
	order.setRid(map.getString("sf_id"));
	order.setSeatNo(map.getString("set_no"));
	order.setState(map.getString("order_status"));
	order.setType(map.getString("order_type"));
	order.setSqlType(1);
	order.setManCount(map.getString("man_count"));

	Row row = new Row(order, new String[] { "arrivingTime", "cancelTime", "handleTime", "orderId", "orderNo",
		"orderTime", "rid", "seatNo", "state", "type", "sqlType", "total", "manCount" }, new String[] { "f1",
		"f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12", "f13" });
	listSend.add(row);

	Response rep = cli.syncOrders(map.getString("sf_id"), "", listSend);
	if (!"1".equals(rep.getErrorCode())) {
	    map.set("id", getMax("order_info_temp", "id"));
	    map.set("rid", map.getString("sf_id"));
	    map.set("sql_type", 1);
	    Table tabTemp = getTable("order_info_temp");
	    tabTemp.setSqlType(Table.INSERT);
	    tabTemp.setParameterMap(map);
	    execute(tabTemp);
	}

	return null;
    }

    public Object customerVipModify(DyncParameterMap map) throws NestedException {
	StringBuffer ext = new StringBuffer();
	ext.append("update order_info set vip_card_no=");
	ext.append(map.getSqlString("vip_card_no", "-1"));
	ext.append(" where order_no=");
	ext.append(map.getSqlString("order_no", "-1"));
	update(ext);
	return null;
    }

    public Object orderModify(DyncParameterMap map) throws NestedException {
	StringBuffer ext = new StringBuffer();

	ext = new StringBuffer();
	ext.append("update order_info set record_staff_id=");
	ext.append(map.getSqlString("staff_id", "-1"));
	ext.append(" ,can_order_time='");
	ext.append(DataUtil.getCurrDateTime());
	ext.append("'");
	ext.append(" ,order_status='3'");

	if (!DataUtil.isNull(map.getString("order_reseon1"))) {
	    ext.append(" ,order_reseon=");
	    ext.append(map.getSqlString("order_reseon1"));
	} else {
	    ext.append(" ,order_reseon=");
	    ext.append(map.getSqlString("order_reseon2"));
	}

	ext.append(" where order_id=").append(map.getSqlString("order_id", "-1"));
	update(ext);

	ClientImpl cli = new ClientImpl(map.getString("service_ip"));
	List listSend = new ArrayList();
	Orders order = new Orders();
	// order.setArrivingTime(DataUtil.getString(obj,
	// "prearrange_order_time"));
	order.setCancelTime(DataUtil.getCurrDateTime());
	order.setHandleTime(DataUtil.getCurrDateTime());
	order.setOrderId(map.getString("order_id", "-1") + "");
	order.setOrderNo(map.getString("order_no"));
	order.setOrderTime(DataUtil.getCurrDateTime());
	order.setRid(map.getString("sf_id"));
	order.setSeatNo(map.getString("set_no"));
	order.setState(3 + "");
	order.setType(map.getString("order_type"));
	order.setSqlType(2);
	order.setManCount(map.getString("man_count"));

	Row row = new Row(order, new String[] { "arrivingTime", "cancelTime", "handleTime", "orderId", "orderNo",
		"orderTime", "rid", "seatNo", "state", "type", "sqlType", "total", "manCount" }, new String[] { "f1",
		"f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12", "f13" });
	listSend.add(row);

	Response rep = cli.syncOrders(map.getString("sf_id"), "", listSend);
	if (!"1".equals(rep.getErrorCode())) {
	    map.set("id", getMax("order_info_temp", "id"));
	    map.set("rid", map.getString("sf_id"));
	    map.set("sql_type", 2);
	    Table tabTemp = getTable("order_info_temp");
	    tabTemp.setSqlType(Table.INSERT);
	    tabTemp.setParameterMap(map);
	    execute(tabTemp);
	}

	return null;
    }

    /**
     * 订单修改
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerEdit(DyncParameterMap map) throws NestedException {
	StringBuffer ext = new StringBuffer();
	// ext.append("select count(*) from order_info where order_status='0'");
	// ext.append(" and set_no=").append(map.getString("set_no", "-1"));
	//
	// if (getInt(ext) > 0) {
	// return getMessage("order_open_failed", null);
	// }

	ext = new StringBuffer();
	ext.append("update order_info set set_no=");
	ext.append(map.getString("set_no", "-1"));
	ext.append(" ,PREARRANGE_NAME='" + map.getString("prearrange_name") + "'");
	ext.append(" ,PREARRANGE_PHONE='" + map.getString("prearrange_phone") + "'");
	if (!("".equals(map.getString("prearrange_order_time")))) {
	    ext.append(" ,PREARRANGE_ORDER_TIME=DATE_FORMAT('" + map.getString("prearrange_order_time")
		    + "','%Y-%m-%d %T')");
	}
	if (!("".equals(map.getString("prearrange_man_count")))) {
	    ext.append(" ,PREARRANGE_MAN_COUNT=" + map.getString("prearrange_man_count"));
	}
	if (!("".equals(map.getString("introducer")))) {
	    ext.append(" ,introducer=" + map.getString("introducer"));
	}
	if (!("".equals(map.getSqlString("vip_card_no")))) {
	    ext.append(" ,VIP_CARD_NO=" + map.getSqlString("vip_card_no"));
	}
	ext.append(" ,balcony_name='" + map.getString("balcony_name") + "'");
	ext.append(" ,MINCOST_TYPE=" + map.getString("mincost_type", 0));
	ext.append(" ,MINCOST_MONEY=" + map.getString("mincost_money"));
	ext.append(" ,cum_point=" + map.getString("cum_point", 0));
	if (!("".equals(map.getString("order_time")))) {
	    ext.append(" ,order_time=DATE_FORMAT('");
	    ext.append(map.getString("order_time"));
	    ext.append("' ,'%Y-%m-%d %T')");
	}
	ext.append(" ,MAN_COUNT=");
	ext.append(map.getString("man_count"));
	ext.append(" ,UPDATE_ORDER_TIME = now()");
	ext.append(" where order_id=").append(map.getString("order_id", "-1"));
	System.out.println(ext);
	update(ext);

	ClientImpl cli = new ClientImpl(map.getString("service_ip"));
	List listSend = new ArrayList();
	Orders order = new Orders();
	// order.setArrivingTime(DataUtil.getString(obj,
	// "prearrange_order_time"));
	// order.setCancelTime(DataUtil.getString(obj, "can_order_time"));
	order.setHandleTime(DataUtil.getCurrDateTime());
	order.setOrderId(map.getString("order_id", "-1") + "");
	order.setOrderNo(map.getString("order_no"));
	order.setOrderTime(DataUtil.getCurrDateTime());
	order.setRid(map.getString("sf_id"));
	order.setSeatNo(map.getString("set_no"));
	order.setState(map.getString("order_status"));
	order.setType(map.getString("order_type"));
	order.setSqlType(2);
	order.setManCount(map.getString("man_count"));

	Row row = new Row(order, new String[] { "arrivingTime", "cancelTime", "handleTime", "orderId", "orderNo",
		"orderTime", "rid", "seatNo", "state", "type", "sqlType", "total", "manCount" }, new String[] { "f1",
		"f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12", "f13" });
	listSend.add(row);

	Response rep = cli.syncOrders(map.getString("sf_id"), "", listSend);
	if (!"1".equals(rep.getErrorCode())) {
	    map.set("sql_type", 2);
	    map.set("id", getMax("order_info_temp", "id"));
	    map.set("rid", map.getString("sf_id"));
	    Table tabTemp = getTable("order_info_temp");
	    tabTemp.setSqlType(Table.INSERT);
	    tabTemp.setParameterMap(map);
	    execute(tabTemp);
	}

	return null;
    }

    /**
     * 获取适合订单人数的座位
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object getPreOrderSet(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select set_no,dining_floor,balcony_code,");
	sql.append("belong_to,balcony_name,set_max,{0} as man_num");
	sql.append(" from dining_set_info t1 where set_status='1' ");
	sql.append("and not exists(select order_id from order_info t2");
	sql.append(" where t2.set_no=t1.set_no and order_status='0') ");
	sql.append("order by t1.set_max desc");

	setRuntimeStaticData("dining_set_info");
	System.out.println(MessageFormat.format(sql.toString(), new String[] { map.getString("man_num") }));
	return showPages(MessageFormat.format(sql.toString(), new String[] { map.getString("man_num") }), 0, -1);
    }

    public Object getPreOrderSet2(DyncParameterMap map) throws NestedException {

	StringBuffer sql = new StringBuffer();
	sql.append("select t1.set_no,t1.dining_floor,t1.balcony_code,");
	sql.append("t1.belong_to,t1.balcony_name,t1.set_max,t1.mincost_type,t1.mincost_money ");
	sql.append(" from dining_set_info t1 where t1.set_status='1'  ");
	sql.append("and t1.set_no not in (select set_no from order_info t2");
	sql.append(" where (t2.order_status='2' ");
	sql.append(" and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('");
	sql.append(map.getString("P_time"));
	sql.append("' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('");
	sql.append(map.getString("P_time"));
	// sql.append("' ,'%Y-%m-%d %T')) or t2.order_status='0') or t1.set_no='"+map.getString("set_no")+"'");
	sql.append("' ,'%Y-%m-%d %T')) or t2.order_status='0')");
	sql.append(" order by t1.set_max desc");
	setRuntimeStaticData("dining_set_info");
	// DataSet ds =
	// showPages(MessageFormat.format(sql.toString(),map.getString("P_time"),map.getString("P_time")),
	// 0, -1);;
	// return ds;
	return showPages(sql.toString(), 0, -1);
    }

    public Object getPreOrderSet3(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.set_no,t1.dining_floor,t1.balcony_code,");
	sql.append("t1.belong_to,t1.balcony_name,t1.set_max");
	sql.append(" from dining_set_info t1 ");
	sql.append("where set_status='1' and t1.set_no=");
	sql.append(map.getString("set_no", "-1"));
	sql.append(" order by t1.set_max desc");

	setRuntimeStaticData("dining_set_info");
	return showPages(sql.toString(), 0, -1);
    }

    /**
     * 客户退菜
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerUploadFood(DyncParameterMap map) throws NestedException {
	String sql = "update order_list set serving_flag='1' where food_action in ('1','2') and serving_flag='0' and order_list_id={0}";

	List a = (List) map.get("food_list");

	for (Iterator os = a.iterator(); os.hasNext();) {
	    Object o = os.next();
	    update(new StringBuffer(MessageFormat.format(sql, new String[] { DataUtil.getString(o, "order_list_id") })));
	}
	return null;
    }

    /**
     * 客户点菜
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerOrderFood(DyncParameterMap map) throws NestedException {
	String[] properties = new String[] { "order_id", "food_id", "food_num", "food_price" };
	int len = map.getMaxSize(properties);
	for (int i = 0; i < len; i++) {
	    StringBuffer strSql = new StringBuffer();
	    strSql
		    .append("insert into order_list (order_list_id,order_id,food_id,food_count,food_price,food_action,SERVING_FLAG) "
			    + " values ("
			    + getMax("order_list", "order_list_id")
			    + ","
			    + map.getString("order_id", i)
			    + ","
			    + map.getString("food_id", i)
			    + ","
			    + map.getString("food_num", i)
			    + ","
			    + map.getString("food_price", i) + ",1,1);");
	    update(strSql);
	}
	// updateBatch(strSql,map,new
	// String[]{"order_id","food_id","food_num","food_price","food_action","SERVING_FLAG"},new
	// int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.INTEGER,Types.INTEGER});
	return null;
    }

    /**
     * 客户退菜
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerCancelFood(DyncParameterMap map) throws NestedException {
	String sql = "update order_list set food_action='2' ,print_flag='0',food_return_type={1},food_return_reseon={2} where serving_flag='0' and order_list_id={0}";

	List a = (List) map.get("food_list");

	for (Iterator os = a.iterator(); os.hasNext();) {
	    Object o = os.next();
	    System.out.println(new StringBuffer(MessageFormat.format(sql, new String[] {
		    DataUtil.getString(o, "order_list_id"), map.getSqlString("food_return_type"),
		    map.getSqlString("food_return_reseon") })));

	    update(new StringBuffer(MessageFormat.format(sql, new String[] { DataUtil.getString(o, "order_list_id"),
		    map.getSqlString("food_return_type"), map.getSqlString("food_return_reseon") })));
	}
	return null;
    }

    /**
     * 报损
     * 
     * @xuli
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerDisFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select * from staff_info where accounts_per=1 and STAFF_CODE=" + map.getSqlString("loginname"));
	Object o = get(sql);
	if (o == null)
	    return ResponseData.createFailure("not_exist_staff_right", null);
	try {
	    String password = PasswordUtils.deCodeValue(DataUtil.getString(o, "staff_password"));
	    if (!password.equals(map.getString("password"))) {
		return ResponseData.createFailure("no_match_password", null);
	    } else {
		String sql1 = "update order_list set modify_staff_name='"
			+ map.getSqlString("loginname")
			+ "' ,food_action='0' ,food_return_type={1},food_return_reseon={2},serving_flag='3' where order_list_id={0}";

		List a = (List) map.get("food_list");

		for (Iterator os = a.iterator(); os.hasNext();) {
		    Object oo = os.next();
		    System.out.println(new StringBuffer(MessageFormat.format(sql1, new String[] {
			    DataUtil.getString(oo, "order_list_id"), map.getSqlString("food_return_type"),
			    map.getSqlString("food_return_reseon") })));

		    update(new StringBuffer(MessageFormat.format(sql1, new String[] {
			    DataUtil.getString(oo, "order_list_id"), map.getSqlString("food_return_type"),
			    map.getSqlString("food_return_reseon") })));
		}
	    }
	} catch (Exception ex) {

	}
	return null;
    }

    /**
     * 获取客户还未上菜的菜品列表
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object getOrderListBySetNO(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.order_list_id,t1.food_id,");
	sql.append("t1.food_count as food_num,t1.serving_flag ,t2.food_name,");
	sql.append("t1.food_price from order_list t1,food_info t2");
	sql.append(" where t1.food_id=t2.food_id and t1.order_id=");
	sql.append(map.getString("order_id", "-1"));
	sql.append(" and t1.food_action in ('1','4') and t1.serving_flag='0'");
	return showPages(sql.toString(), 0, -1);
    }

    /**
     * 获取客户所有菜品列表
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object getOrderAllListBySetNO(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.order_list_id,t1.food_id,");
	sql.append("t1.food_count as food_num,t1.serving_flag ,t2.food_name,");
	sql.append("t1.food_price from order_list t1,food_info t2");
	sql.append(" where t1.food_id=t2.food_id and t1.order_id=");
	sql.append(map.getString("order_id", "-1"));
	// sql.append(" and t1.food_action in ('1') and t1.serving_flag='0'");
	setRuntimeStaticData("order_list");
	return showPages(sql.toString(), 0, -1);
    }

    /**
     * 客户下单
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object customerOrder(DyncParameterMap map) throws NestedException {
	if (map.isNull("set_no")) {
	    return "没有选择座位,请确认操作.";
	}
	String[] setNo = map.getString("set_no").split("[,]");
	String orderNo = getOrderNo();
	int pst = map.getInt("print_starttime");
	map.set("print_starttime", DataUtil.getLastMinutesDateTime(pst));

	for (int i = 0; i < setNo.length; i++) {
	    // 判断是否存在当天的订单没有
	    StringBuffer sql = new StringBuffer();
	    sql.append("select count(*) from order_info where ");
	    sql.append("order_status='0' and set_no=");
	    sql.append(setNo[i]);

	    if (getInt(sql) > 0) {
		return "当前座位还有未结帐的订单,请确认是否遗漏?";
	    }
	    if (i > 0)
		map.set("man_count", "0");
	    map.set("set_no", setNo[i]);
	    map.set("order_id", getMax("order_info", "order_id"));
	    map.set("order_no", orderNo);
	    map.set("order_status", "0");
	    map.set("order_time", DataUtil.getCurrDateTime());
	    map.set("send_time", DataUtil.getCurrDateTime());
	    map.set("has_invoice", "0");
	    map.set("order_type", "0");
	    map.set("record_staff_id", map.getString("staff_id"));
	    map.set("cum_point", map.getString("cum_point"));
	    Table tab = getTable("order_info");
	    tab.setSqlType(Table.INSERT);
	    tab.setParameterMap(map);
	    execute(tab);

	    ClientImpl cli = new ClientImpl(map.getString("service_ip"));
	    List listSend = new ArrayList();
	    Orders order = new Orders();
	    order.setArrivingTime(DataUtil.getCurrDateTime());
	    // order.setCancelTime(DataUtil.getString(obj, "can_order_time"));
	    order.setHandleTime(DataUtil.getCurrDateTime());
	    order.setOrderId(map.getString("order_id", "-1") + "");
	    order.setOrderNo(orderNo);

	    order.setOrderTime(DataUtil.getCurrDateTime());
	    order.setRid(map.getString("sf_id"));
	    order.setSeatNo(map.getString("set_no"));
	    order.setState(map.getString("order_status"));
	    order.setType(map.getString("order_type"));
	    order.setSqlType(1);

	    Row row = new Row(order, new String[] { "arrivingTime", "cancelTime", "handleTime", "orderId", "orderNo",
		    "orderTime", "rid", "seatNo", "state", "type", "sqlType", "total" }, new String[] { "f1", "f2",
		    "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12" });
	    listSend.add(row);

	    Response rep = cli.syncOrdersAdd(map.getString("sf_id"), "", listSend);
	    if (!"1".equals(rep.getErrorCode())) {
		map.set("id", getMax("order_info_temp", "id"));
		map.set("rid", map.getString("sf_id"));
		map.set("sql_type", 1);
		Table tabTemp = getTable("order_info_temp");
		tabTemp.setSqlType(Table.INSERT);
		tabTemp.setParameterMap(map);
		execute(tabTemp);
	    }
	}

	return null;
    }

    public Object getOrderDetails(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.*,t2.food_name from ");
	sql.append("order_list t1,food_info t2 where ");
	sql.append("t1.food_id=t2.food_id and t1.order_id=");
	sql.append(map.getString("order_id", "-1"));
	setRuntimeStaticData("order_list");
	return getAll(sql);
    }

    public Object getValidOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.*,t2.staff_name from ");
	sql
		.append("order_info t1 left join staff_info t2 on(t1.RECORD_STAFF_ID=t2.staff_id) where t1.pay_status='1' and 1=1 ");

	if (!DataUtil.isNull(map.getString("order_type"))) {
	    sql.append("and order_type='");
	    sql.append(map.getString("order_type"));
	    sql.append("' ");
	}
	if (!DataUtil.isNull(map.getString("order_status"))) {
	    sql.append("and order_status='");
	    sql.append(map.getString("order_status"));
	    sql.append("' ");
	} else {
	    sql.append(" and order_status not in ('1','3')");
	}
	if (!map.isNull("starttime")) {
	    sql.append("and t1.OPERATE_ORDER_TIME>=");
	    sql.append(DataUtil.toSqlDateTime(map.getString("starttime")));
	}
	if (!map.isNull("endtime")) {
	    sql.append("and t1.OPERATE_ORDER_TIME<=");
	    sql.append(DataUtil.toSqlDateTime(map.getString("endtime")));
	}
	if (!map.isNull("balcony_name")) {
	    sql.append(" and balcony_name='");
	    sql.append(map.getString("balcony_name"));
	    sql.append("' ");
	}
	if (!map.isNull("prearrange_name")) {
	    sql.append(" and prearrange_name='");
	    sql.append(map.getString("prearrange_name"));
	    sql.append("' ");
	}
	if (!map.isNull("prearrange_phone")) {
	    sql.append(" and prearrange_phone='");
	    sql.append(map.getString("prearrange_phone"));
	    sql.append("' ");
	}
	if (!map.isNull("can_order_time")) {
	    sql.append(" and can_order_time='");
	    sql.append(map.getString("can_order_time"));
	    sql.append("' ");
	}
	if (!map.isNull("order_time")) {
	    sql.append(" and order_time='");
	    sql.append(map.getString("order_time"));
	    sql.append("' ");
	}
	sql.append(" and t1.sf_id=");
	sql.append(map.getString("sf_id", "-1"));
	sql.append(" order by OPERATE_ORDER_TIME DESC");
	setRuntimeStaticData("order_info");
	// System.out.println("orderBOsql.getValidOrders()"+sql.toString());
	return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
    }

    // 充帐订单查询
    public Object getWriteOffsOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.*,t2.staff_name,t3.total,t3.pay_total,t3.pay_id from ");
	sql
		.append("order_info t1 left join staff_info t2 on(t1.RECORD_STAFF_ID=t2.staff_id) left join order_payoff_info t3 on(t1.order_no=t3.order_no) where t1.pay_status='1' and 1=1 ");
	sql.append("and order_type='5'");
	if (!map.isNull("prearrange_name")) {
	    sql.append(" and prearrange_name='");
	    sql.append(map.getString("prearrange_name"));
	    sql.append("' ");
	}
	if (!map.isNull("prearrange_phone")) {
	    sql.append(" and prearrange_phone='");
	    sql.append(map.getString("prearrange_phone"));
	    sql.append("' ");
	}
	sql.append(" and t1.sf_id=");
	sql.append(map.getString("sf_id", "-1"));
	sql.append(" order by OPERATE_ORDER_TIME DESC");
	setRuntimeStaticData("order_info");
	return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
    }

    // 新增
    public Object addWriteOffsOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select * from staff_info where accounts_per=1 and STAFF_CODE=" + map.getSqlString("loginname"));
	Object o = get(sql);
	if (o == null)
	    return ResponseData.createFailure("not_exist_staff_right", null);
	try {
	    String password = PasswordUtils.deCodeValue(DataUtil.getString(o, "accounts_password"));
	    if (!password.equals(map.getString("password"))) {
		return ResponseData.createFailure("no_match_password", null);
	    } else {
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(nowDate.getTime());
		Table tab = getTable("order_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		long primaryKey = getMax("order_info", "order_id");
		map.set("order_no", getOrderNo());
		map.set("order_id", primaryKey);
		map.set("order_type", "5");
		map.set("order_status", "1");
		map.set("service_staff_id", DataUtil.getString(o, "staff_id"));
		map.set("record_staff_id", map.getString("staff_id"));
		map.set("order_time", nowTime);
		map.set("operate_order_time", nowTime);
		execute(tab);

		tab = getTable("order_payoff_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("pay_id", getMax("order_payoff_info", "pay_id"));
		map.set("pay_total", map.getString("total"));
		map.set("fact_pay_total", map.getString("total"));
		map.set("fact_point", map.getString("gain_point"));
		map.set("pay_time", nowTime);
		execute(tab);

		sql = new StringBuffer();
		sql.append("update customer_info set cum_point=(cum_point+" + map.getString("gain_point")
			+ ") where vip_no='" + map.getString("vip_card_no") + "'");
		update(sql);
		return null;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }

    public Object modifyWriteOffsOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select * from staff_info where accounts_per=1 and STAFF_CODE=" + map.getSqlString("loginname"));
	Object o = get(sql);
	if (o == null)
	    return ResponseData.createFailure("not_exist_staff_right", null);
	try {
	    String password = PasswordUtils.deCodeValue(DataUtil.getString(o, "accounts_password"));
	    if (!password.equals(map.getString("password"))) {
		return ResponseData.createFailure("no_match_password", null);
	    } else {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		String nowTime = sdf.format(nowDate.getTime());
		Table tab = getTable("order_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		map.set("order_time", nowTime);
		map.set("operate_order_time", nowTime);
		execute(tab);
		tab = getTable("order_payoff_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		map.set("pay_total", map.getString("total"));
		map.set("fact_pay_total", map.getString("total"));
		map.set("fact_point", map.getString("gain_point"));
		map.set("pay_time", nowTime);
		execute(tab);
		return null;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }

    public Object deleteWriteOffsOrders(DyncParameterMap map) throws NestedException {

	Table tab = getTable("order_info");
	tab.setParameterMap(map);
	tab.setSqlType(Table.DELETE);
	execute(tab);
	tab = getTable("order_payoff_info");
	tab.setParameterMap(map);
	tab.setSqlType(Table.DELETE);
	execute(tab);
	return null;
    }

    private String getOrderNo() {
	StringBuffer orderNo = new StringBuffer("F");
	orderNo.append(DataUtil.format(new Date(), "yyyyMMddHHmm"));
	orderNo.append(DataUtil.lpad(String.valueOf(new Random().nextInt(999)), '0', 3));

	return orderNo.toString();
    }

    public Object order(DyncParameterMap map) throws NestedException {
	StringBuffer orderNo = new StringBuffer("F");
	orderNo.append(DataUtil.format(new Date(), "yyyyMMddHHmm"));
	orderNo.append(DataUtil.lpad(String.valueOf(new Random().nextInt(999)), '0', 3));
	map.set("order_no", orderNo.toString());
	map.set("record_staff_id", map.getString("staff_id"));
	map.set("balcony_name", map.getString("balcony_name"));
	// get order_id;
	map.set("order_id", String.valueOf(getMax("order_info", "order_id")));
	map.set("has_invoice", "0");
	map.set("send_time", DataUtil.getCurrDateTime());
	// map.set("order_time", DataUtil.getCurrDateTime());
	map.set("pay_status", "1");

	// insert into order_info
	Table tab = getTable("order_info");
	tab.setParameterMap(map);
	tab.setSqlType(Table.INSERT);
	execute(tab);

	// insert into
	tab = getTable("order_deal_log");
	tab.setParameterMap(map);
	tab.setSqlType(Table.INSERT);
	map.set("deal_status", "00");
	map.set("deal_time", DataUtil.getCurrDateTime());
	map.set("log_id", String.valueOf(getMax("order_deal_log", "log_id")));
	execute(tab);

	// WS
	ClientImpl cli = new ClientImpl(map.getString("service_ip"));
	List listSend = new ArrayList();
	Orders order = new Orders();
	// order.setArrivingTime(DataUtil.getString(obj,
	// "prearrange_order_time"));
	// order.setCancelTime(DataUtil.getString(obj, "can_order_time"));
	order.setHandleTime(DataUtil.getCurrDateTime());
	order.setOrderId(String.valueOf(getMax("order_info", "order_id")));
	order.setOrderNo(orderNo.toString());
	// order.setOrderTime(DataUtil.getCurrDateTime());
	order.setRid(map.getString("sf_id"));
	order.setSeatNo(map.getString("set_no"));
	order.setState(map.getString("order_status"));
	order.setType(map.getString("order_type"));
	order.setSqlType(1);

	Row row = new Row(order, new String[] { "arrivingTime", "cancelTime", "handleTime", "orderId", "orderNo",
		"orderTime", "rid", "seatNo", "state", "type", "sqlType", "total" }, new String[] { "f1", "f2", "f3",
		"f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12" });
	listSend.add(row);

	Response rep = cli.syncOrdersAdd(map.getString("sf_id"), "", listSend);
	if (!"1".equals(rep.getErrorCode())) {
	    map.set("id", getMax("order_info_temp", "id"));
	    map.set("rid", map.getString("sf_id"));
	    map.set("sql_type", 1);
	    Table tabTemp = getTable("order_info_temp");
	    tabTemp.setSqlType(Table.INSERT);
	    tabTemp.setParameterMap(map);
	    execute(tabTemp);
	}
	System.out.println("status" + map.getString("sms_status", -1) + "~~");
	if (Integer.parseInt(map.getString("sms_status", -1)) == 1) {
	    String str; // 存放调用返回的结果,判断成功与否应该分析这个字符串
	    ChinaSms sms = new ChinaSms("jux3", "888888"); // 这里修改成你自己的用户名和密码
	    str = sms.singleSend("13657912707", "您已成功预订" + map.getString("sf_name") + "的，"
		    + map.getString("balcony_name") + "位置"); // 这里修改成要发送的手机号码和发送内容
	    System.out.println(str);
	    String newstring = null;
	    try {
		newstring = new String(str.getBytes("GBK"), "GB2312");
		System.out.println(newstring);
	    } catch (Exception ex) {
		System.out.println("异常");
	    }
	}
	return null;
    }

    public Object addOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select staff_name from ");
	sql.append("staff_info where staff_id=");
	sql.append(map.getString("staff_id"));
	Object o = get(sql);
	map.set("modify_staff_id", map.getString("staff_id"));
	Table tab = getTable("order_list");
	tab.setParameterMap(map);
	tab.setSqlType(Table.INSERT);
	map.set("food_action", "1");
	map.set("modify_staff_name", DataUtil.getString(o, "staff_name"));
	map.set("serving_flag", "0");
	map.set("food_discount", "100");
	map.set("order_list_id", String.valueOf(getMax("order_list", "order_list_id")));
	execute(tab);
	return null;
    }

    public Object addOrderFoodCheck(DyncParameterMap map) throws NestedException {
	HashMap m = new HashMap();
	String message = "";
	String flag = "";
	String foodId = map.getString("food_id");
	StringBuffer sql = new StringBuffer();
	sql
		.append("select t1.*,t2.mat_name from food_material_info t1 left join material_info t2 on (t1.mat_id=t2.mat_id) where food_id="
			+ foodId);
	List list = getAll(sql);
	for (int i = 0; i < list.size(); i++) {
	    Object o = (Object) list.get(i);
	    sql = new StringBuffer();
	    sql.append("select (stog_count-sale_total) as ttttt from check_detail where mat_id="
		    + DataUtil.getObject(o, "mat_id") + " group by mat_id");
	    Object oo = get(sql);
	    double temp = Double.parseDouble((String) DataUtil.getObject(oo, "ttttt"));

	    if (temp < 40) {
		message = message + DataUtil.getObject(o, "mat_name") + "仓库仅剩" + temp + ";";
		flag = "YES";
	    }
	}
	m.put("Message", message);
	m.put("flag", flag);
	return m;
    }

    public Object getOrderList(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.*,t2.food_name from ");
	sql.append("order_list t1,food_info t2 where ");
	sql.append("t1.food_id=t2.food_id and t1.order_id=");
	sql.append(map.getString("order_id", "-1"));
	setRuntimeStaticData("order_list");
	return showPages(sql.toString(), map.getStartRows(), -1);
    }

    public Object cancelOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("update order_list set food_action='9' where ");
	sql.append("food_action in('1','4') and serving_flag='0' and order_list_id=");
	sql.append(map.getString("order_list_id", "-1"));
	update(sql);
	return null;
    }

    public Object reminderOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql
		.append("update order_list set food_action='4',print_flag='0',REMINDER_TIMES=ifnull(REMINDER_TIMES,0)+1 where ");
	sql.append("food_action in('1','4') and serving_flag='0' and order_list_id in(");
	sql.append(map.getString("order_list_id", "-1")).append(")");

	update(sql);
	return null;
    }

    public Object reprintOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("update order_list set print_flag='0' where ");
	sql.append("food_action in('1','4') and serving_flag='0' and order_list_id in(");
	sql.append(map.getString("order_list_id", "-1")).append(")");

	update(sql);
	return null;
    }

    public Object reprintCustomerOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("update order_list set cust_print_flag='0' ");
	sql.append("where ");
	sql.append("food_action in('1','2','4')  and order_id in(");

	sql.append(DataUtil.nvl(map.getJoinString("order_id", ",", null), "-1")).append(")");

	update(sql);
	return null;
    }

    /**
     * Return food
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object returnOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("update order_list set food_action='2',");
	sql.append("FOOD_RETURN_RESEON='");
	sql.append(map.getString("food_return_reseon"));
	sql.append("',food_return_type='");
	sql.append(map.getString("food_return_type"));
	sql.append("' where ");
	sql.append("food_action in('1','4') and serving_flag='0' and order_list_id=");
	sql.append(map.getString("order_list_id", "-1"));
	update(sql);
	return null;
    }

    public Object servOrderFood(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("update order_list set serving_flag='1' where ");
	sql.append("order_list_id=? and serving_flag='0' and food_action='1'");

	updateBatch(sql, map, new String[] { "order_list_id" }, new int[] { Types.LONG });
	sql = new StringBuffer();
	sql.append("select * from food_material_info where food_id=" + map.getString("food_id"));
	System.out.println(sql);
	List list = getAll(sql);
	for (int i = 0; i < list.size(); i++) {
	    Object o = (Object) list.get(i);
	    sql = new StringBuffer();
	    sql.append("update check_detail set SALE_TOTAL=(SALE_TOTAL+(select MAT_TOTAL  from ");
	    sql.append("food_material_info where food_id=" + map.getString("food_id") + " and mat_id="
		    + DataUtil.getObject(o, "mat_id") + "))");
	    sql.append("where mat_id=" + DataUtil.getObject(o, "mat_id"));
	    System.out.println(update(sql) + "" + sql);
	}
	return null;
    }

    public Object getOnOrder(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	String keyword = map.getString("keyword");
	sql.append("select t1.*,t2.staff_name as staff_name from ");
	sql
		.append("order_info t1 left join staff_info t2 on(t1.record_staff_id=t2.staff_id) where t1.order_status='0' ");
	// sql.append("order_info t1,staff_info t2 where t1.service_staff_id=");
	// sql.append("t2.staff_id and t1.order_status='0' ");
	// sql.append(" and t1.order_time>=");
	// sql.append(DataUtil.toSqlDateTime(map.getString("starttime", DataUtil
	// .getCurrDateTime("yyyy-MM-dd 00:00:00"))));
	sql.append(" and t1.order_time<=");
	sql.append(DataUtil.toSqlDateTime(DataUtil.getCurrDateTime("yyyy-MM-dd 23:59:59")));
	// if (!map.isNull("set_no")) {
	// sql.append(" and set_no in (").append(map.getString("set_no", "-1"));
	// sql.append(") ");
	if (keyword != null && !("".equals(keyword))) {
	    sql.append(" and (t1.PREARRANGE_PHONE like '%" + map.getString("keyword") + "%' or t1.VIP_CARD_NO like '%"
		    + map.getString("keyword") + "%') ");
	}
	sql.append(" and t1.sf_id=");
	sql.append(map.getString("sf_id", "-1"));
	// }
	// System.out.println(sql);
	setRuntimeStaticData("order_info");

	return showPages(sql.toString(), 0, -1);
    }

    public Object getPreOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select * from ");
	sql.append("order_info where order_status='2' ");
	if (!map.isNull("set_no")) {
	    sql.append(" and set_no = " + map.getString("set_no", "-1"));
	}
	if (!map.isNull("vip_card_no")) {
	    sql.append(" and vip_card_no = " + map.getString("vip_card_no", "-1"));
	}
	if (!map.isNull("prearrange_name")) {
	    sql.append(" and prearrange_name = '" + map.getString("prearrange_name", "-1") + "'");
	}
	if (!map.isNull("prearrange_phone")) {
	    sql.append(" and prearrange_phone = '" + map.getString("prearrange_phone", "-1") + "'");
	}
	sql.append(" and DATE_FORMAT(prearrange_order_time,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')");
	sql.append(" and sf_id=");
	sql.append(map.getString("sf_id", "-1"));
	setRuntimeStaticData("order_info");
	System.out.println(sql.toString());

	return showPages(sql.toString(), 0, -1);
    }

    public Object unionOrder(DyncParameterMap map) throws NestedException {
	StringBuffer orderNo = new StringBuffer("F");
	orderNo.append(DataUtil.format(new Date(), "yyyyMMddHHmm"));
	orderNo.append(DataUtil.lpad(String.valueOf(new Random().nextInt(999)), '0', 3));
	map.set("order_no", orderNo.toString());
	if (map.isNull("order_id"))
	    return null;

	String[] orderId = (String[]) map.get("order_id");
	StringBuffer sql = new StringBuffer();
	sql.append("update order_info set order_no={1}");
	sql.append(" where order_id={0}");
	for (int i = 0; i < orderId.length; i++) {
	    String s = MessageFormat.format(sql.toString(), new String[] { orderId[i],
		    DataUtil.getSqlString(orderNo.toString()) });
	    System.out.println(s);
	    update(new StringBuffer(s));
	}

	return null;
    }

    private String getOrderNo1() {
	StringBuffer orderNo = new StringBuffer("F");
	orderNo.append(DataUtil.format(new Date(), "yyyyMMddHHmm"));
	orderNo.append(DataUtil.lpad(String.valueOf(new Random().nextInt(999)), '0', 3));
	return orderNo.toString();
    }

    public Object splitOrder(DyncParameterMap map) throws NestedException {

	if (map.isNull("order_id"))
	    return null;
	String[] orderId = (String[]) map.get("order_id");
	String[] orderNo = new String[orderId.length];
	for (int i = 0; i < orderNo.length; i++) {
	    orderNo[i] = getOrderNo1();
	}

	StringBuffer sql = new StringBuffer();
	sql.append("update order_info set order_no={1}");
	sql.append(" where order_id={0}");
	for (int i = 0; i < orderId.length; i++) {
	    String s = MessageFormat.format(sql.toString(), new String[] { orderId[i],
		    DataUtil.getSqlString(orderNo[i]) });

	    update(new StringBuffer(s));
	}

	return null;
    }

    public Object orderChangeSet(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer("update ");
	sql.append("order_info set set_no=");
	sql.append(map.getString("set_no"));
	sql.append(" where order_id=");
	sql.append(map.getString("order_id", "-1"));
	if (map.isNull("set_no"))
	    return null;
	update(sql);
	return null;
    }

    public Object perM(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select max(order_list_id) as m from order_list");
	Object o = get(sql);
	HashMap m = new HashMap();
	m.put("max", DataUtil.getObject(o, "m"));
	return m;
    }

    public Object perMer(DyncParameterMap map) throws NestedException {
	StringBuffer sql1 = new StringBuffer();
	sql1.append("select count(*) from order_list");
	StringBuffer sql = new StringBuffer();
	sql.append("select max(order_list_id) as m from order_list");
	Object o = get(sql);
	StringBuffer ext = new StringBuffer();
	ext.append("update order_list set modify_staff_name='");
	ext.append(map.getString("staff_name"));
	ext.append("'");
	ext.append(" where order_id in(");
	ext.append(map.getString("id"));
	ext.append(")");
	ext.append(" and order_list_id between ");
	if (getInt(sql1) > 0 && !("".equals(map.getString("m")))) {
	    ext.append(map.getString("m"));
	} else {
	    ext.append("1");
	}
	ext.append(" and ");
	ext.append(DataUtil.getString(o, "m"));
	update(ext);
	System.out.println(ext);
	return null;
    }

    public Object fastPaySearch(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select * from ");
	sql.append("order_info where order_status='0' ");
	if (!map.isNull("order_no")) {
	    sql.append(" and (order_no like '%" + map.getString("order_no") + "%' or balcony_name like '%"
		    + map.getString("order_no") + "%' or vip_card_no like '%" + map.getString("order_no") + "%')");
	}
	// if(!map.isNull("vip_card_no"))
	// {
	// sql.append(" and vip_card_no = "+map.getString("vip_card_no", "-1"));
	// }
	// if(!map.isNull("prearrange_name"))
	// {
	// sql.append(" and prearrange_name = '"+map.getString("prearrange_name",
	// "-1")+"'");
	// }
	// if(!map.isNull("prearrange_phone"))
	// {
	// sql.append(" and prearrange_phone = '"+map.getString("prearrange_phone",
	// "-1")+"'");
	// }
	sql.append(" and sf_id=");
	sql.append(map.getString("sf_id", "-1"));
	setRuntimeStaticData("order_info");
	System.out.println(sql.toString());

	return showPages(sql.toString(), 0, -1);
    }

    public Object getLinkMan(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	String orders = map.getString("order_no", "-1");
	sql.append("select PREARRANGE_NAME as book_man,PREARRANGE_PHONE as book_phone from ");
	sql.append("order_info where order_no in ('").append(orders.replaceAll("[,]", "','")).append("')");
	sql.append(" and (PREARRANGE_NAME is not null or PREARRANGE_PHONE is not null)");
	System.out.println(sql);
	return get(sql);
    }
}
