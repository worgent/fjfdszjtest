var squareChoose;//矩形选择框实例
var squareChooseControl;//矩形选择框搜索控件
var map;
//初始化
function initialize(json500m) {
	if (GBrowserIsCompatible()) {
    	map = new GMap2(document.getElementById("map_canvas")); 
		map.enableScrollWheelZoom();																							 		
		map.setCenter(new GLatLng(document.getElementById("search.plat").value,document.getElementById("search.plng").value), 1*($("#ml").val())); //保存地图缩放级别
		GEvent.addListener(map, "maptypechanged", function(oldLevel, newLevel) {//保存地图类型
        	$("#mt").val(map.getCurrentMapType().getName());
      	});
      	GEvent.addListener(map, "zoomend", function(oldLevel, newLevel) {//保存缩放级别
       		$("#ml").val(newLevel);
      	});
      	
      	//添加一个地图类型控件
	    var mapControl = new GMapTypeControl();//地图类型控件
	    map.addControl(mapControl);
	    map.addControl(new GLargeMapControl());//地图缩放控件
	    map.addControl(new GOverviewMapControl());//小地图浏览控件
	    if($("#mt").val()=='卫星'){//修改地图的类型为上一次查询时的地图类型
      		map.setMapType(map.getMapTypes()[1]);
		}else	if($("#mt").val()=='混合地图'){
      		map.setMapType(map.getMapTypes()[2]);
		}
		
		var	doNowI =json500m.length;
					if(doNowI>=1){
						map.clearOverlays();
						for(i = 0; i < doNowI; i++){
							var lat=json500m[i]["LAT"];
							var lng=json500m[i]["LNG"];
							var name=json500m[i]['USERNAME'];

							//map.addOverlay(new GMarker(new GLatLng(lat, lng)));
							var latlng=new GLatLng(lat, lng);
							map.addOverlay(createMarker(latlng,name));
						}
					}
					
		// 创建信息窗口显示对应给定索引的字母的标记
        function createMarker(point, name) {

          var marker = new GMarker(point);

          GEvent.addListener(marker, "click", function() {
            marker.openInfoWindowHtml("向导<a href='#'>" + name + "</a>");
          });
          return marker;
        }
		
		//点击事件
		GEvent.addListener(map, "click", function(overlay, latlng) {
		if (latlng) {
			var selectType=$("#selectType").val();
			if(selectType==0){
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
		}
		});
		
		//初始化矩形框选控件
		squareChooseControl=new SquareChooseControl();
		if(($("#ne").val()!="")&&($("#sw").val()!="")){
			squareChooseControl.openState=true;//置于打开矩形框状态
		}
		
		squareChooseControl.label="框选搜索";
		//指定矩形选择框open处理函数
		squareChooseControl.setOpenFun(squareOpen);
		//指定矩形选择框close处理函数
		squareChooseControl.setCloseFun(squareClose);
		//指定矩形选择框搜索处理函数
		//squareChooseControl.setSearchFun(squareSearch);
		//添加矩形框选控件到地图上
    	map.addControl(squareChooseControl);
		
		squareChoose=new SquareChoose(map,squareChooseBack);
		var neLatLng=null;
		var swLatLng=null;
		//从页面的ne和sw变量取出上次提交的东北和西南坐标进行初始化
		if($("#ne").val()!=""&&$("#sw").val()!=""){
			var neArray=$("#ne").val().split(",");
			neLatLng=new GLatLng(neArray[0],neArray[1]);
			var swArray=$("#sw").val().split(",");
			swLatLng=new GLatLng(swArray[0],swArray[1]);
			squareChoose.initSqueaMarker(0.3,neLatLng,swLatLng);
		}
    }
} 

//打开矩形搜索功能的事件
function squareOpen(){
	map.clearOverlays();
	if(($("#ne").val())!=""&&($("#sw").val()!="")){
		var neArray=$("#ne").val().split(",");
		neLatLng=new GLatLng(neArray[0],neArray[1]);
		var swArray=$("#sw").val().split(",");
		swLatLng=new GLatLng(swArray[0],swArray[1]);
		squareChoose.initSqueaMarker(0.3,neLatLng,swLatLng);
	}else{
		squareChoose.initSqueaMarker(0.3);
	}
	
	var polySharp=squareChoose.getPolyShape();
	squareChooseBack(polySharp);

}

//关闭矩形区域事件
function squareClose(){
	$("#sw").val("");
	$("#ne").val("");
	$("#selectType").val("0");
	squareChoose.clearPoly();
}

//搜索矩形区域的事件
function squareSearch(){
	var polySharp=squareChoose.getPolyShape();
	squareChooseBack(polySharp);
	
}

///矩形选中的回调函数
function  squareChooseBack(polySharp){
	if(polySharp){
		var bounds=polySharp.getBounds();
		$("#ne").val(bounds.getNorthEast().lat()+","+bounds.getNorthEast().lng());
		$("#sw").val(bounds.getSouthWest().lat()+","+bounds.getSouthWest().lng());
		$("#selectType").val("1");
	}else{
		$("#ne").val("");
		$("#sw").val("");
		$("#selectType").val("0");
	}
}

//通过地图控件之外的按钮来控制矩形框选择打开或是关闭
function squareBtnClick(){
	if($("#squarebtn").checked==true){//squarebtn为页面上的一个checkbox
		squareChooseControl.open();
	}else{
		squareChooseControl.close();
	}
}