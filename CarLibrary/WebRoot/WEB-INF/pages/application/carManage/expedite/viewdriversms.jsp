<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	司机行车记录信息
 -->
<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					司机行车记录
				</th>
			</tr>
		</thead>
		<tbody>
		    <tr>
		    	<td align='right'>车牌号：</td>
				<td colspan="3" >
					<ww:property value="search.car_mark"/>
				</td>
		    </tr>
			<tr>
				<td align='right' width="17%">
					司机:
				</td>
				<td width="33%">
				    <ww:property value="search.driver_staffname"/>
				</td>
				<td align='right' width="17%">
					司机手机:
				</td>
				<td width="33%">
				    <ww:property value="search.driver_mobile"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					起点:
				</td>
				<td>
				    <ww:property value="search.first_locus"/>
				</td>
				<td align='right'>
					起点时间:
				</td>
				<td>
				    <ww:property value="search.first_date"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					终点:
				</td>
				<td>
				    <ww:property value="search.transfer_locur"/>
				</td>
				<td align='right'>
					终点时间:
				</td>
				<td>
				    <ww:property value="search.transfer_date"/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					司机初始里程:
				</td>
				<td>
				    <ww:property value="search.first_mileage"/>
				</td>
				<td align='right'>
					司机回车里程:
				</td>
				<td>
				    <ww:property value="search.transfer_mileage"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					司机行驶里程:
				</td>
				<td>
				    <ww:property value="search.run_mileage"/>
				</td>
				<td align='right'>
					加油量:
				</td>
				<td>
				    <ww:property value="search.use_oil_num"/>
				</td>				
			</tr>
			<tr>
				<td align='right'>
					加油金额:
				</td>
				<td>
					<ww:property value="search.use_oil_charge"/>
				</td>
				<td align='right'>
					路桥费:
				</td>
				<td>
				    <ww:property value="search.road_charge"/>
				</td>				
			</tr>	
			<tr>
				<td align='right'>
					住宿费:
				</td>
				<td>
				    <ww:property value="search.reside_charge"/>
				</td>
				<td align='right'>
					停车费:
				</td>
				<td>
					<ww:property value="search.stopcar_charge"/>
				</td>				
			</tr>	
			<tr>
				<td align='right'>
					洗车费:
				</td>
				<td>
					<ww:property value="search.washcar_charge"/>
				</td>	
				<td align='right'>
					所属地市:
				</td>
				<td >
					<ww:property value="search.city_name"/>
				</td>	
			</tr>
			<tr>
			    <td align='right'>备注：</td>
				<td colspan="3">
					<ww:property value="search.sms_memo"/>
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
