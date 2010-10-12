<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree.js"></script>
<title>menu</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	/*background-color: #99CCCC;*/
}
-->
</style></head>
<body>
<div class="dtree">
<p><a href="javascript:d.openAll();">open all</a>|<a href="javascript:d.closeAll();">close all</a></p>
<script type="text/javascript">
<!--

d = new dTree('d');
//mytree.add(1, 0, 'My node', 'node.html', 'node title', 'mainframe', 'img/musicfolder.gif');
d.add(0,-1,'管理目录');
d.add(1,0,'网站数据结构');
d.add(2,0,'其他');

 d.add(3,1,'新类别', 'aicms/newClass_step1.jsp', '新类别', 'main');
 d.add(4,1,'数据类别', 'aicms/classList.jsp', '数据类别', 'main');
 d.add(5,1,'新数据', 'aicms/newEntity_step1.jsp', '新数据', 'main');
 d.add(6,1,'数据查看', 'aicms/setClass.jsp', '数据查看', 'main');
 
 d.add(7,2,'××栏目管理', '#aicms/toEntityManage.jsp?path=类别>子类别>三级类别>...', '××栏目管理', 'main');//某栏目的管理快捷方式
document.write(d);
//-->
</script>
</div>
</body>
</html>