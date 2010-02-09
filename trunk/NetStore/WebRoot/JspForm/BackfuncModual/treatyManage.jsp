<%@ page language="java" import="com.fredck.FCKeditor.*" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head> 
	<html:base />
	<title>更改注册条约</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
    <script type="text/javascript">
        function clearValue(){
            var oEditor = FCKeditorAPI.GetInstance("treatyContent"); 
			oEditor.SetHTML("");
        } 
    </script>
</head>
      
<body background="<%=request.getContextPath()%>/images/bg.gif">
	<center>
		<form id="treatyForm" action="<%=request.getContextPath()%>/JspForm/BackfuncModual/treaty.do"
			method="post">
			<table class="tbl" width="100%"  cellspacing="0" cellpadding="0">
			    <tr>
			        <td colspan="8"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
			            更改注册条约
			        </td>
			    </tr> 
				<tr>
					<td width="100%">
						<%
			// 获得上下文路径 
			String path = request.getContextPath();
			FCKeditor oFCKeditor;
			// 定义一个属性来使Action通过request来获得FCKeditor编辑器中的值
			oFCKeditor = new FCKeditor(request, "treatyContent");
			oFCKeditor.setBasePath(path + "/fckeditor/");
			String treatyContent=(String)request.getAttribute("treatyContent");
			// 设置FCKeditor编辑器打开时的默认值
			oFCKeditor.setValue(treatyContent);
			out.println(oFCKeditor.create());
%>          
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
					    <input type="hidden" name="status" value="updateTreaty" />
					    <input type="button" value="返回" class="button"/>
						<input type="submit" value="修改" class="button"/>
		                <input type="reset" value="清除" class="button" onclick="clearValue()"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html:html>

<logic:present name="xgResult">
    <script type="text/javascript">
       alert('${xgResult}');
    </script>
</logic:present>
