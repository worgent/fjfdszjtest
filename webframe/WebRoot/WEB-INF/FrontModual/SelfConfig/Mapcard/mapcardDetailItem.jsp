<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@page import="com.qzgf.utils.comm.WebFrameUtil;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">

		</script>
	</head>
	<body>
	<!-- 测试 -->
	<form name="flpage" method="post" action="mapcard.do">
			<input type="hidden" name="search.pid" value='123'/>
			<input type="hidden" name="action" value='detailitem'/>
			<input value="搜索答案"  type="submit" name="search"/>
	</form>
		<!-- 页面导航 -->
		<div class="leaderF" align="left">
			<div class="leader">
				当前位置：
				<a href="http://www.richmap.cn/">首页</a> &gt;
				<a href="http://vip.richmap.cn/">地图名片</a> &gt;
				<a href="http://87602688.vip.richmap.cn/"><s:property value="mapcard.MERCHANTNAME"/></a> &gt; 产品详情
			</div>
		</div>
		<!-- 具体明细信息 -->
		<div class="center">
			<div class="left">
				<div class="dialog" style="width: 660px;">
					<div style="height: 5px; width: 660px;">
						<div style="height: 5px; width: 648px;"></div>
					</div>
					<div style="height: 230px; width: 660px;">
						<div style="height: 230px; width: 651px;">
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td style="padding-top: 3px;" valign="top" width="258"
										rowspan="2">
										<div style="padding: 2px; border: 1px solid #d3d3d3; width: 246px; height: 198px;">
											<img src='<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='product.SPECIALICON'/>' width='240' height='192'>
										</div>
									</td>
									<td width="430">
										<span style="font-size: 20px; font-weight: bold;"><s:property value="product.SPECIALNAME"/></span>
									</td>
								</tr>
								<tr>
									<td valign="top" width="420">
										<table width="100%">
											<tr>
												<td>
													<div style="padding: 3px;">
													</div>
													<div style="padding: 3px;">
														<font class="msg">标签：无</font>
													</div>
													<div style="padding: 3px;">
														<font class="msg">发布时间：<s:property value="product.CREATETIME"/></font>
													</div>
												</td>
												<td valign="top">
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<input type="image"
														src="namecard/images/live/favorite_pro.gif"
														onclick="addFavorite();">
													<input type="image" src="namecard/images/live/vote.jpg"
														style="cursor: pointer;" onClick="toVote('P');">
													<input type="image" src="namecard/images/live/addinfo.jpg"
														style="cursor: pointer;" onClick="addInfo();">
													<input type="image" src="namecard/images/live/msg.jpg"
														style="cursor: pointer;"
														onClick="window.showModalDialog(encodeURI('/com/ifc?act=tosendmsm&msg=<s:property value="product.SPECIALNAME"/>，参考价格：￥0.0，发布时间：<s:property value="product.CREATETIME"/>'), '','dialogWidth:500px;dialogHeight:460px;status:no;center:yes;help:no;minimize:no;maximize:no;scroll:no;')">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div style="height: 11px; width: 660px;">
						<div style="height: 11px; width: 648px;">
							&nbsp;
						</div>
					</div>
				</div>

				<div class="dialog" style="width: 660px;">
					<div style="height: 25px; width: 660px;">
						<div style="height: 25px; width: 648px;">
							<span class="green">产品介绍</span><span style="padding-left: 125px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div style="height: auto; width: 660px;">
						<div style="height: auto; width: 651px;">
							<div style="padding: 10px;">
								<s:property value="product.SPECIALINTRO"/>
							</div>
						</div>
					</div>
					<div style="height: 11px; width: 660px;">
						<div style="height: 11px; width: 648px;">
							&nbsp;
						</div>
					</div>
				</div>

				<div class="dialog" style="width: 660px;">
					<div style="height: 25px; width: 660px;">
						<div style="height: 25px; width: 648px;">
							<span class="green">用户留言</span><span style="padding-left: 125px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div style="height: auto; width: 660px;">
						<div style="height: auto; width: 651px;">
							<div id="remarkContent">
								正在载入。。。
							</div>
							<div style="float: right; padding-right: 25px;" id="remarkPage"></div>
						</div>
					</div>
					<div style="height: 11px; width: 660px;">
						<div style="height: 11px; width: 648px;">
							&nbsp;
						</div>
					</div>
				</div>

				<div class="dialog" style="width: 660px;">
					<div style="height: 25px; width: 660px;">
						<div style="height: 25px; width: 648px;">
							<span class="green">我要留言</span><span style="padding-left: 125px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div style="height: auto; width: 660px;">
						<div style="height: auto; width: 651px;">
							<form name="remark" method="post"
								action="http://www.richmap.cn/richmap4/log/uactrl"
								onSubmit="ajform:getMarkReturn">
								<input type="hidden" name="score1" id="score1" value="2">
								<input type="hidden" name="act" value="ADDCOMMENTS">
								<input type="hidden" name="linktype" value="P">
								<input type="hidden" name="linkid" value="BFEF6GV278QB">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td colspan="8" height="30" style="padding-left: 20px;">
											<span class="heiB">Tmp0123456789</span>
											<span class="msg">正在发表评论 提示：您可以点击下面的星星或格子来快速评分</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="60">
											留言：
										</td>
										<td width="120" id="myMark1">
											<span class="star_high"></span><span class="star_high"></span><span
												class="star_low"></span><span class="star_low"></span><span
												class="star_low"></span>
										</td>
									</tr>
									<tr>
										<td colspan="8" align="center">
											<textarea rows="5" style="width: 604px;" name="text"
												onFocus="this.select();">顺便说点什么？</textarea>
										</td>
									</tr>
									<tr>
										<td colspan="8" style="padding-left: 20px;">
											<table>
												<tr>
													<td>
														请输入验证码：
													</td>
													<td>
														<input name="checkcode" type="text" class="mytext"
															size="4" onFocus="showCheckCode(this)"
															onBlur="closeCheckCode();">
														<div class="rel"></div>
													</td>
													<td>
														<img src="namecard/images/live/ok.jpg"
															onClick="checkRemark()">
													</td>
												</tr>
											</table>
											<font class="msg">（温馨提示：单击上面的输入框会显示验证码哦！）</font>
										</td>
									</tr>
								</table>

							</form>
						</div>
					</div>
					<div style="height: 11px; width: 660px;">
						<div style="height: 11px; width: 648px;">
							&nbsp;
						</div>
					</div>
				</div>
			</div>

			<div class="right">
				<div class="dialog" style="width: 335px;">
					<div style="height: 25px; width: 335px;">
						<div style="height: 25px; width: 323px;">
							<span>所属商家</span><span style="padding-left: 125px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div style="height: 330px; width: 335px;">
						<div style="height: 330px; width: 326px;">
							<table width="98%" cellpadding="2" cellspacing="5" border="0">
								<tr>
									<td colspan="2">
										<A href="http://87602688.vip.richmap.cn/"><s:property value="mapcard.MERCHANTNAME"/></a>
									</td>
								</tr>
								<tr>
									<td valign="top" style="padding-top: 3px;">
										<A href="http://87602688.vip.richmap.cn/"><span> <img
													src="userImg/10048/4LJA87VK2LA8-180-135.jpg" width="80"
													height="80" border="0" name="slide" alt="<s:property value="mapcard.MERCHANTNAME"/>"> </span> </a>
									</td>
									<td valign="top">
										<table>
											<tr>
												<td align="right">
													总评:
												</td>
												<td>
													<img src='namecard/images/live/star_low.jpg'>
													<img src='namecard/images/live/star_low.jpg'>
													<img src='namecard/images/live/star_low.jpg'>
													<img src='namecard/images/live/star_low.jpg'>
													<img src='namecard/images/live/star_low.jpg'>
												</td>
											</tr>
											<tr>
												<td align="right">
													口味:<s:property value="mapcard.TASTEVALUE"/>
												</td>
												<td>
													<div class="level">
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
													</div>
												</td>
											</tr>
											<tr>
												<td align="right">
													服务:<s:property value="mapcard.SERVICEVALUE"/>
												</td>
												<td>
													<div class="level">
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
													</div>
												</td>
											</tr>
											<tr>
												<td align="right">
													环境:<s:property value="mapcard.ENVIRONMENTVALUE"/>
												</td>
												<td>
													<div class="level">
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
														<div class='level_blur'></div>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										地址：<s:property value="mapcard.MERCHANTADDRESS"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										详情：
										<A class="pub_link" href="<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="mapcard.ID" />"><s:property value="mapcard.ID" /></A>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="border-top: dotted 1px #EFA771;">
										<s:property value="mapcard.MERCHANTINTRODUCE"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</body>
</html>