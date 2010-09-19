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
			<strong>处理编号</strong>
		</td>
		<td>
			<strong>订单号</strong>
		</td>
		<td>
			<strong>处理内容</strong>
		</td>
		<td>
			<strong>处理人</strong>
		</td>
		<td>
			<strong>创建时间</strong>
		</td>
		<td align="center">
			<strong>操作</strong>
		</td>
		<td>
			<a href='<%=path%>/net/proLog.do?action=new' target='_self'>增加</a>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
			<td>
				<s:property value="#g.ID" />
			</td>
			<td>
				<s:property value="#g.ORDERID" />
			</td>
			<td>
				<s:property value="#g.PROCONTENT" />
			</td>
			<td>
				<s:property value="#g.PRONAME" />
			</td>
			<td>
				<s:property value="#g.CREATE_DATE" />
			</td>
			<td align="center">
				<a href="javascript:fun_delete(<s:property value="#g.ID"/>)">删除</a>
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="6">
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"/>
		</td>
	</tr>
</table>
<script type="text/javascript">
		function fun_delete(pid){
			jConfirm('您确定删除该信息!', '确认框', function(r) {	
	    		if (r){
				   var url = '<%=path%>/net/proLog.do?action=delete&search.pid='+pid
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
							   window.location.href='<%=path%>/net/proLog.do';
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
