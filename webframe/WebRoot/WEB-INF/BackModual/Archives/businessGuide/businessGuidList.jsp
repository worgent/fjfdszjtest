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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户选择向导并进入向导空间</title>


<script type="text/javascript">
		function fun_delete(BUSINESSGUIDEID){
		    if (!confirm('您确定删除该向导吗？')){
				return;
			}else{
			
			
			   
			   var url = '/businGud.do?action=delete&search.BUSINESSGUIDEID='+BUSINESSGUIDEID;
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
						     window.location.href='<%=path%>/businGud.do?action=guideList';
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
   <center>
	    <table><tr><td>当前商家的向导列表 </td></tr></table>
		<table  border="0" align="center" width="90%" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right" width="80%">
				 &nbsp;
				</td>
				<td  align="right">
                <a href="<%=request.getContextPath() %>/businGud.do?action=guideListAdd" >
                   添加向导
                 </a>
				</td>
				
			</tr>
		</table>
			<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					  向导用户
					</th>
					<th>
					  创建时间
					</th>
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="guide" value="%{pageGuideList.objectList}">
					<tr>
						<td  class="bgColor3">
						<s:property value="#guide.GUIDEUSER" />
						</td>
						<td  class="bgColor4">
						<s:property value="#guide.RELEASETIME" />
						</td>
						<td  class="bgColor4" align="left">
							<a href="javascript:fun_delete(<s:property value="#guide.BUSINESSGUIDEID"/>);">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageGuideList.pages}" />
				</td>
			</tr>
			</table>
		
		    <table><tr><td> 备注:当前商家已经指定的向导 </td></tr></table>
		
		
		
		<center>
</body>
</html>