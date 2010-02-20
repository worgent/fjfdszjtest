
<!--
  main/top.jsp
  功能:顶级菜单显示
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@include file="common.jsp"%>
<%@page contentType="text/html; charset=GB2312"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:useBean id="frmTop" scope="request" type="YzSystem.JMain.FrmTop" class="YzSystem.JMain.FrmTop"></jsp:useBean>
<html>
<head>
<title><%=SystemTitle%></title>
<link rel="stylesheet" href="../css/mm.css" type="text/css">
<style type="text/css">
  <!--
    a:link {  font-family: "Verdana","Arial"; font-size: 12px; color: #FFFFFF; text-decoration: none}
    a:visited {  font-family: "Verdana","Arial"; font-size: 12px; color: #FFFFFF; text-decoration: none}
    a:hover {  font-family: "Verdana","Arial"; font-size: 12px; color: #FFFFFF; text-decoration: none}
  -->
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<f:view locale="topform">
  <h:form>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="76">
      <tr>
        <td height="34" bgcolor="#78A327" valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" height="34">
            <tr>
              <td valign="top">
                <a href="<%=SystemURL%>about.jsp" target="main">
                  <br>
                  <font size="4" color="#FFFFFF">
                    <strong>                      &nbsp;&nbsp;&nbsp;&nbsp;
<%=SystemTitle%>                    </strong>
                  </font>
                </a>
              </td>
              <td align="right" valign="bottom">
                <table border="0" cellspacing="0" cellpadding="0" height="26">
                  <tr>
                    <td width="300" align="center">
                      <font color="#FFFFCC">                        用户:
                        <h:outputLabel value="#{frmTop.userName}(#{frmTop.userId})">                        </h:outputLabel>
                        &nbsp;&nbsp;
                        角色:
                        <h:outputLabel value="#{frmTop.groupName}">                        </h:outputLabel>
                        部门:
                        <h:outputLabel value="#{frmTop.deptName}">                        </h:outputLabel>
                      </font>
                    </td>
                    <td width="100" align="center">                    </td>
                    <td width="28" align="center">
                      <a href="#" onClick="top.close();">
                        <img src="<%=SystemURL%>images/exit_top.gif" width="18" height="18" border="0" alt="退出">
                      </a>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td valign="top" height="24" align="left" background="<%=SystemURL%>images/wl_topbg.gif">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
            <tr>
              <td width="151" align="left" nowrap>
<script language=JavaScript>
 today=new Date();
 function initArray(){
   this.length=initArray.arguments.length
   for(var i=0;i<this.length;i++)
   this[i+1]=initArray.arguments[i]  }
   var d=new initArray(
     "星期日",
     "星期一",
     "星期二",
     "星期三",
     "星期四",
     "星期五",
     "星期六");
document.write(
	 "<img src=<%=SystemURL%>images/ebschedule_18.gif width=18 height=18 align=absmiddle hspace=3>",
     "<font style='font-size:11px;font-family:Verdana;color:#FFFFFF'>",
//     today.getYear(),"年",
     today.getMonth()+1,"月",
     today.getDate(),"日&nbsp;",
     d[today.getDay()+1],
     "</font>" );
</script>
              </td>
            <%out.println(frmTop.getMenu());            %>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </h:form>
</f:view>
<script language="JavaScript" type="text/JavaScript">
</script>
</body>
</html>
