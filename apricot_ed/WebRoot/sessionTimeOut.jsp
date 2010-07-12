<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link rel="stylesheet" type="text/css" href="public/css/ext-all.css"/>
<script type="text/javascript" src="public/js/adapter/ext-base.js"></script>
<script type="text/javascript" src="public/js/widgets/MessageBox.js"></script>
<script type="text/javascript">
//	alert("登陆超时，请重新登陆。");
	Ext.MessageBox.alert("系统提示","登陆超时，请重新登陆。",function(btn, text){
		self.parent.location= "<%=request.getContextPath()%>";
    });
	
	
</script>
</head>
<body>

</body>
</html>