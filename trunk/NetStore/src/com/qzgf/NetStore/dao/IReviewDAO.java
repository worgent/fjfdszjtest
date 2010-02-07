package com.qzgf.NetStore.dao;

import java.util.List;

import com.qzgf.NetStore.pub.Page;

public interface IReviewDAO {
	@SuppressWarnings("unchecked")
	public Page reviewList(int page,String t);
	
	//查询未回复
	@SuppressWarnings("unchecked")
	public List reviewReplyList();
	
	//查询是否审批
	@SuppressWarnings("unchecked")
	public List reviewAuditList(String value);
	
	//审批是否通过
	public void reviewAudited(String rId,String tag);
	
	//点击回复中．．．．
	@SuppressWarnings("unchecked")
	public List replyingList(String rId);
	
	//删除　
	public void Delete(String ID);
	
	//更新回复
	public void update(String rId,String replyContent);
	
	
	
	
	
}
