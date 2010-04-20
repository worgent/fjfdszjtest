<%@page contentType="text/html; charset=UTF-8"%>
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
			<strong>省</strong>
		</td>
		<td>
			<strong>市</strong>
		</td>
		<td>
			<strong>县区</strong>
		</td>
		<td>
			<strong>地址</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		    	<input type="radio" name="recMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.PROVINCENAME" />', '<s:property value="#g.CITYNAME" />', '<s:property value="#g.COUNTYNAME" />','<s:property value="#g.ADDRESS" />')"/>
		    </td>
			<td>
				<s:property value="#g.PROVINCENAME" />
			</td>
			<td>
				<s:property value="#g.CITYNAME" />
			</td>
			<td>
				<s:property value="#g.COUNTYNAME" />
			</td>
			<td>
				<s:property value="#g.ADDRESS" />
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
	<input type="hidden" name="frmprovincename" value="">
	<input type="hidden" name="frmciytname" value="">
	<input type="hidden" name="frmcountyname" value="">
	<input type="hidden" name="frmaddress" value="">
</table>
<script type="text/javascript">
		function setValue(frmid,frmprovincename, frmciytname, frmcountyname,frmaddress){
			document.all.frmid.value = frmid;
			document.all.frmprovincename.value = frmprovincename;
			document.all.frmciytname.value = frmciytname;
			document.all.frmcountyname.value = frmcountyname;		
			document.all.frmaddress.value = frmaddress;		
		}
 </script>
