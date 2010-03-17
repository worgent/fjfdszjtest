<#if (actionMessages?exists && actionMessages?size > 0)>
	<div color="red">
		<#list actionMessages as message>
			<span class="actionMessage">${message}</span><br/>
		</#list>
	</div>
</#if>
