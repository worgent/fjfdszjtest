<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!--
	车辆信息管理 
 -->
<table class='simple' style='width:80%' align='center'>
	<thead>
		<tr>
			<th colspan='4'>车辆信息</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align='right' width="17%">车牌编号(车牌号):</td>
			<td width="33%" colspan="3"><ww:property value="search.car_no_code"/></td>
		</tr>
		<tr>
			<td align='right'>所属公司:</td>
			<td><ww:property value="search.belongCompany"/></td>	
			<td align='right' width="17%">终端类型:</td>
			<td width="33%"><ww:property value="search.terminalType"/></td>
		</tr>
		<tr>
			<td align='right'>车载电话:</td>
			<td><ww:property value="search.car_phone"/></td>
			<td align='right'>车辆颜色:</td>
			<td><ww:property value="search.color2"/></td>	
		</tr>
		<tr>
			<td align='right'>行驶里程初始:</td>
			<td><ww:property value="search.run_mileage_init"/> 单位：米</td>
			<td align='right'>当前行驶总里程:</td>
			<td><ww:property value="search.curr_run_mileage"/> 单位：米</td>
		</tr>
		<tr>
			<td align='right'>安装时间:</td>
			<td><ww:property value="search.install_date"/></td>
			<td align='right'>购买日期:</td>
			<td><ww:property value="search.buy_date"/></td>	
		</tr>
		<tr>				
			<td align='right'>所属地市:</td>
			<td>
				<ww:property value="search.cityId"/>
			</td>
			<td align='right'>油箱总容量:</td>
			<td>
				<ww:property value="search.oil_total"/> 单位：升
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
<div style="color:red">
	&nbsp;&nbsp;&nbsp;&nbsp;注：“行驶里程初始”为车辆信息登记时的抄表里程值。
</div>
	
<br>
<table id="equipmentTab" class='simple' style='width:80%' align='center'>
	<thead>
		<tr>
			<th colspan="4">设备信息</th>
		</tr>
	</thead>
	<tbody id="equipmentBody">
		<tr>
			<td align='center' width="20%">设备编号</td>
			<td align='center' width="20%">设备名称</td>
			<td align='center'>备注</td>
			<td align='center' width="8%">
				<a href="javascript:fun_add()">[添加]</a>
			</td>
		</tr>
		<ww:iterator value="carFixingList">
			<tr>
				<td align='center' width="20%">
					<ww:property value="fixing_code"/>
				</td>
				<td align='center' width="20%"><ww:property value="fixing_name"/></td>
				<td align='center'><ww:property value="memo"/></td>
				<td align='center' width="8%">
					<A style='cursor:hand' onclick='del_row();'>删除</a>
				</td>
			</tr>
		</ww:iterator>
	</tbody>
	<tfoot id="equipmentFoot">
	</tfoot>
</table>