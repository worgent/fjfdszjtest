<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate" method="post" action="site!saveEdit.do" >
  <input type="hidden" name="id" value="${site.id }" />
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
   
     <tr> 
       <th >名称：</th>
       <td> ${site.sitename } 
	   </td>
     </tr>
      <tr> 
       <th >积分：</th>
       <td><input type="text" name="point"  value="${site.point }"   dataType="int" required="true" /> 
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="submit" type="submit"  value="修改" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
});
</script>