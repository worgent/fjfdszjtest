<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> 
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>	
<body>
<center>
<form  method='POST' name='form1' action='${actionBasePath}${classNameLower}.${actionExtension}'  id="form1">
  <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="740" height="45" STYLE="background-repeat: no-repeat;">　</td>
									</tr>
									
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg" STYLE="background-repeat:  repeat-y;">
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<!-- 提示信息 -->
												<tr >
													<td colspan="3">
													<strong>
													<span style="font-size: 9pt">
													${table.tableAlias}</span></strong></td>
												</tr>
												<tr align="left">
													<td colspan="3">
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
												</tr>
											<#list table.columns as c>
											<#if !c.htmlHidden>
											<#if c.isDateTimeColumn>
														<tr align="left">
															<td>
															<span style="FONT-SIZE: 9pt">
															${c.columnAlias}</span><span style="font-size: 9pt">：</span>
															<span style="font-size: 9pt">
															<s:textfield id="p${c.sqlName}"  name="search.p${c.sqlName}"  readonly="readonly"  size="30" value="%{${classNameLower}.${c.sqlName}}" > </s:textfield>
														    </span> <div id="p${c.sqlName}Tip" style="display: inline "></div></td>
														</tr>												
											<#else>
														<tr align="left">
															<td>
															<span style="FONT-SIZE: 9pt">
															${c.columnAlias}</span><span style="font-size: 9pt">：</span>
															<span style="font-size: 9pt">
															<s:textfield id="p${c.sqlName}"  name="search.p${c.sqlName}"  size="30" value="%{${classNameLower}.${c.sqlName}}" > </s:textfield>
														</span> <div id="p${c.sqlName}Tip" style="display: inline "></div></td>
														</tr>												
											</#if>	
											</#if>								
											</#list>
												<tr>
													<td colspan="3">
													<s:hidden value="%{${classNameLower}.id}" name="search.pid" id="pid"></s:hidden>
      												<s:hidden value="%{action}" name="action" id="action"></s:hidden>
													<input type="button" onclick="javascript:save()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
												    <input type="button" onclick="javascript:history.go(-1)" value="返回" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													</td>
												</tr>
											</table>
										</div>
										</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin07.jpg" width="740" height="15" STYLE="background-repeat: no-repeat;">　</td>
									</tr>
	</table>
</form>
</center>
 <script type="text/javascript">
 
  	//保存数据
	function save(){
	    if($.formValidator.pageIsValid()){
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
	
	 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
 
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		<#list table.columns as c>
		<#if c.isDateTimeColumn>
		$("#p${c.sqlName}").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"p${c.sqlName}Tip",onfocus:"",oncorrect:"请输入${c.columnAlias}"});
		<#else>
		$("#p${c.sqlName}").formValidator({onshow:"",tipid:"p${c.sqlName}Tip",onfocus:"",oncorrect:"请输入${c.columnAlias}"});
		</#if>
		</#list>
	});
	</script>
</body>
</html>	