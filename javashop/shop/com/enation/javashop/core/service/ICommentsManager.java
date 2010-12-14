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
package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Comments;
import com.enation.javashop.core.model.support.CommentDTO;

/**
 * 评论/咨询接口
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-26 下午03:35:35<br/>
 *         version 1.0<br/>
 * <br/>
 */
public interface ICommentsManager {

	/**
	 * 取得评论、咨询，包含其子列表
	 * 
	 * @param comment_id
	 * @return CommentDTO,
	 * @see com.enation.javashop.core.model.support.CommentDTO
	 */
	public CommentDTO getComments(Integer comment_id);

	/**
	 * 新增评论、咨询或其回复
	 * 
	 * @param comments
	 */
	public void addComments(Comments comments);

	/**
	 * 修改评论、咨询或其回复，完成如[显示在页面中][删除]等操作
	 * 
	 * @param comments
	 */
	public void updateComments(Comments comments);

	/**
	 * 删除到回收站
	 * 
	 * @param ids
	 */
	public void deleteComments(String ids);

	/**
	 * 将回收站清空
	 * 
	 * @param ids
	 */
	public void cleanComments(String ids);

	/**
	 * 从回收站中还原
	 * 
	 * @param ids
	 */
	public void revert(String ids);

	/**
	 * 分页显示对应object_id的评论（或咨询），用于前台显示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param object_id
	 * @param object_type
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type);
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param object_id
	 * @param object_type
	 * @param commentType
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type, String commentType);
	
	/**
	 * 用户中心-评论或咨询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize);
	
	/**
	 * 列表某一会员的评论或咨询
	 * 具体类型（评论或咨询）由object_type指定
	 * @param member_id
	 * @param object_type
	 * @return
	 */
	public List listComments(int member_id, String object_type);
	
	/**
	 * 分页显示未被删除的评论（或咨询），不针对指定的object_id
	 * @param pageNo
	 * @param pageSize
	 * @param object_type
	 * @return
	 */
	public Page pageComments(int pageNo, int pageSize,	String object_type);
	
	/**
	 * 分页显示回收站内的评论（或咨询）
	 * @param pageNo
	 * @param pageSize
	 * @param object_type
	 * @return
	 */
	public Page pageCommentsTrash(int pageNo, int pageSize, String object_type);

}
