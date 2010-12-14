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
package com.enation.javashop.core.action.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enation.app.setting.service.ISettingService;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.utils.ValidCodeServlet;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Comments;
import com.enation.javashop.core.model.support.CommentDTO;
import com.enation.javashop.core.service.ICommentsManager;

/**
 * 咨询、评论管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-27 下午02:38:07<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class CommentsAction extends WWAction {

	private ICommentsManager commentsManager;
	private String object_type;
	private Integer object_id;
	private Integer count;// 在页面上显示的数量
	private Comments comments;
	private CommentDTO commentDTO;
	
	private String id;
	private Integer comment_id;
	private Integer direct_show = 0;//设置前台发表的评论是否直接可见
	private List commentsList;
	private String commentType;
	private ISettingService settingService;
	private String valid_code;
	private String validcode;  //1需要验证码；0不需要
	private Integer managerid;
	
	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	/**
	 * 取得评论或咨询信息，以及其回复的列表
	 * 正式使用时应该是以Ajax的方式传递进来object_type/object_id/page/pagesize参数的值
	 * 
	 * @return
	 */
	public String list() {
		String pageSize_com = settingService.getSetting("comments", "pageSize");
		pageSize_com = pageSize_com == null ? "5" : pageSize_com;
		this.webpage = commentsManager.pageComments_Display(this.getPage(),
				Integer.valueOf(pageSize_com), object_id, object_type, commentType);
		commentsList= (List)webpage.getResult();
		commentsList = commentsList==null?new ArrayList():commentsList;
		return "list";
	}
	
	public String bglist() {
		this.webpage = commentsManager.pageComments(this.getPage(),
				this.getPageSize(), object_type);
		return "bglist";
	}
	
	public String trashlist(){
		this.webpage = commentsManager.pageCommentsTrash(this.getPage(),
				this.getPageSize(), object_type);
		return "trashlist";
	}
	
	public String detail(){
		IUserService userService = UserServiceFactory.getUserService();
		managerid = userService.getCurrentManagerId();
		commentDTO = commentsManager.getComments(comment_id);
		return "detail";
	}
	
	public String delete(){
		try{
			commentsManager.deleteComments(id);
			this.json = "{'result':0,'message':'操作成功'}";
		}catch(RuntimeException e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String revert(){
		try{
			commentsManager.revert(id);
			this.json = "{'result':0,'message':'操作成功'}";
		}catch(RuntimeException e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String clean(){
		try{
			commentsManager.cleanComments(id);
			this.json = "{'result':0,'message':'操作成功'}";
		}catch(RuntimeException e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String add() {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member == null){
			member = new Member();
			member.setMember_id(0);
			member.setName("匿名");
		}
		String msg = "，待管理员审核后显示";
		try {
			if(validcode.equals("1")){  //如果启用了验证码
				Object realCode = ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"comments");
				if(!valid_code.equals(realCode)){
					this.json = "{'state':1,'message':'验证码不正确'}";
					return this.JSON_MESSAGE;
				}
			}
			if (this.direct_show == 1) {
				comments.setDisplay("true");
				msg = "";
			}
			if(managerid!=null){//TODO 判断是否后台已登录
				member.setMember_id(UserServiceFactory.getUserService().getCurrentManagerId());
				member.setName("管理员");
				try{
					Comments parentcomments = commentsManager.getComments(comments.getFor_comment_id()).getComments();
					parentcomments.setDisplay("true");
					commentsManager.updateComments(parentcomments);
				}catch(RuntimeException e){
					e.printStackTrace();
					this.json = "{'result':1,'message':'操作失败'}";
				}
			}
			comments.setAuthor(member.getName());
			comments.setAuthor_id(member.getMember_id());
			comments.setTime((new Date()).getTime());
			commentsManager.addComments(comments);
			this.json = "{'state':0,'message':'发表成功" + msg + "'}";
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.json = "{'state':1,'message':'发生未知异常'}";
		}

		return this.JSON_MESSAGE;
	}
	
	public String hide() {
		try{
			comments = commentsManager.getComments(comment_id).getComments();
			comments.setDisplay("false");
			commentsManager.updateComments(comments);
			this.json = "{'result':0,'message':'操作成功'}";
		}catch(RuntimeException e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String show() {
		try{
			comments = commentsManager.getComments(comment_id).getComments();
			comments.setDisplay("true");
			commentsManager.updateComments(comments);
			this.json = "{'result':0,'message':'操作成功'}";
		}catch(RuntimeException e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String deletealone() {
		//对于回复的删除是直接删除
		id = comment_id.toString();
		commentsManager.cleanComments(id);
		this.msgs.add("删除成功");
		return this.MESSAGE;
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String objectType) {
		object_type = objectType;
	}

	public Integer getObject_id() {
		return object_id;
	}

	public void setObject_id(Integer objectId) {
		object_id = objectId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer commentId) {
		comment_id = commentId;
	}

	public CommentDTO getCommentDTO() {
		return commentDTO;
	}

	public void setCommentDTO(CommentDTO commentDTO) {
		this.commentDTO = commentDTO;
	}

	public Integer getDirect_show() {
		return direct_show;
	}

	public void setDirect_show(Integer directShow) {
		direct_show = directShow;
	}

	public List getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List commentsList) {
		this.commentsList = commentsList;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String validCode) {
		valid_code = validCode;
	}

	public String getValidcode() {
		return validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

}
