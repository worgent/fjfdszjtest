<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate"   enctype="multipart/form-data">
	<div style="color:red">提示：导入解决方案会可能会覆盖当前站点的数据</div>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >选择导入文件</th>
       <td><input type="file" name="zip"    dataType="string" required="true" />.zip格式
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="button" type="button"  value="导入" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
</div>
<script>
function doImport(){
	$.Loading.show('正在导入，请稍侯...'); 
	$("form").ajaxSubmit({
		url:"solution!imported.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
			 	alert("导入成功");
			 	$.Loading.hide();
			 	$(this)[0].rest();
			}else{
				$.Loading.hide();
				alert("导入失败");
			}
			
		},
		error:function(){
			$.Loading.hide();
			alert("导出错误");
		}
	});	
}
$(function(){
	$(".submitBtn").click(function(){
		if(confirm("导入解决方案会可能会覆盖当前站点的数据，确认导入吗？")){
			doImport();
		}
	});
})
</script>