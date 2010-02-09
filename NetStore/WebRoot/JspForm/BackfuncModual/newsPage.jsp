<%@ page language="java"  pageEncoding="gbk"%>
<jsp:directive.page import="com.qzgf.NetStore.pub.*" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
<head>
	<html:base />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css" />
	<script type="text/javascript">
	    function checkAll(){
	        if(document.getElementsByName("selectAll")[0].checked){
	            for(i=0;i<document.getElementsByName("newsIds").length;i++){
	                document.getElementsByName("newsIds")[i].checked=true;
	            }
	        }
	        else{
	            for(i=0;i<document.getElementsByName("newsIds").length;i++){
	                document.getElementsByName("newsIds")[i].checked=false;
	            }
	        }
	    }
	    function delId(){
	        document.forms[0].action="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do";
	        document.getElementById("status").value="deleteNews";
		    document.forms[0].submit();
	    }
	</script>
	<title>首页新闻管理</title>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<center>
	<table width="80%">
       <tr>
           <td colspan="2" align="right">
           <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=to_addNews">添加</a>
           </td>
       </tr>
    </table>
    <form action="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do">
	<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
	    <tr>
	        <th>
	            新闻主题
	        </th>
	        <th>
	           发布人
	        </th>
	        <th>
	          发布时间
	        </th>
	        <th>
	           选择
	        </th>
	    </tr>
	    
	    <logic:present name="ppage" scope="request">
			<logic:present name="ppage" property="resultList" scope="request">
				<logic:iterate id="item" name="ppage" property="resultList"
					scope="request">
					<tr>
						<td class="main">
							<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryNewsById&id=${item.newsId }">
							${item.newsTitle}</a>
						</td>
						<td class="main">
							${item.releaseMan }
						</td>
						<td class="main">
							${item.releaseTime }
						</td>
						<td class="main">
						   <input type="checkbox" id="newsIds" name="newsIds" value="${item.newsId}"  />
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</logic:present>
		<tr>
			<td class="main" align="left" colspan="2">
				<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryAllNews&targetPage=1"
					class="pagenav">首页</a>
				<%
								if (p.isHasPrevious()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryAllNews&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryAllNews&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
					class="pagenav">下一页</a>
				<%
								} else {
								%>
				下一页
				<%
								}
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryAllNews&targetPage=<%=p.getTotalPages()%>"
					class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页
				&nbsp;&nbsp;共
				<bean:write name="ppage" property="totalRecords" />
				条记录

			</td>
			<td class="main" align="right" colspan="2">
				转
				<input type="text" name="page" class="inp_page" value="" size="4"
					onkeyup="value=value.replace(/[^\d]/g,'')" />
				页
				<input type="button" value="Go" onclick="setAction()" class="button" />
				<input type="hidden" id="totalPages" name="totalPages"
					value="<bean:write name="ppage" property="totalPages"/>" />
			</td>
		</tr>
		<tr>
		    <td colspan="4" class="main">
		        <input type="hidden" name="status" />
		        <input type="submit" value="删除所选新闻" onclick="delId()" class="button0000"  />
                <input type="checkbox" id="selectAll" name="selectAll" value="1" onclick='checkAll();' />全选
		    </td>
		</tr>
	</table>
	</form>
	</center>
</body>
</html:html>