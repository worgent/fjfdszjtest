<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<script type="text/javascript">
        //站内消息初始化信息
		//onloadRegister(function() {megaboxx = new megaboxx()});
		var megaboxx_data={"folder":0};
		
		
		function fun_delete(pid){
		jConfirm('您确定删除该信息!', 'Confirmation Dialog', function(r) {	
    		if (r){
			   var url = '<%=path%>/system/message.do?action=delete&search.pid='+pid
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false ;
					oXMLDom.load(url);  
					var root;
					if (oXMLDom.parseError.errorCode != 0) {
						var myErr = oXMLDom.parseError;
						return;
					} else {
						root = oXMLDom.documentElement;
					}
					
					if (null != root){
						var rowSet = root.selectNodes("//delete");
					
						if(0<rowSet.item(0).selectSingleNode("value").text){
						    //jAlert("删除成功!",fuction(r){
						   window.location.href='<%=path%>/system/message.do?action=index';
						    //});
						    
						}
						else{
						    jAlert('删除失败!');  
						}
					}	
				}catch(e){ 
					jAlert(e,"提示");
				}	
			}
		});
		
		
		}
		</script>
	</head>
	<body>
		<div class="demo">
			<div id="tabs"> 
			    <!-- 导航 -->
			    <div>
			           <ul>
                       <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/system/message.do?action=index" title='发件箱'>发件箱</a></li>
                       <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/system/message.do?action=recmessage" title='收件箱'>收件箱</a></li>
                       <li style="float:left;margin:0px 10px 0 0;list-style-type:none;"><a href="/system/message.do?action=insertmessage" title='写站内信'>写站内信</a></li>
                       </ul>
                </div>
				<!-- 发件箱 -->
				<div>
					<!--消息具体内容 -->
					<table class="message_rows" id="megaboxx" width="100%" style="height: 90px; width: 990px;">
						<tbody>
							<s:iterator id="revList" value="%{pageList.objectList}">
								<tr id="thread_<s:property value="#revList.ID" />" class="">
									<td class="msg_icon">
										<a href="#" onclick="return $.megaboxx.status_menu_onclick(this, 'toggle_read', [955789751])">&nbsp;</a>
									</td>
									<td class="checkbox_toggle">
										<input type="checkbox" onclick="$.megaboxx.selection_onchange(this)" />
									</td>
									<td class="profile_pic">
										<a href="<%=path%>/system/message.do?action=viewmessage&search.pid=<s:property value="#revList.ID" />">
										<img src="***.jpg" width="50" />
										</a>
									</td>
									<td class="name_and_date">
										<span class="name">
										<a href="<%=path%>/system/message.do?action=viewmessage&search.pid=<s:property value="#revList.ID" />">
										<s:property value="#revList.RECNAME" />
										</a> </span>
										<span class="date"><s:property
												value="#revList.SENDTIME" />
										</span>
									</td>
									<td class="subject">
										<div class="subject_wrap">
											<a href="#nogo"
												onclick="$.megaboxx.read_message(<s:property value="#revList.ID" />,0);"
												class="subject_text"> <s:property value="#revList.TITLE" />
											</a>
											<div class="snippet_wrap">
												<a href="#nogo"
													onclick="$.megaboxx.read_message(<s:property value="#revList.ID" />,0);"
													class="snippet"><s:property value="#revList.TITLE" />
												</a>
											</div>
										</div>
									</td>
									<td class="delete_msg">
										<a href="javascript:fun_delete(<s:property value="#revList.ID" />)">删除</a>
									</td>
								</tr>
							</s:iterator>
							<td colspan="5">
								分页:
								<qzgf:pages value="%{pageList.pages}" />
							</td>
						</tbody>
					</table>
				</div>
		</div>
		<!-- End demo -->
	</body>
</html>