<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>管理员信息修改</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
    <script type="text/javascript">
        function fillValue(){
             if('${infoMap}'==null||'${infoMap}'==''){
                document.all.sex[0].checked=true;
                document.all.ifUse[0].checked=true;
             }
        }
    
    </script>
</head>

<body onload="fillValue()" background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do"
		method="post">
		<center>
			<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						管理员信息修改
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						管理员ID
					</td>
					<td class="main" align="left">
						<input type="text" name="adminId" value="${infoMap.adminId }" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						新密码
					</td>
					<td class="main" align="left">
						<input type="password" name="new_password1" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						重输新密码
					</td>
					<td class="main" align="left">
						<input type="password" name="new_password2" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						真实姓名
					</td>
					<td class="main" align="left">
						<input type="text" name="realName"  value="${infoMap.realName }" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						Email
					</td>
					<td class="main" align="left" >
						<input type="text" name="email"  value="${infoMap.email }" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						性别
					</td>
					<td class="main" align="left">
						<input type="radio" name="sex" ${infoMap.sex==1?'checked':'' } value="1"   />
						男 &nbsp;&nbsp;&nbsp;
						<input type="radio" name="sex" ${infoMap.sex==0?'checked':'' } value="0" />
						女 &nbsp;
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						是否启用
					</td>
					<td class="main" align="left">
						<input type="radio" name="ifUse" ${infoMap.ifUse==1?'checked':'' } value="1" />
						启用 &nbsp;
						<input type="radio" name="ifUse" ${infoMap.ifUse==0?'checked':'' } value="0" />
						不启用 &nbsp;
					</td>
				</tr>
				<tr>
				    <td class="main" align="right">
				        用户角色
				    </td>
				    <td class="main" align="left">
	    				<select name="roleId">
    					<logic:present name="roleList">
    						<logic:iterate id="roleValue" name="roleList">
    						<logic:equal name="roleValue" property="roleId" value="${infoMap.roleId }">
    							<option selected="true" value="${roleValue.roleId}" >${roleValue.roleName}</option>
    						</logic:equal>
     						<logic:notEqual name="roleValue" property="roleId" value="${infoMap.roleId }">
    							<option value="${roleValue.roleId}">${roleValue.roleName}</option>
    						</logic:notEqual>   						
    					    </logic:iterate>
    					</logic:present>
						</select>
						</td>
				</tr>
				<tr align="center">
					<td colspan="2" class="main">
					    <input type="hidden" name="status" value="updateAdmin" />
						<input type="submit" value="修改" class="button"/>
						<input type="button" value="返回" class="button" onclick="javascript:self.history.back(); " />
					</td>
				</tr>

			</table>
		</center>
	</form>
</body>
</html:html>
<logic:present name="xgResult">
   <script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
