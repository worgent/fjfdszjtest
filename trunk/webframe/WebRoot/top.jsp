<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ include file="/common/taglibs.jsp"%>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>日历控件</title>  

	<s:head theme="ajax" /> 

</head>  
<body>   

<s:datetimepicker name="todayDate" value="2007-8-9" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/> 

</body>  
</html>  
