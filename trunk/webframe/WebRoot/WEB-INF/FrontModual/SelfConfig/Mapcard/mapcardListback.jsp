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
		$(function() {
			$("#accordion").accordion({
				fillSpace: true
			});
		});
		$(function() {
			$("#accordionResizer").resizable({
				resize: function() {
					$("#accordion").accordion("resize");
				},
				minHeight: 500
			});
		});
		</script>
	</head>
	<body>
		<!-- 头部热点商家信息 -->
		<div style="height: 290px; width: 990px;">
			<div style="height: 290px; width: 981px;" align="center">
				<div id="hotShop"
					style="margin: 10 8 5 8px; border: 1px solid #D8D8D8; background: #EAEAEA; width: 960px; height: 280px; vertical-align: middle;">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td width="375" height="250" align="left">
								    <s:iterator id="mapcardList" value="%{pageList.objectList}" status='st'>
										<div style="display:none" id="shopImg_<s:property value='#st.index'/>"><img width="100%" height="250" border="0" src ="<%=basePath%>img/admin/<s:property value="#mapcardList.MERCHANTICON"/>" /></div>
									</s:iterator>
							</td>
							<td width="430" height="250" valign="middle">
								<table width="100%" height="250">

								    <s:iterator id="mapcardList" value="%{pageList.objectList}" status='st'>
									<tr>
										<td width='420' id='hotImgs_0' height='75'
											onmouseover='(<s:property value='#st.index'/>,this);'
											style='border-bottom: 2px solid #fff'>
											<div class='font18'>
												<a href='<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'
													style='color: #F36F1A'><s:property value="#mapcardList.MERCHANTNAME" /></a>
											</div>
											<div style='padding-left: 2px;'>
												<s:property value="#mapcardList.MERCHANTINTRODUCE" />
												<a href='<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><span
													class='green'>详情》</span> </a>
											</div>
										</td>
									</tr>
									</s:iterator>
									<script>
									function showThisImg(id,mythis){
										$("#shopImg_"+id).show();
									}
									</script>
								</table>
							</td>
							<td width="155" style="border-left: 2px solid #fff" valign="top">
								<div style="padding-left: 6px; border: ">
									<div style=" text-align: center;">
										地图名片
									</div>
									<div style='line-height: 30px'>
										<a href="/richmap4/vip/index.jsp" target='_blank'
											class="fontG">什么是地图名片？<br>能给我带来什么？<br>收费标准是什么？<br>我要试用</a>
									</div>
									<div align="center" style='padding-top: 10px;'>
										<img src="" style='cursor: pointer;' onclick='self.open("/richmap4/vip/join.jsp")' />
										<br>
										<span>点击上面的按钮，<br>立即申请地图名片的试用版。</span>
									</div>
									<div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>




		<!-- 商品分类信息分组 -->
		<div class="demo" style="height: 90px; width: 990px;">
		<div id="accordionResizer" style="padding:10px; height: 90px; width: 990px;" class="ui-widget-content">
			<div id="accordion">
				<h3>
					<a href="#">好吃的</a>
				</h3>
				<div>
								    <s:iterator id="mapcardList" value="%{pageList.objectList.{?#this.MERCHANTTYPE=0}}" status='st'>
									<tr>
									   
										<td width='420' id='hotImgs_0' height='75'
											onmouseover='(<s:property value='#st.index'/>,this);'
											style='border-bottom: 2px solid #fff'>
											<div class='font18'>
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'
													style='color: #F36F1A'><s:property value="#mapcardList.MERCHANTNAME" /></a>
											</div>
											<div style='padding-left: 2px;'>
												<s:property value="#mapcardList.MERCHANTINTRODUCE" />
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><span
													class='green'>详情》</span> </a>
											</div>
										</td>
									
									</tr>
									</s:iterator>
				</div>
				<h3>
					<a href="#">买东西</a>
				</h3>
				<div>
								    <s:iterator id="mapcardList" value="%{pageList.objectList.{?#this.MERCHANTTYPE=1}}" status='st'>
									<tr>
									   
										<td width='420' id='hotImgs_0' height='75'
											onmouseover='(<s:property value='#st.index'/>,this);'
											style='border-bottom: 2px solid #fff'>
											<div class='font18'>
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'
													style='color: #F36F1A'><s:property value="#mapcardList.MERCHANTNAME" /></a>
											</div>
											<div style='padding-left: 2px;'>
												<s:property value="#mapcardList.MERCHANTINTRODUCE" />
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><span
													class='green'>详情》</span> </a>
											</div>
										</td>
									
									</tr>
									</s:iterator>
				</div>
				<h3>
					<a href="#">好玩的</a>
				</h3>
				<div>
								    <s:iterator id="mapcardList" value="%{pageList.objectList.{?#this.MERCHANTTYPE>0}}" status='st'>
									<tr>
									   
										<td width='420' id='hotImgs_0' height='75'
											onmouseover='(<s:property value='#st.index'/>,this);'
											style='border-bottom: 2px solid #fff'>
											<div class='font18'>
												<a href='<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'
													style='color: #F36F1A'><s:property value="#mapcardList.MERCHANTNAME" /></a>
											</div>
											<div style='padding-left: 2px;'>
												<s:property value="#mapcardList.MERCHANTINTRODUCE" />
												<a href='<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><span
													class='green'>详情》</span> </a>
											</div>
										</td>
									
									</tr>
									</s:iterator>
				</div>
				<h3>
					<a href="#">变漂亮</a>
				</h3>
				<div>
								    <s:iterator id="mapcardList" value="%{pageList.objectList.{?#this.MERCHANTTYPE>1}}" status='st'>
									<tr>
									   
										<td width='420' id='hotImgs_0' height='75'
											onmouseover='(<s:property value='#st.index'/>,this);'
											style='border-bottom: 2px solid #fff'>
											<div class='font18'>
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'
													style='color: #F36F1A'><s:property value="#mapcardList.MERCHANTNAME" /></a>
											</div>
											<div style='padding-left: 2px;'>
												<s:property value="#mapcardList.MERCHANTINTRODUCE" />
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><span
													class='green'>详情》</span> </a>
											</div>
										</td>
									
									</tr>
									</s:iterator>
			</div>
		</div>
		</div>
		<!-- End demo -->



		<!-- 产品服务 -->
		<div style="height: 90px; width: 990px;">
			<div style="height: 90px; width: 981px;" align="center">
				<div
					style="background-color: #FDFDFD; width: 975px; text-align: center; vertical-align: middle; padding: 10px;">
					<table width='98%'>
						<tr>
							<td width='20' class='green'>
								产品服务
							</td>
							<s:iterator id="mapcardList" value="%{pageList.objectList.{?#this.MERCHANTTYPE=1}}" status='st'>
							<td width='222' height='75' style='border-left: 1px solid #E4E4E4;' valign='top'>
								<table style='margin-left: 8px;'>
									<tr>
										<td rowspan='2'>
											<div style='border: 1px solid #d4d4d4;'>
												<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />'
													target='_blank'><img
														src="<%=basePath%>img/admin/<s:property value="#mapcardList.MERCHANTICON"/>"
														width='90' height='70' border='0' /> </a>
											</div>
										</td>
										<td valign='top'>
											<a href='<%=path%>/mapcard.do?action=detail&search.pid=<s:property value="#mapcardList.ID" />' target='_blank'><s:property value="#mapcardList.MERCHANTNAME" /></a>
										</td>
									</tr>
								</table>
							</td>
							</s:iterator>
						</tr>
					</table>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>