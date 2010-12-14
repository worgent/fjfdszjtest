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
package com.enation.framework.image;

import com.enation.framework.image.impl.JavaImageIOCreator;
import com.enation.framework.image.impl.ImageMagickCreator;

/**
 * 缩略图生成器工厂<br>
 * 
 * @author kingapex
 * 2010-7-10下午11:40:28
 */
public abstract class ThumbnailCreatorFactory {
	private ThumbnailCreatorFactory(){}
	public static String CREATORTYPE="javaimageio";
	
	/**
	 * 返回缩略图生成器
	 * @param source 图片原文件路径
	 * @param target 图片缩略图路径
	 * @return 
	 * 当{@link #CREATORTYPE} 为javaimageio时使用{@link JavaImageIOCreator }生成器<br>
	 * 当{@link #CREATORTYPE} 为imagemagick时使用{@link ImageMagickCreator }生成器<br>
	 */
	public static final IThumbnailCreator getCreator(String source,String target){
		if(CREATORTYPE.equals("javaimageio")){
			return new JavaImageIOCreator(source, target);
		}
		
		if(CREATORTYPE.equals("imagemagick")){
			return new ImageMagickCreator(source, target);
		}
		
		
		return new JavaImageIOCreator(source, target);
	}
}
