<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<c:set var="fieldList" value="${fieldList}" />
<script type="text/javascript" src="js/datamodel.js"></script>

<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="data!add.do?catid=${catid}">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header> 
	<grid:cell width="50px">id</grid:cell>
	
	<c:forEach items="${fieldList}" var="field">
		<th style="width:100px">${field.china_name}</th> 
	</c:forEach>
		<th style="width:100px">添加时间</th>
		<th style="width:100px">修改</th>
		<th style="width:100px">删除</th>
	</grid:header> 

  <grid:body item="article">
  		<grid:cell>${article.id } </grid:cell>
  		<html:field></html:field>
  		<td><fmt:formatDate value="${article.add_time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	    <td><a href="data!edit.do?dataid=${article.id }&catid=${catid}"><img src="images/transparent.gif" class="modify"></a></td>
	    <td>
	    <a href="data!delete.do?dataid=${article.id }&catid=${catid}" onclick="javascript:return confirm('确定删除此文章吗?');"><img src="images/transparent.gif" class="delete"></a>
	    </td>  		
  </grid:body>   
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>