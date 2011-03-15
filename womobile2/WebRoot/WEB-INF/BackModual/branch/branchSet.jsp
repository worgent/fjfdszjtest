<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>机构配置</title>
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
		<s:form action="branch" name="branchform">
			<table width="100%" border="0" cellpadding="5" cellspacing="0"
				class="table6">
				<tr>
					<td colspan="2">
						<strong> <s:if test="%{action=='addsave'}">
								新增机构
							</s:if> <s:if test="%{action=='editsave'}">
								修改机构
							</s:if> </strong>
					</td>
				</tr>
				<tr>
					<td>
						上级机构
					</td>
					<td >
						<div id="parentBranchName" style="color: red"></div>
					</td>
				</tr>
				<tr>
					<td>
						机构名称
					</td>
					<td>
						<s:textfield id="branchName" name="branchName" cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				
				<tr>
					<td>
						序号
					</td>
					<td>
						<s:textfield id="branchOrder" onkeypress="return onlyNumber();" name="branchOrder"  cssClass="input2" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:if test="%{action=='addsave'}">
							<input type="button" name="saveGroup" value="保存"  class="btn" 
								onclick="addBranch();" />
						</s:if>
						<s:if test="%{action=='editsave'}">
							<input type="button" name="saveGroup" value="保存"  class="btn" 
								onclick="editMenu();" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>