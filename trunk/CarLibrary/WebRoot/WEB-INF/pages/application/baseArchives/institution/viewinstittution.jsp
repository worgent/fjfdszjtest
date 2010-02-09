<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<!--
 * 管理机购
 * @author zhengmh
 -->
 
<table class='simple' style='width:90%' align='center'>
	<thead>
		<tr>
			<th colspan="4">机构信息</th>
		</tr>
	</thead>
	<tbody>
		<tr>
		    <td align='right'>公司编号:</td>
			<td colspan="3">
				<ww:property value="search.company_code"/>
			</td>
		</tr>
		<tr>
			<td align='right' width="17%">公司名称:</td>
			<td width="33%">
				<ww:property  value="search.company_name"/>
			</td>
			<td align='right' width="17%">公司简称:</td>
			<td width="33%">
				<ww:property  value="search.company_easy_name"/>
			</td>
		</tr>
		<tr>
			<td align='right'>注册日期:</td>
			<td>
				<ww:property  value="search.reg_date"/>
			</td>
			<td align='right'>上级公司:</td>
			<td>
				<ww:property value="search.upCompany"/>
			</td>
		</tr>
		<tr>
			<td align='right'>联系人:</td>
			<td>
				<ww:property  value="search.link_man" />
			</td>
			<td align='right'>联系电话:</td>
			<td>
				<ww:property  value="search.link_phone" />
			</td>
		</tr>
		<tr>
			<td align='right'>公司性质:</td>
			<td>
				<ww:property value="search.companyProperty"/>
			</td>
			<td align='right'>公司行业:</td>
			<td>
				<ww:property value="search.CALLING2"/>
		    </td>
		</tr>
		<tr>
			<td align='right'>代理级别:</td>
			 <td colspan="3">
			 	<ww:property value="search.proxyLevel"/>
		    </td>
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