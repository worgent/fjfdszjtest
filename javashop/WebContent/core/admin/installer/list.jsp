<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div class="grid">
	<grid:grid from="webpage">
		<grid:header>
			<grid:cell width="100px">ip</grid:cell>
			<grid:cell width="150px">版本</grid:cell>
			<grid:cell  width="100px">时间</grid:cell>
 
		</grid:header> 
		<grid:body item="user">
			<grid:cell>
			${ user.ip }
			</grid:cell>
			<grid:cell>&nbsp;${user.version } </grid:cell>
			<grid:cell>&nbsp; <html:dateformat pattern="yyyy-MM-dd" time="${user.installtime }"></html:dateformat></grid:cell>
		</grid:body>
	</grid:grid>
</div>

