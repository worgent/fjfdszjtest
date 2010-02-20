<%@include file="../main/common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.J_System.*,YzSystem.JMain.*" errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%FrmGroupUserManage frmGroupUserManage = (FrmGroupUserManage) UtilWebTools.getValueBinding("frmGroupUserManage");%>
<html>
<head>
<title>修改员工密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/mm.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javascript:theForm.oldpwd.focus();">
<f:view>
<form name="theForm" method="post" action="">
  <table width="100%" border="0" cellspacing="10" cellpadding="0">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bborder">
        <%out.println(frmGroupUserManage.genHtmlPos());        %>
          <table width="100%" border="0" cellspacing="1" cellpadding="0" class="bborder">
            <tr>
              <td colspan="8" class="btd">
                <table border="0" cellspacing="1" cellpadding="3" width="100%">
                  <tr>
                    <td colspan="2">&nbsp;</td>
                  </tr>
                  <tr>
                    <td align="right" width="38%">原密码</td>
                    <td>
                      <input type="password" name="oldpwd" size="20" maxlength="20">
                    </td>
                  </tr>
                  <tr>
                    <td align="right">新密码</td>
                    <td>
                      <div id="divBuilding" name="divBuilding">
                        <input type="password" name="newpwd" size="20" maxlength="20">
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td align="right">确认新密码</td>
                    <td>
                      <div id="divFinace" name="divFinace">
                        <input type="password" name="confirmnewpwd" size="20" maxlength="20">
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" height="35" valign="bottom">
                      <hr size=1 color="#C1DA90">
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btd">
                      <table border="0" cellspacing="0" cellpadding="5" width="100%">
                        <tr>
                          <td height="36" align="right">
                            <h:commandButton value="确 认" action="#{frmGroupUserManage.doModifyPwd}" onclick="javascript:return modifypwd();"/>
                            <input type="button" name="Submit2" value="取 消" onclick="Javascript:history.back();">
                          </td>
                          <td>&nbsp;</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </table>
      </td>
    </tr>
  </table>
  <c:if test="${frmGroupUserManage.modifyPwdCode == 'true'}">
<script language="javascript">
    alert("密码修改成功");
    self.location.href="modifyPwd.faces?tseqn="+<%=request.getParameter("tseqn")%>;
    </script>  </c:if>
  <c:if test="${frmGroupUserManage.modifyPwdCode == 'error'}">
<script language="javascript">
    alert("原密码错误,请确认!");
    theForm.oldpwd.focus();
    </script>  </c:if>
</form>
</f:view>
</body>
</html>
<script language="javascript">
var params="?tseqn="+"<%=request.getParameter("tseqn")%>";
function modifypwd(){
   if ((document.theForm.newpwd.value=="") || (document.theForm.confirmnewpwd.value=="")){
    alert("密码不能为空");
    theForm.newpwd.focus();
    return false;
    }
   if(document.theForm.newpwd.value.length<6){
     alert("为了保证您密码的安全，请输入六位数以上的密码!");
     theForm.newpwd.focus();
     return false;
   }
   if(!(document.theForm.newpwd.value==document.theForm.confirmnewpwd.value)){
     alert("请确认您的新密码！");
     theForm.newpwd.focus();
     return false;
   }
 }
</script>
