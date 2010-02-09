<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 派车管理
 	 * @author fangzl 
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					查看派车信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>派车日期:</td>
				<td colspan="3">
					<ww:property value="search.expedite_date"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					派车人:
				</td>
				<td width="33%">
					<ww:property value="search.expedite_man_name"/>
				</td>
				<td align='right' width="17%">
					用车人:
				</td>
				<td width="33%">
					<ww:property value="search.use_car_man_name"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					用车部门:
				</td>
				<td>
					<ww:property value="search.dept_name"/>
				</td>
				<td align='right'>
					车辆编号:
				</td>
				<td>
					<ww:property value="search.car_no_code"/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					驾驶员:
				</td>
				<td>
					<ww:property value="search.driver_nameex"/>
				</td>
				<td align='right'>
					驾驶员联系电话:
				</td>
				<td>
					<ww:property value="search.driver_mobile"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					预计用车时间:
				</td>
				<td>
					<ww:property value="search.intending_dateex"/>
				</td>
				<td align='right'>
					起始里程:
				</td>
				<td>
					<ww:property value="search.init_mileage"/>  单位：公里
				</td>
			</tr>
			<tr>
				<td align='right'>
					预计回程时间:
				</td>
				<td colspan="3">
					<ww:property value="search.intending_date"/>
				</td>				
			</tr>
			<tr>
				<td align='right'>
					用车事由:
				</td>
				<td colspan="3">
					<ww:property value="search.use_excuse"/>
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
	<br>
	<table class='simple' style='width: 98%' align='center' id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='10'>中途站点信息登记</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>出车地</td>
				<td align='center'>出车时间</td>
				<td align='center'>中转站</td>
				<td align='center'>中转站时间</td>			
			</tr>
			<ww:iterator value="roldInfoList" status="roldInfo">               		
	        	<tr>   
	        		<td  valign="top" align='center'>
						<ww:property value="first_locus"/>
					</td>
					<td  valign="top" align='center'>				
						<ww:property value="first_date"/>
					</td>
					<td  valign="top" align='center'>
						<ww:property value="transfer_locur"/>					 
					</td>
					<td  valign="top" align='center'>					
						<ww:property value="transfer_date"/>
					</td>			 
	       		</tr>   
       		</ww:iterator>   
		</tbody>
	</table>
