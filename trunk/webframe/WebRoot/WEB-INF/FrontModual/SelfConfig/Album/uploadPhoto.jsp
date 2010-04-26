<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <s:form action="userFace" method="POST" enctype="multipart/form-data">
  <s:hidden name="action"></s:hidden>
  <tr>
    <td>
      <strong>上传照片</strong>
    </td>
  </tr>
  <tr>
    <td>
      <s:file name="upload" cssClass="input2" size="40" onchange="previewPic('upload');" id="upload"></s:file>
    </td>
  </tr>
  <tr>
    <td>
      <span id="photoview"></span>
    </td>
  </tr>
  <tr>
    <td>
      <input type="submit" value="提交" />
    </td>
  </tr>
  </s:form>
</table>
</body>
</html>
