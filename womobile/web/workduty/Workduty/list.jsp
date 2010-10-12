<%@page import="com.qzgf.application.workduty.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Workduty.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/workduty/Workduty/list.do"/>" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Workduty.ALIAS_TITLE%></td>		
					<td>
						<input value="${query.title}" id="title" name="title" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_DETAIL%></td>		
					<td>
						<input value="${query.detail}" id="detail" name="detail" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_ADJUNCT%></td>		
					<td>
						<input value="${query.adjunct}" id="adjunct" name="adjunct" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_TARGET%></td>		
					<td>
						<input value="${query.target}" id="target" name="target" maxlength="10"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Workduty.ALIAS_FORMAT%></td>		
					<td>
						<input value="${query.format}" id="format" name="format" maxlength="4"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_PROID%></td>		
					<td>
						<input value="${query.proid}" id="proid" name="proid" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_ATTITUDE%></td>		
					<td>
						<input value="${query.attitude}" id="attitude" name="attitude" maxlength="300"  class=""/>
					</td>
					<td class="tdLabel"><%=Workduty.ALIAS_GRADE%></td>		
					<td>
						<input value="${query.grade}" id="grade" name="grade" maxlength="10"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Workduty.ALIAS_ISEND%></td>		
					<td>
						<input value="${query.isend}" id="isend" name="isend" maxlength="2"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/workduty/Workduty/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/workduty/Workduty/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/workduty/Workduty/delete.do','items',document.forms.queryForm)"/>
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
				<th sortColumn="title" ><%=Workduty.ALIAS_TITLE%></th>
				<th sortColumn="detail" ><%=Workduty.ALIAS_DETAIL%></th>
				<th sortColumn="adjunct" ><%=Workduty.ALIAS_ADJUNCT%></th>
				<th sortColumn="target" ><%=Workduty.ALIAS_TARGET%></th>
				<th sortColumn="format" ><%=Workduty.ALIAS_FORMAT%></th>
				<th sortColumn="proid" ><%=Workduty.ALIAS_PROID%></th>
				<th sortColumn="attitude" ><%=Workduty.ALIAS_ATTITUDE%></th>
				<th sortColumn="grade" ><%=Workduty.ALIAS_GRADE%></th>
				<th sortColumn="isend" ><%=Workduty.ALIAS_ISEND%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.title}'/>&nbsp;</td>
				<td><c:out value='${item.detail}'/>&nbsp;</td>
				<td><c:out value='${item.adjunct}'/>&nbsp;</td>
				<td><c:out value='${item.target}'/>&nbsp;</td>
				<td><c:out value='${item.format}'/>&nbsp;</td>
				<td><c:out value='${item.proid}'/>&nbsp;</td>
				<td><c:out value='${item.attitude}'/>&nbsp;</td>
				<td><c:out value='${item.grade}'/>&nbsp;</td>
				<td><c:out value='${item.isend}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/workduty/Workduty/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/workduty/Workduty/edit.do?id=${item.id}&">修改</a>
					<a href="${ctx}/workduty/Workduty/start.do?deploymentId=${item.levelid}&">启动流程</a>
					<a href="${ctx}/workduty/Workduty/remove.do?deploymentId=${item.deploymentid}&">移除流程</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
		
		<!-- 流程实例 -->	
		<table border="1" width="100%">
			<caption>
				流程实例
			</caption>
			<thead>
				<tr>
					<td>
						id
					</td>
					<td>
						activity
					</td>
					<td>
						state
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="processInstanceList">
					<tr>
						<td>${id }</td>
						<td><s:property value="findActiveActivityNames()"/>  </td>
						<td>${state }</td>
						<td>
							<a href="${ctx}/workduty/Workduty/view.do?deploymentId=${id}&">view</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 待办任务 -->	
		<table border="1" width="100%">
			<caption>
				待办任务
			</caption>
			<thead>
				<tr>
					<td>
						id
					</td>
					<td>
						name
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="taskList">
				<tr>
					<td>${id }</td>
					<td>${name }</td>
					<td>
						<!-- getFormResourceName从xml文件中取值 -->
						<a href="${formResourceName }?id=${id}">view</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
				

	</div>
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

