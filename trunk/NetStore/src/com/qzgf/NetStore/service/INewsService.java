package com.qzgf.NetStore.service;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.persistence.News;
import com.qzgf.NetStore.pub.Page;

public interface INewsService {
	@SuppressWarnings("unchecked")
	public Page queryAllNews(int npage);
	@SuppressWarnings("unchecked")
	public Map queryNewsById(String id);
	public boolean updateNews(News news);
	@SuppressWarnings("unchecked")
	public List queryGroupsByApplicationType(String applicationType);
	public boolean addGroup(String groupName, String description,
			String applicationType, String remark);
	@SuppressWarnings("unchecked")
	public Map queryGroupByGroupId(String groupId);
	public boolean updateGroup(String groupId,String groupName,String description);
	public boolean deleteGroup(String groupId);
	public boolean addNews(News news);
	public boolean deleteNews(String[] newsIds);
	public Page queryNews(int npage);
}
