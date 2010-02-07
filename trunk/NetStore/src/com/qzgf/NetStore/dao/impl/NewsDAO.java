package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.INewsDAO;
import com.qzgf.NetStore.persistence.News;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class NewsDAO extends BaseDao implements INewsDAO {
	//UtilDB du = new UtilDB();

	/**
	 * 列出所有的新闻
	 */
	@SuppressWarnings("unchecked")
	public Page queryAllNews(int npage) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_news order by ReleaseTime desc";
		List allNews = new ArrayList();
		Page page = null;

		try {
			page = utilDB.executeQueryByPageForMySql(sql.toString(), npage,
					Page.DEFAULT_PAGESIZE);
			ResultSet rs = page.getRowset();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("newsId", rs.getString("NewsId"));
				map.put("newsTitle", rs.getString("NewsTitle"));
				map.put("releaseMan", rs.getString("ReleaseMan"));
				map.put("releaseTime", rs.getString("ReleaseTime"));
				allNews.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(allNews);

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return page;
	}

	/**
	 * 根据ID列出某一条新闻
	 */
	@SuppressWarnings("unchecked")
	public Map queryNewsById(String id) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_news where newsId='" + id
				+ "' order by releaseTime desc ";
		ResultSet rs;
		try {
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("newsId", rs.getString("NewsId"));
				map.put("newsTitle", rs.getString("NewsTitle"));
				map.put("releaseMan", rs.getString("ReleaseMan"));
				map.put("newsContent", rs.getString("newsContent"));
				map.put("releaseTime", rs.getString("releaseTime"));
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

	/**
	 * 更新某一条新闻
	 */
	public boolean updateNews(News news) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer("update t_news set newsTitle='"
				+ news.getNewsTitle() + "' ,newsContent='"
				+ news.getNewsContent() + "'");
		sql.append(" where newsId='" + news.getNewsId() + "'");

		boolean tf = utilDB.executeUpdate(sql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	public boolean addNews(News news) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer(
				"insert into t_news(newsId,newsTitle,newsContent,releaseMan)");
		sql.append("values(null,'" + news.getNewsTitle() + "','"
				+ news.getNewsContent() + "','");
		sql.append(news.getReleaseMan() + "')");

		boolean tf = utilDB.executeUpdate(sql.toString());

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tf;

	}

	public boolean deleteNews(String[] newsIds) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer(
				"delete from t_news where newsId in ('" + newsIds[0] + "'");
		if (newsIds != null && newsIds.length >= 2) {
			for (int i = 1; i < newsIds.length; i++) {
				sql.append(",'" + newsIds[i] + "'");
			}
		}
		sql.append(")");

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
	 * 根据应该类别查得这个类别下面的所有的组
	 * @param applicationType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryGroupsByApplicationType(String applicationType) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_group where applicationType='"
				+ applicationType + "'";
		List allGroups = new ArrayList();
		ResultSet rs;
		try {
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("groupId", rs.getString("groupId"));
				map.put("groupName", rs.getString("groupName"));
				map.put("description", rs.getString("description"));
				allGroups.add(map);
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

		return allGroups;
	}

	/**
	 * 添加一个新的组
	 * @param groupName
	 * @param description
	 * @param applicationType
	 * @param remark
	 * @return
	 */
	public boolean addGroup(String groupName, String description,
			String applicationType, String remark) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer(
				"insert into t_group(groupId,groupname,applicationType,description,remark)");
		sql.append(" values(null,'" + groupName + "','" + applicationType
				+ "','" + description + "'," + remark + ")");

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
	 * 根据某个组编号查询该组的信息
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryGroupByGroupId(String groupId) {

		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_group where groupId='" + groupId + "'";
		try {
			ResultSet rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("groupId", rs.getString("groupId"));
				map.put("groupName", rs.getString("groupName"));
				map.put("description", rs.getString("description"));
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

	/*
	 * 更新某个组信息
	 */
	public boolean updateGroup(String groupId, String groupName,
			String description) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer("update t_group set groupName='"
				+ groupName + "',description='"
				+ (description == null ? "" : description)
				+ "' where groupId='" + groupId + "'");

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
	 * 删除组
	 * @param groupId
	 * @param groupName
	 * @param description
	 * @return
	 */
	public boolean deleteGroup(String groupId) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "delete from t_group where groupId='" + groupId + "'";

		boolean tf = utilDB.executeUpdate(sql.toString());
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tf;

	}

	//用于前台显示的新闻
	@SuppressWarnings("unchecked")
	public Page queryNews(int npage) {
		UtilDB utilDB = null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from t_news order by releaseTime desc";
		List allNews = new ArrayList();
		Page page = null;

		try {
			page = utilDB.executeQueryByPageForMySql(sql.toString(), npage,
					Page.DEFAULT_PAGESIZE);
			ResultSet rs = page.getRowset();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("newsId", rs.getString("NewsId"));
				map.put("newsTitle", rs.getString("NewsTitle"));
				map.put("releaseTime", rs.getString("ReleaseTime"));
				allNews.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(allNews);

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return page;
	}
}
