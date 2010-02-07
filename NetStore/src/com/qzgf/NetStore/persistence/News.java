package com.qzgf.NetStore.persistence;
// default package

import java.util.Date;


/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News  implements java.io.Serializable {


    // Fields    

     private String newsId;
     private String newsTitle;
     private String newsContent;
     private String releaseMan;
     private Date releaseTime;
     private Byte ifCommend;
     private Byte ifFine;
     private String newsAuthor;


    // Constructors

    /** default constructor */
    public News() {
    }

    
    /** full constructor */
    public News(String newsTitle, String newsContent, String releaseMan, Date releaseTime, Byte ifCommend, Byte ifFine, String newsAuthor) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.releaseMan = releaseMan;
        this.releaseTime = releaseTime;
        this.ifCommend = ifCommend;
        this.ifFine = ifFine;
        this.newsAuthor = newsAuthor;
    }

   
    // Property accessors

    public String getNewsId() {
        return this.newsId;
    }
    
    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return this.newsTitle;
    }
    
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return this.newsContent;
    }
    
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getReleaseMan() {
        return this.releaseMan;
    }
    
    public void setReleaseMan(String releaseMan) {
        this.releaseMan = releaseMan;
    }

    public Date getReleaseTime() {
        return this.releaseTime;
    }
    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Byte getIfCommend() {
        return this.ifCommend;
    }
    
    public void setIfCommend(Byte ifCommend) {
        this.ifCommend = ifCommend;
    }

    public Byte getIfFine() {
        return this.ifFine;
    }
    
    public void setIfFine(Byte ifFine) {
        this.ifFine = ifFine;
    }

    public String getNewsAuthor() {
        return this.newsAuthor;
    }
    
    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }
   








}