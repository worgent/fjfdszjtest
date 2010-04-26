<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@page import="com.qzgf.utils.comm.WebFrameUtil;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  //request.getContextPath()应该是得到项目的名字,如果项目为根目录,则得到一个"",即空的字条串, 
  //如果项目为abc, <%=request.getContextPath()% >/ 将得到abc/,服务器端的路径则会自动加上 
  //request.getScheme(); 返回的协议名称,默认是http 
  //request.getServerName()返回的是你浏览器中显示的主机名，你自己试一下就知道了
  //getServerPort() 获取服务器端口号
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<body>
	<table align="center">
	<tr>
	<td>商家名称:<s:property value="mapcard.MERCHANTNAME"/></td>
	</tr>
	<tr>
	<td>商家个性图标:<img  src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='mapcard.MERCHANTICON' />_Small" alt="<s:property value='mapcard.MERCHANTICON' />" /></td>
	</tr>
	<tr>
	<td>商家类型:<s:property value="mapcard.MERCHANTTYPE"/></td>
	</tr>
	<tr>
	<td>认领商家:<s:property value="mapcard.MERCHANT_NAME"/></td>
	</tr>
	<tr>
	<td>联系方式:<s:property value="mapcard.MERCHANTTELPHONE"/></td>
	</tr>
	<tr>
	<td>地址:<s:property value="mapcard.MERCHANTADDRESS"/></td>
	</tr>
	<tr>
	<td>介绍:<s:property value="mapcard.MERCHANTINTRODUCE"/></td>
	</tr>
	<tr>
		 <!-- 地图显示 -->
		 <div id="map_canvas" style="width: 500px; height: 300px"></div>
	</tr>
	<tr>
		<td>
			<!-- googlemap的引入经纬度信息 -->
		  	<s:hidden name="search.plat" id="search.plat" value="%{mapcard.LAT}"></s:hidden><!-- 纬度 -->
		  	<s:hidden name="search.plng" id="search.plng" value="%{mapcard.LNG}"></s:hidden><!-- 经度 -->
		</td>
	</tr>
	
	</table>
	<table class='simple' style='width: 98%' align='center' id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='10'>商品信息列表</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>商品名称</td>
				<td align='center'>商品图标</td>
				<td align='center'>商品介绍</td>	
			</tr>
			<s:iterator value="productList" status="product">               		
	        	<tr>   
	        		<td  valign="top" align='center'>
						<s:property value="SPECIALNAME"/>
					</td>
					<td  valign="top" align='center'>	
						<img  src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='SPECIALICON' />_Small" alt="<s:property value='SPECIALICON' />" />			
					</td>
					<td  valign="top" align='center'>
						<s:property value="SPECIALINTRO"/>					 
					</td>		 
	       		</tr>   
       		</s:iterator>   
		</tbody>
	</table>
<!-- 共用地图基本处理 -->
<script type="text/javascript" src="<%=path%>/js/MapBase.js"></script>    
<script type="text/javascript"> 
		    //启动时调用google地图引擎     
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
			        //调用googlemap相应方法
			    	var html = "<table>" +
		                         "<tr><td>商家名称:</td> <td> <s:property value='mapcard.MERCHANTNAME'/> </td> </tr>" +
		                         "</table>";
					viewmap(html);
					
					$(window).unload(function (){
				　　　　　$('.').unbind();
				　　　　　GUnload();
				　　});
				}else{
			　　　　 jalert('你使用的浏览器不支持 Google Map!');　　
			　　 }
			})
	</script> 
	</body>
</html>