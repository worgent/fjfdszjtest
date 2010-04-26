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
</head>


	<script type="text/javascript">
		function fun_delete(GUIDENAME){
		    if (!confirm('您确定要添加此向导吗?')){
				return;
			}else{
			 
			    var url = '/businGud.do?action=businessGuideAdd&search.GUIDENAME='+GUIDENAME;
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
 
<body>
   <center>
	    <table><tr><td>当前未被商家指定为向导的列 </td></tr></table>
			<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					  向导用户
					</th>
					 <th>
					 操作
					 </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="guide" value="%{pageGuideAddList.objectList}">
					<tr>
						<td  class="bgColor3">
						<s:property value="#guide.USERNAME" />
						</td>
						<td  class="bgColor4" align="left">
							<a href="javascript:fun_delete(<s:property value="#guide.USERNAME"/>);">加 入</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageGuideAddList.pages}" />
				</td>
			</tr>
			</table>
		
		    <table>
		    <tr><td>
		    备注:当前未被此商家列为指定向导的用户,可选择加入.</td></tr></table>
		
		
		
		<center>
</body>
</html>