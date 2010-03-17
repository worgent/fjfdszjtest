<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
<script type="text/javascript">
<!--
	function loadAddressAddPage(path) {
	  $("#addressDiv").show() ;
	  $('#addressDiv').html("<center>页面载入中...</center>"); 
	  $.get("/archives/address.do",{action:'newAddress',date:new Date()},showResult);
	  function showResult(res){
		  $('#addressDiv').html(res); 
	  }
	}
	
	function delAddressById(id1,page1,path) {
		  if (!confirm('确认删除该取件地址吗？')){
		  	return;
		  }else{
		  	var url = getActionMappingURL("/archives/address",path);
		    var pars="action=deleteAddress&search.pid="+id1;
		  	$.get(url,pars,function(res){
		    	$('#messageDiv').hide();
		    	$('#messageDiv').html(res);
		  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
		  		var codeid = jsonMsgObj.getCodeid();
		  		alert(jsonMsgObj.getMessage());
		  		if (codeid == "0") {
		  			loadAddressList(path,page1);
		  		}
			});
		  }
	}
	
	function loadAddressList(path,page1) {
	  $('#addressList').html("<center>页面载入中...</center>"); 
	
	  var urls = getActionMappingURL("/archives/address",path);
	  
	  $.get(urls,{action:'frame',page:page1,date:new Date()},showResult);
	  function showResult(res){
		  $('#addressList').html(res); 
	  }
	} 
	
	function loadAddressEditPage(id1,pageNum,path) {
		  $("#addressDiv").show() ;
		  $('#addressDiv').html("<center>页面载入中...</center>"); 
		  var urls = getActionMappingURL("/archives/address",path);
		  var pars="action=editAddress&search.pid="+id1+"&page="+pageNum+"&date="+new Date();
		  $.get(urls,pars,showResult);
		  function showResult(res){
			  $('#addressDiv').html(res); 
		  }
	}
	function closeAddressList() {
		  
		  $("#addressDiv").html("") ;
		  $("#addressDiv").hide() ;
		  $("#addressList").html("") ;
		  $("#addressList").hide() ;
		 
	}
	
	
//-->
</script>
<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>选择</strong>
		</td>
		<td>
			<strong>省</strong>
		</td>
		<td>
			<strong>市</strong>
		</td>
		<td>
			<strong>县区</strong>
		</td>
		<td>
			<strong>详细地址</strong>
		</td>
		<td>
			<strong>是否默认地址</strong>
		</td>
		<td>
			<b>操作</b>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		   	     <input type="radio" name="clientMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.PROVINCE" />', '<s:property value="#g.PROVINCENAME" />', '<s:property value="#g.CITY" />','<s:property value="#g.CITYNAME" />','<s:property value="#g.COUNTY" />', '<s:property value="#g.COUNTYNAME" />','<s:property value="#g.ADDRESS" />')"/>
		    </td>
			<td>
				<s:property value="#g.PROVINCENAME" />
			</td>
			<td>
				<s:property value="#g.CITYNAME" />
			</td>
			<td>
				<s:property value="#g.COUNTYNAME" />
			</td>
			<td>
				<s:property value="#g.ADDRESS" />
			</td>
			<td>
				<s:property value="#g.ISCHECKNAME" />
			</td>
			<td>
				<a href="javascript:;" onclick="loadAddressEditPage('<s:property value="#g.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">修改</a>|
				<a href="javascript:;" onclick="delAddressById('<s:property value="#g.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">删除</a>
			</td>
		</tr>
	</s:iterator>
	<tr align="left">
		<td colspan="5" align="center">
			<center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"  javaScript="AddressList"/>
			</center>
		</td>
		
		<td colspan="2"> 
		    <input type="hidden" name="frmid" value=""/>
			<input type="hidden" name="frmprovince" value="">
			<input type="hidden" name="frmprovincename" value="">
			<input type="hidden" name="frmcity" value="">
			<input type="hidden" name="frmcityname" value="">
			<input type="hidden" name="frmcounty" value="">
			<input type="hidden" name="frmcountyname" value="">
			<input type="hidden" name="frmaddress" value="">
			<input type="button" value="确定"  onclick="javascript:onAffirm();" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<input type="button" onclick="javascript:loadAddressAddPage();" value="新增" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<input type="button" onclick="javascript:closeAddressList();" value="关闭" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<div id="messageDiv"></div>
			
			
		</td>
		 
	</tr>
	
	
</table>
<div id="addressDiv"></div><!-- 地址编辑栏 -->
<script type="text/javascript">
		function setValue(frmid,frmprovince,frmprovincename,frmcity,frmcityname,frmcounty,frmcountyname,frmaddress){
			document.all.frmid.value = frmid;
			document.all.frmprovince.value = frmprovince;
			document.all.frmprovincename.value = frmprovincename;
			document.all.frmcity.value = frmcity;
			document.all.frmcityname.value = frmcityname;
			document.all.frmcounty.value = frmcounty;
			document.all.frmcountyname.value = frmcountyname;
			document.all.frmaddress.value = frmaddress;
		}
		
		function onAffirm(){
			if(document.all.frmid.value!=""){
				//操作处理
				$("#paddressid").val(document.all.frmid.value);
				$("#pprovince").val(document.all.frmprovince.value);
				$("#lprovince").val(document.all.frmprovincename.value);
				$("#pcity").val(document.all.frmcity.value);
				$("#lcity").val(document.all.frmcityname.value);
				$("#pcounty").val(document.all.frmcounty.value);
				$("#lcounty").val(document.all.frmcountyname.value);
				$("#paddress").val(document.all.frmaddress.value);
				
				$("#trId").hide();
		 		$('#addressList').html("");
			}else
			{
				alert('请选择一条记录');
			}
		}
 </script>
