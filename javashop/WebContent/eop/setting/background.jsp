<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table width="413" height="139" border="0" cellpadding="2" cellspacing="0" >
	<tbody>
		<tr>
			<td height="25">背景颜色：</td>
			<td>
			
			<div class="colorSelector">
			<div  class="enable"></div><input type="text" id="background-color" eop_type="widget_background" />
			<input class="btn" type="button" value="透明" />
			</div>
			
			</td>
			<td height="25">&nbsp;</td>
		</tr>
		<tr>
			<td width="77" height="25">背景图像：</td>
		  <td width="199"><input type="text" id="background-image" eop_type="widget_background"  /><input type="button" class="back_img_clean" value="清空" /> </td>
			<td width="125" height="25">浏览</td>
	    </tr>
		<tr>
			<td height="25">重复：</td>
			<td>
			<select id="background-repeat"  eop_type="widget_background">
				<option  value="repeat">重复</option>
				<option value="no-repeat">不重复</option>
				<option value="repeat-x">横向重复</option>
				<option value="repeat-y">纵向重复</option>
			</select>			
			</td>
			<td>&nbsp;</td>
	    </tr>

		<tr>
			<td height="25">附件 ：</td>
			<td>
			<select id="background-attachment"  eop_type="widget_background">
				<option value="fixed">固定</option>
				<option selected value="scroll">滚动</option>
			</select>			
			</td>
			<td>&nbsp;</td>
	    </tr>
	    	    
		<tr>
			<td height="25">位置：</td>
			<td><input type="text" id="background-position" eop_type="widget_background" /></td>
			<td>像素</td>
	    </tr>

	</tbody>
</table>
