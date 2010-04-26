<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
		//1.导航
		$(function() {
			$("#tabs").tabs();
		});
		</script>
	</head>
	<body>
	          <div>
					<div>
					   <h3>详情</h3>
					</div>
					<div>
					    <s:form action="reward" method="post">
						悬赏标题:<s:textfield name="search.ptitle" value="%{reward.TITLE}"></s:textfield>
						<br>
						悬赏内容:<s:textfield name="search.pcontent" value="%{reward.CONTENT}" ></s:textfield>
						<br>
						悬赏人:<s:textfield name="search.puserid" value="%{reward.USERID}"></s:textfield>
						<br>	
						积分:<s:textfield name="search.pintegral" value="%{reward.INTEGRAL}"></s:textfield>
						<br>
						是否结帖:<select name="search.pstate" id="pstate">
					 		<option value="">请选择</option>
					 		<option value="1" <s:if test="%{reward.STATE==1}">selected</s:if>>否</option>
					 		<option value="2" <s:if test="%{reward.STATE==2}">selected</s:if>>是</option>	
					 		<option value="3" <s:if test="%{reward.STATE==3}">selected</s:if>>未解决</option>				 		
					   </select>
						<br>
		  				<s:hidden name="action" value="%{action}"></s:hidden>
		  				<s:hidden name="search.pid" value="%{reward.ID}"></s:hidden>
		  				<br>
		  				<s:submit value="提交" align="right"></s:submit>
		  				<s:reset value="重置" align="left"></s:reset>
		  				<br>
                        </s:form>
				    </div>	
				   <!-- 列出揭榜的信息 -->
				   <div>
				   <h3>揭榜相关</h3>
					<table style="height: 90px; width: 990px;">
						<tr>
							<td>揭榜内容</td>
							<td width="20%" align="center">揭榜人</td>
							<td width="20%" align="center">揭榜时间</td>
						</tr>
						<s:iterator id="sovleList" value="%{pageList.objectList}">
						<tr>
							<td>
							<s:property value="#sovleList.CONTENT" />
							</td>
							<td width="20%" align="center"><s:property value="#sovleList.USERID"/></td>
							<td width="20%" align="center"><s:property value="#sovleList.CREATTIME"/></td>
						</tr>
						</s:iterator>
						<tr class="bgColor3">
							<td colspan="4">
								分页:
								<qzgf:pages value="%{pageList.pages}" />
							</td>
						</tr>
		            </table>
				</div>
				</div>
				<div>

	</body>
</html>