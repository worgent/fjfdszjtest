<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 违章管理
 	 * @author fangzl 
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					添加新违章信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					违章类型:
				</td>
				<td >
					<ww:property value="search.peccancy_type_desc"/>
				</td>
				<td align='right' width="17%">
					违章车辆编号:
				</td>
				<td width="33%">
					<ww:property value="search.car_no_code"/>
				</td>
			</tr>
			<tr>
				
				<td align='right'>
					违章日期:
				</td>
				<td>
					<ww:property value="search.peccancy_date"/>
				</td>
				<td align='right'>
					违章地点:
				</td>
				<td>
					<ww:property value="search.peccancy_address"/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					违章内容:
				</td>
				<td colspan="3">
					<ww:property value="search.peccancy_context"/>
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
