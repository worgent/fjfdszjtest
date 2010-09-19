<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<%@  page  import="java.util.*"  %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>图片添加</title>

<script type="text/javascript">
 var dWin=null;

function uploadSmallPic(str){

//document.forms[0].action="../../productModify.do?status=updSmallPic";
//document.forms[0].submit();
var feather="dialogWidth=466px;scrollbars=yes;dialogHeight=200px";
dWin=showModelessDialog('/addUpSmall.jsp',window,feather);
//if(dWin!=null)
//{
//document.getElementById("ip2").value=dWin;   
//}
}

		
function smallPicDetail(str){

			var url="productModify.do?status=showSmallPicture&picPath="+document.getElementById("smallPathId").value;
			var arg=document;
			var feather="dialogWidth=220px;scrollbars=yes;dialogHeight=220px";
			var sonvalue=window.showModalDialog(url,arg,feather);	
		
	}	
 </script>
	
</head>
<body> 
<center>
<table width="458" height="188">
<tr>
<td>

 图片说明:
</td>
<td>
    <input type="text" id="search.EXPLAIN" name="search.EXPLAIN"  />

</td></tr>

<tr>
<td>
图片文件名:

</td>
<td>
 <input type="text" id="smallPathId" name="smallPath" readOnly />
<input type="button" name="small" value="上传小图" onclick="uploadSmallPic('small')" class="button" />
</td>
</tr>

<tr>
					<td class="main" align="left"> 
						是否审核: 
					</td>
					<td class="main" colspan="2" align="left">
						<input type="checkbox" id="search.pisaudit" name="search.pisaudit" checked  value="1"/><font  color="#ff0000" size="2">（如果选中的话将直接审核）</font>
					</td>
				</tr>
						<tr>
					<td class="main" align="left">&nbsp; 
						录入时间: 
					</td>
					<td class="main" colspan="2" align="left">
							
				<%    
Calendar  calendar=Calendar.getInstance();  
   int  year=calendar.get(Calendar.YEAR);  
   int  month=calendar.get(Calendar.MONTH)+1;  
   int  day=calendar.get(Calendar.DATE);  
     String  date=year+"-"+month+"-"+day;
 %>
				<input type="text" id="search.releasetime" name="search.releasetime"
							value="<%=date%>"
							onclick="setday(this)" readonly size="12" />&nbsp;

					</td>
				</tr>





</table>

</center>
</body>
</html>