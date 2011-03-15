<#assign itemCount = 0/>
<#if parameters.list?exists>
<table>
     <@s.iterator value="parameters.list">
         <#assign itemCount = itemCount + 1/>
         <#if parameters.listKey?exists>
             <#assign itemKey = stack.findValue(parameters.listKey)/>
         <#else>
             <#assign itemKey = stack.findValue('top')/>
         </#if>
         <#if parameters.listValue?exists>
             <#assign itemValue = stack.findString(parameters.listValue)/>
         <#else>
             <#assign itemValue = stack.findString('top')/>
         </#if>
 <#assign itemKeyStr=itemKey.toString() />

 <#if (itemCount-1)%3 == 0>
 <tr>
 </#if>
 <td width='100' nowrap="nowrap">
 <input type="checkbox" name="${parameters.name?html}" value="${itemKeyStr?html}" id="${parameters.name?html}-${itemCount}"<#rt/>
         <#if tag.contains(parameters.nameValue, itemKey)>
  checked="checked"<#rt/>
         </#if>
         <#if parameters.disabled?default(false)>
  disabled="disabled"<#rt/>
         </#if>
         <#if parameters.title?exists>
  title="${parameters.title?html}"<#rt/>
         </#if>
         <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
         <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
 />
 <label for="${parameters.name?html}-${itemCount}" class="checkboxLabel">${itemValue?html}</label>
 </td>
 <#if itemCount%3 == 0>
 </tr>

 </#if>
 
     </@s.iterator>
     </table>
</#if>

<input type="hidden" id="__multiselect_${parameters.id?html}" name="__multiselect_${parameters.name?html}" value=""<#rt/>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
 
</#if>

 />
