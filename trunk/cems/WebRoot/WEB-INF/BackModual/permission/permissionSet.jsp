<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>菜单配置</title>
		<link href="css/admin.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" type="text/javascript">			
			function onlyNumber(){
	                if(!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57)) 
	                        || (window.event.keyCode == 13) || (window.event.keyCode == 46) 
	                        || (window.event.keyCode == 45))){
	                        window.event.keyCode = 0 ;
	                }
	        }
		</script>
		
	</head>

	<body>
		<s:form action="permission" name="menuform">
			<table width="100%" border="0" cellpadding="5" cellspacing="0"
				class="table6">
				<tr>
					<td colspan="2">
						<strong> <s:if test="%{action=='addsave'}">
								新增菜单
							</s:if> <s:if test="%{action=='editsave'}">
								修改菜单
							</s:if> </strong>
					</td>
				</tr>
				<tr>
					<td>
						上级菜单
					</td>
					<td >
						<div id="parentPermissionName" style="color: red"></div>
					</td>
				</tr>
				<tr>
					<td>
						菜单名称
					</td>
					<td>
						<s:textfield id="permissionName" name="permissionName" cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						URI(如:test)
					</td>
					<td>
						<s:textfield id="resource" name="resource" required="true" cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						Action(如:list)
					</td>
					<td>
						<s:textfield id="actionName" name="actionName" cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						是否菜单
					</td>
					<td>
						<s:radio list="radioYesNoList" listKey="key" listValue="value"
						  id="isMenu" name="isMenu" ></s:radio>
					</td>
				</tr>
				<tr>
					<td>
						序号
					</td>
					<td>
						<s:textfield id="permissionOrder" onkeypress="return onlyNumber();" name="permissionOrder"  cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:if test="%{action=='addsave'}">
							<input type="button" name="saveGroup" value="保存" class="button1"
								onclick="addMenu();" />
						</s:if>
						<s:if test="%{action=='editsave'}">
							<input type="button" name="saveGroup" value="保存" class="button1"
								onclick="editMenu();" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>