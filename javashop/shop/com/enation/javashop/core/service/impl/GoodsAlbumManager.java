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
package com.enation.javashop.core.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.image.IThumbnailCreator;
import com.enation.framework.image.ThumbnailCreatorFactory;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsAlbumManager;

/**
 * saas式的商品相册管理
 * @author kingapex
 * 2010-2-21上午12:40:05
 */
public final class GoodsAlbumManager extends BaseSupport<Goods> implements IGoodsAlbumManager {

	/**
	 * 删除指定的图片<br>
	 * 将本地存储的图片路径替换为实际硬盘路径<br>
	 * 不会删除远程服务器图片
	 */
	public void delete(String photoName) {
		 if(photoName!=null &&  !photoName.startsWith("http://static.enationsoft.com")){
			 photoName  =UploadUtil.replacePath(photoName);
			 photoName =photoName.replaceAll(EopSetting.IMG_SERVER_DOMAIN , EopSetting.IMG_SERVER_PATH);
			 FileUtil.delete(photoName);
			 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_thumbnail"));
			 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_small"));
			 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_big"));
		 }
	}
	
	public void delete(Integer[] goodsid){
		String id_str = StringUtil.arrayToString(goodsid, ",");
		String sql = "select image_file from goods where goods_id in ("+ id_str +")";
		List<Map> goodsList  = this.baseDaoSupport.queryForList(sql);
		for(Map goods:goodsList){
			String name =(String)goods.get("image_file");
			if(name!=null &&  !name.equals("")){
				String[] pname = name.split(",");
				for(String n:pname)
					this.delete(n);
			}
		}  
		
	}
	
	
	public String[] upload(File file, String fileFileName) {
		String fileName = null;
		String filePath = "";

		String[] path = new String[2];
		
		if (file != null && fileFileName != null) {
			String ext = FileUtil.getFileExt(fileFileName);
			fileName = DateUtil.toString(new Date(), "yyyyMMddHHmmss") + StringUtil.getRandStr(4) + "." + ext;
			filePath = EopSetting.IMG_SERVER_PATH + getContextFolder()+ "/attachment/goods/";
			path[0] = EopSetting.IMG_SERVER_DOMAIN + getContextFolder()+ "/attachment/goods/"+fileName;
			filePath += fileName;
			FileUtil.createFile(file, filePath);

		}
		return path;
	}

	public static String getContextFolder(){
		return EopContext.getContext().getContextPath();
	}

	
	public void createThumb(String filepath,String thumbName,int thumbnail_pic_width,int thumbnail_pic_height) {
		
		 if(filepath!=null &&  !filepath.startsWith("http://static.enationsoft.com")){
			filepath=filepath.replaceAll( EopSetting.IMG_SERVER_DOMAIN, EopSetting.IMG_SERVER_PATH );
			thumbName=thumbName.replaceAll( EopSetting.IMG_SERVER_DOMAIN, EopSetting.IMG_SERVER_PATH );
			IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator( filepath, thumbName);
			thumbnailCreator.resize(thumbnail_pic_width, thumbnail_pic_height);	
		 }
	 
	}
	
}
