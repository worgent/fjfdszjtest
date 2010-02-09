<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/system/systemparam/systemParam.shtml' class="formcheck"  onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>新数据字典信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width='20%'>参数类型:</td>
				<td width='30%'>
					${param.paraTypeDesc}<ww:property value="search.PARA_TYPE_DESC"/>
				</td>
				<td align='right' width='20%'>参数名称:</td>
				<td width='30%'>
					${param.paraDesc}<ww:property value="search.PARA_DESC"/>
				</td>
			</tr>
			<tr>
				<td align='right'>参数值:</td>
				<td>
					<tt:TextField name="search.paraValue" value="search.PARA_VALUE" width="200" verify='string' required='true' onblur='chk_exist()'/>
					<span id='msgSapn' style='color:red'></span>
				</td>
				<td align='right'>参数值说明:</td>
				<td>
					<tt:TextField name="search.paraValueDesc" value="search.PARA_VALUE_DESC" width="200" verify='string' required='true'/>
				</td>
			</tr>
			<tr>
				<td align='right'>参数值备注:</td>
				<td colspan="3">
					<tt:TextField name="search.paraValueMemo" value="search.PARA_VALUE_MEMO" width="200"/>
				</td>
			</tr>	
		</tbody>
	</table>
	<input type="hidden" name="search.paraType" value="${param['search.paraType']}">
	<input type="hidden" name="search.paraName" value="${param['search.paraName']}">
	<input type="hidden" name="search.paraTypeDesc" value="<ww:property value="search.PARA_TYPE_DESC"/>${param.paraTypeDesc}">
	<input type="hidden" name="search.paraDesc" value="<ww:property value="search.PARA_DESC"/>${param.paraDesc}">
	
	<input type="hidden" name="search.oldParaValueMemo" value="<ww:property value="search.PARA_VALUE_MEMO"/>">
	<input type="hidden" name="search.oldParaValue" value="<ww:property value="search.PARA_VALUE"/>">
	<input type="hidden" name="actionType" value="<ww:property value="action"/>"/>
</form>

<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid()) {
        		if (!chk_exist()){
        			return;
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
    
    function chk_exist(){
    	if ($('search.oldParaValue').value == $('search.paraValue').value){
    		$(msgSapn).innerHTML = '';
	   		return true;
	   	}else{
	    	var value = $('search.paraValue').value;
	    	if (value == ''){
	    		$(msgSapn).innerHTML = '请输入参数值！';
	    		return false;
	    	}
	    	var url = '/system/systemparam/systemParam.shtml?actionType=isExist'+
	    													'&search.paraType=${param['search.paraType']}'+
	    													'&search.paraName=${param['search.paraName']}'+
	    													'&search.paraValueMome=<ww:property value="search.PARA_VALUE_MEMO"/>'+
	    													'&search.paraValue='+$('search.paraValue').value;
			try{
				var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
				oXMLDom.async = false ;
				oXMLDom.load(url); 
				var root;
				if (oXMLDom.parseError.errorCode != 0) {
					var myErr = oXMLDom.parseError;
					return;
				} else {
					root = oXMLDom.documentElement;
				}
				if (null != root){
					var rowSet = root.selectNodes("//isExist");
					if (0 < rowSet.item(0).selectSingleNode("value").text){
						$(msgSapn).innerHTML = "对不起，请输入参数值已存在，请重新输入！";
						return false;
					}else{
						$(msgSapn).innerHTML = '';
						return true;
					}
				}
			}catch(e){ 
				alert(e);
			}
		}
    }
</script>