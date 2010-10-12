<%@page import="com.womobile.task.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=WoTaskfield.TABLE_ALIAS%> 维护</title>
	
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm" action="<c:url value="/task/WoTaskfield/list.do"/>" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD1%></td>		
					<td>
						<input value="${query.field1}" id="field1" name="field1" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD2%></td>		
					<td>
						<input value="${query.field2}" id="field2" name="field2" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD3%></td>		
					<td>
						<input value="${query.field3}" id="field3" name="field3" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD4%></td>		
					<td>
						<input value="${query.field4}" id="field4" name="field4" maxlength="30"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD5%></td>		
					<td>
						<input value="${query.field5}" id="field5" name="field5" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD6%></td>		
					<td>
						<input value="${query.field6}" id="field6" name="field6" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD7%></td>		
					<td>
						<input value="${query.field7}" id="field7" name="field7" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD8%></td>		
					<td>
						<input value="${query.field8}" id="field8" name="field8" maxlength="30"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD9%></td>		
					<td>
						<input value="${query.field9}" id="field9" name="field9" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=WoTaskfield.ALIAS_FIELD10%></td>		
					<td>
						<input value="${query.field10}" id="field10" name="field10" maxlength="30"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/task/WoTaskfield/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/task/WoTaskfield/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/task/WoTaskfield/delete.do','items',document.forms.queryForm)"/>
		<div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  
			  <tr>
				<th style="width:1px;"> </th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="field_1" ><%=WoTaskfield.ALIAS_FIELD1%></th>
				<th sortColumn="field_2" ><%=WoTaskfield.ALIAS_FIELD2%></th>
				<th sortColumn="field_3" ><%=WoTaskfield.ALIAS_FIELD3%></th>
				<th sortColumn="field_4" ><%=WoTaskfield.ALIAS_FIELD4%></th>
				<th sortColumn="field_5" ><%=WoTaskfield.ALIAS_FIELD5%></th>
				<th sortColumn="field_6" ><%=WoTaskfield.ALIAS_FIELD6%></th>
				<th sortColumn="field_7" ><%=WoTaskfield.ALIAS_FIELD7%></th>
				<th sortColumn="field_8" ><%=WoTaskfield.ALIAS_FIELD8%></th>
				<th sortColumn="field_9" ><%=WoTaskfield.ALIAS_FIELD9%></th>
				<th sortColumn="field_10" ><%=WoTaskfield.ALIAS_FIELD10%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="fieldId=${item.fieldId}&"></td>
				
				<td><c:out value='${item.field1}'/>&nbsp;</td>
				<td><c:out value='${item.field2}'/>&nbsp;</td>
				<td><c:out value='${item.field3}'/>&nbsp;</td>
				<td><c:out value='${item.field4}'/>&nbsp;</td>
				<td><c:out value='${item.field5}'/>&nbsp;</td>
				<td><c:out value='${item.field6}'/>&nbsp;</td>
				<td><c:out value='${item.field7}'/>&nbsp;</td>
				<td><c:out value='${item.field8}'/>&nbsp;</td>
				<td><c:out value='${item.field9}'/>&nbsp;</td>
				<td><c:out value='${item.field10}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/task/WoTaskfield/show.do?fieldId=${item.fieldId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/task/WoTaskfield/edit.do?fieldId=${item.fieldId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

