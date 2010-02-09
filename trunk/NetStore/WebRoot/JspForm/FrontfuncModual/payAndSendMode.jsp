<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
<head>
	<html:base />
	<title>结算中心:选择付款与支付方式</title>
	<link href="<%=request.getContextPath()%>/css/header01.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/index.css"
		type="text/css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/catalog.css"
		type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	    function check(theForm){
	        var   flag1=false;     
            for(var i=0;i<form2.sendModeId.length;i++){
                if(form2.sendModeId[i].checked)   
                {     
                   
                    flag1=true;     
                    break;
                }   
            }
            if(!flag1){   
                        alert("请选择送货方式");  
                        location="#sendModeId";
                        return false;
                    }
	    }
	    

	</script>
</head>

<body>
	<center>
		<jsp:include page="top.jsp" />

		<form name="form2"
			action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do"
			method="get">
			<table width="98%" align="center" cellspacing="1" cellpadding="2"
				bgcolor="#e1e1e1" border="0">
				<tr>
					<td bgcolor="#ffffff" align="left">
						请选择您的送货
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						<table>
							<tr>
								<td bgcolor="#ffffff">
									送货方式
								</td>
							</tr>
							<tr>
								<td bgcolor="#ffffff">
									<logic:present name="sendModeList">
										<logic:iterate id="item" name="sendModeList">
		                                &nbsp;&nbsp;&nbsp;&nbsp;<input
												type="radio" name="sendModeId"
												${item.ifChecked } value="${item.sendModeId }" />${item.sendModeName}(费用${item.sendFee}元)&nbsp;&nbsp; ${item.sendTime }<br />
										</logic:iterate>
									</logic:present>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td align="left" bgcolor="#ffffff">
						<input type="hidden" name="goFlag" value="${goFlag}" />
						<input type="hidden" name="status" value="to_orderConfirm" />
						<input type="submit" value="下一步" onclick="return check(this.form)" />
					</td>
				</tr>
			</table>
		</form>
	</center>
	<jsp:include flush="true" page="bottom.jsp" />
</body>
</html:html>

