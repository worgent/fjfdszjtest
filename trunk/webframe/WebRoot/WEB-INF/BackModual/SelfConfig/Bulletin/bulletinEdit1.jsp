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
		<s:form action="bulletin" method="post" theme="xhtml">
		  
		  <s:head  value="优惠劵信息"/>
		  <table align="center">
				<tr>
					<td colspan="4">
						<s:actionerror theme="webframe0" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<s:actionmessage theme="webframe0" />
					</td>
				</tr>
				  
				  	<s:textarea cols="6" rows="6" cssStyle="width: 400px; height: 200px;overflow-y:visible;overflow-x:visible;" name="search.pbulletincontent" value="%{bulletin.BULLETINCONTENT}" label="优惠劵内容"></s:textarea>
				  
				  <tr>
				       <!-- 地图显示 -->
				  	   <div id="map_canvas" style="width: 500px; height: 300px"></div>
				  </tr>
				  <tr>
				    <!-- 主键内部隐藏 -->
				  	<s:hidden name="action" value="%{action}"></s:hidden>
				  	<s:hidden name="search.pid" value="%{bulletin.ID}"></s:hidden>
				  	<s:hidden name="search.ptype" value="1"></s:hidden>
				  	<!-- googlemap的引入经纬度信息 -->
				  	<s:hidden name="search.plat" id="search.plat" value="%{bulletin.LAT}"></s:hidden><!-- 纬度 -->
				  	<s:hidden name="search.plng" id="search.plng" value="%{bulletin.LNG}"></s:hidden><!-- 经度 -->
				  </tr>
				  <tr>
				  <s:submit value="提交" align="right"></s:submit>
				  <s:reset value="重置" align="left"></s:reset>
				  </tr>
		</table>
		</s:form>
<!-- 共用地图基本处理 -->
<script type="text/javascript" src="<%=path%>/js/MapBase.js"></script>   
<script type="text/javascript"> 
		    //启动时调用google地图引擎     
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
			        //调用googlemap相应方法
					editmap();
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