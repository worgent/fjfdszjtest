<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	车辆维修申请
 -->
<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					车辆维修申请
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>维修时间:</td>
				<td>
					<ww:property value="search.maintain_date"/>
				</td>
				<td align='right' width="17%">维修车辆编号:</td>
				<td width="33%">
					<ww:property value="search.car_no_code"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">申请人:</td>
				<td width="33%">
					<ww:property value="search.proposer_name"/>
				</td>
				<td align='right' width="17%">维修厂:</td>
				<td width="33%">
					<ww:property value="search.coop_unit_name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>维修公里数:</td>
				<td colspan="3">
					<ww:property value="search.maintain_mileage"/> 单位：公里
				</td>
			</tr>
			<tr>
				<td align='right'>维修原因:</td>
				<td colspan="3">
					<ww:property value="search.maintain_excuse"/>
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
