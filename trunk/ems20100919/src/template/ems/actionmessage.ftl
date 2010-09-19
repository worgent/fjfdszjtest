<#if (actionMessages?exists && actionMessages?size > 0)>
	<div  class="actionMessage">
		<#list actionMessages as message>
			${message}
		</#list>
	</div>
</#if>
