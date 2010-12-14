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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.EopException;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Comments;
import com.enation.javashop.core.model.support.CommentDTO;
import com.enation.javashop.core.service.ICommentsManager;

/**
 * 咨询、评论管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-26 下午03:47:54<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class CommentsManager extends BaseSupport<Comments> implements
		ICommentsManager {
	
	
	public void addComments(Comments comments) {
		this.baseDaoSupport.insert("comments", comments);
	}
	
	
	public void deleteComments(String ids){
		if (ids == null || ids.equals(""))
			return;
		String sql = "update comments set disabled='true' where comment_id in (" + ids
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public void cleanComments(String ids) {
		if (ids == null || ids.equals(""))
			return;
		String sql = "delete  from  comments   where comment_id in (" + ids
				+ ") or for_comment_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public CommentDTO getComments(Integer commentId) {
		String sql = "select * from comments where comment_id=?";
		Comments comments = baseDaoSupport.queryForObject(sql, Comments.class,
				commentId);

		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setComments(comments);
		List<Comments> list = baseDaoSupport.queryForList(
				"select * from comments where for_comment_id = ?",
				Comments.class, commentId);
		commentDTO.setList(list);
		return commentDTO;
	}

	
	public void revert(String ids) {
		if (ids == null || ids.equals(""))
			return;
		String sql = "update comments set disabled='false' where comment_id in (" + ids
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public void updateComments(Comments comments) {
		this.baseDaoSupport.update("comments", comments, "comment_id="
				+ comments.getComment_id());

	}
	
	
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type){
		String sql = "select * from comments where for_comment_id is null and display='true' and object_id = ? and object_type=? order by time desc";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, object_id, object_type);
		int userid = EopContext.getContext().getCurrentSite().getUserid();
		int siteid = EopContext.getContext().getCurrentSite().getId();
		String sql_id_list = "select * from " + this.getTableName("comments") + " where for_comment_id in (select comment_id from " + this.getTableName("comments") + " where for_comment_id is null and display='true' and object_id = ? and object_type=? ) and display='true' order by time desc ";
		List<Comments> listReply = this.daoSupport.queryForList(sql_id_list, Comments.class, object_id, object_type);
		List<Map> list = (List<Map>) (page.getResult());
		for (Map comments : list) {
			List<Comments> child = new ArrayList<Comments>();
			for(Comments reply:listReply){
				if(reply.getFor_comment_id().equals(Integer.valueOf(comments.get("comment_id").toString()))){
					child.add(reply);
				}
			}
			comments.put("list", child);
		}
		return page;
	}
	
	
	public Page pageComments(int pageNo, int pageSize, String object_type){
	 
		String sql = "select c.*, case c.commenttype when 'goods' then g.name when 'article' then a.title end name from " + this.getTableName("comments") + " c left join "+ this.getTableName("goods") + " g on g.goods_id = c.object_id left join " + this.getTableName("article") + " a on a.id = c.object_id where  for_comment_id is null and c.disabled='false' and object_type=? order by time desc";
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize, object_type);
		String sql_id_list = "select * from " + this.getTableName("comments") + " where for_comment_id in (select comment_id from " + this.getTableName("comments") + " where for_comment_id is null and display='true' and object_type=? ) order by time desc ";
		List<Comments> listReply = this.daoSupport.queryForList(sql_id_list, Comments.class, object_type);
		List<Map> list = (List<Map>) (page.getResult());
		for (Map comments : list) {
			List<Comments> child = new ArrayList<Comments>();
			for(Comments reply:listReply){
				if(reply.getFor_comment_id().equals(Integer.valueOf(comments.get("comment_id").toString()))){
					child.add(reply);
				}
			}
			comments.put("list", child);
		}
		return page;
	}
	
	
	public Page pageCommentsTrash(int pageNo, int pageSize, String object_type){
		String sql = "select * from comments where for_comment_id is null and disabled='true' and object_type=? order by time desc";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, object_type);

		String sql_id_list = "select * from " + this.getTableName("comments") + " where for_comment_id in (select comment_id from " + this.getTableName("comments") + " where for_comment_id is null and display='true' and object_type=?) order by time desc ";
		List<Comments> listReply = this.daoSupport.queryForList(sql_id_list, Comments.class, object_type);
		List<Map> list = (List<Map>) (page.getResult());
		for (Map comments : list) {
			List<Comments> child = new ArrayList<Comments>();
			for(Comments reply:listReply){
				if(reply.getFor_comment_id().equals(Integer.valueOf(comments.get("comment_id").toString()))){
					child.add(reply);
				}
			}
			comments.put("list", child);
		}
		return page;
	}

	
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type, String commentType) {
		String sql = "select * from comments where for_comment_id is null and display='true' and object_id = ? and object_type=? and commenttype = ? order by time desc";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, object_id, object_type, commentType);
 
		String sql_id_list = "select * from " + this.getTableName("comments") + " where for_comment_id in (select comment_id from " + this.getTableName("comments") + " where for_comment_id is null and display='true' and object_id = ? and object_type=? and commenttype = ?) and display='true' order by time desc ";
		List<Comments> listReply = this.daoSupport.queryForList(sql_id_list, Comments.class, object_id, object_type, commentType);
		List<Map> list = (List<Map>) (page.getResult());
		for (Map comments : list) {
			Long time = (Long)comments.get("time");
			comments.put("date", (new Date(time)));
			List<Comments> child = new ArrayList<Comments>();
			for(Comments reply:listReply){
				if(reply.getFor_comment_id().equals(Integer.valueOf(comments.get("comment_id").toString()))){
					child.add(reply);
				}
			}
			comments.put("list", child);
		}
		return page;
	}

	
	public Page pageComments_Display(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member == null){
			throw new EopException("您没有登录或已过期，请重新登录！");
		}
		String sql = "select * from comments where for_comment_id is null and display='true' and author_id = ? order by time desc";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, member.getMember_id());
		String sql_id_list = "select * from " + this.getTableName("comments") + " where for_comment_id in (select comment_id from " + this.getTableName("comments") + " where for_comment_id is null and display='true' and author_id = ? ) and display='true' order by time desc ";
		List<Comments> listReply = this.daoSupport.queryForList(sql_id_list, Comments.class, member.getMember_id());
		List<Map> list = (List<Map>) (page.getResult());
		for (Map comments : list) {
			Long time = (Long)comments.get("time");
			comments.put("date", (new Date(time)));
			List<Comments> child = new ArrayList<Comments>();
			for(Comments reply:listReply){
				if(reply.getFor_comment_id().equals(Integer.valueOf(comments.get("comment_id").toString()))){
					child.add(reply);
				}
			}
			comments.put("list", child);
		}
		return page;
	}

	
	public List listComments(int member_id, String object_type) {
		String sql = "select * from comments where for_comment_id is null and author_id = ? and object_type = ? order by time desc";
		List list = this.baseDaoSupport.queryForList(sql, Comments.class, member_id, object_type);
		return list;
	}

}
