<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<form methd='POST' name='form1' action='/system/role/roleInfo.shtml' class="formcheck"  onsubmit="return checkSubmit();">
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>添加新职务信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>职务名称:</td>
				<td><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff'  name='search.roleName' value='<ww:property value="search.ROLE_NAME"/>'/></td>
			  </tr>		
			<tr>
				<td align='right'>职务说明:</td>
				<td><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff' name='search.content' value='<ww:property value="search.CONTENT"/>'/></td>
							
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
	        	document.form1.submit();
	        }
	    }).render(document.all.buttonTD1);
	    new Ext.Button({
	        text: '重 置',
	        handler: function(){
	        	document.form1.reset();
	        }
	    }).render(document.all.buttonTD2);

</script>