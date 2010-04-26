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
		function fun_delete(ID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			    var url = '/user.do?action=favourableDelete&search.ID='+ID;
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false;
					oXMLDom.load(url);
					
				}catch(e){ 
					//alert(e);
				}	
				
				
				
			}
		}
 </script>
 
 
 <script type="text/javascript">
		function fun_delete(ID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
			
			   
			   var url = '/user.do?action=favourableDelete&search.ID='+ID;
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false;
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
						    
					 
						window.location.href='<%=path%>/user.do?action=favourableList';
	    
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
 
</head>
	<body>
		<table  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					优惠券管理
				</td>
			</tr>
		</table>
		<table  border="0" align="center" width="90%" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right" width="80%">
				 &nbsp;
				</td>
				<td  align="right">
                <a href="<%=request.getContextPath() %>/user.do?action=favourableAdd" >
                   添 加
                 </a>
				</td>
				
			</tr>
		</table>

		<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					编号
					</th>
					<th>
				     名称
					</th>
					<th>
				     使用情况
					</th>
					<th>
					介绍
					</th>
					<th>
			         数量
					</th>
					<th>
					领取数量
					</th>
					<th>
					使用部落
					</th>
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="favo" value="%{pageFavourableList.objectList}">
					<tr>
						<td  class="bgColor3">
						  <s:property value="#favo.FAVOURABLENO" />
						</td>
						<td  class="bgColor4">
						<s:property value="#favo.FAVOURABLENAME" />
						</td>
						<td  class="bgColor4">
						 	<s:property value="#favo.USEINSTANCE" />
						</td>
						<td  class="bgColor4">
						
							<s:property value="#favo.INTRODUCE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#favo.COUNT" />
						</td>
						<td  class="bgColor4">
							<s:property value="#favo.COUNTRECEVE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#favo.USERTRIBAL" />
						</td>
						<td  class="bgColor4" align="left">
						  	<a href="/user.do?action=favourableModify&search.ID=<s:property value="#favo.ID"/>" >编辑</a> |
							<a href="javascript:fun_delete(<s:property value="#favo.ID"/>);">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageFavourableList.pages}" />
				</td>
			</tr>
		</table>
	
	
	
	
	</body>
</html>





