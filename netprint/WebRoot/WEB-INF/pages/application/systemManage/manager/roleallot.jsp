<%@page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="/error.jsp" %>
<%@include file="/common/taglibs.jsp"%>

<script language="javascript" src="/themes/default/js/optiontransferselect.js"></script>
<ww:action name="'select'" id="select"></ww:action>
<ww:set name="role" value="''"/>
<ww:set name="roleId" value="''"/>
<ww:iterator value="roleList">
	<ww:if test="0==checked">
		<ww:set name="role" value="#role+\"<option value='\"+ role_id +\"'>\"+role_name+\"</option>\""/>
	</ww:if>
	<ww:elseif test="1==checked">
		<ww:set name="roleId" value="#roleId+\"<option value='\"+ role_id +\"'>\"+role_name+\"</option>\""/>
	</ww:elseif>
</ww:iterator>

<form action="/system/manage/manager.shtml?actionType=roleAllot" method="post" onsubmit="customOnsubmit();">

<br>
<div style="wdith:98%" align="center">
	<INPUT type="hidden" name="userInfo.staffId" value="<ww:property value='userInfo.staffId'/>">
	<INPUT type="hidden" name="userInfo.staffNo" value="<ww:property value='userInfo.staffNo'/>">
	<INPUT type="hidden" name="userInfo.staffName" value="<ww:property value='userInfo.staffName'/>">
	人员工号：${param['userInfo.staffNo']}&nbsp;&nbsp;人员姓名：${param['userInfo.staffName']}
</div>
<br>

<table width="400"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td rowspan="2" align="center">
    	<select name="leftSideCartoonCharacters" size="15" id="exampleSubmit_leftSideCartoonCharacters" multiple="multiple">
    		<option value="headerKey">--- 未分配的角色 ---</option>
    		<option value=""></option>
			<ww:property value="#role"/>
		</select>
	</td>
    <td align="center"><INPUT type="button" value="&lt;&lt;" name="gl" onclick="moveSelectedOptions(document.getElementById('exampleSubmit_rightSideCartoonCharacters'), document.getElementById('exampleSubmit_leftSideCartoonCharacters'), false, 'doubleHeaderKey', '')" ></td>
    <td rowspan="2" align="center">
    	<select name="roleId" size="15"	multiple="multiple" id="exampleSubmit_rightSideCartoonCharacters">
    		<option value="doubleHeaderKey">--- 已分配的角色 ---</option>
		    <option value=""></option>
			<ww:property value="#roleId"/>
		</select>
	</td>
  </tr>
  <tr>
    <td align="center"><INPUT type="button" value="&gt;&gt;" name="gr" onclick="moveSelectedOptions(document.getElementById('exampleSubmit_leftSideCartoonCharacters'), document.getElementById('exampleSubmit_rightSideCartoonCharacters'), false, 'headerKey', '')" /></td>
  </tr>
  <tr>
    <td colspan="3" height="40" align="center">
    	<INPUT type="submit" value="保存">&nbsp;
  	</td>
  </tr>
</table>
</form>
<br>
<div>
	<font color="red">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:当前人员可将自己创建的"自定义角色"分配给其它管理员,<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;也可将自己所拥有的"系统默认角色"分配给其它管理员
	</font>
</div>
<script language="javascript" src="/themes/default/js/optiontransferselect.js"></script>
<script language="javascript">
	function customOnsubmit() {
		// Code that will auto-select optiontransferselect elements upon containing form submission
		// auto-select optiontrasferselect (left side) with id exampleSubmit_leftSideCartoonCharacters
		//var selectObj = document.getElementById("exampleSubmit_leftSideCartoonCharacters");
		//selectAllOptions(selectObj);
		//selectUnselectMatchingOptions(selectObj, null, "unselect", false, "key");
		//selectUnselectMatchingOptions(selectObj, "headerKey", "unselect", false, "key");
		// auto-select optiontransferselect (right side) with id exampleSubmit_rightSideCartoonCharacters
		var doubleSelectObj = document.getElementById("exampleSubmit_rightSideCartoonCharacters");
		selectAllOptions(doubleSelectObj);
		selectUnselectMatchingOptions(doubleSelectObj, null, "unselect", false, "key");
		selectUnselectMatchingOptions(doubleSelectObj, "doubleHeaderKey", "unselect", false, "key")
	}
</script>