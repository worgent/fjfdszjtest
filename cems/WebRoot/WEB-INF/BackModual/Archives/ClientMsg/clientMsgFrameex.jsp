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
<head>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
</head>
<table class="table6" width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>选择</strong>
		</td>
		<td>
			<strong>联系人</strong>
		</td>
		<td>
			<strong>手机</strong>
		</td>
		<td>
			<strong>单位名称</strong>
		</td>
		<td>
			<strong>固话</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		    	<input type="radio" name="clientMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.NAME" />', '<s:property value="#g.MOBILE" />', '<s:property value="#g.UNIT" />', '<s:property value="#g.TEL" />')"/>
		    </td>
			<td>
				<s:property value="#g.NAME" />
			</td>
			<td>
				<s:property value="#g.MOBILE" />
			</td>
			<td>
				<s:property value="#g.UNIT" />
			</td>
			<td>
				<s:property value="#g.TEL" />
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="6">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"/>
			</center>
		</td>
	</tr>
	<input type="hidden" name="frmid" value=""/>
	<input type="hidden" name="frmname" value="">
	<input type="hidden" name="frmmobile" value="">
	<input type="hidden" name="frmunit" value="">
	<input type="hidden" name="frmtel" value="">
</table>
<script type="text/javascript">
		function setValue(frmid,frmname, frmmobile, frmunit,frmtel){
			document.all.frmid.value = frmid;
			document.all.frmname.value = frmname;
			document.all.frmmobile.value = frmmobile;
			document.all.frmunit.value = frmunit;
			document.all.frmtel.value = frmtel;
		}
 </script>
