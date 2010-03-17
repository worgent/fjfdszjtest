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
			<strong>单位名称</strong>
		</td>
		<td>
			<strong>固话</strong>
		</td>
		<td>
			<strong>地址</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		    	<input type="radio" name="recMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.REC_NAME" />',  '<s:property value="#g.REC_UNIT" />','<s:property value="#g.REC_ADDRESS" />', '<s:property value="#g.REC_TEL" />', '<s:property value="#g.REC_POST" />', '<s:property value="#g.REC_ADDRESS" />')"/>
		    </td>
			<td>
				<s:property value="#g.REC_NAME" />
			</td>
			<td>
				<s:property value="#g.REC_UNIT" />
			</td>
			<td>
				<s:property value="#g.REC_TEL" />
			</td>
			<td>
				<s:property value="#g.REC_ADDRESS" />
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="6" align="center">
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"/>
		</td>
	</tr>
	<input type="hidden" name="frmid" value=""/>
	<input type="hidden" name="frmname" value="">
	<input type="hidden" name="frmunit" value="">
	<input type="hidden" name="frmaddress" value="">
	<input type="hidden" name="frmtel" value="">
	<input type="hidden" name="frmpost" value="">
</table>
<script type="text/javascript">
		function setValue(frmid,frmname,frmunit,frmaddress,frmtel,frmpost,frmaddress){
			document.all.frmid.value = frmid;
			document.all.frmname.value = frmname;
			document.all.frmunit.value = frmunit;
			document.all.frmtel.value = frmtel;			
			document.all.frmpost.value = frmpost;			
			document.all.frmaddress.value = frmaddress;		
		}
 </script>
