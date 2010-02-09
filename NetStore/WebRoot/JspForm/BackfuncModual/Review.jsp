<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="com.qzgf.NetStore.pub.*"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
<title>商品评论管理</title>



    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/ext/resources/css/ext-all.css" />
 	<script type="text/javascript" src="<%=request.getContextPath()%>/include/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/ext/ext-all.js"></script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>

 <script type="text/javascript">
    Ext.onReady(function(){
	
	var comboxWithTree = new Ext.form.ComboBox({   
        store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
        editable:false,
        renderTo: 'combo',
        mode: 'local',   
        triggerAction:'all',   
        maxHeight: 200,   
        tpl: "<tpl for='.'><div style='height:200px'><div id='tree'></div></div></tpl>",   
        selectedClass:'',   
        onSelect:Ext.emptyFn   
    });   
    var tree = new Ext.tree.TreePanel({   
        loader: new Ext.tree.TreeLoader({
           dataUrl:'../../productModify.do?status=getTree'
        }), 
        border:false,
        rootVisible: true,  
        root:new Ext.tree.AsyncTreeNode({text: '商品分类',id:'0'})
      });   
      tree.on('click',function(node){ 
          comboxWithTree.setValue(node.text);
          Ext.get("id").dom.value=node.id;
          Ext.get("catalogName").dom.value=node.text;  
          
          comboxWithTree.collapse();  
            
      });   
    comboxWithTree.on('expand',function(){   
        tree.render('tree');   
      });
    comboxWithTree.render('comboxWithTree'); 
    <%
   if (request.getAttribute("catalogName")!=null) {
   %>
      comboxWithTree.setValue("<%=request.getAttribute("catalogName") %>");
   <%
   }
    %>
});
</script>
<script type="text/javascript">
function number()
{
var char = String.fromCharCode(event.keyCode)
var re = /[0-9]/g
event.returnValue = char.match(re) != null ? true : false
}

function setAction(action){
document.forms[0].action="../../review.do?status=allReview"; 
document.forms[0].submit();
}
</script>
</head>



<body background="<%=request.getContextPath()%>/images/bg.gif">
	    <%
			String context = request.getContextPath();
				pageContext.setAttribute("ctx", context);
		%>
   <form action="/review.do?status=select" method="post">
    <center>
      <table  class="tbl" width="780"  cellspacing="0" cellpadding="0"  >
    	   <tr>
    	   <td colspan="4" bgcolor="#009CD6"  background="../../images/newsystem/th2.gif" class="txt_b"  align="center">商品评论管理</td>
    	   </tr>
                   <tr>
    					<td>
    					　<a href="../../review.do?status=allReview&t=1">所有评论</a>
    					</td>
    					<td>
    				     　<a href="../../review.do?status=allReview&t=2">未回复评论</a>
    					</td>
    					<td>
    				     <a href="../../review.do?status=allReview&t=3">未审核评论</a>
    					</td>
    					<td>
    				     <a href="../../review.do?status=allReview&t=4">已审核评论</a>
    					</td>
    				</tr> 
    	    
    				<tr>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    					评论商品名称
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				     评论标题
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				     评论时间
    					</td>
    					<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    				      操作
    					</td>
    				</tr> 
    				
    				
    		    <logic:present name="ppage" scope="request">
				<logic:present   name="ppage" property="resultList" scope="request">
				 <logic:iterate id="item" name="ppage" property="resultList" scope="request">
    			 <tr>
    			 <td  class="main">
    			 <input type="hidden" name="productId" value="${item.productId}"/>
    			 ${item.productName}&nbsp;
    			 </td>
    			 <td class="main">
    			 ${item.title}&nbsp;
    			 </td>
    			 <td class="main">
    			 ${item.releaseTime}&nbsp;
    			 </td>
    			 <td class="main">
    					
    			 <a href="<%=request.getContextPath()%>/review.do?status=Replying&rId=${item.reviewId}&page=<%=request.getAttribute("page")%>&t=<%=request.getAttribute("t")%>">回复</a>
    			 <logic:equal  name="item" property="isAudit" value="false">
    			  <a 
    			 href="<%=request.getContextPath()%>/review.do?status=Audited&rId=${item.reviewId}&page=<%=request.getAttribute("page")%>&t=<%=request.getAttribute("t")%>">审核通过</a>
    			</logic:equal>
    			 <logic:equal  name="item" property="isAudit" value="true">
    				  <a 
    			 href="<%=request.getContextPath()%>/review.do?status=Auditing&rId=${item.reviewId}&page=<%=request.getAttribute("page")%>&t=<%=request.getAttribute("t")%>">取消审核</a>
    			</logic:equal>
    			
    			 <a onclick='return confirm("确定要删除当前记录吗?")' 
    			 href="<%=request.getContextPath()%>/review.do?status=delete&rId=${item.reviewId}&page=<%=request.getAttribute("page")%>&t=<%=request.getAttribute("t")%>">删除</a>
    			 
    			 </td>
    			 </tr>
    			</logic:iterate>
    			</logic:present>
    			</logic:present>
    		</table>
    	
  
   <table class="tbl" width="780"  cellspacing="0" cellpadding="0"  >
	<tr>
	<td class="main" align="left">
								<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
								<a
									href="../../review.do?status=allReview&targetPage=1&t=<%=request.getAttribute("t")%>"
									class="pagenav">首页</a>
								<%
								if (p.isHasPrevious()) {
								%>
								<a
									href="../../review.do?status=allReview&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>&t=<%=request.getAttribute("t")%>"
									class="pagenav">上一页</a>
								<%
								} else {
								%>
								上一页
								<%
									}
									if (p.isHasNext()) {
								%>
								<a
									href="../../review.do?status=allReview&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>&t=<%=request.getAttribute("t")%>"
									class="pagenav">下一页</a>
								<%
								} else {
								%>
								下一页
								<%
								}
								%>
								<a
									href="../../review.do?status=allReview&targetPage=<%=p.getTotalPages()%>&t=<%=request.getAttribute("t")%>"
									class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页 &nbsp;&nbsp;共<bean:write name="ppage" property="totalRecords"/>条记录
							</td>
    
    <td align="right" class="main">
    转<input type="text" name="page" class="inp_page" value="" size="2"  onkeypress="number()"/>页 
    <input type="button" value="Go" onclick="setAction('qry')" class="button"/>
    </td>
	</tr>
	</table>
	
     <input type="hidden" id="totalPages" name="totalPages" value="<bean:write name="ppage" property="totalPages"/>"/>
    <input type="hidden" id="tt" name="tt" value="<%=request.getAttribute("t") %>"/>

    	</center>
    	</form>
</body>
</html:html>