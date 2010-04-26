<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table width="95%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="bgColor1" colspan="4">
			<table width="100%" border="0" cellpadding="5" cellspacing="1">
				<tr class="bgColor3">
					<td colspan="4" class="font1">
						<strong>向导锦囊</strong>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="bgColor4">标题</td><td class="bgColor4">创建时间</td>
		<td class="bgColor4">被浏览数</td><td class="bgColor4">操作</td>
	</tr>
	<s:iterator value="%{pageList.objectList}" id="guideCoup">
      <tr>
        <td width="20%" class="bgColor4">
          <span class="font1">
           <s:property value="#guideCoup.COUPTITLE"/>
          </span>
        </td>
        <td width="20%" class="bgColor4">
           <s:property value="#guideCoup.COUPCREATEDATE"/>
        </td>
        <td width="20%" class="bgColor4">
          <s:property value="#guideCoup.COUPCOUNT"/>
        </td>
        <td width="40%" class="bgColor4">
          <a href="javascript:;" onclick="loadCoupEditPage('<s:property value="#guideCoup.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">修改</a>|
          <a href="javascript:;" onclick="delCoupById('<s:property value="#guideCoup.ID"/>','<s:property value="%{pageList.pages.page}"/>','<%=path%>');">删除</a>
        </td>
      </tr>
      </s:iterator>
      <tr class="bgColor3">
        <td colspan="4">
          	分页:<webframe:pages value="%{pageList.pages}" javaScript="loadCoupList" />
          [<strong><a href="javascript:;" onclick="loadCoupAddPage('<%=path%>');">新增</a></strong>]</td>
        </tr>
</table>
