<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" defer="defer"></script>
<script type="text/javascript" src="<%=basePath%>js/comm.js"></script>
<script type="text/javascript" src="<%=basePath%>js/usermark.js"></script>
<script type="text/javascript" src="js/wtree.js"></script>
<script type="text/javascript" src="/js/stdlib.js"></script>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />

<s:form action="user" method="post">
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td class="bgColor1">
				<table width="100%" border="0" cellpadding="5" cellspacing="1">
					<tr class="bgColor3">
						<td colspan="6">
							<strong class="font1">编辑</strong>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="15%" >
							员工名称:
						</td>
						<td class="bgColor2" width="35%" >
							<s:property value="search.userName"/>
						</td>
						<td class="bgColor4" width="15%" valign="top">
							时间间隔(分钟):
						</td>
						<td class="bgColor2" width="35%" valign="top">
							<s:textfield name="interval" id="interval" value="%{search.intervalVal}" cssClass="input2"
							onkeyup="value=value.replace(/[^\d]/g,'') " 
								size="20" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="15%" nowrap="nowrap">
							有效时间hh:mm(开始):
						</td>
						<td class="bgColor2" width="35%" >
							<input type="text" id="startTime" name="startTime"  value="<s:date name='search.startTime'format='HH:mm:00' />" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm:00'})" class="Wdate"/>
						</td> 
						<td class="bgColor4" width="15%" nowrap="nowrap">
							有效时间hh:mm(结束):
						</td>
						<td class="bgColor2" width="35%">
							<input type="text" id="endTime" name="endTime" value="<s:date name='search.endTime'format='HH:mm:00' />" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm:00'})" class="Wdate"/>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="15%" valign="top">
							上传设置:
						</td>
						<td class="bgColor2" width="35%" valign="top">
							 <s:checkboxlist list="uploadMap" listKey="key" listValue="value" name="uploadIDs" theme="ems"></s:checkboxlist>
						</td>
						<td class="bgColor4" width="15%" >
							&nbsp;
						</td>
						<td class="bgColor2" width="35%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="bgColor3" colspan="6">
								<input type="button" name="saveButton" value="保存"
									 class="btn" 
									onclick="userCheckWork('<%=path %>','<s:property id="" value="%{id}"/>','<s:property id="" value="%{page}"/>','<s:property id="" value="%{branchId}"/>');" />
							<input type="button" name="closeButton" value="关闭"
								 class="btn"  onclick="closeEditUser();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
</s:form>
