<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../core/common/commonlibs.jsp"%>

<div>
<div>
<span>名称：</span><span>${eopProduct.product_name }</span>
</div>
<div>
<span>作者：</span><span>${eopProduct.author }</span>
</div>
<div>
<span>描述：</span><span>${eopProduct.descript }</span>
</div>
<div><a href="product!install.do?productid=${eopProduct.productid }">安装</a></div>
</div>