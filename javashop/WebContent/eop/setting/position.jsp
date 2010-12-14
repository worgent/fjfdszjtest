<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String isbox = request.getParameter("isbox");

%>	
<table width="469" height="125" border="0" cellpadding="2" cellspacing="0" >
	<tbody>
		<tr>
		  <td height="25">浮动：</td>
		  <td>
		  
		  <select   id="float"  eop_type="widget_position">
		    <option value="">无</option>
            <option value="left">左对齐</option>
            <option  value="right">右对齐</option>
          </select>		  </td>
		  <td>清除：</td>
		  <td height="25">
		  <select id="clear"  eop_type="widget_position">
		    <option value="">无</option>
            <option value="left">左对齐</option>
            <option  value="right">右对齐</option>
            <option  value="both">右对齐</option>			
          </select>		  </td>
	  </tr>
	  
	  <% if( "yes".equals(isbox) ){ %>
 
		<tr>
			<td height="25">宽：</td>
			<td><input type="text" id="width" eop_type="widget_position"/></td>
			<td>高：</td>
			<td height="25"><input type="text" id="height"  eop_type="widget_position" /></td>
		</tr>
		<%} %>
		<tr>
			<td width="42" height="25">上：</td>
			<td width="142"><input type="text" id="top"  eop_type="widget_position" /></td>
			<td width="46">下：</td>
			<td width="187" height="25"><input type="text" id="bottom"  eop_type="widget_position" /></td>
	    </tr>
		<tr>
			<td height="25">左：</td>
			<td><input type="text" id="left"  eop_type="widget_position" /></td>
			<td>右：</td>
			<td><input type="text" id="right"  eop_type="widget_position" /></td>
	    </tr>
	</tbody>
</table> 
 