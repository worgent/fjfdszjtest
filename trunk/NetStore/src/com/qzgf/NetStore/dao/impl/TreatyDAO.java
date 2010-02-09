package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qzgf.NetStore.dao.ITreatyDAO;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class TreatyDAO implements ITreatyDAO {

	public boolean addTreaty(String treatyContent) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "insert into t_treaty(treatyId,treatyContent)values(1,'"
				+ treatyContent + "')";

		boolean tf = utilDB.executeUpdate(sql);
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tf;

	}

	/**
	 * 更新条约
	 * @param treatyContent
	 * @return
	 */
	public boolean updateTreaty(String treatyContent) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (null == treatyContent) {
			treatyContent = "";
		}
		StringBuffer sql = new StringBuffer(
				"update t_treaty set treatyContent='" + treatyContent
						+ "' where treatyId=1");

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
	 * 取得条约的信息
	 * @return
	 */
	public String getTreaty() {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_treaty where treatyId=1";
		try {
			ResultSet rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				String treatyContent = rs.getString("treatyContent");
				return treatyContent;
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

		return "";
	}

	/**
	 * 返回拥有Id的数量
	 * @return
	 */
	public int getTreatyNumById() {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select count(*) as num from t_treaty where treatyId=1";
		try {
			ResultSet rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				int num = Integer.parseInt(rs.getString("num"));
				return num;
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
