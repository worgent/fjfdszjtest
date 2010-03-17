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
	function loadClientAddPage(path) {
	  $("#clientDiv").show() ;
	  $('#clientDiv').html("<center>页面载入中...</center>"); 
	  $.get("/archives/clientMsg.do",{action:'newClient',date:new Date()},showResult);
	  function showResult(res){
		  $('#clientDiv').html(res); 
	  }
	}
	
	
	function loadClientEditPage(id1,pageNum,path) {
		  $("#clientDiv").show() ;
		  $('#clientDiv').html("<center>页面载入中...</center>"); 
		  var urls = getActionMappingURL("/archives/clientMsg",path);
		  var pars="action=editClient&search.pid="+id1+"&page="+pageNum+"&date="+new Date();
		  $.get(urls,pars,showResult);
		  function showResult(res){
			  $('#clientDiv').html(res); 
		  }
	}
	
	function closeClientList() {
		  $("#clientDiv").html("") ;
		  $("#clientDiv").hide() ;
		  $("#clientList").html("") ;
		  $("#clientList").hide() ;
		 
	}
	
	function delClientById(id1,page1,path) {
		  if (!confirm('确认删除该联系人吗？')){
		  	return;
		  }else{
		  	var url = getActionMappingURL("/archives/clientMsg",path);
		    var pars="action=deleteClient&search.pid="+id1;
		  	$.get(url,pars,function(res){
		    	$('#messageDiv').hide();
		    	$('#messageDiv').html(res);
		  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
		  		var codeid = jsonMsgObj.getCodeid();
		  		alert(jsonMsgObj.getMessage());
		  		if (codeid == "0") {
		  			loadClientList(path,page1);
		  		}
			});
		  }
	}
	
	function loadClientList(path,page1) {
	  $('#addressList').html("<center>页面载入中...</center>"); 
	
	  var urls = getActionMappingURL("/archives/clientMsg",path);
	  
	  $.get(urls,{action:'frame',page:page1,date:new Date()},showResult);
	  function showResult(res){
		  $('#clientList').html(res); 
	  }
	} 
-->
</script>

<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>选择</strong>
		</td>
		<td>
			<strong>联系人</strong>
		</td>
		<td>
			<strong>手机</strong>
		</td>
		<td>
			<strong>固话</strong>
		</td>
		<td>
			<strong>单位名称</strong>
		</td>
		<td>
			<b>操作</b>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		    	<input type="radio" name="clientMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.NAME" />', '<s:property value="#g.MOBILE" />', '<s:property value="#g.UNIT" />','<s:property value="#g.TEL" />')"/>
		    </td>
			<td>
				<s:property value="#g.NAME" />
			</td>
			<td>
				<s:property value="#g.MOBILE" />
			</td>
			<td>
				<s:property value="#g.TEL" />
			</td>
			<td>
				<s:property value="#g.UNIT" />
			</td>
			<td>
				<a href="javascript:;" onclick="loadClientEditPage('<s:property value="#g.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">修改</a>|
				<a href="javascript:;" onclick="delClientById('<s:property value="#g.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">删除</a>
			</td>
		</tr>
	</s:iterator>
	<tr  align="left">
		<td colspan="4" align="center">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}" javaScript="ClientList"/>
			</center>
		</td>
		<td colspan="2">
			<input type="hidden" name="frmid" value=""/>
			<input type="hidden" name="frmname" value="">
			<input type="hidden" name="frmmobile" value="">
			<input type="hidden" name="frmunit" value="">
			<input type="hidden" name="frmareacode" value="">
			<input type="hidden" name="frmtel" value="">
			<input type="button" value="确定"  onclick="javascript:onAffirm();" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<input type="button" onclick="javascript:loadClientAddPage();" value="新增" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<input type="button" onclick="javascript:closeClientList();" value="关闭" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
			<div id="messageDiv"></div>
		</td>
	</tr>
	
</table>
<div id="clientDiv"></div><!-- 联系人编辑栏 -->
<script type="text/javascript">
	function test(){
        alert("b");
    };
    document.body.onload=test;//document.body.onload   window.onload
    
    
		function onAffirm(){
			if(document.all.frmid.value!=""){
				//操作处理
				$("#pname").val(document.all.frmname.value);
				$("#pmobile").val(document.all.frmmobile.value);
				$("#punit").val(document.all.frmunit.value);
				$("#ptel").val(document.all.frmtel.value);
				
				$("#clientTr").hide();
		 		$('#clientList').html("");
			}else
			{
				alert('请选择一条记录');
			}
		}

		function setValue(frmid,frmname, frmmobile, frmunit,frmtel){
			document.all.frmid.value = frmid;
			document.all.frmname.value = frmname;
			document.all.frmmobile.value = frmmobile;
			document.all.frmunit.value = frmunit;
			document.all.frmtel.value = frmtel;			
		}
 </script>
