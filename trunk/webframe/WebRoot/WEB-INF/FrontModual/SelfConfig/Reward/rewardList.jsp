<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript">

		</script>
	</head>
	<body>
		<!-- 悬赏问题-->
		<form name="rewardform" method="post" action="reward.do">
			<div>
				
				<div style="margin-top:9px">
					<div>
					<s:hidden name="action" value="solvehide"></s:hidden>
					<s:textfield name="search.ptitle" value="" label="关键字"></s:textfield>
					<s:submit name="searchbtn" value="查询"></s:submit>
					<s:submit name="ask" value="我要悬赏" ></s:submit>
					<s:submit name="searchguide" value="查询向导(通过搜索查找,针对性)"></s:submit>
					</div>
				</div>
			</div>
		</form>
	<!-- 列出相关的悬赏信息 -->
         <!--右栏头部菜单-->
		<div>
			<ul>
				<li
					style="float: left; margin: 5px 50px 0 0; list-style-type: none;">
					<a href="<%=path%>/selfconfig/reward.do?search.orderby=1&search.pstate=1" title='待揭榜的悬赏'>待揭榜的悬赏</a>
				</li>
				<li
					style="float: left; margin: 5px 50px 0 0; list-style-type: none;">
					<a href="<%=path%>/selfconfig/reward.do?search.orderby=2&search.pstate=1" title='高悬赏'>高悬赏</a>
				</li>
				<li
					style="float: left; margin: 5px 50px 0 0; list-style-type: none;">
					<a href="<%=path%>/selfconfig/reward.do?search.orderby=1&search.pstate=2" title='已揭榜的悬赏'>已揭榜的悬赏</a>
				</li>
				<!-- 
				<li
					style="float: left; margin: 5px 50px 0 0; list-style-type: none;">
					<a href="<%=path%>/selfconfig/reward.do" title='热门'>热门</a>
				</li>
				 -->
			</ul>
		</div>
		<!-- 列出待揭榜的信息 -->
				   <div>
					<table style="height: 90px; width: 990px;">
						<tr>
							<td>悬赏标题</td>
							<td width="40%" align="center">悬赏内容</td>
							<td width="10%" align="center">悬赏额度</td>
							<td width="20%" align="center">悬赏时间</td>
						</tr>
						<s:iterator id="rewardList" value="%{pageList.objectList.{?#this.TYPE=3}}">
						<tr>
							<td>
							<a href="<%=path%>/selfconfig/reward.do?action=rewarddetail&search.pid=<s:property value="#rewardList.ID" />" target="_self">
							_<s:property value="#rewardList.TITLE" />_
							</a>
							</td>
							<td width="20%" align="center"><s:property value="#rewardList.CONTENT" /></td>
							<td width="20%" align="center"><s:property value="#rewardList.INTEGRAL"/></td>
							<td width="20%" align="center"><s:property value="#rewardList.CREATTIME"/></td>
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
	</body>
</html>