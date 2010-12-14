<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript"  >
loadScript("js/Goods.js");
</script>

<div class="grid">
<form action="goods!list.do" method="get">
	<div class="toolbar">
		<ul>
			<li><a href="goods!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="goods!trash_list.do">回收站</a></li>
			<li><a href="goods!list.do?is_edit=true">列表编辑</a></li>
		</ul>
<div style="margin-left:10px">商品名称:<input type="text" style="width:150px" name="name" value="${name }"/>商品编号:<input type="text" style="width:150px" name="sn" value="${sn }"/><span id="searchCat"></span><input type="submit" name="submit" value="搜索"></div>		
		<div style="clear:both"></div>
	</div>
</form>	
<form id="gridform">
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="50px"><input type="checkbox" id="toggleChk"/></grid:cell> 
		<grid:cell sort="sn" width="80px">商品编号</grid:cell> 
		<grid:cell sort="name" width="250px">商品名称</grid:cell>
		<grid:cell sort="cat_id"  width="100px">分类</grid:cell> 
		<grid:cell sort="price">销售价格</grid:cell>
		<grid:cell sort="store">库存</grid:cell>
		<grid:cell sort="market_enable">上架</grid:cell>
		<grid:cell sort="brand_id">品牌</grid:cell>
		<grid:cell sort="last_modify">上架时间</grid:cell>
		<grid:cell sort="type_id">类型</grid:cell>
		<grid:cell  width="50px">操作</grid:cell> 
	</grid:header>


  <grid:body item="goods" >
  
  		<grid:cell><input type="checkbox" name="id" value="${goods.goods_id }" />${goods.goods_id }</grid:cell>
        <grid:cell>&nbsp;${goods.sn } </grid:cell>
        <grid:cell>&nbsp;${goods.name }</grid:cell> 
        <grid:cell>&nbsp;${goods.cat_name} </grid:cell> 
        <grid:cell>&nbsp;<fmt:formatNumber value="${goods.price}" type="currency" pattern="￥.00"/> </grid:cell> 
        <grid:cell>&nbsp;${goods.store} </grid:cell> 
        <grid:cell>&nbsp;<c:if test="${goods.market_enable==0}" >否</c:if> <c:if test="${goods.market_enable==1}" >是</c:if></grid:cell>
        <grid:cell>&nbsp;${goods.brand_name} </grid:cell> 

        <grid:cell>&nbsp;<html:dateformat pattern="yyyy-MM-dd" time="${goods.last_modify}"></html:dateformat> </grid:cell> 
        <grid:cell>&nbsp;${goods.type_name} </grid:cell> 
        <grid:cell>
        <a href="goods!edit.do?goods_id=${goods.goods_id }" > 
      	 <img class="modify" src="../shop/admin/images/transparent.gif" > 
        </a>
        
        </grid:cell> 
         
  </grid:body>
  
</grid:grid>
</form>
<div style="clear:both;padding-top:5px;"></div>
</div>
<script type="text/javascript">

$(function(){
	$.ajax({
		url:basePath+'goods!getCatTree.do?ajax=yes',
		type:"get",
		dataType:"html",
		success:function(html){
			 
			var serachCatSel =$(html).appendTo("#searchCat");
			serachCatSel.removeClass("editinput").attr("name","catid");
			serachCatSel.children(":first").before("<option value=\"\" selected>全部类别</option>");
			<c:if test="${catid!=null}">serachCatSel.val(${catid})</c:if>
		},
		error:function(){
			alert("获取分类树出错");
		}
	});
});

</script>
