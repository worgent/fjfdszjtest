<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="toolsposition" eop_type="widget_tool">
<div id="eop_widget_tool" >

<div id="widget_setting"></div>
<form id="pageForm" method="POST"><input type="hidden" id="bodyHtml" name="bodyHtml"></form>

<div id="custom_panel" class="gb_custom_l">
   <div class="gb_custom_r">
       <div class="gb_custom_m">
				 <div class="nav_tools">
				    <div class="nav_custom_img"></div>
					<div class="nav_custom_left" >
					    <ul id="customTab">
					    <li id="custom_menu_start1" ></li>
						<li id="custom_menu_widget2" class="now"><span><a href="javascript:;" onclick="tag(2);">版式/布局</a></span></li>
						<li id="custom_menu_widget3"><span><a href="javascript:;" onclick="tag(3);">风格</a></span></li>
						<li id="custom_menu_widget1"><span><a href="javascript:;" onclick="tag(1);">挂件</a></span></li>
						</ul>
						
					</div>
				
					<div class="nav_custom_right" ><input type="radio" name="layout_type" id="layout_type_auto" checked="true"/>自动式 <input type="radio"  name="layout_type"  id="layout_type_free" />自由式
					<button id="bt_save_custom" class="bt_save_custom" onclick="javascript:savePage(true);">保存</button>
					<button class="bt_cancel_custom" onclick="javascript:top.location='index.html'">退出</button>
					<button id="bt_hidden_custom" class="bt_cancel_custom" onclick="javascript:hid(1);">隐藏</button>
					</div>
				 </div> 
				<!-- 挂件 -->
				<div id="customFrames1" style="display:none;">
				
				<div id="widgetall">
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" > 
							 
							   <ul  class="widget_all">
								 <li class="now" id="admincol_all"><span >全部挂件</span></li>
								 <li class=" " id="admincol_core"><span >核心挂件</span></li>
								 <li class=" " id="admincol_diy"><span >自定内容</span></li>
								 <li class=" " id="admincol_menu"><span >导航菜单</span></li>
								 <li class=" " id="admincol_page"><span >网页模块</span></li>
								 <li class=" " id="admincol_news"><span >文章模块</span></li>
								 <li class=" " id="admincol_comment"><span >互动点评</span></li>
								 <li class=" " id="admincol_member"><span >会员模块</span></li>
								 <li class=" " id="admincol_shop"><span >网上购物</span></li>
								 <li class=" " id="admincol_search"><span >全站搜索</span></li>
								 <li class=" " id="admincol_effect"><span >素材特效</span></li>
								 <li class=" " id="admincol_job"><span >企业招聘</span></li>
								 <li class=" " id="admincol_advs"><span >网站广告</span></li>
								 <li class=" " id="admincol_tools"><span >辅助工具</span></li>
								 <li class=" " id="admincol_feedback"><span >留言反馈</span></li>
							   </ul>
						 
					         <div class="widget_title">选择挂件类型</div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 <div id="widgetsystem">
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m">
				<div id="systemwidgetLayout" class="widget_types">
				 
					        <li eop_type="widgetHandle" widgetType="goods_list" appId="shop"><a href="javascript:void(0)">商品列表</a></li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							<li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">商品列表</li>
							<li eop_type="widgetHandle" widgetType="article_list" appId="cms">文章列表</li>
							
			 	
				</div>
					        <div class="widget_title">选择可用挂件，拖动添加</div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
				 <div id="widgetcreate">
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" > 
							<div id="widget_used" class="widget_create">
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">
								  <div class="widget_t">商品列表</div> 
								   <div class="widget_s">
		                              <div id="d_1" class="widget_del" title="删除"></div>
		                              <div id="s_1" class="widget_edit" title="设置"></div>
	                              </div>
                              </li>
							 <li eop_type="widgetHandle" widgetType="article_list" appId="cms">
								 <div class="widget_t">文章列表</div> 
								   <div class="widget_s">
		                              <div id="d_2" class="widget_del" title="删除"></div>
		                              <div id="s_2" class="widget_edit" title="设置"></div>
	                              </div>
							 </li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">
								  <div class="widget_t">商品列表</div> 
								   <div class="widget_s">
		                              <div id="d_3" class="widget_del" title="删除"></div>
		                              <div id="s_3" class="widget_edit" title="设置"></div>
	                              </div>
							 </li>
							 <li eop_type="widgetHandle" widgetType="article_list" appId="cms">
								 <div class="widget_t">文章列表</div> 
								   <div class="widget_s">
								      <div id="d_4" class="widget_del" title="删除"></div>
									  <div id="s_4" class="widget_edit" title="设置"></div>
	                              </div>
							 </li>
							 <li eop_type="widgetHandle" widgetType="goods_list" appId="shop">
								  <div class="widget_t">商品列表</div> 
								   <div class="widget_s">
								      <div id="d_5" class="widget_del" title="删除"></div>
									  <div id="s_5" class="widget_edit" title="设置"></div>
	                              </div>
							 </li><li eop_type="widgetHandle" widgetType="article_list" appId="cms">
								 <div class="widget_t">文章列表</div> 
								   <div class="widget_s">
								      <div id="d_6" class="widget_del" title="删除"></div>
									  <div id="s_6" class="widget_edit" title="设置"></div>
	                              </div>
							 </li>
							
							</div>
					         <div class="widget_title">本页已有挂件</div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
				</div>
				
				
				<!-- 布局 -->
				<div id="customFrames2" >
				 
				 <div id="widgetpage1" >
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" >
							<div id="createwidget" class="page_style">
							 
							<button id="layout_3_1" class="layout_13 " onclick=" "><span><span class="none">1:3</span></span></button>
							<button id="layout_3_2" class="layout_31 now" onclick=" "><span><span class="none">3:1</span></span></button>
							<button id="layout_3_4" class="layout_121" onclick=" "><span><span class="none">1:2:1</span></span></button>
							<button id="layout_3_5" class="layout_112" onclick=" "><span><span class="none">1:1:2</span></span></button>
							<button id="layout_3_6" class="layout_211" onclick=" "><span><span class="none">2:1:1</span></span></button>
							<button id="layout_3_3" class="layout_22" onclick=" "><span><span class="none">2:2</span></span></button>
							<button id="layout_3_7" class="layout_14" onclick=" "><span><span class="none">1:4</span></span></button>
							<button id="layout_3_8" class="layout_41" onclick=" "><span><span class="none">4:1</span></span></button>
							<button id="layout_3_9" class="layout_122" onclick=" "><span><span class="none">1:2:2</span></span></button>
							<button id="layout_3_10" class="layout_221" onclick=" "><span><span class="none">2:2:1</span></span></button>
							<button id="layout_3_11" class="layout_32" onclick=" "><span><span class="none">3:2</span></span></button>
							<button id="layout_3_12" class="layout_113" onclick=" "><span><span class="none">1:1:3</span></span></button>
							<button id="layout_3_13" class="layout_311" onclick=" "><span><span class="none">3:1:1</span></span></button>
							<button id="layout_3_14" class="layout_131" onclick=" "><span><span class="none">1:3:1</span></span></button>
							<button id="layout_3_0" class="layout_0" onclick=" "><span><span class="none">0</span></span></button>
							
							</div>
					         <div class="widget_title">布局<span >(请您选择喜欢的形式)</span></div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
				  <div id="widgetpage"  >
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" >
					<div  id="widgetlistpage_color" >
						 <div id="pagebgcolor">
                                <div class="colorlist" style="background: rgb(53, 106, 160) none repeat scroll 0% 0%;"></div>
                                <div class="colorlist" style="background: rgb(63, 76, 107) none repeat scroll 0% 0%;"></div>
                                <div class="colorlist" style="background: rgb(107, 186, 112) none repeat scroll 0% 0%; "></div>
                                <div class="colorlist" style="background: rgb(115, 136, 10) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(208, 31, 60) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(209, 86, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(176, 43, 44) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 0, 132) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(64, 150, 238) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(0, 110, 46) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(0, 140, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 116, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 26, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(54, 57, 61) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(195, 217, 255) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(205, 235, 139) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 255, 136) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(249, 247, 237) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(0, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(204, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(105, 105, 105) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(128, 128, 128) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(169, 169, 169) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(192, 192, 192) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(211, 211, 211) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(220, 220, 220) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(238, 238, 238) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(245, 245, 245) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(128, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(139, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(178, 34, 34) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(165, 42, 42) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(230, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 0, 0) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(205, 92, 92) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(188, 143, 143) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(240, 128, 128) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 250, 250) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(250, 128, 114) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 228, 225) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 99, 71) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(233, 150, 122) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 69, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 127, 80) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 160, 122) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(160, 82, 45) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 245, 238) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(139, 69, 19) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(210, 105, 30) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(244, 164, 96) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 218, 137) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(205, 133, 63) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(250, 240, 230) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 140, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 228, 196) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(222, 184, 135) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(210, 180, 140) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(250, 235, 215) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 222, 173) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 235, 205) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 239, 213) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 153, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 102, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 165, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 228, 181) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(245, 222, 179) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(253, 245, 230) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 250, 240) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(184, 134, 11) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(218, 165, 32) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 248, 220) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 215, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(240, 230, 140) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(238, 232, 170) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 250, 205) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(189, 183, 107) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(128, 128, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 255, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 255, 224) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 255, 240) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(250, 250, 210) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(245, 245, 220) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(107, 143, 35) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(154, 205, 50) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(85, 107, 47) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(173, 255, 47) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(124, 252, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(127, 255, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 100, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 128, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(34, 139, 34) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 255, 0) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(50, 205, 50) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(143, 188, 143) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(152, 251, 152) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(144, 238, 144) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(240, 255, 240) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(46, 139, 87) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(60, 179, 113) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 255, 127) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(245, 255, 250) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 250, 154) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(102, 205, 170) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(127, 255, 212) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(64, 224, 208) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(32, 178, 170) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(72, 209, 204) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 128, 128) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 153, 153) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 139, 139) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(47, 79, 79) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 206, 209) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 255, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(175, 238, 238) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(247, 251, 254) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(221, 238, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(224, 255, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(240, 255, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(95, 158, 160) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(176, 224, 230) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(173, 216, 230) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 191, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(135, 206, 235) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(135, 206, 250) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(70, 130, 180) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(240, 248, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(30, 144, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(112, 128, 144) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(119, 136, 153) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(176, 196, 222) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(100, 149, 237) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(65, 105, 225) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 0, 128) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 0, 139) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 153, 204) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(34, 102, 170) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(51, 102, 153) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(25, 25, 112) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 0, 205) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(0, 0, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(248, 248, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(230, 230, 250) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(72, 61, 139) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(106, 90, 205) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(123, 104, 238) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(147, 112, 219) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(138, 43, 226) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(75, 0, 130) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(153, 50, 204) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(148, 0, 211) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(186, 85, 211) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(128, 0, 128) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(139, 0, 139) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 0, 255) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(238, 130, 238) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(221, 160, 221) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(216, 191, 216) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(218, 112, 214) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(199, 21, 133) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(225, 0, 85) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 20, 147) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 105, 180) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(219, 112, 147) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 240, 245) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(220, 20, 60) none repeat scroll 0% 0%;      "></div><div class="colorlist" style="background: rgb(255, 192, 203) none repeat scroll 0% 0%;      "></div>
                                <div class="colorlist" style="background: rgb(255, 182, 193) none repeat scroll 0% 0%;"></div>
                               
                         </div>
                         <div id="pagecolorset">
                             <select id="pagecolorsel">
                             <option value="pagebgcolor">网页背景</option>
                             <option value="pagecontainbg">容器背景</option>
                             <option value="pagetopbg">顶部背景</option>
                             <option value="pagecontentbg">中间背景</option>
                             <option value="pagebottombg">底部背景</option>
                             </select>
                             <input id="pageshowcolor" maxlength="7" value="transparent" type="text">
                             <span id="pagetransparent">透明</span></div>
				    </div>
			         <div class="widget_title">请选择背景颜色</div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
				 <div id="widgetpage"  >
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" >
					   <div  id="widgetlistpage_img" >
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu1.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu2.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu3.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu4.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu5.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu6.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu7.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu8.gif) repeat scroll 0% 0%;"></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu9.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu10.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu11.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu12.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu13.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu14.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu15.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu16.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu17.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu18.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu19.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu20.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu21.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu22.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu23.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu24.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu25.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu26.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu27.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu28.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu29.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu30.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu31.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu32.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu33.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu34.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu35.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu36.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu37.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu38.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu39.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu40.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu41.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu42.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu43.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu44.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu45.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu46.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu47.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu48.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu49.gif) repeat scroll 0% 0%;      "></div>
						   <div class="pagesourcediv" style="background: transparent url(/eop/images/tools/eop_tu50.gif) repeat scroll 0% 0%;      "></div>
				    </div>
			         <div class="widget_title">选择背景图片
					         <span id="bgimg_big">[大]</span>
					         <span id="bgimg_normal">[中]</span>
					         <span id="bgimg_small">[小]</span>
					         <span id="bgimg_upload">[上传图片]</span> </div> 
					 </div>
					     </div>
				     </div>
				      
				 </div>
				 
				 <div id="widgetpage2"  >
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m" >
					<div  id="widgetlistpage_set" >
						   <div class="pageconsetitem">背景定位：<select id="pagebgposition"><option value="left top">左上</option><option value="center top">中上</option><option value="right top">右上</option><option value="left bottom">左下</option><option value="center bottom">中下</option><option value="right bottom">右下</option></select></div>
						   <div class="pageconsetitem">背景重复：<select id="pagebgrepeat"><option value="repeat">重复</option><option value="no-repeat">不重复</option><option value="repeat-x">水平重复</option><option value="repeat-y">垂直重复</option></select></div>
						   <div class="pageconsetitem">背景滚动：<select id="pagebgatt"><option value="scroll">滚动</option><option value="fixed">不滚动</option></select></div>
						   <div class="pageconsetitem">容器宽度：<select id="pagecontainwidth"><option value="900">默认(900PX)</option><option value="100%">全屏(100%)</option><option value="950">门户(950PX)</option><option value="990">宽幅(990PX)</option><option value="850">中幅(850PX)</option><option value="780">窄幅(780PX)</option><option value="720">小窝(720PX)</option><option value="600">名片(600PX)</option></select></div>
						   <div class="pageconsetitem">容器定位：<select id="containcentersel"><option value="auto">居中</option><option value="0">居左</option></select></div>
						   <div class="pageconsetitem">上下边距：<input size="3" id="containmargin" class="input" maxlength="2" value="0" type="text"> PX</div>
						   <div class="pageconsetitem">容器边距：<input size="3" id="containpadding" class="input" maxlength="2" value="10" type="text"> PX</div>
						   <div class="pageconsetitem">容器间距：<input size="3" id="contentmargin" class="input" maxlength="2" value="10" type="text"> PX</div>
						   
				    </div>
			         <div class="widget_title">页面布局风格设置</div>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
				</div>
				
				<!-- 风格 -->
				<div id="customFrames3" style="display:none;" >
				
				 <div id="list_style" >
				     <div class="widgetlist_l">
					     <div class="widgetlist_r">
					        <div class="widgetlist_m">
				<div class="list_style" >
				<button id="style_151" onclick=" "> <span>蓝调优雅</span></button>
				<button id="style_88" onclick=" "> <span>透明简约</span></button>
				<button id="style_12" onclick=" "> <span>粉黑蕾丝</span></button>
				<button id="style_9" onclick=" "> <span>蓝绿粉彩</span></button>
				<button id="style_15" onclick=" "> <span>金色麦芒</span></button>
				<button id="style_5" onclick=" "> <span>深紫浆果</span></button>
				<button id="style_2" onclick=" "><span>橙色秋叶</span></button>
				<button id="style_11" onclick=" "><span>彩炫欢乐</span></button>
				<button id="style_85" onclick=" "><span>黑白水墨</span></button>
				<button class="now" id="style_6" onclick=" "> <span>墨绿山峦</span></button>
				<button id="style_82" onclick=" "> <span>深邃炫蓝</span></button>
				<button id="style_3" onclick=" "> <span>黑色极夜</span></button>
				<button id="style_17" onclick=" "> <span>深蓝彩虹</span></button>
				<button id="style_7" onclick=" "><span>银灰摩登</span></button>
				<button id="style_23" onclick=" "> <span>清新校友</span></button>
				<button id="style_18" onclick=" "> <span>粉红童年</span></button>
				<button id="style_10" onclick=" "> <span>动感洋红</span></button>
				<button id="style_14" onclick=" "> <span>湛蓝天空</span></button>
				<button id="style_19" onclick=" "> <span>翠绿晨曦</span></button>
				<button id="style_83" onclick=" "> <span>炫紫罗马</span></button>
				<button id="style_1" onclick=" "> <span>湖蓝薄雾</span></button>
				<button id="style_21" onclick=" "> <span>紫蓝初荷</span></button>
				<button id="style_81" onclick=" "> <span>黑暗骑士</span></button>
				<button id="style_8" onclick=" "> <span>粉色糖霜</span></button>
				<button id="style_16" onclick=" "> <span>灰白典雅</span></button> 
				 
				</div>
					         <ul id="filter_mode_custom" >
					            <li class="selected"><a class="" href="javascript:;" ><span>全部</span></a></li>
					            <li ><a href="javascript:;" ><span>庄重</span></a></li>
					            <li ><a href="javascript:;" ><span>清新</span></a></li>
					            <li ><a href="javascript:;" ><span>成熟</span></a></li>
					            <li ><a href="javascript:;" ><span>可爱</span></a></li>
					            <li ><a href="javascript:;" ><span>其他</span></a></li>
					         </ul>
					         </div>
					     </div>
				     </div>
				      
				 </div>
				 
 
				</div>
	     </div>
	</div>
</div> 
</div>

</div>
<div id="show_panl" class="gb_custom_m nav_custom_show" style="display:none;" eop_type="widget_tool"> 
<input type="radio" name="layout_type" id="layout_type_auto" checked="true"/>自动式 <input type="radio"  name="layout_type"  id="layout_type_free" />自由式
					<button id="bt_save_custom" class="bt_save_custom" onclick="javascript:savePage(true);">保存</button>
					<button class="bt_cancel_custom" onclick="javascript:top.location='index.html'">退出</button>
					<button class="bt_cancel_custom" onclick="javascript:hid(2);">显示</button>
</div>