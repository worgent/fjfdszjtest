package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IPayTypeDAO;
import com.qzgf.NetStore.persistence.PayType;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class PayTypeDAO extends BaseDao implements IPayTypeDAO {

	/**
	 * 添加某一付款方式
	 */
	@Override
	public boolean addPayType(PayType payType) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer strsql = new StringBuffer(
				"insert into t_payType(payTypeName,companyName,openAccountName,bankAccount,remark) values('");
		strsql
				.append(payType.getPayTypeName() + "','"
						+ payType.getCompanyName() + "','"
						+ payType.getOpenAccountName() + "','"
						+ payType.getBankAccount() + "','"
						+ payType.getRemark() + "')");

		boolean tf = utilDB.executeUpdate(strsql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	/**
	 * 删除某一付款方式
	 */
	@Override
	public boolean deletePayType(String payTypeId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String strsql = "delete from t_payType where payTypeId=" + payTypeId;
		/*
		 * //UtilDB du=new UtilDB(); try { return du.stmt.execute(strsql); }
		 * catch (SQLException e) { e.printStackTrace(); } return false;
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

	/**
	 * 查询所有的付款方式
	 */
	@SuppressWarnings("unchecked")
	public List queryPayType() {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String strsql = "select * from t_payType";
		List payTypeList = new ArrayList();
		ResultSet rs = null;
		try {
			rs = utilDB.executeQuery(strsql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("payTypeId", rs.getString("payTypeId"));
				map.put("payTypeName", rs.getString("payTypeName"));
				map.put("companyName", rs.getString("companyName"));
				map.put("openAccountName", rs.getString("openAccountName"));
				map.put("bankAccount", rs.getString("bankAccount"));
				map.put("remark", rs.getString("remark"));
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
	 * 修改付款方式
	 */
	@Override
	public boolean updatePayType(PayType payType) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer strsql = new StringBuffer(
				"update t_payType set payTypeName='" + payType.getPayTypeName()
						+ "',");
		strsql.append("companyName='" + payType.getCompanyName()
				+ "',openAccountName='" + payType.getOpenAccountName() + "' ");
		strsql.append(",bankAccount='" + payType.getBankAccount()
				+ "',remark='" + payType.getRemark() + "' ");
		strsql.append("where payTypeId=" + payType.getPayTypeId());

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
