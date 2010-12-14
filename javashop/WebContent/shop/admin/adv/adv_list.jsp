<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Adv.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="adv!add.do">新增广告</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">广告名称</grid:cell>
	<grid:cell sort="cname">所属广告位</grid:cell>
	<grid:cell sort="company">单位名称</grid:cell> 
	<grid:cell sort="begintime">开始时间</grid:cell>
	<grid:cell sort="endtime">截止时间</grid:cell> 
	<grid:cell sort="isclose">状态</grid:cell>
	<grid:cell sort="clickcount">点击数</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="adv">
  		<grid:cell><input type="checkbox" name="id" value="${adv.aid }" />${adv.aid } </grid:cell>
        <grid:cell>${adv.aname } </grid:cell>
        <grid:cell>${adv.cname } </grid:cell>
        <grid:cell>${adv.company }</grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${adv.begintime }"></html:dateformat> </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${adv.endtime }"></html:dateformat></grid:cell>
        <grid:cell>
        	<c:if test="${adv.isclose == 0 }">已开启&nbsp;<input type="button" class="stop" advid="${adv.aid }" value="停用" /></c:if>
        	<c:if test="${adv.isclose == 1 }">已停用&nbsp;<input type="button" class="start" advid="${adv.aid}" value="开启" /></c:if>
        </grid:cell> 
        <grid:cell>${adv.clickcount }</grid:cell>  
        <grid:cell><a href="adv!edit.do?advid=${adv.aid}" ><img class="modify" src="images/transparent.gif"/></a>  </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


