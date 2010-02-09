<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 派车申请信息
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					查看派车申请信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>	
				<td align='right' width="17%">派车类型:</td>
				<td width="33%">
					<ww:property value="search.expediteapply_type_name"/>	
				</td>						
				<td align='right'>预计用车时间:</td>
				<td>	
				    <ww:property value="search.intending_date"/>				
				</td>
			</tr>		
			<tr>
				<td align='right' width="17%" >使用单位:</td>
				<td width="33%">
				  <ww:property value="search.dept_name"/>
				</td>		
				<td align='right'>运达地点:</td>
				<td>
				   <ww:property value="search.destination_local"/>
				</td>		
			</tr>
			<tr>
		    	<td align='right' width="17%">用车人:</td>
				<td>
				    <ww:property value="search.use_man"/>		     				  
				</td>
				<td align='right'>用车人联系人方式:</td>				
				<td>
				    <ww:property value="search.use_mobile"/>
				</td>			
			</tr>
            <tr>   
				<td align='right'>用车事由:</td>
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
	<table class='simple' style='width: 80%' align='center' id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='4'>审批流程记录</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>审批流程名称</td>
				<td align='center'>审批人员</td>
				<td align='center'>审批时间</td>
				<td align='center'>审批建议</td>			
			</tr>
			<ww:iterator value="flowerTaskList" status="flowerTask">               		
	        	<tr>   
	        		<td  valign="top" align='center'>
						<ww:property value="flowernode_name"/>
					</td>
					<td  valign="top" align='center'>				
						<ww:property value="approve_name"/>
					</td>
					<td  valign="top" align='center'>
						<ww:property value="approve_date"/>					 
					</td>
					<td  valign="top" align='center'>					
						<ww:property value="approve_remark"/>
					</td>			 
	       		</tr>   
       		</ww:iterator>   
		</tbody>
	</table>
