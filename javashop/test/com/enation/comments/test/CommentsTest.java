/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.comments.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.database.Page;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.Comments;
import com.enation.javashop.core.service.ICommentsManager;

 
public class CommentsTest extends SpringTestSupport {

	private ICommentsManager commentsManager;
	private ApplicationContext context;
    
	@Before
	public void mock() {
	 
		commentsManager =  getBean("commentsManager");

		
		 //mock site 
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		
	}
	
	@Test
	public void getComments(){
		Page page = commentsManager.pageComments(1, 15, "discuss");
		List<Map> list = (List<Map>) (page.getResult());
		for(Map map : list){
			System.out.println(map.get("comment_id")+":main");
			for(Comments comments:(List<Comments>)map.get("list")){
				System.out.println(comments.getComment_id()+":sub");
			}
		}
	}
	
	@Test
	public void getComments_display(){
		Page page = commentsManager.pageComments_Display(2, 3, 2, "discuss");
		List<Map> list = (List<Map>) (page.getResult());
		for(Map map : list){
			System.out.println(map.get("comment_id")+":main");
			for(Comments comments:(List<Comments>)map.get("list")){
				System.out.println(comments.getComment_id()+":sub");
			}
		}
	}
	
	@Test
	public void addComments(){
		Comments comments = new Comments();
		comments.setFor_comment_id(1);
		comments.setAuthor("王峰");
		comments.setTime((new Date()).getTime());
		comments.setTitle("测试");
		commentsManager.addComments(comments);
	}
	@Test
	public void widgetTest(){
		IWidget widget= (IWidget)this.getBean("goods_comments");
		String content = widget.process(null);
		System.out.println(content);		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
