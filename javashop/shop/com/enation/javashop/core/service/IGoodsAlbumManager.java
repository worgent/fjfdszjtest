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
package com.enation.javashop.core.service;

import java.io.File;

/**
 * 相册管理接口
 * @author kingapex
 * 2010-2-21上午12:35:02
 */
public interface IGoodsAlbumManager {
	
	/**
	 * 上传商品图片
	 * @param file
	 * @param fileFileName
	 * @return
	 */
	public  String[] upload(File file, String fileFileName) ;
	
	
	/**
	 * 生成缩略图，并返回缩略图的路径
	 * @param photoName
	 * @return
	 */
	public void createThumb(String filepath,String thumbName,int width,int height);
	
	
	/**
	 * 删除商品图片
	 * @param photoName
	 */
	public  void delete(String photoName);
	
	/**
	 * 批量删除某些商品的图片
	 * @param goodsid
	 */
	public void delete(Integer[] goodsid);
	
}
