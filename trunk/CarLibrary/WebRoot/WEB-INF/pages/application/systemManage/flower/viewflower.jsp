<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
   * 流程定义
--%>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					查看流程定义
				</th>
			</tr>
		</thead>
		<tbody>
		    <tr>
				<td align='right'>流程编号:</td>				
				<td>
				  <ww:property value="search.flowerdefine_code"/>
				</td>
				<td align='right'>流程名称:</td>
				<td>
				  <ww:property value="search.flowerdefine_name"/>					
				</td>				
			</tr>
            <tr>
            	<td align='right'>版本:</td>
				<td>					
					<ww:property value="search.flowerdefine_versions"/>	*启用版本级最高的
				</td>	
				<td align='right'>所属地市:</td>
				<td>
				    <ww:property value="search.city_id"/>
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
				<th colspan='10'>流程结点配置信息</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>环节名称</td>
				<td align='center'>环节是否启用</td>
				<td align='center'>是否同一部门</td>
				<td align='center'>是否多人</td>			
				<td align='center'>审批人员</td>			
			</tr>
			<ww:iterator value="flowerNodeList" status="flowerNode">               		
	        	<tr>   
	        		<td  valign="top" align='center'>
						<ww:property value="flowernode_name"/>
					</td>
					<td  valign="top" align='center'>				
						<ww:property value="is_flowmust_name"/>
					</td>
					<td  valign="top" align='center'>
						<ww:property value="is_samedep_name"/>					 
					</td>
					<td  valign="top" align='center'>					
						<ww:property value="is_flowers_name"/>
					</td>	
					<td  valign="top" align='center'>					
						<ww:property value="approve_codearrayname"/>
					</td>
	       		</tr>   
       		</ww:iterator>   
		</tbody>
	</table>
