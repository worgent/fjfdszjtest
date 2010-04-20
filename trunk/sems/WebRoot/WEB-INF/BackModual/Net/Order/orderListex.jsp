<%@page contentType="text/html; charset=utf-8"%>
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
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
	</head>
	<body>

<center>
<form  method='POST' name='form1' action='/archives/user.do?action=update'  id="form1">
  <table width="90%" height="190" border="0">
  <tr>
   <td> 
       <strong>网上寄件</strong>
   </td>
  </tr>
  <tr>
	<td >
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>
  </tr>
  <tr> 
     <td align="left">
     <font color="red">必填</font>
     </td>
   </tr>
   <tr> 
   <td>联系人:
   <s:property value="%{order.NAME}"/>
   &nbsp;手机:
   <s:property value="%{order.MOBILE}"/> 
   &nbsp;固定电话:
   <s:property value="%{order.TEL}"/> 
   单位名称:
   <s:property value="%{order.UNIT}"/> 
   </td>
   </tr>   
   <tr> 
   <td>取件地址:
   <s:property value="%{order.PROVINCENAME}"/>
   &nbsp;<s:property value="%{order.CITYNAME}"/>
   &nbsp;<s:property value="%{order.COUNTYNAME}"/>
   </td>
   </tr>
   <tr> 
   <td align="left">
      详细地址:<s:property value="%{order.ADDRESS}"/>   
   </td>   
   </tr>
   <tr>
   <td>邮件种类:
   <s:property value="%{order.MAILTYPENAME}"/> 
   </td>
   </tr>
   <tr> 
     <td align="left">
     <font color="red">选填</font>
     </td>
   </tr>
   <tr>
   <td>
	    揽收要求:
	   <s:textfield  id="pclientremark"  name="search.pclientremark" value="%{order.CLIENTREMARK}"  cssStyle="width:80%"></s:textfield>
	   <div id="pclientremarkTip"  ></div>
   </td>
   </tr>
   
   <tr> 
   <td>预约时间:
   <s:property value="%{order.BOOKINGTIME}"/>  
   </td>
   </tr>
   
   <tr>
   <td>寄件数量:
   <s:property value="%{order.ORDERINGNUM}"/>
   &nbsp;物品重量:
   <s:property value="%{order.ORDERINGWEIGHT}"/>(千克)
   </td>
   </tr>  
    
   <tr> 
   <td align="left">
   目的地:
   <s:property value="%{order.RECPROVINCENAME}"/>(省)
   &nbsp;<s:property value="%{order.RECCITYNAME}"/>(市)
   </td>
   </tr>

   <tr>
   <td  align="center">
      <s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
      <s:if test="%{order.ORDERINGVALUE.substring(7,8)==1}">
			<input type="button" id="btnupdate" name="btnupdate"  onclick="javascript:save('<s:property value="%{action}"/>');"  value="保 存"  />
	  </s:if>
	  <s:if test="%{order.ORDERINGVALUE.substring(8,9)==1}">
 	        &nbsp; &nbsp; &nbsp;<input type="button" id="btnupdateauding" name="btnupdateauding"  onclick="javascript:save('send');"  value="保存并寄件"  />
 	  </s:if>
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  /></td>
   </tr>
 </table>
</form>
</center>
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab1" class="f_tabClass1">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail','',1,11);">全部</a>
					</li>
					<li id="tab2" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待寄件',2,11);">等待寄件</a>
					</li>
					<li id="tab3" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待收件',3,11);">等待收件</a>
					</li>
					<li id="tab4" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待处理',4,11);">等待处理</a>
					</li>
					<li id="tab5" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户催揽',5,11);">客户催揽</a>
					</li>
					<li id="tab6" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户撤单',6,11);">客户撤单</a>
					</li>
					<li id="tab7" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','超范围',7,11);">超范围</a>
					</li>
					<li id="tab8" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','无法联系客户',8,11);">无法联系客户</a>
					</li>
					<li id="tab9" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户取消',9,11);">客户取消</a>
					</li>
					<li id="tab10" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户要求延时',10,11);">客户要求延时</a>
					</li>
					<li id="tab11" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','揽收成功',11,11);">揽收成功</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="f_main">
			<div id="defaultlist"></div>
		</div>
<script language="javascript" type="text/javascript">	
		    //加载默认页面    
	$(document).ready(function(){
		    //默认加载页面
		    loadDefaultListex('<%=path%>/net/order.do?action=listdetail','',1,11);
		    //验证
		    $.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存');return true;}});
		    $("#pclientremark").formValidator({onfocus:"",oncorrect:""});
	});
	
	//保存数据
	function save(action){
	    if($.formValidator.pageIsValid()){
			var url ='<%=path%>/net/order.do?action='+action;
			document.forms[0].action=url;
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
</script>		    		
	</body>
</html>