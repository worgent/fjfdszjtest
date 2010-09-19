<#if (actionErrors?exists && actionErrors?size > 0)>
<div class="errorMessage">
	<#list actionErrors as error>
		${error}
	</#list>
	</div>
</#if>
