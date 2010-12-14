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
package com.enation.javashop.widget.gallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.app.setting.service.ISettingService;
import com.enation.eop.utils.UploadUtil;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * 商品相册挂件
 * @author kingapex
 *
 */
public class GoodsGalleryWidget extends AbstractGoodsDetailWidget {
	private ISettingService settingService;
	
	protected void config(Map<String, String> params) {
		 
	}

	
	protected void execute(Map<String, String> params,Map goods) {

		String image_file = goods.get("image_file")==null?"":goods.get("image_file").toString();
		List<Gallery> galleryList = new ArrayList<Gallery>();
		if(image_file!=null && !image_file.equals("")){
		
			String[] imgs= image_file.split(",");
		
	 
			
			for(String img : imgs){
				
				Gallery gallery = new Gallery();
				gallery.setSmall(UploadUtil.getThumbPath(img, "_small"));
				gallery.setBig(img);
				gallery.setThumbnail(UploadUtil.getThumbPath(img, "_thumbnail") );
				galleryList.add(gallery);
				
			}
			
		}
		
		String album_pic_width = this.settingService.getSetting("photo", "detail_pic_width");
		String album_pic_height = this.settingService.getSetting("photo", "detail_pic_height");
		
		this.putData("album_pic_width", album_pic_width);
		this.putData("album_pic_height", album_pic_height);
		
		putData("galleryList", galleryList);
		//setPageFolder("/plugin/kingapex_gallery");
		setPageName("gallery");
		putData("goods", goods);
		

		
	}

    public class Gallery{
    	private String small;
    	private String thumbnail;
    	private String big;
		public String getSmall() {
			return small;
		}
		public void setSmall(String small) {
			this.small = small;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getBig() {
			return big;
		}
		public void setBig(String big) {
			this.big = big;
		}
    	
    }

	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}
	

}
