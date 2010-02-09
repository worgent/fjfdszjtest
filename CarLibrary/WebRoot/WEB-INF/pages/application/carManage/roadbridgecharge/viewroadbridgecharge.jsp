<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	过路过桥费用管理
 -->
<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					过路过桥费用信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%" colspan="3">
					<ww:property value="search.car_no_code"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">收费类型:</td>
				<td width="33%">
					<ww:property value="search.charge_type_desc"/>
				</td>
				<td align='right' width="17%">收费时间:</td>
				<td width="33%">
					<ww:property value="search.charge_date"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">收费标准:</td>
				<td>
					<ww:property value="search.charge_standard"/>
				</td>
				<td align='right' width="17%">收费点:</td>
				<td>
					<ww:property value="search.charge_address"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">费用:</td>
				<td colspan="3">
					<ww:property value="search.charge"/> 单位：元
				</td>
			</tr>
			<tr>	
			    <td align='right'>备注：</td>
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
