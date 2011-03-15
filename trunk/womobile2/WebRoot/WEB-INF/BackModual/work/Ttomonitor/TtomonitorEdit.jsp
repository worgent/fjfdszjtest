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
<form  method='POST' name='form1' action='/work/ttomonitor.do'  id="form1">
  <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg" STYLE="background-repeat:  repeat-y;">
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<!-- 提示信息 -->
												<tr >
													<td colspan=3 align="center" colspan=3 height="30" style="vertical-align:middle;">
													<strong>督办工作</strong></td>
												</tr>
												<tr align="left">
													<td colspan="3" >
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
												</tr>
														<tr align="left">
															<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															主&nbsp;&nbsp;&nbsp;&nbsp;题：</span>
															<span style="font-size: 9pt">
															<s:textfield id="ptitle"  name="search.ptitle"  size="15" value="%{ttomonitor.title}"  cssClass="editcss"> </s:textfield>
														</span> <div id="ptitleTip" style="display: inline "></div></td>
														
														<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															登记时间：</span>
															<span style="font-size: 9pt">
															<s:textfield id="pmakedatetime"  name="search.pmakedatetime"  readonly="true"  size="15" value="%{ttomonitor.makedatetime}"  cssClass="editcss"> </s:textfield>
														</span> <div id="pmakedatetimeTip" style="display: inline "></div></td>
															
															<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															登&nbsp;记&nbsp;人：</span>
															<span style="font-size: 9pt">
															<s:textfield id="pmaker"  name="search.pmaker"  size="15" value="%{ttomonitor.maker}"  cssClass="editcss"> </s:textfield>
														</span> <div id="pmakerTip" style="display: inline "></div></td>
														
														</tr>												
														<tr align="left">
															<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															督办类别：</span>
															<span style="font-size: 9pt">
															<s:select id="ptomonitortype" name="search.ptomonitortype" value="%{ttomonitor.tomonitortype}"   list="tomonitortypeList" listKey="id" listValue="name"  cssClass="editcss"></s:select>
														</span> <div id="ptomonitortypeTip" style="display: inline "></div></td>
														
														<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															任务级别：</span>
															<span style="font-size: 9pt">
															<s:select id="pmissiongrade"  name="search.pmissiongrade" value="%{ttomonitor.missiongrade}"   list="missiongradeList" listKey="id" listValue="name"   cssClass="editcss"></s:select>
														</span> <div id="pmissiongradeTip" style="display: inline "></div></td>
														
														<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															附&nbsp;&nbsp;&nbsp;&nbsp;件：</span>
															<span style="font-size: 9pt">
															<s:textfield id="pattachment"  name="search.pattachment"  size="15" value="%{ttomonitor.attachment}"  cssClass="editcss"> </s:textfield>
														</span> <div id="pattachmentTip" style="display: inline "></div></td>
														
														</tr>																						
														<tr align="left">
															<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															指&nbsp;&nbsp;&nbsp;&nbsp;标：</span>
															<span style="font-size: 9pt">
															<s:select id="ptarget"  name="search.ptarget" value="%{ttomonitor.target}"   list="#{'日':'日','周':'周','月':'月'}" cssClass="editcss" cssStyle="width:40px"></s:select>
														</span> <div id="ptargetTip" style="display: inline "></div>
															<span style="FONT-SIZE: 9pt">
															指标值：</span>
															<span style="font-size: 9pt">
															<s:textfield id="ptargetvalue"  name="search.ptargetvalue"  size="5" value="%{ttomonitor.targetvalue}"  cssClass="editcss" cssStyle="width:30px"> </s:textfield>
														</span> <div id="ptargetvalueTip" style="display: inline "></div></td>
														
														
														
														<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															反馈单模板：</span>
															<span style="font-size: 9pt">
															<s:select id="pfeedbackpattern"  name="search.pfeedbackpattern" value="%{ttomonitor.feedbackpattern}"   list="patternList" listKey="id" listValue="name"   cssClass="editcss"></s:select>
														</span> <div id="pfeedbackpatternTip" style="display: inline "></div></td>
														
														<td  align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															执&nbsp;行&nbsp;人：</span>
															<span style="font-size: 9pt">
															<s:hidden value="%{tdailywork.executors}" name="search.pexecutors" id="pexecutors"></s:hidden>
															<s:textfield id="pexecutorsname"  name="search.pexecutorsname"  size="15" value="%{ttomonitor.executorsname}" readonly="true"  cssClass="editcss" onfocus="executorsiframe();"> </s:textfield>
														</span> <div id="pexecutorsTip" style="display: inline "></div></td>
														
														</tr>												
																						
														<tr>
															<td colspan="3" align="left" class="tdex" style="font-size:12px">
															<span style="FONT-SIZE: 9pt">
															描&nbsp;&nbsp;&nbsp;&nbsp;述：</span>
															<span style="font-size: 9pt">
															<s:textarea id="premark"  name="search.premark" cols="100" rows="5" value="%{ttomonitor.remark}"  cssClass="editcss" cssStyle="width:425px;height:40px"> </s:textarea>
														</span> <div id="premarkTip" style="display: inline "></div></td>
														</tr>												
												<tr>
													<td align="center" colspan="3" height=40>
													<s:hidden value="%{ttomonitor.id}" name="search.pid" id="pid"></s:hidden>
      												<s:hidden value="%{action}" name="action" id="action"></s:hidden>
													<input type="button" onclick="javascript:save()" value="保存" class="btn" />
													<input type="button" onclick="javascript:reset()" value="重置" class="btn" />
												    <input type="button" onclick="javascript:history.go(-1)" value="返回" class="btn" />
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
		$("#ptitle").formValidator({onshow:"",tipid:"ptitleTip",onfocus:"",oncorrect:"请输入主题"});
		$("#ptomonitortype").formValidator({onshow:"",tipid:"ptomonitortypeTip",onfocus:"",oncorrect:"请输入督办类别"});
		$("#pmissiongrade").formValidator({onshow:"",tipid:"pmissiongradeTip",onfocus:"",oncorrect:"请输入任务级别"});
		$("#pattachment").formValidator({onshow:"",tipid:"pattachmentTip",onfocus:"",oncorrect:"请输入附件"});
		$("#ptarget").formValidator({onshow:"",tipid:"ptargetTip",onfocus:"",oncorrect:"请输入指标"});
		$("#ptargetvalue").formValidator({onshow:"",tipid:"ptargetvalueTip",onfocus:"",oncorrect:"请输入指标值"});
		$("#pfeedbackpattern").formValidator({onshow:"",tipid:"pfeedbackpatternTip",onfocus:"",oncorrect:"请输入反馈单模板"});
		$("#pmakedatetime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pmakedatetimeTip",onfocus:"",oncorrect:"请输入登记时间"});
		$("#pmaker").formValidator({onshow:"",tipid:"pmakerTip",onfocus:"",oncorrect:"请输入登记人"});
		$("#premark").formValidator({onshow:"",tipid:"premarkTip",onfocus:"",oncorrect:"请输入描述"});
		$("#pexecutors").formValidator({onshow:"",tipid:"pexecutorsTip",onfocus:"",oncorrect:"请输入执行人"});
	});
	
	
	//执行人
	function executorsiframe(){
	    var pid=$("#pid").val();
	    if(pid==""){
	    	pid="ex";
	    }
        if ($("#executorsdialog").length == 0){
	    	$("body").append(' <div id="executorsdialog" title="执行人"> '+
	    	'<iframe src="/user!getUser.do?search.type=2&search.pid='+pid+' " id = "executorsframe" width="100%"   height="100%"></iframe>'+
	    	'</div>');  
	    	
	    	$("#executorsdialog").dialog({
				bgiframe: true,
				autoOpen: false,
				width: 700,
				height: 300,
				modal: true,
				buttons: {
					'确认': function() {
					   //调用iframe中的方法
					   executorsframe.procheck();
					   if(executorsframe.document.all.frmid.value!=""){
							   $("#pexecutorsname").val(executorsframe.document.all.frmname.value);
							   $("#pexecutors").val(executorsframe.document.all.frmid.value); 
					   }else
					   {
					       jAlert('请选择一个执行人','提示');
					   }
					   $(this).dialog('close');
					},
					'取消': function() {
						$(this).dialog('close');
					}
				},
				close: function() {
					 
				}
		   });
    	} 
    	//显示
		 $('#executorsdialog').dialog('open');
	}
	</script>
</body>
</html>	