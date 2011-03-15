<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15" style="vertical-align: middle;">
						<img src="<%=path%>/css/images/tab_03.gif" width="15" />
					</td>
					<td background="<%=path%>/css/images/tab_05.gif">
						<img src="<%=path%>/css/images/311.gif" width="16" />
						<span class="STYLE4">用户管理</span>
					</td>
					<td width="14">
						<img src="<%=path%>/css/images/tab_07.gif" width="14" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	 <tr>
    	<td colspan="5">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
		        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
		          <tr>
		            <td height="18" width="140" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户名</div></td>
		            <td width="60" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户组</div></td>
		            <td width="60" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">状态</div></td>
		            <td height="18" width="140" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">组织机构</div></td>
		            <td width="140" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作 [
			<strong><a href="javascript:;"
				onclick='loadUserAddPage("<%=path %>","<s:property value='branchId'/>");'>新增</a>
			</strong>]</div></td>
		          </tr>
		          <s:iterator value="%{pageList.objectList}" id="user">
					<tr>
						<td height="18" bgcolor="#FFFFFF" class="STYLE2">
							<div align="center" class="STYLE2 STYLE1"><s:property value="#user.userName" /></div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><s:property value="#user.groupName" /></div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<s:if test="#user.state==1">启用</s:if>
							<s:if test="#user.state==0">停用</s:if>
							</div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<s:property value="#user.branchName" />
							</div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<a href="javascript:;"
								onclick="loadCheckWorkEditPage('<s:property value="#user.id"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>','<s:property value="branchId"/>');">考勤设置</a>|
							<a href="javascript:;"
								onclick="loadUserEditPage('<s:property value="#user.id"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>','<s:property value="branchId"/>');">修改</a>|
							<a href="javascript:;"
								onclick="delUserId('<s:property value="#user.id"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>','<s:property value="branchId"/>');">删除</a>
							</div>
						</td>
					</tr>
				</s:iterator>		          
				</table>
				</td>
		        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
		      </tr>
    		</table>
    	</td>
 	</tr>
	
	<tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              <s:hidden name="branchId" id="branchId"></s:hidden>
			分页:<webframe:pages value="%{pageList.pages}" javaScript="loadUserList" />	
            </span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
