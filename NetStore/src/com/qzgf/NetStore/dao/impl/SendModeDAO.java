package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.ISendModeDAO;
import com.qzgf.NetStore.persistence.SendMode;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class SendModeDAO extends BaseDao implements ISendModeDAO {

	/**
	 * 查询所有的配送方式
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List querySendMode() {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String strsql = "select * from t_sendMode";
		//UtilDB du=new UtilDB();
		List sendModeList = new ArrayList();
		ResultSet rs = null;
		try {
			rs = utilDB.executeQuery(strsql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("sendModeId", rs.getString("sendModeId"));
				map.put("sendModeName", rs.getString("sendModeName"));
				map.put("sendFee", rs.getString("sendFee"));
				map.put("sendTime", rs.getString("sendTime"));
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
	 * 修改配送方式
	 * @return
	 */
	public boolean updateSendMode(SendMode sendMode) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer strsql = new StringBuffer(
				"update t_sendMode set sendModeName='"
						+ sendMode.getSendModeName() + "',");
		strsql.append("sendFee=" + sendMode.getSendFee() + ",sendTime='"
				+ sendMode.getSendTime() + "' ");
		strsql.append("where sendModeId=" + sendMode.getSendModeId());

		boolean tf = utilDB.executeUpdate(strsql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	public boolean deleteSendMode(String sendModeId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String strsql = "delete from t_sendMode where sendModeId=" + sendModeId;
		//UtilDB du=new UtilDB();

		/*try {
			return du.stmt.execute(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		 */

		boolean tf = utilDB.executeUpdate(strsql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	public boolean addSendMode(SendMode sendMode) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer strsql = new StringBuffer(
				"insert into t_sendMode(sendModeName,sendFee,sendTime) values('");
		strsql.append(sendMode.getSendModeName() + "'," + sendMode.getSendFee()
				+ ",'" + sendMode.getSendTime() + "')");

		boolean tf = utilDB.executeUpdate(strsql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}
}
