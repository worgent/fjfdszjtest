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
		function fun_delete(SPACEID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
			   var url = '/space.do?action=delete&search.SPACEID='+SPACEID;
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
						   window.location.href='<%=path%>/space.do?action=list';    
						}
						else{
						    alert('删除失败!');  
						}
					}
				}catch(e){ 
					//alert(e);
				}	
	
			}
		}
 </script>
 <script type="text/javascript">
 function fun_edit(SPACEID){
 
  var url = '/space.do?action=edit&search.SPACEID='+SPACEID;
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
	
				}catch(e){
					//alert(e);
				}	
				
 }
 </script>
 
</head>
	<body>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right">
				  <a href="/space.do?action=add">添加</a>&nbsp;
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="750">
			<thead>
				<tr>
					<th>
						编号
					</th>
					<th>
						空间名称
					</th>
					<th>
						空间公告
					</th>
					
					<th>
					文章数	
					</th>
					<th>
					空间风格	
					</th>
					<th>
					匿名回复
					</th>
					<th>
					显示访客
					</th>
					<th>
					显示好友	
					</th>
					<th>
					显示公告
					</th>
					 <th>
					操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="space" value="%{pageList.objectList}">
					<tr>
						<td  class="bgColor3">
							<span class="font1"> <s:property value="#space.SPACEID" /> </span>
						</td>
						<td  class="bgColor4">
						
							<s:property value="#space.SPACENAME" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.SPACEBULLETIN" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.SHOWARLTICLENUM" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.SPACESTYLE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.ANONREVERSION" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.SHOWCALLER" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#space.SHOWFRIEND" />
						</td>
						<td  class="bgColor4">
							<s:property value="#space.SHOWBULLETIN" />
						</td>
						<td  class="bgColor4" align="left">
							<a href="/space.do?action=edit&search.SPACEID=<s:property value="#space.SPACEID"/>" >编辑</a> |
							<a href="javascript:fun_delete(<s:property value="#space.SPACEID" />)">删除</a>
						</td>
						
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="10">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>





