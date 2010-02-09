<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>邮寄方式维护</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
	<script type="text/javascript">
	    function addSendMode(){
	       var sendModeName=document.getElementById("sendModeName").value;  
	       if(sendModeName==null||sendModeName==""){
	           alert("邮寄方式名称不能为空!");
	           document.getElementById("sendModeName").focus;
	           return false;
	       }  
	       var sendFee=document.getElementById("sendFee").value;  
	       if(sendFee==null||sendFee==""){
	           document.getElementById("sendFee").value=0;
	       }  
	       else{
	           //判断这个金额是否合法
	               if(isNaN(sendFee)){
	                   alert("您要修改的邮寄费用不对,请重输!");
	                   document.getElementById("sendFee").focus();
	                   return false;
	               }
	       }
	    
	       if(confirm("确定添加新邮寄方式吗？")){
	           document.forms[0].action="<%=request.getContextPath()%>/JspForm/BackfuncModual/sendMode.do";
		       document.getElementById("status").value="addSendMode";
		       document.forms[0].submit();
		   }
		   else{
		       return false;
		   }
	    }
	    function modify(ind){
	    
	           //判断要修改的数据是否正确
	           var sendModeName=document.getElementById("sendModeName"+ind).value;
	           if(sendModeName==null||sendModeName==""){
	               alert("您要修改的邮寄方式名称不对,请重输!");
	               document.getElementById("sendModeName"+ind).focus();
	               return false;
	           }
	           var sendFee=document.getElementById("sendFee"+ind).value;
	           
	           if(sendFee==null||sendFee==""){
	               document.getElementById("sendFee"+ind).value=0;
	           }
	           else{
	               //判断这个金额是否合法
	               if(isNaN(sendFee)){
	                   alert("您要修改的邮寄费用不对,请重输!");
	                   document.getElementById("sendFee"+ind).focus();
	                   return false;
	               }
	           }
	       if(confirm("确定修改吗?")){
			   document.forms[0].action="<%=request.getContextPath()%>/JspForm/BackfuncModual/sendMode.do";
		       document.getElementById("status").value="updateSendMode";
		       document.getElementById("ind").value=ind;
		       document.forms[0].submit();
		   }
	       else{
	           return false;
	       } 
	   }
	   
	   function del(sendModeId){
	      if(confirm("确定删除该配送方式吗?")){
	      $.ajax({
	 	    type: "POST",
	 		url: "<%=request.getContextPath()%>/JspForm/BackfuncModual/sendMode.do?status=deleteSendMode",
            data:"&sendModeId="+sendModeId+"&day1=new Date()",
	 		success: function(msg){
	 		  if(msg){
	 		      $("#tr"+sendModeId).remove();
	 		      alert("配送编号:"+sendModeId+"数据删除成功");
	 		  }
	 		  else{
	 		      alert("配送编号:"+sendModeId+"数据删除失败");
	 		  } 
	 		}
		  });
		  }
		  else{
		      return false;
		  }
	   }
	   
    </script>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<center>

		<form action="<%=request.getContextPath()%>/JspForm/BackfuncModual/sendMode.do">
		<table   class="tbl" width="80%"  cellspacing="0" cellpadding="0" >
		    <tr>
		        <td colspan="5"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						邮寄方式维护
					</td>
		    </tr>
			<tr>
			    <td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					序号
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					邮寄方式名称
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					邮寄费用
				</td>
				<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
					邮寄时间
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
			       <input type="text" name="sendModeName" />
			    </td>
			    <td class="main">
			       <input type="text" name="sendFee"/>
			    </td>
			    <td class="main">
			       <input type="text" name="sendTime" />
			    </td>
			    <td class="main">
			       <input type="hidden" name="status" value=""/>
			       <input type="button" value="添加" onclick="addSendMode()" class="button" />&nbsp;
			       <input type="reset" value="重置" class="button"/>
			    </td>
			</tr>
			<tr>
			    <td colspan="5" class="main">
			    &nbsp;
			    </td>
			</tr>
			<logic:present name="sendModeList">
				<logic:iterate id="item" name="sendModeList" indexId="ind">
					<tr id="tr${item.sendModeId }">
					    <td class="main">
							${item.sendModeId }	
							<input type="hidden" name="sendModeId<%=ind%>" value="${item.sendModeId}" />&nbsp;
						</td>
						<td class="main">
							<input type="text" name="sendModeName<%=ind%>"
								value="${item.sendModeName }" />
							<input type="hidden" name="sendModeName<%=ind%>" value="${item.sendModeName}" />&nbsp;
						</td>
						<td class="main">
							<input type="text" name="sendFee<%=ind%>"
								value="${item.sendFee }" />
							<input type="hidden" name="sendFee<%=ind%>" value="${item.sendFee }" />&nbsp;
						</td>
						<td class="main">
							<input type="text" name="sendTime<%=ind%>"
								value="${item.sendTime }" />
						    <input type="hidden" name="sendTime<%=ind%>" value="${item.sendTime}" />&nbsp;
						</td>
						<td class="main">
							<input type="button" value="修改" class="button"
								onclick="modify(${ind})" />&nbsp;
							<input type="hidden" name="ind" value=""/>
							<input type="button" value="删除"
								onclick="del(${item.sendModeId })" class="button" />&nbsp;
						    <input type="reset" value="重置"class="button" />
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

