
<!--
  main/error.jsp
  功能:错误处理
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@page contentType="text/html; charset=GB2312" isErrorPage="true"%>
<%@page import="java.io.*,YzSystem.JMain.*,java.util.*,YzSystem.J_System.*"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/mm.css" type="text/css">
</head><BODY oncontextmenu=self.event.returnValue=false>
<table width="100%" border="0" height="100%">
  <tr align="center">
    <td>
    <form method="post" action="" name="forms[0]">
      <table border="1" bordercolorlight="000000" bordercolordark="FFFFFF" cellspacing="0" class="btd">
        <tr>
          <td>
            <table width="100%" border="0" class="btd3" cellspacing="0" cellpadding="2">
              <tr align="right">
                <td width="90%" align="left">异常信息</td>
                <td width="10%">
                  <table border="1" class="btd2" bordercolorlight="666666" bordercolordark="FFFFFF" cellpadding="0" cellspacing="0" width="18">
                    <tr>
                      <td width="16">
                        <b>
                          <a href="javascript:history.go(-1)" onMouseOver="window.status='';return true" onMouseOut="window.status='';return true" title="关闭">
                            <font color="000000">×</font>
                          </a>
                        </b>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <table border="0" width="350" cellpadding="4">
              <tr>
                <td width="59" align="center" valign="top">&nbsp;</td>
                <td width="269">
                <%
                  String errorInfo = "";
                  // servlet异常处理
                  if (exception != null) {
                    // if ((exception.getMessage() != null) && (!(exception.getMessage().equals("")))) {
                    // 本系统异常处理
                    out.println("<P> 系统出现"+exception.getClass()+"异常，请联系维护人员! </P>");
                    if (wlglException.class.isInstance(exception)) {
                      errorInfo = "异常为yzapprove异常!堆栈为:<br>" + exception.getMessage();
                    }
                    // 其他servlet异常处理
                    else {
                      StringWriter sw = new StringWriter();
                      PrintWriter ps = new PrintWriter(sw, true);
                      exception.printStackTrace(ps);
                      //                    out.println("<P>" + sw.getBuffer() + "</P>");
                      errorInfo = "异常为其他异常!堆栈为:<br>" + sw.getBuffer();
                    }
                  }
                  // web页面错误处理
                  else {
                    Integer errorcode = (Integer) request.getAttribute("javax.servlet.error.status_code");
                    if (errorcode.intValue() == 404) {
                      out.println("您访问的页面不存在，请确定输入的url正确!</br>");
                      // errorInfo = "您访问的页面不存在，请确定输入的url正确!";
                    }
                    else {
                      out.println("<p>错误码：" + request.getAttribute("javax.servlet.error.status_code") + "<br>");
                      out.println("<p>讯息：" + request.getAttribute("javax.servlet.error.message") + "<br>");
                      out.println("<p>例外：" + request.getAttribute("javax.servlet.error.exception_type") + "<br>");
                      errorInfo = "错误码：" + request.getAttribute("javax.servlet.error.status_code") + "\n"
                          + "讯息：" + request.getAttribute("javax.servlet.error.message") + "\n"
                          + "例外：" + request.getAttribute("javax.servlet.error.exception_type");
                    }
                  }
                  // 写文件
                  if (!errorInfo.equals("")) {
                    String sSysPath="EleWork";
                    File sysd = new File(sSysPath); //建立代表Sub目录的File对象，并得到它的一个引用
                    if (!sysd.exists()) { //检查Sub目录是否存在
                      sysd.mkdir(); //建立Sub目录
                      System.out.println(sSysPath + "目录不存在，已建立!");
                    }

                    String sPath = sSysPath+File.separator+"log"; //将要建立的目录路径
                    String sFile = UtilCommon.getDate() + ".log"; //将要建立的日志
                    File d = new File(sPath); //建立代表Sub目录的File对象，并得到它的一个引用
                    if (!d.exists()) { //检查Sub目录是否存在
                      d.mkdir(); //建立Sub目录
                      System.out.println(sPath + "目录不存在，已建立!");
                    }
                    File f = new File(sPath, sFile);
                    if (!f.exists()) { //检查File.txt是否存在
                      f.createNewFile(); //在当前目录下建立一个名为File.txt的文件
                      System.out.println(sPath + File.separator + sFile + " 不存在，已建立。"); //输出目前所在的目录路径
                    }
                    RandomAccessFile rf = new RandomAccessFile(sPath + File.separator + sFile, "rw"); //定义一个类RandomAccessFile的对象，并实例化
                    rf.seek(rf.length()); //将指针移动到文件末尾
                    String sInfo = "";
                    //----打印时间
                    Date today = new Date();
                    sInfo = "时间:" + today + "\n<br>";
                    rf.write(sInfo.getBytes());
                    //----打印客户地址
                    sInfo = "客户地址:" + request.getRemoteAddr() + "(" + request.getRemoteHost() + ")" + "\n<br>";
                    rf.write(sInfo.getBytes());
                    //----打印登陆用户
                    BeanLogin userinfo = ((BeanLogin) session.getAttribute(
                        "userInfo"));
                    if (userinfo != null) {
                      sInfo = "用户:" + userinfo.getLoginSeqn() + "(" + userinfo.getLoginName() + ")\n<br>";
                      rf.write(sInfo.getBytes());
                    }
                    sInfo = "错误详情:" + "\n<br>";
                    rf.write(sInfo.getBytes());
                    rf.write(errorInfo.getBytes());
                    rf.writeBytes("\n==============================================================\n");
                    rf.close(); //关闭文件流}
                  }
                %>
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center" valign="top">
                  <input type="button" name="ok" value="　确 定　" onclick=javascript:history.go(-1)>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>
