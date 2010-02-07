package com.qzgf.NetStore.persistence;
// default package

import java.util.Date;


/**
 * Review entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Review  implements java.io.Serializable {


    // Fields    

     private String reviewId;
     private String productId;
     private String productName;
     private String userId;
     
     private String userName;//外加在xml没有
     
     private String reviewIdentity;
     private String ip;
     private String content;
     private String reviewGrade;
     private Byte isAudit;
     private String title;
     private Byte isReply;
     private Date releaseTime;
     private String replyContent;


    // Constructors

    public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/** default constructor */
    public Review() {
    }

	/** minimal constructor */
    public Review(String reviewId, String productId, String reviewIdentity, String ip, String content) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.reviewIdentity = reviewIdentity;
        this.ip = ip;
        this.content = content;
    }
    
    /** full constructor */
    public Review(String reviewId, String productId, String userId, String reviewIdentity, String ip, String content, String reviewGrade, Byte isAudit, String title, Byte isReply, Date releaseTime) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.userId = userId;
        this.reviewIdentity = reviewIdentity;
        this.ip = ip;
        this.content = content;
        this.reviewGrade = reviewGrade;
        this.isAudit = isAudit;
        this.title = title;
        this.isReply = isReply;
        this.releaseTime = releaseTime;
    }

   
    // Property accessors

    public String getReviewId() {
        return this.reviewId;
    }
    
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewIdentity() {
        return this.reviewIdentity;
    }
    
    public void setReviewIdentity(String reviewIdentity) {
        this.reviewIdentity = reviewIdentity;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getReviewGrade() {
        return this.reviewGrade;
    }
    
    public void setReviewGrade(String reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public Byte getIsAudit() {
        return this.isAudit;
    }
    
    public void setIsAudit(Byte isAudit) {
        this.isAudit = isAudit;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getIsReply() {
        return this.isReply;
    }
    
    public void setIsReply(Byte isReply) {
        this.isReply = isReply;
    }

    public Date getReleaseTime() {
        return this.releaseTime;
    }
    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
   








}