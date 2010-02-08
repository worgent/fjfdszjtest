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
<table width="100%" border="0" cellpadding="4" cellspacing="0">
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
			<strong>详细地址</strong>
		</td>
		<td>
			<strong>是否默认地址</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
		    <td>
		   	     <input type="radio" name="clientMsg" onclick="setValue('<s:property value="#g.ID" />','<s:property value="#g.PROVINCE" />', '<s:property value="#g.PROVINCENAME" />', '<s:property value="#g.CITY" />','<s:property value="#g.CITYNAME" />','<s:property value="#g.COUNTY" />', '<s:property value="#g.COUNTYNAME" />','<s:property value="#g.ADDRESS" />')"/>
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
			<td>
				<s:property value="#g.ISCHECKNAME" />
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="6">
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"/>
		</td>
	</tr>
	<input type="hidden" name="frmid" value=""/>
	<input type="hidden" name="frmprovince" value="">
	<input type="hidden" name="frmprovincename" value="">
	<input type="hidden" name="frmcity" value="">
	<input type="hidden" name="frmcityname" value="">
	<input type="hidden" name="frmcounty" value="">
	<input type="hidden" name="frmcountyname" value="">
	<input type="hidden" name="frmaddress" value="">
</table>
<script type="text/javascript">
		function setValue(frmid,frmprovince,frmprovincename,frmcity,frmcityname,frmcounty,frmcountyname,frmaddress){
			document.all.frmid.value = frmid;
			document.all.frmprovince.value = frmprovince;
			document.all.frmprovincename.value = frmprovincename;
			document.all.frmcity.value = frmcity;
			document.all.frmcityname.value = frmcityname;
			document.all.frmcounty.value = frmcounty;
			document.all.frmcountyname.value = frmcountyname;
			document.all.frmaddress.value = frmaddress;
		}
 </script>
