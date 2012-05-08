<%@ Page Language="C#" AutoEventWireup="true" CodeFile="index.aspx.cs" Inherits="admin_index" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
      <title>欢迎使用 ligerUI 权限管理系统</title> 
    <link href="../../../Lib/ligerUI/skins/Silvery/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="../../../Lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="../../../Lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="../../../Lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  
    <link href="../../Lib/css/index.css" rel="stylesheet" type="text/css" /> 
    <script src="../../Lib/js/index.js" type="text/javascript"></script> 
    <script type="text/javascript">
        $(function ()
        {
            $("#pageloading").height($(window).height());
            bulidLayout();
            bulidMenu(function ()
            {
                $("#pageloading").remove();
                setTimeout(function ()
                {
                    $("#mainmenu .l-link:first").click();
                }, 100);
            });
        });
    </script>
    <style type="text/css">
        #pageloading{position:absolute; left:0px; top:0px; background:white url('lib/images/loading.gif') no-repeat center; width:100%; height:100%; height:600px; z-index:99999;}
    </style>
</head>
<body style="padding:0; overflow:hidden; height:100%;">
<div id="pageloading"></div>
    <div id="topmenu" class="l-topmenu">
        <div class="l-topmenu-logo"></div>
        <div class="l-topmenu-welcome">
            <span class="l-topmenu-username"><%=RealName %>，</span>欢迎你
        </div>
        <div class="l-topmenu-items"></div>  
    </div>
    <div id="navigationbars" class="l-navigationbars">
        <div class="l-navigationbars-l">管理菜单</div>
        <div class="l-navigationbars-r" style="">
        <span class="l-icon-busy" style="line-height:16px;">&nbsp;&nbsp;&nbsp;&nbsp; </span>
           <a href="login.aspx?Action=out">退出</a>
        </div>
        <div class="l-navigationbars-c">
           <div class="l-icon l-icon-home"></div>
           <span></span>
        </div>
    </div>
    <div id="mainbody" class="l-mainbody" style="width:100%">
        <div position="left" title="主要菜单" id="mainmenu"> 
        </div>
        <div position="center" title="栏目">
            <iframe src="welcome.htm" frameborder="0" width="100%" height="100%" id="mainframe" name="mainframe"></iframe>
         </div> 
    </div>
    <div id="footbar" class="l-footbar"></div>
</body>
</html>
