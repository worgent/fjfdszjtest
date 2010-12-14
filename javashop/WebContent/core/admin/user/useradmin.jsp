<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<script type="text/javascript" src="../js/PasswordOperator.js"></script>
<div class="tab_bar">
	<ul class="tab">
		<li><a href="/eop/core/admin/user/baseInfo.do">我的账户</a></li>
		<li class="active"><a href="/eop/core/admin/user/userAdmin.do">账户管理员</a></li>
	</ul>
</div>
<div class="grid">
<div class="toolbar">
<ul>
	<li><a href="userAdmin!add.do">添加</a></li>
	<li><a href="#">删除</a></li>
	<li>
	<form action="userAdmin.do" method="post" id="theForm" name="theForm">
	<label>关键字：</label> <input id="search" name="search" type="text"
		value="${search }"></input> <input type="submit"
		style="margin-left: 5px;" value="查询" /></form>
	</li>
</ul>
</div>
<grid:grid from="webpage">
	<grid:header>
		<grid:cell sort="id" width="50px">id</grid:cell>
		<grid:cell sort="realname" width="110px">管理员姓名</grid:cell>
		<grid:cell sort="username" width="200px">管理员账号</grid:cell>
		<grid:cell>管理站点 </grid:cell>
		<grid:cell>Email</grid:cell>
		<grid:cell width="180px">操作</grid:cell>
	</grid:header>
	<grid:body item="userAdmin">
		<grid:cell>
			<input type="checkbox" name="id" value="${userAdmin.id }" />${userAdmin.id }</grid:cell>
		<grid:cell>&nbsp;${userAdmin.realname }
		</grid:cell>
		<grid:cell>&nbsp;${userAdmin.username } </grid:cell>
		<grid:cell><ul style="width:90%">
			<c:forEach var="item" items="${userAdmin.eopSiteAdminList }">
				<li style="width:100%;float:left">${item.sitename }</li>
			</c:forEach></ul> 
		</grid:cell>
		<grid:cell>&nbsp;${userAdmin.email }</grid:cell>
		<grid:cell>&nbsp;<span>
		<a href="userAdmin!delete.do?id=${userAdmin.id }"
			onclick="return confirm('您确定要删除当前记录吗？')">
			<img class="delete"  src="../images/transparent.gif"></a>
			
			&nbsp;<a
				href="userAdmin!edit.do?id=${userAdmin.id }">
				<img class="modify"  src="../images/transparent.gif"></a>
				<a href="userAdmin!initPassword.do?id=${userAdmin.id }" onclick="return confirm('您确定要将该管理员的密码初始化为123456吗？')">初始化密码</a>  
				<!--&nbsp;<input type="button" id="btn" value="修改密码" adminid="${userAdmin.id }"/>--></span>
		</grid:cell>
	</grid:body>
</grid:grid></div>


