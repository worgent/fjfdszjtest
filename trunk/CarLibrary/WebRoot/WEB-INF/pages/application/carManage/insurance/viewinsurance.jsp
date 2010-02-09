<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 保险管理
 	 * @author zhengmh 
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					添加新保险信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>单据日期:</td>
				<td colspan="3">
					<ww:property value="search.booking_date"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					被保险人:
				</td>
				<td width="33%">
					<ww:property value="search.insurant"/>
				</td>
				<td align='right' width="17%">
					保险车辆编号:
				</td>
				<td width="33%">
					<ww:property value="search.carNoId"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					保险金额:
				</td>
				<td>
					<ww:property value="search.insurance_charge"/>
				</td>
				<td align='right'>
					保险开始日期:
				</td>
				<td>
					<ww:property value="search.begin_date"/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					保险费:
				</td>
				<td>
					<ww:property value="search.insurance_fee"/>
				</td>
				<td align='right'>
					保险结束日期:
				</td>
				<td>
					<ww:property value="search.end_date"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					保险公司:
				</td>
				<td colspan="3">
					<ww:property value="search.insuranceAgent"/>
				</td>				
			</tr>
			<tr>
				<td align='right'>
					用途:
				</td>
				<td colspan="3">
					<ww:property value="search.purpose"/>
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
