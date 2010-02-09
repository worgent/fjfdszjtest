<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>支付方式维护</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
	<script type="text/javascript">
	    function addPayType(){
	       var payTypeName=document.getElementById("payTypeName").value;  
	       if(payTypeName==null||payTypeName==""){
	           alert("付款方式名称不能为空!");
	           document.getElementById("payTypeName").focus;
	           return false;
	       }  
	    
	       if(confirm("确定添加新支付方式吗？")){
	           document.forms[0].action="<%=request.getContextPath()%>/JspForm/BackfuncModual/payType.do";
		       document.forms[0].submit();
		   }
		   else{
		       return false;
		   }
	    }
	    function modify(ind){
	       var payTypeName=document.getElementById("payTypeName"+ind).value; 
	       if(payTypeName==null||payTypeName==""){
	           document.getElementById("payTypeName"+ind).focus();
	           return false;
	       }
	       if(confirm("确定修改吗?")){
	           alert(document.getElementById("companyName"+ind).value);
	           document.forms[0].action="<%=request.getContextPath()%>/JspForm/BackfuncModual/payType.do";
	           document.getElementById("status").value="updatePayType";
		       document.getElementById("ind").value=ind;
		       document.forms[0].submit();
	       }
	       else{
	           return false;
	       } 
	   }
	   
	   function del(payTypeId){
	      if(!confirm("确定删除当前记录")){
	         return false;
	      }
	      else{
	      $.ajax({
	 	    type: "POST",
	 		url: "<%=request.getContextPath()%>/JspForm/BackfuncModual/payType.do?status=deletePayType",
            data:"&payTypeId="+payTypeId+"&day1=new Date()",
	 		success: function(msg){
	 		  if(msg){
	 		      $("#tr"+payTypeId).remove();
	 		      alert("配送编号:"+payTypeId+"数据删除成功");
	 		  }
	 		  else{
	 		      alert("配送编号:"+payTypeId+"数据删除失败");
	 		  } 
	 		}
		  });
		  }
	   }
	   
    </script>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
    <%
	response.setHeader("Cache-Control","no-cache"); 
    response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
    %>
	<center>
		<form action="<%=request.getContextPath()%>/JspForm/BackfuncModual/payType.do">
		<table  class="tbl" width="80%"  cellspacing="0" cellpadding="0" >
		    <tr>
		        <td colspan="7"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						支付方式维护
					</td>
		    </tr>
			<tr >
			    <td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					网关号
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					付款方式名称
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					公司名称
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					开户行名称
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				    银行帐号
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				   备注
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					操作
				</td>
			</tr>
			<tr>
			    <td class="main">
			       &nbsp;
			    </td>
			    <td class="main">
			       <input type="text" name="payTypeName" />
			    </td>
			    <td class="main">
			       <input type="text" name="companyName"/>
			    </td>
			    <td class="main">
			       <input type="text" name="openAccountName" />
			    </td>
			    <td class="main">
			       <input type="text" name="bankAccount" />
			    </td>
			    <td class="main">
			       <input type="text" name="remark" />
			    </td>
			    <td class="main">
			       <input type="hidden" name="status" value="addPayType" />
			       <input type="button" value="添加" onclick="addPayType()" class="button" />&nbsp;
			       <input type="reset" value="重置" class="button"/>
			    </td>
			</tr>
			<tr>
			    <td colspan="7" class="main">
			    &nbsp;
			    </td>
			</tr>
			<logic:present name="payTypeList">
				<logic:iterate id="item" name="payTypeList" indexId="ind">
					<tr id="tr${item.payTypeId }">
					    <td class="main" valign="top">
                            ${item.payTypeId }<input type="hidden" name="payTypeId<%=ind%>" value="${item.payTypeId}" />
						</td>
						<td class="main">
							<input type="text" name="payTypeName<%=ind%>"
								value="${item.payTypeName }" />
							<input type="hidden" name="payTypeName<%=ind%>" value="${item.payTypeName}" />
						</td>
						<td class="main">
							<input type="text" name="companyName<%=ind%>"
								value="${item.companyName }" />
							<input type="hidden" name="companyName<%=ind%>" value="${item.companyName}" />
						</td>
						<td class="main">
							<input type="text" name="openAccountName<%=ind%>"
								value="${item.openAccountName }" />
							<input type="hidden" name="openAccountName<%=ind%>" value="${item.openAccountName}" />
						</td>
						<td class="main">
							<input type="text" name="bankAccount<%=ind%>"
								value="${item.bankAccount }" />
							<input type="hidden" name="bankAccount<%=ind%>" value="${item.bankAccount}" />
						</td>
						<td class="main">
							<input type="text" name="remark<%=ind%>"
								value="${item.remark }" />
							<input type="hidden" name="remark<%=ind%>" value="${item.remark}" />
						</td>
						<td class="main" valign="top">
							<input type="button" value="修改" class="button" onclick="modify(${ind})" />&nbsp;
							<input type="hidden" name="ind" value=""/>
							<input type="button" value="删除" class="button"
								onclick="del(${item.payTypeId })" />&nbsp;
						    <input type="reset" value="重置" class="button" />
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</form>
	</center>
</body>
</html:html>

<script type="text/javascript">
   	<logic:present name="xgResult">
   	    alert("${xgResult}");
   	</logic:present>
</script>

