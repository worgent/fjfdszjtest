<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>

<script type="text/javascript" src="../js/DomainOperator.js">
</script>
<div class="input">
<form  class="validate"  action="userSite!editSave.do" method="post" name="theForm" id="theForm" enctype="multipart/form-data">
<table  class="form-table">
	<tr>
		<th><input type="hidden" name="eopSite.id"  value="${eopSite.id }" /><label class="text">网站名称：</label></th>
		<td><input type="text" name="eopSite.sitename" 
			value="${eopSite.sitename }" dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">网站LOGO：</label></th>
		<td><input type="file" name="cologo" id="cologo" size="45" /> <span
			class="notice-span" id="warn_brandlogo">请上传图片，做为网站的LOGO</span></td>
	</tr>

	<c:if test="${eopSite.logofile!=null }">
		<tr>
			<th>&nbsp;</td>
			<td><img src="${eopSite.logofile }" width="207"
				height="56" />&nbsp;</td>
		</tr>
	</c:if>
	<tr>
		<th><label class="text">网站ICO文件：</label></th>
		<td><input type="file" name="ico" id="ico" size="45" /> <span
			class="notice-span" id="warn_brandlogo">请上传网站的ico文件</span></td>
	</tr>
	
	<c:if test="${eopSite.icofile!=null }">
		<tr>
			<th><label class="text">&nbsp;</label></th>
			<td><img src="${eopSite.icofile }" width="16"
				height="16" />&nbsp;</td>
		</tr>
	</c:if>
	<tr>
		<th><label class="text">META_KEYWORDS：<br/>
				(页面关键词)：</label></th>
		<td><input type="text" name="eopSite.keywords" 
			value="${eopSite.keywords }" /></td>
	</tr>
	<tr>
		<th><label class="text">META_DESCRIPTION：<br/>
					(页面描述)</label></th>
		<td><textarea name="eopSite.descript" />${eopSite.descript }</textarea></td>
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


<div class="grid">
	<div class="toolbar">
		<ul>
			<li>
				<input type='hidden' name='siteid' id='siteid' value='${eopSite.id }'>
			<input type="text" name="domainname"  id="domainname"/>
			<a href="javascript:;"  id="btnAdd">新增域名</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
 
	<div id="list_wrapper" style="font-size: 12px;"></div>

	</td>
 
</div>

<script type="text/javascript">
$("form.validate").validate();
</script>

