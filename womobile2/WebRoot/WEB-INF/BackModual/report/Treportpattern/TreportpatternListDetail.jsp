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
<head>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="vertical-align:middle;"><img src="<%=path%>/css/images/tab_03.gif" width="15" /></td>
        <td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" /> <span class="STYLE4">报表管理</span></td>
        <td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14"  /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
          <tr>
<td width="6%" height="26" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><a href="javascript:chk_all();">选择</a></div></td>
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">模板</div></td>
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">报表名称</div></td>
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">是否启动</div></td>
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">备注</div></td>	 
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">创建人</div></td>
<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">创建时间</div></td>
	</tr>          
          <s:iterator id="g" value="%{pageList.objectList}" >
		<tr>
		    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
		    <input class="STYLE2"  type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.id"/>'/>
		    <a href='<%=path%>/report/treportpattern.do?action=view&search.pid=<s:property value="#g.id"/>'  target='_self'> <s:property value="#g.id"/></A>
		   </div> </td>			 
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.PatternId" /> </div></td>			
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.ReportName" /> </div></td>
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.State" /> </div></td>
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.Remark" /> </div></td>
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.Maker" /> </div></td>
 <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.MakeDateTime" /> </div></td>
		</tr>
		 </s:iterator>
        </table></td>
        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              分页:<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
            </span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>

