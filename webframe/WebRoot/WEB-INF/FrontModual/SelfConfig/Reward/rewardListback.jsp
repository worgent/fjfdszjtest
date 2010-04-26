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

	<!-- 悬赏问题-->
		<form name="rewardform" method="post" action="reward.do">
			<div>
				<a href="/"><img src="<%=basePath%>/img/logo.gif"  title="悬赏图标"></a>
				<div style="margin-top:9px">
					<div>
					<s:hidden name="action" value="solvehide"></s:hidden>
					<s:textfield name="search.ptitle" value="" label="关键字"></s:textfield>
					<s:submit name="searchbtn" value="查询"></s:submit>
					<s:submit name="ask" value="提问" ></s:submit>
					</div>
					<div style="padding-top:4px;">
					<a href="<%=basePath%>/system/help.html" target=_blank>帮助</a>|<a href="<%=basePath%>/system/advice.html" target="_blank">提意见</a>
					</div>
				</div>
			</div>
		</form>
	<!-- 列出相关的悬赏信息 -->
		<div class="demo">
			<div id="tabs">
			    <!-- 导航  questionhot.html-->
			    
			    
			                          <!--右栏头部菜单-->
                        <div class="poiRL">
                             <ul class="poiRLT">
                               <li class='sel'><a href="<%=path%>/selfconfig/reward.do" title='待揭榜的悬赏'>待揭榜的悬赏</a></li>
                               <li ><a href="<%=path%>/selfconfig/reward.do" title='已揭榜的悬赏'>已揭榜的悬赏</a></li>
                               <li ><a href="<%=path%>/selfconfig/reward.do" title='高悬赏'>高悬赏</a></li>
                               <li ><a href="<%=path%>/selfconfig/reward.do" title='热门'>热门</a></li>
                             </ul>
 						<div class="poiRL2">
 						
 						
 						
				<ul>
					<li><a href="#question">待揭榜的悬赏</a></li>
					<li><a href="#questionsolve">已揭榜的悬赏</a></li>
					<li><a href="#questionoffer">高悬赏</a></li>
					<li><a href="#questionhot">热门</a></li>
				</ul>
				<!-- 列出待揭榜的信息 -->
				<div id="question">
					<table cellspacing="0" cellpadding="0" class="pubbox9 ft_l">
						<tr>
							<td>标题</td>
							<td width="10%" align="center">回答数</td>
							<td width="10%" align="center">提问时间</td>
						</tr>
						<s:iterator id="rewardList" value="%{pageList.objectList.{?#this.TYPE=3}}">
						<tr>
							<td>
							<a href="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="#rewardList.ID" />" target="_blank">
							_<s:property value="#rewardList.TITLE" />_
							</a>
							</td>
							<td width="10%" align="center"><s:property value="#rewardList.TITLE" />回答数</td>
							<td width="10%" align="center"><s:property value="#rewardList.CREATTIME"/></td>
						</tr>
						</s:iterator>
					</table>	
				</div>
				<!-- 列出待揭榜的信息 -->
				<div id="questionsolve">
					<table cellspacing="0" cellpadding="0" class="pubbox9 ft_l">
						<tr>
							<td>标题</td>
							<td width="10%" align="center">回答数</td>
							<td width="10%" align="center">提问时间</td>
						</tr>
						<s:iterator id="rewardList" value="%{pageList.objectList.{?#this.TYPE=2}}">
						<tr>
							<td>
							<a href="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="#rewardList.ID" />" target="_blank">
							<s:property value="#rewardList.TITLE" />
							</a>
							</td>
							<td width="10%" align="center"><s:property value="#rewardList.TITLE" />回答数</td>
							<td width="10%" align="center"><s:property value="#rewardList.CREATTIME"/></td>
						</tr>
						</s:iterator>
					</table>	
				</div>
				<!-- 列出待揭榜的信息 -->
				<div id="questionoffer">
					<table cellspacing="0" cellpadding="0" class="pubbox9 ft_l">
						<tr>
							<td>标题</td>
							<td width="10%" align="center">回答数</td>
							<td width="10%" align="center">提问时间</td>
						</tr>
						<s:iterator id="rewardList" value="%{pageList.objectList.{?#this.TYPE=1}}">
						<tr>
							<td>
							<a href="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="#rewardList.ID" />" target="_blank">
							<s:property value="#rewardList.TITLE" />
							</a>
							</td>
							<td width="10%" align="center"><s:property value="#rewardList.TITLE" />回答数</td>
							<td width="10%" align="center"><s:property value="#rewardList.CREATTIME"/></td>
						</tr>
						</s:iterator>
					</table>	
				</div>	
				<!-- 列出待揭榜的信息 -->
				<div id="questionhot">
					<table cellspacing="0" cellpadding="0" class="pubbox9 ft_l">
						<tr>
							<td>标题</td>
							<td width="10%" align="center">回答数</td>
							<td width="10%" align="center">提问时间</td>
						</tr>
						<s:iterator id="rewardList" value="%{pageList.objectList.{?#this.TYPE=0}}">
						<tr>
							<td>
							<a href="<%=path%>/selfconfig/reward.do?action=solvehide&search.pid=<s:property value="#rewardList.ID" />" target="_blank">
							<s:property value="#rewardList.TITLE" />
							</a>
							</td>
							<td width="10%" align="center"><s:property value="#rewardList.TITLE" />回答数</td>
							<td width="10%" align="center"><s:property value="#rewardList.CREATTIME"/></td>
						</tr>
						</s:iterator>
					</table>	
				</div>												
			</div>
		</div>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="750">
			<thead>
				<tr>
					<th>
						编号
					</th>
					<th>
						名称
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="reward" value="%{pageList.objectList}">
					<tr>
						<td width="30%" class="bgColor3">
							<span class="font1"> <s:property value="#reward.ID" /> </span>
						</td>
						<td width="70%" class="bgColor4">
							<s:property value="#reward.content" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="4">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>