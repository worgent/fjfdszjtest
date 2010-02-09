<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='8'>员工信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>员工编号:</td>
				<td >
					<ww:property value="search.staff_code"/>
				</td>
				<td align='right'>系统用户:</td>
				<td >
					<ww:property value="search.operator_name"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">员工姓名:</td>			
				<td width="33%">
					<ww:property value="search.staff_name"/>
				</td>
				<td align='right' width="17%">性别:</td>
				<td width="33%">
					<ww:property value="search.sex2"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>证件类型:</td>
				<td>
					<ww:property value="search.certificateType"/>
			    </td>
			    <td align='right'>员工类型:</td>
				<td>
					<ww:property value="search.staffType"/>
				</td>
			</tr>
			<tr>
				<td align='right'>证件编号:</td>
				<td>
					<ww:property value="search.certificate_no"/>
				</td>
				<td align='right'>联系电话:</td>
				<td>
					<ww:property value="search.link_phone"/>
				</td>
			</tr>
			<tr>
				<td align='right'>所属公司:</td>
				<td>
					<ww:property value="search.belongCompany"/>
			    </td>
				<td align='right'>其他联系电话:</td>
				<td>
					<ww:property value="search.link_other"/>
				</td>   
			</tr>
			<tr>
				<td align='right'>所属部门:</td>
			    <td>
					<ww:property value="search.belongDept"/>
			    </td>	
			    <td align='right'>接收短信号码</td>
			    <td>
			    	<ww:property value="search.sms_phone"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>车牌号:</td>
				<td>
					<ww:property value="search.carNo"/>			    	
			    </td>
			    <td align='right'>IC卡编号:</td>
				<td>
					<ww:property value="search.Ic_Code"/>			    	
			    </td>
			</tr>
			<tr>
				<td align='right'>入职时间:</td>
				<td>
					<ww:property value="search.accession_date"/>
				</td>
			    <td align='right'>离职时间:</td>
			    <td>
			    	<ww:property value="search.dimission_date"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>通信地址:</td>
				<td colspan="3">
					<ww:property value="search.address"/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注：</td>
				<td  colspan="3">
					<ww:property value="search.memo"/>
				</td>
			</tr>	
			<tr>				
				<td align='right'>所属地市:</td>
				<td>
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
