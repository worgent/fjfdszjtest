<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="2" border="0">
	<tbody>
		<tr>
			<th height="25" colspan="2">样式
			  <input type="checkbox" id="border-style" />
		    全部相同</th>
			<th height="25">宽度 <input name="checkbox" type="checkbox" id="border-width" />
			全部相同</th>
			<th>颜色 <input name="checkbox2" type="checkbox" id="border-color" /> 全部相同</th>
		</tr>
		<tr>
			<td width="40" height="25">上</td>
			<td width="142">
			<select id="border-top-style"  eop_type="widget_border">
			<jsp:include page="border_style.jsp"></jsp:include>
			</select>
			</td>
			<td width="163" height="25">
			
			<select  eop_type="widget_border" id="border-top-width">
				<option value="0px" selected="selected">0px</option>
				<option value="1px">1px</option>
				<option value="2px">2px</option>
				<option value="3px">3px</option>
				<option value="4px">4px</option>
				<option value="5px">5px</option>
				<option value="6px">6px</option>
				<option value="7px">7px</option>
				<option value="8px">8px</option>
				<option value="9px">9px</option>
				<option value="10px">10px</option>
			</select>
			
			  像素</td>
			<td width="133">
			<div class="colorSelector">
				<div  class="enable"></div>
				<input type="text" class="text"  id="border-top-color" eop_type="widget_border" /><input class="btn" type="button" value="透明" />
			</div>
		   </td>
		</tr>
		<tr>
			<td height="25">下</td>
			<td>
			
			<select   id="border-bottom-style" eop_type="widget_border">
				<jsp:include page="border_style.jsp"></jsp:include>
			</select>
			
			</td>
			<td><select  eop_type="widget_border" id="border-bottom-width">
				<option value="0px" selected="selected">0px</option>
				<option value="1px">1px</option>
				<option value="2px">2px</option>
				<option value="3px">3px</option>
				<option value="4px">4px</option>
				<option value="5px">5px</option>
				<option value="6px">6px</option>
				<option value="7px">7px</option>
				<option value="8px">8px</option>
				<option value="9px">9px</option>
				<option value="10px">10px</option>
			</select>
		    像素</td>
			<td>
			
			<div class="colorSelector">
			<div class="enable"></div><input class="text"  type="text" id="border-bottom-color" eop_type="widget_border" /><input class="btn" type="button" value="透明" />
			</div>			</td>
		</tr>
		<tr>
			<td height="25">左</td>
			<td>
			<select id="border-left-style" eop_type="widget_border">
				<jsp:include page="border_style.jsp"></jsp:include>
			</select>			</td>
			<td><select  eop_type="widget_border" id="border-left-width">
				<option value="0px" selected="selected">0px</option>
				<option value="1px">1px</option>
				<option value="2px">2px</option>
				<option value="3px">3px</option>
				<option value="4px">4px</option>
				<option value="5px">5px</option>
				<option value="6px">6px</option>
				<option value="7px">7px</option>
				<option value="8px">8px</option>
				<option value="9px">9px</option>
				<option value="10px">10px</option>
			</select>
		    像素</td>
			<td>
			<div class="colorSelector">
			<div class="enable"></div><input class="text" type="text" id="border-left-color" eop_type="widget_border" /><input class="btn" type="button" value="透明" />
			</div>			</td>
		</tr>
		<tr>

			<td height="25">右</td>
			<td>
			<select  id="border-right-style" eop_type="widget_border">
				<jsp:include page="border_style.jsp"></jsp:include>
			</select>
			</td>
			<td><select  eop_type="widget_border" id="border-right-width">
				<option value="0px" selected="selected">0px</option>
				<option value="1px">1px</option>
				<option value="2px">2px</option>
				<option value="3px">3px</option>
				<option value="4px">4px</option>
				<option value="5px">5px</option>
				<option value="6px">6px</option>
				<option value="7px">7px</option>
				<option value="8px">8px</option>
				<option value="9px">9px</option>
				<option value="10px">10px</option>
			</select>
		    像素</td>
			<td>
			<div class="colorSelector">
				<div class="enable"></div><input type="text" class="text"  id="border-right-color" eop_type="widget_border" /><input class="btn" type="button" value="透明" />
			</div>		
				</td>
		</tr>
	</tbody>
</table>
