package com.qzgf.NetStore.service.impl;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.INewsDAO;
import com.qzgf.NetStore.persistence.News;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.INewsService;

public class NewsService implements INewsService {
    private INewsDAO newsDAO;
	public INewsDAO getNewsDAO() {
		return newsDAO;
	}
	public void setNewsDAO(INewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page queryAllNews(int npage){
		return this.newsDAO.queryAllNews(npage);
	}
   
	@SuppressWarnings("unchecked")
	public Map queryNewsById(String id){
		return this.newsDAO.queryNewsById(id);
	}
	
	public boolean updateNews(News news){
		return this.newsDAO.updateNews(news);
	}
	
	@SuppressWarnings("unchecked")
	public List queryGroupsByApplicationType(String applicationType){
		return this.newsDAO.queryGroupsByApplicationType(applicationType);
	}
	@Override
	public boolean addGroup(String groupName, String description,
			String applicationType, String remark) {
		return this.newsDAO.addGroup(groupName, description, applicationType, remark);
	}
	@SuppressWarnings("unchecked")
	public Map queryGroupByGroupId(String groupId){
		return this.newsDAO.queryGroupByGroupId(groupId);
	}
	
	public boolean updateGroup(String groupId,String groupName,String description){
		return this.newsDAO.updateGroup(groupId, groupName, description);
	}
	
	public boolean deleteGroup(String groupId){
		return this.newsDAO.deleteGroup(groupId);
	}
	
	public boolean addNews(News news){
		return this.newsDAO.addNews(news);
	}
	
	public boolean deleteNews(String[] newsIds){
		return this.newsDAO.deleteNews(newsIds);
	}
	
	public Page queryNews(int npage){
		return this.newsDAO.queryNews(npage);
	}
}
