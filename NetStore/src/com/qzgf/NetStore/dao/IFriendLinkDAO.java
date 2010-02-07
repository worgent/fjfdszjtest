package com.qzgf.NetStore.dao;

import com.qzgf.NetStore.pub.Page;

public interface IFriendLinkDAO {	
	
	public Page friendLinkList(int page);	
	
	public void addFriendLink(String urlID,String urlTitle,String url,String no);
	
	public void delete(String urlID);
	
	public void update(String urlId,String urlTitle,String url,String no);
}
