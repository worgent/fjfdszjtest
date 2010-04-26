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
 <s:head theme="ajax" /> 
 <script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确定保存。。。');return true;}});
	$("#position").formValidator({onshow:"请输入地图位置标注",onfocus:"范围1到16个汉字",oncorrect:"格式正确"}).inputValidator({min:1,max:16,onerror:"输入地图位置标注非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"格式不正确"});
	});
	</script>
  <script type='text/javascript' src='/js/setday.js'></script>
 <script type="text/javascript">
	  function  merchant(obj){
	  if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=merchantEnter';//ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=merchantEnter';	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		
	  }
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
			      		  
			      		  alert(document.getElementById("search.plat").value);
			              alert(document.getElementById("search.plng").value);
			      		  
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
<form  method='POST' name='form1' action='/user.do?action=editOnlyList'  id="form1">

   <table width="794" height="931" border="1">
   <tr>
   <td>用户名:</td>
   <td>
      <s:property value="search.USERNAME" />
      <input type="hidden" id="username"  name="search.pusername"  value="<s:property value="search.USERNAME" />" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr>
      <td>性别：</td>
      <td>
       <select name="search.psex" id="sex">
          <option value="1"   <s:if test="search.SEX==1">selected</s:if> >男</option>
          <option value="2"   <s:if test="search.SEX==2">selected</s:if> >女</option>
          <option value="3"   <s:if test="search.SEX==3">selected</s:if> >保密</option>
       </select>
   
      <font color="red">*</font>
      </td>
      <td>&nbsp;</td>
   </tr>
   

   
      <tr> 
   <td>经验值:</td>
   <td>
   <input type="text" id="experience"  name="search.pexperience" value="<s:property value="search.EXPERIENCE" />" />
   
   </td>
   <td></td>
   </tr>
   
    
     <tr> 
   <td>贡献值:</td>
   <td>
   <input type="text" id="contribute"  name="search.pcontribute"  value="<s:property value="search.CONTRIBUTE" />"/>
 
   </td>
   <td>&nbsp;</td>
   </tr>
   
  <tr> 
   <td>名望值:</td>
   <td>
   <input type="text" id="fame"  name="search.pfame"  value="<s:property value="search.FAME"/>"/>
 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr> 
   <td>实力值:</td>
   <td>
   <input type="text" id="strength"  name="search.pstrength"  value="<s:property value="search.STRENGTH" />"/>
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr> 
   <td>人气值:</td>
   <td>
   <input type="text" id="favor"  name="search.pfavor"  value="<s:property value="search.FAVOR" />"/>
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr> 
   <td>财力值:</td>
   <td>
   <input type="text" id="financial"  name="search.pfinancial"  value="<s:property value="search.FINANCIAL" />"/>
   </td>
   <td>&nbsp;</td>
   </tr>
 
  <tr> 
   <td> 地图位置标注：</td>
   <td  colspan="2">
             <div id="map_canvas" style="width: 500px; height: 300px"></div>

             <input type="hidden"  id="search.plat" name="search.plat" value="<s:property value="search.LAT" />"   /><!-- 纬度 -->
		  	 <input type="hidden"  id="search.plng" name="search.plng" value="<s:property value="search.LNG" />"  /><!-- 经度 -->
		  
	</td>
   </tr>
   
 
   
   
   
   
   
    <tr> 
   <td>&nbsp;</td>
   <td  colspan="2">
   
   
   
   
   &nbsp;
     </td>
   </tr>
  
   
     <tr> 
   <td>&nbsp;</td>
   <td>
  &nbsp;
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td>猎友实名:</td>
   <td>
     <input type="text" id="realName"  name="search.prealname"   value="<s:property value="search.REALNAME" />" />
      <font color="red">*</font>
   </td>
   <td><div id="realNameTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>手机号码:</td>
   <td>
     <input type="text" id="telephone"  name="search.ptelephone"  value="<s:property value="search.TELEPHONE" />" />
   </td>
   <td><div id="telephoneTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>职业:</td>
   <td>
     <input type="text" id="occupation"  name="search.poccupation"   value="<s:property value="search.OCCUPATION" />"/>
   </td>
   <td><div id="occupationTip" style="width:250px"></div></td>
   </tr>
 
   <tr>
   <td>行业:</td>
   <td>
     <input type="text" id="vocation"  name="search.pvocation"    value="<s:property value="search.VOCATION" />" />
   </td>
   <td><div id="vocationTip" style="width:250px"></div></td>
   </tr>
   
  <tr>
   <td>生日:</td>
   <td> 
      <s:datetimepicker   id="birthday"  name="search.pbirthday"    value='%{search.BIRTHDAY}'  displayFormat="yyyy-MM-dd"/>
   </td>
   <td><div id="birthdayTip" style="width:250px"></div></td>
   </tr>
 
   <tr>
   <td>QQ:</td>
   <td>
     <input type="text" id="qq"  name="search.pqq"   value="<s:property value="search.QQ" />"/>
   </td>
   <td><div id="qqTip" style="width:250px"></div></td>
   </tr>
 
 
    <tr>
   <td>工作位置:</td>
   <td>
     <input type="text" id="workPlace"  name="search.pworkplace"  value="<s:property value="search.WORKPLACE" />" />
   </td>
   <td><div id="workPlaceTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>推荐者:</td>
   <td>
     <input type="text" id="commend"  name="search.pcommend"  value="<s:property value="search.COMMEND"/>"/>
   </td>
     <td><div id="commendTip" style="width:250px"></div></td>
   </tr>

   <tr>
   <td>喜爱的地方:</td>
   <td>
     <input type="text" id="favorPlace"  name="search.pfavorplace"  value="<s:property value="search.FAVORPLACE" />" />
   </td>
   <td><div id="favorPlaceTip" style="width:250px"></div></td>
   </tr>
   
   
   <tr>
   <td>常去的地方:</td>
   <td>
     <input type="text" id="oftenGoPlace"  name="search.poftengoplace"  value="<s:property value="search.OFTENGOPLACE" />" />
   </td>
   <td><div id="oftenGoPlaceTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>常消费的地方:</td>
   <td>
     <input type="text" id="oftenConsumePlace"  name="search.poftenconsumeplace"  value="<s:property value="search.OFTENCONSUMEPLACE" />" />
   </td>
   <td><div id="oftenConsumePlaceTip" style="width:250px"></div></td>
   </tr>
   

   
    <tr>
   <td>大部分时间做的事:</td>
   <td>
     <input type="text" id="oftenDoThing"  name="search.poftendothing"  value="<s:property value="search.OFTENDOTHING" />" />
   </td>
   <td><div id="oftenDoThingTip" style="width:250px"></div></td>
   </tr>
      
    <tr>
   <td> 常参加的活动:</td>
   <td>
     <input type="text" id="oftenJoinActivity"  name="search.poftenjoinactivity" value="<s:property value="search.OFTENJOINACTIVITY" />"  />
   </td>
   <td><div id="oftenJoinActivity" style="width:250px"></div></td>
   </tr>
   
      <tr>
   <td> 看过的书:</td>
   <td>
     <input type="text" id="lookedBook"  name="search.plookedbook"    value="<s:property value="search.LOOKEDBOOK" />"/>
   </td>
   <td><div id="lookedBookTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>兴趣爱好:</td>
   <td>
     <input type="text" id="interestLike"  name="search.pinterestlike"  value="<s:property value="search.INTERESTLIKE" />"  />
   </td>
   <td><div id="interestLikeTip" style="width:250px"></div></td>
   </tr>
 

   <tr>
   <td>传音入密:</td>
   <td>
     <input type="text" id="newMood"  name="search.pnewmood"  value="<s:property value="search.NEWMOOD" />" />
   </td>
   <td><div id="newMoodTip" style="width:250px"></div>
   <input type="hidden" id="userid"  name="search.USERID"   value="<s:property value="search.USERID" />" />
   
   </td>
   </tr>
   

   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
            <input type="submit" id="saveId"  name="save" value="保 存" />
      &nbsp;<input type="reset"  id="setId"  name="rereset" value="重 置" />
      
      </td>
   <td>&nbsp;</td>
   </tr>
   

 
 </table>

</form>
</center>
</body>
</html>