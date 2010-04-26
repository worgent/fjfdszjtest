package com.qzgf.application.systemManage.message.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 框架测试模块
 * @author lsr
 *
 */
public class MessageFacadeImpl implements MessageFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MessageFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	
	@SuppressWarnings("unchecked")
	public int insertMessage(HashMap map){
		int st = 0;
		// 正向发送
		map.put("pstate", "1");// 发送
		st = baseSqlMapDAO.update("Message.insertMessage", map);
		// 逆向接收
		map.put("pstate", "2");// 接收
		/*
		// 转换发送与接收人员姓名
		String sendcodeStr = "";
		String reccodeStr = "";
		//是否存在接收者
		if (map.containsKey("preccode")) {
			sendcodeStr = map.get("psendcode").toString();
			reccodeStr = map.get("preccode").toString();
		}
		map.put("psendcode", reccodeStr);
		map.put("preccode", sendcodeStr);
		*/
		st = baseSqlMapDAO.update("Message.insertMessage", map);
		return st;
	}
	
	
	@SuppressWarnings("unchecked")
	public int deleteMessageById(HashMap map) {
		int num = baseSqlMapDAO.update("Message.deleteMessageById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateMessageById(HashMap map) {
		int num = baseSqlMapDAO.update("Message.updateMessageById", map);
		if (num == 1) {
			// 修改成功
			return 1;
		}
		// 修改失败
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findMessagePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Message.findMessageCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Message.findMessagePage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	/*查询单行记录*/
	public List findMessage(HashMap map) {
		return baseSqlMapDAO.queryForList("Message.findMessage", map);
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
