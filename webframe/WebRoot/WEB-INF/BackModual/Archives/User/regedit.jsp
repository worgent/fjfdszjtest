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
		<script type="text/javascript">
	     function isExist(obj){
	
				  var url ='<%=path%>/user.do?action=isExist&search.pusername='+document.getElementById("username").value+'&search.checkcode='+document.getElementById("checkcode").value;
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
		document.forms[0].attributes[83].value='/user.do?action=insert';//ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=insert';	//alert('firefox');firefox浏览器用action
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

 <script type='text/javascript' src='/js/DateTimeCalendar.js'></script>
 <script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确定保存。。。');return true;}});
	$("#username").formValidator({onfocus:"用户名至少6个字符,最多16个字符",oncorrect:"输入格式正确"}).inputValidator({min:6,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
	$("#password").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#repassword").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password",operateor:"=",onerror:"两次密码不一致,请确认"});
	});
	</script>
	
		
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
	
	
	

</head>

	<body>
<center>
<form  method='POST' name='form1' action='/user.do?action=insert'  id="form1">
   <table width="905" height="301" border="1">
     <tr> 
   <td>&nbsp;</td>
   <td> 
       <strong>注册页面</strong>
   </td>
   <td>&nbsp;</td>
   </tr>
     <tr> 
   <td>&nbsp;</td>
   <td>
   &nbsp;
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr> 
   <td>用户名:</td>
   <td>
   <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
    <tr> 
   <td>昵称:</td>
   <td>
   <input type="text" id="nickname"  name="search.pnickname"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>密码:</td>
   <td>
   <input type="password" id="password"  name="search.ppassword"   />
   <font color="red">*</font>
   </td>
   <td><div id="passwordTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>确认密码:</td>
   <td>
   <input type="password" id="repassword"  name="search.prepassword"   />
   <font color="red">*</font>
   </td>
   <td><div id="repasswordTip" style="width:250px"></div></td>
   </tr>
    <tr>
      <td>性别：</td>
      <td>
     <select name="search.psex" id="sex">
          <option value="1"   <s:if test="#user.SEX==1">selected</s:if> >男</option>
          <option value="2"   <s:if test="#user.SEX==2">selected</s:if> >女</option>
          <option value="3"   <s:if test="#user.SEX==3">selected</s:if> >保密</option>
   </select>
      <font color="red">*</font>
      </td>
      <td>&nbsp;</td>
   </tr>
    <tr>
      <td>验证码：</td>
      <td>
       <input type="text"  id="checkcode" name="search.checkcode"  size="6" maxlength="4"/> 
       <img border=0 src="../../../../image.jsp" id="imgcode" style="cursor:pointer;" border="0" onClick="document.getElementById('imgcode').src='../../../../image.jsp'" title="看不清？！换一个" alt="看不清？！换一个"/>
                 <input type="button" value="看不清，换一张" onClick="document.getElementById('imgcode').src='../../../../image.jsp'"> <span id="msg_6"> </span>
      <font color="red">*</font>
      </td>
      <td>&nbsp;</td>
   </tr>
  
    
   
    <tr> 
   <td> 地图位置标注：</td>
   <td  colspan="2">
             <div id="map_canvas" style="width: 500px; height: 300px"></div>
		 	 <s:hidden name="search.plat" id="search.plat" ></s:hidden><!-- 纬度 -->
		  	 <s:hidden name="search.plng" id="search.plng"></s:hidden><!-- 经度 -->
		  			
	</td>
   </tr>
   
  
   <tr> 
   <td> <input type="checkbox" id="checkbox1"  name="search.checkbox1"/>我已经看过并同意
   <a href="user.do?action=article">《猎图网服务条款》</a></td>
   
   <td colspan=2>
      <font color="#ff0000" size="2"> 提供1个地图位置标注(提供家庭、工作或学习、常去消费的地方、好友住地等可选地图位置),点图即可</font>
   &nbsp;
   </td>
   </tr>
   
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="button" id="saveId" name="save"  onclick="javascript:isExist(this);"  value="保 存"  />
      &nbsp;<input type="reset"  id="setId"  name="rereset" value="重 置" />
      </td>
   <td>&nbsp;</td>
   </tr>
 </table>

</form>
</center>
</body>
</html>