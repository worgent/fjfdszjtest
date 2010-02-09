<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<!-- 
	设备管理
 -->
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>设备信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>设备编号:</td>
				<td width="33%">
					<ww:property value="search.fixing_code"/>
				</td>
				<td align='right' width="17%">设备名称:</td>
				<td width="33%">
					<ww:property value="search.fixing_name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注：</td>
				<td colspan='3'>
					<ww:property value="search.memo"/>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	
	
	<table id='paramTab' class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan="3">设备保养参数</th>
			</tr>
			<tr>
				<td align='center' width='5%'>序号</td>
				<td align='center' width='15%'>参数类型</td>
				<td align='center' width='30%'>参数值</td>
			</tr>
		</thead>
		<tbody id='paramBody'>
				<ww:iterator value="equipentParamList" status="equipentParam">    
					<tr>
						<td align='center'>1</td>
						<td align='center'><ww:property value="param_type_desc"/></td>
						<td align='center'>
							<ww:property value="param_value"/>
						</td>
					</tr>
				</ww:iterator>
		</tbody>
		<tfoot id='paramFoot'>
			<tr>
				<td colspan="4"></td>
			</tr>
		</tfoot>
	</table>
	
