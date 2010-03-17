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
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>联系人</strong>
		</td>
		<td>
			<strong>下单时间</strong>
		</td>
		<td>
			<strong>手机</strong>
		</td>
		<td>
			<strong>取件地址</strong>
		</td>
		<td>
			<strong>邮件种类</strong>
		</td>
		<td>
			<strong>客户要求</strong>
		</td>
		<td>
			<strong>单据状态</strong>
		</td>
		<td align="center">
			<strong>操作</strong>
		</td>
		<!-- 
		<td>
			<a href='<%=path%>/net/order.do?action=new' target='_self'>增加</a>
			<a href='javascript:fun_submit("clientmodify",<s:property value="#g.ID"/>)' target='_self'>修改</a>
		</td>
		 -->
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
			<td>
			    <s:label value="%{#g.NAME}" title="%{#g.PLORDERID}"></s:label>
			</td>
			<td>
				<s:property value="#g.CREATE_DATE" />
			</td>
			<td>
				<s:property value="#g.MOBILE" />
			</td>
			<td>
				<s:property value="#g.ADDRESS" />
			</td>
			<td>
				<s:property value="#g.MAILTYPENAME" />
			</td>
			<td>
				<s:property value="#g.CLIENTREMARK" />
			</td>
			<td>
				<s:property value="#g.ORDERINGSTATE" />
			</td>
			<td align="left">	
			<a href='<%=path%>/net/order.do?action=view&search.pid=<s:property value="#g.ID" />'  target='_self'>查看</a>			
			<s:if test="%{#g.ORDERINGVALUE.substring(0,1)==1}">
				<a href='<%=path%>/net/order.do?action=edit&search.pid=<s:property value="#g.ID" />' target='_self'>修改</a>
			</s:if>
			<s:if test="%{#g.ORDERINGVALUE.substring(1,2)==1}">
			    <a href='<%=path%>/net/order.do?action=perclientmodify&search.pid=<s:property value="#g.ID" />' target='_self'>修改</a>
			</s:if>
			<s:if test="%{#g.ORDERINGVALUE.substring(2,3)==1}">
				<a href='javascript:fun_submitex("clientadd",<s:property value="#g.ID"/>)'>寄件</a>
			</s:if>	
			<s:if test="%{#g.ORDERINGVALUE.substring(3,4)==1}">
				<a href='javascript:fun_submit("clientcancel",<s:property value="#g.ID"/>)'>撤单</a>
			</s:if>	
			<s:if test="%{#g.ORDERINGVALUE.substring(4,5)==1}">
				<a href='javascript:fun_submit("clienthurry",<s:property value="#g.ID"/>)'>催揽</a>
			</s:if>	
			<!-- 
			<s:if test="%{#g.ORDERINGVALUE.substring(5,6)==1}">
				<a href='<%=path%>/net/order.do?action=print&search.pid=<s:property value="#g.ID" />' target='_self'>邮件管理</a>
			</s:if>	
			 -->
			<s:if test="%{#g.ORDERINGVALUE.substring(6,7)==1}">
				<a href="javascript:fun_delete(<s:property value="#g.ID"/>)">删除</a>
			</s:if>	
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="8">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}" javaScript="loadDefaultList"/>
			</center>
		</td>
	</tr>
</table>
<script type="text/javascript">
		function fun_delete(pid){
			jConfirm('您确定删除该信息!', '确认框', function(r) {
	    		if (r){
					var url = '<%=path%>/net/order.do?action=delete&search.pid='+pid
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
							   window.location.href='<%=path%>/net/order.do';
							    //});
							}
							else{
							    jAlert('删除失败!','提示');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
		//2010-01-05用户提交信息到派揽系统
		function fun_submit(action,pid){
			//jConfirm('您确定执行该操作!', '确认框', function(r) {	
			jPrompt('客户要求:', ' ', '提示框', function(r) {
	    		if (r){
	    		var url = '<%=path%>/net/order.do?action='+action+'&search.pid='+pid+'&search.pclientremark='+r
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
							var remark=rowSet.item(0).selectSingleNode("remark").text;
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    // jAlert("操作成功!",fuction(r){
							   window.location.href='<%=path%>/net/order.do';
							   // });
							}
							else{
							    jAlert(remark);  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示异常");
					}	
				}
			});
		}
        //2010-01-05用户提交信息到派揽系统
		function fun_submitex(action,pid){
			jConfirm('您确定执行该操作!', '确认框', function(r) {	
			//jPrompt('客户要求:', '', '提示框', function(r) {
	    		if (r){
	    		    var url = '<%=path%>/net/order.do?action='+action+'&search.pid='+pid+'&search.pclientremark=';
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
							var remark=rowSet.item(0).selectSingleNode("remark").text;
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    // jAlert("操作成功!",fuction(r){
							   window.location.href='<%=path%>/net/order.do';
							   // });
							}
							else{
							   jAlert(remark);  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示异常");
					}	
				}
			});
		}
 </script>
