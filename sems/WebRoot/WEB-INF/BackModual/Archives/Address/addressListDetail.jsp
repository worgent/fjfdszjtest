<%@page contentType="text/html; charset=UTF-8"%>
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
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
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
		<td align="center">
			<strong>操作</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}" >
		<tr>
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
			   <input type="radio" name="recCheck" <s:if test="#g.ISCHECK==1">checked</s:if>
			   onclick="setCheck('<s:property value="#g.ID"/>','<s:property value="#g.PROVINCE"/>','<s:property value="#g.CITY"/>','<s:property value="#g.COUNTY"/>','<s:property value="#g.ADDRESS"/>')"/>
			</td>
			<td align="center" >
				<a href='<%=path%>/archives/address.do?action=new' target='_self'>增加</a>
				<s:if test="#g.ISCHECK==0">
				    <a href='<%=path%>/archives/address.do?action=edit&search.pid=<s:property value="#g.ID" />' target='_self'>编辑</a>
					<a href="javascript:fun_delete(<s:property value="#g.ID"/>)">删除</a>
				</s:if>
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="7" align="center">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
			</center>
		</td>
	</tr>
</table>
<script type="text/javascript">

//通过ajax方式,验证是否登录成功2010-04-14
	function setCheck(pid,pprovince,pcity,pcounty,paddress){
	    		    var url = '<%=path%>/archives/address.do?action=setcheck&search.pid='+pid+'&search.pprovince='+pprovince+'&search.pcity='+pcity+'&search.pcounty='+pcounty+'&search.paddress='+encodeURIComponent(encodeURIComponent(paddress));
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url);  
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return;
						} else {
							root = oXMLDom.documentElement;
						}
						
						if (null != root){
							var rowSet = root.selectNodes("//result");
							var value=rowSet.item(0).selectSingleNode("value").text;
							if(0<value){//验证码错误
							   //jAlert("设置默认地址成功!","提示");
							   window.location.href='<%=path%>/archives/address.do';
							}else{//登录异常
							   //jAlert("设置默认地址出错!","提示"); 
							   window.location.href='<%=path%>/archives/address.do';
							}
						}	
					}catch(e){ 
						jAlert(e,"连接数据库不正确,请与管理员联系!","提示");
					}
		}



		function fun_delete(pid){
			jConfirm('您确定删除该信息!', '确认框', function(r) {	
	    		if (r){
				   var url = '<%=path%>/archives/address.do?action=delete&search.pid='+pid
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url);  
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return;
						} else {
							root = oXMLDom.documentElement;
						}
						
						if (null != root){
							var rowSet = root.selectNodes("//delete");
						
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    //jAlert("删除成功!",fuction(r){
							   window.location.href='<%=path%>/archives/address.do';
							    //});
							}
							else{
							    jAlert('删除失败!');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
 </script>
