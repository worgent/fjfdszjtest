<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>Excel文件--身份证列校验程序</title>
	</head>
	<link rel="stylesheet" href="css.css" type="text/css"> 
	
	<body>

		<center>
			<h3>
				<font color=#FF0000>Excel文件--身份证列校验程序</font>
			</h3>
			<form action="../../UploadTestAction" method="post"
				enctype="multipart/form-data">
				上传文件：
				<input type="file" name="file1" size="30" maxlength="80">
				<br>
				
				<input type="submit" value="确认">
			</form>
			
		</center>
	</body>
</html>
