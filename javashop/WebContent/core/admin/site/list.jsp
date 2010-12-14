<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="solution!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="250px">id</grid:cell> 
		<grid:cell >名称</grid:cell> 
		<grid:cell >积分</grid:cell> 
		<grid:cell  width="50px">修改</grid:cell> 
	</grid:header>

  <grid:body item="site">
        <grid:cell>${site.id} </grid:cell>
        <grid:cell>${site.sitename} </grid:cell> 
        <grid:cell>${site.point} </grid:cell> 
        <grid:cell> <a  href="site!edit.do?id=${site.id}" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	 
<div style="clear:both;padding-top:5px;"></div>
</div>