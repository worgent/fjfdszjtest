<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15" style="vertical-align: middle;">
						<img src="<%=path%>/css/images/tab_03.gif" width="15" />
					</td>
					<td background="<%=path%>/css/images/tab_05.gif">
						<img src="<%=path%>/css/images/311.gif" width="16" />
						<span class="STYLE4">用户组管理</span>
					</td>
					<td width="14">
						<img src="<%=path%>/css/images/tab_07.gif" width="14" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	 <tr>
    	<td>
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
		        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
		          <tr>
		            <td height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户组名</div></td>
		            <td width="160" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">描述</div></td>
		            <td width="240" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作 [ <input type="button" name="newGroup" value="新增"  class="btn" onclick="newGroup();"/> ]</div></td>
		          </tr>
  <s:iterator id="group1" value="%{groupList}">
  <tr>
    <td height="18" bgcolor="#FFFFFF" class="STYLE2">
							<div align="center" class="STYLE2 STYLE1"><s:property value="#group1.groupName"/></div></td>
    <td height="18" bgcolor="#FFFFFF" class="STYLE2">
							<div align="center" class="STYLE2 STYLE1"><s:property value="#group1.groupDesc"/></div></td>
    <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=path%>/css/images/037.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="javascript:;" onclick="editGroup('<s:property value="#group1.groupId"/>');">修改</a><span class="STYLE1">]</span>
      <img src="<%=path%>/css/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="javascript:;" onclick="delGroup('<s:property value="#group1.groupId"/>');">删除</a><span class="STYLE1">]</span> </div>
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
      <td height="29">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="right"><span class="STYLE1">
            </span></div>
            </td>
            <td width="100" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
       </table>
      </td>
     </tr>
</table>