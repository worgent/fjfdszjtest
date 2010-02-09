<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	加油管理
 -->
<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					加油信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					加油时间:
				</td>
				<td width="33%">
					<ww:property value="search.put_on_date"/>
				</td>
				<td align='right' width="17%">
					加油车辆编号:
				</td>
				<td width="33%">
					<ww:property value="search.carNoId"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					加油站:
				</td>
				<td>
					<ww:property value="search.gasStation"/>
			    </td>				
				<td align='right'>
					加油类型:
				</td>
				<td>
					<ww:property value="search.putOnType"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					上次加油里程:
				</td>
				<td>
					<ww:property value="search.last_put_on_mileage"/> 单位：公里
				</td>
				<td align='right'>
					加油时公里数:
				</td>
				<td>
					<ww:property value="search.put_on_mileage"/> 单位：公里
				</td>
			</tr>	
			<tr>
				<td align='right'>
					行驶里程:
				</td>
				<td>
					<ww:property value="search.run_mileage"/> 单位：公里
				</td>
				<td align='right'>
					油箱总容量:
				</td>
				<td>
				    <ww:property value="search.oil_total"/> 单位：升
				</td>
			</tr>
		   <tr>
				<td align='right'>
					上次加油量 :
				</td>
				<td>
				   <ww:property value="search.last_put_on_oil"/> 单位：升
				</td>
				<td align='right'>
					上次存油量:
				</td>
				<td>
				  <ww:property value="search.last_put_on_exoil"/> 单位：升
				</td>
			</tr>
			
			<tr>
				<td align='right'>
					当前加油量:
				</td>
				<td>
				  <ww:property value="search.put_on_oil"/> 单位：升
				</td>
				<td align='right'>
					当前存油量:
				</td>
				<td>
				  <ww:property value="search.put_on_exoil"/> 单位：升
				</td>
			</tr>
			
			<tr>
			
				<td align='right'>
					单价:
				</td>
				<td>
					<ww:property value="search.price"/> 单位：元
				</td>
				<td align='right'>
					金额:
				</td>
				<td>
					<ww:property value="search.charge"/> 单位：元
				</td>	
			</tr>		
			<tr>
				<td align='right'>
					百公里油耗:
				</td>
				<td>
				<ww:property value="search.consume_100"/>
				</td>
				<td align='right'>
					加油量:
				</td>
				<td>
					<ww:property value="search.put_on_num"/>单位：升
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
					<ww:property value="search.cityName"/>
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
