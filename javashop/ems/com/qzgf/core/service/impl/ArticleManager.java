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
package com.qzgf.core.service.impl;

import java.util.List;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.database.StringMapper;
import com.qzgf.core.model.Article;
import com.qzgf.core.service.IArticleManager;


public class ArticleManager extends BaseSupport implements IArticleManager {

	public void saveAdd(Article article){
		if(article.getCat_id() != null){
			String sql = "select count(*) from ems_article_cat where cat_id=?";
			int count = this.baseDaoSupport.queryForInt(sql, article.getCat_id());	
			    if(count <= 0)
			    	throw new ArticleRuntimeException(2);//没有文章分类
		}
		article.setCreate_time(System.currentTimeMillis());
		this.baseDaoSupport.insert("article", article);
	}

	public void saveEdit(Article article){
		if(article.getCat_id() != null){
			String sql = "select count(*) from ems_article_cat where cat_id=?";
			int count = this.baseDaoSupport.queryForInt(sql, article.getCat_id());	
			    if(count <= 0)
			    	throw new ArticleRuntimeException(2);//没有文章分类
		}
		this.baseDaoSupport.update("article", article, "id=" + article.getId());
	}

	public Article get(Integer id){
		
			String sql = "select * from ems_article where id=?";
			 Article article = null;
			try{
				article = (Article) this.baseDaoSupport.queryForObject(sql, Article.class, id);
				
			}catch(Exception ex){
				article = null;
			}
			if(article == null)
				throw new ArticleRuntimeException(1);//没有此文章
			return article;
		
	
	}

	/**
	 * 读取文章列表
	 * 
	 * @param cat_id
	 * @return
	 */
	public List listByCatId(Integer cat_id) {
		String cat_path = (String)this.baseDaoSupport.queryForObject("select cat_path from ems_article_cat where cat_id = ?", new StringMapper(), cat_id);
		String sql = "select a.*,b.cat_path from "+this.getTableName("ems_article") + " a left join " + this.getTableName("ems_article_cat") + " b on b.cat_id = a.cat_id where cat_path like '" + cat_path + "%' order by create_time ";
		List list = this.daoSupport.queryForList(sql);
		return list;
	}
	
	public String getCatName(Integer cat_id){
		String sql ="select name from ems_article_cat where cat_id=?";
		String cat_name = null;
		try{
		    cat_name =(String)this.baseDaoSupport.queryForObject(sql, String.class, cat_id); 
		}catch(Exception ex){
			cat_name = null;
		}
		if( cat_name == null)
			throw new ArticleRuntimeException(1);//没有文章
		return cat_name;
	}

	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from ems_article  where id in (" + id
				+ ")";
		this.baseDaoSupport.execute(sql);
		
	}

	
	public Page pageByCatId(Integer pageNo, Integer pageSize, Integer catId) {
		String cat_path = (String)this.baseDaoSupport.queryForObject("select cat_path from ems_article_cat where cat_id = ?", new StringMapper(), catId);
		String sql = "select a.*,b.cat_path from " + this.getTableName("ems_article") + " a left join " + this.getTableName("ems_article_cat") + " b on b.cat_id = a.cat_id where cat_path like '" + cat_path + "%' order by create_time desc";
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		return page;
	}

	
	public List topListByCatId(Integer catId, Integer topNum) {
		String sql = "select * from ems_article where cat_id=? order by create_time limit 0," + topNum;
		List list = this.baseDaoSupport.queryForList(sql, catId);
		return list;
	}
}
