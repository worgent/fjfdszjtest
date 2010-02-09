<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'statusUpdateSuccess.jsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css" />
    <script type="text/javascript">
        function back(){
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=${preOrderStatus}";
        }
    </script>
  </head>
  
  <body onload="history.forward()" background="<%=request.getContextPath()%>/images/bg.gif">
      <center>
      <table width="40%" class="tbl">
          <tr>
              <td class="main">
                  <h3>${updateInfo }</h3>
              </td>
          </tr>
          <tr>
              <td class="main" align="left">
                   <input type="button" value="·µ»Ø" onclick="back()" class="button"/>
              </td>
          </tr>
      </table>
      
     
      </center>
  </body>
</html>
