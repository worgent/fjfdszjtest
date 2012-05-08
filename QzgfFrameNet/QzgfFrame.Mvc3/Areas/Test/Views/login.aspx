﻿<%@ Page Language="C#" AutoEventWireup="true" CodeFile="login.aspx.cs" Inherits="QzgfFrame.Mvc3.Areas.Test.Views.admin_login" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>ligerUI 权限管理系统登陆</title>
    <script src="../../../Lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <style type="text/css">
        *{ padding:0; margin:0;}
        body{ text-align:center; background:#4974A4;}
        #login{ width:740px; margin:0 auto; font-size:12px;}
        #loginlogo{ width:700px; height:100px; overflow:hidden; background:url('lib/images/login/logo.jpg') no-repeat; margin-top:50px;   }
        #loginpanel{ width:729px; position:relative;height:300px;}
        .panel-h{ width:729px; height:20px; background:url('lib/images/login/panel-h.gif') no-repeat; position:absolute; top:0px; left:0px; z-index:3;}
        .panel-f{ width:729px; height:13px; background:url('lib/images/login/panel-f.gif') no-repeat; position:absolute; bottom:0px; left:0px; z-index:3;}
        .panel-c{ z-index:2;background:url('lib/images/login/panel-c.gif') repeat-y;width:729px; height:300px;}
        .panel-c-l{ position:absolute; left:60px; top:40px;}
        .panel-c-r{ position:absolute; right:20px; top:50px; width:222px; line-height:200%; text-align:left;}
        .panel-c-l h3{ color:#556A85; margin-bottom:10px;}
        .panel-c-l td{ padding:7px;}
        
        
        .login-text{ height:24px; left:24px; border:1px solid #e9e9e9; background:#f9f9f9;}
        .login-text-focus{ border:1px solid #E6BF73;}
        .login-btn{width:114px; height:29px; color:#E9FFFF; line-height:29px; background:url('lib/images/login/login-btn.gif') no-repeat; border:none; overflow:hidden; cursor:pointer;}
        #txtUsername,#txtPassword{ width:191px;} 
        #logincopyright{ text-align:center; color:White; margin-top:50px;}
    </style>
    <script type="text/javascript">
        var FromUrl = '<%=FromUrl %>';
        $(function ()
        {
            $(".login-text").focus(function ()
            {
                $(this).addClass("login-text-focus");
            }).blur(function ()
            {
                $(this).removeClass("login-text-focus");
            });

            $("#btnLogin").click(function ()
            {
                var username = $("#txtUsername").val();
                var password = $("#txtPassword").val();
                if (username == "")
                {
                    alert('账号不能为空!');
                    $("#txtUsername").focus();
                    return;
                }
                if (password == "")
                {
                    alert('密码不能为空!');
                    $("#txtPassword").focus();
                    return;
                }
                $.ajax({
                    type: 'post', cache: false, dataType: 'json',
                    url: 'service/ValidateData.ashx',
                    data: [
                    { name: 'Action', value: 'Login' },
                    { name: 'username', value: username },
                    { name: 'password', value: password }
                    ],
                    success: function (result)
                    {
                        if (!result)
                        {
                            alert('登陆失败,账号或密码有误!');
                            $("#txtUsername").focus();
                            return;
                        } else
                        {
                            location.href = FromUrl;
                        }
                    },
                    error: function ()
                    {
                        alert('发送系统错误,请与系统管理员联系!');
                    },
                    beforeSend: function ()
                    {
                        $("#btnLogin").attr("disabled", true);
                    },
                    complete: function ()
                    {
                        $("#btnLogin").attr("disabled", false);
                    }
                });
            });
        });
    </script>
</head>
<body style="padding:10px">
    <div id="login">
        <div id="loginlogo"></div>
        <div id="loginpanel">
            <div class="panel-h"></div>
            <div class="panel-c">
                <div class="panel-c-l">
                   
                    <table cellpadding="0" cellspacing="0">
                        <tbody>
                         <tr>
                            <td align="left" colspan="2"> 
                             <h3>请使用LigerUI 权限管理系统账号登陆</h3>
                            </td>
                            </tr> 
                            <tr>
                            <td align="right">账号：</td><td align="left"><input type="text" name="loginusername" id="txtUsername" class="login-text" /></td>
                            </tr>
                            <tr>
                            <td align="right">密码：</td><td align="left"><input type="password" name="loginpassword" id="txtPassword" class="login-text" /></td>
                            </tr> 
                            <tr>
                            <td align="center" colspan="2">
                                <input type="submit" id="btnLogin" value="登陆" class="login-btn" />
                            </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="panel-c-r">
                <p>请从左侧输入登录账号和密码登录</p>
                <p>如果遇到系统问题，请联系网络管理员。</p>
                <p>如果没有账号，请联系网站管理员。 </p>
                <p>......</p>
                </div>
            </div>
            <div class="panel-f"></div>
        </div>
         <div id="logincopyright">Copyright © 2011 LigerUI </div>
    </div>
</body>
</html>
