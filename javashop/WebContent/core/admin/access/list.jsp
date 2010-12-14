<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form method="POST" >
<div class="grid">
	<div class="toolbar">
		 
		本月 由<select name="startday" id="startday">
		 <option value="0">全部</option>
		 <%
		 	for(int i=1;i<=31;i++){
		 %>
		 	 <option value="<%=i %>"><%=i %>日</option>
		 <%
		 	}
		 %> </select>
		 
		  至
		 
	<select name="endday" id="endday">
		 <option value="0">全部</option>
		 <%
		 	for(int i=1;i<=31;i++){
		 %>
		 	 <option value="<%=i %>"><%=i %>日</option>
		 <%
		 	}
		 %> </select>
		 		 
		 
		
		 <input type="submit" name="submit" value="查询" />   <a href="access!history.do" >其它月份报表下载</a>
		<div style="clear:both"></div>
	</div>

<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="120px">IP</grid:cell> 
		<grid:cell  width="150px">地区</grid:cell> 
		<grid:cell  width="150px">页面名称</grid:cell> 
		<grid:cell  width="100px">停留时间(秒) </grid:cell>
		<grid:cell  >访问时间 </grid:cell> 
		<grid:cell  >消耗积分</grid:cell>
	</grid:header>

  <grid:body item="access">
        <grid:cell>${access.ip} </grid:cell>
        <grid:cell>${access.area} </grid:cell> 
        <grid:cell>${access.page} </grid:cell> 
        <grid:cell>${access.stay_time } </grid:cell> 
        <grid:cell><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${access.access_time*1000}"></html:dateformat> </grid:cell> 
          <grid:cell>${access.point}   </grid:cell> 
  </grid:body>  
  
</grid:grid>  

<div style="clear:both;padding-top:5px;"></div>
</div>
</form>	 

<script>
$(function(){
	$("#startday").val(${startday});
	$("#endday").val(${endday});
});
</script>