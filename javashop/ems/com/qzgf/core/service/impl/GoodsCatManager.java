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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.qzgf.core.model.Cat;
import com.qzgf.core.model.mapper.CatMapper;
import com.qzgf.core.service.IGoodsCatManager;

public class GoodsCatManager extends BaseSupport<Cat> implements IGoodsCatManager {
 
	public boolean checkname(String name,Integer catid){
		if(name!=null)name=name.trim();
		String sql ="select count(0) from ems_goods_cat where name=? and cat_id!=?";
		if(catid==null){
			catid=0;
		}
		
		int count  = this.baseDaoSupport.queryForInt(sql, name,catid);
		if(count>0) return true;
		else 		return false;
	}
	
	public int delete(int catId) {
		String sql =  "select count(0) from ems_goods_cat where parent_id = ?";
		int count = this.baseDaoSupport.queryForInt(sql,  catId );
		if (count > 0) {
			return 1; // 有子类别
		}

		sql =  "select count(0) from ems_goods where cat_id = ?";
		count = this.baseDaoSupport.queryForInt(sql,  catId );
		if (count > 0) {
			return 2; // 有子类别
		}
		sql =  "delete from  ems_goods_cat   where cat_id=?";
		this.baseDaoSupport.execute(sql,  catId );

		return 0;
	}

	/**
	 * 获取类别详细，将图片加上静态资源服务器地址
	 */
	public Cat getById(int catId) {
		String sql = "select * from ems_goods_cat  where cat_id=?";
		Cat cat =baseDaoSupport.queryForObject(sql, Cat.class, catId);
		String image = cat.getImage();
		if(image!=null){
			image  =UploadUtil.replacePath(image); 
			cat.setImage(image);
		}
		return cat;
	}
	

	
	public List listChildren(Integer catId) {
		 String sql  ="select c.*,'' type_name from ems_goods_cat c where parent_id=?";
		return this.baseDaoSupport.queryForList(sql,new CatMapper(), catId);
	}
	
	
	
	public List<Cat> listAllChildren(Integer catId) {

		String tableName =this.getTableName("ems_goods_cat");
		String sql = "select c.*,t.name as type_name  from  "
				+ tableName
				+ " c  left join "+this.getTableName("ems_goods_type")+" t on c.type_id = t.type_id  "
				+ " order by parent_id,cat_order";// this.findSql("all_cat_list");

		List<Cat> allCatList = daoSupport.queryForList(sql, new CatMapper());
		List<Cat> topCatList  = new ArrayList<Cat>();
		for(Cat cat :allCatList){
			if(cat.getParent_id().compareTo(catId)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("发现子["+cat.getName()+"-"+cat.getCat_id() +"]"+cat.getImage());
				}
				List<Cat> children = this.getChildren(allCatList, cat.getCat_id());
				cat.setChildren(children);
				topCatList.add(cat);
			}
		}
		return topCatList;
	}
 
	
	private List<Cat> getChildren(List<Cat> catList ,Integer parentid){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找["+parentid+"]的子");
		}
		List<Cat> children =new ArrayList<Cat>();
		for(Cat cat :catList){
			if(cat.getParent_id().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug(cat.getName()+"-"+cat.getCat_id()+"是子");
				}
			 	cat.setChildren(this.getChildren(catList, cat.getCat_id()));
				children.add(cat);
			}
		}
		return children;
	}
	

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAdd(Cat cat) {
		 
		baseDaoSupport.insert("ems_goods_cat", cat);
		int cat_id = baseDaoSupport.getLastId("ems_goods_cat");
		String sql = "";

		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) {
			sql = "select * from ems_goods_cat  where cat_id=?";
			Cat parent = baseDaoSupport.queryForObject(sql, Cat.class, cat
					.getParent_id());
			cat.setCat_path(parent.getCat_path() + "|" + cat_id);
		} else {
			cat.setCat_path(cat.getParent_id() + "|" + cat_id);
		}

		sql = "update ems_goods_cat set  cat_path=? where  cat_id=?";
		baseDaoSupport.execute(sql, new Object[] { cat.getCat_path(), cat_id });

	}

	
	public void update(Cat cat) {
		checkIsOwner(cat.getCat_id());
		// 如果有父类别，根据父的path更新这个类别的path信息
		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) {

			String sql = "select * from ems_goods_cat where cat_id=?";
			Cat parent = baseDaoSupport.queryForObject(sql, Cat.class, cat
					.getParent_id());
			cat.setCat_path(parent.getCat_path() + "|" + cat.getCat_id());
		} else {
			// 顶级类别，直接更新为parentid|catid
			cat.setCat_path(cat.getParent_id() + "|" + cat.getCat_id());
		}

		HashMap map = new HashMap();
		map.put("name", cat.getName());
		map.put("parent_id", cat.getParent_id());
		map.put("cat_order", cat.getCat_order());
		map.put("type_id", cat.getType_id());
		map.put("cat_path", cat.getCat_path());
		map.put("list_show", cat.getList_show());
		map.put("image", cat.getImage());
		baseDaoSupport.update("ems_goods_cat", map, "cat_id=" + cat.getCat_id());
	}

	
	protected void checkIsOwner(Integer catId) {
//		String sql = "select userid from  goods_cat  where cat_id=?";
//		int userid = saasDaoSupport.queryForInt(sql, catId);
//		super.checkIsOwner(userid);
	}

	

	/**
	 * 保存分类排序
	 * 
	 * @param cat_ids
	 * @param cat_sorts
	 */
	
	public void saveSort(int[] cat_ids, int[] cat_sorts) {
		String sql = "";
		if (cat_ids != null) {
			for (int i = 0; i < cat_ids.length; i++) {
			    sql= "update  ems_goods_cat  set cat_order=? where cat_id=?" ;
			    baseDaoSupport.execute(sql,  cat_sorts[i], cat_ids[i] );
			}
		}
	}
 
 

	
}
