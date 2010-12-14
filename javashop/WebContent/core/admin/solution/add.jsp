<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate" method="post" action="solution!saveAdd.do"  enctype="multipart/form-data">
 
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >ID：</th>
       <td><input type="text" name="product.productid"    dataType="string" required="true" /> 
	   </td>
     </tr>
      <tr> 
       <th >名称：</th>
       <td><input type="text" name="product.product_name"    dataType="string" required="true" /> 
	   </td>
     </tr>
      <tr> 
       <th >描述：</th>
       <td> 
       <textarea  name="product.descript" style="width:300px;height:200px"></textarea>
	   </td>
     </tr>     
     <tr> 
       <th >解决方案：</th>
       <td><input type="file" name="zip"    dataType="string" required="true" />.zip格式
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="submit" type="submit"  value="添加" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
</div>