<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	财务单据信息
 -->
<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					财务单据信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">单据号:</td>
				<td width="33%">
					<ww:property value="search.finance_id"/>
				</td>
				<td align='right' width="17%">单据日期:</td>
				<td width="33%">
					<ww:property value="search.booking_date"/>
				</td>
			</tr>
			<tr>
				<td align='right'>源单类型:</td>
				<td>
					<ww:property value="search.source_order_type_name"/>
				</td>
				<td align='right'>源单号:</td>
				<td>
					<ww:if test="\"1\".equals(search.source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-加油费用', 'viewSourceOrder_PutOnSteamManage', '/carmanage/putonsteam/putonsteam.shtml?actionType=view&search.putonsteamId=<ww:property value="search.source_order_code"/>','NO')">
							<ww:property value="search.source_order_code"/>
						</a>
					</ww:if>
					<ww:if test="\"2\".equals(search.source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-维修费用', 'viewSourceOrder_MaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?actionType=view&search.maintainId=<ww:property value="search.source_order_code"/>','NO')">
							<ww:property value="search.source_order_code"/>
						</a>
					</ww:if>
					<ww:if test="\"3\".equals(search.source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-保养费用', 'viewSourceOrder_MaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?actionType=view&search.nurseId=<ww:property value="search.source_order_code"/>','NO')">
							<ww:property value="search.source_order_code"/>
						</a>
					</ww:if>
					<ww:if test="\"4\".equals(search.source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-过路过桥费用', 'viewSourceOrder_RoadBridgeCharge', '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=view&search.nurseId=<ww:property value="source_order_code"/>','NO')">
							<ww:property value="search.source_order_code"/>
						</a>
					</ww:if>
				</td>
			</tr>
			<tr>
				<td align='right'>车辆编号:</td>
				<td>
					<ww:property value="search.car_no_code"/>
				</td>
				<td align='right'>金额:</td>
				<td>
					<ww:property value="search.charge"/>
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