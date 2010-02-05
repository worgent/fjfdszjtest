<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 收件人信息管理增加与编辑 
 	 * @author zhengmh 
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					寄件人信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					寄件人用户代码:
				</td>
				<td width="33%">
				    <ww:property value="search.code"/>
				</td>
				<td align='right' width="17%">
					寄件人姓名:
				</td>
				<td width="33%">
					<ww:property value="search.name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					寄件人单位:
				</td>
				<td colspan=3>
				    <ww:property value="search.unit"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					寄件人所属国家:
				</td>
				<td>
					<ww:property value="search.nation"/>
				</td>
				<td align='right'>
					寄件人所属省份:
				</td>
				<td>
				    <ww:property value="search.provincename"/>
				</td>					
			</tr>
			<tr>
				<td align='right'>
					寄件人所属地市:
				</td>
				<td>
				    <ww:property value="search.cityname"/>
				</td>
				<td align='right'>
					寄件人所属区县:
				</td>
				<td>
				   <ww:property value="search.countyname"/>
				</td>							
			</tr>
            <tr>
				<td align='right'>发件人详细地址:</td>
				<td colspan="3">
				    <ww:property value="search.address"/>
				</td>
			</tr>
			<tr>
				<td align='right'>发件人联系电话:</td>
				<td>
				    <ww:property value="search.tel"/>
				</td>
				<td align='right'>邮编:</td>
				<td>
					<ww:property value="search.post"/>
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
