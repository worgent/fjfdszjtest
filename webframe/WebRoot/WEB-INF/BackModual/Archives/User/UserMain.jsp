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

	<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	 <script type="text/javascript">
       function change()
		{ 
	//doc = new ActiveXObject("Microsoft.XMLDOM"); 
   // doc.load("data.xml"); 
   // var rss = doc.documentElement; 
    //var channel  = rss.getElementsByTagName("channel"); 
    //var item = channel[0].getElementsByTagName("item"); 
    //var title = item[0].getElementsByTagName("title"); 
	   		

		           var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
                       cdsales.async=false; //使用异步加载
                      // cdsales.onreadystatechange=LoadedSales;
                      cdsales.load("user.do?action=listAllCity&search.fid="+document.getElementById("provinceId").value);   

             var bi=cdsales.documentElement.selectNodes("item");
            
            if(bi!=null&&bi.length>0)
            {
              
                for(var i=0;i<bi.length;i++)
                {     
                {
                document.getElementById("cityId").length=i+1; 
	   			document.getElementById("cityId").options[i].value = bi[i].childNodes[0].text;
	   			document.getElementById("cityId").options[i].text =  bi[i].childNodes[1].text;
                }
                }
            }
            else
            {
              
               //for (var i = 0; i <document.getElementById("cityId").options.length; i++) {   
               // document.getElementById("cityId").options.remove(document.getElementById("cityId")[i]);  
               // }
                document.getElementById("cityId").options.length = 1;   
                
                document.getElementById("cityId").options[0].value ="0";
	   			document.getElementById("cityId").options[0].text = "请选择";
	   			
	   			document.getElementById("districtId").options.length = 1;   
                document.getElementById("districtId").options[0].value ="0";
	   			document.getElementById("districtId").options[0].text = "请选择";
	   			
	   			
            }
                       
		
		
		
		///////////////////////////
		//	var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		//	var objLst = new ActiveXObject("Microsoft.XMLDOM");
		//	xmlDoc.async="false";
        //    xmlDoc.load("user.do?action=listAllCity&search.fid="+document.getElementById("provinceId").value);   
	   	//	objLst = xmlDoc.getElementsByTagName("check");

	   	 //        	    var root;
		//				if (xmlDoc.parseError.errorCode != 0) {
		//					var myErr = xmlDoc.parseError;
		//					return ;
		//				} else {
		//					root = xmlDoc.documentElement;
		//				}
						
	   	//	if(objLst.length==0) {
		//   		document.getElementById("cityId").length=1;
		 //  		document.getElementById("cityId").options[0].value="";
		 //  		document.getElementById("cityId").options[0].text="";
	   	//	}
	   	//	  for(var i=0;i<objLst.length;i++) {
	   	//		document.getElementById("cityId").length=i+1; 
	   	//		document.getElementById("cityId").options[i].value = objLst.item(i).childNodes(0).text;
	   	//		document.getElementById("cityId").options[i].text = objLst.item(i).childNodes(1).text;
	   	//	}
	   		
	   		
	  
						
						
		}</script>
		
		 <script type="text/javascript">
       function changeDistrict()
		{ 
    
		           var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
                       cdsales.async=false; //使用异步加载
                      // cdsales.onreadystatechange=LoadedSales;
                      cdsales.load("user.do?action=listAllDistrict&search.fid="+document.getElementById("cityId").value);   
 
             var bi=cdsales.documentElement.selectNodes("item");
            if(bi!=null&&bi.length>0)
            {
                for(var i=0;i<bi.length;i++)
                {     
                {
                document.getElementById("districtId").length=i+1; 
	   			document.getElementById("districtId").options[i].value = bi[i].childNodes[0].text;
	   			document.getElementById("districtId").options[i].text =  bi[i].childNodes[1].text;
                }
                }
            }
              else
            {
                 document.getElementById("districtId").options.length = 1;   
                document.getElementById("districtId").options[0].value ="0";
	   			document.getElementById("districtId").options[0].text = "请选择";
            }
			
		}</script>
		
		
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
	<body >

<center>
<form  method='POST' name='form1' action='/user.do?action=personal'  id="form1">

   <table width="90%"  border="1">
   <s:iterator id="user"  value="%{userOnlyList.objectList}" status="st">
   <tr>
   <td>用户名:</td>
   <td>
    <s:property value="#user.USERNAME" />
      <input type="hidden" id="username"  name="search.pusername"  value="<s:property value="#user.USERNAME" />" />
   </td>
   <td>&nbsp;</td>
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
      <td>&nbsp;    </td>
   </tr>
   
  
   
   <tr> 
   <td>邮箱:</td>
   <td>
   <s:property value="#user.EMAIL"/>
   <font color="red">*</font>
   </td>
   <td><div id="positionTip" style="width:250px"></div></td>
   </tr>
   
   
   
   
   
      <tr> 
   <td>经验值:</td>
   <td>
     <s:property value="#user.EXPERIENCE" />
   
   </td>
   <td></td>
   </tr>
   
    
     <tr> 
   <td>贡献值:</td>
   <td>
   <s:property value="#user.CONTRIBUTE" />
 
   </td>
   <td>&nbsp;</td>
   </tr>
   
  <tr> 
   <td>名望值:</td>
   <td>
   <s:property value="#user.FAME"/>
 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr> 
   <td>实力值:</td>
   <td>
   <s:property value="#user.STRENGTH" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr> 
   <td>人气值:</td>
   <td>
   <s:property value="#user.FAVOR" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr> 
   <td>财力值:</td>
   <td>
    <s:property value="#user.FINANCIAL" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
 <tr> 
   <td> 地图位置标注：</td>
   <td  colspan="2">
             <div id="map_canvas" style="width: 500px; height: 300px"></div>

             <input type="hidden"  id="search.plat" name="search.plat" value="<s:property value="#user.LAT" />"  /><!-- 纬度 -->
		  	 <input type="hidden"  id="search.plng" name="search.plng" value="<s:property value="#user.LNG" />"  /><!-- 经度 -->
		  
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
   <td  colspan="3" align="left"><font color="red">如下补充信息（向导、领主、部落首领必须填写，普通猎友自主选择)</font></td>
   </tr> 
    
  
   <tr> 
     <td align="left"></td>
     <td align="left"></td>
     <td align="left"></td>
   </tr> 
   
   
   
   <tr> 
     <td>会员类型:</td>
     <td align="left">
        <select id="membertype" name="search.pmembertype" >
        <option value="1"  <s:if test="#user.MEMBERTYPE==1">selected</s:if> >
		       普通猎友
		 </option>		
		<option value="2"  <s:if test="#sel.MEMBERTYPE==2">selected</s:if> >
		       向导会员
		</option>
		<option value="3"  <s:if test="#sel.MEMBERTYPE==3">selected</s:if> >
		       领主会员
		</option>
		<option value="4"  <s:if test="#sel.MEMBERTYPED==4">selected</s:if> >
		       部落首领
		</option>
		</select>
     
     
     </td>
     <td align="left"></td>
   </tr> 
   
   
  <tr>
   <td colspan="3" align="left">
                           省份:   
                       <select id="provinceId" name="search.pprovince" onchange="change()">
							 <s:iterator id="sel"  value="%{provinceList.objectList}">
									<option value="<s:property value="#sel.ID" />"  <s:if test="#sel.ID==#user.PROVINCEID">selected</s:if> >
										<s:property value="#sel.NAME" />
									</option>
							 </s:iterator>
						</select>
    
                        地市：<select id="cityId" name="search.pcity"   onchange="changeDistrict()">
							 <s:iterator id="cit"  value="%{cityList.objectList}">
									<option value="<s:property value="#cit.ID" />"  <s:if test="#cit.ID==#user.CITYID">selected</s:if> >
										<s:property value="#cit.NAME" />
									</option>
							 </s:iterator>
						</select>
						
					  县、区：<select id="districtId" name="search.pdistrict" >
							 <s:iterator id="district"  value="%{districtList.objectList}">
									<option value="<s:property value="#district.ID" />"  <s:if test="#district.ID==#user.DISTRICTID">selected</s:if> >
										<s:property value="#district.NAME" />
									</option>
							 </s:iterator>
						</select>
        
        
         </td>
   </tr> 
   
   
   
   <tr>
   <td>猎友实名:</td>
   <td>
     <input type="text" id="realName"  name="search.prealname"   value="<s:property value="#user.REALNAME" />" />
      <font color="red">*</font>
   </td>
   <td><div id="realNameTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>手机号码:</td>
   <td>
     <input type="text" id="telephone"  name="search.ptelephone"  value="<s:property value="#user.TELEPHONE" />" />
   </td>
   <td><div id="telephoneTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>手机型号:</td>
   <td>
     <input type="text" id="pteltypeId"  name="search.pteltype"  value="<s:property value="#user.TELTYPE" />" />
   </td>
   <td>&nbsp;</td>
   </tr> 
    
   	 
    
   <tr>
   <td>职业:</td>
   <td>
     <input type="text" id="occupation"  name="search.poccupation"   value="<s:property value="#user.OCCUPATION" />"/>
   </td>
   <td><div id="occupationTip" style="width:250px"></div></td>
   </tr>
 
   <tr>
   <td>行业:</td>
   <td>
     <input type="text" id="vocation"  name="search.pvocation"    value="<s:property value="#user.VOCATION" />" />
   </td>
   <td><div id="vocationTip" style="width:250px"></div></td>
   </tr>
   
  <tr>
   <td>生日:</td>
   <td>
   <s:if test="#user.BIRTHDAY!=null">
   <s:datetimepicker name="search.pbirthday"  value="%{#user.BIRTHDAY}"    displayFormat="yyyy-MM-dd"/> 
   </s:if>
    <s:if test="#user.BIRTHDAY==null">
    <s:datetimepicker name="search.pbirthday"  value="%{#user.BIRTHDAY}"  value="today"  displayFormat="yyyy-MM-dd"/> 
    </s:if>
  
  
  </td>
   <td><div id="birthdayTip" style="width:250px"></div></td>
   </tr>
 
   <tr>
   <td>QQ:</td>
   <td>
     <input type="text" id="qq"  name="search.pqq"   value="<s:property value="#user.QQ" />"/>
     
     
   </td>
   <td><div id="qqTip" style="width:250px"></div></td>
   </tr>
 
 
    <tr>
   <td>工作位置:</td>
   <td>
     <input type="text" id="workPlace"  name="search.pworkplace"  value="<s:property value="#user.WORKPLACE" />" />
   </td>
   <td><div id="workPlaceTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>推荐者:</td>
   <td>
     <input type="text" id="commend"  name="search.pcommend"  value="<s:property value="#user.COMMEND"/>"/>
   </td>
     <td><div id="commendTip" style="width:250px"></div></td>
   </tr>

   <tr>
   <td>喜爱的地方:</td>
   <td>
     <input type="text" id="favorPlace"  name="search.pfavorplace"  value="<s:property value="#user.FAVORPLACE" />" />
   </td>
   <td><div id="favorPlaceTip" style="width:250px"></div></td>
   </tr>
   
   
   <tr>
   <td>常去的地方:</td>
   <td>
     <input type="text" id="oftenGoPlace"  name="search.poftengoplace"  value="<s:property value="#user.OFTENGOPLACE" />" />
   </td>
   <td><div id="oftenGoPlaceTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>常消费的地方:</td>
   <td>
     <input type="text" id="oftenConsumePlace"  name="search.poftenconsumeplace"  value="<s:property value="#user.OFTENCONSUMEPLACE" />" />
   </td>
   <td><div id="oftenConsumePlaceTip" style="width:250px"></div></td>
   </tr>
   

   
    <tr>
   <td>大部分时间做的事:</td>
   <td>
     <input type="text" id="oftenDoThing"  name="search.poftendothing"  value="<s:property value="#user.OFTENDOTHING" />" />
   </td>
   <td><div id="oftenDoThingTip" style="width:250px"></div></td>
   </tr>
      
    <tr>
   <td> 常参加的活动:</td>
   <td>
     <input type="text" id="oftenJoinActivity"  name="search.poftenjoinactivity" value="<s:property value="#user.OFTENJOINACTIVITY" />"  />
   </td>
   <td><div id="oftenJoinActivity" style="width:250px"></div></td>
   </tr>
   
      <tr>
   <td> 看过的书:</td>
   <td>
     <input type="text" id="lookedBook"  name="search.plookedbook"    value="<s:property value="#user.LOOKEDBOOK" />"/>
   </td>
   <td><div id="lookedBookTip" style="width:250px"></div></td>
   </tr>
   
    <tr>
   <td>兴趣爱好:</td>
   <td>
     <input type="text" id="interestLike"  name="search.pinterestlike"  value="<s:property value="#user.INTERESTLIKE" />"  />
   </td>
   <td><div id="interestLikeTip" style="width:250px"></div></td>
   </tr>
 

   <tr>
   <td>传音入密:</td>
   <td>
     <input type="text" id="newMood"  name="search.pnewmood"  value="<s:property value="#user.NEWMOOD" />" />
   </td>
   <td><div id="newMoodTip" style="width:250px"></div></td>
   </tr>
   <input type="hidden" id="userid"  name="search.USERID"   value="<s:property value="#user.USERID" />" />
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
            <input type="submit" id="saveId"  name="save" value="保存"  />
      &nbsp;<input type="reset"  id="setId"  name="rereset" value="重置"  />
      
      </td>
   <td>&nbsp;</td>
   </tr>
   
   
  
   
   
    </s:iterator>
   
   
 
 </table>

</form>
</center>
</body>
</html>