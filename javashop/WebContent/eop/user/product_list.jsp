<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../core/common/commonlibs.jsp"%>

<div class="grid">
	<grid:grid from="webPage">
		<grid:header>
			<grid:cell width="100px">名称</grid:cell>
			<grid:cell width="150px">描述</grid:cell>
			<grid:cell  width="100px">作者</grid:cell>
			<grid:cell >操作</grid:cell>
		</grid:header>
		<grid:body item="product">
			<grid:cell>
			${ product.product_name }
			</grid:cell>
			<grid:cell>&nbsp;${product.descript } </grid:cell>
			<grid:cell>&nbsp;${product.author }</grid:cell>
			<grid:cell>&nbsp; <a href="product!detail.do?productid=${product.productid }">查看</a></grid:cell>
		</grid:body>
	</grid:grid>
</div>