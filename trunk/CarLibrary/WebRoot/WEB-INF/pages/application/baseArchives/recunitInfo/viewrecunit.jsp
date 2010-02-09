<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<%--
 * 来往单位信息管理
 * @author zhengmh 
--%>

<table class='simple' style='width:90%' align='center'>
	<thead>
		<tr>
			<th colspan="4">来往单位信息</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align='right' width="17%">往来单位编号:</td>
			<td width="33%"><ww:property value="search.coop_unit_code"/></td>
			<td align='right' width="17%">往来单位类别:</td>
			<td width="33%"><ww:property value="search.coopUnitType"/></td>
		</tr>
		<tr>
			<td align='right'>往来单位名称:</td>
			<td><ww:property value="search.coop_unit_name"/></td>
			<td align='right'>往来单位简称:</td>
			<td><ww:property value="search.unit_easy_name"/></td>
		</tr>
		<tr>
			<td align='right'>电话:</td>
			<td><ww:property value="search.phone"/></td>
			<td align='right'>传真:</td>
			<td><ww:property value="search.fax"/></td>
		</tr>
		<tr>
			<td align='right'>联系人:</td>
			<td><ww:property value="search.link_man"/></td>
			<td align='right'>联系人电话:</td>
			<td><ww:property value="search.link_man_phone"/></td>
		</tr>
		<tr>	
		    <td align='right'>备注：</td>
			<td colspan="3">
				<ww:property  value="search.memo"/>
			</td>
		</tr>
		<tr>				
			<td align='right'>所属地市:</td>
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