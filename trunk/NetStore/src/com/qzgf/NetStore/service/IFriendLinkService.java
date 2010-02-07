package com.qzgf.NetStore.service;




import com.qzgf.NetStore.pub.Page;

public interface IFriendLinkService {
	
	public Page friendLinkList(int nPage);	
	
	public void addFriendLink(String urlID,String urlTitle,String url,String no);
	
	public void delete(String urlID);
	
	
	public void update(String urlId,String urlTitle,String url,String no);
	
	

}
