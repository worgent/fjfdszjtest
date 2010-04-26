<%@ page language="java"  import="java.util.Date" errorPage="/error.jsp" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>

<jsp:directive.page import="java.util.List" />
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@include file="/common/taglibs.jsp"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
  <head>
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
			        
					
					
					
					$(window).unload(function (){
						$('.').unbind();
						GUnload();
					});
					
				}else{
					jalert('你使用的浏览器不支持 Google Map!');　　
				} 
			})
		</script>
		
 	<script type="text/javascript">
	     function createIsExist(obj){
	              
				  var url ='<%=path%>/user.do?action=ifexistTribal&search.pusername='+document.getElementById("username").value+"&search.pmembertype="+document.getElementById("membertype").value+"&search.pgroupname="+document.getElementById("groupname").value+"&search.plat="+document.getElementById("search.plat").value;
					
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url); 
						
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return ;
						} else {
							root = oXMLDom.documentElement;
						}
						if (null != root){
							var rowSet = root.selectNodes("//check");
							var mgr=rowSet.item(0).selectSingleNode("value").text;
							if(mgr!=0){
							alert(mgr);
							return false;
							}
							else
							{
						
	   if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='<%=path%>/user.do?action=newCreateTribal';	 //ie7支持用attributes[83]
		}else{
		document.forms[0].action='<%=path%>/user.do?action=newCreateTribal';			//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();			
						
						
	
							}
								
						}
					}catch(e){
						alert(e);
					}
			return true;				
	}
	</script>
	
	<script type="text/javascript">
function showAndHide(obj,types){
  var Layer=window.document.getElementById(obj);
  switch(types){
    case "show":
    Layer.style.display="block";
    break;
    case "hide":
    Layer.style.display="none";
    break;
  }
}
function getValue(obj,str,strId){
  var input=window.document.getElementById(obj);
  input.value=str;
  document.getElementById("merchantsort").value=strId;
}
</script>

	
	        <link rel="StyleSheet" href="<%=request.getContextPath() %>/css/dtree.css" type="text/css" />   
            <script type="text/javascript" src="<%=request.getContextPath() %>/js/dtree.js"></script> 
<style type="text/css">
<!--
body {background:#fff}
#testbox{padding:20px; background:#D0E6F0; border:1px #09c;}

.Menu {position:relative;width:195px;height:127px; overflow:scroll;z-index:100; background: #FFF; border:1px solid #000; margin-top:-93px; display:none;}
.Menu2 {position: absolute; left:0; top:0; width:100%; height:auto; overflow:hidden; z-index:1;}
.Menu2 ul {margin:0; padding:0}
.Menu2 ul li {width:100%; height:25px;line-height:25px;text-indent:15px;border-bottom:1px dashed #ccc;color:#666;cursor:pointer;change:expression(this.onmouseover=function(){ this.style.background="#F2F5EF";},this.onmouseout=function() {this.style.background="";})}
input {width:190px}
.form {width:200px;height:auto; background:#ff0;}
.form div {position:relative;top:0;left:0;margin-bottom:5px}
#List1, #List2, #List3 {left:0px;top:93px;}
-->
</style>


  </head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do'  id="form1">
   <table border="1"  width="90%">
    <tr>
   <td>&nbsp;</td>
   <td>   
   创建部落<br></td>
   <td>&nbsp;</td>
   </tr>
   
    <tr>
   <td  align="right">会员名称:</td>
   <td>
    <s:property value="search.pusername" />
  
    <input type="hidden" id="username"  name="search.pusername"  value="<s:property value="search.pusername" />" />
   &nbsp;
   </td>
   
   <td>&nbsp;</td>
   </tr>
   
    <tr>
   <td  align="right">会员类型:</td>
   <td>
   
   <s:if test="search.pmembertype==1">普通会员</s:if>
   <s:if test="search.pmembertype==2">向导会员</s:if>
   <s:if test="search.pmembertype==3">领主会员</s:if>
   <s:if test="search.pmembertype==4">部落首领</s:if>
    
    
    <input type="hidden" id="membertype"  name="search.pmembertype"  value="<s:property value="search.pmembertype" />" />
   
   </td>
   
   <td>&nbsp;</td>
   </tr>
   
  
    <tr>
   <td  align="right">部落名称:</td>
   <td>
    <input type="text" id="groupname"  name="search.pgroupname"  />
    <font color="#ff0000" size="2">* 必填写(部落需有明确活动主题或兴趣聚合点)</font>

   </td>
   <td>&nbsp;</td>
   </tr>
   
     <tr>
   <td  align="right"><span style="font-size: 10.5pt; font-family: 宋体; color: red;"></span>标注活动地点:</td>
   <td>
    <div id="map_canvas" style="width: 500px; height: 300px"></div>

             <input type="hidden"  id="search.plat" name="search.plat"  /><!-- 纬度 -->
		  	 <input type="hidden"  id="search.plng" name="search.plng"   /><!-- 经度 -->
             <font size="2" color="#ff0000">* 必填写(部落需标注1～2处主要的活动地点)</font>

   </td>
   <td>&nbsp;</td>
   </tr>
   
   

  
  <tr>
   <td  align="right">商家类别:</td>
   <td align="left">
 
   <input type="text" id="txt" name="search.ptxt" onfocus="showAndHide('List1','show');"  >
    <div class="Menu" id="List1">
           <div class="dtree">      
        <script type="text/javascript">   
        d = new dTree('d');
        d.add(0,-1,'商业分类');  
        
        <s:iterator id="prod" value="%{categoriesList.objectList}">
        d.add(<s:property value="#prod.CATEGORIESID" />,<s:property value="#prod.FID" />,
        '<s:property value="#prod.CATEGORIESNAME" />',"javascript:getValue('txt','<s:property value="#prod.CATEGORIESNAME" />','<s:property value="#prod.CATEGORIESID" />');showAndHide('List1','hide');");    
         </s:iterator>
        document.write(d);
    </script>  
                 
      <input type="hidden" id="merchantsort" name="search.pmerchantsort"   >           
                 
                 
           </div>   
      </div>
   
   
   <font size="2" color="#ff0000">* 必填写(选择可能涉及的商家类别)</font>
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
    
         <input type="button" id="saveId" name="save"  onclick="javascript:createIsExist(this);"  value="创 建"  />
    
    
   &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
    
     &nbsp;
   </td>
   </tr>
  
  
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
    
            <font color="#ff0000"><s:actionmessage theme="webframe0"/></font>
        <font color="#ff0000"><s:actionerror theme="webframe0"/></font>
        &nbsp; 
        </td>
   <td>
     &nbsp; 
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>