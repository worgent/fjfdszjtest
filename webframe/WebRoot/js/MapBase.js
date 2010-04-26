 /*
 时间:      2009-11-25
 功能说明:google地图基本操作:点的标注与显示
 用法:    1. 首先页面层需要有  <div id="map_canvas" style="width: 500px; height: 300px"></div>(存放地图位置)
 		  	2. googlemap的引入经纬度信息:例如
		  	<s:hidden name="search.plat" id="search.plat" value="%{mapcard.LAT}"></s:hidden><!-- 纬度 -->
		  	<s:hidden name="search.plng" id="search.plng" value="%{mapcard.LNG}"></s:hidden><!-- 经度 -->
 */
 
//适用与后台,标注地图点
function editmap() {     
		//定点
		var map = new GMap2(document.getElementById("map_canvas"));
		//是否有默认点
		var srclat=document.getElementById("search.plat").value;
		var srlng=document.getElementById("search.plng").value;
		//默认定位点
		if(srclat!=0){
			map.setCenter(new GLatLng(srclat, srlng), 15);
			var marker = new GMarker(new GLatLng(srclat, srlng), {draggable:false});
			map.addOverlay(marker);
		}else{
			map.setCenter(new GLatLng(24.894689, 118.602218), 12); //默认取泉州中心
		}
        //增加按钮控件
        map.addControl(new GOverviewMapControl());//GOverviewMapControl - 位于屏幕一角的可折叠概览地图。
		map.addControl(new GMapTypeControl());//让用户切换地图类型（例如“地图”和“卫星”）的按钮
		map.addControl(new GLargeMapControl());//一个在 Google 地图上使用的大平移/缩放控件。默认情况下显示在地图的左上角。
        
		//事件
        GEvent.addListener(map, "click", function(overlay, latlng) {
          if (latlng) {
            //移除原标识点(仅标识一个点)
            map.clearOverlays();
			//生成标识点
            var marker = new GMarker(latlng, {draggable:false});
            //存储标识点数据
            var latlng=marker.getLatLng();
			var lat = latlng.lat();
      		var lng = latlng.lng();
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
	}  
    //查看地图,适用于查看点
	function viewmap(html) {     
		//1定点
		var map = new GMap2(document.getElementById("map_canvas"));
		//是否有默认点(定位时要除以1000000)
		var srclat=document.getElementById("search.plat").value;
		var srlng=document.getElementById("search.plng").value;
		//默认定位点
		if(srclat!=0){
		    point= new GLatLng(srclat, srlng);
			map.setCenter(point, 15);
			//增加点的层
			var marker = new GMarker(point,{draggable:false});
			map.addOverlay(marker);
	         //单击标识事件,查看经纬度
	         GEvent.addListener(map, "click", function(overlay, latlng) {
		         GEvent.addListener(marker, "click", function() {
		              marker.openInfoWindow(html);
		         });
	         });
	         
		}else{
			 map.setCenter(new GLatLng(24.894689, 118.602218), 12); 
		}
	}   