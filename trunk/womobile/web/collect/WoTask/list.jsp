<%@page import="com.womobile.collect.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp"%>

<rapid:override name="head">
	<title><%=WoTask.TABLE_ALIAS%> 维护</title>

	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>"
		type="text/css" rel="stylesheet">
	<script type="text/javascript"
		src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm"
		action="<c:url value="/collect/WoTask/list.do"/>" method="get"
		style="display: inline;">
		<div class="queryPanel">
			<fieldset>
				<legend>
					搜索
				</legend>
				<table>
					<tr>
						<td class="tdLabel"><%=WoTask.ALIAS_TOPIC%></td>
						<td>
							<input value="${query.topic}" id="topic" name="topic"
								maxlength="30" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_GUIDELINE%></td>
						<td>
							<input value="${query.guideline}" id="guideline" name="guideline"
								maxlength="10" class="validate-integer max-value-2147483647" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_DESCRIPTION%></td>
						<td>
							<input value="${query.description}" id="description"
								name="description" maxlength="200" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FEEDBACKTYPE_ID%></td>
						<td>
							<input value="${query.feedbacktypeId}" id="feedbacktypeId"
								name="feedbacktypeId" maxlength="10"
								class="validate-integer max-value-2147483647" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><%=WoTask.ALIAS_EXAMINECYCLE_ID%></td>
						<td>
							<input value="${query.examinecycleId}" id="examinecycleId"
								name="examinecycleId" maxlength="10"
								class="validate-integer max-value-2147483647" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_STATUS_ID%></td>
						<td>
							<input value="${query.statusId}" id="statusId" name="statusId"
								maxlength="5" class="validate-integer max-value-2147483647" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD_ID%></td>
						<td>
							<input value="${query.fieldId}" id="fieldId" name="fieldId"
								maxlength="10" class="validate-integer max-value-2147483647" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD1%></td>
						<td>
							<input value="${query.field1}" id="field1" name="field1"
								maxlength="100" class="" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD2%></td>
						<td>
							<input value="${query.field2}" id="field2" name="field2"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD3%></td>
						<td>
							<input value="${query.field3}" id="field3" name="field3"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD4%></td>
						<td>
							<input value="${query.field4}" id="field4" name="field4"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD5%></td>
						<td>
							<input value="${query.field5}" id="field5" name="field5"
								maxlength="100" class="" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD6%></td>
						<td>
							<input value="${query.field6}" id="field6" name="field6"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD7%></td>
						<td>
							<input value="${query.field7}" id="field7" name="field7"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD8%></td>
						<td>
							<input value="${query.field8}" id="field8" name="field8"
								maxlength="100" class="" />
						</td>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD9%></td>
						<td>
							<input value="${query.field9}" id="field9" name="field9"
								maxlength="100" class="" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><%=WoTask.ALIAS_FIELD10%></td>
						<td>
							<input value="${query.field10}" id="field10" name="field10"
								maxlength="100" class="" />
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="handleControl">
				<input type="submit" class="stdButton" style="width: 80px"
					value="查询"
					onclick="getReferenceForm(this).action='${ctx}/collect/WoTask/list.do'" />
				<input type="submit" class="stdButton" style="width: 80px"
					value="新增"
					onclick="getReferenceForm(this).action='${ctx}/collect/WoTask/create.do'" />
				<input type="button" class="stdButton" style="width: 80px"
					value="删除"
					onclick="batchDelete('${ctx}/collect/WoTask/delete.do','items',document.forms.queryForm)" />
				<div>
				</div>

				<div class="gridTable">

					<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>

					<table width="100%" border="0" cellspacing="0" class="gridBody">
						<thead>

							<tr>
								<th style="width: 1px;">
								</th>
								<th style="width: 1px;">
									<input type="checkbox"
										onclick="setAllCheckboxState('items',this.checked)">
								</th>

								<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
								<th sortColumn="topic"><%=WoTask.ALIAS_TOPIC%></th>
								<th sortColumn="guideline"><%=WoTask.ALIAS_GUIDELINE%></th>
								<th sortColumn="description"><%=WoTask.ALIAS_DESCRIPTION%></th>
								<th sortColumn="feedbacktype_id"><%=WoTask.ALIAS_FEEDBACKTYPE_ID%></th>
								<th sortColumn="examinecycle_id"><%=WoTask.ALIAS_EXAMINECYCLE_ID%></th>
								<th sortColumn="status_id"><%=WoTask.ALIAS_STATUS_ID%></th>
								<th sortColumn="field_id"><%=WoTask.ALIAS_FIELD_ID%></th>
								<th>
									操作
								</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${page.result}" var="item" varStatus="status">

								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
									<td>
										${page.thisPageFirstElementNumber + status.index}
									</td>
									<td>
										<input type="checkbox" name="items"
											value="taskId=${item.taskId}&">
									</td>

									<td>
										<c:out value='${item.topic}' />
										&nbsp;
									</td>
									<td>
										<c:out value='${item.guideline}' />
										&nbsp;
									</td>
									<td>
										<c:out value='${item.description}' />
										&nbsp;
									</td>
									<td>
										<c:out value='${item.feedbacktypeId}' />
										&nbsp;
									</td>
									<td>
										<c:out value='${item.examinecycleId}' />
										&nbsp;
									</td>
									<td>
										<c:if test="${item.statusId=='1'}">
											新任务
										</c:if>
										<c:if test="${item.statusId=='2'}">
											处理中
										</c:if>
										<c:if test="${item.statusId=='3'}">
											已反馈
										</c:if>
										<c:if test="${item.statusId=='4'}">
											已完成
										</c:if>
										&nbsp;
									</td>
									<td>
										<c:out value='${item.fieldId}' />
										&nbsp;
									</td>
									<td>
										<a href="${ctx}/collect/WoTask/show.do?taskId=${item.taskId}&">查看</a>
										&nbsp;
										<a href="${ctx}/collect/WoTask/urge.do?taskId=${item.taskId}&">督办</a>
										&nbsp;
										<a href="${ctx}/collect/WoTask/edit.do?taskId=${item.taskId}&">修改</a>
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
<%@ include file="base.jsp"%>

