<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<script type="text/javascript">
$(function(){
	$("input[name='sites']").each(function(i){
		var chk= $(this);
		if(chk.attr("siteid")!=''){
			chk.attr("checked",true);
		}
	});
});
</script>
<div class="input">
<form action="userAdmin!editSave.do"  class="validate" method="post" >
<table  class="form-table">
	<tr>
		<th><input type="hidden" name="eopUserAdmin.id" id="eopUserAdmin.id"
			value="${eopUserAdmin.id }" /><label class="text">真实姓名：</label></th>
		<td><input type="text" name="eopUserAdmin.realname"  value="${eopUserAdmin.realname }" dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">管理员账号：</label></th>
		<td><input type="text" name="eopUserAdmin.username"  value="${eopUserAdmin.username }"
			dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">联系电话：</label></th>
		<td><input type="text" name="eopUserAdmin.tel"  value="${eopUserAdmin.tel }"
			dataType="tel"  /></td>
	</tr>
	<tr>
		<th><label class="text">手机：</label></th>
		<td><input type="text" name="eopUserAdmin.mobile"  value="${eopUserAdmin.mobile }"
		  /></td>
	</tr>
	<tr>
		<th><label class="text">电子邮件：</label></th>
		<td><input type="text" name="eopUserAdmin.email"  value="${eopUserAdmin.email }"
		  /></td>
	</tr>
	<tr>
		<th><label class="text">QQ：</label></th>
		<td><input type="text" name="eopUserAdmin.qq" value = "${eopUserAdmin.qq }"
			dataType="string" required="true" /></td>
	</tr>
	<tr>
	<td colspan=2 class="grid"><div class="toolbar">管理网站</div></td>
	</tr>
	<tr>
		<td colspan=2>
		<ul style="width:100%">
			<c:forEach var="item" items="${siteManagerList }">
			<li style="width:33%;float:left"><input type="checkbox" name="sites" value="${item.id }" siteid ="${item.siteid }" />
				${item.sitename }&nbsp;</li>
			</c:forEach>
			</ul>
		</td>
	</tr>

</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div> 
</form>
</div>
<script type="text/javascript">
$("form.validate").validate();
</script>