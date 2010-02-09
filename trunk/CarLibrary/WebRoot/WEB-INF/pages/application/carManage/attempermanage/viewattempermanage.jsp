<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	加油管理
 -->
<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/carmanage/attemperManage/attemper.shtml' class="formcheck" onsubmit="return checkSubmit();">
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					调度记录信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%">
					<ww:property value="search.car_no_code"/>
				</td>
				<td align='right' width="17%">司机:</td>
				<td width="33%">
					<ww:property value="search.motor_id_name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>调度地点:</td>
				<td colspan="3">
					<ww:property value="search.attemper_locus"/>
				</td>
			</tr>
			<tr>
				<td align='right'>用车部门:</td>
				<td>
					<ww:property value="search.dept_id_name"/>
				</td>
				<td align='right'>用车人:</td>
				<td>
					<ww:property value="search.use_car_man_name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>调度原因:</td>
				<td colspan="3">
					<ww:property value="search.attemper_cause"/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注:</td>
				<td colspan="3">
					<ww:property value="search.memo"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					所属地市:
				</td>
				<td colspan="3">
					<ww:property value="search.city_name"/>
				</td>
			</tr>	
		</tbody>
	</table>
	<input type='hidden' name='search.attemperManageId' value='<ww:property value="search.attemper_manage_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>

