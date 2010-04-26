<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<html>
<head>
<script type="text/javascript">
        //站内消息初始化信息
		//onloadRegister(function() {megaboxx = new megaboxx()});
		var megaboxx_data={"folder":0};
		</script>
	</head>
	<body>
		<div class="demo">
			    <!-- 导航 -->
			    <div>
			           <ul>
                       <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/system/message.do?action=index" title='发件箱'>发件箱</a></li>
                       <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/system/message.do?action=recmessage" title='收件箱'>收件箱</a></li>
                       <li style="margin:0px 50px 0 0;list-style-type:none;"><a href="/system/message.do?action=insertmessage" title='写站内信'>写站内信</a></li>
                       </ul>
                </div>
				<!-- 提交写邮件信息 -->
					<form id="compose_message" action="/system/message.do?action=insertmessagedata" method="post" >
						<div>
							收件人：
							<input type="text" id="preccode" name="search.preccode" style="width: 80%" />
							<div id="preccodeTip" style="width: 300px"></div>
						</div>
						<div>
							主题&nbsp;&nbsp;&nbsp;&nbsp;：
							<input type="text" id="ptitle" name="search.ptitle" style="width: 80%" />
							<div id="ptitleTip" style="width: 300px"></div>
						</div>
						<div>
							内容&nbsp;&nbsp;&nbsp;&nbsp;：
							<textarea id="pcontent" name="search.pcontent"  style="width: 80%" cols="4" rows="7"></textarea>
							<div id="pcontentTip" style="width: 300px"></div>
						</div>
						<div>
						    <center>
						    <s:submit value="发送"></s:submit>
						    <s:reset value="重置"></s:reset>
							</center>
						</div>
					</form>
		</div>
		<!-- End demo -->
	</body>
</html>