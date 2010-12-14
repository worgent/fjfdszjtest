<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<script type="text/javascript">
<!--
process_request = "加载系统设置插件...";
showLoader();
Event.observe(window, "load", function() {
   new ui.utils.tabForm("tab-form");
   hideLoader();
});
//-->
</script>
<c:forEach var="script" items="${scripts}">
	<script language="JavaScript" src="${ctx }${script }"></script>
</c:forEach>
<c:forEach var="style" items="${styles}">
<link href="${ctx }${style }" rel="stylesheet" type="text/css" />
</c:forEach>

<script language="JavaScript" src="${ctx }/commons/js/ui.utils.js"></script>
<script language="JavaScript" src="${ctx }/commons/js/uc.js"></script>
<span id="title" value="系统设置"></span>
<form method="post"  name="theForm" action="setting!save.do" id="theForm">

	<div class="tab-form" id="tab-form" style="display: block;">
		<div class="tab_bar">
			<ul class="tab">
				<c:forEach var="tab" items="${tabs}">
					<li id="goods-${tab.key }-li">
						<a href="#">${tab.value }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="input">
			<div class="tab-page">
	
				<html:out plugin_type="setting_input"></html:out>
	
			</div>
		</div>
		<p class="submitlist">
			<input name="submit" type="submit" class="btn" value=" 确定 " />
			<input name="reset" type="reset" class="btn" value=" 重置 " />
		</p>

	</div>

</form>
