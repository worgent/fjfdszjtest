<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					添加新养路费信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>
					单据日期:
				</td>
				<td colspan="3">
					<ww:property value="search.booking_date"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					车辆编号:
				</td>
				<td colspan="3">
					<ww:property value="search.carNoId"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					收费项目名称:
				</td>
				<td width="33%">
					<ww:property value="search.charge_item_name"/>
				</td>
				<td align='right' width="17%">
					收费标准:
				</td>
				<td width="33%">
					<ww:property value="search.charge_standard"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					收费范围:
				</td>
				<td>
					<ww:property value="search.charge_bound"/>
				</td>
				<td align='right'>
					批准机关:
				</td>
				<td>
					<ww:property value="search.authorize_organ"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					所属地市:
				</td>
				<td colspan="3">
					<ww:property value="search.cityId"/>
				</td>
			</tr>
			<tr>
				<td align='right'>创建人:</td>
				<td>
					<ww:property value="search.create_man_name "/>
				</td>
				<td align='right'>创建时间:</td>
				<td>
					<ww:property value="search.create_date"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>编辑人:</td>
				<td>
					<ww:property value="search.editor_man_name"/>
				</td>
				<td align='right'>编辑时间:</td>
				<td>
					<ww:property value="search.editor_date"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>执行人:</td>
				<td>
					<ww:property value="search.execute_man_name"/>
				</td>
				<td align='right'>执行时间:</td>
				<td>
					<ww:property value="search.execute_date"/>
			    </td>
			</tr>
		</tbody>
	</table>
