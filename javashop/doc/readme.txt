index ${widget_2}:左菜单         goods_cat             widget.goodscat

      ${widget_5}: jquery滑动.    jqueryAdAdvance      widget.adv.jqueryAdAdvance

      ${widget_3}:新品热卖     goods_list {tagid:'1'}  widget.goods.list

      ${widget_6}:文胸系列     ad  <acid>4</acid>
      ${widget_13}:价格表      goods_list  term{tagid:'4'}
      ${widget_12}:tab         goods_list {type:'morediv',goodsImgPosition:'top',changeEffect:0,columnnum:5,
				goodsNum:10}

      ${widget_7}:精品推荐      goods_list   {tagid:'3'}
{type:'default',goodsImgPosition:'top',
				thumbnail_pic_width:'202px',thumbnail_pic_height:'268px',
				showGoodsName:'on', columnnum:5, goodsNum:10}







header ${widget_menu}:头菜单   shop_menu   widget.menu   site_menu


footer: ${widget_footer1}:导航   30


       FootMenu: ${widget_footer2} 31


       Copyright:${widget_footer3} 32


                 ${widget_footer}    partZone 33      widget.partZone   


菜单 menu
select * from es_menu where menutype=2 and pid=0

select * from es_menu where menutype=2 and pid=90

select * from es_menu where menutype=2 and pid in(select id from es_menu where pid=90)

添加类型 emstype   emstypeAction

添加列表 emscat   emscatAction

添加列表 emsgoods  emsgoodsAction
==============================================================
备注:
菜单修改表



类型列表:es_goods_type

类别:es_goods_cat


后台菜单:
select * from es_menu where menutype=2 and pid=0
后台导航:
select * from es_menu where menutype=1 and pid=0

前台站点菜单
select * from es_ems_site_menu

左测菜单:
ems_goods_cat,ems_goods_type

<a href="search-cat{${cat.cat_id }}.html">

<page id="/default.html">
<widget id="/search-(.*).html">
			<overflow>visible</overflow>
			<border />
			<type>goods_search</type>
</widget>


---后台图片存放.


--修改页头

--修改页尾
ems_article,ems_article_cat


/********************************************2010.12.13********************************/
修改升级
安装部署
1.配置属性设置.
framework.util.stringutil==>filePath = filePath.replaceAll("%20", " ");
2.src:eop.properties相关配置信息(product路径问题)
3.读取xml文件异常
ProfileLoaderImpl.java     ========profile.xml
AdminThemeinfoloaderimp.java ======themeini.xml
4.首页无法打开
com.enation.eop.core.facade.widget.widgetxmlutil.paseparamdo==>widgets.xml
com.enation.eop.impl.facade.widgetXmlWidgetParamParser==>
5.首页显示原理
<widget id="/search-(.*).html">
			<overflow>visible</overflow>
			<border />
			<type>goods_search</type>
</widget>
GoodsSearchWidget
GoodssearchWidget.html
com.enation.javashop.widget.goods.search


6.127.0.0.1
com.enation.eop.impl.resource.AccessRecorder流量统计

7.图片存在.\mm\statics\attachment\goods

8.管理显示原理
清数据==通过后台.

select* from es_site_menu

use javashop

select* from es_ems_site_menu

use javashopex

13,5,2
select* from es_site_menu where parentid=0

select * from es_site_menu where  parentid=21

select * from es_site_menu where parentid in(select menuid from es_site_menu where parentid=0) order by parentid,sort,menuid


update es_goods_type a,es_goods_cat b set a.name=b.name where a.type_id=b.cat_id

select * from es_goods_cat where parent_id=0

select * from es_goods_cat where parent_id in (select cat_id from  es_goods_cat where parent_id=0) order by parent_id,cat_id
1,0,首页,index.html,0
2,0,生日礼仪,search-cat{3}.html,3
3,2,鲜花花束,search-cat{16}.html,0
5,0,节日礼仪,search-cat{2}.html,2
6,5,春节送礼,search-cat{13}.html,0
7,5,端午送礼,search-cat{14}.html,1
10,4,领带,\N,0
13,0,商务礼仪,search-cat{1}.html,1
15,13,商务礼仪1,search-cat{6}.html,0
16,13,商务礼仪2,search-cat{7}.html,0
17,13,商务礼仪3,search-cat{8}.html,0
18,5,中秋送礼,search-cat{15}.html,2
19,2,精致蛋糕,search-cat{17}.html,0
20,2,创意礼品,search-cat{18}.html,0
25,0,品牌专区,brand.html,5
26,28,捆绑促销,package.html,0
27,0,客户留言,guestbook.html,7
28,0,促销专区,#,6
29,28,兑换赠品,giftlist.html,1
30,0,帮助中心,help-1.html,9
--商品数据清除.

select * from es_menu where menutype=2 and pid=14

select * from es_brand

select * from es_goods_type

delete from es_goods 

SELECT last_insert_id() as id

/*定时流量统计*/
com.enation.eop.impl.resource.AccessExporter
select * from eop_site 

select * from es_access

delete from es_car_article where id=76
INSERT INTO es_car_article  VALUES ('76','0','2010-09-21 10:33:15.0',null,null,null,null,'14',null,'服务热线','','<p>	<img alt="" src="fs:/attachment/ckeditor/201009211045114188.jpg" style="width: 571px; height: 120px;" /></p>')

delete from es_site_menu

INSERT INTO `es_site_menu` VALUES ('1', '0', '首页', 'index.html', '0');
INSERT INTO `es_site_menu` VALUES ('2', '0', '生日礼仪', 'search-cat{3}.html', '3');
INSERT INTO `es_site_menu` VALUES ('3', '2', '鲜花花束', 'search-cat{16}.html', '0');
INSERT INTO `es_site_menu` VALUES ('5', '0', '节日礼仪', 'search-cat{2}.html', '2');
INSERT INTO `es_site_menu` VALUES ('6', '5', '春节送礼', 'search-cat{13}.html', '0');
INSERT INTO `es_site_menu` VALUES ('7', '5', '端午送礼', 'search-cat{14}.html', '1');
INSERT INTO `es_site_menu` VALUES ('10', '4', '领带', null, '0');
INSERT INTO `es_site_menu` VALUES ('13', '0', '商务礼仪', 'search-cat{1}.html', '1');
INSERT INTO `es_site_menu` VALUES ('15', '13', '商务礼仪1', 'search-cat{6}.html', '0');
INSERT INTO `es_site_menu` VALUES ('16', '13', '商务礼仪2', 'search-cat{7}.html', '0');
INSERT INTO `es_site_menu` VALUES ('17', '13', '商务礼仪3', 'search-cat{8}.html', '0');
INSERT INTO `es_site_menu` VALUES ('18', '5', '中秋送礼', 'search-cat{15}.html', '2');
INSERT INTO `es_site_menu` VALUES ('19', '2', '精致蛋糕', 'search-cat{17}.html', '0');
INSERT INTO `es_site_menu` VALUES ('20', '2', '创意礼品', 'search-cat{18}.html', '0');
INSERT INTO `es_site_menu` VALUES ('25', '0', '品牌专区', 'brand.html', '5');
INSERT INTO `es_site_menu` VALUES ('26', '28', '捆绑促销', 'package.html', '0');
INSERT INTO `es_site_menu` VALUES ('27', '0', '客户留言', 'guestbook.html', '7');
INSERT INTO `es_site_menu` VALUES ('28', '0', '促销专区', '#', '6');
INSERT INTO `es_site_menu` VALUES ('29', '28', '兑换赠品', 'giftlist.html', '1');
INSERT INTO `es_site_menu` VALUES ('30', '0', '帮助中心', 'help-1.html', '9');

1.样式
ems.css <--index.css
2.脚本


select * from es_article

9.当前位置导航
com.enation.javashop.widget.nav.SiteNavWidget


select rel_id from tag_rel where tag_id in {tagid:'1'}

select * from es_tag_rel

select * from es_goods

select * from es_goods_cat

select * from eop_site

mm/core/admin/solution!toImport.do
com.enation.app.saler.action.backend.SolutionAction

com.enation.app.saler.service.impl.ProductServiceImpl
写封冻