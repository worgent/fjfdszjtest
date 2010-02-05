<%@ page language="java" errorPage="/error.jsp" pageEncoding="GB2312" contentType="text/html;charset=gb2312" %>
<%-- 加入标签 --%>
<%@ include file="/common/taglibs.jsp"%>
<br>
<center>
<table width="300" class="message" cellSpacing=0 cellPadding=0 border=0>
		<thead>
		  <tr>
			<th colspan="2" align="left" height="24"> ::<b>提示信息</b></th>
		  </tr>
		</thead>
		<TBODY>
			<TR>
				<TD width="20" align="right" class="bodyleft"><img src="/themes/default/images/mnuAbout.gif"></TD>
				<TD width="300" height="100" align="left" class="bodyright">
					<ww:if test="actionMessages==null">
						操作成功!
					</ww:if>
					<!-- 后台数据操作成功后的提示 -->
					<ww:else>
						<ww:iterator value="actionMessages">
							<ww:property />
						</ww:iterator>
					</ww:else>
				</TD>
			</TR>
			<TR >
				<TD align="center" colspan="2" class="buttons">
					<ww:if test="button==null || button.size()==0">
						<a href="javascript:history.back()">返回上一页</a>
					</ww:if>
					<ww:iterator value="button">
						<a href="<ww:property value='url'/>"><ww:property value="name"/></a>
					</ww:iterator>
				</TD>
			</TR>
		</TBODY>
</table>
</center>

<%-- 功能操作成功提示 --%>
<script language="javascript">	
	<%--
	刷新框架(刷新异常处理,多刷一次就正常了. )
	//parent.FunListFrm.window.location.reload();
	for(i=0;i<2;i++)
	{
		<ww:iterator value="actionScripts">
			<ww:property />
		</ww:iterator>
			i++;
    }	    
    
    问题根据源是:当前触发的窗口必须是刷新的窗口,否则就会发生异常

	--%>
        <ww:iterator value="actionScripts">
			<ww:property />
		</ww:iterator>
</script>
