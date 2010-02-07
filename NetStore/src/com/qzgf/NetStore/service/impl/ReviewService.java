package com.qzgf.NetStore.service.impl;


import java.util.List;


import com.qzgf.NetStore.dao.IReviewDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IReviewService;

public class ReviewService implements IReviewService {

	private IReviewDAO reviewDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page reviewList(int page,String t) {
		return this.reviewDAO.reviewList(page,t);
	}
    
	//未回复
	@SuppressWarnings("unchecked")
	public List reviewReplyList()
	{
		return this.reviewDAO.reviewReplyList();
	}

	//未审核
	@SuppressWarnings("unchecked")
	public List reviewAuditList(String value){
		return this.reviewDAO.reviewAuditList(value);
		
	}
	
	//审核是否已经通过
	public void reviewAudited(String rId,String tag)
	{
	    this.reviewDAO.reviewAudited(rId, tag);
	}
	
	
	
	//回复中。．．．．
	@SuppressWarnings("unchecked")
	public List replyingList(String rId)
	{
		return this.reviewDAO.replyingList(rId);
	}
	
	//保持回复
	public void update(String rId,String replyContent)
	{
		this.reviewDAO.update(rId, replyContent);
	}
	
	
	public void Delete(String ID)
	{
		
		this.reviewDAO.Delete(ID);
	}
	
	
	public IReviewDAO getReviewDAO() {
		return reviewDAO;
	}

	public void setReviewDAO(IReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}

}
