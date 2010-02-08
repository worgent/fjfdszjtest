<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<meta http-equiv=Content-Language content=zh-cn/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<center>
&nbsp;&nbsp;&nbsp;
用户名:<s:property value="%{search.CODE}"/>
&nbsp;&nbsp;&nbsp;客户名称:<s:property value="%{search.NAME}"/>
&nbsp;&nbsp;&nbsp;客户代码:<s:property value="%{search.CLIENTCODE}"/>
&nbsp;&nbsp;&nbsp;结算方式:<s:property value="%{search.CLIENTBALANCENAME}"/>
</center>
</body>
</html>
