<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form method='POST' name='form1' action='/system/manage/manager.shtml' class="formcheck"  onsubmit="return checkSubmit();">
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>用户注册信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width='20%'>用户名:</td>
				<td width='30%'>
					<input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff'  name='userInfo.staffNo' value='' onblur='chk_ifExist()'/>
					<span style='color:red' id='msgSpan'></span>
				</td>
				<td align='right' width='20%'>昵称:</td>
				<td width='30%'><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff' name='userInfo.staffName' value=''/></td>
			</tr>
			<tr>
				<td align='right'>登录密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='numchar' name='userInfo.password' value=''/></td>
				<td align='right'>确认密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='samefield' field='userInfo.password' name='userpwd1' value=''/></td>
			</tr>
			<tr>
				<td align='right'>联系电话:</td>
				<td>
					<input type='text' class='wenbenkuang' name='userInfo.phone' value=''/>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="userInfo.isUse" value=1/>
	<input type="hidden" name="userInfo.deptId" value="2009091600000006" />
</form>
<input type="hidden" value="true" name="isTips" id="isTips" />

<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

<script language="javascript">
	new Ext.Button({
        text: '保 存',
        handler: function(){
        	if( document.form1.checkSubmit()){
        		if (!chk_ifExist()){
        			return;
        		}
        		$(isTips).value = 'false';
        		document.form1.submit();
        	}
        }
    }).render(document.all.buttonTD1);
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    
    function chk_ifExist(){
		var url = '/system/manage/manager.shtml?actionType=ifExist&userInfo.staffNo='+$('userInfo.staffNo').value;
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
				if (1 == rowSet.item(0).selectSingleNode("value").text){
					$(msgSpan).innerHTML = '该工号已存在';
					return false;
				}else{
					$(msgSpan).innerHTML = '';
					return true;
				}
			}
		}catch(e){ 
			alert(e);
		}
		return false;
	}
	
	window.onbeforeunload = function(){
		if(document.all("isTips").value == "true")
			event.returnValue = "您的文档可能没有保存!" 
	}
</script>