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
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="<%=path%>/css/images/tab_03.gif" width="15" height="30" /></td>
        <td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" height="16" /> <span class="STYLE4">申报事项</span></td>
        <td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14" height="30" /></td>
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
            <td width="24%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">主题</div></td>
            <td width="8%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">申报类别</div></td>
            <td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">任务级别</div></td>
            <td width="14%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">登记时间</div></td>
            <td width="24%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">登记人</div></td>
            <td width="7%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">任务状态</div></td>
          </tr>
          
          <s:iterator id="g" value="%{pageList.objectList}" >
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
            <input class="STYLE2" type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.id"/>'/>
		    <a href='<%=path%>/work/tdeclare.do?action=view&search.pid=<s:property value="#g.id"/>'  target='_self'> <s:property value="#g.id"/></A>
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.title" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.declaretypename" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.missiongradename" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.makedatetime" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.maker" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.state" /></div></td>
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