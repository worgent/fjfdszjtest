<%@page language="java" errorPage="/error.jsp" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312"%>
<%-- ¼ÓÈë±êÇ© --%>
<%@include file="/common/taglibs.jsp"%>
<table class="simple" height="474">
	<tr>
		<td rowspan="2" width="20%" id="tMenuList" valign="top" class="resize" empty="true" vertical="true"><iframe
			width="100%" height="100%" src="/system/manage/menutree.shtml" id="MenuListFrm"
			name="MenuListFrm" frameborder="0"></iframe></td>
		<td valign="top" id="tFunList" class="resize" empty="true" horizontal="true"><iframe width="100%" height="237"
			src="/system/manage/menu.shtml" id="FunListFrm" name="FunListFrm"
			frameborder="0"></iframe></td>
	</tr>
	<tr>
		<td id="tOpt" valign="top" height="100%"><iframe width="100%" height="260" src="" id="OptFrm"
			name="OptFrm" frameborder="0"></iframe></td>
	</tr>

</table>