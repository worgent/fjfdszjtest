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
				车辆维修申请信息
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
			<td>
				<ww:property value="search.maintain_mileage"/> 单位：公里
			</td>
			<td align='right'>
				所属地市:
			</td>
			<td>
				<ww:property value="search.city_name"/>
			</td>
		</tr>
		<tr>
			<td align='right'>维修原因:</td>
			<td colspan="3">
				<ww:property value="search.maintain_excuse"/>
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
	</tbody>
</table>
<br>
<table class='simple' style='width: 80%' align='center'>
	<thead>
		<tr>
			<th colspan='4'>
				车辆维修登记信息
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align='right' width="17%">维修完毕时间:</td>
			<td width="33%">
				<ww:property value="search.maintain_end_date"/>
			</td>
			<td align='right' width="17%">金额:</td>
			<td width="33%">
				<ww:property value="search.charge"/> 单位：元
			</td>
		</tr>
		<tr>
			<td align='right'>维修结果:</td>
			<td colspan="3">
				<ww:property value="search.maintain_result"/>
			</td>
		</tr>
	</tbody>
</table>
<br>
<table id='detailTable' class='simple' style='width: 98%' align='center'>
	<thead>
		<tr>
			<th colspan='8'>
				维修明细
			</th>
		</tr>
		<tr>
			<th width='40'>行号</th>
			<th width='200'>维修项目</th>
			<th width='55'>计量单位</th>
			<th width='50'>数量</th>
			<th width='50'>单价</th>
			<th width='50'>金额</th>
			<th>备注</th>
		</tr>
	</thead>
	<ww:iterator value="search.detailList" status="detailList">
		<tr>
			<td align='center'><ww:property value="#detailList.count"/></td>
			<td align='center'><ww:property value="maintain_item"/></td>
			<td align='center'><ww:property value="unit"/></td>
			<td align='center'><ww:property value="num"/></td>
			<td align='center'><ww:property value="price"/></td>
			<td align='center'><ww:property value="charge"/></td>
			<td align='center'><ww:property value="memo"/></td>
		</tr>
	</ww:iterator>
</table>
