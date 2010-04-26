<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<body>
	<table align="center">
	<tr>
	<td>
	<s:textarea theme="xhtml" cols="6" rows="6" cssStyle="width: 400px; height: 200px;overflow-y:visible;overflow-x:visible;" name="search.pbulletincontent" value="%{bulletin.BULLETINCONTENT}" label="领主招纳内容" readonly="true"></s:textarea>
	</td>
	</tr>
	<tr>
		 <!-- 地图显示 -->
		 <div id="map_canvas" style="width: 500px; height: 300px"></div>
	</tr>
	<tr>
		<td>
			<!-- googlemap的引入经纬度信息 -->
		  	<s:hidden name="search.plat" id="search.plat" value="%{bulletin.LAT}"></s:hidden><!-- 纬度 -->
		  	<s:hidden name="search.plng" id="search.plng" value="%{bulletin.LNG}"></s:hidden><!-- 经度 -->
		</td>
	</tr>
	</table>
<!-- 共用地图基本处理 -->
<script type="text/javascript" src="<%=path%>/js/MapBase.js"></script>    
<script type="text/javascript"> 
		    //启动时调用google地图引擎     
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
			        //调用googlemap相应方法
			    	var html = "<table>" +
		                         "<tr><td>发布者:</td> <td> <s:property escape='false'   value='%{bulletin.USERNAME}'/> </td> </tr>" +
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