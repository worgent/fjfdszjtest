 /*实现在地图上的矩形框选择区域功能 
  *实现主要功能如下:
  *1.在地图上绘制一个指定经纬坐标的四顶点和中心点可以拖放的矩形框。移动四顶点可以改变矩形大小，移动中心点可以拖动矩形框
  *2.每次改变矩形框位置或大小时，会自动触发回调函数，并传递当前矩形对象GPolygon
  *3.提供GControl,可以控制矩形选择框的显示、关闭、提交事件
  *4.如果不喜欢使用GContorl，可以使用外部按钮等方式进行控制
  *@author lsr linshengru@126.com
 */

/*
参数说明:pmap地图
			 callbackfun回调函数
*/
function PointDistance(pmap,callbackfun){
	//地图对象
    var map=pmap;
    //矩形临时变量
    var polyShape;
    //线条颜色
    this.polyLineColor = "#3355ff";
    //形状填充颜色
    this.polyFillColor = "#335599";
    var polyPoints = new Array();
    var squareMarkers = new Array();
    var report = document.getElementById("status");
    var dragMarker=null;
    var savedLatLng=null;
    var centerMovedMarker=null;
    //回调函数
    var callback=callbackfun;
    
    /*初始化一个选择框
     *@scale 透明度
     *@nSw 西南坐标
     *@nNe 东北坐标
    */
    this.initSqueaMarker=function (scale,nSw, nNe){
    
     GMarker.prototype.zxLatSibling=null;
     GMarker.prototype.zxLngSibling=null;
     //地图的四个标注点
       var bounds=map.getBounds();
       var sw=bounds.getSouthWest();
       var ne=bounds.getNorthEast();
       var w=ne.lng()-sw.lng();
       var h=ne.lat()-sw.lat();
       if(nSw==null)
          nSw=new GLatLng(sw.lat()+(h-h*scale)/2,sw.lng()+(w-w*scale)/2);
       if(nNe==null)
          nNe=new GLatLng(ne.lat()-(h-h*scale)/2,ne.lng()-(w-w*scale)/2);
     //添加四个标注点     
       addSquearMarker(new GLatLng(nSw.lat(),nSw.lng()));
       addSquearMarker(new GLatLng(nSw.lat(),nNe.lng()));
       addSquearMarker(new GLatLng(nNe.lat(),nNe.lng()));
       addSquearMarker(new GLatLng(nNe.lat(),nSw.lng()));
     //设置中点  
       var centerPoint=new GLatLng(nSw.lat()/2+nNe.lat()/2,nSw.lng()/2+nNe.lng()/2);
       addCenterMovedMarker(centerPoint);
     //地图中心  
       map.setCenter(centerPoint);
     //组装四个点  
       squareMarkers[0].zxLatSibling=squareMarkers[1];
       squareMarkers[0].zxLngSibling=squareMarkers[3];
       squareMarkers[1].zxLatSibling=squareMarkers[0];
       squareMarkers[1].zxLngSibling=squareMarkers[2];
       squareMarkers[2].zxLatSibling=squareMarkers[3];
       squareMarkers[2].zxLngSibling=squareMarkers[1];
       squareMarkers[3].zxLatSibling=squareMarkers[2];
       squareMarkers[3].zxLngSibling=squareMarkers[0];
       
    }
    
    /*添加一个矩形的坐标点*/
    function addSquearMarker(point) {
		     if(point) {
			      //点属性描述
			      var square = new GIcon();
			      square.image = "img/map/square.png";
			      addIcon(square);
			      //标注点
			      var marker =new GMarker(point, {icon:square, draggable:true, bouncy:false, dragCrossMove:true});
			      squareMarkers.push(marker);//放入数组
			      map.addOverlay(marker);
		
			      GEvent.addListener(marker, "drag", function() {
			      dragMarker=marker;
			       drawPoly();
			       savedLatLng=marker.getLatLng();
			      });
			      GEvent.addListener(marker, "dragend", function() {
			      callback(polyShape);
			      });
			
			      GEvent.addListener(marker, "mouseover", function() {
			       savedLatLng=marker.getLatLng();
			      });
			
			      GEvent.addListener(marker, "mouseout", function() {
			       marker.setImage("img/map/square.png");
			      });
			      
			      
		         drawPoly();
		     }
    }
    
    /*添加矩形中心坐标点*/
    function addCenterMovedMarker(point) {
	     if(point) {
	            //点的相关属性描述
		        var micon = new GIcon();
		        micon.image = "img/map/marker_move_icon.png";
		        micon.iconSize = new GSize(32, 32);
		       micon.dragCrossSize = new GSize(0, 0);
		       micon.shadow=null;
		       micon.maxHeight=0;
		       micon.iconAnchor = new GPoint(16, 16);
	
	            //标注中心点
			      var marker =new GMarker(point, {icon:micon, draggable:true, bouncy:false, dragCrossMove:true});
			      map.addOverlay(marker);
	
			 	//托动事件(过程中)
			      GEvent.addListener(marker, "drag", function() {
				      dragMarker=marker;
				      drawPoly();
			          savedLatLng=marker.getLatLng();
			      });
			    //托动释放  
			      GEvent.addListener(marker, "dragend", function() {
				      callback(polyShape);
			      });
			      
			   //开始拖动   
			      GEvent.addListener(marker, "dragstart", function() {
				       savedLatLng=marker.getLatLng();
			      });
	           //描图
	      		drawPoly();
	     }
	     //中点坐标
         centerMovedMarker=marker;
    }
    
    /*添加坐标点图标*/
    function addIcon(icon) { 
	     icon.iconSize = new GSize(11, 11);
	     icon.dragCrossSize = new GSize(0, 0);
	     icon.shadow=null;
	     icon.maxHeight=0;
	     icon.iconAnchor = new GPoint(5, 5);
    }
    
    /*绘图*/
    function drawPoly() {
	    //先移除
    	 if(polyShape) map.removeOverlay(polyShape);
         polyPoints.length = 0;	
      if(dragMarker&&dragMarker!=centerMovedMarker){
		        var dragLatLng=dragMarker.getLatLng();
		        var latmoved=dragLatLng.lat()-savedLatLng.lat();
		        var lngmoved=dragLatLng.lng()-savedLatLng.lng();
		     	for(i=0;i<squareMarkers.length;i++){
					          var m=squareMarkers[i];
					          if(m==dragMarker){
					            var lats=m.zxLatSibling;
					            lats.setLatLng(new GLatLng(m.getLatLng().lat(),lats.getLatLng().lng()));
					           var lngs=m.zxLngSibling;
					            lngs.setLatLng(new GLatLng(lngs.getLatLng().lat(),m.getLatLng().lng()));
					            centerMovedMarker.setLatLng(new GLatLng(centerMovedMarker.getLatLng().lat()+latmoved/2,centerMovedMarker.getLatLng().lng()+lngmoved/2));
					          }
		        }
     }else if(dragMarker&&dragMarker==centerMovedMarker){
			        var dragLatLng=dragMarker.getLatLng();
			        var latmoved=dragLatLng.lat()-savedLatLng.lat();
			        var lngmoved=dragLatLng.lng()-savedLatLng.lng();
			        for(i=0;i<squareMarkers.length;i++){
			          var m=squareMarkers[i];
			          m.setLatLng(new GLatLng(m.getLatLng().lat()+latmoved,m.getLatLng().lng()+lngmoved));
			        }
      }
	      //标注点
	     polyPoints=getSortedMarkers();
	     //画图形
	     polyShape = new GPolygon(polyPoints, this.polyLineColor, 2, .8, this.polyFillColor,.1);
	     map.addOverlay(polyShape);
    }
    
    
    //所有标注点
    function getSortedMarkers(){
		      var swLat=null;
		      var swLng=null;
		      var neLat=null;
		      var neLng=null;
		       for(i = 0; i < squareMarkers.length; i++) {
		        var m=squareMarkers[i];
		        if(swLat==null||swLat>m.getLatLng().lat()){
		          swLat=m.getLatLng().lat();
		        }
		        if(swLng==null||swLng>m.getLatLng().lng()){
		          swLng=m.getLatLng().lng();
		        }
		        if(neLat==null||neLat<m.getLatLng().lat()){
		          neLat=m.getLatLng().lat();
		        }
		        if(neLng==null||neLng<m.getLatLng().lng()){
		          neLng=m.getLatLng().lng();
		        }
		     }
		      var sLatLngs=new Array();
		      sLatLngs.push(new GLatLng(neLat,swLng));
		      sLatLngs.push(new GLatLng(neLat,neLng));
		      sLatLngs.push(new GLatLng(swLat,neLng));
		      sLatLngs.push(new GLatLng(swLat,swLng));
		      sLatLngs.push(new GLatLng(neLat,swLng));
		
		     return sLatLngs;
     }
    
    /*清除图形*/
    this.clearPoly=function () {
		    for(i=0;i<squareMarkers.length;i++){
		    	map.removeOverlay(squareMarkers[i]);
		    }
		    map.removeOverlay(centerMovedMarker);
		    polyPoints.length = 0;
		    squareMarkers.length = 0;
		    polyShape.hide();
    }
    /*取得图形*/
    this.getPolyShape=function(){
	    return polyShape;
    }
}



/*矩形选择控件 */
function PointDistanceControl() {
  //相应set,get方法
  this.setSearchFun= function( fun){searchfun=fun};
  var searchfun=function(){};
  this.setCloseFun= function(fun){closefun=fun};
  var closefun=function(){};
  this.setOpenFun= function( fun){openfun=fun};
  var openfun=function(){};
  
  //设置标题,与创建相应按钮
  this.label="画框搜索";
  this.openState=false;
   var openbtn=document.createElement("div");
   var searchbtn= document.createElement("div");
   var canclebtn=document.createElement("div");
   
		        this.initialize = function(map) {
							    var container = document.createElement("div");
							    container.className= "gmap_button";
							    openbtn.title= "打开矩形框搜索功能";
							   //openbtn.className= "googlemap_button";
							    if(this.openState==true){
							       openbtn.className= "on";
							   }
							
							   container.appendChild(openbtn);
							   openbtn.appendChild(document.createTextNode(this.label));
							   GEvent.addDomListener(openbtn, "click", this.open);
							   searchbtn.title= "搜索矩形框范围内的信息";
							   //searchbtn.className= "googlemap_button";
							    if(this.openState==false){
							       searchbtn.style.display="none";
							   }
							   //container.appendChild(searchbtn);
							   //searchbtn.appendChild(document.createTextNode("搜索"));
							    //GEvent.addDomListener(searchbtn, "click", this.search);
							
							   canclebtn.title= "关闭矩形框搜索功能";
							   //canclebtn.className= "googlemap_button";
							    if(this.openState==false){
							       canclebtn.style.display="none";
							   }
							   container.appendChild(canclebtn);
							   canclebtn.appendChild(document.createTextNode("关闭"));
							    GEvent.addDomListener(canclebtn, "click", this.close);
							   map.getContainer().appendChild(container);
							   return container;
				}
			  
			  this.open=function() {
			    openfun();
			    openbtn.className= "on";
			    canclebtn.style.display="block";
			    searchbtn.style.display="block";
			   }
			   
			 this.search=function() {
			     searchfun();
			    }
			    
			  this.close=function() {
			    closefun();
			     canclebtn.style.display="none";
			     searchbtn.style.display="none";
			     openbtn.className= "";
			    }
}


PointDistanceControl.prototype = new GControl();
//设置新建控件位置与大小
PointDistanceControl.prototype.getDefaultPosition = function() {
		 return new GControlPosition(G_ANCHOR_TOP_RIGHT, new GSize(7,31));
}