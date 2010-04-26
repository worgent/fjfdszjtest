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
    var polyLine;
    //线条颜色
    this.polyLineColor = "#3355ff";
    //形状填充颜色
    this.polyFillColor = "#335599";
    var pointMarkers = new Array();//所有标注点
    var perpoint=null;//前一个点
    var curpoint=null;//当前点
    //回调函数
    var callback=callbackfun;
    
    /*添加一个矩形的坐标点*/
    function addpointMarkers(point) {
		     if(point) {
			      //点属性描述
			      var square = new GIcon();
			      square.image = "img/map/square.png";
			      addIcon(square);
			      //标注点
			      var marker =new GMarker(point, {icon:square, draggable:true, bouncy:false, dragCrossMove:true});
			      pointMarkers.push(marker);//放入数组
			      curpoint=marker;//当前点
			      map.addOverlay(marker);
			      //托放事件
			      GEvent.addListener(marker, "drag", function() {
				       curpoint=marker;
				       drawLine();
			      });
			      GEvent.addListener(marker, "dragend", function() {
				       curpoint=marker;
				       drawLine();
			      });
			      //划线
		          drawLine();
		          perpoint=marker;//之前一个点
		     }
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
    function drawLine() {
	      if(perpoint&&curpoint){
	      		pointMarkers.push(perpoint);
				pointMarkers.push(curpoint);
				 //显示距离
	     		var distance=perpoint.getLatLng().distanceFrom(curpoint.getLatLng(),6378137);
	     		var myHtml = "<table>" +
									 "<tr><td>距离:</td> <td> "+distance+
			                         "</table>"; 
				 map.openInfoWindow(curpoint, myHtml); 
	     }else {
	             pointMarkers.push(curpoint);
	     }
	     //画图形
	     polyLine = new GPolyline(pointMarkers, this.polyLineColor, 2, .8, .1);
	     map.addOverlay(polyLine);
    }
    
    /*清除图形*/
    this.clearLine=function () {
        map.removeOverlay();
    }
    /*取得图形*/
    this.getPolyLine=function(){
	    return polyLine;
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
							    if(this.openState==true){
							       openbtn.className= "on";
							   }
							
							   container.appendChild(openbtn);
							   openbtn.appendChild(document.createTextNode(this.label));
							   GEvent.addDomListener(openbtn, "click", this.open);
							   searchbtn.title= "搜索矩形框范围内的信息";
							    if(this.openState==false){
							       searchbtn.style.display="none";
							   }
							
							   canclebtn.title= "关闭矩形框搜索功能";
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
//控件
PointDistanceControl.prototype = new GControl();
//设置新建控件位置与大小
PointDistanceControl.prototype.getDefaultPosition = function() {
		 return new GControlPosition(G_ANCHOR_TOP_RIGHT, new GSize(7,31));
}