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
			<strong>用户名</strong>
		</td>
		<td>
			<strong>单位名称</strong>
		</td>
		<td>
			<strong>姓名</strong>
		</td>
		<td>
			<strong>手机</strong>
		</td>
		<td>
			<strong>客户类型</strong>
		</td>
		<td>
			<strong>操作</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
			<td>
				<s:property value="#g.CODE" />
			</td>
			<td>
				<s:property value="#g.UNIT" />
			</td>
			<td>
				<s:property value="#g.NAME" />
			</td>
			<td>
				<s:property value="#g.MOBILE" />
			</td>
			<td>
				<s:property value="#g.CLIENTTYPENAME" />
			</td>
			<td colspan="2">
			    <s:if test="%{search.pbill_type==0}">
				<a href='<%=path%>/archives/user.do?action=updateauding&search.pid=<s:property value="#g.ID" />' target='_self'>审核</a>
				</s:if>
				<a href='<%=path%>/archives/user.do?action=edit&search.pid=<s:property value="#g.ID" />' target='_self'>编辑</a>
				<a href="javascript:fun_delete(<s:property value="#g.ID"/>)">删除</a>
				<a href="javascript:fun_setpwd(<s:property value="#g.ID"/>)">密码重置</a>
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
		function fun_delete(pid){
			jConfirm('您确定删除该信息!', 'Confirmation Dialog', function(r) {	
	    		if (r){
				   var url = '<%=path%>/archives/user.do?action=delete&search.pid='+pid
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
							   window.location.href='<%=path%>/archives/user.do';
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
		
		
	    function fun_setpwd(pid){
			jConfirm('您确定重置该密码吗?', 'Confirmation Dialog', function(r) {	
	    		if (r){
				   var url = '<%=path%>/archives/user.do?action=setpwd&search.pid='+pid
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
							   window.location.href='<%=path%>/archives/user.do';
							}
							else{
							    jAlert('重置密码失败!');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
 </script>
