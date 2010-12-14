<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../core/common/commonlibs.jsp"%>
<form action="product!registerlogin.do" method="POST">
<div class="input">
<div>您还没有登录</div>
<div><input type="hidden" name="productid" value="${productid }" />请选择：</div>
<div><input type="radio" name="newuser" value="0" checked/>我是新用户，请为我注册&nbsp;<input type="radio" name="newuser" value="1" />我是老用户，请为我登录</div>
<div>
<span>用户名：</span><span><input type="text" name="username"/></span>
</div>
<div>
<span>密码：</span><span><input type="password" name="password" class="password"></span>
</div>
<div>
<span>域名：</span><span><input type="text" name="domain" ></span>
</div>

<div><input type="submit" name="submit" value="确定"></div>
</div>
</form>