<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>管理员信息修改</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
	<script type="text/javascript">
	    function check_old(){
	        var old_password=document.getElementById("old_password").value;
	        if(old_password!=null&&old_password!=""){
	            if(old_password.length<5||old_password.length>12){
	                alert("旧密码输入有误,请重输");
	                document.getElementById("old_password").focus();
	                return false;
	            }
	        }
	    }
	    function check_new1(){
	        var old_password=document.getElementById("old_password").value;
	        var new_password1=document.getElementById("new_password1").value;
	        if(new_password1!=null&&new_password1!=""){
	            if(old_password==null||old_password==""){
	                alert("请先输入旧密码");
	                document.getElementById("old_password").focus();
	                return false;
	            }
	            if(new_password1.length>=5&&new_password1.length<=12){
	                return false;
	            }
	            else if(old_password!=null&&old_password!="")
	            {
	                alert("新密码输入有误,请重输");
	                document.getElementById("new_password1").focus();
	                return false;
	            }
	        }
	        if(old_password!=null&&old_password!=""){
	            if(new_password1==""||new_password1==null||new_password1<5||new_password1>12){
	                alert("新密码输入有误,请重输");
	                return false;
	            }
	        }        
	    }
	    
	    function check_new2(){
	        var old_password=document.getElementById("old_password").value;
	        var new_password1=document.getElementById("new_password1").value;
	        var new_password2=document.getElementById("new_password2").value;
	   
	        if(new_password1!=null&&new_password1!=""){
	            if(old_password==null||old_password==""){
	                alert("请先输入旧密码");
	                document.getElementById("old_password").focus();
	            }
	            
	            if(new_password1.length<5||new_password1.length>12){
	                alert("新密码输入有误,请重输");
	                document.getElementById("new_password1").focus();
	                return false;
	            }
	            if(new_password2.length<5&&new_password2.length>12){
	                alert("重输密码有误,请重输");
	                document.getElementById("new_password2").focus();
	                return false;
	            }
	            if(new_password1!=new_password2){
	                alert("您两次输入的密码不一致,请重输");
	            }
	        }
	            
	    }
	</script>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do"
		method="post">
		<center>
			<table class="tbl" width="80%" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" bgcolor="#009CD6"
						background="../../images/newsystem/th2.gif" class="txt_b"
						align="center">
						管理员信息修改
					</td>
				</tr>
				<tr>
					<td class="main">
						真实姓名
					</td>
					<td class="main">
						<input type="text" name="realName" value="${managerMap.realName }" />
					</td>
				</tr>
				<tr>
					<td class="main">
						性别
					</td>
					<td class="main">
						<input type="radio" name="sex" ${managerMap.sex==1?
							'checked':'' } value="1" />
						男 &nbsp;
						<input type="radio" name="sex" ${managerMap.sex==0?
							'checked':'' } value="0" />
						女 &nbsp;
					</td>
				</tr>
				<tr>
					<td class="main">
						Email
					</td>
					<td class="main">
						<input type="text" name="email" value="${managerMap.email }" />
					</td>
				</tr>
				<tr>
					<td class="main">
						旧密码
					</td>
					<td class="main">
						<input type="password" name="old_password" onblur="check_old()" />
					</td>
				</tr>
				<tr>
					<td class="main">
						新密码
					</td>
					<td class="main">
						<input type="password" name="new_password1" onblur="check_new1()" />
					</td>
				</tr>
				<tr>
					<td class="main">
						重输新密码
					</td>
					<td class="main">
						<input type="password" name="new_password2" onblur="check_new2()" />
					</td>
				</tr>
				<tr align="center">
					<td colspan="2" class="main">
						<input type="hidden" name="adminId" value="${managerMap.adminId }" />
						<input type="hidden" name="status" value="updateManager" />
						<input type="submit" value="保存修改" class="button" />
					</td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html:html>
<logic:present name="xgResult">
	<script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
