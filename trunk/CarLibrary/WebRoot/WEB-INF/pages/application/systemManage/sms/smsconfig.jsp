<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
    2008-12-22
	网关方式发送短信
 -->
<form methd='POST' name='form1' action='/system/sms/smsconfig.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					系统短信发送设置
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="33%" align='left'>IC卡报警短信开关设置(每隔30分钟查询一次)：</td>
				<td >
				<tt:CheckBox name="search.iccardswitch" filedValue="true" value="search.iccardswitch"></tt:CheckBox>
				</td>
			</tr>
			<tr bgcolor="green">
      		</tr>
			<tr>
				<td width="33%"  align='left'>非工作时间，停车超时短信开关设置：</td>
				<td>
					<tt:CheckBox name="search.outbandswitch" filedValue="true" value="search.outbandswitch"></tt:CheckBox>
				</td>
			</tr>
			<tr>
				<td align='left' colspan="4" >
				非工作时间，停车超时报警在工作时间(周一到周五)短信在<tt:TextField name="search.beforehours" value="search.beforehours" required="true" msg="请填写"/>时之前和
				在<tt:TextField name="search.afterhours" value="search.afterhours" required="true" msg="请填写"/>时之后发送,每隔30分钟查询一次
				</td>
			</tr>
			<tr>
				<td width="33%"  align='left'> 到站报警短信开关设置：</td>
				<td>
					<tt:CheckBox name="search.backswitch" filedValue="true" value="search.backswitch"></tt:CheckBox>
				</td>
			</tr>			
			<tr>
				<td align='left' colspan="4" >
				到站报警短信,派车预订在<tt:TextField name="search.expforehours" value="search.expforehours" required="true" msg="请填写"/>分钟之内有效,之后作废.
				</td>
			</tr>
			<!-- 
			<tr>
				<td width="33%" align='left'>审批短信开关设置</td>
				<td >
				<tt:CheckBox name="search.flowerswitch" filedValue="true" value="search.flowerswitch"></tt:CheckBox>
				</td>
			</tr>	
			 -->		
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

<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid()) {
				document.form1.submit();
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