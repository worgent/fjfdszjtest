<#include "/macro.include"/>
<#include "/my_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entities;

import ${basepackage}.provider.${className}Provider;
import android.net.Uri;

public class ${className} {
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ ${className}Provider.CONTENT_URI + "/${table.sqlName}");
		
	//alias
	<#list table.columns as column>
	public static final String KEY_${column.constantName} = "${column.sqlName}";
	</#list>
}
