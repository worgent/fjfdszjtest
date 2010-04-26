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
		function fun_delete(Memberid){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
		
			   
			   var url = '/user.do?action=deleteMember&search.Memberid='+Memberid;
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
						
						   window.location.href='<%=path%>/user.do?action=MemberPageList';  
						  
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
				 &nbsp;
				</td>
			</tr>
		</table>
		<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					会员名称
					</th>
					<th>
					会员类型
					</th>
					<th>
				     商家分类
					</th>
					<th>
					商家名称
					</th>
					<th>
					地址
					</th>
					<th>
					法人代表
					</th>
					<th>
					联络方式
					</th>
					<th>
					位置	
					</th>
					<th>
					场景图照
					</th>
					<th> 
					简介
					</th>
					
					<th>
				     实力
					</th>
					<th>
					口碑
					</th>
					<th>
					人气
					</th>
					<th>
					财力
					</th>
				      <th>
					  是否审核
					  </th>
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="menb" value="%{pageMemberList.objectList}">
					<tr>
						<td  class="bgColor3">
							<span class="font1"><s:property value="#menb.MEMBERNAME" /> </span>
						</td>
						<td  class="bgColor4">
						    <s:if test="#menb.MEMBERTYPE==1">商家会员</s:if>
						    <s:if test="#menb.MEMBERTYPE==2">普通会员</s:if>
						    <s:if test="#menb.MEMBERTYPE==3">向导会员</s:if>
						    <s:if test="#menb.MEMBERTYPE==4">领主会员</s:if>
							
						</td>
						<td  class="bgColor4">
						    <s:if test="#menb.MERCHANTCATEGORIES!=0">&nbsp;</s:if>
						    <s:if test="#menb.MERCHANTCATEGORIES==1">门店经营</s:if>
						    <s:if test="#menb.MERCHANTCATEGORIES==2">家政服务</s:if>	
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.MERCHANTNAME" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.ADDRESS" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.LEGALREPRESENTATIVE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.CONTACT" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#menb.LOCATION" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.PICTURE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.INTRODUCTION" />
						</td>
						<td  class="bgColor3">
							<span class="font1"> <s:property value="#menb.STRENGTH" /> </span>
						</td>
						<td  class="bgColor4">
						
							<s:property value="#menb.PRAISE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.POPULARITY" />
						</td>
						<td  class="bgColor4">
							<s:property value="#menb.FINANCIAL" />
						</td>
						
						<td  class="bgColor4">
						     <s:if test="#menb.ISAUDIT==0">未审核</s:if>
						     <s:if test="#menb.ISAUDIT==1">已审核</s:if>
						</td>
						
						
						<td  class="bgColor4" align="left">
						  	<a href="/user.do?action=editOnlyRecord&search.memberid=<s:property value="#menb.MEMBERID"/>&search.pmembername=<s:property value="#menb.MEMBERNAME"/>" >编辑</a> |
							<a href="javascript:fun_delete(<s:property value="#menb.MEMBERID"/>);">删除</a>
						</td>
						
						
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageMemberList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>





