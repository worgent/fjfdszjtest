<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/system/manage/manager.shtml' class="formcheck"  onsubmit="return checkSubmit();">
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>添加新管理员人信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width='20%'>管理员工号:</td>
				<td width='30%'>
					<input type='text' class='wenbenkuang check' <c:if test="${param.actionType == 'edit'}">disabled</c:if> verify='string' required='true' shade='true' requiredColor='#ffffff'  name='userInfo.staffNo' value='<ww:property value="userInfo.staffNo"/>' onblur='chk_ifExist()'/>
					<span style='color:red' id='msgSpan'></span>
				</td>
				<td align='right' width='20%'>管理员姓名:</td>
				<td width='30%'><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff' name='userInfo.staffName' value='<ww:property value="userInfo.staffName"/>'/></td>
			</tr>
			<tr>
				<td align='right'>登录密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='numchar' <c:if test="${param.actionType != 'edit'}">required='true' shade='true' requiredColor='#ffffff'</c:if> name='userInfo.password' value=''/></td>
				<td align='right'>确认密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='samefield' field='userInfo.password' name='userpwd1' value=''/></td>
			</tr>
			<tr>
				<td align='right'>联系电话:</td>
				<td>
					<input type='text' class='wenbenkuang' name='userInfo.phone' value='<ww:property value="userInfo.phone"/>'/>
				</td>
				<td align='right'>所在地市:</td>
				<td>
					<select name="userInfo.cityId">
						<option value=''></option>
						<tt:setProperty name="#select.dynamicSql" value="'select city_id,city_name from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'"/>	
						<ww:iterator value="#select.selectList">
							<option value='<ww:property value="city_id"/>' <ww:if test="city_id == userInfo.cityId">selected</ww:if>><ww:property value="city_name"/></option>
						</ww:iterator>
					</select>
				</td>
			</tr>

		</tbody>
	</table>
	<input type='hidden' name='userInfo.staffId' value='<ww:property value="userInfo.staffId"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
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
        	<c:if test="${param.actionType != 'edit'}">
        		if (!chk_ifExist()){
        			return;
        		}
        	</c:if>
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
</script>
<script language="javascript">
	
</script>