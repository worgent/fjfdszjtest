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
		function fun_delete(PRODUCTID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
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
		function fun_delete(PRODUCTID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
			     var url = '/user.do?action=productDelete&search.PRODUCTID='+PRODUCTID;
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
						   window.location.href='<%=path%>/user.do?action=productMessage';    
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
					商品信息
				</td>
			</tr>
		</table>
		<table  border="0" align="center" width="90%" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right" width="80%">
				 &nbsp;
				</td>
				<td  align="right">
             <a href="<%=request.getContextPath() %>/user.do?action=productAdd" >
                 添 加</a>
				</td>
				
			</tr>
		</table>
		
		
		
		
		<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					商品分类
					</th>
					<th>
				    商品名称
					</th>
					<th>
				     品牌
					</th>
					<th>
					商品介绍
					</th>
					<th>
				    图片
					</th>
					<th>
					 产地
					</th>
					<th>
					供应商
					</th>
					<th>
					浏览数
					</th>
					<th>
				    向导锦囊
					</th>
					<th> 
					其它商家产品
					</th>
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="prod" value="%{pageProductList.objectList}">
					<tr>
						<td  class="bgColor3">
						    <s:if test="#prod.PRODUCTSORT==1">新</s:if>
						    <s:if test="#prod.PRODUCTSORT==2">特</s:if>
						    <s:if test="#prod.PRODUCTSORT==3">优</s:if>
						</td>
						<td  class="bgColor4">
						<s:property value="#prod.PRODUCTNAME" />
						</td>
						<td  class="bgColor4">
						 	<s:property value="#prod.PRODUCTBRAND" />
						</td>
						<td  class="bgColor4">
						
							<s:property value="#prod.INTRODUCT" />
						</td>
						<td  class="bgColor4">
							<s:property value="#prod.PICTURE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#prod.ORIGIN" />
						</td>
						<td  class="bgColor4">
							<s:property value="#prod.SUPPLIER" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#prod.VIEWS" />
						</td>
						<td  class="bgColor4">
							<s:property value="#prod.GUIDE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#prod.OTHERPRODUCT" />
						</td>
						
						<td  class="bgColor4" align="left">
						  	<a href="/user.do?action=productModify&search.PRODUCTID=<s:property value="#prod.PRODUCTID"/>" >编辑</a> |
							<a href="javascript:fun_delete(<s:property value="#prod.PRODUCTID"/>);">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageProductList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>





