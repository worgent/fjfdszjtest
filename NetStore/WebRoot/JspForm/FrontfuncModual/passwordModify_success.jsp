<%@ page language="java"  pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>  
    <title>修改我的密码</title>
	<link href="<%=request.getContextPath()%>/css/header01.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/index.css"
			type="text/css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/catalog.css"
			type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
	    function to_userAccount(){
	       window.location.href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition";
	    }
	</script>

  </head>
  
  <body>
    <center>
			<jsp:include page="top.jsp" />
			<table width="98%" align="center" cellspacing="1" cellpadding="2"
				bgcolor="#e1e1e1" border="0">
				<tr>
					<td bgcolor="#ffffff" align="left">
  		<a
			href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>
		> 修改我的密码
 </td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">
    <table width="100%" >
        <tr>
            <td align="left">
                修改我的密码
            </td>
        </tr>
        <tr>
            <td  valign="middle" align="center">
            <br/>
                密码更新成功
            <br/>
            <br/>
            </td>
        </tr>
        <tr>
            <td align="center">
               <input type="button" value="完成" onclick="to_userAccount()"/>
            </td>
        </tr>
    </table>
    </td>
				</tr>
			</table>
			<jsp:include flush="true" page="bottom.jsp" />
		</center>
  </body>
</html>
