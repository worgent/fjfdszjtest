<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>菜单</title>
		<style type="text/css">
<!--
body {
	background-color: #FAFAFA;
}
-->
</style>
<script language="javascript" type="text/javascript">
function showMemu(i) {
  $("#t" + i).toggle();
}

function over(tid)
{
	tid.style.backgroundColor="#ebeadd";
}

function out(tid)
{
	tid.style.backgroundColor="#ffffff";
}
</script>
	</head>
	<body>
		<table width="160" border="0" cellspacing="2" cellpadding="0"
			class="table2">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="3" cellpadding="0"
						class="table3">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="table4">
									<tr>
										<td class="title" height="21" onClick="showMemu(0)">
											&nbsp;□ 首页
										</td>
									</tr>
								</table>
								
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t0" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url value="/adminIndex.jsp"
												id="adminIndexUrl"></s:url>
											<a href="${adminIndexUrl}" target="webframe_mainFrame">浏览首页</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url value="/mapList.jsp"
												id="mapListUrl"></s:url>
											<a href="${mapListUrl}" target="webframe_mainFrame">地图宝典测试页</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td class="title" height="21" onClick="showMemu(1)">
											&nbsp;□ 个人空间
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t1" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="twitterType.do"
												id="twitterType"></s:url>
											<a href="${twitterType}" target="webframe_mainFrame">日志分类</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="twitter.do"
												id="twitter"></s:url>
											<a href="${twitter}" target="webframe_mainFrame">日志列表</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="album.do" id="album"></s:url>
											<a href="${album}" target="webframe_mainFrame">相册管理</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="coup.do" id="coup">
											</s:url>
											<a href="${coup}" target="webframe_mainFrame">向导锦囊</a>
										</td>
									</tr>
									
									
									<!-- ==================================================== -->
									
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="system/message.do" id="message" ></s:url>
											<a href="${message}" target="webframe_mainFrame">站内消息</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/mapcard.do?action=list" id="mapcard">
											<s:param name="action">list</s:param>
											</s:url>
											<a href="${mapcard}" target="webframe_mainFrame">地图名片</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/reward.do?action=list" id="reward">
											<s:param name="action">list</s:param>
											</s:url>
											<a href="${reward}" target="webframe_mainFrame">悬赏揭榜</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/mapcardsort.do" id="mapcardsort">
											</s:url>
											<a href="${mapcardsort}" target="webframe_mainFrame">悬赏类别</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/bulletin.do" id="bulletin1">
											<s:param name="action">list</s:param>
											<s:param name="search.ptype">1</s:param>
											</s:url>
											<a href="${bulletin1}" target="webframe_mainFrame">优惠劵发布</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/bulletin.do" id="bulletin2">
											<s:param name="action">list</s:param>
											<s:param name="search.ptype">2</s:param>
											</s:url>
											<a href="${bulletin2}" target="webframe_mainFrame">狩猎贴发布</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/bulletin.do" id="bulletin3">
											<s:param name="action">list</s:param>
											<s:param name="search.ptype">3</s:param>
											</s:url>
											<a href="${bulletin3}" target="webframe_mainFrame">领主招纳</a>
										</td>
									</tr>
									
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/shareFav.do" id="shareFav">
											<s:param name="action">searchUser</s:param>
											</s:url>
											<a href="${shareFav}" target="webframe_mainFrame">优惠分享</a>
										</td>
									</tr>
									
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="selfconfig/bulletin.do" id="bulletin3">
											<s:param name="action">list</s:param>
											<s:param name="search.ptype">3</s:param>
											</s:url>
											<a href="${bulletin3}" target="webframe_mainFrame">悬赏短信</a>
										</td>
									</tr>
									<!-- ==================================================== -->
									
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
										 <a href="<%=request.getContextPath()%>/space.do?action=list" target="webframe_mainFrame">空间管理</a>	
											
										</td>
									</tr>	
									
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
										 <a href="<%=request.getContextPath()%>/space.do?action=index" target="webframe_mainFrame">空间配置</a>	
										</td>
									</tr>
										
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
										        <a href="<%=request.getContextPath()%>/user.do?action=list" target="webframe_mainFrame">用户信息</a>
										        </td>
									</tr>
									 
       
                                    <tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
										     <a href="<%=request.getContextPath() %>/user.do?action=login" target="webframe_mainFrame" > 
                                          个人信息</a>	        </td>
									</tr>
									
									
									
									
        	                           <tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
										     <a href="<%=request.getContextPath() %>/user.do?action=merchantEnter&search.puserid=<s:property value="#user.USERID" />&search.pusername=<s:property value="#user.USERNAME" />" target="webframe_mainFrame" > 
                   商家基本信息</a>	        </td>
									</tr>

        
               
        	                           <tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
		<a href="<%=request.getContextPath() %>/user.do?action=productMessage&search.puserid=<s:property value="#user.USERID" />&search.pusername=<s:property value="#user.USERNAME" />"  target="webframe_mainFrame"> 
                    商品信息</a>
                    </td>
									</tr>
               
       
              
                  <tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
		<a href="<%=request.getContextPath() %>/user.do?action=favourableList&search.puserid=<s:property value="#user.USERID" />&search.pusername=<s:property value="#user.USERNAME" />"  target="webframe_mainFrame"> 
                  优惠或VIP信息</a>
                    </td>
									</tr>
									
									    <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		    <a href="<%=request.getContextPath() %>/user.do?action=insertAppeal&search.pusername=<s:property value="#user.USERNAME" />"  target="webframe_mainFrame">会员申诉</a>
 
                    </td>
					</tr>	
					
									
				   <tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
		    
             <a href="<%=request.getContextPath() %>/user.do?action=appealList&search.pusername=<s:property value="#user.USERNAME" />"  target="webframe_mainFrame">商家回复申诉</a>
        
                    </td>
									</tr>					
                
              
              
              		   <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		    
            <a href="<%=request.getContextPath() %>/user.do?action=passwordSet&search.pusername=<s:property value="#user.USERNAME" />"  target="webframe_mainFrame">修改密码</a>
          
                    </td>
					</tr>	
									
                 <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		    <a href="<%=request.getContextPath() %>/user.do?action=createTribal"  target="webframe_mainFrame">创建部落</a>
         
                    </td>
					</tr>	
          
          
                    <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		             <a href="<%=request.getContextPath() %>/user.do?action=tribalGroup"  target="webframe_mainFrame">部落群</a>
          
                    </td>
					</tr>	
					
					 <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		                <a href="<%=request.getContextPath() %>/user.do?action=friendSet"  target="webframe_mainFrame">好友设置</a>
          
                    </td>
					</tr>	
					 <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		                <a href="<%=request.getContextPath() %>/user.do?action=categories"  target="webframe_mainFrame">商业分类</a>
          
                    </td>
					</tr>	
					
				
					 <tr>
						<td class="item" height="21" align="center"
						onMouseOver="over(this);" onMouseOut="out(this)">
		                <a href="<%=request.getContextPath() %>/businGud.do?action=guideList"  target="webframe_mainFrame">商家指定向导</a>
          
                    </td>
					</tr>	
					
					   
          	</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="table4">
									<tr>
										<td class="title" height="21" onClick="showMemu(3)">
											&nbsp;□ 用户与权限
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t3">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											权限列表
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											角色列表
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											用户组列表
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="adminUserLevel?action=index"
												id="adminUserLevelUrl" includeParams="none">
											</s:url>
												<a href="${adminUserLevelUrl}" target="webframe_mainFrame">用户等级设定</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td class="title" height="21" onClick="showMemu(4)">
											&nbsp;□ 退出管理
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
