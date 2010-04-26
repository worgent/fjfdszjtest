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
						<h3>
							待解决问题
						</h3>
						<div>
							<div></div>
							<a
								onclick="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="reward.ID" />"
								title="收藏该问题，以便下次查看" href="#">收藏</a>
						</div>
					</div>
					<div>
						<h3>悬赏标题:
							<s:property value="reward.TITLE" />
						</h3>
						<h3>悬赏内容:
							<s:property value="reward.CONTENT" />
						</h3>						
						<div>
							<div>
								<span>悬赏人:<s:property value="reward.USERNAME" /></span>
								<span>积分:<s:property value="reward.INTEGRAL" /></span>
								<span>人气:1</span>
								<span>悬赏时间:<s:property value="reward.CREATTIME" /></span>
							</div>
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
							<td width="20%" align="center"><s:property value="#sovleList.USERNAME"/></td>
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
				</div>
				<div>
				
				<div>
					<!-- 回答提交信息 -->
					<form method="post" action="/selfconfig/reward.do?action=insertsolvequestion" name="answerQuestion" id="answerQuestion">
						<s:hidden id="search.prewardid"   name="search.prewardid" value="%{reward.ID}"></s:hidden>
						<h3>揭榜补充</h3>
						<div class="tips">
							为避免你的答案被删除，请严格按照问题内容和
							<a href="<%=basePath%>/system/help.html" target="_blank">揭榜原则</a>回答问题。
						</div>
						<!-- 用户回答问题区域 -->
						<div class="answer_textarea">
							<ul>
								<li>
									<textarea name="search.pcontent" id="search.pcontent" cols="41"
										style="display: block;" rows="5"></textarea>
								</li>

								<li>
									<input type="checkbox" name="anonymousAnswer"
										id="anonymousAnswer" />
									<label for="anonymousAnswer">
										匿名回答
									</label>
								</li>
								<li class="bt_wrap">
									<input type="submit" name="answerSubmit" id="answerSubmit"
										value="登录并提交" class="bt1" />
									<span>Ctrl+Enter直接提交</span>
								</li>
							</ul>
						</div>
					</form>
				</div>
	</body>
</html>