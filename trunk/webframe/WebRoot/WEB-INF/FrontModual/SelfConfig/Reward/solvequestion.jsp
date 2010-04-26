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
							待解决问题:
						</h3>
						<div>
							<div></div>
							<a
								onclick="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="reward.ID" />"
								title="收藏该问题，以便下次查看" href="#">收藏</a>
						</div>
					</div>
					<div>
						<h3>
							<s:property value="solve.TITLE" />
						</h3>
						<h3>
							<s:property value="solve.CONTENT" />
						</h3>
						<div>
							标签：
							<a target="_blank"
								href="/z/ShowTag.e?sp=1091108864&amp;sp=Sqq&amp;sp=Sqq">qq</a><span>,</span>
							<a target="_blank"
								href="/z/ShowTag.e?sp=1091108864&amp;sp=S%E5%B0%81%E5%8F%B7&amp;sp=S%E5%B0%81%E5%8F%B7">封号</a>
						</div>
						<div>
							<input id="meAnswer" value="我来回答" type="button"></input>
						</div>


						<div>
							<div>
								<span><a href="" target="_blank"><s:property value="solve.CONTENT" /></a>
								</span>
								<span>积分:<s:property value="solve.INTEGRAL" /></span>
								<span>人气:1</span>
								<span>提问时间:<s:property value="solve.SCREATTIME" /></span>
							</div>
							<div>
								&nbsp;&nbsp;
								<a
									onclick="questionOperate('/z/Impeach.e?sp=150073799&amp;sp=0&amp;sp=0&amp;sp=X&amp;sp=F','检举问题',380,370);return false;"
									title="不符合问问原则，我来检举" href="javascript:void(0);">检举</a>
							</div>
						</div>
					</div>
				</div>
				<div>
					<!-- 回答提交信息 -->
					<form method="post" action="/selfconfig/reward.do?action=insertsolvequestion" name="answerQuestion" id="answerQuestion">
						<s:hidden id="search.prewardid"   name="search.prewardid" value="%{reward.ID}"></s:hidden>
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