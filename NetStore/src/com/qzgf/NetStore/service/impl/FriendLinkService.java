package com.qzgf.NetStore.service.impl;
import com.qzgf.NetStore.dao.IFriendLinkDAO;

import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IFriendLinkService;

public class FriendLinkService implements IFriendLinkService{
	
	private IFriendLinkDAO friendLinkDAO;
	
	public Page friendLinkList(int nPage)
	{
		return this.friendLinkDAO.friendLinkList(nPage);
	}
	
	public void addFriendLink(String urlID,String urlTitle,String url,String no){
		this.friendLinkDAO.addFriendLink(urlID, urlTitle, url, no);
	}
	
	//É¾³ý
	public void delete(String urlID){
		this.friendLinkDAO.delete(urlID);
	}
	
	
	//¸üÐÂ
	public void update(String urlId,String urlTitle,String url,String no)
	{
		this.friendLinkDAO.update(urlId, urlTitle, url, no);
	}
	

	public IFriendLinkDAO getFriendLinkDAO() {
		return friendLinkDAO;
	}

	public void setFriendLinkDAO(IFriendLinkDAO friendLinkDAO) {
		this.friendLinkDAO = friendLinkDAO;
	}
	
	
	
}
