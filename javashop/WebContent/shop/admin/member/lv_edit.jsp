<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="member!saveEditLv.do" name="theForm" id="theForm"  >
 <input type="hidden" name="lv.lv_id"  value="${lv.lv_id }" />
 
   <table cellspacing="1" cellpadding="3" width="100%">
     <tr>
       <th><label class="text">等级名称</label></th>
       <td width="27%"><input type="text" name="lv.name" id="name" maxlength="60" value="${lv.name }" dataType="string" required="true"/>       </td>
       <td width="58%" align="left"><div id="nameTip" style="width:80px;"></div></td>
     </tr>

     <tr>
       <th><label class="text">默认等级</label></th>
       <td  >
       <input type="radio" name="lv.default_lv"  value="0" <c:if test="${lv.default_lv == 0}">checked</c:if>  />否&nbsp;&nbsp;<input type="radio" name="lv.default_lv"  <c:if test="${lv.default_lv == 1}">checked</c:if>  value="1" />是
       </td>
       <td   align="left"><div id="default_lvTip" style="width:80px;"></div></td>
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
 <script type="text/javascript">
$("form.validate").validate();
</script>
</div>