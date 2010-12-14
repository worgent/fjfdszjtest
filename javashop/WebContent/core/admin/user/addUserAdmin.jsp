<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<div class="input">
<form action="userAdmin!addSave.do"  class="validate" method="post" name="theForm" id="theForm" enctype="multipart/form-data">
<table  class="form-table">
	<tr>
		<th><label class="text">真实姓名：</label></th>
		<td><input type="text" name="eopUserAdmin.realname"  dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">管理员账号：</label></th>
		<td><input type="text" name="eopUserAdmin.username"  dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">联系电话：</label></th>
		<td><input type="text" name="eopUserAdmin.tel"   /></td>
	</tr>
	<tr>
		<th><label class="text">手机：</label></th>
		<td><input type="text" name="eopUserAdmin.mobile"   /></td>
	</tr>
	<tr>
		<th><label class="text">电子邮件：</label></th>
		<td><input type="text" name="eopUserAdmin.email"  dataType="email"  /></td>
	</tr>
	<tr>
		<th><label class="text">QQ：</label></th>
		<td><input type="text" name="eopUserAdmin.qq"   /></td>
	</tr>
	<tr>
	<td colspan=2 class="grid"><div class="toolbar">管理网站</div></td>
	</tr>
	<tr>
		<td colspan=2>
			<c:forEach var="item" items="${siteManagerList }">
			<input type="checkbox" name="sites" value="${item.id }" siteid ="${item.siteid }" />
				${item.sitename }&nbsp;
			</c:forEach>
		</td>
	</tr>

</table>
<script type="text/javascript">
$("form.validate").validate();
</script>

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