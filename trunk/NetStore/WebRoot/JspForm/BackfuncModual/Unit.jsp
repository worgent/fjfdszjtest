<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
<title>单位设置</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>
 <script type="text/javascript">
	function addUnit(obj){
	      var context=document.getElementById("addUnitNameID").value;
	    if(context!="")
	    {
	   
		document.forms[0].action='../../unit.do?status=add';
		
		document.forms[0].submit();
		document.getElementById("addUnitNameID").value="";
		}
		else
		{
		alert('添加的单位不能为空.');
		}
	}
	
	function saveUnit(obj){
		document.forms[0].action='../../unit.do?status=save';
		document.forms[0].submit();
		alert('保存修改成功.');
	}
 </script>
 
 <script type="text/javascript">
 <logic:present name="xgResult">
 	alert('${xgResult}');
 </logic:present>
 </script> 
</head>
<body background="<%=request.getContextPath()%>/images/bg.gif">
   <html:form  action="/productModify.do?status=save"  method="post" >
  <center>
    <table  class="tbl" width="780"  cellspacing="0" cellpadding="0" >
    	   <tr>
    	   <td colspan="3" bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">单位设置</td>
    	   </tr>
    				<tr>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    					序号
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				      <div class="txt_tr">单位名称</div>
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				      <div class="txt_tr">操作</div>
    					</td>
    				</tr> 
    		    <logic:present name="unitList">
    			 <logic:iterate id="item" name="unitList">
    			 <tr>
    			 <td class="main" align="center">
    			${item.unitId}
    			 <input type="hidden" name="unitId" value="${item.unitId}"/>
    			 </td>
    			 <td class="main" align="center">
    			 <input type="text" name="unitName" value="${item.unitName}"/>
    			 </td>
    			 <td class="main" align="center">
    			 <a onclick='return confirm("确定要删除当前记录吗?")' 
    			 href="<%=request.getContextPath()%>/unit.do?status=delete&uId=${item.unitId}">删除</a>
    			 </td>
    			 </tr>
    			 </logic:iterate>
    			</logic:present>
    			<tr>
    			<td colspan="3" class="main" align="center">
    			<input type="button" name="save" value="保存修改"  onclick="saveUnit(this)" class="button"/>
    			<input type="reset" name="reWrite" value="重置"  class="button" />
    			</td>
    			</tr>
    		</table>
    		
    		
    	<div>
    	<br>
    	<table class="tbl" width="780"  cellspacing="0" cellpadding="0">
    	<tr>
    	<td colspan="3" bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">添加单位</td>
    	</tr>
    	<tr>
    	<td class="main" colspan="3" align="center">添加单位
    	<input type="text" id="addUnitNameID" name="addUnitName" />
    	<input type="button" name="save" value="添 加"  onclick="addUnit(this)" class="button"/>
    	</td>
    	</tr>
    	</table>
    	</div>			
  </center>
  </html:form>
</body>
</html:html>