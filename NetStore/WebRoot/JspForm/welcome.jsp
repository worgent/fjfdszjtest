<%@ page language="java"%>
<%@ page contentType="text/html;charset=GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>中国邮政速递产品成本核算和辅助决策系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=GB2312">
<link rel="stylesheet" href="<%=path %>/css/style.css" type="text/css">
</head>
<body>
    <table width="95%"  border="0" align="center" cellpadding="8" cellspacing="20">
      <tr valign="top">
        <td width="50%" bgcolor="#F7F7F7"><img src="../images/Light_Blog_icons_001.png" width="80" height="80" hspace="8" vspace="2" align="left">商品管理
         <div class="txt_info_portal">商品管理是对系统中一些最基本的商品信息进行添加、修改、删除．  
         </div>
        </td>
        <td width="50%" bgcolor="#F7F7F7"><img src="../images/Light_Blog_icons_003.png" width="80" height="80" hspace="8" vspace="2" align="left">信息管理
        <div class="txt_info_portal">信息管理是用来发布新闻、公告、查看意见、回复反馈信息。 </div></td>
         
      </tr>
      <tr valign="top">
        <td width="50%" bgcolor="#F7F7F7"><img src="../images/Light_Blog_icons_002.png" width="80" height="80" hspace="8" vspace="2" align="left">分类管理
          <div class="txt_info_portal">产品分类管理对系统商品进行分类管理．<br> 
          </div>
        </td>
        <td width="50%" bgcolor="#F7F7F7"><img src="../images/Light_Blog_icons_004.png" width="80" height="80" hspace="8" vspace="2" align="left">用户管理
        <div class="txt_info_portal">用户管理提供用户注册条约设置、商城会员信息管理、商加信息增、删、改,修改用户登录密码。 
        </div>
        </td>
      </tr>
      <tr valign="top">
        <td width="50%" bgcolor="#F7F7F7"><img src="../images/Light_Blog_icons_005.png" width="80" height="80" hspace="8" vspace="2" align="left">参数设置
        <div class="txt_info_portal">参数设置包含商城信息管理、系统参数设置、汇款方式、配送方式等等。  
		</div>
		</td>
        <td width="50%">&nbsp;</td>
      </tr>
</table>
    <br>
</body>
</html>
