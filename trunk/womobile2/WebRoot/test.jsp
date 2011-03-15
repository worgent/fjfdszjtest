<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>测试TestUrlRewrite</title>
		<script type="text/javascrīpt" src="FCKeditor/fckeditor.js"></script>

		
	</head>
	<body>
		<%   
        	String id = (String) request.getParameter("id");   
            out.println("<h3> the value is  : " + id +" </h3> ");   
        %>
        
 <textarea name="content" cols="80" rows="4">
</textarea>
<script type="text/javascrīpt">
  var ōFCKeditor = new FCKeditor('content') ;
  oFCKeditor.BasePath = "FCKeditor/";
  oFCKeditor.Height = 400;
  oFCKeditor.ToolbarSet = "Default" ;
  oFCKeditor.ReplaceTextarea();
</script>




	</body>
</html>
