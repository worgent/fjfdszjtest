package com.qzgf.application.netprint.emsmail.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ImportFactory;
import com.qzgf.utils.export.ImportIface;
import com.qzgf.utils.export.Importbase;

/**
 * 租赁合同管理
 * 
 * @author lsr
 * 
 */
public class EmsmailFacadeImpl implements EmsmailFacade {
	BaseSqlMapDAO baseSqlMapDAO;

	/**
	 * 查询客户信息总记录数
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findEmsmailCount(HashMap map) {
		if (!map.get("pdeptid").toString().equals("110")) {
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid")
					.toString()));
		}
		return ((Integer) baseSqlMapDAO.queryForObject(
				"Emsmail.findEmsmailCount", map)).intValue();
	}

	/**
	 * 查询客户信息
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEmsmail(HashMap map) {
		if (!map.get("pdeptid").toString().equals("110")) {
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid")
					.toString()));
		}
		return baseSqlMapDAO.queryForList("Emsmail.findEmsmail", map);
	}

	/**
	 * 修改客户信息
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmail(HashMap map) {
		return baseSqlMapDAO.update("Emsmail.updateEmsmail", map);
	}

	/**
	 * 添加客户信息
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertEmsmail(HashMap map) {
		return baseSqlMapDAO.update("Emsmail.insertEmsmail", map);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteEmsmail(HashMap map) {
		return baseSqlMapDAO.update("Emsmail.deleteEmsmail", map);

	}

	/**
	 * 增加客户信息(收件人信息,发件人信息);
	 */
	@SuppressWarnings("unchecked")
	public int insertClientmsg(HashMap map) {
		return baseSqlMapDAO.update("Clientmsg.insertClientmsg", map);
	}

	/**
	 * 查询邮件费用区域
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List procEmsmailFeeArea(String psendaddress) {
		HashMap hs = new HashMap();
		hs.put("psendaddress", psendaddress);
		return baseSqlMapDAO.queryForList("Emsmail.procEmsmailFeeArea", hs);
	}

	/**
	 * 查询打印数据集
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEmsmailPrint(HashMap map) {
		return baseSqlMapDAO.queryForList("Emsmail.findEmsmailPrint", map);
	}

	/**
	 * 更新打印
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmailPrint(HashMap map) {
		return baseSqlMapDAO.update("Emsmail.updateEmsmailPrint", map);
	}
	
	

	/**
	 * 
	 * Purpose      : 上传excel文件的处理接口,导入ems详单数据.
	 * @param exportExc :操作excel的接口
	 * @param search :原HashMap中的相关参数
	 * @return :是否执行成功
	 */
	public int insertexcelupload(ImportIface exportExc, HashMap search) {
		int i = 0;// 是否处理成功;
		try {
			//开单人一些必备信息
			String userid=search.get("createMan").toString();
			String pdeptid=search.get("pdeptid").toString();
			String commstaffId=search.get("commstaffId").toString();
			
			
			// 导入数据,读取每一行信息
			HashMap line = exportExc.readLine();
			while (line != null) {
				//每次先清除原封发局信息 20110321
				search.clear();
				//还原必备信息处理
				search.put("createMan", userid);
				search.put("pdeptid", pdeptid);
				search.put("commstaffId", commstaffId);
				
				// 将数据放入search中.
				search.putAll(line);
				// ========================信息处理===========================================
				// 是否保价
				if (search.containsKey("psendinsure")
						&& search.get("psendinsure").toString()
								.equalsIgnoreCase("是")) {
					search.put("psendinsure", "1");
				} else {
					search.put("psendinsure", "0");
				}
				// 邮件类型
				if (search.containsKey("psendmailtype")) {
					if (search.get("psendmailtype").toString()
							.equalsIgnoreCase("信函")) {
						search.put("psendmailtype", "0");
					} else if (search.get("psendmailtype").toString()
							.equalsIgnoreCase("文件")) {
						search.put("psendmailtype", "1");
					} else {
						search.put("psendmailtype", "2");
					}
				} else {
					search.put("psendmailtype", "2");
				}
				// 收寄局处理
				search.put("psenddateyear", "");// 年
				search.put("psenddatemonth", "");// 月
				search.put("psenddateday", "");// 日
				search.put("psenddatehour", "");// 时
				if (search.containsKey("psendoffice")) {
					if (!search.get("psendoffice").toString().equalsIgnoreCase(
							"")) {
						Calendar cal = Calendar.getInstance();
						search.put("psenddateyear", cal.get(Calendar.YEAR));// 年
						search.put("psenddatemonth",
								cal.get(Calendar.MONTH) + 1);// 月
						search.put("psenddateday", cal
								.get(Calendar.DAY_OF_MONTH));// 日
						search.put("psenddatehour", cal
								.get(Calendar.HOUR_OF_DAY));// 时
					}
				}

				// 为解决,只填写寄件人信息的处理
				if (search.containsKey("precaddress")) {
					// 取得价格区域
					String address = search.get("precaddress").toString();
					// 处理地址去除(省市县区).
					Pattern p = Pattern.compile("省|市|县|区");
					Matcher mat = p.matcher(address.toString());
					address = mat.replaceAll("");

					List emsmailList = procEmsmailFeeArea(address);
					if (emsmailList.size() > 0) {
						search.put("pmail_feearea", ((HashMap) emsmailList
								.get(0)).get("mail_feearea").toString()); // 计费区
						search.put("pmail_sendoffice", ((HashMap) emsmailList
								.get(0)).get("send_office").toString());// 发送局
						search.put("pemail_fee", ((HashMap) emsmailList.get(0))
								.get("email_fee").toString());// e邮宝信息

						// 增加收件人的邮政编码
						if (search.containsKey("precpost")) {
							if (search.get("precpost").toString().trim()
									.equals("")) {
								search.put("precpost", ((HashMap) emsmailList
										.get(0)).get("recpost").toString());// 邮政编号
								search.put("preccode", ((HashMap) emsmailList
										.get(0)).get("city_name").toString());// 城市
							}
						} else {
							search.put("precpost", ((HashMap) emsmailList
									.get(0)).get("recpost").toString());// 邮政编号
							search.put("preccode",
									((HashMap) emsmailList.get(0)).get(
											"city_name").toString());// 城市
						}
					}
				}
				i = baseSqlMapDAO.update("Emsmail.insertEmsmail", search);
				// 循环处理信息
				line = exportExc.readLine();
			}
			// 关闭连接
			exportExc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	@SuppressWarnings("unchecked")
	public List getUserConfig(HashMap map) {
		return baseSqlMapDAO.queryForList("ManagerInfo.finduserConfig", map);
	}
}
