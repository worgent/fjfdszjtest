package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.dao.IReviewDAO;
import com.qzgf.NetStore.persistence.Review;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class ReviewDAO  extends BaseDao  implements IReviewDAO{
	//UtilDB du = new UtilDB();
	
	@SuppressWarnings("unchecked")
	//所有评论列表 t=1
	//未回复评论   t=2
	//未审核评论   t=3
	//已审核评论   t=4
	
	
	
	
	public Page reviewList(int npage,String t)
	{
		
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		String sql="";
		
		if(t.equals("2"))//未回复评论   t=2
		{
	    sql="select  a.productId, reviewId,productName,isAudit,title,releaseTime  from  " +
	    	"t_review  a  left join  t_product b on  a.productId=b.productId  " +
	    	" where a.isReply='0' order by releaseTime desc";
		}
		else if(t.equals("3"))//未审核评论   t=3
		{
		sql="select  a.productId, reviewId,productName,isAudit,title,releaseTime  from  " +
		    " t_review  a  left join  t_product b on  a.productId=b.productId  " +
		    " where a.isAudit='0' order by releaseTime desc";	
		}
		else if(t.equals("4"))//已审核评论   t=4
		{
		sql="select  a.productId, reviewId,productName,isAudit,title,releaseTime from " +
				" t_review  a  left join  t_product b on  a.productId=b.productId  " +
				" where a.isAudit='1' order by releaseTime desc";	
		}
		else  //t=1为默认的
		{
		sql="select  a.productId, reviewId,productName,isAudit,title,releaseTime from  " +
				" t_review  a  left join  t_product b on  a.productId=b.productId " +
				" order by releaseTime desc";	
		}
		
		Page page=utilDB.executeQueryByPageForMySql(sql,npage,Page.DEFAULT_PAGESIZE);
		
	
		ResultSet rs=page.getRowset();
	    List results = new ArrayList();	

		try {
			
			while(rs.next()){
				//Review review=new Review();
				Map map=new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("reviewId", rs.getString("reviewId"));
				map.put("productName", rs.getString("productName"));
				map.put("isAudit", rs.getString("isAudit"));
				map.put("title", rs.getString("title"));
				map.put("releaseTime", rs.getTimestamp("releaseTime"));
				
				/*review.setProductId(rs.getString("productId"));
				review.setReviewId(rs.getString("reviewId"));
				review.setProductName(rs.getString("productName"));
				review.setIsAudit(rs.getByte("isAudit"));
				review.setTitle(rs.getString("title"));
				review.setReleaseTime(rs.getTimestamp("releaseTime"));*/
				
				
				results.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		page.setResultList(results);	
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return page;
	}

	
	//查询未回复
	@SuppressWarnings("unchecked")
	public List reviewReplyList()
	{
		
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
	  String sql="select  *  from  t_review  a  left join  t_product b on  a.productId=b.productId  where a.isReply='0'";
	
		ResultSet rs=null;
	    List results = new ArrayList();	

		try {
			rs = utilDB.executeQuery(sql);
			while(rs.next()){
				Review review=new Review();
				
				review.setProductId(rs.getString("productId"));
				review.setReviewId(rs.getString("reviewId"));
				review.setProductName(rs.getString("productName"));
				review.setIsAudit(rs.getByte("isAudit"));
				review.setTitle(rs.getString("title"));
				review.setReleaseTime(rs.getTimestamp("releaseTime"));
				results.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return results;	
	}
	
	//查询未审批
	@SuppressWarnings("unchecked")
	public List reviewAuditList(String value)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String sql="";
		if(value.equals("0")) //0:未审核
		{
		sql="select  *  from  t_review  a  left join  t_product b on  a.productId=b.productId  where a.isAudit='0'";
		}
		else //1：已审核 
		{
		sql="select  *  from  t_review  a  left join  t_product b on  a.productId=b.productId  where a.isAudit='1'";
		}
		
		ResultSet rs=null;
	    List results = new ArrayList();	
		try {
			rs = utilDB.executeQuery(sql);
			while(rs.next()){
				Review review=new Review();
				review.setProductId(rs.getString("productId"));
				review.setReviewId(rs.getString("reviewId"));
				review.setProductName(rs.getString("productName"));
				review.setIsAudit(rs.getByte("isAudit"));
				review.setTitle(rs.getString("title"));
				review.setReleaseTime(rs.getTimestamp("releaseTime"));
				results.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;	
	}
	
	
	
	
	//点击回复．．．
	@SuppressWarnings("unchecked")
	public List replyingList(String rId)
	{
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		 String sql="select  reviewId,a.UserId,title,Content,replyContent  from t_review a " +
		 		"  left join t_user b on a.UserId=b.UserId where reviewId='"+rId+"'";
			ResultSet rs=null;
		    List results = new ArrayList();	
			try {
				rs = utilDB.executeQuery(sql);
				while(rs.next()){
					Review review=new Review();
					review.setReviewId(rs.getString("reviewId"));
					review.setUserId(rs.getString("UserId"));
					//review.setUserName(rs.getString("userName"));
					review.setTitle(rs.getString("title"));
					review.setContent(rs.getString("Content"));
					review.setReplyContent(rs.getString("replyContent"));
					results.add(review);
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
			
			
			
			return results;	
	}
	
	
	
	
	
	
	
	
	
	//删除
	public void Delete(String ID)
	{
		
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String str=" delete from t_review where ReviewId='"+ID+"'";
		/* try {
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		utilDB.executeUpdate(str);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void update(String rId,String replyContent)
	{
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String str="update  t_review    set isReply=1,ReplyContent='"+replyContent+"'" +
				" where ReviewId='"+rId+"'";
		
		/*System.out.println(str);
		 try {
			du.stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	    utilDB.executeUpdate(str);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	//审核通过－更新标记
	public void reviewAudited(String rId,String tag)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		String str="";
		if(tag.equals("1"))
		{
		 str="update  t_review  set  isAudit=1 where ReviewId='"+rId+"'";
		}
		else
		{
		 str="update  t_review  set  isAudit=0 where ReviewId='"+rId+"'";
		}
		
		System.out.println(str);
		/* try {
			du.stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		*/
		
		 utilDB.executeUpdate(str);
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
