package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.pub.Page;

public interface IReviewService {


	public Page reviewList(int page,String t);
	
	//未回复
	@SuppressWarnings("unchecked")
	public List reviewReplyList();
	
	//未审核
	@SuppressWarnings("unchecked")
	public List reviewAuditList(String value);
	
	//审核是否已经通过
	public void reviewAudited(String rId,String tag);
	
	
	//回复中。．．．
	@SuppressWarnings("unchecked")
	public List replyingList(String rId);
	
	//保存回复
	public void update(String rId,String replyContent);
	
	public void Delete(String ID);
}
