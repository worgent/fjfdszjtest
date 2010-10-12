<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=WoTask.TABLE_ALIAS%>督办</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/collect/WoTask/dispose.do" method="post">
		<input id="submitButton" name="submitButton" type="submit" value="督办" />
		<input type="button" value="返回列表" onclick="window.location='${ctx}/collect/WoTask/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<table class="formTable">
		<%@ include file="form_include_disable.jsp" %>
		</table>
	</s:form>
	
	<script>
		
		new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
			var finalResult = result;
			
			//在这里添加自定义验证
			
			return disableSubmit(finalResult,'submitButton');
		}});
	</script>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>