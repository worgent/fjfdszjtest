<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>错误信息</title>
		<link href="css/css1.css" rel="stylesheet" type="text/css">	
		<script type="text/javascript">
		window.onload=Load;  
		var secs =5; //倒计时的秒数 
	    var URL ;
	    function Load(url){
	       URL ="backlogin.do";
	       for(var i=secs;i>=0;i--) 
	       { 
	       		window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000); 
	       } 
	    }
	    
	    function doUpdate(num) 
	    { 
	     	document.getElementById('ShowDiv').innerHTML = '将在'+num+'秒后自动跳转到登录页面' ;
	     	if(num == 0) { window.parent.location=URL;  } 
	    }   
		</script>
		
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<table width="600" border="0" align="center" cellpadding="10"
			cellspacing="0">
			<tr>
				<td width="400">
					<div id="error" class="errormsg">
						<s:property value="%{interceptError}" escape="false"/>
					</div>
				</td>
			</tr>
			<tr>
				<td width="400">
					<div id="ShowDiv" class="errormsg">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<a href="javascript:history.go(-1);" >重新登录</a>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
