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
		<title>周边向导动态</title>
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"> 
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
					//调用googlemap相应方法
					//1定点
					var map = new GMap2(document.getElementById("map_canvas"));
					//是否有默认点(定位时要除以1000000)
					var srclat=document.getElementById("search.plat").value;
					var srlng=document.getElementById("search.plng").value;
					//默认定位点
					if(srclat!=0){
					    point= new GLatLng(srclat, srlng);
						map.setCenter(point, 12);
						//增加点的层
						var marker = new GMarker(point,{draggable:true});
						map.addOverlay(marker);
						
				         //单击标识事件,查看经纬度
				         GEvent.addListener(map, "click", function(overlay, latlng) {
					         GEvent.addListener(marker, "click", function() {
					              var html = "<table>" +
					                         "<tr><td>商家名称:</td> <td> <s:property value='mapcard.MERCHANTNAME'/> </td> </tr>" +
					                         "</table>";
					              marker.openInfoWindow(html);
					         });
				         });
				         
					}else{
						 map.setCenter(new GLatLng(24.904450211196607, 118.597412109375), 12); 
					}
					
					//增加按钮控件
			        map.addControl(new GOverviewMapControl());
					map.addControl(new GMapTypeControl());
					map.addControl(new GLargeMapControl());
					
					//点击事件
			        GEvent.addListener(map, "click", function(overlay, latlng) {
			          if (latlng) {
			            //移除原标识点(仅标识一个点)
			            map.clearOverlays();
						//生成标识点
			            var marker = new GMarker(latlng, {draggable:true});
			            //存储标识点数据
			            var latlng=marker.getLatLng();
						var lat = latlng.lat();
			      		var lng = latlng.lng();
			      		//$("#search.plat").val("123");因为.操作是jquery有特殊处理
			      		//$("#search.plng").val("123");
			      		document.getElementById("search.plat").value=lat;
			      		document.getElementById("search.plng").value=lng;
			      		
			      		 
			      
			      	
			      		 
			            //单击标识事件,查看经纬度
			            GEvent.addListener(marker, "click", function() {
			              var html = "<table>" +
			                         "<tr><td>经度:</td> <td> "+lng+" </td> </tr>" +
			                         "<tr><td>纬度:</td> <td> "+lat+" </td> </tr>" +
			                         "</table>";
			              marker.openInfoWindow(html);
			            });
			            
			            map.addOverlay(marker);
			          }
			        });
			        
			        //移动事件
			        GEvent.addListener(marker, "dragend", function(overlay, latlng) {
			          		document.getElementById("search.plat").value=marker.getPoint().y;
			      			document.getElementById("search.plng").value=marker.getPoint().x;		
			          
			        });
					var userLogScript=<s:property value="json500m" escape="false"/>;
					
					var	doNowI =userLogScript.length;
					if(doNowI>=1){
						map.clearOverlays();
						for(i = 0; i < doNowI; i++){
							var lat=userLogScript[i]["LAT"];
							var lng=userLogScript[i]["LNG"];
							var name=userLogScript[i]['USERNAME'];

							map.addOverlay(new GMarker(new GLatLng(lat, lng)));
							
						}
					}
					
					
					$(window).unload(function (){
						$('.').unbind();
						GUnload();
					});
					
				}else{
					jalert('你使用的浏览器不支持 Google Map!');　　
				} 
			})
			
			
		</script>

	</head>
	<body>
		<table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" align="left">
					周边向导动态
				</td>
			</tr>
		</table>
		<s:form action="guideDynamic">
		<s:hidden name="action" value="query"></s:hidden>
		<table width="80%" id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="80%">
			<tr>
				 <td>
		 			<div id="map_canvas" style="width: 500px; height: 300px"></div>
		 			<s:hidden name="search.plat" id="search.plat" ></s:hidden><!-- 纬度 -->
		  			<s:hidden name="search.plng" id="search.plng" ></s:hidden><!-- 经度 -->
		 		</td>
		 		<td width="50%" valign="top">
		 			<s:iterator id="guide" value="%{guideList}">
		 					<a href="#" onclick="findLocation('1600 amphitheatre mountain view ca');return false;"><s:property
										value="#guide.USERNAME" /></a><br><br>
		 			</s:iterator>
		 		</td>
			</tr>
			<tr>
				<td width="10%" height="19" align="center">
					是否在线向导<s:checkbox name="search.onLine" id="onLine" fieldValue="true" />
				</td>
				
				<td>
					<s:submit value="重新查询"></s:submit>
				</td>
			</tr>
		</table>
		</s:form>
	</body>
</html>