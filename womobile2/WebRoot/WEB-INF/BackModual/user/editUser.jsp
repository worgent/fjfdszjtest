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
<script type="text/javascript" src="<%=basePath%>js/usermark.js"></script>
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
						<td class="bgColor4" width="10%" >
							组织机构:
						</td>
						<td class="bgColor2" width="24%" >
							<s:select id="branchId2" name="branchId2" listKey="key" listValue="value" value="%{search.branchId}" list="branchValues"></s:select>
						</td>
						<td class="bgColor4" width="10%" >
							状态:
						</td>
						<td class="bgColor2" width="23%" >
							<select name="state" id="state" >
								<option value="1" >启用</option>
								<option value="0">停用</option>
							</select>
						</td>
						<td class="bgColor4" width="10%" >
							&nbsp;
						</td>
						<td class="bgColor2" width="23%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							用户名:
						</td>
						<td class="bgColor2" width="24%" >
							<s:textfield name="userName" id="userName" value="%{search.userName}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							密码:
						</td>
						<td class="bgColor2" width="23%" >
							<s:password id="passwd" name="passwd" cssClass="input2"
								size="20" maxlength="70" />
						</td>
						<td class="bgColor4" width="10%" >
							确认密码:
						</td>
						<td class="bgColor2" width="23%">
							<s:password id="repasswd" name="repasswd" cssClass="input2"
								size="20" maxlength="70" />
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							用户编号:
						</td>
						<td class="bgColor2" width="24%" >
							<s:textfield id="userId" name="userId" value="%{search.userId}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							用户组:
						</td>
						<td class="bgColor2" width="24%" >
							<s:select id="userGroup" name="userGroup" listKey="key" listValue="value" value="%{search.groupId}" list="groupValues"></s:select>
						</td>
						<td class="bgColor4" width="10%" >
							真实姓名:
						</td>
						<td class="bgColor2" width="23%" >
							<s:textfield id="realName" name="realName" value="%{search.realName}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							职称:
						</td>
						<td class="bgColor2" width="23%" >
							<s:select listKey="key" listValue="value" list="zhichengValues" value="%{search.zhicheng}" id="zhicheng" name="zhicheng"></s:select>
						</td>
						<td class="bgColor4" width="10%" >
							职务:
						</td>
						<td class="bgColor2" width="23%">
							<s:select listKey="key" listValue="value" list="dutyValues" value="%{search.duty}" id="duty" name="duty"></s:select>
						</td>
						<td class="bgColor4" width="10%" >
							性别:
						</td>
						<td class="bgColor2" width="23%">
							<s:select list="sexMap" id="sex" name="sex" value="%{search.sex}"></s:select>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							手机号码:
						</td>
						<td class="bgColor2" width="24%" >
							<s:textfield id="mobilephone" name="mobilephone" value="%{search.mobilephone}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							工作电话:
						</td>
						<td class="bgColor2" width="23%" >
							<s:textfield id="workphone"  name="workphone" value="%{search.workphone}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							邮箱:
						</td>
						<td class="bgColor2" width="23%">
							<s:textfield id="email"  name="email" value="%{search.email}" cssClass="input2"
								size="20" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							生日:
						</td>
						<td class="bgColor2" width="23%">
							<input id="birthday" name="birthday" class="Wdate" value="<s:date name='search.birthday'format='yyyy-MM-dd' />" type="text" onClick="WdatePicker()" />
						</td>
						<td class="bgColor4" width="10%" >
							通讯地址:
						</td>
						<td class="bgColor2" width="24%" >
							<s:textfield id="address" name="address" cssClass="input2" value="%{search.address}"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							邮编:
						</td>
						<td class="bgColor2" width="23%" >
							<s:textfield id="postId" name="postId" cssClass="input2" value="%{search.postId}"
								size="20" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="bgColor4" width="10%" >
							开户行:
						</td>
						<td class="bgColor2" width="24%" >
							<s:textfield id="accountBank" name="accountBank" cssClass="input2" value="%{search.accountBank}"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							银行账号:
						</td>
						<td class="bgColor2" width="23%" >
							<s:textfield id="accountId" name="accountId" cssClass="input2" value="%{search.accountId}"
								size="20" maxlength="70"></s:textfield>
						</td>
						<td class="bgColor4" width="10%" >
							备注:
						</td>
						<td class="bgColor2" width="23%">
							<s:textfield id="remark"  name="remark" cssClass="input2" value="%{search.remark}"
								size="20" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="bgColor3" colspan="6">
								<input type="button" name="saveButton" value="保存"
									 class="btn" 
									onclick="userEdit('<%=path %>','<s:property id="" value="%{id}"/>','<s:property id="" value="%{page}"/>','<s:property value="branchId"/>');" />
							<input type="button" name="closeButton" value="关闭"
								 class="btn"  onclick="closeEditUser();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
</s:form>
