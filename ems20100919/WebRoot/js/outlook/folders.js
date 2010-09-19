/***************************************
*                                      *
*           OUTLOOK-LIKE BAR           *
*                                      *
*             Written by               *
*           Massimo Guccione           *
*            Multimedia Lab            *
*       Intersiel S.p.A. (W)1999       *
*                                      *
* Important: to use this script don't  *
*            remove these comments     *
*                                      *
* Version 1.0Beta Freeware (MSIE only) *
*                                      *
* mail : m.guccione@telcal.it          *
*        obyone@antares.it             *
*        please report for bugs        *
*                                      *
*  for both Netscape and MSIE version  *
*  contact me! (freeware of course)    *
*                                      *
*   update : doggie_wang@hotmail.com   *
*           1.0 (MSIE only)            *
*                                      *
****************************************/


/********************************************
folder name must be OutBarFolder# where # start with 1 and increase by 1
first element of array is the folder label, next elements are :
1) url for icon of item
2) label for item
3) action link : put 'javascript:MyFunction()' to execute javascript instead of hyperlink
4) target frame : ignored if you use 'javascript:' in the action link (use 'window' instead of 'parent.MAIN' if you wish the link to load in the CURRENT page
********************************************/
//parent.main.content  and   parent.main

/*
OutBarFolder1=new Array(
"首页",
"js/outlook/images/menu1-1.gif"    ,    "浏览首页","adminIndexmain.jsp"             ,"parent.main.content"      ,"浏览首页"
);
*/
OutBarFolder1=new Array(
"自助管理",
"js/outlook/images/menu1-1.gif"    ,    "网上寄件"    ,"net/order.do"      ,"parent.main.content"      ,"网上寄件",
"js/outlook/images/menu1-1.gif"    ,    "网上打单"    ,"net/print.do"      ,"parent.main.content"      ,"网上打单",
"js/outlook/images/menu1-1.gif"    ,    "打印配置"    ,"net/printConfig.do"      ,"parent.main.content"      ,"打印配置"
);

OutBarFolder2=new Array(
"用户设置",
"js/outlook/images/menu1-1.gif"    ,    "修改用户信息"    ,"archives/user.do?action=perchangeuser"      ,"parent.main.content"      ,"修改用户信息",
"js/outlook/images/menu1-1.gif"    ,    "修改密码"    ,"archives/user.do?action=perchangepwd"      ,"parent.main.content"      ,"修改密码"
);

OutBarFolder3=new Array(
"用户管理",
"js/outlook/images/menu1-1.gif"    ,    "用户列表"    ,"archives/user.do"      ,"parent.main.content"      ,"添加用户"
);

OutBarFolder4=new Array(
"基础档案",
"js/outlook/images/menu1-1.gif"    ,    "取件地址"    ,"archives/address.do"      ,"parent.main.content"      ,"取件地址",
"js/outlook/images/menu1-1.gif"    ,    "取件联系人"    ,"archives/clientMsg.do"      ,"parent.main.content"      ,"取件联系人",
"js/outlook/images/menu1-1.gif"    ,    "服务时间"    ,"archives/serTime.do"      ,"parent.main.content"      ,"服务时间"
);
