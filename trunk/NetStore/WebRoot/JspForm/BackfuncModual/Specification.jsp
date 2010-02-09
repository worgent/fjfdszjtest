<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
<title>品牌设置</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>

</head>
<script type="text/javascript">
	function addSpec(obj){
	     var context=document.getElementById("addSpecNameID").value;
	    if(context!="")
	    {
		document.forms[0].action='../../specification.do?status=add';
		document.forms[0].submit();
		}
		else
		{
		alert('添加的品牌不能为空.');
		}
	}
	
	function saveSpec(obj){
		document.forms[0].action='../../specification.do?status=save';
		document.forms[0].submit();
		alert('保存修改成功.');
	}
 </script>
<script type="text/javascript">
  <logic:present name="xgResult">
 	alert('${xgResult}');
</logic:present>
 </script>

 
 
<body background="<%=request.getContextPath()%>/images/bg.gif">
  <form  action="/specification.do?status=save"  method="post" >
  <center>
       <table class="tbl" width="780"  cellspacing="0" cellpadding="0"  >
    	   <tr>
    	   <td colspan="3"   background="../../images/newsystem/th2.gif" class="txt_b"  align="center">品牌设置</td></tr>
    				<tr>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    					序号
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				     品牌名称
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				      操作
    					</td>
    				</tr> 
    		    <logic:present name="specList">
    			 <logic:iterate id="item" name="specList">
    			 <tr>
    			 <td class="main">
    			 ${item.specificationId}
    			 <input type="hidden" name="specId" value="${item.specificationId}"/>
    			 </td>
    			 <td class="main">
    			 <input type="text" name="specName" value="${item.specificationName}"/>
    			 </td>
    			 <td class="main"><a onclick='return confirm("确定要删除当前记录吗?")' 
    			 href="<%=request.getContextPath()%>/specification.do?status=delete&specId=${item.specificationId}">删除</a></td>
    			 </tr>
    			 </logic:iterate>
    			</logic:present>
    			
    			<tr>
    			<td colspan="3" class="main" align="center">
    			<input type="button" name="save1" value="保存修改"  onclick="saveSpec(this)" class="button" />
    			<input type="reset" name="reWrite" value="重置" class="button" />
    			</td>
    			</tr>
    		</table>
    	<div>
        <br>
    	<table class="tbl" width="780"  cellspacing="0" cellpadding="0" >
    	<tr>
    	<td  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">添加品牌</td>
    	</tr>
    	<tr>
    	<td class="main"  align="center">
    	添加品牌
    	<input type="text" id="addSpecNameID" name="addSpecName" />
    	<input type="button" name="save" value="添 加"  onclick="addSpec(this)" class="button" />
    	</td>
    	</tr>
    	</table>
  
  </center>
  </form>
</body>
</html:html>





