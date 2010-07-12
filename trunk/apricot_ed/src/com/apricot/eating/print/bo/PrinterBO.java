/**
 * 
 */
package com.apricot.eating.print.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.print.Printer;
import com.apricot.eating.print.docs.FoodPrintDocument;
import com.apricot.eating.print.docs.OrderPrintDocument;
import com.apricot.eating.util.DataUtil;

/**
 * @author xudahu
 * 
 */
public class PrinterBO extends BO {

    /**
     * 
     */
    public PrinterBO() {
	// TODO Auto-generated constructor stub
    }

    private String getOrderInfo(Object obj) {
	StringBuffer sb = new StringBuffer();
	// sb.append("").append(DataUtil.getString(obj,
	// "order_no")).append("   ");

	sb.append(DataUtil.getString(obj, "belong_to_text")).append(DataUtil.getString(obj, "balcony_code")).append(
		"号桌").append(DataUtil.lpad("", ' ', 6));
	sb.append(DataUtil.getString(obj, "man_count")).append("人   ");
	sb.append(DataUtil.formatDateTime(DataUtil.getString(obj, "order_time"), "HH:mm")).append("\n");
	sb.append(DataUtil.rbytepad("菜品", ' ', 24));
	sb.append(DataUtil.rbytepad("单价", ' ', 8));
	sb.append(DataUtil.rbytepad("数量", ' ', 8));
	sb.append(DataUtil.rbytepad("动作", ' ', 8));
	sb.append("\n");
	sb.append(DataUtil.rbytepad("", '—', 48)).append("\n");

	return sb.toString();
    }

    private String getFoodInfo(Object obj) {
	StringBuffer sb = new StringBuffer();

	sb.append(DataUtil.rbytepad(DataUtil.getString(obj, "food_name"), ' ', 24));
	sb.append(DataUtil.rbytepad(DataUtil.getString(obj, "food_price"), ' ', 8));
	sb.append(DataUtil.rbytepad(DataUtil.getString(obj, "food_count"), ' ', 8));
	sb.append(DataUtil.rbytepad(DataUtil.getString(obj, "food_action_text"), ' ', 8));
	sb.append("\n");

	String s = DataUtil.getString(obj, "food_memo");
	if (!DataUtil.isNull(s) && !"null".equals(s)) {
	    sb.append(DataUtil.lpad("", ' ', 6)).append("口味备注:").append(s).append("\n");
	}

	return sb.toString();
    }

    private List readFoodList(DyncParameterMap map, String type) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select distinct t1.order_list_id,t1.FOOD_COUNT,");
	sql.append("t1.food_memo,t1.reminder_times,(t1.print_times-t1.ERROR_TIMES) as rptimes,");
	sql.append("t1.FOOD_PRICE,t3.*,t2.food_name,t4.order_time,t1.food_action,");
	sql.append("t4.order_no,t5.BELONG_TO,t5.BALCONY_CODE,t4.MAN_COUNT,t1.ORDER_LIST_ID ");
	sql.append("from ORDER_LIST t1,FOOD_INFO t2,PRINT_CONFIG t3,ORDER_INFO t4,DINING_SET_INFO t5 ");
	sql.append("where t1.food_id=t2.FOOD_ID and t2.food_type=t3.food_type ");
	sql.append("and t1.order_id=t4.order_id and t4.set_no=t5.set_no ");
	sql.append("and t4.ORDER_STATUS='0' ");// 未结帐
	sql.append("and t1.SERVING_FLAG='0' ");// 未上菜
	sql.append("and t1.FOOD_ACTION in(").append(type).append(") ");// 退菜
	sql.append("and t1.PRINT_FLAG<>'1' ");// 未打印、重新打印、打印失败
	if ("'1'".equals(type)) {
	    sql.append("and t3.print_mode='0' ");
	}
	sql.append("and t4.PRINT_STARTTIME<='").append(DataUtil.getCurrDateTime()).append("' ");
	sql.append("and t4.PRINT_STARTTIME>='").append(
		DataUtil.formatDateTime(DataUtil.getCurrDateTime(), "yyyy-MM-dd 00:00:00")).append("' ");
	sql.append("order by t3.printer_type asc ");// 按照打印机类型排序

	setRuntimeStaticData("dining_set_info");
	setRuntimeStaticData("order_list");
	List arr = getAll(sql);
	List docs = new ArrayList();

	for (Iterator objs = arr.iterator(); objs.hasNext();) {
	    Object obj = objs.next();
	    FoodPrintDocument doc = new FoodPrintDocument();
	    doc.setPrinter(new Printer(obj));
	    doc.setId(DataUtil.getString(obj, "order_list_id"));
	    StringBuffer sb = new StringBuffer(getOrderInfo(obj)).append(getFoodInfo(obj));

	    if ("4".equals(type)) {
		sb.append("\n第").append(DataUtil.getString(obj, "reminder_times")).append("次催单");
	    } else {
		sb.append("\n第").append(DataUtil.getString(obj, "rptimes")).append("次打印");
	    }

	    doc.setContent(sb.toString());
	    docs.add(doc);
	}

	arr.clear();
	return docs;
    }

    /**
     * 读取菜品信息失败
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public List readCancelFoodDocument(DyncParameterMap map) throws NestedException {
	return readFoodList(map, "'2'");
    }

    /**
     * 读取菜品信息失败
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public List readReminderFoodDocument(DyncParameterMap map) throws NestedException {
	return readFoodList(map, "'4'");
    }

    /**
     * 读取菜品信息失败
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public List readFoodDocument(DyncParameterMap map) throws NestedException {
	return readFoodList(map, "'1'");
    }

    public List readOrderDocument(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select distinct t1.order_list_id,t1.FOOD_COUNT,t4.order_time,t1.food_memo,");
	sql.append("t1.FOOD_PRICE,t3.*,t2.food_name,t1.food_action,");
	sql.append("t4.order_no,t5.BELONG_TO,t5.BALCONY_CODE,t4.MAN_COUNT,t4.order_id ");
	sql.append("from ORDER_LIST t1,FOOD_INFO t2,PRINT_CONFIG t3,ORDER_INFO t4,DINING_SET_INFO t5 ");
	sql.append("where t1.food_id=t2.FOOD_ID and t2.food_type=t3.food_type ");
	sql.append("and t1.order_id=t4.order_id and t4.set_no=t5.set_no ");
	sql.append("and t4.ORDER_STATUS='0' ");// 未结帐
	sql.append("and t1.SERVING_FLAG='0' ");// 未上菜
	sql.append("and t1.FOOD_ACTION in('1') ");// 点菜或者加菜
	sql.append("and t1.PRINT_FLAG<>'1' ");// 未打印、重新打印、打印失败
	sql.append("and t3.print_mode='1' ");
	sql.append("and t4.PRINT_STARTTIME<='").append(DataUtil.getCurrDateTime()).append("' ");
	sql.append("and t4.PRINT_STARTTIME>='").append(
		DataUtil.formatDateTime(DataUtil.getCurrDateTime(), "yyyy-MM-dd 00:00:00")).append("' ");
	sql.append("order by t3.printer_type asc ");// 按照打印机类型排序
	setMaxSize(map.getInt("size"));
	setRuntimeStaticData("dining_set_info");
	setRuntimeStaticData("order_list");
	List arr = getAll(sql);
	List temp = new ArrayList();
	HashMap docs = new HashMap();
	for (Iterator objs = arr.iterator(); objs.hasNext();) {
	    Object obj = objs.next();
	    StringBuffer key = new StringBuffer(DataUtil.getString(obj, "order_id")).append("_").append(
		    DataUtil.getString(obj, "food_type"));
	    OrderPrintDocument doc = (OrderPrintDocument) docs.get(key.toString());
	    if (doc == null) {
		doc = new OrderPrintDocument();
		doc.setPrinter(new Printer(obj));
		doc.append(getOrderInfo(obj));

		docs.put(key.toString(), doc);
		temp.add(doc);
	    }
	    doc.add(DataUtil.getString(obj, "order_list_id"));
	    doc.append(getFoodInfo(obj));

	}

	arr.clear();
	docs.clear();
	return temp;
    }

    public List readCashierDeskDocument(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select distinct t1.order_list_id,t1.FOOD_COUNT,t4.order_time,t1.food_memo,");
	sql.append("t1.FOOD_PRICE,t2.food_name,t1.food_action,");
	sql.append("t4.order_no,t5.BELONG_TO,t5.BALCONY_CODE,t4.MAN_COUNT,t4.order_id ");
	sql.append("from ORDER_LIST t1,FOOD_INFO t2,ORDER_INFO t4,DINING_SET_INFO t5 ");
	sql.append("where t1.food_id=t2.FOOD_ID ");
	sql.append("and t1.order_id=t4.order_id and t4.set_no=t5.set_no ");
	sql.append("and t4.ORDER_STATUS='0' ");// 未结帐
	sql.append("and t1.FOOD_ACTION in('1','2','4') ");// 点菜或者加菜
	sql.append("and t1.CUST_PRINT_FLAG<>'1' ");// 未打印、重新打印、打印失败
	sql.append("and t4.PRINT_STARTTIME<='").append(DataUtil.getCurrDateTime()).append("' ");
	sql.append("and t4.PRINT_STARTTIME>='").append(
		DataUtil.formatDateTime(DataUtil.getCurrDateTime(), "yyyy-MM-dd 00:00:00")).append("' ");

	setMaxSize(map.getInt("size"));
	setRuntimeStaticData("dining_set_info");
	setRuntimeStaticData("order_list");

	List arr = getAll(sql);
	List temp = new ArrayList();
	HashMap docs = new HashMap();
	for (Iterator objs = arr.iterator(); objs.hasNext();) {
	    Object obj = objs.next();
	    StringBuffer key = new StringBuffer(DataUtil.getString(obj, "order_id")).append("_").append(
		    DataUtil.getString(obj, "food_type"));
	    OrderPrintDocument doc = (OrderPrintDocument) docs.get(key.toString());
	    if (doc == null) {
		doc = new OrderPrintDocument();
		doc.append(getOrderInfo(obj));
		doc.setPrinter((Printer) map.get("printer"));
		docs.put(key.toString(), doc);
		temp.add(doc);
	    }
	    doc.add(DataUtil.getString(obj, "order_list_id"));
	    doc.append(getFoodInfo(obj));

	}

	arr.clear();
	docs.clear();
	return temp;
    }

    private void writePrint(String detailId, int flag) throws NestedException {
	StringBuffer sql = new StringBuffer();
	if (DataUtil.isNull(detailId))
	    return;
	sql.append("update order_list set ");
	if (flag == 0) {
	    sql.append("print_flag=1,");
	} else {
	    sql.append("print_flag=4,ERROR_TIMES=ifnull(ERROR_TIMES,0)+1,");
	}
	sql.append("PRINT_TIMES=ifnull(PRINT_TIMES,0)+1 ");
	sql.append("where ORDER_LIST_ID in (").append(detailId).append(")");

	update(sql);
    }
    
    private void writeCashierDeskPrint(String detailId, int flag) throws NestedException {
	StringBuffer sql = new StringBuffer();
	if (DataUtil.isNull(detailId))
	    return;
	sql.append("update order_list set ");
	if (flag == 0) {
	    sql.append("cust_print_flag=1 ");
	} else {
	    sql.append("cust_print_flag=4 ");
	}
	sql.append("where ORDER_LIST_ID in (").append(detailId).append(")");

	update(sql);
    }

    public void writeDocument(DyncParameterMap map) throws NestedException {
	int len = map.getMaxSize(new String[] { "id", "flag" });
	for (int i = 0; i < len; i++) {
	    writePrint(map.getString("id", i), map.getInt("flag", i));
	}
    }
    
    public void writeCashierDeskDocument(DyncParameterMap map) throws NestedException {
	int len = map.getMaxSize(new String[] { "id", "flag" });
	for (int i = 0; i < len; i++) {
	    writeCashierDeskPrint(map.getString("id", i), map.getInt("flag", i));
	}
    }

    public Object getWaitingPrintOrders(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select distinct t4.*, ");
	sql.append("t5.BELONG_TO,t5.BALCONY_CODE ");
	sql.append("from ORDER_LIST t1,FOOD_INFO t2,ORDER_INFO t4,DINING_SET_INFO t5 ");
	sql.append("where t1.food_id=t2.FOOD_ID  ");
	sql.append("and t1.order_id=t4.order_id and t4.set_no=t5.set_no ");
	sql.append("and t4.ORDER_STATUS='0' ");// 未结帐
	sql.append("and t1.SERVING_FLAG='0' ");// 未上菜
	sql.append("and t1.FOOD_ACTION in('1') ");// 点菜或者加菜
	sql.append("and t1.PRINT_FLAG<>'1' ");// 未打印、重新打印、打印失败
	sql.append("and t4.PRINT_STARTTIME>='").append(DataUtil.getCurrDateTime()).append("' ");
	setRuntimeStaticData("dining_set_info");
	setRuntimeStaticData("order_info");
	return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
    }

    /**
     * 读取菜品信息失败
     * 
     * @param map
     * @return
     * @throws NestedException
     */
    public Object getWaitingPrintOrderDetail(DyncParameterMap map) throws NestedException {
	StringBuffer sql = new StringBuffer();
	sql.append("select t1.*,t2.food_name,t2.food_type ");
	sql.append("from ORDER_LIST t1,FOOD_INFO t2 ");
	sql.append("where t1.food_id=t2.FOOD_ID  ");
	sql.append("and t1.SERVING_FLAG='0' ");// 未上菜
	sql.append("and t1.FOOD_ACTION in('1','2','3') ");// 退菜
	sql.append("and t1.PRINT_FLAG<>'1' ");// 未打印、重新打印、打印失败
	sql.append("and t1.order_id=").append(map.getString("order_id", "-1"));
	setRuntimeStaticData("order_list");
	setRuntimeStaticData("food_info");
	return showPages(sql.toString(), 0, -1);
    }
}
