<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<form methd='POST' name='form1' action='/system/role/roleInfo.shtml' class="formcheck"  onsubmit="return checkSubmit();">
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>添加新角色信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>角色名称:</td>
				<td><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff'  name='search.roleName' value='<ww:property value="search.ROLE_NAME"/>'/></td>
			  </tr>		
			<tr>
				<td align='right'>角色说明:</td>
				<td><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff' name='search.content' value='<ww:property value="search.CONTENT"/>'/></td>
							
			</tr>								
			<tr>				
				<td align='right'>所属地市:</td>
				<td>
					<select name='search.cityId' class='check' verify='empty' required='true' shade='true' requiredColor='#ffffff' msg='该项不能为空'>
						<option >---请选择---</option>
						<tt:setProperty name="#select.dynamicSql" value="'select city_id,city_name from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'"/>	
						<ww:iterator value="#select.selectList">
							<option value='<ww:property value="city_id"/>' <ww:if test="city_id==search.CITY_ID">selected</ww:if>><ww:property value="city_name"/></option>
						</ww:iterator>
					</select>			
				</td>
			</tr>					
		</tbody>
	</table>
		<input type='hidden' name='search.roleId' value='<ww:property value="search.ROLE_ID"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
</form>
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

</script>