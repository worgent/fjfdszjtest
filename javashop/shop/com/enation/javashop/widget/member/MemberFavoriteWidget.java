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
package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IFavoriteManager;

public class MemberFavoriteWidget extends AbstractMemberWidget {
	
	private IFavoriteManager favoriteManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if(action.equals("")){
		this.setPageName("member_favorite");
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		int pageSize = 20;
		Page favoritePage = favoriteManager.list(Integer.valueOf(page), pageSize);
		Long pageCount = favoritePage.getTotalPageCount();
		List favoriteList = (List)favoritePage.getResult();
		favoriteList = favoriteList == null ? new ArrayList() : favoriteList;
		this.putData("pageSize", pageSize);
		this.putData("page", page);
		this.putData("favoriteList", favoriteList);
		this.putData("pageCount", pageCount);
		this.putData("GoodsPic", new GoodsPicDirectiveModel());
		}else if(action.equals("delete")){
			String favorite_id = request.getParameter("favorite_id");
			try{
				this.favoriteManager.delete(Integer.valueOf(favorite_id));
				this.showSuccess("删除成功","商品收藏", "member_favorite.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("删除失败", "商品收藏", "member_favorite.html");
			}
		}
	}

	public IFavoriteManager getFavoriteManager() {
		return favoriteManager;
	}

	public void setFavoriteManager(IFavoriteManager favoriteManager) {
		this.favoriteManager = favoriteManager;
	}

}
