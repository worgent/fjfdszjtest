<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>查询优惠</title>
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
		<link href="css/map.css" rel="stylesheet" type="text/css" /><!-- 地图的样式 -->
		<script type="text/javascript" src="js/hireGuide.js"></script><!-- initial -->
		<script type="text/javascript" src="js/squarechoose.js"></script><!-- 矩形框 -->
 		<script type="text/javascript"> 
		    $(document).ready(function(){
		        //且必须设置
				initialize(<s:property value="json500m" escape="false"/>);
			});	
		</script>
	</head>
	<body >
		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" align="left">
					查询优惠
				</td>
			</tr>
		</table>
		<s:form action="hireGuide">
		<s:hidden name="action"></s:hidden>
		<table width="80%" id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="80%">
			<tr>
				<td width="10%" height="19" align="right">商家</td>
				<td width="10%" align="left">

				</td>
				<td width="14%" align="right">地图周边500m</td>
				<td width="9%" align="left">
				</td>
				<td width="57%" rowspan="4" align=center>
					<div id="map_canvas" style="width: 500px; height: 300px"></div>
					<input type=hidden  name='ml' id='ml' value="16"/><!-- 缩放级别 -->
					<input type=hidden  name='mt' id='mt' value="地图"/><!-- 地图类型 -->
					<input type=hidden  name='sw' id='sw' /><!-- 西南角经纬度 -->
					<input type=hidden  name='ne' id='ne' /><!-- 东北角经纬度 -->
					<input type=hidden  name='selectType' id='selectType' value="0"/><!-- 默认查询周边 -->
					<s:hidden name="search.plat" id="search.plat" value="24.904450211196607"></s:hidden><!-- 纬度 -->
		  			<s:hidden name="search.plng" id="search.plng" value="118.597412109375"></s:hidden><!-- 经度 -->
				</td>
			</tr>
			<tr>
				<td width="10%" height="19" align="right">
					用户位置修改
				</td>
				<td width="10%" align="left">
					<s:checkbox name="search.onLine" id="onLine" fieldValue="true" />
				</td>
				<td width="14%" align="right">
					必须有优惠券
				</td>
				<td width="9%" align="left">
					<s:checkbox name="search.haveVoucher" id="havaVoucher" fieldValue="true"/>
				</td>
				
			</tr>
			
			<tr>
				
				<td align="right" >
						结果排序
				</td>
				<td width="9%" align="left">
					
				</td>
				
				<td colspan="2"  align="right">
					<s:submit value="查询" />
				</td>
				
			</tr>
			
		</table>
		</s:form>
       <table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" align="left">
					向导信息（索要优惠）
				</td>
			</tr>
		</table>
		<table width="80%" id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="80%">
			<thead>
			<tr>
				<th>名称</th><th>收入</th><th>经验值</th><th>贡献值</th><th>名望值</th><th>状态</th><th>是否有优惠券</th>
			</tr>
			</thead>
			
			<s:iterator id="hireGuide" value="%{pageList.objectList}">
			<tr>
				<td><s:property value="#hireGuide.USERNAME" /></td>
				<td><s:property value="#hireGuide.FINANCIAL" /></td>
				<td><s:property value="#hireGuide.EXPERIENCE" /></td>
				<td><s:property value="#hireGuide.CONTRIBUTE" /></td>
				<td><s:property value="#hireGuide.FAME" /></td>
				<td><s:if test="#hireGuide.IFONLINE==1">在线</s:if><s:if test="#hireGuide.IFONLINE==0">离线</s:if></td>
				<td><s:if test="#hireGuide.HAVEVOUCHER==1">有</s:if><s:if test="#hireGuide.HAVEVOUCHER==0">没有</s:if></td>
			</tr>
			</s:iterator>
			
			<s:if test="%{pageList.pages!=null}" >
			<tr class="bgColor3">
				<td colspan="7">
				分页:                                                     
				<qzgf:pages value="%{pageList.pages}" javaScript="loadDefaultList"/>
				</td>
			</tr>
			</s:if>
		</table>
	</body>
</html>