<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">

		</script>
	</head>
	<body>
	<link  href="<%=path%>/css/poi_search.css"  rel="stylesheet" type="text/css" media="screen" / >				



<div class="poi_srhL">
<ul class="poi_listL2">
<!-- 左侧商家类别信息 -->
<s:property value="menustr" escape="false"/>
<div class="clear"></div>
</div>

<script language='javascript'> 
function showType(el){$("#Nav"+el).show();}
function hidType(el){$("#Nav"+el).hide();}
</script>


<!--具体信息 -->
                   <div class="poi_srhRB">
                      <!--右栏头部菜单-->
                        <div class="poiRL">
                             <ul class="poiRLT">
                               <li class='sel'><a href="<%=path%>/selfconfig/mapcard.do?search.orderby=1" title='默认排序'>默认排序</a></li>
                               <li ><a href="<%=path%>/selfconfig/mapcard.do?search.orderby=1" title='搜索热度的排序'>实力排序</a></li>
                               <li ><a href="<%=path%>/selfconfig/mapcard.do?search.orderby=2" title='点评量排序'>口碑排序</a></li>
                               <li ><a href="<%=path%>/selfconfig/mapcard.do?search.orderby=1" title='点评量排序'>人气排序</a></li>
                             </ul>
 						<div class="poiRL2">
 <s:iterator id="mapcardList" value="%{pageList.objectList}" status='st'>
                             <div class="poiRL21" id='srh_u3_umain1' onmouseenter="showTypedetail(<s:property value="#mapcardList.ID" />)" onmouseleave="hidTypedetail(<s:property value="#mapcardList.ID" />)">
                                  <ul class="ListT" >
                                    <li class="ListT1">1 . 
                                    <a href='<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />'><s:property value="#mapcardList.MERCHANTNAME" /></a><span id='chain1' class='sch_chain'></span><span></span> <span>点评[2]</span><span id='srh_u3_mu1l5'></span></li>
                                    <li class="ListT2">
									标签:<a href='<%=path%>/selfconfig/mapcard.do?action=index&search.pmerchanttype='<s:property value="#mapcardList.MERCHANTTYPE"/>><s:property value="#mapcardList.MERCHANTTYPE"/></a> 
									</li>
                                    <!--li><a href="#">优惠券</a></li-->
                                  </ul>
                                  <ul class="ListC">
                                   <li class="ListC1">
                                     <span>简介：</span>
                                     <span class="intro"><s:property value="#mapcardList.MERCHANTINTRODUCE" /></span>
                                   </li>
                                   <li class="ListC2">
                                     <p>地址<s:property value="#mapcardList.MERCHANTADDRESS"/></p>
                                     <p>电话:<s:property value="#mapcardList.MERCHANTTELPHONE"/></p>
                                   </li>
                                  </ul>
                                  <!-- 
								  <ul id='srh_u3_mu4_1' style='position:relative' class='nogreen'><div id='detail_<s:property value="#mapcardList.ID" />' style='display:none;'><ul><li style='width:50px;'></li><li><a href='/mstmap50007/in_bmap.jsp?g_mapid=21&g_poiId=22101035&pname=上海' title='看大图' href2='lp_c[820, 500]'><img src='<%=path%>/img/mapcard/g_srh_bmap.gif' />&nbsp;&nbsp;&nbsp;看大图</a></li><li><a href="/map/21---%C9%CF%BA%A3%C2%ED%CF%B7%B3%C7@H@%CA%B1%BF%D5%D6%AE%C2%C3--edeifdEkF-dibEdkd--/"><img src='<%=path%>/img/mapcard/g_srh_round.gif' />&nbsp;&nbsp;&nbsp;查询周边</a></li><li><a  href='/mstmap50007/in_gjhc.jsp?to=上海市共和新路2266号&g_mapid=21' title='公交换乘/开车路线'  href2='lp_c[600, 290]'><img src='<%=path%>/img/mapcard/g_srh_bus.gif' />&nbsp;&nbsp;&nbsp;公交换乘/开车路线</a></li></ul></div></ul>
								   -->
                             </div>
</s:iterator>
分页:<qzgf:pages value="%{pageList.pages}" />
					   </div><!--内容明细  -->
</div> <!--右栏排序列表 end-->
</div>

<script language='javascript'> 

function showTypedetail(el){$("#detail_"+el).show();}

function hidTypedetail(el){$("#detail_"+el).hide();}
</script>

	</body>
</html>