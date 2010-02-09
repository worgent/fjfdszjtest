<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
<title>评论回复</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>



<script type="text/javascript"> 	
function checkValue(obj)
{
var revCon=document.getElementById("replyContentId").value;
 if(revCon=="")
 {
 alert("回复内容不能为空.");
 return false;
 }
document.getElementById("replyContentHId").value=document.getElementById("replyContentId").value;

return true;
}		
</script>
</head>
<body background="<%=request.getContextPath()%>/images/bg.gif">
　<form  action="<%=request.getContextPath()%>/review.do?status=save"  method="post">
 <center>
        <logic:present name="replyingList">
        <logic:iterate id="item" name="replyingList">   
        <table   class="tbl" style="width: 600px; height: 360px;">
    	   <tr><td colspan="3" align="center" background="../../images/newsystem/th2.gif"> 评论回复</td></tr>
    	          <tr>
    					<td  class="main" > 
    				    用户: 
    					</td>
    					<td   class="main"  align="left">
    					<input type="text" name="userId" readonly="readonly"   value="${item.userId}"/>
    					</td>
    				</tr>
    				<tr>
    					<td  class="main" >
    				    评论标题:
    					</td>
    					<td class="main" align="left">
    				    <input type="text" name="title" readonly="readonly"   value="${item.title}"/>
    					</td>
    				</tr>
    				<tr>
    					<td height="120"   class="main" >
    				    评论内容: 
    					</td>
    					<td align="left" class="main">
    					<textarea id="reviewContectId" name="reviewContect" readonly="readonly"  cols="30" rows="5" ><%=request.getAttribute("reviewContect") %></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td   class="main"   height="120">
    				      回复内容:
    					</td>
    					<td   class="main"  align="left">
    				   <textarea id="replyContentId" name="replyContent"  cols="30" rows="5"><%=request.getAttribute("replyContent") %></textarea>
    	<input type="hidden" id="replyContentHId"  name="replyContentH"  />
    					</td>
    				</tr> 
    				<tr>
    					<td colspan="2"  class="main"  align="center">
 <input type="submit" name="save" onclick="javascript:return checkValue(this);" value="保 存" class="button"/>
    				     <input type="button" name="cacl" value="返 回" onclick="Back(this)" class="button"/>
    					</td>
    				
    				</tr> 
    		</table>
 </logic:iterate>
 </logic:present>
  <input type="hidden" name="t" value="<%=request.getAttribute("t") %>" />
  <input type="hidden" name="page" value="<%=request.getAttribute("page") %>" />
  <input type="hidden" name="rId" value="<%=request.getAttribute("rId") %>" />
 </center>
</form>
</body>
</html:html>