<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
    2008-12-22
	网关方式发送短信
 -->
<form methd='POST' name='form1' action='/system/sms/smproxysend.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					网关方式短信发送
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>手机号：</td>
				<td colspan="3">
				    <tt:TextField name="search.mobiles" value="" width="350"></tt:TextField>(多个手机号以";"号隔开)
				</td>
			</tr>
			<tr>
				<td align='right'>短信内容:</td>
				<td colspan="3">
					<tt:TextArea name="search.smsContext" value="search.smsContext" width="400" height="50" required='true' msg='请填写短信内容！'/>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="actionType" value="<ww:property value="action"/>"/>
</form>
<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>
<br>
<div style="color:red">
	&nbsp;&nbsp;&nbsp;&nbsp;注：如果需要群发，多个手机号以";"号隔开<br>
	&nbsp;&nbsp;&nbsp;&nbsp;例如：需要发送的手机号码为(13512345678,13612345678)<br>
	&nbsp;&nbsp;&nbsp;&nbsp;则：手机号文本框输入: 13512345678;13612345678 <br>
</div>
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '发 送',
        handler: function(){
        	if (fm2.isValid()) {
        		if ($('search.mobiles').value == ''){
        			Ext.Msg.show({
	 					title:'信息',
						msg: '手机号不能为空！',
						modal : true,
						buttons: Ext.Msg.OK
	 				});
	        		return ;
	        	}
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
					 		document.form1.submit();
					 	} 
				 	},
				 	animEl: 'buttonTD1'
			 	});
        	} else {
        		Ext.Msg.show({
 					title:'信息',
					msg: '请填写完整后再提交!',
					modal : true,
					buttons: Ext.Msg.OK
 				});
        	}
        }
    }).render(document.all.buttonTD1);
    
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
</script>