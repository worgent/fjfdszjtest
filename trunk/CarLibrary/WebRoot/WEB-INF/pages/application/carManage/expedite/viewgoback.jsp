<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 回车管理
 	 * @author fangzl 
--%>
<ww:set name="expediteInfo" value="search.expediteInfo"/>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>派车信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">出车单据号:</td>
				<td colspan="3"><ww:property value="#expediteInfo.expedite_car_id"/></td>
			</tr>
			<tr>
				<td align='right' width="17%">派车人:</td>
				<td width="33%"><ww:property value="#expediteInfo.expedite_man_name"/></td>
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%"><ww:property value="#expediteInfo.car_no_code"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">用车人:</td>
				<td width="33%"><ww:property value="#expediteInfo.use_car_man_name"/></td>
				<td align='right' width="17%">用车部门:</td>
				<td width="33%"><ww:property value="#expediteInfo.dept_name"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">驾驶员:</td>
				<td width="33%"><ww:property value="#expediteInfo.driver_name"/></td>
				<td align='right' width="17%">派车日期:</td>
				<td width="33%"><ww:property value="#expediteInfo.expedite_date"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">起始里程:</td>
				<td width="33%"><ww:property value="#expediteInfo.init_mileage"/>  单位：公里</td>
				<td align='right' width="17%">预计回程时间:</td>
				<td width="33%"><ww:property value="#expediteInfo.intending_date"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%" >用车事由:</td>
				<td colspan="3"><ww:property value="#expediteInfo.use_excuse"/></td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class='simple' style='width: 98%' align='center'
		id="maintaindetailinfo">
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
	<br>
	
	<table class='simple' style='width: 98%' align='center'
		id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='8'>回车中途站点信息</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>起点</td>
				<td align='center'>出车时间</td>
				<td align='center'>起公里数</td>
				<td align='center'>目的地</td>
				<td align='center'>目的时间</td>
				<td align='center'>止公里数</td>
				<td align='center'>实际行驶公里数</td>	
				<td align='center'>实际用车时间</td>		
			</tr>
			<ww:iterator value="gobackRoldList" status="roldInfo">               		
        	<tr>   
        		<td  valign="top" align='center'>
					<ww:property value="first_locus"/>
				</td>
				<td  valign="top" align='center'>				
					<ww:property value="first_date"/>
				</td>
				<td  valign="top" align='center'>				
					<ww:property value="first_mileage"/>
				</td>
				<td  valign="top" align='center'>
					<ww:property value="transfer_locur"/>					 
				</td>
				<td  valign="top" align='center'>					
					<ww:property value="transfer_date"/>
				</td>
				<td  valign="top" align='center'>				
					<ww:property value="transfer_mileage"/>
				</td>
				<td  valign="top" align='center'>
					<ww:property value="run_mileage"/>
				</td>	
				<td  valign="top" align='center'>
					<ww:property value="run_time"/>
				</td>
				<!-- 		
				<td  valign="top" align='center'>
					<ww:property value="road_charge"/>				
				</td>
				<td  valign="top" align='center'>
					<ww:property value="use_oil_num"/>
				</td>
				<td  valign="top" align='center'>
					<ww:property value="use_oil_charge"/>
				</td>
				 -->			 
       		</tr>   
       		</ww:iterator>  
       	</tbody>
     </table>