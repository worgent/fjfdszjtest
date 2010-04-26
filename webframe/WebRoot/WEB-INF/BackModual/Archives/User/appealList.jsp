<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
		$(document).ready(function(){ 
			$("#myTable").tablesorter({ 
				headers: { 
					1: {sorter: false }
				}   
			}); 
		}); 
		</script>
		

	

<script type="text/javascript">
		function fun_delete(APPEALID){
		jConfirm('您确定删除该优惠劵信息!', 'Confirmation Dialog', function(r) {	
    		if (r){
			  var url = '/user.do?action=deleteAppeal&search.APPEALID='+APPEALID;
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
						   window.location.href='<%=path%>/user.do?action=appealList';
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
		<table  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<table  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right">
				 <strong> <font color="#ff0000">商家申诉信息管理</font></strong>
				</td><td valign="top"><br></td>
			</tr>
		</table>
		<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					商家名称
					</th>
					<th>
					申诉标题
					</th>
					<th>
				    申诉内容
					</th>
					<th>
					申诉时间
					</th>
					<th>
					商家回复
					</th>
					<th>
					回复时间
					</th>
					
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="Appe" value="%{searchAppealList.objectList}">
					<tr>
						<td  class="bgColor3">
							<span class="font1"><s:property value="#Appe.APPEALMERCHANT" /> </span>
						</td>
						<td  class="bgColor4">
						   	<s:property value="#Appe.APPEALTITLE" />
						</td>
						<td  class="bgColor4">
						   	 	<s:property value="#Appe.APPEALCONTENT" />...
						</td>
						<td  class="bgColor4">
							<s:property value="#Appe.APPEALTIME" />
						</td>
						<td  class="bgColor4">
						
							 <s:property value="#Appe.REPLYCONTENT" />...
							
							
							
						</td>
						<td  class="bgColor4">
							<s:property value="#Appe.REPLYTIME" />
						</td>
					
						<td  class="bgColor4" align="left">
						  	<a href="/user.do?action=editOnlyAppeal&search.APPEALID=<s:property value="#Appe.APPEALID"/>" >回复</a> |
							<a href="javascript:fun_delete(<s:property value="#Appe.APPEALID"/>);">删除</a>
						</td>
						
						
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{searchAppealList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>





