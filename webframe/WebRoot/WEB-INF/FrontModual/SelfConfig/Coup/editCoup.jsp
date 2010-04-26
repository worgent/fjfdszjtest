<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<s:form action="coup">
	<s:hidden name="action"></s:hidden>
	
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td class="bgColor1">
				<table width="100%" border="0" cellpadding="5" cellspacing="1">
					<tr class="bgColor3">
						<td colspan="2">
							<strong class="font1"> <s:if test="%{action=='addsave'}">
									新增
								</s:if> <s:if test="%{action=='editdo'}">
									编辑
								</s:if> </strong>
						</td>
					</tr>
					<tr>
						<td width="20%" class="bgColor4">
							主题:
						</td>
						<td width="80%" class="bgColor2">
							<s:textfield id="coupTitle" name="coupTitle"
								cssClass="input2" size="40" maxlength="70"></s:textfield>
						</td>
					</tr>
					<tr>
						<td width="20%" class="bgColor4">
							内容:
						</td>
						<td width="80%" class="bgColor2">
							<s:textarea name="coupContent"  id="coupContent" cols="50" rows="10"></s:textarea>
						</td>
					</tr>
					<tr>
						<td class="bgColor3">
							&nbsp;
						</td>
						<td class="bgColor3">
							<s:if test="%{action=='addsave'}">
								<input type="button" name="saveButton"
									value="保存" class="button1"
									onclick="guideCoupAdd('<%=path %>');" />
							</s:if>
							<s:if test="%{action=='editdo'}">
								<input type="button" name="saveButton"
									value="提交修改" class="button1"
									onclick="guideCoupEdit('<%=path %>','<s:property id="" value="%{id}"/>','<s:property id="" value="%{page}"/>');" />
							</s:if>
							<input type="button" name="closeButton"
								value="关闭" class="button1"
								onclick="closeEditGuideCoup();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

