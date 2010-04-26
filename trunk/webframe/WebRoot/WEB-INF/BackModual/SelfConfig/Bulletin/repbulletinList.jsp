<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<script type="text/javascript">
		function fun_delete(pid){
		jConfirm('您确定删除该信息!', 'Confirmation Dialog', function(r) {	
    		if (r){
			   var url = '<%=path%>/selfconfig/bulletin.do?action=delete&search.pid='+pid
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
						   window.location.href='<%=path%>/selfconfig/bulletin.do?action=list';
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
	</head>
	<body>
			<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="4">
						<s:actionerror theme="webframe0" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<s:actionmessage theme="webframe0" />
					</td>
				</tr>
			<tr>
				 <s:label value="明细" ></s:label>
			</tr>
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1"/>
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="750">
			<thead>
				<tr>
					<th>
						参于者
					</th>
					<th>
						电话
					</th>
					<th>
						是否确认
					</th>
					<th>
						参于时间
					</th>					
				</tr>
			</thead>
			<tbody>
				<s:iterator id="repbulletin" value="%{pageList.objectList}">
					<tr>
						<td class="bgColor3">
							 <s:property value="#repbulletin.USERNAME" />
						</td>
						<td class="bgColor4">
							<s:property value="#repbulletin.MOBILE" />
						</td>
						<td class="bgColor4">
							<s:property value="#repbulletin.ISREALIZE" />
						</td>
						<td class="bgColor4">
							<s:property value="#repbulletin.CREATETIME" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="8">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>